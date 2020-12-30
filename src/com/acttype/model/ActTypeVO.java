package com.acttype.model;
import java.sql.Date;

public class ActTypeVO implements java.io.Serializable{
	private String actTypeNo;
	private String actTypeName;
	
	public String getActTypeNo() {
		return actTypeNo;
	}
	public void setActTypeNo(String actTypeNo){
		this.actTypeNo = actTypeNo;
	}
	public String getActTypeName() {
		return actTypeName;
	}
	public void setActTypeName(String actTypeName) {
		this.actTypeName = actTypeName;
	}
	

}
