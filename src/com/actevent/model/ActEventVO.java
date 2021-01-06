package com.actevent.model;

import java.sql.Date;


public class ActEventVO implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String actEventNo;
	private String actTypeNo;
	private String actEventName;
	private String actInfo;
	
	public String getActInfo() {
		return actInfo;
	}
	public void setActInfo(String actInfo) {
		this.actInfo = actInfo;
	}
	public String getActEventNo() {
		return actEventNo;
	}
	public void setActEventNo(String actEventNo) {
		this.actEventNo = actEventNo;
	}
	public String getActTypeNo() {
		return actTypeNo;
	}
	public void setActTypeNo(String actTypeNo) {
		this.actTypeNo = actTypeNo;
	}
	public String getActEventName() {
		return actEventName;
	}
	public void setActEventName(String actEventName) {
		this.actEventName = actEventName;
	}
	
	
}
