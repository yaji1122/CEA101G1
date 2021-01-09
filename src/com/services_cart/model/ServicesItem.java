package com.services_cart.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ServicesItem implements Serializable{
	private static final long serialVersionUID = 1L;

	public ServicesItem() {
		servicesName = "";
		price = 0;
		quantity = 0;
		locations = "";
		servTime = null;
	}

	private String servicesName;
	private LocalDateTime servTime;
	private Integer price;
	private Integer quantity;
	private String locations;
	
	public String getServicesName() {
		return servicesName;
	}
	public void setServicesName(String servicesName) {
		this.servicesName = servicesName;
	}
	
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getLocations() {
		return locations;
	}
	public void setLocations(String locations) {
		this.locations = locations;
	}
	public LocalDateTime getServTime() {
		return servTime;
	}
	public void setServTime(LocalDateTime servTime) {
		this.servTime = servTime;
	}	

}
