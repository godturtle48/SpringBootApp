package com.misa.report.service;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.misa.report.model.GeneralLedger;
import com.misa.report.repository.GeneralLedgerRepository;




import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;


import com.misa.report.model.CACashbook;
import com.misa.report.model.GeneralLedger;
import com.misa.report.model.Inventory;
import com.misa.report.model.ParameterReportModel;
import com.misa.report.repository.GeneralLedgerRepository;

@Service
public class GeneralLedgerService {
	@Autowired
	GeneralLedgerRepository generalLedgerRepository;
	@Autowired
	MongoTemplate mongoTemplate;
	
	public boolean write(GeneralLedger generalLedger) {
		try {
			generalLedgerRepository.save(generalLedger);
			System.out.println(generalLedger);
			return true;
		}
		catch(Exception e) {
			return false;
		}		
	}
	
	public boolean unWrite(String string) {
		try {
			generalLedgerRepository.deleteByRefID(string);
			return true;
		}
		catch(Exception e) {
			return false;
		}		
	}
	
	public List<GeneralLedger>  findAll(String keycompany) {
		List<GeneralLedger> list;
		try {
			list=generalLedgerRepository.findByKeycompany(keycompany);
			System.out.println(list);
			return list;
		}catch (Exception e) {
			return null;
		}
	}
	public Map<String, Object>getReport(ParameterReportModel parameter,String keyCompany){
		Map<String, Object> map=new HashMap<>();
		Inventory inven=new Inventory();
		Double inventoryMoney=0.0;
		
		
		try {
			Aggregation agg = Aggregation.newAggregation(
	                Aggregation.match(new Criteria().where("postedDate").lt(parameter.getFromDate()).and("keyCompany").is(keyCompany)),
	                Aggregation.group().sum("signTotalAmountOC").as("inventory")
	        );
			AggregationResults<Inventory> result = mongoTemplate.aggregate(agg, CACashbook.class, Inventory.class);
			List<Inventory> ins=result.getMappedResults();
			if(ins.size()!=0)
			{
				inven= result.getMappedResults().get(0);
				inventoryMoney=	inven.getInventory();
			}
	
		} catch (Exception e) {
			e.printStackTrace();
			map.put("error", "Lỗi tính sô tồn");
			
		}
		List<GeneralLedger> generalLedger=  new ArrayList<>();
		try {

			Query query = new Query();
			query.addCriteria(Criteria.where("keyCompany").is(keyCompany).and("postedDate").gte(parameter.getFromDate()).lte(parameter.getToDate()));
			generalLedger=mongoTemplate.find(query, GeneralLedger.class);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("error", "Lỗi lấy báo cáo");
			return map;
		}
		
		map.put("message", "success");
		map.put("data",generalLedger);
		map.put("inventoryMoney", inventoryMoney);
		return map;
	}


	
	public boolean insert(GeneralLedger generalLedger) {
		boolean isSuccess = false;
		try {
			isSuccess = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSuccess;
	}
	
	public boolean delete(GeneralLedger generalLedger) {
		boolean isSuccess = false;
		try {
			isSuccess = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSuccess;
	}
	
	public List<GeneralLedger> getGeneralLedgerOfCompanyPage(String keyCompany, long index, int size) {
		List<GeneralLedger> listReport = new ArrayList<>();
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listReport;
	}
	
	public boolean update(GeneralLedger generalLedger) {
		boolean isSuccess = false;
		try {
			isSuccess = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSuccess;
	}
}
