package com.misa.report.config;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

//cmd :rabbitmq-plugins enable rabbitmq_management --online
//@Component
public class ConfigMessageQueue {
	private static Connection connection;
	private static Channel channel;
	
	public static void init() {
	    ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("rabbit1");	
	    factory.setPort(5672);
	    factory.setUsername("guest");
	    factory.setPassword("guest");
		try {
			connection =  factory.newConnection();
		    channel =  connection.createChannel();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}