package com.example.demo.configmessagequeue;


import java.io.IOException;

import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

@Component
public class RemoteMessageQueue {
	private static Connection connection;
	private static Channel channel;
	
	public static void init() {
	    ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");	
	    factory.setPort(5672);
	    factory.setUsername("guest");
	    factory.setPassword("guest");
		try {
			connection =  factory.newConnection();
		    channel =  connection.createChannel();
		  }						 
        catch(Exception e) {
       	 e.printStackTrace();
        }	
	}
	public static void produceMsg(String msg){
	    try {
			channel.basicPublish("Remote.exchange", "Remote.routingkey", null, msg.getBytes("UTF-8"));
			System.out.println("Payment Service send message: " + msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		    	   			 
	}	

}