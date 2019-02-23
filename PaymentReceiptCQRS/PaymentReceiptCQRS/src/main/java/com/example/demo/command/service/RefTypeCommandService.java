package com.example.demo.command.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.command.model.RefTypeCommand;
import com.example.demo.command.repository.RefTypeCommandRepository;

@Service
public class RefTypeCommandService {

	
	@Autowired
	RefTypeCommandRepository refTypeRepository;
	public int Save(RefTypeCommand refType) {
		return refTypeRepository.save(refType);
	}
	
	public int delete(RefTypeCommand refType) {
		return refTypeRepository.delete(refType);
	}
	
	public int update(RefTypeCommand refType) {
		return refTypeRepository.update(refType);
	}
	public RefTypeCommand findByID(int id) {
		return refTypeRepository.getRefTypeById(id);
	}
	
	public List<RefTypeCommand> findAll() {
		return refTypeRepository.getAll();
	}
}
