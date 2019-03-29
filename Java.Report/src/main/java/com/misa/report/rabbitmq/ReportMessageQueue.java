package com.misa.report.rabbitmq;



import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.misa.report.common.ConvertToCashBook;
import com.misa.report.common.ConvertToGeneralLedger;
import com.misa.report.model.CACashbook;
import com.misa.report.model.GeneralLedger;
import com.misa.report.model.InvoiceDetail;
import com.misa.report.model.PaymentReceipt;
import com.misa.report.repository.CACashbookRepository;
import com.misa.report.repository.GeneralLedgerRepository;
import com.rabbitmq.client.*;
@Component
public class ReportMessageQueue {

	private static Connection connection;
	private static Channel channel;
	private static final Logger LOGGER = LoggerFactory.getLogger(ReportMessageQueue.class);
	private static GeneralLedgerRepository generalRepository;
	private static CACashbookRepository cashbookReposirory;
//	@Autowired
//	public static void setGeneralRepository(GeneralLedgerRepository generalRepository) {
//		ReportMessageQueue.generalRepository = generalRepository;
//	}
//	@Autowired
//	public static void setCashbookReposirory(CACashbookRepository cashbookReposirory) {
//		ReportMessageQueue.cashbookReposirory = cashbookReposirory;
//	}
	@Autowired
	public ReportMessageQueue(GeneralLedgerRepository generalRepository,CACashbookRepository cashbookReposirory) {
		ReportMessageQueue.generalRepository = generalRepository;
		ReportMessageQueue.cashbookReposirory = cashbookReposirory;
	}

	
	public static void init() {
	    ConnectionFactory factory = new ConnectionFactory();
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
			
		    boolean autoAck = false;
		    channel.basicConsume("Report.queue", autoAck, "myConsumerTag",
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
//		              =================================================
		                 /*
		                  * Xử lý code ở đây
		                  */
		                 
		                 System.out.println("receipt msg:" + message);
		                 PaymentReceipt paymentReceipt;
		                 EventType evenType;
		                 
		                 try {
								Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
								MessageForm messageFormat = gson.fromJson(message, MessageForm.class);
								paymentReceipt = messageFormat.getData();
								evenType=messageFormat.getType();
							} catch (Exception e) {
								// TODO: handle exceptione
								produceMsg(message);
								e.printStackTrace();
								LOGGER.error(e.getMessage());
								return;
							}
		                if(evenType.equals(EventType.CREATE)) {
		                	/*
		                	 * Ghi
		                	 */
		                	/*
		                	 * Ghi sổ quỹ chung
		                	 */
		                	
		                	try {
		                		if(cashbookReposirory.findByRefID(paymentReceipt.getRefID())==null) {
		                			
		                			CACashbook cashbook=ConvertToCashBook.convert(paymentReceipt);
		                			try {
		                				cashbookReposirory.insert(cashbook);
									} catch (Exception e) {
										/*
										 * Cho gui lai
										 */
										cashbookReposirory.insert(cashbook);
										produceMsg(message);
										 e.printStackTrace();
										 LOGGER.error(e.getMessage());
									}
		                		}
							} catch (Exception e) {
								produceMsg(message);
							 e.printStackTrace();
							 LOGGER.error(e.getMessage());
							}
		                	
		                	/*
		                	 * Ghi sổ chi tiết
		                	 */
		                	try {
		                		if(generalRepository.findByRefID(paymentReceipt.getRefID()).size()==0) {
		                			GeneralLedger general=ConvertToGeneralLedger.convert(paymentReceipt);
		                			for(InvoiceDetail invoice :paymentReceipt.getInvoices()) {
		                				
		                				if (paymentReceipt.getRef().getRefTypeID()==1) {
		                					//Tai khoan no la chi
		                					general.setDebitAmount(invoice.getAmount());
		                					general.setDebitAmountOC(invoice.getAmountOC());
		                					general.setCreditAmount(Double.valueOf(0));
		                					general.setCreditAmountOC(Double.valueOf(0));
		                				}
		                				else if(paymentReceipt.getRef().getRefTypeID()==2) {
		                					/*
		                					 * Tai khoan co la thu
		                					 */
		                					general.setCreditAmount(invoice.getAmount());
		                					general.setCreditAmountOC(invoice.getAmountOC());
			                				general.setDebitAmount(Double.valueOf(0));
			                				general.setDebitAmountOC(Double.valueOf(0));
		                				}
		                				
//		                				general.setAccountNumber(accountNumber);
//		                				general.setCorrespondingAccountNumber(correspondingAccountNumber);		
		                				general.setRefDetailID(invoice.getRefDetailID());
		                				
		                				
		                				
		                				try {
		                					if(generalRepository.findByRefDetailID(invoice.getRefDetailID())==null) {
		                						generalRepository.insert(general);
		                					}
			                				
										} catch (Exception e) {
											/*
											 * Cho gui lai
											 */
											produceMsg(message);
											 e.printStackTrace();
											 LOGGER.error(e.getMessage());
										}
		                				
		                			}
		                			
		                		}
							} catch (Exception e) {
								produceMsg(message);
							 e.printStackTrace();
							 LOGGER.error(e.getMessage());
							}
		                	
		                	
		                	
		                	
		                }else if(evenType.equals(EventType.DELETE)) {
		                	/*
		                	 * Bỏ ghi
		                	 */
		                	
		                	try {
		                		if(cashbookReposirory.findByRefID(paymentReceipt.getRefID())!=null) {
		                			System.err.println("Vao bo ghi");
		                			try {
		                				cashbookReposirory.deleteByRefID(paymentReceipt.getRefID());
									} catch (Exception e) {
										/*
										 * Cho gui lai
										 */
										produceMsg(message);
										 e.printStackTrace();
										 LOGGER.error(e.getMessage());
									}
		                		}
		                		if(generalRepository.findByRefID(paymentReceipt.getRefID()).size()!=0) {
		                			try {
		                				generalRepository.deleteByRefID(paymentReceipt.getRefID());
									} catch (Exception e) {
										/*
										 * Cho gui lai
										 */
										produceMsg(message);
										 e.printStackTrace();
										 LOGGER.error(e.getMessage());
									}
		                		}
							} catch (Exception e) {
								produceMsg(message);
							 e.printStackTrace();
							 LOGGER.error(e.getMessage());
							}
		                	
		                	try {
		                		if(generalRepository.findByRefID(paymentReceipt.getRefID()).size()!=0) {
		                			
		                				
		                				try {
		                					generalRepository.deleteByRefID(paymentReceipt.getRefID());		                				
										} catch (Exception e) {
											/*
											 * Cho gui lai
											 */
											produceMsg(message);
											 e.printStackTrace();
											 LOGGER.error(e.getMessage());
										}
		                				   			
		                		}
							} catch (Exception e) {
								produceMsg(message);
							 e.printStackTrace();
							 LOGGER.error(e.getMessage());
							}
		                	
		                	
		                }
		              
		                 channel.basicAck(deliveryTag, false);
		             }
		         });  
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		} catch (TimeoutException e) {
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
