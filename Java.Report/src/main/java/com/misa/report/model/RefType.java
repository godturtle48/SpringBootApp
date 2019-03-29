package com.misa.report.model;



import org.json.simple.JSONObject;

public class RefType {
	private Integer refTypeID;
	private String refTypeName;
	public Integer getRefTypeID() {
		return refTypeID;
	}
	public void setRefTypeID(Integer refTypeID) {
		this.refTypeID = refTypeID;
	}
	public String getRefTypeName() {
		return refTypeName;
	}
	public void setRefTypeName(String refTypeName) {
		this.refTypeName = refTypeName;
	}
	public String toString() {
		JSONObject obj=new JSONObject();
		obj.put("refTypeID",refTypeID);
		obj.put("refTypeName", refTypeName);
		return obj.toJSONString();

	}
	
}
