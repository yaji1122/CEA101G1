package com.auth.model;

import java.util.*;

import com.emp.model.EmpVO;

public interface AuthDAO_interface {

	public void insert(AuthVO authVO);
//	public void update(AuthVO authVO);
	public void delete(String emp_id, String func_no);
	public AuthVO findByPrimaryKey(String emp_id);
	public List<AuthVO> getAll();
	public List <AuthVO> findByEmp(String emp_id);
	public List <AuthVO> findByFunc(String func_no);
    //同時新增員工與權限 
    public void insert2 (AuthVO authVO , java.sql.Connection con);
}
