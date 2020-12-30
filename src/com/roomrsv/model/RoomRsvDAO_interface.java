package com.roomrsv.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

public interface RoomRsvDAO_interface {
	public void insert(LocalDate rsvDate);
	public void update(LocalDate rsvDate, String rmType ,Integer rmLeft);
	public void delete(LocalDate rsvDate);
	public Integer roomCheck(LocalDate rsvDate, Integer stay, String rmType);
	public RoomRsvVO getOneByDateNRmType(LocalDate rsvDate, String rm_ype);
	public List<RoomRsvVO> getOneDayByDate(LocalDate rsvDate);
	public List<RoomRsvVO> getAll();
	public List<RoomRsvVO> getAllByRmType(String rmType);
}
