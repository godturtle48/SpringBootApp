package com.example.demo.rabbidmq;






import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.*;

public class ReportMessageQueue {

	private static Connection connection;
	private static Channel channel;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReportMessageQueue.class);
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
			channel.exchangeDeclare("Report.exchange", "direct", true);
			// create queue
			channel.queueDeclare("Report.queue", true, false, false, null);
			// bind queue to exchange
			channel.queueBind("Report.queue", "Report.exchange", "Report.routingkey");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
	}

	public static boolean produceMsg(String msg) {
		try {
			channel.basicPublish("Report.exchange", "Report.routingkey", null, msg.getBytes("UTF-8"));
			System.out.println("Create-read side send message: " + msg);
		} catch (IOException e) {
			
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return false;
		}
		return true;
	}
}
