package com.pickup.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class PickupVO implements Serializable {
	private String pkup_no;
	private String bk_no;
	private String chop_no;
	private Timestamp pkup_time;
	private Timestamp arrive_datetime;
	private String pkup_status;
	
	public PickupVO() {};
	
	public PickupVO(String pkup_no, String bk_no, String chop_no, Timestamp arrive_datetime, String pkup_status) {
		super();
		this.pkup_no = pkup_no;
		this.chop_no = chop_no;
		this.bk_no = bk_no;
		this.arrive_datetime = arrive_datetime;
		this.pkup_status = pkup_status;
	}
	
	public PickupVO(Timestamp pkup_time) {
		this.pkup_time = pkup_time;
	};

	public String getPkup_no() {
		return pkup_no;
	}

	public void setPkup_no(String pkup_no) {
		this.pkup_no = pkup_no;
	}

	public String getChop_no() {
		return chop_no;
	}

	public void setChop_no(String chop_no) {
		this.chop_no = chop_no;
	}

	public String getBk_no() {
		return bk_no;
	}

	public void setBk_no(String bk_no) {
		this.bk_no = bk_no;
	}

	public Timestamp getPkup_time() {
		return pkup_time;
	}

	public void setPkup_time(Timestamp pkup_time) {
		this.pkup_time = pkup_time;
	}

	public Timestamp getArrive_datetime() {
		return arrive_datetime;
	}

	public void setArrive_datetime(Timestamp arrive_datetime) {
		this.arrive_datetime = arrive_datetime;
	}

	public String getPkup_status() {
		return pkup_status;
	}

	public void setPkup_status(String pkup_status) {
		this.pkup_status = pkup_status;
	}
}
