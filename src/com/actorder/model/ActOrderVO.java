package com.actorder.model;

import java.sql.Date;

public class ActOrderVO implements java.io.Serializable{
	
	private String actOdno;
	private String actNo;
	private String bk_no;
	private Date odTime;
	private String odStatus;
	private Integer ppl;
	private Integer totalPrice;
	
	public String getActOdno() {
		return actOdno;
	}
	public void setActOdno(String actOdno) {
		this.actOdno = actOdno;
	}
	public String getActNo() {
		return actNo;
	}
	public void setActNo(String bk_no) {
		this.bk_no = bk_no;
	}
	public String getBkNo() {
		return bk_no;
	}
	public void setBkNo(String bk_no) {
		this.bk_no = bk_no;
	}
	public Date getOdTime() {
		return odTime;
	}
	public void setOdTime(Date odTime) {
		this.odTime = odTime;
	}
	public String getOdStatus() {
		return odStatus;
	}
	public void setOdStatus(String odStatus) {
		this.odStatus = odStatus;
	}
	public Integer getPpl() {
		return ppl;
	}
	public void setPpl(Integer ppl) {
		this.ppl = ppl;
	}
	public Integer getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

}
