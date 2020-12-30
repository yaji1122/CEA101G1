package com.roomrsv.model;

import java.io.Serializable;
import java.time.LocalDate;

public class RoomRsvVO implements Serializable {
	private LocalDate rsv_date;
	private String rm_type;
	private Integer rm_left;
	
	public RoomRsvVO() {};
	
	public RoomRsvVO(LocalDate rsv_date, String rm_type, Integer rm_left) {
		super();
		this.rsv_date = rsv_date;
		this.rm_type = rm_type;
		this.rm_left = rm_left;
	}

	public LocalDate getRsv_date() {
		return rsv_date;
	}

	public void setRsv_date(LocalDate rsv_date) {
		this.rsv_date = rsv_date;
	}

	public String getRm_type() {
		return rm_type;
	}

	public void setRm_type(String rm_type) {
		this.rm_type = rm_type;
	}

	public Integer getRm_left() {
		return rm_left;
	}

	public void setRm_left(Integer rm_left) {
		this.rm_left = rm_left;
	}
	
	
	
}
