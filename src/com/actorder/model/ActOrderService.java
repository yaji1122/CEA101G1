package com.actorder.model;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

import com.mealorder.model.MealOrderVO;

public class ActOrderService {
	
    private ActOrderDAO dao;
	
	public ActOrderService() {
		
		dao = new ActOrderDAO(); 
	}
	
	public ActOrderVO addActOrder(String actOdno,String actNo,String bk_no
    		,LocalTime odTime,String odStatus,Integer ppl,Integer totalPrice){
		
    	ActOrderVO actOrderVO = new ActOrderVO();
    	actOrderVO.setActOdno(actOdno);
    	actOrderVO.setActNo(actNo);
    	actOrderVO.setBkNo(bk_no);
    	actOrderVO.setOdTime(odTime);
    	actOrderVO.setOdStatus(odStatus);
    	actOrderVO.setPpl(ppl);
    	actOrderVO.setTotalPrice(totalPrice);
		
		dao.insert(actOrderVO);
		return actOrderVO;
	}
	
	public ActOrderVO updateActOrder(String actOdno, String odStatus, Integer ppl, Integer totalPrice){
		
    	ActOrderVO actOrderVO = new ActOrderVO();
    	actOrderVO.setActOdno(actOdno);
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
    
    public List<ActOrderVO> getAllByBkNo(String bk_no) {
		return dao.getAllByBkNo(bk_no);
	}	
    
    public List<ActOrderVO> getAllByStatus(String status) {
    	if (status.equals("all")) {
    		return dao.getAll();
    	} else {
    		return dao.getAll().stream().filter(e -> e.getOdStatus().equals(status)).collect(Collectors.toList());
    	}
	}	

}
