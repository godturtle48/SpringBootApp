package com.example.demo.command.repository;



import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.example.demo.command.model.InvoiceDetailCommand;
import com.example.demo.command.model.PaymentReceiptCommand;
import com.example.demo.config.SimpleCorsFilter;
import com.example.demo.configmessagequeue.TenantInfo;

@Repository
public class InvoiceDetailCommandRepository {

	public InvoiceDetailCommand getInvoiceById(String id, String keydatabase) {
		Session session=TenantInfo.getConnect(keydatabase).openSession();
		InvoiceDetailCommand invoice=null;
		Transaction tx=null;
		try {
			 tx = session.beginTransaction();
			invoice=session.get(InvoiceDetailCommand.class, id);
			tx.commit();
		} catch (Exception e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}
		  session.close(); 
		return invoice;
	}
	
	public List<InvoiceDetailCommand> getAll(String keydatabase){
		
		Session session=TenantInfo.getConnect(keydatabase).openSession();
		InvoiceDetailCommand invoice=null;
		Transaction tx=null;
		List<InvoiceDetailCommand> lst=new ArrayList<>();
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
	
	public List<InvoiceDetailCommand> getInvoicesByPaymentID(String PaymentID, String keydatabase){
		
		Session session=TenantInfo.getConnect(keydatabase).openSession();
		InvoiceDetailCommand invoice=null;
		Transaction tx=null;
		List<InvoiceDetailCommand> lst=new ArrayList<>();
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
	public int  update(InvoiceDetailCommand invoice, String keydatabase) {

		Session session=TenantInfo.getConnect(keydatabase).openSession();
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
				PaymentReceiptCommand payment=session.get(PaymentReceiptCommand.class,invoice.getPayment().getRefID());
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
	public int  save(InvoiceDetailCommand invoice, String keydatabase) {

		Session session=TenantInfo.getConnect(keydatabase).openSession();
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
				PaymentReceiptCommand payment=session.get(PaymentReceiptCommand.class,invoice.getPayment().getRefID());
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
	public int  delete(InvoiceDetailCommand invoice,String paymentID, String keydatabase) {

		Session session=TenantInfo.getConnect(keydatabase).openSession();
		Transaction tx=null;
		int result=0;
		try {
			 tx = session.beginTransaction();
			 session.delete(invoice);
			 Double amount=0.0;
			 PaymentReceiptCommand payment=session.get(PaymentReceiptCommand.class,paymentID);
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
