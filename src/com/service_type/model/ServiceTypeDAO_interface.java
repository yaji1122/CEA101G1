package com.service_type.model;

import java.util.List;

public interface ServiceTypeDAO_interface {
	
	public void insert(ServiceTypeVO serviceTypeVO);

	public void update(ServiceTypeVO serviceTypeVO);

	public void delete(String serv_type_no);

	public ServiceTypeVO findByPrimaryKey(String serv_type_no);

	public List<ServiceTypeVO> getAll();
	// 萬用複合查詢(傳入參數型態Map)(回傳 List)
    // public List<EmpVO> getAll(Map<String, String[]> map); 

}
