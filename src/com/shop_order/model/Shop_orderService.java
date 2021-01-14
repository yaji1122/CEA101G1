package com.shop_order.model;

import java.sql.Timestamp;
import java.util.List;

import com.shop_order_detail.model.Shop_order_detailVO;

public class Shop_orderService {
	
	private Shop_orderDAO_interface dao;
	
	public Shop_orderService() {
		dao = new Shop_orderDAO();
	}
	
	public Shop_orderVO addShop_order(
			String mb_id, String sp_status, Double total_price, Integer points_total, String rm_no) {
		Shop_orderVO shop_orderVO = new Shop_orderVO();
		shop_orderVO.setMb_id(mb_id);
		shop_orderVO.setSp_status(sp_status);
		shop_orderVO.setTotal_price(total_price);
		shop_orderVO.setPoints_total(points_total);
		shop_orderVO.setRm_no(rm_no);
		dao.insert(shop_orderVO);
		
		return shop_orderVO;
	}
	
	public Shop_orderVO updateShop_order(
			String sp_status, Timestamp sp_dlvr, String sp_odno) {
		Shop_orderVO shop_orderVO = new Shop_orderVO();
		shop_orderVO.setSp_status(sp_status);
		shop_orderVO.setSp_dlvr(sp_dlvr);
		shop_orderVO.setSp_odno(sp_odno);
		dao.update(shop_orderVO);
		
		return shop_orderVO;
		
	}
	
	public Shop_orderVO updateStatus(
			String sp_status, String sp_odno) {
		Shop_orderVO shop_orderVO = new Shop_orderVO();
		shop_orderVO.setSp_status(sp_status);
		shop_orderVO.setSp_odno(sp_odno);
		dao.updateStatus(shop_orderVO);
		
		return shop_orderVO;
		
	}
	
	public Shop_orderVO getOneShop_order(String sp_odno) {
		return dao.findByPrimaryKey(sp_odno);
	}
	
	public List<Shop_orderVO> getAllShop_order(){
		return dao.getAll();
	}
	
	public List<Shop_orderVO> getSp_odnoByMb_id(String mb_id){
		return dao.getSp_odnoByMb_id(mb_id);
	}

	synchronized public void addShop_orderWithShop_order_detail(
			Shop_orderVO shop_orderVO ,
			List<Shop_order_detailVO> list) {
		dao.insertWithShop_order_details(shop_orderVO, list);
	}	
}
