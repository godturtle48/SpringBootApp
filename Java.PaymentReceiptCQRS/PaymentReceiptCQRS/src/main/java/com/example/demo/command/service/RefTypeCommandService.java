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
	public int Save(RefTypeCommand refType,String keydatabase) {
		return refTypeRepository.save(refType, keydatabase);
	}
	
	public int delete(RefTypeCommand refType,String keydatabase) {
		return refTypeRepository.delete(refType, keydatabase);
	}
	
	public int update(RefTypeCommand refType,String keydatabase) {
		return refTypeRepository.update(refType, keydatabase);
	}
	public RefTypeCommand findByID(int id,String keydatabase) {
		return refTypeRepository.getRefTypeById(id, keydatabase);
	}
	
	public List<RefTypeCommand> findAll(String keydatabase) {
		return refTypeRepository.getAll(keydatabase);
	}
}
