package com.misa.sme.config.repository;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.misa.sme.config.ConfigDatabaseForThisService;
import com.misa.sme.config.model.PaymentDatabaseOfUser;

@Repository
public class PaymentDatabaseOfUserRepository {
	public boolean save(PaymentDatabaseOfUser paymentDatabaseOfUser) {
		Session session=ConfigDatabaseForThisService.getInstance().openSession();
		try {
			session.beginTransaction();
			session.save(paymentDatabaseOfUser);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		finally{
			session.close();
		}
		return true;
	}
	
	public List<PaymentDatabaseOfUser> getAll() {
		Session session=ConfigDatabaseForThisService.getInstance().openSession();
		List<PaymentDatabaseOfUser> list;
		try {
			session.beginTransaction();
			list = session.createQuery("FROM PaymentDatabaseOfUser").list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			session.close();
		}
		return list;
	}
	
	
}
