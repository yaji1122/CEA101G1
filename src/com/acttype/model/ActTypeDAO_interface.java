package com.acttype.model;

import java.util.*;

public interface ActTypeDAO_interface {
	public void insert(ActTypeVO actTypeVO);
	public void update(ActTypeVO actTypeVO);
	public void delete(String actTypeno);
	public ActTypeVO findByPrimaryKey(String actTypeno);
	public List<ActTypeVO> getAll();

}
