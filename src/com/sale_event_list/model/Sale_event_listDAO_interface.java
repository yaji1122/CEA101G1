package com.sale_event_list.model;

import java.util.List;

public interface Sale_event_listDAO_interface {

	public void insert(Sale_event_listVO sale_event_listVO);

	public void update(Sale_event_listVO sale_event_listVO);

	public Sale_event_listVO findByPrimaryKey(String sale_no, String item_no);

	public List<Sale_event_listVO> getAll();

}
