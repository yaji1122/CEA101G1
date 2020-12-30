package com.roomrsv.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

public class RoomRsvService {
	private RoomRsvDAO_interface dao;
	
	public RoomRsvService() {
		dao = new RoomRsvDAO();
	}
	
	public void insertRsvDate(LocalDate rsvDate) {
		dao.insert(rsvDate);
	}
	
	public RoomRsvVO updateRmLeft(LocalDate rsvDate, String rmType, Integer rmLeft) {
		RoomRsvVO rsvo = new RoomRsvVO();
		rsvo.setRsv_date(rsvDate);
		rsvo.setRm_type(rmType);
		rsvo.setRm_left(rmLeft);
		return rsvo;
	}
	
	public void deleteRsvDate(LocalDate rsvDate) {
		dao.delete(rsvDate);
	}
	
	public Integer roomCheck(LocalDate rsvDate, Integer stay, String rmType) {
		return dao.roomCheck(rsvDate, stay, rmType);
	}
	
	public RoomRsvVO getOneByDateNRmType(LocalDate rsvDate, String rm_type) {
		return dao.getOneByDateNRmType(rsvDate, rm_type);
	}
	
	public List<RoomRsvVO> getOneByDate(LocalDate rsvDate) {
		return dao.getOneDayByDate(rsvDate);
	}
	
	public List<RoomRsvVO> getAll() {
		return dao.getAll();
	}
	
	public List<RoomRsvVO> getAllByRmType(String rmType) {
		return dao.getAllByRmType(rmType);
	}
}
