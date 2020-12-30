package com.emp.model;

import java.util.*;

import com.auth.model.*;

public interface EmpDAO_interface {

	public EmpVO insert(EmpVO empVO);
	public void update(EmpVO empVO);
//	public void delete(String getEmp_ID);
	public EmpVO findByPrimaryKey(String getEmp_ID);
	public List <EmpVO> getAll();
	public List <EmpVO> findByDept(String dept_no);
	public List <EmpVO> findByTitle(String title_no);
	public EmpVO getOnePic(String emp_id);
	//同時新增員工與權限 
    public void insertWithAuths(EmpVO empVO , List<AuthVO> list);
    //員工登入驗證
    public EmpVO login(String emp_id, String emp_pwd);
    
}
