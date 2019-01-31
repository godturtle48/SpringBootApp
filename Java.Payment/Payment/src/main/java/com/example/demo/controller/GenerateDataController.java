package com.example.demo.controller;



import java.sql.Date;
import java.sql.Ref;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.InvoiceDetailService;
import com.example.demo.Service.PaymentReceiptService;
import com.example.demo.Service.RefTypeService;
import com.example.demo.configAuthen.Authenticate;
import com.example.demo.model.InvoiceDetail;
import com.example.demo.model.PaymentReceipt;
import com.example.demo.model.RefType;

@RestController
public class GenerateDataController {

	
	@Autowired
	RefTypeService refService;
	@Autowired
	PaymentReceiptService payService;
	@Autowired
	InvoiceDetailService invoiceService;
	@RequestMapping("/ref")
	public String ref() {
		RefType ref=new RefType(1,"thu");
		RefType ref2=new RefType(2,"chi");
		refService.Save(ref);
		refService.Save(ref2);
		return "ref";
	}

	
	@RequestMapping("/payment:{keyCompany}")
	public String paymnet(@PathVariable String keyCompany,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
		
//		Authenticate.Auth(httpServletRequest, httpServletResponse); 
//		
//		
//		String keyCompany= (String) httpServletRequest.getAttribute("maSoThue");
//		String keyCompany="company2";
		List<RefType> ref=refService.findAll();
		Random rand=new Random();
		String[]	accountObjectAddresss= {"CTCP MISA","tap doan FPT","CT MSR","CT MSE","CT SME","CT DARR"};	
		String[]	accountObjectContactNames= {"misa@Gmail.com","group@Gmail.com","contact@Gmail.com","hoa@Gmail.com","manager@Gmail.com","stresy@Gmail.com"};	
		
		String[]	accountObjectNames= {"C MISA","FPT","MSR","SME","DARR","DARR"};	
		String[]	createdBys= {"Hoàng","Hà","Nam","Hiếu","Hùng","Ngọc"};	
		System.out.println(accountObjectAddresss[rand.nextInt(5)]);
		java.util.Date date=new java.util.Date();
				for(int i=1;i<1000;i++) {
				PaymentReceipt payment=new PaymentReceipt();
				payment.setAccountObjectAddress(accountObjectAddresss[rand.nextInt(5)]);
				payment.setAccountObjectContactName(accountObjectContactNames[rand.nextInt(5)]);
				payment.setAccountObjectID("accountObjectID"+i%10);
				payment.setAccountObjectName(accountObjectNames[rand.nextInt(5)]);
				payment.setCreatedBy(createdBys[rand.nextInt(5)]);
				payment.setCreatedDate(new Date(date.getTime()));
				payment.setDocumentInclude("documentInclude"+i%100+".doc");
				payment.setEditVersion(new Date(date.getTime()));
				payment.setJournalMemo("Chi đi chơi "+(i%12+1));
				payment.setKeyCompany(keyCompany);
				payment.setModifiedBy(createdBys[rand.nextInt(5)]);
				payment.setModifiedDate(new Date(date.getTime()));
				payment.setPostedDate(new Date(date.getTime()));
				payment.setRefDate(new Date(date.getTime()));
				payment.setRefNoFinance("CT"+i);
//				payment.setReasonTypeID(reasonTypeID);
				payment.setRefOrdef(i);
				payment.setTotalAmount(Double.valueOf(0.0));
				payment.setTotalAmountOC(Double.valueOf(0.0));
				payment.setRef(ref.get(i%2));
				payment.setPostedFinance(Integer.valueOf(1));
				payService.save(payment);
				}
		return "payment";
	}
	
	
	@GetMapping("/getref")
	public List<RefType> getref(){
		return refService.findAll();
	}
	
	@RequestMapping("/invoice:{keyCompany}")
	public String invoice(@PathVariable String keyCompany,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
		
		
		Random rand=new Random();
		String[]	accountObjectAddresss= {"CTCP MISA","tap doan FPT","CT MSR","CT MSE","CT SME","CT DARR"};	
		String[]	accountObjectContactNames= {"misa@Gmail.com","group@Gmail.com","contact@Gmail.com","hoa@Gmail.com","manager@Gmail.com","stresy@Gmail.com"};	
		
		String[]	accountObjectNames= {"C MISA","FPT","MSR","SME","DARR","DARR"};	
		String[]	createdBys= {"Hoàng","Hà","Nam","Hiếu","Hùng","Ngọc"};	
		List<PaymentReceipt> payments=payService.getPaymentReceiptOfCompany("company1");
		if(payments==null) {
			return "fails";
		}
		for(int i=0;i<1000;i++) {
			InvoiceDetail invoice=new InvoiceDetail();
			invoice.setAccountObjectID("accountObjectID"+i%10);
			invoice.setAmount(Double.valueOf(1000+i/100));
			invoice.setAmountOC(Double.valueOf(1000+i/1000));
			invoice.setDiscription("Trả lương nhân viên tháng"+(i%12+1));
			invoice.setPayment(payments.get(rand.nextInt(payments.size())));
			invoice.setSortOrder(i);
		invoiceService.save(invoice);
		}
		return "invoice";
	}
}
