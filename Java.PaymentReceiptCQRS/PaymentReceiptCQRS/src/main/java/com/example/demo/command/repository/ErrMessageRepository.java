package com.example.demo.command.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.command.model.ErrMessageModel;

@Repository
public interface ErrMessageRepository extends MongoRepository<ErrMessageModel, String> {
	public ErrMessageModel findByRefID(String refID);
	public long deleteByRefID(String refID);
}
