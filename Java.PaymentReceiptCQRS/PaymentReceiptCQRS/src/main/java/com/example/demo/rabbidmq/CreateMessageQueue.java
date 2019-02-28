package com.example.demo.rabbidmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.command.model.PaymentReceiptCommand;
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
	
	@Autowired
	public CreateMessageQueue(PaymentReceiptViewRepository paymentRepository) {
		CreateMessageQueue.paymentRepository = paymentRepository;
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
			channel.exchangeDeclare("Create.exchange", "direct", true);
			// create queue
			channel.queueDeclare("Create.queue", true, false, false, null);
			// bind queue to exchange
			channel.queueBind("Create.queue", "Create.exchange", "Create.routingkey");

			// create consume. can put in other service
			boolean autoAck = false;
			channel.basicConsume("Create.queue", autoAck, "myConsumerTag", new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
						byte[] body) throws IOException {
					long deliveryTag = envelope.getDeliveryTag();
					String message = new String(body, "UTF-8");
					// process message
					PaymentReceiptCommand paymentReceipt;

					/*
					 * phan gia message sang json
					 */
					try {

						Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
						MessageFormat messageFormat = gson.fromJson(message, MessageFormat.class);
						paymentReceipt = messageFormat.getData();

					} catch (Exception e) {
						// TODO: handle exceptione
						e.printStackTrace();
						return;
					}

					/*
					 * Buil dữ liệu từu model command sang model view
					 */

					PaymentReceiptView paymentView = ConvertModelCommandToView.convert(paymentReceipt);
					try {
						paymentRepository.insert(paymentView);
					} catch (Exception e) { // Yêu cầu gửi lại mesage do lỗi
						e.printStackTrace();
						return;
					}
					System.out.println("Create-write side recieved message: " + message);
					channel.basicAck(deliveryTag, false);
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
			channel.basicPublish("Create.exchange", "Create.routingkey", null, msg.getBytes("UTF-8"));
			System.out.println("Create-read side send message: " + msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
