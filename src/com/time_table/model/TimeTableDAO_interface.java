package com.time_table.model;

import java.util.List;

public interface TimeTableDAO_interface {

	public void insert(TimeTableVO timeTableVO);

	public void update(TimeTableVO timeTableVO);

	public void delete(String serv_no, Integer serv_period);

	public TimeTableVO findByPrimaryKey(String serv_no, Integer serv_period);

	public List<TimeTableVO> getAll();
	// 萬用複合查詢(傳入參數型態Map)(回傳 List)
	// public List<EmpVO> getAll(Map<String, String[]> map);

}
