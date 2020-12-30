package com.acttype.model;

import java.util.*;

public class ActTypeService {
	
	private ActTypeDAO dao;
		
	public ActTypeService(){
		
		dao = new ActTypeDAO();
	}
	
	public ActTypeVO addActType(String actTypeno,String actTypeName){
		
		ActTypeVO actTypeVO = new ActTypeVO();
		actTypeVO.setActTypeNo(actTypeno);
		actTypeVO.setActTypeName(actTypeName);
		
		dao.insert(actTypeVO);
		return actTypeVO;
	}
	
    public ActTypeVO updateActType(String actTypeno,String actTypeName){
		
		ActTypeVO actTypeVO = new ActTypeVO();
		actTypeVO.setActTypeNo(actTypeno);
		actTypeVO.setActTypeName(actTypeName);
		
		
		dao.update(actTypeVO);
		return actTypeVO;
	}
    
    public void deleteActType(String actTypeno){
		
		dao.delete(actTypeno);
	}
    
    public ActTypeVO getOneActType(String actTypeno) {
    	return dao.findByPrimaryKey(actTypeno);
    	
    }
    
    public List<ActTypeVO> getAll() {
		return dao.getAll();
	}

}
