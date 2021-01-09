package com.act.model;

import java.sql.Date;

public class ActVO implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;

	private String actNo;
	private String actEventNo;
	private String actName;
	private String actStatus;
	private Date actDate;
	private String actTime;
	private String participant;
	private Integer actPrice;
	private byte[] actPic;
	private String actInfo;
	
	public byte[] getActPic() {
		return actPic;
	}
	public void setActPic(byte[] actPic) {
		this.actPic = actPic;
	}
	public String getActInfo() {
		return actInfo;
	}
	public void setActInfo(String actInfo) {
		this.actInfo = actInfo;
	}
	public String getActNo() {
		return actNo;
	}
	public void setActNo(String actNo) {
		this.actNo = actNo;
	}
	public String getActEventNo() {
		return actEventNo;
	}
	public void setActEventNo(String actEventNo) {
		this.actEventNo = actEventNo;
	}
	public String getActName() {
		return actName;
	}
	public void setActName(String actName) {
		this.actName = actName;
	}
	public String getActStatus() {
		return actStatus;
	}
	public void setActStatus(String actStatus) {
		this.actStatus = actStatus;
	}
	public Date getActDate() {
		return actDate;
	}
	public void setActDate(Date actDate) {
		this.actDate = actDate;
	}
	public String getActTime() {
		return actTime;
	}
	public void setActTime(String actTime) {
		this.actTime = actTime;
	}
	public String getParticipant() {
		return participant;
	}
	public void setParticipant(String participant) {
		this.participant = participant;
	}
	public Integer getActPrice() {
		return actPrice;
	}
	public void setActPrice(Integer actPrice) {
		this.actPrice = actPrice;
	}
	
	

}
