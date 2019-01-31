package com.example.demo.repository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Session;

//import javax.management.Query;
//import javax.websocket.Session;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.databaseconfig.SimpleCorsFilter;
import com.example.demo.model.FormFilter;
import com.example.demo.model.PaymentReceipt;
import com.example.demo.model.RefType;

@Repository
public class FilterPaymentRepository {
	public List<PaymentReceipt> filterByDate(String date, int order, String columnName, String keyCompany) {
		Session session = SimpleCorsFilter.sessionFactory.openSession();
		List<PaymentReceipt> listFilter;
		String qrString = null;
		if(order == 1) {
			qrString = "FROM PaymentReceipt WHERE keyCompany = :keyCompany AND postedDate >= :date order by " + columnName + " ASC";
		}
		else {
			qrString = "FROM PaymentReceipt WHERE keyCompany = :keyCompany AND postedDate <= :date order by " + columnName + " DESC";
		}
		try {
				listFilter = session.createQuery(qrString)
						.setParameter("date", java.sql.Date.valueOf(date))
						.setParameter("keyCompany", keyCompany)
						.getResultList();
				session.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("typing day!");
			return null;
		}

		return listFilter;
	}
	
	public List<PaymentReceipt> filterExactString(String strToFilter, String columnName, String keyCompany) {
		Session session = SimpleCorsFilter.sessionFactory.openSession();
		List<PaymentReceipt> listFilter;
		try {
			listFilter = session.createQuery("from PaymentReceipt where keyCompany = :keyCompany AND " 
											+ columnName + " = :strToFilter ORDER BY createdDate ASC")
								.setParameter("keyCompany", keyCompany)
								.setParameter("strToFilter", strToFilter)
								.getResultList();
			session.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("typing!");
			return null;
		}
		return listFilter;
	}
	
	public List<PaymentReceipt> filterNearString(String strToFilter, String columnName, int order, String keyCompany){
		Session session = SimpleCorsFilter.sessionFactory.openSession();
		List<PaymentReceipt> listFilter;
		String qrString=null;
		if(order == 1) {
			qrString = "from PaymentReceipt where MATCH(" + columnName + ") AGAINST (:strToFilter IN NATURAL LANGUAGE MODE"; 
				
		}
		else {
			qrString = "from PaymentReceipt where keyCompany = :keyCompany AND " + columnName + " LIKE %:strToFilter%"
					+ " ORDER BY postedDate DESC";
		}
		
		try {
			listFilter = session.createSQLQuery(qrString)
								.addEntity(PaymentReceipt.class)
//								.setParameter("keyCompany", keyCompany)
								.setParameter("strToFilter", strToFilter)
								.getResultList();
			session.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		return listFilter;
	}

	public List filerNumber(double parseDouble, int order, String columnName, String keyCompany) {
		// TODO Auto-generated method stub
		Session session = SimpleCorsFilter.sessionFactory.openSession();
		List<PaymentReceipt> listFilter;
		String qrString=null;
		if(order == 1) {
			qrString = "from PaymentReceipt where keyCompany = :keyCompany AND " + columnName + " >= :parseDouble ORDER BY postedDate ASC"; 
				
		}
		else {
			qrString = "from PaymentReceipt where keyCompany = :keyCompany AND " + columnName + " <= :parseDouble ORDER BY postedDate DESC";
		}
		try {
			listFilter = session.createSQLQuery(qrString)
								.addEntity(PaymentReceipt.class)
								.setParameter("parseDouble", parseDouble)
								.getResultList();
			session.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("typing!");
			return null;
		}
		return listFilter;
	}

	public List<PaymentReceipt> filterRef(String strToFilter, String columnName, String keyCompany) {
		// TODO Auto-generated method stub
		Session session = SimpleCorsFilter.sessionFactory.openSession();
		System.out.println("strToFIlter" + strToFilter);
		List<PaymentReceipt> listFilter;
		try {
			listFilter = session.createSQLQuery("select p.* from PaymentReceipt p"
												+ " INNER JOIN RefType r ON p.refTypeID = r.refTypeID"
												+ " WHERE keyCompany = :keyCompany"
												+ " AND refTypeName = :strToFilter")
								.addEntity(PaymentReceipt.class)
								.setParameter("strToFilter", strToFilter)
								.setParameter("keyCompany", keyCompany)
								.getResultList();
			session.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("typing!");
			return null;
		}
		return listFilter;
	}

	public List<PaymentReceipt> filter(List<FormFilter> dataFilter) {
		// TODO Auto-generated method stub
		Session session = SimpleCorsFilter.sessionFactory.openSession();
		String qrString="select * from PaymentReceipt WHERE ";
		if(dataFilter.size() > 1) {
			for(int i = 0; i < dataFilter.size(); i++) {
				qrString += dataFilter.get(i).getColum() + " >= " + dataFilter.get(i).getDataTofilter() + " AND ";
			}
			qrString += dataFilter.get(dataFilter.size()-1).getColum() + " >= " + dataFilter.get(dataFilter.size()-1).getDataTofilter();
		}
		List<PaymentReceipt> listFilteReceipts = session.createSQLQuery(qrString).getResultList();
		return listFilteReceipts;
	}
	
	
	
}
