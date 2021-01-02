package com.roomrsv.model;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

public class RoomRsvService {
	private RoomRsvDAO_interface dao;
	
	public RoomRsvService() {
		dao = new RoomRsvDAO();
	}
	
	public void insertRsvDate(LocalDate rsvDate, Connection conn) {
		dao.insert(rsvDate, conn);
	}
	
	public void updateRmLeft(JSONObject bkitem, Connection conn) {
		dao.update(bkitem, conn);
	}
	
	public void cancelNupdateRmLeft(Integer stay, LocalDate startDate, String rmType, Connection conn) {
		dao.cancel(stay, startDate, rmType, conn);
	}
	
	public void deleteRsvDate(LocalDate rsvDate) {
		dao.delete(rsvDate);
	}
	
	public Integer roomCheck(LocalDate rsvDate, Integer stay, String rmType) {
		return dao.roomCheck(rsvDate, stay, rmType);
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
