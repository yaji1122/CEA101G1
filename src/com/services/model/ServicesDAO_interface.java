package com.services.model;

import java.util.List;

public interface ServicesDAO_interface {
	public void insert(ServicesVO servicesVO);

	public void update(ServicesVO servicesVO);

	public void delete(String serv_no);

	public ServicesVO findByPrimaryKey(String serv_no);

	public List<ServicesVO> getAll();
	
	public byte[] getOneServicesPic(String serv_no);
	
	// 萬用複合查詢(傳入參數型態Map)(回傳 List)
    // public List<EmpVO> getAll(Map<String, String[]> map); 

}
