package com.shop_order.model;

import java.util.List;

public interface Shop_orderDAO_interface {

	public void insert(Shop_orderVO shop_orderVO);

	public void update(Shop_orderVO shop_orderVO);

	public Shop_orderVO findByPrimaryKey(String sp_odno);

	public List<Shop_orderVO> getAll();

	public List<Shop_orderVO> getSp_odnoByMb_id(String mb_id);

}
