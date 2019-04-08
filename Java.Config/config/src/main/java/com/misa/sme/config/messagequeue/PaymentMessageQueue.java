package com.misa.sme.config.messagequeue;


import java.io.IOException;
import java.util.concurrent.TimeoutException;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.*;

//cmd :rabbitmq-plugins enable rabbitmq_management --online
@Component
public class PaymentMessageQueue {
	private static Connection connection;
	private static Channel channel;
	
	public static void init() {
	    ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("10.108.179.29");	
	    factory.setPort(5672);
	    factory.setUsername("guest");
	    factory.setPassword("guest");
		try {
			connection =  factory.newConnection();
		    channel =  connection.createChannel();
		    channel.exchangeDeclare("Payment.exchange", "fanout",true);			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void produceMsg(String msg){
	    try {
			channel.basicPublish("Payment.exchange", "", null, msg.getBytes("UTF-8"));
			System.out.println("Config Service send message: " + msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		    	   			 
	}	
	
	
}