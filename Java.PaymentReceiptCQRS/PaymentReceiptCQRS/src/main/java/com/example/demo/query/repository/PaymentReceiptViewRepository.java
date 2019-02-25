package com.example.demo.query.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.demo.query.model.PaymentReceiptView;

public interface PaymentReceiptViewRepository extends MongoRepository<PaymentReceiptView, String>{
	long deleteByRefID(String lastname);
	long countByKeyCompany(String keyCompany);

	List<PaymentReceiptView> findByKeyCompany(String keyCompany);
	PaymentReceiptView  findByRefID(String refID);
	

}
