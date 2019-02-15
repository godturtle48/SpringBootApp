package com.example.demo.controller;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.InvoiceDetailService;
import com.example.demo.Service.PaymentReceiptService;
import com.example.demo.Service.RefTypeService;
import com.example.demo.configAuthen.Authenticate;
import com.example.demo.model.InvoiceDetail;
import com.example.demo.model.PaymentReceipt;
import com.example.demo.model.RefType;
import com.example.demo.repository.InvoiceDetailRepository;

@RestController
public class PaymentReceiptController {

	@Autowired
	RefTypeService refTypeService;
	@Autowired
	PaymentReceiptService paymentService;
	@Autowired
	InvoiceDetailService invoiceService;
	@RequestMapping("/getAllPage_Size:{size}")
	public Map<String, Object> getAllPage(@PathVariable("size")int size,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
//		Authenticate.Auth(httpServletRequest, httpServletResponse); 
		String keyCompany= "company1";
		System.out.println("size:"+size);
		long totalPage = 0;
		Map<String, Object> map = new HashMap<>();
		long x =  paymentService.count(keyCompany);
		System.out.println(x);
		if(x==0) {
			map.put("totalRecord", x);
			map.put("data", null);
			return map;
		}
		List<PaymentReceipt> lst = paymentService.getPaymentReceiptOfCompanyPage(keyCompany, 0, size);
		map.put("totalRecord", x);
		map.put("data", lst);
		return map;
	}
	
	
	
	@RequestMapping("/getPage/page:{page}_size:{size}")
	public List<PaymentReceipt> get(@PathVariable("page") long page,@PathVariable("size") int size,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
		System.out.println(page);
		System.out.println(size);

		String keyCompany= "company1";

		long countRecord =  paymentService.count(keyCompany);
		
		long index=(page-1)*size;
		if(index>countRecord) {
			return null;
		}
		List<PaymentReceipt> lst=paymentService.getPaymentReceiptOfCompanyPage(keyCompany, index, size);
		return lst;
		
	}
	
	
	
	
	
	//them chung tu
	@PostMapping("/addRef")
	public Map<String, Object> addRef(@RequestBody PaymentReceipt payment,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){
		Map<String, Object> map=new HashMap<String, Object>();
		
		//Authenticate.Auth(httpServletRequest, httpServletResponse); 
		
		
		String keyCompany= "company1";
		double totalAmountReturn = 0;
		if(payment.getRef()==null) {
			
			map.put("error", "RefType not empty!");
			return map;
		}
		
		
		
		if(payment.getPostedDate()==null) {

			map.put("error", "PostedDate không được để trống");
			return map;
		}
		if(payment.getRefDate()==null) {

			map.put("error", "RefDate không được để trống");
			return map;
		}

		if(payment.isPostedFinance()==null) {
			payment.setPostedFinance(Integer.valueOf(0));
		}
		
		
		payment.setKeyCompany(keyCompany);
		payment.setCreatedDate(new Date(new java.util.Date().getTime()));
		RefType ref=refTypeService.findByID(payment.getRef().getRefTypeID());
		if(ref==null) {
			refTypeService.Save(payment.getRef());
		}else {
			payment.setRef(ref);
		}
		
		if(payment.getInvoices()==null) {
			payment.setTotalAmount(Double.valueOf(0));
			payment.setTotalAmountOC(Double.valueOf(0));		
		}else {
			double amount=0;
			List<InvoiceDetail> invoices=payment.getInvoices();
			
			for(int i=0;i<payment.getInvoices().size();i++) {
				if(payment.getInvoices().get(i).getAmountOC()==null) {
					map.put("error", "Invoice không hợp lệ");
				return map;	
				}	
				//them ne status =0 unmodify
				payment.getInvoices().get(i).setStatus(0);
				//tinh totalAmountOC
				amount+=payment.getInvoices().get(i).getAmountOC().doubleValue();
				//cap nhat amount cho moi invoice
				if(payment.getExchangeRate()==null) payment.getInvoices().get(i).setAmount(Double.valueOf(0));
				else {
					
					double amountInvoice=payment.getExchangeRate().doubleValue()*payment.getInvoices().get(i).getAmountOC().doubleValue();
					payment.getInvoices().get(i).setAmount(Double.valueOf(amountInvoice));
					
				}
			}

			
			payment.setTotalAmountOC(Double.valueOf(amount));
			if(payment.getExchangeRate()==null) payment.setTotalAmount(Double.valueOf(0));
			else {
				
				double totalAmount=payment.getExchangeRate().doubleValue()*payment.getTotalAmountOC().doubleValue();
				payment.setTotalAmount(Double.valueOf(totalAmount));
			}
			totalAmountReturn = amount;
		}
	
		
		int status=paymentService.save(payment);
		if(status==1) {
			map.put("message", "success!");
			map.put("totalAmount", totalAmountReturn);
			httpServletResponse.setStatus(200);
		}
		else map.put("error", "fail!");
		//map.put("message", "success!");
		return map;
	}
	
	
		//sua Ref
		@PostMapping("/updateRef")
		public Map<String, Object> updateRef(@RequestBody PaymentReceipt payment,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){
				Map<String, Object> map=new HashMap<String, Object>();
			//	Authenticate.Auth(httpServletRequest, httpServletResponse); 
				
				
				String keyCompany= "company1";
						//(String) httpServletRequest.getAttribute("maSoThue");
				if(payment.getRef()==null) {
					map.put("error", "RefType not empty!");
					return map;
				}
				
				if(payment.getPostedDate()==null) {

					map.put("error", "PostedDate khong duoc de trong");
					return map;
				}
				if(payment.getRefDate()==null) {

					map.put("error", "RefDate khong duoc de trong");
					return map;
				}
				
				if(payment.isPostedFinance()==null) {
					payment.setPostedFinance(Integer.valueOf(0));
				}
				
				
				
				payment.setKeyCompany(keyCompany);
				
				RefType ref=refTypeService.findByID(payment.getRef().getRefTypeID());
				if(ref==null) {
					refTypeService.Save(payment.getRef());
				}else {
					payment.setRef(ref);
				}

				if(payment.getInvoices()==null) {
					payment.setTotalAmount(Double.valueOf(0));
					payment.setTotalAmountOC(Double.valueOf(0));
				}else {
					double amount=0;
					List<InvoiceDetail> invoices=payment.getInvoices();
					for(int i=0;i<payment.getInvoices().size();i++) {
						if(payment.getInvoices().get(i).getAmountOC()==null&&payment.getInvoices().get(i).getStatus()!=2) {
							map.put("error", "Invoice thứ "+i+"không hợp lệ!");
						return map;	
						}	
						if(payment.getInvoices().get(i).getStatus()==0||payment.getInvoices().get(i).getStatus()==1||payment.getInvoices().get(i).getStatus()==3) {	
							//0 khong thay doi,1 sua ,2 xoa,3 them
							
							amount+=payment.getInvoices().get(i).getAmountOC().doubleValue();
							
							if(payment.getExchangeRate()==null) payment.getInvoices().get(i).setAmount(Double.valueOf(0));
							else {
								
								double amountInvoice=payment.getExchangeRate().doubleValue()*payment.getInvoices().get(i).getAmountOC().doubleValue();
								payment.getInvoices().get(i).setAmount(Double.valueOf(amountInvoice));
							}
						
						}
						
						
					
					}
					
					payment.setTotalAmountOC(Double.valueOf(amount));
					if(payment.getExchangeRate()==null) 
						payment.setTotalAmount(Double.valueOf(0));
					else {
						double totalAmount=payment.getExchangeRate().doubleValue()*payment.getTotalAmountOC().doubleValue();
						payment.setTotalAmount(Double.valueOf(totalAmount));
					}
				}
				
				
			int status=paymentService.update(payment);
			if(status==1) map.put("message", "success!");
			else map.put("error", "fail!");
			return map;
			
		}
	
		
		//xoa
		@PostMapping("/deleteRef")
		public Map<String, Object> deleteRef(@RequestBody PaymentReceipt pay,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){
				Map<String, Object> map=new HashMap<String, Object>();
				
//			Authenticate.Auth(httpServletRequest, httpServletResponse); 
				
			int status=paymentService.delete(pay);
			if(status==1) map.put("message", "success!");
			else map.put("error", "fail!");
			return map;
		}
		
	

		@PostMapping("/search")
		 public List<PaymentReceipt> search(@RequestBody String nameSearch,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){
			 String keyCompany="company1";
			 return paymentService.search(nameSearch, keyCompany);
		 }

		@GetMapping("/getRefNoFinance")
		public String getRefNoFinance() {
			String refNoFinance = "";
			Integer numPaymentReceipt = paymentService.getRefNoFinance();
			do {
				numPaymentReceipt += 1;
				String postFixString = "";
				if(numPaymentReceipt.toString().length() < 9) {
					int addNum = 9 - numPaymentReceipt.toString().length();		
					for(int i = 0; i < addNum; i++) {
						postFixString += "0";
					}
				}
				postFixString += numPaymentReceipt.toString();
				refNoFinance += postFixString;
			} while(paymentService.ifRefNoFinanceExist(refNoFinance));
			
			return refNoFinance;
		}

}
