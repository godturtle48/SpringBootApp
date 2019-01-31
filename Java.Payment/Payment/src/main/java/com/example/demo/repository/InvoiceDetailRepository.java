package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.example.demo.databaseconfig.SimpleCorsFilter;
import com.example.demo.model.InvoiceDetail;
import com.example.demo.model.PaymentReceipt;



@Repository
public class InvoiceDetailRepository {
	
	public InvoiceDetail getInvoiceById(String id) {
		Session session=SimpleCorsFilter.sessionFactory.openSession();
		InvoiceDetail invoice=null;
		Transaction tx=null;
		try {
			 tx = session.beginTransaction();
			invoice=session.get(InvoiceDetail.class, id);
			tx.commit();
		} catch (Exception e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}
		  session.close(); 
		return invoice;
	}
	
	public List<InvoiceDetail> getAll(){
		
		Session session=SimpleCorsFilter.sessionFactory.openSession();
		InvoiceDetail invoice=null;
		Transaction tx=null;
		List<InvoiceDetail> lst=new ArrayList<>();
		try {
			//	tx = session.beginTransaction();
			lst = session.createQuery("FROM InvoiceDetail").list(); 
			//tx.commit();
		} catch (Exception e) {
			//if (tx!=null) tx.rollback();
			e.printStackTrace();
		}
		  session.close(); 
		return lst;
	
	}
	
	public List<InvoiceDetail> getInvoicesByPaymentID(String PaymentID){
		
		Session session=SimpleCorsFilter.sessionFactory.openSession();
		InvoiceDetail invoice=null;
		Transaction tx=null;
		List<InvoiceDetail> lst=new ArrayList<>();
		try {
//			 tx = session.beginTransaction();
			String sql = " FROM InvoiceDetail WHERE payment.refID = :refID";
			lst= session.createQuery(sql).setParameter("refID", PaymentID).list();
			//tx.commit();
		} catch (Exception e) {
			//if (tx!=null) tx.rollback();
			e.printStackTrace();
		}
		  session.close(); 
		return lst;
	
	}
	public int  update(InvoiceDetail invoice) {

		Session session=SimpleCorsFilter.sessionFactory.openSession();
		Transaction tx=null;
	
		try {
			 tx = session.beginTransaction();
			 session.update(invoice);
			 //update lai payment
			 Double amount=0.0;
			  Object  obj= (Object) session.createQuery("select sum(amountOC) from InvoiceDetail where payment.refID=:refID")
						.setParameter("refID",invoice.getPayment().getRefID()).uniqueResult(); 
				if(obj!=null)
					amount=((Double)obj).doubleValue();
				PaymentReceipt payment=session.get(PaymentReceipt.class,invoice.getPayment().getRefID());
				payment.setTotalAmountOC(amount);
				session.update("PaymentReceipt", payment);
			
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
	public int  save(InvoiceDetail invoice) {

		Session session=SimpleCorsFilter.sessionFactory.openSession();
		Transaction tx=null;
		int result=0;
		try {
			 tx = session.beginTransaction();
			 session.save(invoice);
			 Double amount=0.0;
			  Object  obj= (Object) session.createQuery("select sum(amountOC) from InvoiceDetail where payment.refID=:refID")
						.setParameter("refID",invoice.getPayment().getRefID()).uniqueResult(); 
				if(obj!=null)
					amount=((Double)obj).doubleValue();
				PaymentReceipt payment=session.get(PaymentReceipt.class,invoice.getPayment().getRefID());
				payment.setTotalAmountOC(amount);
				session.update("PaymentReceipt", payment);
			 
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
	public int  delete(InvoiceDetail invoice,String paymentID) {

		Session session=SimpleCorsFilter.sessionFactory.openSession();
		Transaction tx=null;
		int result=0;
		try {
			 tx = session.beginTransaction();
			 session.delete(invoice);
			 Double amount=0.0;
			 PaymentReceipt payment=session.get(PaymentReceipt.class,paymentID);
			  Object  obj= (Object) session.createQuery("select sum(amountOC) from InvoiceDetail where payment.refID=:refID")
						.setParameter("refID",paymentID)
						.uniqueResult(); 
				if(obj!=null)
					amount=((Double)obj).doubleValue();
				
				payment.setTotalAmountOC(payment.getTotalAmountOC()-invoice.getAmountOC());
				session.update("PaymentReceipt", payment);
				
			
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
