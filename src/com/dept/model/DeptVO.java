package com.dept.model;

import java.io.Serializable;

public class DeptVO implements Serializable {

	private String dept_no;
	private String dept;
	
	
	public String getDept_no() {
		return dept_no;
	}
	public void setDept_no(String dept_no) {
		this.dept_no = dept_no;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	
	
}
