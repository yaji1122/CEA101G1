package com.members.model;

import java.io.Serializable;
import java.sql.Date;

public class MembersVO implements Serializable {
	private String mb_id;
	private String mb_name;
	private String mb_pwd;
	private String mb_salt;
	private Date mb_bd;
	private byte[] mb_pic;
	private String mb_phone;
	private String mb_email;
	private String mb_city;
	private String mb_town;
	private String mb_address;
	private String mb_status;
	private Date create_date;
	private Integer mb_point;
	
	public MembersVO() {};
	
	public MembersVO(String mb_id, String mb_name, String mb_pwd, String mb_salt, Date mb_bd, byte[] mb_pic,
			String mb_phone, String mb_email, String mb_city, String mb_town, String mb_address, Integer mb_point) {
		super();
		this.mb_id = mb_id;
		this.mb_name = mb_name;
		this.mb_pwd = mb_pwd;
		this.mb_pwd = mb_salt;
		this.mb_bd = mb_bd;
		this.mb_pic = mb_pic;
		this.mb_phone = mb_phone;
		this.mb_email = mb_email;
		this.mb_city = mb_city;
		this.mb_town = mb_town;
		this.mb_address = mb_address;
		this.mb_point = mb_point;
	}
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	public String getMb_name() {
		return mb_name;
	}
	public void setMb_name(String mb_name) {
		this.mb_name = mb_name;
	}
	public String getMb_pwd() {
		return mb_pwd;
	}
	public void setMb_pwd(String mb_pwd) {
		this.mb_pwd = mb_pwd;
	}
	public Date getMb_bd() {
		return mb_bd;
	}
	public void setMb_bd(Date mb_bd) {
		this.mb_bd = mb_bd;
	}
	public byte[] getMb_pic() {
		return mb_pic;
	}
	public void setMb_pic(byte[] mb_pic) {
		this.mb_pic = mb_pic;
	}
	public String getMb_phone() {
		return mb_phone;
	}
	public void setMb_phone(String mb_phone) {
		this.mb_phone = mb_phone;
	}
	public String getMb_email() {
		return mb_email;
	}
	public void setMb_email(String mb_email) {
		this.mb_email = mb_email;
	}
	public String getMb_city() {
		return mb_city;
	}
	public void setMb_city(String mb_city) {
		this.mb_city = mb_city;
	}
	public String getMb_town() {
		return mb_town;
	}
	public void setMb_town(String mb_town) {
		this.mb_town = mb_town;
	}
	public String getMb_address() {
		return mb_address;
	}
	public void setMb_address(String mb_address) {
		this.mb_address = mb_address;
	}
	public String getMb_status() {
		return mb_status;
	}
	public void setMb_status(String mb_status) {
		this.mb_status = mb_status;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public Integer getMb_point() {
		return mb_point;
	}
	public void setMb_point(Integer mb_point) {
		this.mb_point = mb_point;
	}
	public String getMb_salt() {
		return mb_salt;
	}
	public void setMb_salt(String mb_salt) {
		this.mb_salt = mb_salt;
	}
	

}
