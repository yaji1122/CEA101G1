package com.item.model;

import java.util.List;

import com.shop_order_detail.model.Shop_order_detailVO;

public interface ItemDAO_interface {
	public void insert(ItemVO itemVO);

	public void update(ItemVO itemVO); 
	
	public List<ItemVO> getAll();

	public ItemVO findByPrimaryKey(String item_no);
		
	public List<ItemVO> getAllByItem_type_no(String item_no);
	
	public List<ItemVO> getAllByStatus();
	
	public List<ItemVO> getAllByItem_type_noByStatus(String item_type_no);
	
	
}
