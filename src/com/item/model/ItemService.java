package com.item.model;

import java.util.List;

public class ItemService {
 
	private ItemDAO_interface dao;
	
	public ItemService() {
		dao = new ItemDAO();
	}
	
	public ItemVO addItem(
			String item_name, String item_type_no, Double item_price, String item_status, String item_on_sale, String item_detail, Integer points) {
		
		ItemVO itemVO = new ItemVO();
		itemVO.setItem_name(item_name);
		itemVO.setItem_type_no(item_type_no);
		itemVO.setItem_price(item_price);
		itemVO.setItem_status(item_status);
		itemVO.setItem_on_sale(item_on_sale);
		itemVO.setItem_detail(item_detail);
		itemVO.setPoints(points);
		dao.insert(itemVO);
		
		return itemVO;
	}
	
	public ItemVO updateItem(String item_name, String item_type_no, Double item_price,
			String item_status, String item_on_sale, String item_detail, Integer points, String item_no) {

		ItemVO itemVO = new ItemVO();

		itemVO.setItem_name(item_name);
		itemVO.setItem_type_no(item_type_no);
		itemVO.setItem_price(item_price);
		itemVO.setItem_status(item_status);
		itemVO.setItem_on_sale(item_on_sale);
		itemVO.setItem_detail(item_detail);
		itemVO.setPoints(points);
		itemVO.setItem_no(item_no);
		dao.update(itemVO);

		return itemVO;
	}

	public ItemVO getOneItem(String item_no) {
		return dao.findByPrimaryKey(item_no);
	}
	public List<ItemVO> getAllItem(){
		return dao.getAll();
	}
	
	public List<ItemVO> getAllByItem_type_no(String item_type_no){
		return dao.getAllByItem_type_no(item_type_no);
	}
	
	public List<ItemVO> getAllItemBySt(){
		return dao.getAllByStatus();
	}
	
	public List<ItemVO> getAllByItem_type_noBySt(String item_type_no){
		return dao.getAllByItem_type_noByStatus(item_type_no);
	}
}
