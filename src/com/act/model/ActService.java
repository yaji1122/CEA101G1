package com.act.model;

import java.sql.Date;
import java.time.LocalTime;
import java.util.*;

import com.act.model.ActVO;

public class ActService {
	
	private ActDAO dao;
	
	public ActService() {
		
		dao = new ActDAO();
	}
	
	public ActVO addAct(String actEventNo, String actName, String actStatus, LocalTime actTime, Integer actPrice, byte[] actPic, String actInfo) {
		ActVO actVO = new ActVO();
		actVO.setActEventNo(actEventNo);
		actVO.setActName(actName);
		actVO.setActStatus(actStatus);
		actVO.setActTime(actTime);
		actVO.setActPrice(actPrice);
		actVO.setActPic(actPic);
		actVO.setActInfo(actInfo);
		return dao.insert(actVO);
	}
	
	public ActVO updateAct(String actNo,String actEventNo,String actName, String actStatus, LocalTime actTime, Integer actPrice, byte[] actPic, String actInfo) {
		
		ActVO actVO = new ActVO();
		actVO.setActNo(actNo);
		actVO.setActEventNo(actEventNo);
		actVO.setActName(actName);
		actVO.setActStatus(actStatus);
		actVO.setActTime(actTime);
		actVO.setActPrice(actPrice);
		actVO.setActInfo(actInfo);
		actVO.setActPic(actPic);
		dao.update(actVO);
		return actVO;
	}
	
	public void deleteAct(String actNo) {
		
		dao.delete(actNo);
		
	}
	
	public ActVO getOneAct(String actNo) {
    	return dao.findByPrimaryKey(actNo);
    }
    
    public List<ActVO> getAll() {
		return dao.getAll();
	}
    
    public List<ActVO> getAllActByStatus(String ActNo){
    	return dao.getAllActStatus(ActNo);
    }
    
    public byte[] getOneActPic(String actNo) {
     	return dao.getOnePic(actNo);
     }


}
