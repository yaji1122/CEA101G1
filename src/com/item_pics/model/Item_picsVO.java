package com.item_pics.model;

import javax.servlet.http.Part;

public class Item_picsVO implements java.io.Serializable{
	private String item_pic_no;
	private String item_no;
	private byte[] item_pic;
	private Part part;
	public Item_picsVO() {
		
	}
	public String getItem_pic_no() {
		return item_pic_no;
	}
	public Item_picsVO (String item_pic_no, String item_no, byte[] item_pic) {
		super();
		this.item_pic_no = item_pic_no;
		this.item_no = item_no;
		this.item_pic = item_pic;
	}
	public Item_picsVO (String item_pic_no, String item_no, Part part) {
		super();
		this.item_pic_no = item_pic_no;
		this.item_no = item_no;
		this.part = part;
	}
	public void setItem_pic_no(String item_pic_no) {
		this.item_pic_no = item_pic_no;
	}
	public String getItem_no() {
		return item_no;
	}
	public void setItem_no(String item_no) {
		this.item_no = item_no;
	}
	public byte[] getItem_pic() {
		return item_pic;
	}
	public void setItem_pic(byte[] item_pic) {
		this.item_pic = item_pic;
	}
	public Part getPart() {
		// TODO Auto-generated method stub
		return part;
	}
	
	public void setPart(Part part) {
		this.part = part;
	}
}
