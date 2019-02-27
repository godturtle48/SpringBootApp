package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.RefType;
import com.example.demo.repository.RefTypeRepository;

@Service
public class RefTypeService {

	
	@Autowired
	RefTypeRepository refTypeRepository;
	public int Save(RefType refType) {
		return refTypeRepository.save(refType);
	}
	
	public int delete(RefType refType) {
		return refTypeRepository.delete(refType);
	}
	
	public int update(RefType refType) {
		return refTypeRepository.update(refType);
	}
	public RefType findByID(int id) {
		return refTypeRepository.getRefTypeById(id);
	}
	
	public List<RefType> findAll() {
		return refTypeRepository.getAll();
	}
}
