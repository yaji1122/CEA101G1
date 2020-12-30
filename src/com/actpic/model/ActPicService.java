package com.actpic.model;

import java.util.*;

public class ActPicService {
	
	private ActPicDAO dao;
	
	public ActPicService() {
		
		dao = new ActPicDAO(); 
	}
	
	public ActPicVO addActPic(String actPicNo, String actEventNo,byte[] actPic) {
	    
		ActPicVO actPicVO = new ActPicVO();
		actPicVO.setActPicNo(actPicNo);
		actPicVO.setActEventNo(actEventNo);
		actPicVO.setActPic(actPic);
		dao.insert(actPicVO);
		return actPicVO;
	}
	
    public ActPicVO updateActPic(String actPicNo, String actEventNo,byte[] actPic) {
	    
		ActPicVO actPicVO = new ActPicVO();
		actPicVO.setActPicNo(actPicNo);
		actPicVO.setActEventNo(actEventNo);
		actPicVO.setActPic(actPic);
		dao.update(actPicVO);
		return actPicVO;
	}
    
     public void deleteActPic(String actPicNo) {
	    
		dao.delete(actPicNo);
		
	}
     
     public ActPicVO getOneActPic(String actPicNo) {
    	 
     	return dao.findByPrimaryKey(actPicNo);
     	
     }
     
     public List<ActPicVO> getAll() {
    	 
    	 return dao.getAll();
      	
      }

           
}

