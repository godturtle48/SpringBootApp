package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.InvoiceDetail;
import com.example.demo.model.PaymentReceipt;
import com.example.demo.repository.InvoiceDetailRepository;
import com.example.demo.repository.PaymentReceiptRepository;

@Service
public class InvoiceDetailService {

	@Autowired
	InvoiceDetailRepository invoiceDetailRepository;
	
	public int save(InvoiceDetail invoiceDetail) {
		return invoiceDetailRepository.save(invoiceDetail);
	}
	public int delete(InvoiceDetail invoiceDetail,String paymentID) {
		return invoiceDetailRepository.delete(invoiceDetail,paymentID);
	}
	
	public int update(InvoiceDetail invoiceDetail) {
		return invoiceDetailRepository.update(invoiceDetail);
	}
	
	public InvoiceDetail findByID(String id) {
		return invoiceDetailRepository.getInvoiceById(id);
	}
	
	public List<InvoiceDetail> findByPaymentID(String paymentID){
		return invoiceDetailRepository.getInvoicesByPaymentID(paymentID);
	}
	
}
