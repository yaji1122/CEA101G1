package com.actevent.model;

import java.sql.Date;


public class ActEventVO implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String actEventNo;
	private String actEventName;
	

	public String getActEventNo() {
		return actEventNo;
	}
	public void setActEventNo(String actEventNo) {
		this.actEventNo = actEventNo;
	}
	
	public String getActEventName() {
		return actEventName;
	}
	public void setActEventName(String actEventName) {
		this.actEventName = actEventName;
	}
	
	
}
