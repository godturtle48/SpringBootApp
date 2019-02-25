package com.webencyclop.demo.ConfigMessageQueue;


import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.*;

//cmd :rabbitmq-plugins enable rabbitmq_management --online
@Component
public class ConfigMessageQueue {
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void produceMsg(String userId,String keycompany){
	    try {
	    	JSONObject obj=new JSONObject();
	    	obj.put("userId", userId);
	    	obj.put("keycompany", keycompany);
	    	String msg=obj.toJSONString();
			channel.basicPublish("Register.exchange", "Register.routingkey", null, msg.getBytes("UTF-8"));
			System.out.println("Register Service send message: " + msg);
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	
}