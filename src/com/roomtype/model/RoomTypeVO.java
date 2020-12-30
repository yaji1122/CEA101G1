package com.roomtype.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class RoomTypeVO implements Serializable {
	private String rm_type;
	private String type_name;
	private String type_eng_name;
	private Integer rm_qty;
	private Integer rm_price;
	private Integer rm_capacity;
	private String rm_info_title;
	private String rm_info;
	
	public RoomTypeVO() {}
	
	public RoomTypeVO(String rm_type, String type_name, String type_eng_name, Integer rm_qty, Integer rm_price, Integer rm_capacity, String rm_info_title, String rm_info) {
		super();
		this.rm_type = rm_type;
		this.type_name = type_name;
		this.type_eng_name = type_eng_name;
		this.rm_qty = rm_qty;
		this.rm_price = rm_price;
		this.rm_capacity = rm_capacity;
		this.rm_info_title = rm_info_title;
		this.rm_info = rm_info;
	}
	
	public String getRm_type() {
		return rm_type;
	}
	public void setRm_type(String rm_type) {
		this.rm_type = rm_type;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public Integer getRm_qty() {
		return rm_qty;
	}
	public void setRm_qty(Integer rm_qty) {
		this.rm_qty = rm_qty;
	}
	public Integer getRm_price() {
		return rm_price;
	}
	public void setRm_price(Integer rm_price) {
		this.rm_price = rm_price;
	}
	public Integer getRm_capacity() {
		return rm_capacity;
	}
	public void setRm_capacity(Integer rm_capacity) {
		this.rm_capacity = rm_capacity;
	}
	public String getRm_info() {
		return rm_info;
	}
	public void setRm_info(String rm_info) {
		this.rm_info = rm_info;
	}
	public String getType_eng_name() {
		return type_eng_name;
	}
	public void setType_eng_name(String type_eng_name) {
		this.type_eng_name = type_eng_name;
	}
	public String getRm_info_title() {
		return rm_info_title;
	}
	public void setRm_info_title(String rm_info_title) {
		this.rm_info_title = rm_info_title;
	}
	
}
