package com.title.model;

import java.util.*;

public interface TitleDAO_interface {

	public void insert(TitleVO titleVO);
	public void update(TitleVO titleVO);
//	public void delete(String pay_no);
	public TitleVO findByPrimaryKey(String title_no);
	public List<TitleVO> getAll();
}
