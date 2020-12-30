package com.service_order.model;

import java.sql.Timestamp;
import java.util.List;

import com.services.model.ServicesDAO_interface;
import com.services.model.ServicesJDBCDAO;
import com.services.model.ServicesVO;

public class ServiceOrderService {
	
	ServiceOrderDAO_interface dao;

	public ServiceOrderService() {
		dao = new ServiceOrderDAO();
	}
	
	public ServiceOrderVO addServiceOrder(/*String serv_odno,*/ String mb_id, /*Timestamp od_time,*/ String od_status, String rm_no, String serv_no, Timestamp serv_time, Integer serv_count, Integer total_price) {
		
		ServiceOrderVO serviceOrderVO = new ServiceOrderVO();
		
//		serviceOrderVO.setServ_odno(serv_odno);
		serviceOrderVO.setMb_id(mb_id);
//		serviceOrderVO.setOd_time(od_time);
		serviceOrderVO.setOd_status(od_status);
		serviceOrderVO.setRm_no(rm_no);
		serviceOrderVO.setServ_no(serv_no);
		serviceOrderVO.setServ_time(serv_time);
		serviceOrderVO.setServ_count(serv_count);
		serviceOrderVO.setTotal_price(total_price);
		
		dao.insert(serviceOrderVO);
		
		return serviceOrderVO;
		
	}
	
	public ServiceOrderVO updateServiceOrder(String serv_odno, String mb_id, /*Timestamp od_time,*/ String od_status, String rm_no, String serv_no, Timestamp serv_time, Integer serv_count, Integer total_price) {
		
		ServiceOrderVO serviceOrderVO = new ServiceOrderVO();
		
		serviceOrderVO.setServ_odno(serv_odno);
		serviceOrderVO.setMb_id(mb_id);
//		serviceOrderVO.setOd_time(od_time);
		serviceOrderVO.setOd_status(od_status);
		serviceOrderVO.setRm_no(rm_no);
		serviceOrderVO.setServ_no(serv_no);
		serviceOrderVO.setServ_time(serv_time);
		serviceOrderVO.setServ_count(serv_count);
		serviceOrderVO.setTotal_price(total_price);
		
		dao.update(serviceOrderVO);
		
		return serviceOrderVO;
		
	}
	
	public void deleteServiceOrder(String serv_odno) {
		dao.delete(serv_odno);
	}
	
	public ServiceOrderVO getOneServiceOrder(String serv_odno) {
		return dao.findByPrimaryKey(serv_odno);
	}

	public List<ServiceOrderVO> getAll() {
		return dao.getAll();
	}

}
