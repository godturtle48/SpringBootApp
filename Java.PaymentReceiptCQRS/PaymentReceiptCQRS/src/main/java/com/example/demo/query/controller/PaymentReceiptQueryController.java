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
import com.example.demo.query.model.CustomerDetails;
import com.example.demo.query.model.FilterModel;
import com.example.demo.query.model.PaymentReceiptView;
import com.example.demo.query.service.CustomerDetailsService;
import com.example.demo.query.service.PaymentReceiptViewService;

@RestController
public class PaymentReceiptQueryController {

	@Autowired
	CustomerDetailsService customerDetailSv;
	
	@Autowired
	PaymentReceiptViewService paymentService;
	
	@GetMapping("/getAllPage_Size:{size}")
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
	
	

	@GetMapping("/getPage/page:{page}_size:{size}")
	public List<PaymentReceiptView> get(@PathVariable("page") long page,@PathVariable("size") int size,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
		System.out.println(page);
		System.out.println(size);

		String keyCompany= httpServletRequest.getHeader("keycompany");

//		long countRecord =  paymentService.countByKeyCompany(keyCompany);
//		
		long index=(page-1)*size;
//		if(index>countRecord) {
//			return null;
//		}
		List<PaymentReceiptView> lst=paymentService.getPaymentReceiptOfCompanyPage(keyCompany, index, size);
		return lst;
		
	}
	
	@GetMapping("/generateRefNoFinance/{refTypeID}")
	public String generateRefNoFinance(@PathVariable("refTypeID") int refTypeID, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		
		String keyCompany= httpServletRequest.getHeader("keycompany");
		return paymentService.generateRefNoFinace(refTypeID, keyCompany);
	}
	
	@PostMapping("/filterPaymentReceipt")
	public Map<String, Object> filterPaymentReceipt(@RequestBody List<FilterModel> filterData, HttpServletRequest httpServletRequest){
		String keyCompany= httpServletRequest.getHeader("keycompany");
		Map<String, Object> filterMap = new HashMap<>();
		
		try {
			filterMap.put("result", paymentService.getFilterPaymentReceipt(keyCompany, filterData));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
//		filterMap.put("result", paymentService.getFilterPaymentReceipt(keyCompany, filterData));
		filterMap.put("totalRecord", paymentService.getFilterPaymentReceipt(keyCompany, filterData).size());
		return filterMap;
	}
	
	//load data to combobox
	@GetMapping("/getCustomerDetail")
	public List<CustomerDetails> getCustomerDetail(HttpServletRequest httpServletRequest){
		String keyCompany= httpServletRequest.getHeader("keycompany");
		return customerDetailSv.getGeneralDetails(keyCompany);
	}
	
	//load data to combobox by input and display by autocomplete
	@GetMapping("/getCustomerDetailByInput:{input}")
	public List<CustomerDetails> getCustomerDetailByInput(@PathVariable("input") String input, HttpServletRequest httpServletRequest){
		String keyCompany= httpServletRequest.getHeader("keycompany");
		return customerDetailSv.getGeneralDetails(keyCompany);
	}
	
	public String generateCustomerDetail(HttpServletRequest httpServletRequest) {
		String keyCompany= httpServletRequest.getHeader("keycompany");
		customerDetailSv.generateCustomerDetail(keyCompany);
		return "ok";
	}
}
