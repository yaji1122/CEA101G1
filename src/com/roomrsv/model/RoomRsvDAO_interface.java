package com.roomrsv.model;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

public interface RoomRsvDAO_interface {
	public void insert(LocalDate rsvDate, Connection conn);
	public void update(JSONObject bkitem, Connection conn);
	public void cancel(Integer stay, LocalDate startDate, String rmType, Connection conn);
	public void delete(LocalDate rsvDate);
	public void renewQty(String rm_type, Integer upordown, Connection conn);
	public Integer roomCheck(LocalDate rsvDate, Integer stay, String rmType);
	public RoomRsvVO getOneByDateNRmType(LocalDate rsvDate, String rm_ype, Connection conn);
	public List<RoomRsvVO> getOneDayByDate(LocalDate rsvDate);
	public List<RoomRsvVO> getAll();
	public List<RoomRsvVO> getAllByRmType(String rmType);
}
