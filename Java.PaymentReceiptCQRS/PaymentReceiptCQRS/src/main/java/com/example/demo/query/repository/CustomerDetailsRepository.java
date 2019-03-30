package com.example.demo.query.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.query.model.CustomerDetails;

@Repository
public interface CustomerDetailsRepository extends MongoRepository<CustomerDetails, String> {
	public List<CustomerDetails> findByKeyCompany(String keyCompany);
}
