package com.services_cart.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ServicesItem implements Serializable{
	private static final long serialVersionUID = 1L;

	public ServicesItem() {
		servicesName = "";
		servicesNo = "";
		price = 0;
		quantity = 0;
		locations = "";
		servTime = null;
		unitPrice = 0;
	}
    private String servicesName;
	private String servicesNo;
	private LocalDateTime servTime;
	private Integer price;
	private Integer quantity;
	private String locations;
	private Integer unitPrice;
	
	
	
	public String getServicesName() {
		return servicesName;
	}
	public void setServicesName(String servicesName) {
		this.servicesName = servicesName;
	}
	public String getServicesNo() {
		return servicesNo;
	}
	public void setServicesNo(String servicesNo) {
		this.servicesNo = servicesNo;
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
	public Integer getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Integer unitPrice) {
		this.unitPrice = unitPrice;
	}	
	
	

}
