package com.example.demo.repository;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import com.example.demo.databaseconfig.SimpleCorsFilter;
import com.example.demo.model.ComboBox;
import com.example.demo.model.PaymentReceipt;

@Repository
public class ComboBoxRepository {

	public List<Object> loadComboboxAddPayment() {
		// TODO Auto-generated method stub
		Session session = SimpleCorsFilter.sessionFactory.openSession();
		session.beginTransaction();
		String qrString="select distinct accountObjectID, accountObjectName, accountObjectAddress, createdBy, journalMemo, description "
				+ "from PaymentReceipt Where reftypeID = 2";
		Query query = session.createSQLQuery(qrString);	
		query.setFirstResult(1);
		query.setMaxResults(6);
		List<Object> list = query.getResultList();
		session.getTransaction().commit();
		System.out.println(list.get(1));
		session.close();
		return list;
	}

	public List<Object> loadComboboxAddReceipt() {
		// TODO Auto-generated method stub
		Session session = SimpleCorsFilter.sessionFactory.openSession();
		session.beginTransaction();
		String qrString="select distinct accountObjectID, accountObjectName, accountObjectAddress, createdBy, journalMemo, description "
				+ "from PaymentReceipt Where reftypeID = 1";
		Query query = session.createSQLQuery(qrString);	
		query.setFirstResult(1);
		query.setMaxResults(6);
		List<Object> list = query.getResultList();
		session.getTransaction().commit();
		System.out.println(list.get(1));
		session.close();
		return list;
	}
}
