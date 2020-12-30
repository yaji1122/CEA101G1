package com.cart.model;

public class CartItem implements java.io.Serializable{
	
	public CartItem() {
		item_name = "";
		quantity = 0;
		price = 0;
	}
	
	private String item_name;
	private Integer quantity;
	private Integer price;
	
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
