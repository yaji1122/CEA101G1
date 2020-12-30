package com.shop_order_detail.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import com.shop_order.model.Shop_orderDAO;
import com.shop_order.model.Shop_orderDAO_interface;
import com.shop_order.model.Shop_orderVO;

public class Shop_order_detailService {
	private Shop_order_detailDAO_interface dao;
	
	public Shop_order_detailService() {
		dao = new Shop_order_detailDAO();
	}
	
	public Shop_order_detailVO addShop_order_detail(
			String sp_odno, String item_no, Integer qty, Double sale_discount, Double item_price, Integer points) {
		Shop_order_detailVO shop_order_detailVO = new Shop_order_detailVO();
		shop_order_detailVO.setSp_odno(sp_odno);
		shop_order_detailVO.setItem_price(item_price);
		shop_order_detailVO.setItem_no(item_no);
		shop_order_detailVO.setQty(qty);
		shop_order_detailVO.setSale_discount(sale_discount);
		shop_order_detailVO.setPoints(points);
		dao.insert(shop_order_detailVO);
		
		return shop_order_detailVO;
	}
	
	public Shop_order_detailVO updateShop_order_detail(
			Integer qty, String sp_odno, String item_no) {
		Shop_order_detailVO shop_order_detailVO = new Shop_order_detailVO();
		shop_order_detailVO.setQty(qty);
		shop_order_detailVO.setSp_odno(sp_odno);
		shop_order_detailVO.setItem_no(item_no);
		dao.update(shop_order_detailVO);
		
		return shop_order_detailVO;
		
	}
	
	public Shop_order_detailVO getOneShop_order_detail(String sp_odno, String item_no) {
		return dao.findByPrimaryKey(sp_odno, item_no);
	}
	
	public List<Shop_order_detailVO> getAllShop_order_detail(){
		return dao.getAll();
	}
	
	public Set<Shop_order_detailVO> getShop_order_detailBySp_odno_inSet(String sp_odno){
		return dao.getShop_order_detailBySp_odno_inSet(sp_odno);
	}
	
	public List<Shop_order_detailVO> getShop_order_detailBySp_odno(String sp_odno){
		return dao.getShop_order_detailBySp_odno(sp_odno);
	}
}
