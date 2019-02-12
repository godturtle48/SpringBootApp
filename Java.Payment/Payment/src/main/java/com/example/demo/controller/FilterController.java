package com.example.demo.controller;

import com.example.demo.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.sound.midi.Soundbank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.FilterService;
import com.example.demo.Service.InvoiceDetailService;
import com.example.demo.Service.PaymentReceiptService;
import com.example.demo.Service.RefTypeService;

@RestController
public class FilterController {
	@Autowired
	RefTypeService refTypeService;
	@Autowired
	PaymentReceiptService paymentService;	
	@Autowired
	InvoiceDetailService invoiceService;
	@Autowired
	FilterService filterService;

	@CrossOrigin	
	@PostMapping(value="/filter")
	public Map<Object,Object> Filter(@RequestBody List<FormFilter> FormFilters, HttpServletRequest httpSvlRes){
		String keyCompanyString = (String)httpSvlRes.getHeader("keyCompany");
		Map<Object,Object> map = new HashMap<>();
		List<PaymentReceipt> listRes = filterService.filter(FormFilters);
		map.put("result", listRes);
		map.put("totalRecord", listRes.size());
		return map;
	}


	
	@PostMapping(value="/filterDate")
	public List<PaymentReceipt> filterDate(@RequestBody Map<String, String> dateData, HttpServletRequest httpSvlRes) {
		String keyCompany = (String)httpSvlRes.getHeader("keyCompany");
		Map<String, Object> map = new HashMap<String, Object>();
		List<PaymentReceipt> list = filterService.filterByDate(dateData.get("data"), 
																Integer.parseInt(dateData.get("order")),
																dateData.get("columnName"), keyCompany);
		return list;
	}
	
	@PostMapping(value="/filterExactlyStr")
	public List filterExactlyStr(@RequestBody Map<String, String> dataToFilter, HttpServletRequest httpSvlRes){
		String keyCompany = (String)httpSvlRes.getHeader("keyCompany");
		return filterService.filterExactString(dataToFilter.get("data"), 
													dataToFilter.get("columnName"),
													keyCompany);
	}
	
	@PostMapping(value="/filterRef")
	public List<PaymentReceipt> filterRef(@RequestBody Map<String, String> dataToFilter, HttpServletRequest httpSvlRes){
		String keyCompany = (String)httpSvlRes.getHeader("keyCompany");
		return filterService.filterRef(dataToFilter.get("data"), 
													"refType",
													"company1");
	}
	
	@PostMapping(value="/filterNumber")
	public List filterNumber(@RequestBody Map<String, String> dataToFilter, HttpServletRequest httpSvlRes){
		String keyCompany = (String)httpSvlRes.getHeader("keyCompany");
		return filterService.filterNumber(Double.parseDouble(dataToFilter.get("data")),
													Integer.parseInt(dataToFilter.get("order")), 
													dataToFilter.get("columnName"),
													keyCompany);
	}
	//filter theo khop xau mau
	@PostMapping(value="/filterNearString")
	public List filteNearString(@RequestBody Map<String, String> dataToFilter, HttpServletRequest httpSvlRes) {
		String keyCompany = (String)httpSvlRes.getHeader("keyCompany");
		return filterService.filterNearString(dataToFilter.get("data"),
												dataToFilter.get("columnName"),
												Integer.parseInt(dataToFilter.get("order")), 
												keyCompany);
	}

	

}
