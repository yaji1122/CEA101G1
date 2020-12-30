package com.func.model;

import java.util.*;

import com.emp.model.EmpVO;

public interface FuncDAO_interface {

	public void insert(FuncVO funcVO);
	public void update(FuncVO funcVO);
	public void delete(String func_no);
	public FuncVO findByPrimaryKey(String func_no);
	public List<FuncVO> getAll();
	
}
