package com.example.demo.configmessagequeue;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.*;


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
		    boolean autoAck = false;
		    channel.basicConsume("Payment.queue", autoAck, "myConsumerTag",
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
		                 try {
			                JSONParser parser = new JSONParser();
							JSONObject content = (JSONObject) parser.parse(message);							
							
							long contentType= (long)content.get("type");
							
							if (contentType==1) {
								JSONObject userInfo=(JSONObject) content.get("paymentDatabaseOfUser");
								TenantInfo.databaseMap.put((String) userInfo.get("keycompany"),(String) userInfo.get("keydatabase"));
								TenantInfo.userMap.put((String) userInfo.get("keycompany"),(String) userInfo.get("userId"));
								
							}
				
							if (contentType==2) {
								JSONObject databaseInfo = (JSONObject) content.get("paymentDatabaseInfo");
								TenantInfo.sessionFactoryMap.put(
										(String) databaseInfo.get("keydatabase"),
										TenantConfig.create(
												true,
												(String) databaseInfo.get("host"),
												(String) databaseInfo.get("port"),
												(String) databaseInfo.get("keydatabase"),
												(String) databaseInfo.get("username"),
												(String) databaseInfo.get("password"))
										);								
							}						

						} catch (ParseException e) {
							e.printStackTrace();
						}
		                 System.out.println(TenantInfo.databaseMap.size());
		                 System.out.println(TenantInfo.sessionFactoryMap.size());
		                 System.out.println("Payment Service recieved message: " + message);
		                 channel.basicAck(deliveryTag, false);
		             }
		         });  
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
	}
	
}