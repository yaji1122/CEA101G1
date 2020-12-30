package com.actorder.model;

import java.sql.Date;
import java.util.*;

public class ActOrderService {
	
    private ActOrderDAO dao;
	
	public ActOrderService() {
		
		dao = new ActOrderDAO(); 
	}
	
	public ActOrderVO addActOrder(String actOdno,String actNo,String mbId
    		,Date odTime,String odStatus,Integer ppl,Integer totalPrice){
		
    	ActOrderVO actOrderVO = new ActOrderVO();
    	actOrderVO.setActOdno(actOdno);
    	actOrderVO.setActNo(actNo);
    	actOrderVO.setMbId(mbId);
    	actOrderVO.setOdTime(odTime);
    	actOrderVO.setOdStatus(odStatus);
    	actOrderVO.setPpl(ppl);
    	actOrderVO.setTotalPrice(totalPrice);
		
		dao.insert(actOrderVO);
		return actOrderVO;
	}
	
	public ActOrderVO updateActOrder(String actOdno,String actNo,String mbId
    		,Date odTime,String odStatus,Integer ppl,Integer totalPrice){
		
    	ActOrderVO actOrderVO = new ActOrderVO();
    	actOrderVO.setActOdno(actOdno);
    	actOrderVO.setActNo(actNo);
    	actOrderVO.setMbId(mbId);
    	actOrderVO.setOdTime(odTime);
    	actOrderVO.setOdStatus(odStatus);
    	actOrderVO.setPpl(ppl);
    	actOrderVO.setTotalPrice(totalPrice);
		
		dao.update(actOrderVO);
		return actOrderVO;
	}
	
    public void deleteActOrder(String actOdno){
		
		dao.delete(actOdno);
	}
    
    public ActOrderVO getOneActOrder(String actOdno) {
    	return dao.findByPrimaryKey(actOdno);
    	
    }
    
    public List<ActOrderVO> getAll() {
		return dao.getAll();
	}

}
