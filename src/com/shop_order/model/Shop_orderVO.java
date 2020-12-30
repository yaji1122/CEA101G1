package com.shop_order.model;

import java.sql.Timestamp;

public class Shop_orderVO implements java.io.Serializable{
	private String sp_odno;
	private String mb_id;
	private Timestamp sp_time;
	private String sp_status;
	private Timestamp sp_dlvr;
	private Double total_price;
	private Integer points_total;
	private String rm_no;
	public String getSp_odno() {
		return sp_odno;
	}
	public void setSp_odno(String sp_odno) {
		this.sp_odno = sp_odno;
	}
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	public Timestamp getSp_time() {
		return sp_time;
	}
	public void setSp_time(Timestamp sp_time) {
		this.sp_time = sp_time;
	}
	public String getSp_status() {
		return sp_status;
	}
	public void setSp_status(String sp_status) {
		this.sp_status = sp_status;
	}
	public Timestamp getSp_dlvr() {
		return sp_dlvr;
	}
	public void setSp_dlvr(Timestamp sp_dlvr) {
		this.sp_dlvr = sp_dlvr;
	}
	public Double getTotal_price() {
		return total_price;
	}
	public void setTotal_price(Double total_price) {
		this.total_price = total_price;
	}
	public Integer getPoints_total() {
		return points_total;
	}
	public void setPoints_total(Integer points_total) {
		this.points_total = points_total;
	}
	public String getRm_no() {
		return rm_no;
	}
	public void setRm_no(String rm_no) {
		this.rm_no = rm_no;
	}
	
	
}
