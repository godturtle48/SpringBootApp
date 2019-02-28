package com.misa.sme.config.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.misa.sme.config.controller.TestController;
import com.misa.sme.config.messagequeue.PaymentMessageContent;
import com.misa.sme.config.messagequeue.PaymentMessageQueue;
import com.misa.sme.config.model.PaymentDatabaseInfo;
import com.misa.sme.config.model.PaymentDatabaseOfUser;
import com.misa.sme.config.model.PaymentDatabaseServerInfo;
import com.misa.sme.config.repository.PaymentDatabaseInfoRepository;
import com.misa.sme.config.repository.PaymentDatabaseOfUserRepository;
import com.misa.sme.config.repository.PaymentDatabaseServerInfoRepository;

@Service
public class PaymentDatabaseScale {
	@Autowired
	PaymentDatabaseInfoRepository paymentDatabaseInfoRepository;
	@Autowired
	PaymentDatabaseOfUserRepository paymentDatabaseOfUserRepository;
	@Autowired
	PaymentDatabaseServerInfoRepository paymentDatabaseServerInfoRepository;
	private static final int MAX_DATABASE_PER_SERVER=5;
	private static final int MAX_COMPANY_PER_SERVER=100000;
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);

	public boolean createCompany(String userId,String keycompany) {
		/*
		 * Kiem tra : db < so Server
		 * 		tao db moi voi server la sever co so luong db it nhat ( 0 db), tao company moi voi db do
		 * Neu khong
		 * 		Tim server it company nhat
		 * 		Kiem tra : so company cua server < maxDatabasePerServer 
		 * 			Tao db moi tren server do,tao company moi voi db do
		 * 		Neu khong
		 * 			Kiem tra: maxCompanyPerServer>=so company cua server >= maxDatabasePerServer 
		 * 				Tim Db it company nhat
		 * 				tao company moi voi db do
		 * 			Neu khong :	
		 * 				THAT BAI
		 * 
		 * query to show resulft: 
		 * 
		 * select paymentdatabaseinfo.*,paymentdatabaseserverinfo.host,paymentdatabaseserverinfo.port ,count(*) as numCompany from paymentdatabaseofuser,paymentdatabaseserverinfo,paymentdatabaseinfo where paymentdatabaseserverinfo.keyserver=paymentdatabaseinfo.keyserver and paymentdatabaseinfo.keydatabase=paymentdatabaseofuser.keydatabase Group by keydatabase Order by paymentdatabaseinfo.keyserver
		 */
		if (paymentDatabaseInfoRepository.count()<paymentDatabaseServerInfoRepository.count()) {
//			System.out.println("case1"); 			
			PaymentDatabaseServerInfo paymentDatabaseServerInfo =paymentDatabaseServerInfoRepository.getServerHaveMinNumDB();
			if (paymentDatabaseServerInfo==null) return false;
//			System.out.println("server :"+paymentDatabaseServerInfo);
					
			PaymentDatabaseInfo paymentDatabaseInfo =new PaymentDatabaseInfo(paymentDatabaseServerInfo);
			if (!paymentDatabaseInfoRepository.save(paymentDatabaseInfo)) return false;
//			System.out.println("db info :"+paymentDatabaseInfo);

			PaymentDatabaseOfUser paymentDatabaseOfUser=new PaymentDatabaseOfUser(userId,keycompany,paymentDatabaseInfo);
			if (!paymentDatabaseOfUserRepository.save(paymentDatabaseOfUser)) return false;
//			System.out.println("company info:" +paymentDatabaseOfUser); 
			sendDatabaseConfig(paymentDatabaseInfo);
			sendCompanyConfig(paymentDatabaseOfUser);
			return true;
		}
		else {
			PaymentDatabaseServerInfo paymentDatabaseServerInfo =paymentDatabaseServerInfoRepository.getServerHaveMinNumCompany();
			if (paymentDatabaseServerInfo==null) return false;
//			System.out.println("server :"+paymentDatabaseServerInfo);
			
			int numCompanyOfThisServer=paymentDatabaseServerInfoRepository.getNumCompany(paymentDatabaseServerInfo) ;
			if(numCompanyOfThisServer==-1) return false;
//			System.out.println("server have " +numCompanyOfThisServer+" company, min in servers");
			
			if (numCompanyOfThisServer<MAX_DATABASE_PER_SERVER) {
//				System.out.println("case2");
				PaymentDatabaseInfo paymentDatabaseInfo =new PaymentDatabaseInfo(paymentDatabaseServerInfo);
				if (!paymentDatabaseInfoRepository.save(paymentDatabaseInfo)) return false;
//				System.out.println("db info :"+paymentDatabaseInfo);
				
				PaymentDatabaseOfUser paymentDatabaseOfUser=new PaymentDatabaseOfUser(userId,keycompany,paymentDatabaseInfo);
				if(!paymentDatabaseOfUserRepository.save(paymentDatabaseOfUser)) return false;
//				System.out.println("company info:" +paymentDatabaseOfUser);
				sendDatabaseConfig(paymentDatabaseInfo);
				sendCompanyConfig(paymentDatabaseOfUser);
				return true;
			}
			if (MAX_DATABASE_PER_SERVER <= numCompanyOfThisServer && numCompanyOfThisServer < MAX_COMPANY_PER_SERVER) {
//				System.out.println("case3");				
				PaymentDatabaseInfo paymentDatabaseInfo= paymentDatabaseInfoRepository.getDatabaseHaveMinNumCompany(paymentDatabaseServerInfo);
//				System.out.println("db info :"+paymentDatabaseInfo);
				
				PaymentDatabaseOfUser paymentDatabaseOfUser=new PaymentDatabaseOfUser(userId,keycompany,paymentDatabaseInfo);
				if (!paymentDatabaseOfUserRepository.save(paymentDatabaseOfUser)) return false;
//				System.out.println("company info:" +paymentDatabaseOfUser);
				sendCompanyConfig(paymentDatabaseOfUser);
				return true;
			}
			logger.error("ERROR: Can't create company or database info");
			return false;
		}
	}
	
	private static void sendCompanyConfig(PaymentDatabaseOfUser paymentDatabaseOfUser) {
		logger.info("INFO: Create company --> success :"+paymentDatabaseOfUser);
		String msg=new PaymentMessageContent(
				1,
				paymentDatabaseOfUser,
				null
			).toString();
		PaymentMessageQueue.produceMsg(msg);
	}
	
	private static void sendDatabaseConfig(PaymentDatabaseInfo paymentDatabaseInfo) {
		logger.info("INFO: Create database --> success :"+ paymentDatabaseInfo);
		String msg=new PaymentMessageContent(
				2,
				null,
				paymentDatabaseInfo
			).toString();
			PaymentMessageQueue.produceMsg(msg);
	}
	
}
