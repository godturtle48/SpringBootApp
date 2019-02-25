package com.example.demo.command.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.command.model.InvoiceDetailCommand;
import com.example.demo.command.repository.InvoiceDetailCommandRepository;

@Service
public class InvoiceDetailCommandService {

	@Autowired
	InvoiceDetailCommandRepository invoiceDetailRepository;
	
	public int save(InvoiceDetailCommand invoiceDetail,String keydatabase) {
		return invoiceDetailRepository.save(invoiceDetail, keydatabase);
	}
	public int delete(InvoiceDetailCommand invoiceDetail,String paymentID,String keydatabase) {
		return invoiceDetailRepository.delete(invoiceDetail,paymentID, keydatabase);
	}
	
	public int update(InvoiceDetailCommand invoiceDetail,String keydatabase) {
		return invoiceDetailRepository.update(invoiceDetail, keydatabase);
	}
	
	public InvoiceDetailCommand findByID(String id,String keydatabase) {
		return invoiceDetailRepository.getInvoiceById(id, keydatabase);
	}
	
	public List<InvoiceDetailCommand> findByPaymentID(String paymentID,String keydatabase){
		return invoiceDetailRepository.getInvoicesByPaymentID(paymentID, keydatabase);
	}
	
}