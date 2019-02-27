package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.InvoiceDetailService;
import com.example.demo.Service.PaymentReceiptService;
import com.example.demo.Service.RefTypeService;
import com.example.demo.model.InvoiceDetail;
import com.example.demo.model.PaymentReceipt;
@RestController
public class InvoiceController {
	@Autowired
	RefTypeService refTypeService;
	@Autowired
	PaymentReceiptService paymentService;
	@Autowired
	InvoiceDetailService invoiceService;
	
	//them hoa don chi tiet
		@PostMapping("/addInvoice:{idPayment}")
		public Map<String, Object> addInvoice(@RequestBody InvoiceDetail invoice,@PathVariable String idPayment,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){
			Map<String, Object> map=new HashMap<String, Object>();
			
			if(invoice.getAmount()<=0) {
				 map.put("error", "fail!");
					return map;
			}
			PaymentReceipt paymentReceipt=paymentService.findByID(idPayment);
			if(paymentReceipt==null) {
				 map.put("error", "fail!");
					return map;
			}
			
			int status=invoiceService.save(invoice);
			if(status==1) map.put("message", "success!");
			else map.put("error", "fail!");
			return map;
		}
		
		//cap nhat lai invoice dong thoi co cap nhat lai payment
		
		
		@PostMapping("/updateInvoice:{idPayment}")
		public Map<String, Object> updateInvoice(@RequestBody InvoiceDetail invoice, @PathVariable String idPayment,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){
			Map<String, Object> map=new HashMap<String, Object>();
			PaymentReceipt paymentReceipt=paymentService.findByID(idPayment);
			if(paymentReceipt==null) {
				 map.put("error", "fail!");
					return map;
			}
			invoice.setPayment(paymentReceipt);
			
			int status=invoiceService.update(invoice);
			if(status==1) {
			
				map.put("message", "success!");
			}
			else map.put("error", "fail!");
			return map;
		}

		//xoa 
		@PostMapping("/deleteInvoice:{idPayment}")
		public Map<String, Object> deleteInvoice(@RequestBody InvoiceDetail invoice,@PathVariable String idPayment,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){
			Map<String, Object> map=new HashMap<String, Object>();
			PaymentReceipt paymentReceipt=paymentService.findByID(idPayment);
			if(paymentReceipt==null) {
				 map.put("error", "fail!");
					return map;
			}
		
			int status=invoiceService.delete(invoice,idPayment);
			if(status==1) {
				
				map.put("message", "success!");
			}
			else map.put("error", "fail!");
			return map;
		}
		
		
		@RequestMapping("/getInvoiceDetail:{idRef}")
		public List<InvoiceDetail> getInvoiceDetail(@PathVariable String idRef,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){
			return invoiceService.findByPaymentID(idRef);
		}



}
