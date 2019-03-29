package com.example.demo.command.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.command.model.PaymentReceiptCommand;
import com.example.demo.command.service.PaymentReceiptCommandService;

@RestController
public class WriteCashBookController {

	@Autowired
	PaymentReceiptCommandService paymentReceiptService;
	@GetMapping("/write:{idPayment}")
	public Map<String, Object> write(@PathVariable String idPayment,HttpServletRequest httpServletRequest){
		System.out.println("id: " + idPayment + "==============================================");
		System.out.println("id: " + idPayment);
		System.out.println("id: " + idPayment);
		System.out.println("id: " + idPayment);
		System.out.println("id: " + idPayment);
		Map<String, Object> map=new HashMap<>();
		String keydatabase=(String) httpServletRequest.getAttribute("keydatabase");
		String keyCompany= httpServletRequest.getHeader("keycompany");
		PaymentReceiptCommand paymentReceiptCommand=paymentReceiptService.findByID(idPayment,keydatabase);
		if(paymentReceiptCommand==null) {
			map.put("error", "Không tìm thấy chứng từ");
			return map;
		}
//		 kiểm tra xem đã ghi chứng từ chưa
		if(paymentReceiptCommand.getIsPostedFinance()==1) {
			map.put("error", "Chứng từ đã được ghi sổ");
			return map;
		}
		//Đánh dấu đã ghi sổ
		paymentReceiptCommand.setIsPostedFinance(Integer.valueOf(1));
	
		if(paymentReceiptService.updateWriteCashBook(paymentReceiptCommand,keydatabase)) {
			map.put("message", "Bỏ ghi chứng từ thành công");
		}else {
			map.put("error", "Bỏ ghi chứng từ thất bại");
		};
		return map;
	}
	@GetMapping("/deleteWrite:{idPayment}")
	public Map<String, Object> delete(@PathVariable String idPayment,HttpServletRequest httpServletRequest){
		String keydatabase=(String) httpServletRequest.getAttribute("keydatabase");
		String keyCompany= httpServletRequest.getHeader("keycompany");
		Map<String, Object> map=new HashMap<>();
		PaymentReceiptCommand paymentReceiptCommand=paymentReceiptService.findByID(idPayment,keydatabase);
		if(paymentReceiptCommand==null) {
			map.put("error", "Không tìm thấy chứng từ");
			return map;
		}
//		 kiểm tra xem đã ghi chứng từ chưa
		if(paymentReceiptCommand.getIsPostedFinance()==0) {
			map.put("error", "Chứng từ đã được bỏ ghi sổ");
			return map;
		}
		//Đánh dấu đã bỏ ghi sổ
		paymentReceiptCommand.setIsPostedFinance(Integer.valueOf(0));
		if(paymentReceiptService.deleteWriteCashBook(paymentReceiptCommand,keydatabase)) {
			map.put("message", "Bỏ ghi chứng từ thành công");
		}else {
			map.put("error", "Bỏ ghi chứng từ thất bại");
		};
		return map;
	}
	
}
