package com.example.demo.rabbidmq;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.PaymentReceiptCqrsApplication;
import com.example.demo.command.model.PaymentReceiptCommand;
import com.example.demo.command.service.ErrMessageService;
import com.example.demo.common.ConvertModelCommandToView;
import com.example.demo.query.model.PaymentReceiptView;
import com.example.demo.query.repository.PaymentReceiptViewRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rabbitmq.client.*;

@Component
public class CreateMessageQueue {
	private static Connection connection;
	private static Channel channel;

	private static PaymentReceiptViewRepository paymentRepository;
	public static ErrMessageService mapErrMessage;
	private static final Logger LOGGER = LoggerFactory.getLogger(CreateMessageQueue.class);
	@Autowired
	public CreateMessageQueue(PaymentReceiptViewRepository paymentRepository) {
		CreateMessageQueue.paymentRepository = paymentRepository;
		CreateMessageQueue.mapErrMessage=mapErrMessage;
	}

	public static void init() {
		ConnectionFactory factory = new ConnectionFactory();
		// config rabbitmq address
		factory.setHost("52.230.4.199");
		factory.setPort(5672);
		factory.setUsername("guest");
		factory.setPassword("guest");
		try {
			connection = factory.newConnection();
			channel = connection.createChannel();
			// create exchange
			channel.exchangeDeclare("Create.exchange", "direct", true);
			// create queue
			channel.queueDeclare("Create.queue", true, false, false, null);
			// bind queue to exchange
			channel.queueBind("Create.queue", "Create.exchange", "Create.routingkey");

			// create consume. can put in other service
			boolean autoAck = false;
			channel.basicConsume("Create.queue", autoAck, "myCreateConsumerTag", new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
						byte[] body) throws IOException {
					long deliveryTag = envelope.getDeliveryTag();
					String message = new String(body, "UTF-8");
					System.out.println("Create-write side recieved message: " + message);
					channel.basicAck(deliveryTag, false);
					// process message
					PaymentReceiptCommand paymentReceipt;
					EventType evenType;
					/*
					 * phan gia message sang json
					 */
					try {
						Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
						MessageFormat messageFormat = gson.fromJson(message, MessageFormat.class);
						paymentReceipt = messageFormat.getData();
						evenType=messageFormat.getType();
					} catch (Exception e) {
						// TODO: handle exceptione
						produceMsg(message);
						LOGGER.error(e.getMessage());
						e.printStackTrace();
						return;
					}

					/*
					 * Buil dữ liệu từu model command sang model view
					 */

					PaymentReceiptView paymentView = ConvertModelCommandToView.convert(paymentReceipt);
					
					if(evenType.equals(EventType.CREATE)) {

						try {
							paymentRepository.insert(paymentView);
						} catch (Exception e) { // Yêu cầu gửi lại mesage do lỗi
							produceMsg(message);
							LOGGER.error(e.getMessage());
							e.printStackTrace();
							return;
						}
					}
					else if (evenType.equals(EventType.UPDATE)) {

						/*
						 * Kiêm tra xem có bản update trong map hay ko
						 */
						if (mapErrMessage.get(paymentReceipt.getRefID()) == null) {
							// không có bản version trước bị gửi lại
							PaymentReceiptView paymentOld = paymentRepository.findByRefID(paymentReceipt.getRefID());
							if (paymentOld == null) {
								// không tồn tại bản ghi==>lưu vào map yêu cầu gửi lại
								produceMsg(message);
								mapErrMessage.put(paymentReceipt.getRefID(), paymentReceipt.getVersion(),evenType);
								return;
							}
							paymentView.setId(paymentOld.getId());
							try {
								paymentRepository.save(paymentView);
							} catch (Exception e) {
								// Lỗi sảy ra khi lưu==>lưu vào map yêu cầu gửi lại
								produceMsg(message);
								LOGGER.error(e.getMessage());
								mapErrMessage.put(paymentReceipt.getRefID(), paymentReceipt.getVersion());
							}
							// thành công
							return;
						} else {
							/*
							 * //có bản version trước bị lỗi vào lưu lại trong map mesage.version ==
							 * map.version ==> chính là gói tin đã được gửi trc đó=> lưu vào database,xóa
							 * trong map mesage.version< map.version ==> gói tin này ko có ý nghĩa nữa =>
							 * hủy gói tin mesage.version>map.version ==>cập nhật lại map và yêu cầu gửi lại
							 * 
							 */
							Integer vesion = mapErrMessage.getVersion(paymentReceipt.getRefID());
							if (paymentReceipt.getVersion() < vesion) {
								// Hủy gói tin không ý nghía
								return;
							} else if (paymentReceipt.getVersion() == vesion) {
								try {
									paymentRepository.save(paymentView);
								} catch (Exception e) {
									// Lỗi sảy ra khi lưu==>lưu vào map yêu cầu gửi lại
									LOGGER.error(e.getMessage());
									produceMsg(message);
//				                			 mapErrMessage.put(paymentReceipt.getRefID(), paymentReceipt.getVersion());
									return;
								}
								// thành công và xoa trong map
								mapErrMessage.remove(paymentReceipt.getRefID());
								return;
							} else {
								// goi tin co y nghia hon
								mapErrMessage.put(paymentReceipt.getRefID(), paymentReceipt.getVersion());
							}
						}

					} else if (evenType.equals(EventType.DELETE)) {
						if (mapErrMessage.get(paymentReceipt.getRefID()) == null) {
							/*
							 * Khong map khong co==> thuc hien xoa
							 */
							PaymentReceiptView paymentOld = paymentRepository.findByRefID(paymentReceipt.getRefID());
							if (paymentOld != null) {
								try {
									paymentRepository.deleteByRefID(paymentReceipt.getRefID());
									// channel.basicAck(deliveryTag, false);
								} catch (Exception e) {
									// bi loi gui lai goi tin
									produceMsg(message);
									LOGGER.error(e.getMessage());
									e.printStackTrace();
								}
								return;
							} else {
								// gui lai
								produceMsg(message);
							}
						}
					}
				}
			});

		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public static boolean produceMsg(String msg) {
		try {
			channel.basicPublish("Create.exchange", "Create.routingkey", null, msg.getBytes("UTF-8"));
			System.out.println("Create-read side send message: " + msg);
			return true;
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

}
