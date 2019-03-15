package com.example.demo.command.controller;


//create Huyen 22/2
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.command.model.GeneralDetailCommand;
import com.example.demo.command.model.InvoiceDetailCommand;
import com.example.demo.command.model.PaymentReceiptCommand;
import com.example.demo.command.model.RefTypeCommand;
import com.example.demo.command.service.InvoiceDetailCommandService;
import com.example.demo.command.service.PaymentReceiptCommandService;
import com.example.demo.command.service.RefTypeCommandService;
import com.example.demo.rabbidmq.CommandMessageQueue;
import com.example.demo.rabbidmq.CreateMessageQueue;
import com.example.demo.rabbidmq.EventType;
import com.example.demo.rabbidmq.MessageFormat;
import com.example.demo.rabbidmq.MessageQueue;


@RestController
public class PaymentReceiptController {

	
	@Autowired
	RefTypeCommandService refTypeService;
	@Autowired
	PaymentReceiptCommandService paymentService;
	@Autowired
	InvoiceDetailCommandService invoiceService;

	
	
	//them chung tu
	@PostMapping("/addRef")
	public Map<String, Object> addRef(@RequestBody PaymentReceiptCommand payment,HttpServletRequest httpServletRequest){
		Map<String, Object> map=new HashMap<String, Object>();
		String keydatabase=(String) httpServletRequest.getAttribute("keydatabase");
		String keyCompany= httpServletRequest.getHeader("keycompany");
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
		RefTypeCommand ref=refTypeService.findByID(payment.getRef().getRefTypeID(), keydatabase);
		if(ref==null) {
			refTypeService.Save(payment.getRef(), keydatabase);
		}else {
			payment.setRef(ref);
		}
		
		if(payment.getInvoices()==null) {
			payment.setTotalAmount(Double.valueOf(0));
			payment.setTotalAmountOC(Double.valueOf(0));		
		}else {
			double amount=0;
			List<InvoiceDetailCommand> invoices=payment.getInvoices();
			
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
			
		}
		
		//Xét version cho payment la trang thai them =0;
		payment.setVersion(new Integer(0));
		int status=paymentService.save(payment, keydatabase);
		if(status==1) {
		
			map.put("message", "success!");
		}
		else map.put("error", "fail!");
		return map;
	}
	
	
		//sua Ref
		@PostMapping("/updateRef")
		public Map<String, Object> updateRef(@RequestBody PaymentReceiptCommand payment,HttpServletRequest httpServletRequest){
			String keydatabase=(String) httpServletRequest.getAttribute("keydatabase");
			String keyCompany= httpServletRequest.getHeader("keycompany");
				Map<String, Object> map=new HashMap<String, Object>();
				if(payment.getRefID()==null) {
					map.put("error", "Không tìm được payment");
					return map;
				}
				
					
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
				
				RefTypeCommand ref=refTypeService.findByID(payment.getRef().getRefTypeID(),keydatabase);
				if(ref==null) {
					refTypeService.Save(payment.getRef(),keydatabase);
				}else {
					payment.setRef(ref);
				}
				
				
				PaymentReceiptCommand paymentOld=paymentService.findByID(payment.getRefID(),keydatabase);
				if(paymentOld==null) {
					map.put("error", "Không tìm được payment");
					return map;
				}
				
				if(payment.getInvoices()==null) {
					payment.setTotalAmount(Double.valueOf(0));
					payment.setTotalAmountOC(Double.valueOf(0));
				}else {
					double amount=0;
					List<InvoiceDetailCommand> invoices=payment.getInvoices();
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
				
				
				//set tăng version lên
				payment.setVersion(Integer.valueOf(paymentOld.getVersion().intValue()+1));
				
			int status=paymentService.update(payment,keydatabase);
			if(status==1) {
				
				//send sang rabbitmq
				MessageFormat command=new MessageFormat(EventType.UPDATE,payment);
				CommandMessageQueue.produceMsg(command.toString());
				map.put("message", "success!");
			}
			else map.put("error", "fail!");
			return map;
			
		}
	
		
		//xoa
		@PostMapping("/deleteRef")
		public Map<String, Object> deleteRef(@RequestBody PaymentReceiptCommand payment,HttpServletRequest httpServletRequest){
			String keydatabase=(String) httpServletRequest.getAttribute("keydatabase");
				Map<String, Object> map=new HashMap<String, Object>();
				if(payment.getRefID()==null) {
					map.put("error", "Không tìm được payment");
					return map;
				}
				PaymentReceiptCommand paymentOld=paymentService.findByID(payment.getRefID(),keydatabase);
				if(paymentOld==null) {
					map.put("error", "Không tìm được payment");
					return map;
				}
				
			int status=paymentService.delete(payment,keydatabase);
			if(status==1) {
				MessageFormat command=new MessageFormat(EventType.DELETE,paymentOld);
				CommandMessageQueue.produceMsg(command.toString());
				map.put("message", "success!");
			}
			else map.put("error", "fail!");
			return map;
		}
		//Get data to add PaymentReceipt
		@GetMapping("/getGeneralDetailAddCombobox/{refTypeID}")
		public List<GeneralDetailCommand> getGeneralDetailAddPay_Re(@PathVariable("refTypeID") int refTypeID, HttpServletRequest httpServletRequest){
			String keydatabase=(String) httpServletRequest.getAttribute("keydatabase");
			String keyCompany = httpServletRequest.getHeader("keycompany");
			return paymentService.getGeneralDetailAddPay_Re(refTypeID, keyCompany);
		}
}
