package com.misa.sme.config.messagequeue;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.misa.sme.config.service.PaymentDatabaseScale;
import com.rabbitmq.client.*;

//cmd :rabbitmq-plugins enable rabbitmq_management --online
@Component
public class RegisterMessageQueue {
	private static Connection connection;
	private static Channel channel;

	static PaymentDatabaseScale scaleService;
	@Autowired
	public void setScaleService(PaymentDatabaseScale scaleService) {
		this.scaleService=scaleService;
	}
	public static void init() {
	    ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("rabbit1");	
	    factory.setPort(5672);
	    factory.setUsername("guest");
	    factory.setPassword("guest");
		try {
			connection =  factory.newConnection();
		    channel =  connection.createChannel();
		    channel.exchangeDeclare("Register.exchange", "direct",true);			
			
		    channel.queueDeclare("Register.queue",true,false,false,null);
		    channel.queueBind("Register.queue", "Register.exchange", "Register.routingkey");
		    
		    boolean autoAck = false;
		    channel.basicConsume("Register.queue", autoAck, "myConsumerTag",
		         new DefaultConsumer(channel) {
		             @Override
		             public void handleDelivery(String consumerTag,
		                                        Envelope envelope,
		                                        AMQP.BasicProperties properties,
		                                        byte[] body)
		                 throws IOException
		             {
		                 long deliveryTag = envelope.getDeliveryTag();
		                 String message = new String(body, "UTF-8");
		                 System.out.println("Config Service recieved message: " + message);
		                 try {
		                	 JSONParser parser = new JSONParser();
							 JSONObject content = (JSONObject) parser.parse(message);
							 String userId=(String)content.get("userId");
							 String keycompany=(String)content.get("keycompany");

							 if(scaleService.createCompany(userId, keycompany)) {
								 channel.basicAck(deliveryTag, false);
							 }else {
								 System.out.println("create database for company : fail");
							 }
		                 }
		                 catch(Exception e) {
		                	 e.printStackTrace();
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
}
	