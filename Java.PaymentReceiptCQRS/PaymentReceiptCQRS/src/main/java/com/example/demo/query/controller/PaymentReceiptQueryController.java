package com.example.demo.query.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.command.model.PaymentReceiptCommand;
import com.example.demo.query.model.FilterModel;
import com.example.demo.query.model.PaymentReceiptView;
import com.example.demo.query.service.PaymentReceiptViewService;

@RestController
public class PaymentReceiptQueryController {

	
	
		@Autowired
	PaymentReceiptViewService paymentService;
	
	@RequestMapping("/getAllPage_Size:{size}")
	public Map<String, Object> getAllPage(@PathVariable("size")int size,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
//		Authenticate.Auth(httpServletRequest, httpServletResponse); 
		String keyCompany= httpServletRequest.getHeader("keycompany");
		System.out.println("size:"+size);
		long totalPage = 0;
		Map<String, Object> map = new HashMap<>();
		long x =  paymentService.countByKeyCompany(keyCompany);
		System.out.println(x);
		if(x==0) {
			map.put("totalRecord", x);
			map.put("data", null);
			return map;
		}
		List<PaymentReceiptView> lst = paymentService.getPaymentReceiptOfCompanyPage(keyCompany, 0, size);
		map.put("totalRecord", x);
		map.put("data", lst);
		return map;
	}
	
	

	@RequestMapping("/getPage/page:{page}_size:{size}")
	public List<PaymentReceiptView> get(@PathVariable("page") long page,@PathVariable("size") int size,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
		System.out.println(page);
		System.out.println(size);

		String keyCompany= httpServletRequest.getHeader("keycompany");

		long countRecord =  paymentService.countByKeyCompany(keyCompany);
		
		long index=(page-1)*size;
		if(index>countRecord) {
			return null;
		}
		List<PaymentReceiptView> lst=paymentService.getPaymentReceiptOfCompanyPage(keyCompany, index, size);
		return lst;
		
	}
	
	@RequestMapping("/generateRefNoFinance/{refTypeID}")
	public String generateRefNoFinance(@PathVariable("refTypeID") int refTypeID, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		
		String keyCompany= httpServletRequest.getHeader("keycompany");
		return paymentService.generateRefNoFinace(refTypeID, keyCompany);
	}
	
	@PostMapping("/filterPaymentReceipt")
	public Map<String, Object> filterPaymentReceipt(@RequestBody List<FilterModel> filterData, HttpServletRequest httpServletRequest){
		String keyCompany= httpServletRequest.getHeader("keycompany");
		Map<String, Object> filterMap = new HashMap<>();
		filterMap.put("result", paymentService.getFilterPaymentReceipt(keyCompany, filterData));
		filterMap.put("totalRecord", paymentService.getFilterPaymentReceipt(keyCompany, filterData).size());
		return filterMap;
	}
	
	@PostMapping("/testDate")
	public List<PaymentReceiptView> getListByDate(@RequestBody Map<String, String> data, HttpServletRequest httpServletRequest){
		String keyCompany= httpServletRequest.getHeader("keycompany");
		return paymentService.getListByDate(keyCompany, data);
	}
	
}
