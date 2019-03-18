package com.misa.sme.config.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.misa.sme.config.messagequeue.PaymentMessageContent;
import com.misa.sme.config.messagequeue.PaymentMessageQueue;
import com.misa.sme.config.model.PaymentDatabaseInfo;
import com.misa.sme.config.model.PaymentDatabaseOfUser;
import com.misa.sme.config.model.PaymentDatabaseServerInfo;
import com.misa.sme.config.repository.PaymentDatabaseInfoRepository;
import com.misa.sme.config.repository.PaymentDatabaseServerInfoRepository;
import com.misa.sme.config.service.PaymentDatabaseInfoService;
import com.misa.sme.config.service.PaymentDatabaseOfUserService;
import com.misa.sme.config.service.PaymentDatabaseScale;
import com.misa.sme.config.service.PaymentDatabaseServerInfoService;


@RestController
public class TestController {
	@Autowired
	PaymentDatabaseInfoService paymentDatabaseInfoService;
	@Autowired
	PaymentDatabaseServerInfoService paymentDatabaseServerInfoService;
	@Autowired
	PaymentDatabaseOfUserService paymentDatabaseOfUserService;
	@Autowired
	PaymentDatabaseScale paymentDatabaseScale;
	private final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@RequestMapping("/genarate-company")
	public String genarateScale(){
		try {
			int numCompany=3000;
			int numCompanyOfUser=3;
			
			for(int i=0;i<numCompany;i++) {
				paymentDatabaseScale.createCompany(""+(i/numCompanyOfUser), "company"+i);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return "FAIL";
		}
		return "OK" ;
	}
//	
//	@RequestMapping("/sendPaymentConfig")
//	public String sendPaymentConfig(){
//		try{
//			List<PaymentDatabaseInfo> listDb=paymentDatabaseInfoService.getAll();
//			if(listDb==null) 
//				throw new Exception("There is no payment database info");
//			else {
//				for (int i=0;i<listDb.size();i++) {
//					String msg=new PaymentMessageContent(
//						2,
//						null,
//						listDb.get(i)
//					).toString();
//					PaymentMessageQueue.produceMsg(msg);
//				}
//			}
//			
//			List<PaymentDatabaseOfUser> listDbOfUser=paymentDatabaseOfUserService.getAll();
//			if(listDbOfUser==null) 
//				throw new Exception("There is no database info of user");
//			else {
//				for (int i=0;i<listDbOfUser.size();i++) {
//					String msg=new PaymentMessageContent(
//						1,
//						listDbOfUser.get(i),
//						null
//					).toString();
//					PaymentMessageQueue.produceMsg(msg);
//				}
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//			return "WARNNING";
//		}
//		
//		return "OK";		
//	}

}
