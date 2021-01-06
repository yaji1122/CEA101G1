package com.cart.model;

public class CartItem implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;

	public CartItem() {
		item_no="";
		item_name = "";
		quantity = 0;
		price = 0;
	}


	private String item_no;
	private String item_name;
	private Integer quantity;
	private Integer price;	
	
	public String getItem_no() {
		return item_no;
	}
	public void setItem_no(String item_no) {
		this.item_no = item_no;
	}
	
	public String getItem_name() {
		return item_name;	
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}

}
