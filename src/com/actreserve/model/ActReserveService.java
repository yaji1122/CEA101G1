package com.actreserve.model;

import java.util.*;
import com.members.model.MembersVO;
import com.act.model.ActVO;

public class ActReserveService {
	
	private ActReserve_interface dao;
	
	public ActReserveService() {
		dao = new ActReserveDAO();
	}
	
	public void insertActToCart(String mb_id,String actNo,String participant,Integer actPrice) {
		MembersVO membersVO = new MembersVO();
		membersVO.setMb_id(mb_id);
		ActVO actVO = new ActVO();
		actVO.setActNo(actNo);
		actVO.setParticipant(participant);
		actVO.setActPrice(actPrice);
		
		dao.insert(membersVO, actVO);
		
	}
	
	public void updateActCart(String mb_id,String actNo,String participant,Integer actPrice) {
	    MembersVO membersVO = new MembersVO();
	    membersVO.setMb_id(mb_id);
	    ActVO actVO = new ActVO();
	    actVO.setActNo(actNo);
		actVO.setParticipant(participant);
		actVO.setActPrice(actPrice);
		
		dao.update(membersVO, actVO);
   }
	
	public void deleteActCart(String mb_id,String actNo,String participant,Integer actPrice) {
		MembersVO membersVO = new MembersVO();
	    membersVO.setMb_id(mb_id);
	    ActVO actVO = new ActVO();
	    actVO.setActNo(actNo);
		actVO.setParticipant(participant);
		actVO.setActPrice(actPrice);
		
		dao.delete(membersVO, actVO);
	} 
	
	public List<ActVO> getAllAct_ByMb_id(String mb_id){
		
		List<ActVO> actlist = new ArrayList<>();
		System.out.println("getAllAct"+dao.getAllAct_ByMb_id(mb_id));
		
		for(String i : dao.getAllAct_ByMb_id(mb_id)) {
			System.out.println(i);
			ActVO act = new ActVO();
			act.setActNo(i);
			actlist.add(act);
		}
		
		System.out.println(actlist.toString());
		return actlist;
	}
	
	public Integer getPriceByMbid(String mb_id,String actNo) {
		
		return dao.getPriceByMbid(mb_id, actNo);
	}
}
	   
