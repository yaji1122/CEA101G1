package com.sale_event_list.model;

public class Sale_event_listVO implements java.io.Serializable {
	private String sale_no;
	private String item_no;
	private Double sale_discount;
	public String getSale_no() {
		return sale_no;
	}
	public void setSale_no(String sale_no) {
		this.sale_no = sale_no;
	}
	public String getItem_no() {
		return item_no;
	}
	public void setItem_no(String item_no) {
		this.item_no = item_no;
	}
	public Double getSale_discount() {
		return sale_discount;
	}
	public void setSale_discount(Double sale_discount) {
		this.sale_discount = sale_discount;
	}
	
	
}
