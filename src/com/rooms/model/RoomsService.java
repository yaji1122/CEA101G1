package com.rooms.model;

import java.util.List;

public class RoomsService {
	private RoomsDAO_interface dao;
	
	public RoomsService() {
		dao = new RoomsDAO();
	}
	
	public RoomsVO addRoom(String rm_type, String rm_no) {
		RoomsVO rmvo = new RoomsVO();
		rmvo.setRm_type(rm_type);
		rmvo.setRm_no(rm_no);
		String rmno = dao.insert(rmvo);
		rmvo.setRm_no(rmno);
		return rmvo;
	}
	
	public RoomsVO addRoomManually(String rm_type, String rm_no) {
		RoomsVO rmvo = new RoomsVO();
		rmvo.setRm_type(rm_type);
		rmvo.setRm_no(rm_no);
		dao.insert(rmvo);
		return rmvo;
	}
	
	public RoomsVO updateRoomStatus(String rm_no, String rm_status) {
		RoomsVO rmvo = new RoomsVO();
		rmvo.setRm_no(rm_no);
		rmvo.setRm_status(rm_status);
		dao.update(rmvo);
		return rmvo;
	}
	
	public RoomsVO updateCheckIn(String rm_no, String mb_id) {
		RoomsVO rmvo = new RoomsVO();
		rmvo.setRm_no(rm_no);
		rmvo.setMb_id(mb_id);
		dao.update_checkin(rmvo);
		return rmvo;
	}
	
	public void delete(String rm_no) {
		dao.delete(rm_no);
	}
	
	public List<RoomsVO> getAllByStatus(String rmstatus) {
		return dao.getAllByStatus(rmstatus);
	}
	
	public List<RoomsVO> getAllByRmType(String rmtype) {
		return dao.getAllByRmType(rmtype);
	}
	public List<RoomsVO> getAll() {
		return dao.getAll();
	}
}
