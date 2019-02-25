package com.example.demo.query.service;



import java.util.ArrayList;

import java.util.List;

import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.example.demo.query.model.PaymentReceiptView;
import com.example.demo.query.repository.PaymentReceiptViewRepository;


@Service
public class PaymentReceiptViewService {

	@Autowired
	PaymentReceiptViewRepository  paymentRepository;
	
	@Autowired
	MongoTemplate mongoTemplate;
	public long countByKeyCompany(String keyCompany) {
		return paymentRepository.countByKeyCompany(keyCompany);
	}
	

	public int insert(PaymentReceiptView paymentReceipt) {
		int result=0;
		try {
			paymentRepository.insert(paymentReceipt);
			result=1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public int delete(PaymentReceiptView paymentReceipt) {
		int result=0;
		try {
			paymentRepository.deleteByRefID((paymentReceipt).getRefID());
			result=1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	
	}
	public List<PaymentReceiptView> getPaymentReceiptOfCompany(String keyCompany){
		return paymentRepository.findByKeyCompany(keyCompany);
	}
	
	public List<PaymentReceiptView> getPaymentReceiptOfCompanyPage(String keyCompany,long index,int size){
		List<PaymentReceiptView> lst=new ArrayList<>();
		try {
			  Query query = new Query();
			   query.addCriteria(Criteria.where("keyCompany").is(keyCompany));
			   query.with(new Sort(Sort.Direction.ASC, "modifiedDate"));
			   query.skip(index * size);
			   query.limit(size);
			   lst=mongoTemplate.find(query, PaymentReceiptView.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
			   return lst;

	}
	
	public List<PaymentReceiptView> getAlL(){
		return paymentRepository.findAll();
				}
	public int update(PaymentReceiptView paymentReceipt) {
		int result=0;
		try {
			paymentRepository.save(paymentReceipt);
			result=1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public PaymentReceiptView findByRefID(String refID) {
		return paymentRepository.findByRefID(refID);
	}
}
