package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.ComboBoxService;
import com.example.demo.model.ComboBox;

@RestController
public class BoxController {
	@Autowired
	ComboBoxService comboBoxService;
	@GetMapping("/loadComboboxAddPayment")
	public List<Object> loadComboboxAddPayment(){
		return comboBoxService.loadComboboxAddPayment();
	}
	
	@GetMapping("/loadComboboxAddReceipt")
	public List<Object> loadComboboxAddReceipt(){
		return comboBoxService.loadComboboxAddReceipt();
	}
}
