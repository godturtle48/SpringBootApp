package com.example.demo.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ComboBox;
import com.example.demo.repository.ComboBoxRepository;

@Service
public class ComboBoxService {
	@Autowired
	ComboBoxRepository comboBoxRepository;
	public List<Object> loadComboboxAddPayment() {
		// TODO Auto-generated method stub
		return comboBoxRepository.loadComboboxAddPayment();
	}
	public List<Object> loadComboboxAddReceipt() {
		// TODO Auto-generated method stub
		return comboBoxRepository.loadComboboxAddReceipt();
	}
	
}
