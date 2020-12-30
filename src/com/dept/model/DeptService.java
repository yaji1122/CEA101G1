package com.dept.model;

import java.util.*;

public class DeptService {
	
	private DeptDAO_interface dao;
	
	public DeptService() {
		dao = new DeptDAO();
	} 

	public DeptVO addDept(String dept_no, String dept) {
		
		DeptVO deptVO = new DeptVO();
		deptVO.setDept_no(dept_no);
		deptVO.setDept(dept);
		dao.insert(deptVO);
		return deptVO;
	}
	
	public DeptVO updateDept(String dept_no, String dept) {
		
		DeptVO deptVO = new DeptVO();
		deptVO.setDept_no(dept_no);
		deptVO.setDept(dept);
		dao.update(deptVO);
		return deptVO;
	}

	public DeptVO getOneDept(String dept_no) {
		return dao.findByPrimaryKey(dept_no);
	}
	
	public List<DeptVO> getAll(){
		return dao.getAll();
	} 
	
}
