package com.time_table.model;

import java.util.List;

public class TimeTableService {
	
	private TimeTableDAO_interface dao;
	
	public TimeTableService() {
		dao = new TimeTableDAO();
	}
	
	public TimeTableVO addTimeTable(Integer serv_period, String serv_no, Integer max_serv_ppl) {
		
		TimeTableVO timeTableVO = new TimeTableVO();
		
		timeTableVO.setServ_period(serv_period);
		timeTableVO.setServ_no(serv_no);
		timeTableVO.setMax_serv_ppl(max_serv_ppl);
		
		dao.insert(timeTableVO);
		
		return timeTableVO;
	}
	
	public TimeTableVO updateTimeTable(Integer serv_period, String serv_no, Integer max_serv_ppl) {

		TimeTableVO timeTableVO = new TimeTableVO();
		
		timeTableVO.setServ_period(serv_period);
		timeTableVO.setServ_no(serv_no);
		timeTableVO.setMax_serv_ppl(max_serv_ppl);
		
		dao.update(timeTableVO);
		
		return timeTableVO;
	}
	
	public void deleteTimeTable(String serv_no, Integer serv_period) {
		dao.delete(serv_no, serv_period);
	}
	
	public TimeTableVO getOneTimeTable(String serv_no, Integer serv_period) {
		return dao.findByPrimaryKey(serv_no, serv_period);
	}

	public List<TimeTableVO> getAll() {
		return dao.getAll();
	}
	

}
