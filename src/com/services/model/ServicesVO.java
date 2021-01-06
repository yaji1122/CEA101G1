package com.services.model;

public class ServicesVO implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String serv_no;
	private String serv_type_no;
	private String serv_status;
	private Integer serv_price;
	private byte[] serv_pic;
	private String serv_info;
	private String serv_name;
	private Integer serv_dura;
	private Integer serv_ppl;

	public String getServ_no() {
		return serv_no;
	}

	public void setServ_no(String serv_no) {
		this.serv_no = serv_no;
	}

	public String getServ_type_no() {
		return serv_type_no;
	}

	public void setServ_type_no(String serv_type_no) {
		this.serv_type_no = serv_type_no;
	}

	public String getServ_status() {
		return serv_status;
	}

	public void setServ_status(String serv_status) {
		this.serv_status = serv_status;
	}

	public Integer getServ_price() {
		return serv_price;
	}

	public void setServ_price(Integer serv_price) {
		this.serv_price = serv_price;
	}

	public byte[] getServ_pic() {
		return serv_pic;
	}

	public void setServ_pic(byte[] serv_pic) {
		this.serv_pic = serv_pic;
	}

	public String getServ_info() {
		return serv_info;
	}

	public void setServ_info(String serv_info) {
		this.serv_info = serv_info;
	}

	public String getServ_name() {
		return serv_name;
	}

	public void setServ_name(String serv_name) {
		this.serv_name = serv_name;
	}

	public Integer getServ_dura() {
		return serv_dura;
	}

	public void setServ_dura(Integer serv_dura) {
		this.serv_dura = serv_dura;
	}

	public Integer getServ_ppl() {
		return serv_ppl;
	}

	public void setServ_ppl(Integer serv_ppl) {
		this.serv_ppl = serv_ppl;
	}

}
