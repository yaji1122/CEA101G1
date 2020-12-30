package com.bookingdetail.model;

import java.io.Serializable;

public class BookingDetailVO implements Serializable {
	private String bk_no;
	private String rm_type;
	private Integer rm_price;
	private Integer qty;
	
	public BookingDetailVO() {};
	
	public BookingDetailVO(String bk_no, String rm_type, Integer rm_price, Integer qty) {
		super();
		this.bk_no = bk_no;
		this.rm_type = rm_type;
		this.rm_price = rm_price;
		this.qty = qty;
	}

	public String getBk_no() {
		return bk_no;
	}

	public void setBk_no(String bk_no) {
		this.bk_no = bk_no;
	}

	public String getRm_type() {
		return rm_type;
	}

	public void setRm_type(String rm_type) {
		this.rm_type = rm_type;
	}

	public Integer getRm_price() {
		return rm_price;
	}

	public void setRm_price(Integer rm_price) {
		this.rm_price = rm_price;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}
	
	
}
