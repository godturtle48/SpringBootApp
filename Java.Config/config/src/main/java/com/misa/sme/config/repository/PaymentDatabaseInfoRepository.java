package com.misa.sme.config.repository;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.misa.sme.config.ConfigDatabaseForThisService;
import com.misa.sme.config.model.PaymentDatabaseInfo;
import com.misa.sme.config.model.PaymentDatabaseServerInfo;


@Repository
public class PaymentDatabaseInfoRepository {
	public boolean save(PaymentDatabaseInfo paymentDatabaseInfo) {
		Session session=ConfigDatabaseForThisService.getInstance().openSession();
		try {
			session.beginTransaction();
			session.save(paymentDatabaseInfo);
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
	
	public List<PaymentDatabaseInfo> getAll() {
		Session session=ConfigDatabaseForThisService.getInstance().openSession();
		List<PaymentDatabaseInfo> list;
		try {
			session.beginTransaction();
			list = session.createQuery("FROM PaymentDatabaseInfo").list();
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

	public int count() {
		Session session=ConfigDatabaseForThisService.getInstance().openSession();		
		try {
			int count = ((Long)session.createQuery("select count(*) from PaymentDatabaseInfo").uniqueResult()).intValue();
			
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		finally{
			session.close();
		}
	}

	public PaymentDatabaseInfo getDatabaseHaveMinNumCompany(PaymentDatabaseServerInfo paymentDatabaseServerInfo) {
		Session session=ConfigDatabaseForThisService.getInstance().openSession();
		try {
			List<PaymentDatabaseInfo> result =session.createQuery("from PaymentDatabaseInfo where keyserver=:keyserver")
					.setParameter("keyserver",paymentDatabaseServerInfo.getKeyserver()).list();
			if(result.size()==0) return null;
			if (result.size()==1) return result.get(0);
			int min=result.get(0).getListPaymentDatabaseOfUser().size();
			int minIndex=0;
			for(int i=1;i<result.size();i++) 
				if(min>result.get(i).getListPaymentDatabaseOfUser().size()) {
					min=result.get(i).getListPaymentDatabaseOfUser().size();
					minIndex=i;
				}
			return result.get(minIndex);					
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			session.close();
		}
	}

}
