package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.FormFilter;
import com.example.demo.model.PaymentReceipt;
import com.example.demo.repository.FilterPaymentRepository;
import com.example.demo.repository.InvoiceDetailRepository;
import com.example.demo.repository.PaymentReceiptRepository;

@Service
public class FilterService {
	@Autowired
	InvoiceDetailRepository invoiceDetailRepository;
	@Autowired
	PaymentReceiptRepository paymentReceiptRepository;
	@Autowired
	RefTypeService refTypeService;
	@Autowired
	FilterPaymentRepository filterPaymentRepository;
	
	public List<PaymentReceipt> filterByDate(String postedDate, int order, String columnName, String keyCompany){
		return filterPaymentRepository.filterByDate(postedDate, order, columnName, keyCompany);
	}
	public List<PaymentReceipt> filterExactString(String strToFilter, String columnName, String keyCompany){
		return filterPaymentRepository.filterExactString(strToFilter, columnName, keyCompany);		
	}
	public List<PaymentReceipt> filterNearString(String strToFilter, String columnName, int order, String keyCompany){
		return filterPaymentRepository.filterNearString(strToFilter, columnName, order, keyCompany);
	}
	public List<PaymentReceipt> filterNumber(double parseDouble, int order, String column, String keyCompany) {
		// TODO Auto-generated method stub
		return filterPaymentRepository.filerNumber(parseDouble, order, column, keyCompany);
	}
	public List<PaymentReceipt> filterRef(String strToFilter, String column, String keyCompany) {
		// TODO Auto-generated method stub
		return filterPaymentRepository.filterRef(strToFilter, column, keyCompany);
	}
	public List<PaymentReceipt> filter(List<FormFilter> dataFilter) {
		// TODO Auto-generated method stub
		return filterPaymentRepository.filter(dataFilter);
	}
	
	
	
}
