package com.payment.model;

import java.io.Serializable;

public class PaymentVO implements Serializable {
	private String pay_no;
	private String mb_id;
	private String card_no;
	private String card_name;
	private String exp_mon;
	private String exp_year;
	private String csc;
	
	public PaymentVO() {};
	
	public PaymentVO(String pay_no, String card_no, String card_name, String exp_mon, String exp_year, String csc) {
		super();
		this.pay_no = pay_no;
		this.card_no = card_no;
		this.card_name = card_name;
		this.exp_mon = exp_mon;
		this.exp_year = exp_year;
		this.csc = csc;
	}

	public String getPay_no() {
		return pay_no;
	}

	public void setPay_no(String pay_no) {
		this.pay_no = pay_no;
	}

	public String getCard_no() {
		return card_no;
	}

	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}

	public String getCard_name() {
		return card_name;
	}

	public void setCard_name(String card_name) {
		this.card_name = card_name;
	}

	public String getExp_mon() {
		return exp_mon;
	}

	public void setExp_mon(String exp_mon) {
		this.exp_mon = exp_mon;
	}

	public String getExp_year() {
		return exp_year;
	}

	public void setExp_year(String exp_year) {
		this.exp_year = exp_year;
	}

	public String getCsc() {
		return csc;
	}

	public void setCsc(String csc) {
		this.csc = csc;
	}

	/**
	 * @return the mb_id
	 */
	public String getMb_id() {
		return mb_id;
	}

	/**
	 * @param mb_id the mb_id to set
	 */
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	
	
	
}
