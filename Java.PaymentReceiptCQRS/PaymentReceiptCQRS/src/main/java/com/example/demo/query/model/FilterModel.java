package com.example.demo.query.model;


/*
 * filter model: use to save data to filter 
 * create by Quan
 */
public class FilterModel {
	private String columnName;
	private String dataFilter;
	private String dataType;
	private int arrange;
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getDataFilter() {
		return dataFilter;
	}
	public void setDataFilter(String dataFilter) {
		this.dataFilter = dataFilter;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public int getArrange() {
		return arrange;
	}
	public void setArrange(int arrange) {
		this.arrange = arrange;
	}
	
}
