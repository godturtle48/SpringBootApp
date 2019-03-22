package com.example.demo.query.service;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.example.demo.query.model.FilterModel;
import com.example.demo.query.model.PaymentReceiptView;
import com.example.demo.query.repository.PaymentReceiptViewRepository;


@Service
public class PaymentReceiptViewService {

	@Autowired
	PaymentReceiptViewRepository  paymentRepository;
	
	@Autowired
	MongoTemplate mongoTemplate;
	public long countByKeyCompany(String keyCompany) {
		return paymentRepository.countByKeyCompany(keyCompany);
	}
	

	public int insert(PaymentReceiptView paymentReceipt) {
		int result=0;
		try {
			paymentRepository.insert(paymentReceipt);
			result=1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public int delete(PaymentReceiptView paymentReceipt) {
		int result=0;
		try {
			paymentRepository.deleteByRefID((paymentReceipt).getRefID());
			result=1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	
	}
	public List<PaymentReceiptView> getPaymentReceiptOfCompany(String keyCompany){
		return paymentRepository.findByKeyCompany(keyCompany);
	}
	
	public List<PaymentReceiptView> getPaymentReceiptOfCompanyPage(String keyCompany,long index,int size){
		List<PaymentReceiptView> lst=new ArrayList<>();
		try {
			  Query query = new Query();
			   query.addCriteria(Criteria.where("keyCompany").is(keyCompany));
			   query.with(new Sort(Sort.Direction.ASC, "modifiedDate"));
			   query.skip(index);
			   query.limit(size);
			   lst=mongoTemplate.find(query, PaymentReceiptView.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
			   return lst;

	}
	
	public List<PaymentReceiptView> getAlL(){
		return paymentRepository.findAll();
				}
	public int update(PaymentReceiptView paymentReceipt) {
		int result=0;
		try {
			paymentRepository.save(paymentReceipt);
			result=1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public PaymentReceiptView findByRefID(String refID) {
		return paymentRepository.findByRefID(refID);
	}
	
	//filter By any column
	public List<PaymentReceiptView> getFilterPaymentReceipt(String keyCompany, List<FilterModel> filterData ){
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Query query = new Query();
		query.addCriteria(Criteria.where("keyCompany").is(keyCompany));
		int numFieldFilter = filterData.size();
		System.out.println("==========================work here=========================");
		System.out.println("==========================work here=========================");
		System.out.println("==========================work here=========================");
		System.out.println("==========================work here=========================");
		System.out.println("==========================work here=========================");
		System.out.println("==========================work here=========================");
		System.out.println("==========================work here=========================");
		System.out.println("==========================work here=========================");
		System.out.println("==========================work here=========================");
		System.out.println("==========================work here=========================" + keyCompany);
		for(int i = 0; i < numFieldFilter; i++) {
			if(filterData.get(i).getDataType().equals("date")) {
				try {
					if(filterData.get(i).getArrange() == 1) {//
						query.addCriteria(Criteria.where(filterData.get(i).getColumnName())
								.gte(dateFormatter.parse(filterData.get(i).getDataFilter())));
						query.with(new Sort(Sort.Direction.ASC, filterData.get(i).getColumnName()));
					}
					else {
						query.addCriteria(Criteria.where(filterData.get(i).getColumnName())
								.lte(dateFormatter.parse(filterData.get(i).getDataFilter())));
						query.with(new Sort(Sort.Direction.DESC, filterData.get(i).getColumnName()));
					}
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(filterData.get(i).getDataType().equals("string")){
					query.addCriteria(Criteria.where(filterData.get(i).getColumnName())
										.regex(filterData.get(i).getDataFilter()));
			}
			else if(filterData.get(i).getDataType().equals("double")){
				if(filterData.get(i).getArrange() == 1) {
					query.addCriteria(Criteria.where(filterData.get(i).getColumnName())
							.gte(Integer.parseInt(filterData.get(i).getDataFilter())));
				}
				else {
					query.addCriteria(Criteria.where(filterData.get(i).getColumnName())
							.lte(Integer.parseInt(filterData.get(i).getDataFilter())));
				}
					
			}
			else if(filterData.get(i).getDataType().equals("stringExactly")){
					query.addCriteria(Criteria.where(filterData.get(i).getColumnName())
								.is(filterData.get(i).getDataFilter()));
			}
		}
		List<PaymentReceiptView> filterResult = mongoTemplate.find(query, PaymentReceiptView.class);
		return filterResult;
	}
	/*
	 * Generate refNoFinance automatic
	 * refTypeID = 1 => Receipt(PT)
	 * refTypeID = 2 => Payment(PC)
	 */
	
	public String generateRefNoFinace(int refTypeID, String keyCompany) {
		//Count number Ref of company belong to Type
		Query query = new Query();
		String generateRefNoFinance = (refTypeID == 1) ? "PT" : "PC";
		query.addCriteria(Criteria.where("ref.refTypeID").is(refTypeID));
		query.addCriteria(Criteria.where("keyCompany").is(keyCompany));
		Integer countNumRef = mongoTemplate.find(query, PaymentReceiptView.class).size() + 1;//Number ref of this type
		
		//RefNo String generrate returned
		
		Integer lengCount = countNumRef.toString().length();
		if(lengCount < 10){
			String appendZero = "";
			for(int i = 0; i < 10 - lengCount; i++){
				appendZero += "0";
			}
			generateRefNoFinance += appendZero + countNumRef.toString();
			
			while(IfRefNoFinanceExist(generateRefNoFinance, keyCompany)) {
				countNumRef++;
				lengCount = countNumRef.toString().length();
				if(lengCount < 10){
					appendZero = "";
					for(int i = 0; i < 10 - lengCount; i++){
						appendZero += "0";
					}
					generateRefNoFinance += appendZero + countNumRef.toString();
				}
				else{
					while(IfRefNoFinanceExist(generateRefNoFinance, keyCompany)) {
						countNumRef++;
					}
					generateRefNoFinance += countNumRef.toString();
				}
			}
		}
		else{
			while(IfRefNoFinanceExist(generateRefNoFinance, keyCompany)) {
				countNumRef++;
			}
			generateRefNoFinance += countNumRef.toString();
		}
		return generateRefNoFinance;
		
	}
	
	//Check if RefNoFinance exist
	public boolean IfRefNoFinanceExist(String refNoFinance, String keyCompany) {
		Query query = new Query();
		query.addCriteria(Criteria.where("keyCompany").is(keyCompany));
		query.addCriteria(Criteria.where("refNoFinance").is(refNoFinance));
		List<PaymentReceiptView> list = mongoTemplate.find(query, PaymentReceiptView.class);
		try {
			if(list.size() == 0) return false;
		}
		catch(NullPointerException e) {
			return false;
		}
		return true;
	}


	public List<PaymentReceiptView> getListByDate(String keyCompany, Map<String, String> data) {
		// TODO Auto-generated method stub
		Query query = new Query();
		query.addCriteria(Criteria.where("keyCompany").is(keyCompany));
		query.addCriteria(Criteria.where("ref.refTypeID")
				.is(1));
		List<PaymentReceiptView> filterResult = mongoTemplate.find(query, PaymentReceiptView.class);
		return filterResult;
	}	
	
	
}
