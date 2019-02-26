package com.example.demo.rabbidmq;

	

import java.io.IOException;
import java.util.concurrent.TimeoutException;


import com.example.demo.query.repository.PaymentReceiptViewRepository;
import com.rabbitmq.client.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class MessageQueue {
	
	
	private static PaymentReceiptViewRepository paymentReceiptViewRepository;
    @Autowired
    public void setTodoViewService(PaymentReceiptViewRepository paymentReceiptViewRepository){
        this.paymentReceiptViewRepository = paymentReceiptViewRepository;
    }
	
	private static Connection connection;
	private static Channel channel;
	public static void init() {
	    ConnectionFactory factory = new ConnectionFactory();
	    // config rabbitmq address
	    factory.setHost("localhost");	
	    factory.setPort(5672);
	    factory.setUsername("guest");
	    factory.setPassword("guest");
		try {
			connection =  factory.newConnection();
		    channel =  connection.createChannel();
		    //create exchange
		    channel.exchangeDeclare("Payment.exchange", "direct",true);			
			// create queue
		    channel.queueDeclare("Payment.queue",true,false,false,null);
		    // bind queue to exchange
		    channel.queueBind("Payment.queue", "Payment.exchange", "Payment.routingkey");
		    
		    // create consume. can put in other service
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
		                 // process message
		                 try {
							JSONParser parser=new JSONParser();
							JSONObject obj=(JSONObject)parser.parse(message);
							
							
							
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
		                 
		                
		                 
		                 
		                 System.out.println("Service B recieved message: " + message);
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
	
	public static void xly() {
		
	}
	public static void produceMsg(String msg){
	    try {
			channel.basicPublish("Payment.exchange", "Payment.routingkey", null, msg.getBytes("UTF-8"));//publish message
			System.out.println("Service A send message: " + msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		    	   			 
	}	
	
}
