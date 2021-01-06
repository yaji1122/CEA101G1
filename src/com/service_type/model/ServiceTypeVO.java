package com.service_type.model;

public class ServiceTypeVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String serv_type_no;
	private String serv_type_name;

	public String getServ_type_no() {
		return serv_type_no;
	}

	public void setServ_type_no(String serv_type_no) {
		this.serv_type_no = serv_type_no;
	}

	public String getServ_type_name() {
		return serv_type_name;
	}

	public void setServ_type_name(String serv_type_name) {
		this.serv_type_name = serv_type_name;
	}

}
