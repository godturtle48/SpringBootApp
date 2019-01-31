package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.example.demo.databaseconfig.SimpleCorsFilter;
import com.example.demo.model.PaymentReceipt;
import com.example.demo.model.RefType;

@Repository
public class RefTypeRepository {
	public RefType getRefTypeById(int id) {
		Session session=SimpleCorsFilter.sessionFactory.openSession();
		RefType refType=null;
		Transaction tx=null;
		try {
			 tx = session.beginTransaction();
			 refType=session.get(RefType.class, id);
			tx.commit();
		} catch (Exception e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}
		  session.close(); 
		return refType;
	}
	
	public List<RefType> getAll(){
		
		Session session=SimpleCorsFilter.sessionFactory.openSession();
		Transaction tx=null;
		List<RefType> lst=new ArrayList<>();
		try {
			//	tx = session.beginTransaction();
			lst = session.createQuery("FROM RefType").list(); 
			//tx.commit();
		} catch (Exception e) {
			//if (tx!=null) tx.rollback();
			e.printStackTrace();
		}
		  session.close(); 
		return lst;
	
	}
	
	
	public int  update(RefType refType) {

		Session session=SimpleCorsFilter.sessionFactory.openSession();
		Transaction tx=null;
	
		try {
			 tx = session.beginTransaction();
			 session.update("RefType", refType);
			
			tx.commit();
		} catch (Exception e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
			 session.close();
			 return 0;
		}
		 session.close();
		 return 1;
	}
	public int  save(RefType refType) {

		Session session=SimpleCorsFilter.sessionFactory.openSession();
		Transaction tx=null;
		
		try {
			 tx = session.beginTransaction();
			 session.save(refType);
			tx.commit();
		} catch (Exception e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
			 session.close();
			 return 0;
		}
		  session.close(); 
		return 1;
	}
	public int  delete(RefType refType) {
		Session session=SimpleCorsFilter.sessionFactory.openSession();
		Transaction tx=null;
	
		try {
			 tx = session.beginTransaction();
			 session.createQuery("delete from PaymentReceipt where ref.refTypeID=:refTypeID")
					 .setParameter("refTypeID", refType.getRefTypeID()).executeUpdate(); 
			 session.delete(refType);
			
			 tx.commit();
		} catch (Exception e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
			 session.close();
			 return 0;
		}
		 session.close();
		 return 1;
	}
	
}
