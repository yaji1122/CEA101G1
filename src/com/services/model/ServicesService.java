package com.services.model;

import java.util.List;

public class ServicesService {

	private ServicesDAO_interface dao;

	public ServicesService() {
		dao = new ServicesDAO();
	}

	public ServicesVO addServices(String serv_type_no, String serv_status, Integer serv_price,
			byte[] serv_pic,String serv_info, String serv_name, Integer serv_dura, Integer serv_ppl) {

		ServicesVO servicesVO = new ServicesVO();

		servicesVO.setServ_type_no(serv_type_no);
		servicesVO.setServ_status(serv_status);
		servicesVO.setServ_price(serv_price);
		servicesVO.setServ_pic(serv_pic);
		servicesVO.setServ_info(serv_info);
		servicesVO.setServ_name(serv_name);
		servicesVO.setServ_dura(serv_dura);
		servicesVO.setServ_ppl(serv_ppl);

		dao.insert(servicesVO);

		return servicesVO;
	}

	public ServicesVO updateServices(String serv_no, String serv_type_no, String serv_status, Integer serv_price, byte[] serv_pic, 
			String serv_info, String serv_name, Integer serv_dura, Integer serv_ppl) {
		ServicesVO servicesVO = new ServicesVO();

		servicesVO.setServ_no(serv_no);
		servicesVO.setServ_type_no(serv_type_no);
		servicesVO.setServ_status(serv_status);
		servicesVO.setServ_price(serv_price);
		servicesVO.setServ_pic(serv_pic);
		servicesVO.setServ_info(serv_info);
		servicesVO.setServ_name(serv_name);
		servicesVO.setServ_dura(serv_dura);
		servicesVO.setServ_ppl(serv_ppl);

		dao.update(servicesVO);

		return servicesVO;
	}

	public void deleteServices(String serv_no) {
		dao.delete(serv_no);
	}

	public ServicesVO getOneServices(String serv_no) {
		return dao.findByPrimaryKey(serv_no);
	}

	public List<ServicesVO> getAll() {
		return dao.getAll();
	}
	
	public byte[] getOneByPicNo(String serv_no) {
		return dao.getOneServicesPic(serv_no);
	}
	
	public List<ServicesVO> getAllByServTypeNo(String serv_type_no) {
		return dao.getAllByServTypeNo(serv_type_no);
	}

}
