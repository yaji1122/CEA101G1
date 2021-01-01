package com.shop_order_detail.model;

import java.sql.Connection;
import java.util.List;
import java.util.Set;

import com.shop_order.model.Shop_orderVO;

public interface Shop_order_detailDAO_interface {

	public void insert(Shop_order_detailVO shop_order_detailVO);

	public void update(Shop_order_detailVO shop_order_detailVO);

	public Shop_order_detailVO findByPrimaryKey(String sp_odno, String item_no);

	public List<Shop_order_detailVO> getAll();

	public Set<Shop_order_detailVO> getShop_order_detailBySp_odno_inSet(String sp_odno);

	public List<Shop_order_detailVO> getShop_order_detailBySp_odno(String sp_odno);

	
}
