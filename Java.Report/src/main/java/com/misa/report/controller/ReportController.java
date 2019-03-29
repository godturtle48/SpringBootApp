package com.misa.report.controller;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.misa.report.model.CACashbook;
import com.misa.report.model.GeneralLedger;
import com.misa.report.model.Inventory;
import com.misa.report.model.ParameterReportModel;
import com.misa.report.service.CACashbookService;
import com.misa.report.service.GeneralLedgerService;

@RestController
public class ReportController {
	@Autowired
	GeneralLedgerService generalLedgerService;
	@Autowired
	CACashbookService caCashbookService;
	
	@RequestMapping(value="/report", method=RequestMethod.POST)
	public Map<String, Object> test(@RequestBody ParameterReportModel paramater,HttpServletRequest request) {
		String keyCompany=request.getHeader("keycompany");
		if(keyCompany.equals("")) {
			Map<String, Object> map=new HashMap<>();
			map.put("error", "Không có dữ liệu về công ty");
			return map;
		}
		if(paramater.getFromDate()==null) {
			Map<String, Object> map=new HashMap<>();
			map.put("error", "Ngày bắt đầu không được bỏ trống");
			return map;
		}
		if(paramater.getToDate()==null) {
			Map<String, Object> map=new HashMap<>();
			map.put("error", "Ngày kết thúc không được bỏ trống");
			return map;
		}
		 return generalLedgerService.getReport(paramater,keyCompany);
	}

}
