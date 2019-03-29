package com.misa.report.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.misa.report.model.ParameterReportModel;





@RestController
public class Consumer {
	@GetMapping("/getGeneralLedger")
	public Map<String,Object> getGeneralLedger(@RequestBody List<ParameterReportModel> parameters, HttpServletRequest request, HttpServletResponse response) {
		String keyCompany = request.getHeader("keycompany");
		Map<String,Object> filterMap = new HashMap<>();
		
		return filterMap;
	}
	
	@PostMapping("/test")
	public String hello(@RequestBody ParameterReportModel parameter, HttpServletRequest request, HttpServletResponse response) {
		String keyCompany = request.getHeader("keycompany");
		System.out.println(keyCompany);
		System.out.println(parameter.getFromDate());
		System.out.println(parameter.getToDate());
		System.out.println(parameter.getBranch());
		return "test"; 
	}
	
	@GetMapping("/test")
	public String hello2(@RequestBody ParameterReportModel parameter, HttpServletRequest request, HttpServletResponse response) {
		String keyCompany = request.getHeader("keycompany");
		System.out.println(keyCompany);
		System.out.println(parameter.getFromDate());
		System.out.println(parameter.getToDate());
		System.out.println(parameter.getBranch());
		return "test"; 
	}
}