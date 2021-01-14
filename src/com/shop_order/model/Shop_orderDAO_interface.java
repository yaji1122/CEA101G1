package com.shop_order.model;

import java.util.List;

import com.shop_order_detail.model.Shop_order_detailVO;

public interface Shop_orderDAO_interface {

	public void insert(Shop_orderVO shop_orderVO);

	public void update(Shop_orderVO shop_orderVO);

	public Shop_orderVO findByPrimaryKey(String sp_odno);

	public List<Shop_orderVO> getAll();

	public List<Shop_orderVO> getSp_odnoByMb_id(String mb_id);

	public void insertWithShop_order_details(Shop_orderVO shop_orderVO , List<Shop_order_detailVO> list);

	void updateStatus(Shop_orderVO shop_orderVO);
}
