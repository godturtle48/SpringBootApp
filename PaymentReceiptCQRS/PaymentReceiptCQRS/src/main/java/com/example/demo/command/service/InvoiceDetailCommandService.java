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
	
	public int save(InvoiceDetailCommand invoiceDetail) {
		return invoiceDetailRepository.save(invoiceDetail);
	}
	public int delete(InvoiceDetailCommand invoiceDetail,String paymentID) {
		return invoiceDetailRepository.delete(invoiceDetail,paymentID);
	}
	
	public int update(InvoiceDetailCommand invoiceDetail) {
		return invoiceDetailRepository.update(invoiceDetail);
	}
	
	public InvoiceDetailCommand findByID(String id) {
		return invoiceDetailRepository.getInvoiceById(id);
	}
	
	public List<InvoiceDetailCommand> findByPaymentID(String paymentID){
		return invoiceDetailRepository.getInvoicesByPaymentID(paymentID);
	}
	
}