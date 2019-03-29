package com.example.demo.command.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.command.model.ErrMessageModel;
import com.example.demo.command.repository.ErrMessageRepository;
import com.example.demo.rabbidmq.EventType;

@Service
public class ErrMessageService {

	@Autowired
	ErrMessageRepository errMessageRepository;
	
	public ErrMessageModel get(String refID) {
		ErrMessageModel errMessage=new ErrMessageModel();
		try {
			errMessage=errMessageRepository.findByRefID(refID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return errMessage;
	}

	public void put(String refID, Integer version,EventType type) {
		ErrMessageModel errMessage=new ErrMessageModel(refID, version, type);
		try {
			errMessageRepository.insert(errMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void remove(String refID) {
		try {
			errMessageRepository.deleteByRefID(refID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void put(String refID, Integer version) {
		try {
			ErrMessageModel errMessage=errMessageRepository.findByRefID(refID);
			if(errMessage!=null) {
				errMessage.setVersion(version);
				errMessage.setCreateDate(new Timestamp(System.currentTimeMillis()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public Integer getVersion(String refID) {
		
		try {
			ErrMessageModel errMessage=errMessageRepository.findByRefID(refID);
			return errMessage.getVersion();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
}
