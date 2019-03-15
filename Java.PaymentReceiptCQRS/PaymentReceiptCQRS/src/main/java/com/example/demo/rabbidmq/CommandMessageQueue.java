package com.example.demo.rabbidmq;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.hibernate.SessionFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.command.model.PaymentReceiptCommand;
import com.example.demo.command.repository.PaymentReceiptCommandRepository;
import com.example.demo.common.ConvertModelCommandToView;
import com.example.demo.query.model.PaymentReceiptView;
import com.example.demo.query.repository.PaymentReceiptViewRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rabbitmq.client.*;
@Component
public class CommandMessageQueue {
	public static Map<String, Integer> mapErrMessage = new HashMap<>();
	private static Connection connection;
	private static Channel channel;

	private static PaymentReceiptViewRepository paymentRepository;

	@Autowired
	public void setTodoViewService(PaymentReceiptViewRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
	}

	public static void init() {
		ConnectionFactory factory = new ConnectionFactory();
		// config rabbitmq address
		factory.setHost("rabbit1");
		factory.setPort(5672);
		factory.setUsername("guest");
		factory.setPassword("guest");
		try {
			connection = factory.newConnection();
			channel = connection.createChannel();
			// create exchange
			channel.exchangeDeclare("Command.exchange", "direct", true);
			// create queue
			channel.queueDeclare("Command.queue", true, false, false, null);
			// bind queue to exchange
			channel.queueBind("Command.queue", "Command.exchange", "Command.routingkey");

			// create consume. can put in other service
			boolean autoAck = false;
			channel.basicConsume("Command.queue", autoAck, "myConsumerTag", new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
						byte[] body) throws IOException {
					long deliveryTag = envelope.getDeliveryTag();
					String message = new String(body, "UTF-8");
					// process message
					System.out.println("Create-write side recieved message: " + message);
					channel.basicAck(deliveryTag, false);

					// su ly du lieu tu message nhan duoc trong queue chua du lieu xoa va sua
					PaymentReceiptCommand paymentReceipt;
					EventType evenType;
					try {
						Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
						MessageFormat messageFormat = gson.fromJson(message, MessageFormat.class);
						paymentReceipt = messageFormat.getData();
						evenType=messageFormat.getType();
					} catch (Exception e) {
						// TODO: handle exceptione
						produceMsg(message);
						e.printStackTrace();
						return;
					}
					/*
					 * Xu ly loai command
					 */

					/*
					 * Buil dữ liệu từu model command sang model view
					 */

					PaymentReceiptView paymentView = ConvertModelCommandToView.convert(paymentReceipt);

					if (evenType.equals(EventType.UPDATE)) {

						/*
						 * Kiêm tra xem có bản update trong map hay ko
						 */
						if (mapErrMessage.get(paymentReceipt.getRefID()) == null) {
							// không có bản version trước bị gửi lại
							PaymentReceiptView paymentOld = paymentRepository.findByRefID(paymentReceipt.getRefID());
							if (paymentOld == null) {
								// không tồn tại bản ghi==>lưu vào map yêu cầu gửi lại
								produceMsg(message);
								mapErrMessage.put(paymentReceipt.getRefID(), paymentReceipt.getVersion());
								return;
							}
							paymentView.setId(paymentOld.getId());
							try {
								paymentRepository.save(paymentView);
							} catch (Exception e) {
								// Lỗi sảy ra khi lưu==>lưu vào map yêu cầu gửi lại
								produceMsg(message);
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
							Integer vesion = mapErrMessage.get(paymentReceipt.getRefID());
							if (paymentReceipt.getVersion() < vesion) {
								// Hủy gói tin không ý nghía
								return;
							} else if (paymentReceipt.getVersion() == vesion) {
								try {
									paymentRepository.save(paymentView);
								} catch (Exception e) {
									// Lỗi sảy ra khi lưu==>lưu vào map yêu cầu gửi lại
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
									channel.basicAck(deliveryTag, false);
								} catch (Exception e) {
									// bi loi gui lai goi tin
									produceMsg(message);
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
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void produceMsg(String msg) {
		try {
			channel.basicPublish("Command.exchange", "Command.routingkey", null, msg.getBytes("UTF-8"));
			System.out.println("Update-read side send message: " + msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
