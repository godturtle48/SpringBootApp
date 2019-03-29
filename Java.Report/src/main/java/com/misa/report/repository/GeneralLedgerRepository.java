package com.misa.report.repository;

import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.misa.report.model.GeneralLedger;

@Repository
public interface GeneralLedgerRepository extends MongoRepository<GeneralLedger, String>{	
	public long deleteByRefID(String refID);
	public List<GeneralLedger> findByRefID(String refID);
	List<GeneralLedger> findByKeycompany(String keycompany);
	public GeneralLedger findByRefDetailID(String refDetailID);
}
