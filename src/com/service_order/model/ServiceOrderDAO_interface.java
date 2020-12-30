package com.service_order.model;

import java.util.List;

public interface ServiceOrderDAO_interface {
	
	public void insert(ServiceOrderVO serviceOrderVO);

	public void update(ServiceOrderVO serviceOrderVO);

	public void delete(String serv_odno);

	public ServiceOrderVO findByPrimaryKey(String serv_odno);

	public List<ServiceOrderVO> getAll();
	// 萬用複合查詢(傳入參數型態Map)(回傳 List)
    // public List<EmpVO> getAll(Map<String, String[]> map); 

}
