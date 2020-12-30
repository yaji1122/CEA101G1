package com.dept.model;

import java.util.*;

public interface DeptDAO_interface {
	
	public void insert(DeptVO deptVO);
	public void update(DeptVO deptVO);
	public DeptVO findByPrimaryKey(String dept_no);
	public List<DeptVO> getAll();
	
}
