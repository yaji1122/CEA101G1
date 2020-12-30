package com.item_type.model;

import java.util.List;
import java.util.Set;

import com.item.model.ItemVO;

public class Item_typeService {
	private Item_typeDAO_interface dao;

	public Item_typeService() {
		dao = new Item_typeDAO();
	}

	public List<Item_typeVO> getAllItem_type() {
		return dao.getAll();
	}

	public Item_typeVO getOneItem_type(String item_type_no) {
		return dao.findByPrimaryKey(item_type_no);
	}
	
	public Item_typeVO addItem_type(String item_type_no, String type_name) {
		Item_typeVO item_typeVO = new Item_typeVO();
		item_typeVO.setItem_type_no(item_type_no);
		item_typeVO.setType_name(type_name);
		dao.insert(item_typeVO);
		return item_typeVO;
	}
	
	public Item_typeVO updateItem_type(String type_name,String item_type_no) {
		Item_typeVO item_typeVO = new Item_typeVO();
		item_typeVO.setItem_type_no(item_type_no);
		item_typeVO.setType_name(type_name);
		dao.update(item_typeVO);
		return item_typeVO;
	}
//	public Set<ItemVO> getItemByItem_type_no(String item_type_no) {
//		return dao.getItemByItem_type_no(item_type_no);
//	}

}
