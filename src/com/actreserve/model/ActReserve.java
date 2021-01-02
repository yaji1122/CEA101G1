package com.actreserve.model;

import java.util.Date;

public class ActReserve implements java.io.Serializable{
	
    private static final long  serialVersionUID = 1L;
	
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

	@Override
	public String toString() {
		return "ACT [ACT_NO="+actNo+",ACT_EVENT_NO="+actEventNo+",ACT_NAME="+actName+",ACT_STATIS="+actStatus+
				",ACT_REG_TIME="+actRegTime+",ACT_DATE="+actDate+",DEADLINE="+deadLine+",ACT_TIME="+actTime+
				",PPL="+participant+"人,ACT_PRICE="+actPrice+"]";
				
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) //判斷該物件是否為本ACT
			return true;
		if (obj == null)//判斷該物件為其他ACT
			return false;
		if (getClass() != obj.getClass()) //該類別與物件取得class不相同
			return false;
		
		ActReserve other = (ActReserve) obj;//對取得之物件進行判別
		if (actNo == null) { //送入為空值
			if (other.actNo != null)
				return false;
		} else if (!actNo.equals(other.actNo))
			return false;
		
		return true;
	}

}
