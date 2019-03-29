package com.misa.report.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.misa.report.model.CACashbook;
import com.misa.report.model.GeneralLedger;

@Repository
public interface CACashbookRepository  extends MongoRepository<CACashbook, String>{
	public long deleteByRefID(String refID);
	public CACashbook findByRefID(String refID);
}
