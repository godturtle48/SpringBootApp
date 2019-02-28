package com.misa.sme.config.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.misa.sme.config.model.PaymentDatabaseInfo;
import com.misa.sme.config.repository.PaymentDatabaseInfoRepository;

@Service
public class PaymentDatabaseInfoService {
	@Autowired
	PaymentDatabaseInfoRepository paymentDatabaseInfoRepository;
	public boolean save(PaymentDatabaseInfo paymentDatabaseInfo) {
		return  paymentDatabaseInfoRepository.save(paymentDatabaseInfo);
	}
	public List<PaymentDatabaseInfo> getAll() {
		return  paymentDatabaseInfoRepository.getAll();
	}	
	
}
