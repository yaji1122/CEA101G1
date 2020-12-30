package com.item.model;

import java.sql.Timestamp;

public class ItemVO implements java.io.Serializable {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((item_no == null) ? 0 : item_no.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemVO other = (ItemVO) obj;
		if (item_no == null) {
			if (other.item_no != null)
				return false;
		} else if (!item_no.equals(other.item_no))
			return false;
		return true;
	}
	private String item_no;
	private String item_name;
	private String item_type_no;
	private Double item_price;
	private Timestamp item_renew;
	private String item_status;
	private String item_on_sale;
	private String item_detail;
	private Integer points;
	private Integer quantity;
	
	public ItemVO() {
		
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
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
	public String getItem_type_no() {
		return item_type_no;
	}
	public void setItem_type_no(String item_type_no) {
		this.item_type_no = item_type_no;
	}
	public Double getItem_price() {
		return item_price;
	}
	public void setItem_price(Double item_price) {
		this.item_price = item_price;
	}
	public Timestamp getItem_renew() {
		return item_renew;
	}
	public void setItem_renew(Timestamp item_renew) {
		this.item_renew = item_renew;
	}
	public String getItem_status() {
		return item_status;
	}
	public void setItem_status(String item_status) {
		this.item_status = item_status;
	}
	public String getItem_on_sale() {
		return item_on_sale;
	}
	public void setItem_on_sale(String item_on_sale) {
		this.item_on_sale = item_on_sale;
	}
	public String getItem_detail() {
		return item_detail;
	}
	public void setItem_detail(String item_detail) {
		this.item_detail = item_detail;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	
}
