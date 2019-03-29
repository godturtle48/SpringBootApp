package com.misa.report.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;


import com.misa.report.model.CACashbook;
import com.misa.report.repository.CACashbookRepository;

@Service
public class CACashbookService {
	@Autowired
	CACashbookRepository caCashbookRepository;
	MongoTemplate mongoTemplate;
	
	public boolean write(CACashbook caCashbook) {
		try {
			caCashbookRepository.save(caCashbook);
			System.out.println(caCashbookRepository);
			return true;
		}
		catch(Exception e) {
			return false;
		}		
	}
	
	

}
