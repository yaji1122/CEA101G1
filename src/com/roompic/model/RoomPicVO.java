package com.roompic.model;


import java.io.Serializable;

import javax.servlet.http.Part;

public class RoomPicVO implements Serializable {
	private String rm_pic_no;
	private String rm_type;
	private byte[] rm_pic;
	private Part part;

	public RoomPicVO() {
	};

	public RoomPicVO(String rm_pic_no, String rm_type, byte[] rm_pic) {
		super();
		this.rm_pic_no = rm_pic_no;
		this.rm_type = rm_type;
		this.rm_pic = rm_pic;
	}

	public RoomPicVO(String rm_pic_no, String rm_type, Part part) {
		super();
		this.rm_pic_no = rm_pic_no;
		this.rm_type = rm_type;
		this.setPart(part);
	}

	public RoomPicVO(String rm_pic_no, String rm_type) {
		super();
		this.rm_pic_no = rm_pic_no;
		this.rm_type = rm_type;
	}

	public RoomPicVO(String rm_pic_no) {
		super();
		this.rm_pic_no = rm_pic_no;
	}

	public String getRm_pic_no() {
		return rm_pic_no;
	}

	public void setRm_pic_no(String rm_pic_no) {
		this.rm_pic_no = rm_pic_no;
	}

	public String getRm_type() {
		return rm_type;
	}

	public void setRm_type(String rm_type) {
		this.rm_type = rm_type;
	}

	public byte[] getRm_pic() {
		return rm_pic;
	}

	public void setRm_pic(byte[] rm_pic) {
		this.rm_pic = rm_pic;
	}
	public Part getPart() {
		return part;
	}

	public void setPart(Part part) {
		this.part = part;
	}

	

}
