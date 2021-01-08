package com.actevent.model;

import java.util.*;

public class ActEventService{
	
	private ActEventDAO dao;
	
	public ActEventService(){
		
		dao = new ActEventDAO();
	}
	
    public ActEventVO addActEvent(String actEventNo,String actEventName
    		){
		
    	ActEventVO actEventVO = new ActEventVO();
    	actEventVO.setActEventNo(actEventNo);
    	actEventVO.setActEventName(actEventName);
		
		dao.insert(actEventVO);
		return actEventVO;
	}
    
    public ActEventVO updateActEvent(String actEventNo,String actEventName
    		){
		
    	ActEventVO actEventVO = new ActEventVO();
    	actEventVO.setActEventNo(actEventNo);
    	actEventVO.setActEventName(actEventName);
		
		dao.update(actEventVO);
		return actEventVO;
	}
    
    public void deleteActEvent(String actEventNo){
		
		dao.delete(actEventNo);
	}
    
    public ActEventVO getOneActEvent(String actEventNo) {
    	return dao.findByPrimaryKey(actEventNo);
    	
    }
    
    public List<ActEventVO> getAll() {
		return dao.getAll();
	}


}
