package com.actevent.model;

import java.util.*;

public interface ActEventDAO_interface {
	
	public void insert(ActEventVO actEventVO);
	public void update(ActEventVO actEventVO);
	public void delete(String actEventNo);
	public ActEventVO findByPrimaryKey(String actEventNo);
	public List<ActEventVO> getAll();

}
