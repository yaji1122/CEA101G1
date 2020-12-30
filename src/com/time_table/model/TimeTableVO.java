package com.time_table.model;

public class TimeTableVO implements java.io.Serializable {
	
	private Integer serv_period;
	private String serv_no;
	private Integer max_serv_ppl;

	public Integer getServ_period() {
		return serv_period;
	}

	public void setServ_period(Integer serv_period) {
		this.serv_period = serv_period;
	}

	public String getServ_no() {
		return serv_no;
	}

	public void setServ_no(String serv_no) {
		this.serv_no = serv_no;
	}

	public Integer getMax_serv_ppl() {
		return max_serv_ppl;
	}

	public void setMax_serv_ppl(Integer max_serv_ppl) {
		this.max_serv_ppl = max_serv_ppl;
	}

}
