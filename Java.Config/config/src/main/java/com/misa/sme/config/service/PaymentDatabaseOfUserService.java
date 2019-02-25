package com.misa.sme.config.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.misa.sme.config.model.PaymentDatabaseOfUser;
import com.misa.sme.config.repository.PaymentDatabaseOfUserRepository;

@Service
public class PaymentDatabaseOfUserService {
	@Autowired
	PaymentDatabaseOfUserRepository paymentDatabaseOfUserRepository;
	public boolean save(PaymentDatabaseOfUser paymentDatabaseOfUser) {
		return  paymentDatabaseOfUserRepository.save(paymentDatabaseOfUser);
	}
	public List<PaymentDatabaseOfUser> getAll() {
		return  paymentDatabaseOfUserRepository.getAll();
	}
}
