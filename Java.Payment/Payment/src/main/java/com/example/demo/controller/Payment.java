package com.example.demo.controller;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.InvoiceDetailService;
import com.example.demo.Service.PaymentReceiptService;
import com.example.demo.Service.RefTypeService;
import com.example.demo.model.InvoiceDetail;
import com.example.demo.model.PaymentReceipt;
import com.example.demo.model.RefType;
import com.example.demo.repository.InvoiceDetailRepository;

@RestController
public class Payment {

//	@Autowired
//	RefTypeService refTypeService;
//	@Autowired
//	PaymentReceiptService paymentService;
//	@Autowired
//	InvoiceDetailService invoiceService;
//	@RequestMapping("/")
//	public String refTypeSave() {
//		
//		RefType refType=new RefType();
//		refType.setRefTypeID(1);
//		refType.setRefTypeName("chi");
//		refTypeService.Save(refType);
//		
//		RefType refType2=new RefType();
//		refType2.setRefTypeID(2);
//		refType2.setRefTypeName("thu");
//		refTypeService.Save(refType2);
//		
//		return"OK";
//	}
//	
//	
//	@RequestMapping("/payment")
//	public String refTypeDelete() {
//		Double x=Double.valueOf(1212.1212);
//		for(int i=0;i<100;i++) {
//			PaymentReceipt payment=new PaymentReceipt();
//			payment.setCreatedDate(new Date(new java.util.Date().getTime()));
//			payment.setAccountObjectAddress("company1"+i);
//			payment.setAccountObjectContactName("company1"+i);
//			payment.setAccountObjectID("company1"+i);
//			payment.setKeyCompany("company1"+i);
//			payment.setPostedDate(new Date(new java.util.Date().getTime()));
//			payment.setRefDate(new Date(new java.util.Date().getTime()));
//			payment.setPostedFinance(0);
//			RefType refType=new RefType();
//			refType.setRefTypeName("thu");
//			payment.setTotalAmount(x);
//			payment.setTotalAmountOC(x);
//			payment.setRef(refType);
//			paymentService.save(payment);
//		}
//		
//		
//		return"OK";
//	}
//	
//	@RequestMapping("/invoice")
//	public List<PaymentReceipt>  getPage() {
//		List<PaymentReceipt> payments=paymentService.getAlL();
//		Random rand=new Random();
//		for(int i=0;i<10;i++) {
//			InvoiceDetail invoiceDetail=new InvoiceDetail();
//			invoiceDetail.setAccountObjectID("acount"+i);
//	
//			Double x=Double.valueOf(1212.1212);
//			invoiceDetail.setAmount(x);
//			invoiceDetail.setAmountOC(x);
//			invoiceDetail.setDiscription("description"+i);
//			
//			PaymentReceipt pay=payments.get(5);
//			invoiceDetail.setPayment(pay);
//			invoiceService.save(invoiceDetail);
//		}
//		return payments;	
//	}
//	@PostMapping("/updateInvoice")
//	public String updateInvoice(@RequestBody InvoiceDetail invoice) {
//		invoiceService.update(invoice);
//		return "OK";
//		
//	}
//	@PostMapping("/deleteInvoice:{paymentID}")
//	public String deleteInvoice(@RequestBody InvoiceDetail invoice,@PathVariable String paymentID) {
//		invoiceService.delete(invoice,paymentID);
//		return "OK";
//	}
//	@RequestMapping("/getInvoiceDetailsByPaymentID:{paymentID}")
//	public List<InvoiceDetail> getInvoiceDetailsByPaymentID(@PathVariable String paymentID){
//		return invoiceService.findByPaymentID(paymentID);
//	}
//	@PostMapping("/updatePayment")
//	public String updatePayment(@RequestBody PaymentReceipt paymentReceipt) {
//		paymentService.update(paymentReceipt);
//		return "OK";
//	}
//	@PostMapping("/deletePayment")
//	public String deletePayment(@RequestBody PaymentReceipt paymentReceipt) {
//		paymentReceipt.setRef(null);
//		paymentService.delete(paymentReceipt);
//		return "OK";
//	}












}
