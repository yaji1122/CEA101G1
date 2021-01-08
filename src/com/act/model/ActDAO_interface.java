package com.act.model;
import java.util.*;

import com.actevent.model.ActEventVO;

public interface ActDAO_interface {
	
	public void insert(ActVO actVO);
	public void update(ActVO actVO);
	public void delete(String actNo);
	public ActVO findByPrimaryKey(String actNo);
	public List<ActVO> getAll();
	public List<ActVO> getAllByActStatus();
	public byte[] getOnePic(String  actNo);

}
