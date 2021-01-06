package com.choppers.model;

import java.io.Serializable;

public class ChoppersVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String chop_no;
	private byte[] chop_pic;
	private String chop_name;
	private String chop_status;
	private Integer chop_price;
	private String chop_info;
	
	public ChoppersVO() {};

	public ChoppersVO(String chop_no, byte[] chop_pic, String chop_name, String chop_status, Integer chop_price, String chop_info) {
		super();
		this.chop_no = chop_no;
		this.chop_pic = chop_pic;
		this.chop_name = chop_name;
		this.chop_status = chop_status;
		this.chop_price = chop_price;
		this.chop_info = chop_info;
	}

	public String getChop_no() {
		return chop_no;
	}

	public void setChop_no(String chop_no) {
		this.chop_no = chop_no;
	}

	public byte[] getChop_pic() {
		return chop_pic;
	}

	public void setChop_pic(byte[] chop_pic) {
		this.chop_pic = chop_pic;
	}

	public String getChop_name() {
		return chop_name;
	}

	public void setChop_name(String chop_name) {
		this.chop_name = chop_name;
	}
	public String getChop_status() {
		return chop_status;
	}

	public void setChop_status(String chop_status) {
		this.chop_status = chop_status;
	}
	
	public Integer getChop_price() {
		return chop_price;
	}

	public void setChop_price(Integer chop_price) {
		this.chop_price = chop_price;
	}

	public String getChop_info() {
		return chop_info;
	}

	public void setChop_info(String chop_info) {
		this.chop_info = chop_info;
	}
	
}
