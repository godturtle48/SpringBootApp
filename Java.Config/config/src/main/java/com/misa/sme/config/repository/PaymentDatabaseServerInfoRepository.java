package com.misa.sme.config.repository;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.misa.sme.config.ConfigDatabaseForThisService;
import com.misa.sme.config.model.PaymentDatabaseInfo;
import com.misa.sme.config.model.PaymentDatabaseServerInfo;

@Repository
public class PaymentDatabaseServerInfoRepository {

	public boolean save(PaymentDatabaseServerInfo paymentDatabaseServerInfo) {
		Session session=ConfigDatabaseForThisService.getInstance().openSession();
		try {
			session.beginTransaction();
			session.save(paymentDatabaseServerInfo);
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
	
	public int count() {
		Session session=ConfigDatabaseForThisService.getInstance().openSession();		
		try {
			int count = ((Long)session.createQuery("select count(*) from PaymentDatabaseServerInfo").uniqueResult()).intValue();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		finally{
			session.close();
		}
	}

	public PaymentDatabaseServerInfo getServerHaveMinNumDB() {
		Session session=ConfigDatabaseForThisService.getInstance().openSession();
		try {
			List<PaymentDatabaseServerInfo> result =session.createQuery("from PaymentDatabaseServerInfo").list();
			if(result.size()==0) return null;
			if (result.size()==1) return result.get(0);
			int min=result.get(0).getListPaymentDatabaseInfo().size();
			int minIndex=0;
			for(int i=1;i<result.size();i++) 
				if(min>result.get(i).getListPaymentDatabaseInfo().size()) {
					min=result.get(i).getListPaymentDatabaseInfo().size();
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

	public PaymentDatabaseServerInfo getServerHaveMinNumCompany() {
		Session session=ConfigDatabaseForThisService.getInstance().openSession();
		try {
			String keyserver =(String) session.createSQLQuery("select PaymentDatabaseServerInfo.keyserver "
					+ "from PaymentDatabaseServerInfo,PaymentDatabaseInfo,PaymentDatabaseOfUser "
					+ "where PaymentDatabaseServerInfo.keyserver=PaymentDatabaseInfo.keyserver"
					+ " and PaymentDatabaseInfo.keydatabase=PaymentDatabaseOfUser.keydatabase "
					+ "group by PaymentDatabaseServerInfo.keyserver order by count(*) asc").list().get(0);	
			return session.get(PaymentDatabaseServerInfo.class, keyserver); 				
		} catch (Exception e) {
			return null;
		}
		finally{
			session.close();
		}
	}

	public int getNumCompany(PaymentDatabaseServerInfo paymentDatabaseServerInfo) {
		Session session=ConfigDatabaseForThisService.getInstance().openSession();
		try {
//			int count =(int)session.createSQLQuery("select count(*) "
//					+ "from PaymentDatabaseServerInfo,PaymentDatabaseInfo,PaymentDatabaseOfUser "
//					+ "where PaymentDatabaseServerInfo.keyserver=PaymentDatabaseInfo.keyserver"
//					+ " and PaymentDatabaseInfo.keydatabase=PaymentDatabaseOfUser.keydatabase "
//					+ "and PaymentDatabaseServerInfo.keyserver='da181d6a-34b3-11e9-aebb-1c6f65d5887b'").list().get(0);
			int count=0;
			for(PaymentDatabaseInfo dbInfo: paymentDatabaseServerInfo.getListPaymentDatabaseInfo()) {
				count=count+dbInfo.getListPaymentDatabaseOfUser().size();
			}
			return count; 				
		} catch (Exception e) {
			return -1;
		}
		finally{
			session.close();
		}
	}
}
