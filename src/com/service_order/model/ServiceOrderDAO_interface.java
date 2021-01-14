package com.service_order.model;

import java.util.List;

public interface ServiceOrderDAO_interface {
	
	public void insert(ServiceOrderVO serviceOrderVO);

	public void update(ServiceOrderVO serviceOrderVO);
	public void cancelOd_status(ServiceOrderVO serviceOrderVO);

	public void delete(String serv_odno);

	public ServiceOrderVO findByPrimaryKey(String serv_odno);

	public List<ServiceOrderVO> getAll();
	
	public List<ServiceOrderVO> getAllByMbId(String mb_id);

	List<ServiceOrderVO> getAllByBkNo(String bk_no);

}
