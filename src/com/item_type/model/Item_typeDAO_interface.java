package com.item_type.model;

import java.util.List;

public interface Item_typeDAO_interface {

	public void insert(Item_typeVO item_typeVO);

	public void update(Item_typeVO item_typeVO);

	public Item_typeVO findByPrimaryKey(String item_type_no);

	public List<Item_typeVO> getAll();

}
