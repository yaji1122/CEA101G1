package com.mealorderdetail.model;

import java.io.Serializable;

public class MealOrderDetailVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String meal_odno;
	private String meal_no;
	private Integer price;
	private Integer qty;
	
	public String getMeal_odno() {
		return meal_odno;
	}
	public void setMeal_odno(String meal_odno) {
		this.meal_odno = meal_odno;
	}
	public String getMeal_no() {
		return meal_no;
	}
	public void setMeal_no(String meal_no) {
		this.meal_no = meal_no;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
}
