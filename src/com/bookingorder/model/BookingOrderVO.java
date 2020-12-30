package com.bookingorder.model;

import java.io.Serializable;
import java.sql.*;

public class BookingOrderVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String bk_no;
	private String mb_id;
	private Date bk_date;
	private Date dateIn;
	private Date dateOut;
	private Date checkIn;
	private Date checkOut;
	private String bk_status;
	private Integer total_price;
	
	
	
	public BookingOrderVO() {};
	
	public BookingOrderVO(String bk_no, String mb_id, Date bk_date, Date dateIn, Date dateOut, String bk_status, 
			Integer total_price) {
		super();
		this.bk_no = bk_no;
		this.mb_id = mb_id;
		this.bk_date = bk_date;
		this.dateIn = dateIn;
		this.dateOut = dateOut;
		this.bk_status = bk_status;
		this.total_price = total_price;
	}

	public String getBk_no() {
		return bk_no;
	}

	public void setBk_no(String bk_no) {
		this.bk_no = bk_no;
	}

	public String getMb_id() {
		return mb_id;
	}

	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}

	public Date getBk_date() {
		return bk_date;
	}

	public void setBk_date(Date bk_date) {
		this.bk_date = bk_date;
	}

	public Date getDateIn() {
		return dateIn;
	}

	public void setDateIn(Date dateIn) {
		this.dateIn = dateIn;
	}

	public Date getDateOut() {
		return dateOut;
	}

	public void setDateOut(Date dateOut) {
		this.dateOut = dateOut;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}
	
	public String getBk_status() {
		return bk_status;
	}

	public void setBk_status(String od_status) {
		this.bk_status = od_status;
	}

	public Integer getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Integer total_price) {
		this.total_price = total_price;
	}
	
	
	
	
}
