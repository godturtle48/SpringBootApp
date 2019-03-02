package com.example.demo.query.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

import com.example.demo.query.model.InvoiceDetailView;
import com.example.demo.query.model.PaymentReceiptView;
//@Repository
public interface InvoiceDetailViewRepository extends MongoRepository<InvoiceDetailView, String> {
//	long deleteByRefID(String lastname);
//	long countByKeyCompany(String keyCompany);
//
//	List<InvoiceDetailView> findByAccountObjectID(String accountObjectID);
//	InvoiceDetailView  findByInvoiceId(String refID);
}
