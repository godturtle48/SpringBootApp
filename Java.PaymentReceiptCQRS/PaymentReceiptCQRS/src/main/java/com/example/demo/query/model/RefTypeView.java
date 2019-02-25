package com.example.demo.query.model;

public class RefTypeView {

	private Integer reftypeID;
	private String reftypeName;
	public Integer getReftypeID() {
		return reftypeID;
	}
	public void setReftypeID(Integer reftypeID) {
		this.reftypeID = reftypeID;
	}
	public String getReftypeName() {
		return reftypeName;
	}
	public void setReftypeName(String reftypeName) {
		this.reftypeName = reftypeName;
	}
	public RefTypeView(Integer reftypeID, String reftypeName) {
		super();
		this.reftypeID = reftypeID;
		this.reftypeName = reftypeName;
	}
	public RefTypeView() {
		super();
	}
	
}
