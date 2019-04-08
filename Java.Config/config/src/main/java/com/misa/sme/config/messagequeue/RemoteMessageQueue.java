package com.misa.sme.config.messagequeue;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.misa.sme.config.model.PaymentDatabaseInfo;
import com.misa.sme.config.model.PaymentDatabaseOfUser;
import com.misa.sme.config.model.PaymentDatabaseServerInfo;
import com.misa.sme.config.service.PaymentDatabaseInfoService;
import com.misa.sme.config.service.PaymentDatabaseOfUserService;
import com.misa.sme.config.service.PaymentDatabaseServerInfoService;
import com.rabbitmq.client.*;

//cmd :rabbitmq-plugins enable rabbitmq_management --online
@Component
public class RemoteMessageQueue {
	private static Connection connection;
	private static Channel channel;
	private static PaymentDatabaseInfoService paymentDatabaseInfoService;
	private static PaymentDatabaseOfUserService paymentDatabaseOfUserService;
	private static PaymentDatabaseServerInfoService paymentDatabaseServerInfoService;

	@Autowired
	public void setPaymentDatabaseInfoService(PaymentDatabaseInfoService paymentDatabaseInfoService) {
		RemoteMessageQueue.paymentDatabaseInfoService=paymentDatabaseInfoService;
	}
	@Autowired
	public void setPaymentDatabaseOfUserService(PaymentDatabaseOfUserService paymentDatabaseOfUserService) {
		RemoteMessageQueue.paymentDatabaseOfUserService=paymentDatabaseOfUserService;
	}
	@Autowired
	public void setPaymentDatabaseServerInfoService(PaymentDatabaseServerInfoService paymentDatabaseServerInfoService) {
		RemoteMessageQueue.paymentDatabaseServerInfoService=paymentDatabaseServerInfoService;
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
		    channel.exchangeDeclare("Remote.exchange", "direct",true);			
			
		    channel.queueDeclare("Remote.queue",true,false,false,null);
		    channel.queueBind("Remote.queue", "Remote.exchange", "Remote.routingkey");
		    
		    boolean autoAck = false;
		    channel.basicConsume("Remote.queue", autoAck, "myConsumerTag",
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
		                	 if (message.equals("SEND_PAYMENT_CONFIG")) {
		                		 if (sendPaymentConfig()) System.out.println("send payment config success");
		                		 else  System.out.println("send payment config fail");
		                	 };		       	 
		                 }						 
		                 catch(Exception e) {
		                	 e.printStackTrace();
		                 }	
		                 finally{
		                	 channel.basicAck(deliveryTag, false);
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
	
	public static boolean sendPaymentConfig(){
		try{
			List<PaymentDatabaseInfo> listDb=paymentDatabaseInfoService.getAll();
			if(listDb==null) 
				throw new Exception("There is no payment database info");
			else {
				for (int i=0;i<listDb.size();i++) {
					String msg=new PaymentMessageContent(
						2,
						null,
						listDb.get(i)
					).toString();
					PaymentMessageQueue.produceMsg(msg);
				}
			}
			
			List<PaymentDatabaseOfUser> listDbOfUser=paymentDatabaseOfUserService.getAll();
			if(listDbOfUser==null) 
				throw new Exception("There is no database info of user");
			else {
				for (int i=0;i<listDbOfUser.size();i++) {
					String msg=new PaymentMessageContent(
						1,
						listDbOfUser.get(i),
						null
					).toString();
					PaymentMessageQueue.produceMsg(msg);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
		return true;		
	}
	
	public static boolean genaratePaymentDatabaseServerInfo(){
		int numServer=1;
		for (int i=0;i<numServer;i++) {
			PaymentDatabaseServerInfo paymentDatabaseServerInfo1 =new PaymentDatabaseServerInfo("mariadb-payment",""+(3306+i),"root","misasme");
			if (!paymentDatabaseServerInfoService.save(paymentDatabaseServerInfo1)) return false;
		}
		return true;
	}
}
	