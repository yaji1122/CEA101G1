package com.meal.model;

import java.io.Serializable;

public class MealVO implements Serializable{
	private static final long serialVersionUID = 1L;	
	private String meal_no;
	private String meal_type_no;
	private String meal_name;
	private Integer price;
	private byte[] meal_pic;
	private String meal_info;	
	private Integer making_time;
	private String meal_status;
	
	public String getMeal_no() {
		return meal_no;
	}
	public void setMeal_no(String meal_no) {
		this.meal_no = meal_no;
	}
	public String getMeal_type_no() {
		return meal_type_no;
	}
	public void setMeal_type_no(String meal_type_no) {
		this.meal_type_no = meal_type_no;
	}
	public String getMeal_name() {
		return meal_name;
	}
	public void setMeal_name(String meal_name) {
		this.meal_name = meal_name;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public byte[] getMeal_pic() {
		return meal_pic;
	}
	public void setMeal_pic(byte[] meal_pic) {
		this.meal_pic = meal_pic;
	}
	public String getMeal_info() {
		return meal_info;
	}
	public void setMeal_info(String meal_info) {
		this.meal_info = meal_info;
	}	
	public Integer getMaking_time() {
		return making_time;
	}
	public void setMaking_time(Integer making_time) {
		this.making_time = making_time;
	}
	public String getMeal_status() {
		return meal_status;
	}
	public void setMeal_status(String meal_status) {
		this.meal_status = meal_status;
	}

}