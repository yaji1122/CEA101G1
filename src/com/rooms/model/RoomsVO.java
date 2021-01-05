package com.rooms.model;

import java.io.Serializable;

public class RoomsVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String rm_no;
	private String rm_type;
	private String rm_status;
	private String mb_id;
	private String bk_no;
	
	public RoomsVO() {};
	
	public String getRm_no() {
		return rm_no;
	}
	public void setRm_no(String rm_no) {
		this.rm_no = rm_no;
	}
	public String getRm_type() {
		return rm_type;
	}
	public void setRm_type(String rm_type) {
		this.rm_type = rm_type;
	}
	public String getRm_status() {
		return rm_status;
	}
	public void setRm_status(String rm_status) {
		this.rm_status = rm_status;
	}
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}

	public String getBk_no() {
		return bk_no;
	}

	public void setBk_no(String bk_no) {
		this.bk_no = bk_no;
	}
	
}
