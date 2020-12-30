package com.shop_order_detail.model;

public class Shop_order_detailVO implements java.io.Serializable {
	private String sp_odno;
	private String item_no;
	private Integer qty;
	private Double sale_discount;
	private Double item_price;
	private Integer points;
	
	public String getSp_odno() {
		return sp_odno;
	}
	public void setSp_odno(String sp_odno) {
		this.sp_odno = sp_odno;
	}
	public String getItem_no() {
		return item_no;
	}
	public void setItem_no(String item_no) {
		this.item_no = item_no;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public Double getSale_discount() {
		return sale_discount;
	}
	public void setSale_discount(Double sale_discount) {
		this.sale_discount = sale_discount;
	}
	public Double getItem_price() {
		return item_price;
	}
	public void setItem_price(Double item_price) {
		this.item_price = item_price;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	
	
	
}
