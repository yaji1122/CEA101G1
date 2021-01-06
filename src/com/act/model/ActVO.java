package com.act.model;

import java.sql.Date;

public class ActVO implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;

	private String actNo;
	private String actEventNo;
	private String actName;
	private String actStatus;
	private Date actRegTime;
	private Date actDate;
	private Date deadLine;
	private String actTime;
	private String participant;
	private Integer actPrice;
	
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
	public Date getActRegTime() {
		return actRegTime;
	}
	public void setActRegTime(Date actRegTime) {
		this.actRegTime = actRegTime;
	}
	public Date getActDate() {
		return actDate;
	}
	public void setActDate(Date actDate) {
		this.actDate = actDate;
	}
	public Date getDeadLine() {
		return deadLine;
	}
	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
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
