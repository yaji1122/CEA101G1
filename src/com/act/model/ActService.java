package com.act.model;

import java.sql.Date;
import java.util.*;

import com.act.model.ActVO;

public class ActService {
	
	private ActDAO dao;
	
	public ActService() {
		
		dao = new ActDAO();
	}
	
	public ActVO addAct(String actNo,String actEventNo,String actName,
			String actStatus,Date actRegTime,Date actDate,Date deadLine,
			String actTime,String participant,Integer actPrice,byte[] actPic,String actInfo) {
		
		ActVO actVO = new ActVO();
		actVO.setActNo(actNo);
		actVO.setActEventNo(actEventNo);
		actVO.setActName(actName);
		actVO.setActStatus(actStatus);
		actVO.setActDate(actDate);
		actVO.setActTime(actTime);
		actVO.setParticipant(participant);
		actVO.setActPrice(actPrice);
		actVO.setActPic(actPic);
		actVO.setActInfo(actInfo);
		
		dao.insert(actVO);
		return actVO;
		
	}
	
	public ActVO updateAct(String actNo,String actEventNo,String actName,
			String actStatus,Date actRegTime,Date actDate,Date deadLine,
			String actTime,String participant,Integer actPrice,byte[] actPic,String actInfo) {
		
		ActVO actVO = new ActVO();
		actVO.setActNo(actNo);
		actVO.setActEventNo(actEventNo);
		actVO.setActName(actName);
		actVO.setActStatus(actStatus);
		actVO.setActDate(actDate);
		actVO.setActTime(actTime);
		actVO.setParticipant(participant);
		actVO.setActPrice(actPrice);
		actVO.setActPic(actPic);
		actVO.setActInfo(actInfo);
		
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
    public ActVO getOneActPic(String actNo) {
   	 
     	return dao.findByPrimaryKey(actNo);
     	
     }


}
