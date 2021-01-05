package com.emp.model;

import java.sql.Blob;
import java.sql.Date;
import java.sql.Timestamp;

public class EmpVO implements java.io.Serializable{
	private static final long serialVersionUID = 1L;	
	private String emp_id;
	private String emp_name;
	private String emp_pwd;
	private byte[] emp_pic;
	private String emp_phone;
	private String emp_email;
	private String emp_city;
	private String emp_town;
	private String emp_address;
	private String emp_status;
	private java.sql.Date emp_date;
	private String title_no;
	private String dept_no;
	
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getEmp_pwd() {
		return emp_pwd;
	}
	public void setEmp_pwd(String emp_pwd) {
		this.emp_pwd = emp_pwd;
	}
	public byte[] getEmp_pic() {
		return emp_pic;
	}
	public void setEmp_pic(byte[] emp_pic) {
		this.emp_pic = emp_pic;
	}
	public String getEmp_phone() {
		return emp_phone;
	}
	public void setEmp_phone(String emp_phone) {
		this.emp_phone = emp_phone;
	}
	public String getEmp_email() {
		return emp_email;
	}
	public void setEmp_email(String emp_email) {
		this.emp_email = emp_email;
	}
	public String getEmp_city() {
		return emp_city;
	}
	public void setEmp_city(String emp_city) {
		this.emp_city = emp_city;
	}
	public String getEmp_town() {
		return emp_town;
	}
	public void setEmp_town(String emp_town) {
		this.emp_town = emp_town;
	}
	public String getEmp_address() {
		return emp_address;
	}
	public void setEmp_address(String emp_address) {
		this.emp_address = emp_address;
	}
	public String getEmp_status() {
		return emp_status;
	}
	public void setEmp_status(String emp_status) {
		this.emp_status = emp_status;
	}
	public Date getEmp_date() {
		return emp_date;
	}
	public void setEmp_date(Date emp_date) {
		this.emp_date = emp_date;
	}
	public String getTitle_no() {
		return title_no;
	}
	public void setTitle_no(String title_no) {
		this.title_no = title_no;
	}
	public String getDept_no() {
		return dept_no;
	}
	public void setDept_no(String dept_no) {
		this.dept_no = dept_no;
	}
	
	
	}