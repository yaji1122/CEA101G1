package com.service_order.model;

import java.sql.Timestamp;

public class ServiceOrderVO implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String serv_odno;
	private String bk_no;
	private Timestamp od_time;
	private String od_status;
	private String rm_no;
	private String serv_no;
	private Timestamp serv_time;
	private Integer serv_count;
	private Integer total_price;
	private String location;

	public String getServ_odno() {
		return serv_odno;
	}

	public void setServ_odno(String serv_odno) {
		this.serv_odno = serv_odno;
	}

	public String getBk_no() {
		return bk_no;
	}

	public void setBk_no(String bk_no) {
		this.bk_no = bk_no;
	}

	public Timestamp getOd_time() {
		return od_time;
	}

	public void setOd_time(Timestamp od_time) {
		this.od_time = od_time;
	}

	public String getOd_status() {
		return od_status;
	}

	public void setOd_status(String od_status) {
		this.od_status = od_status;
	}

	public String getRm_no() {
		return rm_no;
	}

	public void setRm_no(String rm_no) {
		this.rm_no = rm_no;
	}

	public String getServ_no() {
		return serv_no;
	}

	public void setServ_no(String serv_no) {
		this.serv_no = serv_no;
	}

	public Timestamp getServ_time() {
		return serv_time;
	}

	public void setServ_time(Timestamp serv_time) {
		this.serv_time = serv_time;
	}

	public Integer getServ_count() {
		return serv_count;
	}

	public void setServ_count(Integer serv_count) {
		this.serv_count = serv_count;
	}

	public Integer getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Integer total_price) {
		this.total_price = total_price;
	}
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
