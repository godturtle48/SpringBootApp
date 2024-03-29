package com.example.demo.command.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.example.demo.command.model.RefTypeCommand;
import com.example.demo.configmessagequeue.TenantInfo;


@Repository
public class RefTypeCommandRepository {

	
	public RefTypeCommand getRefTypeById(int id, String keydatabase) {
		Session session=TenantInfo.getConnect(keydatabase).openSession();
		RefTypeCommand refType=null;
		Transaction tx=null;
		try {
			 tx = session.beginTransaction();
			 refType=session.get(RefTypeCommand.class, id);
			tx.commit();
		} catch (Exception e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}
		  session.close(); 
		return refType;
	}
	
	public List<RefTypeCommand> getAll(String keydatabase){
		
		Session session=TenantInfo.getConnect(keydatabase).openSession();
		Transaction tx=null;
		List<RefTypeCommand> lst=new ArrayList<>();
		try {
			//	tx = session.beginTransaction();
			lst = session.createQuery("FROM RefTypeCommand").list(); 
			//tx.commit();
		} catch (Exception e) {
			//if (tx!=null) tx.rollback();
			e.printStackTrace();
		}
		  session.close(); 
		return lst;
	
	}
	
	
	public int  update(RefTypeCommand refType, String keydatabase) {

		Session session=TenantInfo.getConnect(keydatabase).openSession();
		Transaction tx=null;
	
		try {
			 tx = session.beginTransaction();
			 session.update("RefTypeCommand", refType);
			
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
	public int  save(RefTypeCommand refType, String keydatabase) {

		Session session=TenantInfo.getConnect(keydatabase).openSession();
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
	public int  delete(RefTypeCommand refType, String keydatabase) {
		Session session=TenantInfo.getConnect(keydatabase).openSession();
		Transaction tx=null;
	
		try {
			 tx = session.beginTransaction();
			 session.createQuery("delete from PaymentReceiptCommand where ref.refTypeID=:refTypeID")
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
