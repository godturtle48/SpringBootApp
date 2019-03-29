package com.example.demo.command.model;

import java.sql.Timestamp;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.demo.rabbidmq.EventType;

@Document(collection="errmessage")
public class ErrMessageModel {
	@Id
	private String id;
	private String refID;
	private Integer version;
	private Timestamp createDate;
	private EventType type;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRefID() {
		return refID;
	}
	public void setRefID(String refID) {
		this.refID = refID;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public EventType getType() {
		return type;
	}
	public void setType(EventType type) {
		this.type = type;
	}
	public ErrMessageModel(String refID, Integer version, EventType type) {
		super();
		this.refID = refID;
		this.version = version;
		this.type = type;
		this.createDate=new Timestamp(System.currentTimeMillis());
	}
	public ErrMessageModel() {
		super();
	}
	
	
	
	
}
