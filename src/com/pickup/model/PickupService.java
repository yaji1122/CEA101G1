package com.pickup.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class PickupService {
	
	private PickupDAO_interface dao;
	
	public PickupService() {
		dao = new PickupDAO();
	}
	
	public PickupVO addPkup(String bk_no, String chop_no, Timestamp arrive_datetime) {
		PickupVO pkupvo = new PickupVO();
		pkupvo.setBk_no(bk_no);
		pkupvo.setChop_no(chop_no);
		pkupvo.setArrive_datetime(arrive_datetime);
		pkupvo = dao.insert(pkupvo);
		return pkupvo;
	}
	
	public PickupVO updatePkup(String chop_no, Timestamp arrive_datetime, String pkup_no) {
		PickupVO pkupvo = new PickupVO();
		pkupvo.setChop_no(chop_no);
		pkupvo.setArrive_datetime(arrive_datetime);
		pkupvo.setPkup_no(pkup_no);
		dao.update(pkupvo);
		return pkupvo;
	}
	
	public void customerArrive(String pkup_no, String pkup_status) {
		dao.updatePkupTime(pkup_no, pkup_status);
	}
	
	public void delete(String pkup_no) {
		dao.delete(pkup_no);
	}
	
	public List<PickupVO> getAllPkup(){
		return dao.getAll();
	}
	
	public List<PickupVO> getAllPkupByStatus(String pkup_status){
		return dao.getAllByPkupStatus(pkup_status);
	}
	
	public PickupVO getOneByPkupNo(String pkup_no) {
		return dao.getOneByPkupNo(pkup_no);
	}
	
	public List<PickupVO> getAllByBkNo(String bk_no) {
		return dao.getAllByBkNo(bk_no);
	}
}
