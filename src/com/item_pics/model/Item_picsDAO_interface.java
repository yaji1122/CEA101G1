package com.item_pics.model;

import java.util.List;

public interface Item_picsDAO_interface {
	public void insert(Item_picsVO item_picsVO);

	public void update(Item_picsVO item_picsVO);

	public Item_picsVO findByPrimaryKey(String item_pic_no);

	public List<Item_picsVO> getAll(String item_no);
	
	public void delete(String item_pic_no);
	
}
