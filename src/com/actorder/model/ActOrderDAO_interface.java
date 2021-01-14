package com.actorder.model;

import java.util.*;

public interface ActOrderDAO_interface {
	
	public void insert(ActOrderVO actOrderVO);
	public void update(ActOrderVO actOrderVO);
	public void delete(String actOdno);
	public ActOrderVO findByPrimaryKey(String actOdno);
	public List<ActOrderVO> getAll();
	List<ActOrderVO> getAllByBkNo(String bk_no);
	public List<ActOrderVO> getOrderListByMemId(String mb_id);
}
