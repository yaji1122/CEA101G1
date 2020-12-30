package com.sale_event.model;

import java.sql.Date;


public class Sale_eventVO implements java.io.Serializable {
	private String sale_no;
	private Date sale_start;
	private Date sale_end;
	private String sale_status;
	public String getSale_status() {
		return sale_status;
	}
	public void setSale_status(String sale_status) {
		this.sale_status = sale_status;
	}
	public String getSale_no() {
		return sale_no;
	}
	public void setSale_no(String sale_no) {
		this.sale_no = sale_no;
	}
	public Date getSale_start() {
		return sale_start;
	}
	public void setSale_start(Date sale_start) {
		this.sale_start = sale_start;
	}
	public Date getSale_end() {
		return sale_end;
	}
	public void setSale_end(Date sale_end) {
		this.sale_end = sale_end;
	}
	
	
	
}
