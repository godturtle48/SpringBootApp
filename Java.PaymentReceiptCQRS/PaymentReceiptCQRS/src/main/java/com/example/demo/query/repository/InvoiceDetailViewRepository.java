package com.example.demo.query.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.query.model.InvoiceDetailView;
//@Repository
public interface InvoiceDetailViewRepository extends MongoRepository<InvoiceDetailView, String> {
//	long deleteByRefID(String lastname);
//	long countByKeyCompany(String keyCompany);
//
//	List<InvoiceDetailView> findByAccountObjectID(String accountObjectID);
//	InvoiceDetailView  findByInvoiceId(String refID);
}
