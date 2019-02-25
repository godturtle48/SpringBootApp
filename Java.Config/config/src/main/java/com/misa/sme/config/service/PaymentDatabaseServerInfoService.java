package com.misa.sme.config.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.misa.sme.config.model.PaymentDatabaseServerInfo;
import com.misa.sme.config.repository.PaymentDatabaseServerInfoRepository;

@Service
public class PaymentDatabaseServerInfoService {
	@Autowired
	PaymentDatabaseServerInfoRepository paymentDatabaseServerInfoRepository;
	public boolean save(PaymentDatabaseServerInfo paymentDatabaseServerInfo) {
		return  paymentDatabaseServerInfoRepository.save(paymentDatabaseServerInfo);
	}
	
}
