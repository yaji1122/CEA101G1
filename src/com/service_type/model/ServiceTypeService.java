package com.service_type.model;

import java.util.List;

import com.services.model.ServicesVO;

public class ServiceTypeService {
	
	private ServiceTypeDAO_interface dao;
	
	public ServiceTypeService() {
		
		dao = new ServiceTypeDAO();
	}
	
	public ServiceTypeVO addServiceType(String serv_type_name) {
		ServiceTypeVO serviceTypeVO = new ServiceTypeVO();
		
		serviceTypeVO.setServ_type_name(serv_type_name);
		
		dao.insert(serviceTypeVO);
		
		return serviceTypeVO;
	}
	
	public ServiceTypeVO updateServiceType(String serv_type_no, String serv_type_name) {
		ServiceTypeVO serviceTypeVO = new ServiceTypeVO();
		
		serviceTypeVO.setServ_type_no(serv_type_no);
		serviceTypeVO.setServ_type_name(serv_type_name);
		
		dao.update(serviceTypeVO);
		
		return serviceTypeVO;
	}
	
	public void deleteServiceType(String serv_type_no) {
		dao.delete(serv_type_no);
	}

	public ServiceTypeVO getOneServiceType(String serv_type_no) {
		return dao.findByPrimaryKey(serv_type_no);
	}

	public List<ServiceTypeVO> getAll() {
		return dao.getAll();
	}

}
