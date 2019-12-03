package com.example.demo.command.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.demo.command.model.GeneralDetailCommand;
import com.example.demo.command.model.InvoiceDetailCommand;
import com.example.demo.command.model.PaymentReceiptCommand;
import com.example.demo.configmessagequeue.TenantInfo;
import com.example.demo.rabbidmq.CreateMessageQueue;
import com.example.demo.rabbidmq.EventType;
import com.example.demo.rabbidmq.MessageFormat;
import com.example.demo.rabbidmq.ReportMessageQueue;



@Repository
public class PaymentReceiptCommandRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentReceiptCommandRepository.class);


	public PaymentReceiptCommand getPaymentReceiptById(String id, String keydatabase) {
		Session session=TenantInfo.getConnect(keydatabase).openSession();
		PaymentReceiptCommand payment=null;
		Transaction tx=null;
		try {
			 tx = session.beginTransaction();
			 payment= session.get(PaymentReceiptCommand.class, id);
			tx.commit();
		} catch (Exception e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
		  session.close(); 
		return payment;
	}
	
	public List<PaymentReceiptCommand> getAll(String keydatabase){
		
		Session session=TenantInfo.getConnect(keydatabase).openSession();
		Transaction tx=null;
		List<PaymentReceiptCommand> lst=new ArrayList<>();
		try {
			//	tx = session.beginTransaction();
			lst = session.createQuery("FROM PaymentReceiptCommand").list(); 
			//tx.commit();
		} catch (Exception e) {
			//if (tx!=null) tx.rollback();
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
		  session.close(); 
		return lst;
	
	}
	
	public List<PaymentReceiptCommand> getPaymentReceiptByRefType(int refTypeID, String keydatabase){
		
		Session session=TenantInfo.getConnect(keydatabase).openSession();
		Transaction tx=null;
		List<PaymentReceiptCommand> lst=new ArrayList<>();
		try {
//			 tx = session.beginTransaction();
			String sql = "SELECT * FROM PaymentReceiptCommand WHERE refTypeID = :refTypeID";
			lst= session.createSQLQuery(sql).addEntity(PaymentReceiptCommand.class).setParameter("refTypeID", refTypeID).getResultList();
			//tx.commit();
		} catch (Exception e) {
			//if (tx!=null) tx.rollback();
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
		  session.close(); 
		return lst;
	
	}
	
	public List<PaymentReceiptCommand> getPaymentReceiptOfCompany(String keyCompany, String keydatabase){
		
		Session session=TenantInfo.getConnect(keydatabase).openSession();
		Transaction tx=null;
		List<PaymentReceiptCommand> lst=new ArrayList<>();
		try {
//			 tx = session.beginTransaction();
			String sql = "SELECT * FROM PaymentReceiptCommand WHERE keyCompany = :keyCompany";
			lst= session.createSQLQuery(sql).addEntity(PaymentReceiptCommand.class).setParameter("keyCompany", keyCompany).getResultList();
			//tx.commit();
		} catch (Exception e) {
			//if (tx!=null) tx.rollback();
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
		  session.close(); 
		return lst;
	
	}
	
	
	public long count(String keyCompany, String keydatabase) {
		Session session=TenantInfo.getConnect(keydatabase).openSession();
		Transaction tx=null;
		long result=0;
		try {
			 tx = session.beginTransaction();
			Object  obj= session.createQuery("select count(refID) from PaymentReceiptCommand where keyCompany=:keyCompany")
					.setParameter("keyCompany", keyCompany).uniqueResult(); 
			if(obj!=null)result=((Long)obj).longValue();
			//tx.commit();
		} catch (Exception e) {
			//if (tx!=null) tx.rollback();
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
		  session.close(); 
		return result;
	}
	
	public List<PaymentReceiptCommand> getPaymentReceiptOfCompanyPage(String keyCompany,long index,int size, String keydatabase){
		
		Session session=TenantInfo.getConnect(keydatabase).openSession();
		Transaction tx=null;
		List<PaymentReceiptCommand> lst=new ArrayList<>();
		try {
//			 tx = session.beginTransaction();
			String sql = "FROM PaymentReceiptCommand WHERE keyCompany = :keyCompany order by modifiedDate asc";
			lst= session.createQuery(sql)
					.setParameter("keyCompany", keyCompany)
					.setFirstResult((int)index)
					.setMaxResults(size)
					.list();
			//tx.commit();
		} catch (Exception e) {
			//if (tx!=null) tx.rollback();
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
		  session.close(); 
		return lst;
	
	}
	
	

	public int  save(PaymentReceiptCommand paymentReceipt, String keydatabase) {

		Session session=TenantInfo.getConnect(keydatabase).openSession();
		Transaction tx=null;

		try {
			 tx = session.beginTransaction();
			 session.save(paymentReceipt);
			 if(paymentReceipt.getInvoices()!=null) {
				 List<InvoiceDetailCommand> invoices=paymentReceipt.getInvoices();
				 for(InvoiceDetailCommand invoice:invoices) {
					 invoice.setPayment(paymentReceipt);
					 session.save(invoice);
				 } 
			 }
			 
				//send sang rabbitmq
				MessageFormat command=new MessageFormat(EventType.CREATE,paymentReceipt);
				if( !CreateMessageQueue.produceMsg(command.toString()))
					tx.rollback();
			tx.commit();
		} catch (Exception e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			 session.close();
			 return 0;
		}
		 session.close();
		 return 1;
	}
	
	
	
	public int  update(PaymentReceiptCommand paymentReceipt, String keydatabase) {
		Session session=TenantInfo.getConnect(keydatabase).openSession();
		
		Transaction tx=null;
		int result=0;
		try {
			 tx = session.beginTransaction();
			
			 session.update(paymentReceipt);

			 if(paymentReceipt.getInvoices()!=null) {

				 List<InvoiceDetailCommand> invoices=paymentReceipt.getInvoices();
				 for(InvoiceDetailCommand invoice:invoices) {
					 if(invoice.getStatus()==1) {
						 //sua
						 invoice.setPayment(paymentReceipt);
						 invoice.setStatus(0);
						 session.update(invoice);
					 }else if(invoice.getStatus()==2) {
						 //xoa
						 session.delete(invoice);
					 }else if(invoice.getStatus()==3) {
						 //them
						 invoice.setPayment(paymentReceipt);
						 invoice.setStatus(0);
						 session.save(invoice);
					 }
				 } 
			 }
			 MessageFormat command=new MessageFormat(EventType.UPDATE,paymentReceipt);
			if( !CreateMessageQueue.produceMsg(command.toString()))
				tx.rollback();
			tx.commit();
		} catch (Exception e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			 session.close();
			 return 0;
		}
		  
		 session.close();
		 return 1;
	}
	
	public int  delete(PaymentReceiptCommand paymentReceipt, String keydatabase) {

		System.out.println(keydatabase);
		Session session=TenantInfo.getConnect(keydatabase).openSession();
		Transaction tx=null;
		
		try {
			 tx = session.beginTransaction();
			 //phai xoa ca lien ket nua
			 session.createQuery("delete from InvoiceDetailCommand where payment.refID=:refID")
			 .setParameter("refID", paymentReceipt.getRefID()).executeUpdate(); 
			 session.createQuery("delete from PaymentReceiptCommand where refID=:refID")
			 .setParameter("refID", paymentReceipt.getRefID()).executeUpdate(); 
				MessageFormat command=new MessageFormat(EventType.DELETE,paymentReceipt);
				if( !CreateMessageQueue.produceMsg(command.toString()))
					tx.rollback();
			
				tx.commit();
		} catch (Exception e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			 session.close();
			 return 0;
		}
		  session.close(); 
		return 1;
	}
	
	
	
	
	public Integer getRefNoFinance(String keydatabase) {
		// TODO Auto-generated method stub
		Session session=TenantInfo.getConnect(keydatabase).openSession();
		try {
			return session.createSQLQuery("select * from PaymentReceiptCommand").getResultList().size();
		}
		catch(NullPointerException e) {
			LOGGER.error(e.getMessage());
			return 0;
		}
	}

	public boolean ifRefNoFinanceExist(String refNoFinance_gen, String keydatabase) {
		// TODO Auto-generated method stub
		Session session=TenantInfo.getConnect(keydatabase).openSession();
		try {
			if(session.createQuery("from PaymentReceiptCommand where refNoFinance = :refNoFinance_gen")
					.setParameter("refNoFinance_gen", refNoFinance_gen).getResultList().size() != 0) {
				return true;
			}
		}
		catch (NullPointerException e) {
			// TODO: handle exception
			LOGGER.error(e.getMessage());
			return true;
		}
		return false;
	}

	public List<GeneralDetailCommand> getGeneralDetailAddPay_Re(String keyCompany, int refTypeID) {
		String keydatabase=TenantInfo.getKeydatabase(keyCompany);
		Session session=TenantInfo.getConnect(keydatabase).openSession();
		try {
			return session.createSQLQuery("select distinct accountObjectID, accountObjectName, accountObjectAddress, "
					+ "accountObjectContactName, journalMemo, description createdBy"
					+ " from PaymentReceiptCommand where refTypeID = :refTypeID  LIMIT 6")
					.setParameter("refTypeID", refTypeID)
					.addEntity(GeneralDetailCommand.class).getResultList();
		}
		catch(NullPointerException e) {
			LOGGER.error(e.getMessage());
			return null;
		}
	}
	public boolean updateWriteCashBook(PaymentReceiptCommand paymentReceipt,String keydatabase) {
		Session session=TenantInfo.getConnect(keydatabase).openSession();
		Transaction tx=null;
		int result=0;
		try {
			 tx = session.beginTransaction();
			
			 session.update(paymentReceipt);
			//gửi payment sang service Report để ghi vào database
			  MessageFormat messageForm=new MessageFormat(EventType.CREATE, paymentReceipt);
			  if(!ReportMessageQueue.produceMsg(messageForm.toString())) {
				  tx.rollback();
			  }
			tx.commit();
		} catch (Exception e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
			 session.close();
			 return false;
		}
		 session.close();
		 return true;
		
	}
	
	public boolean deleteWriteCashBook(PaymentReceiptCommand paymentReceipt,String keydatabase) {
		Session session=TenantInfo.getConnect(keydatabase).openSession();
		Transaction tx=null;
		int result=0;
		try {
			 tx = session.beginTransaction();
			
			 session.update(paymentReceipt);
			//gửi payment sang service Report bỏ ghi vào database
			  MessageFormat messageForm=new MessageFormat(EventType.DELETE, paymentReceipt);
			  if(!ReportMessageQueue.produceMsg(messageForm.toString())) {
				  tx.rollback();
			  }
			tx.commit();
		} catch (Exception e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
			 session.close();
			 return false;
		}
		 session.close();
		 return true;
		
	}
	
}
