package com.auth.model;

import java.util.List;
import com.emp.model.EmpVO;

public class AuthService {

	private AuthDAO_interface dao;
	
	public AuthService() {
		dao = new AuthDAO();
	}
	
	public AuthVO addAuth(String emp_id, String func_no) {
		
		AuthVO authVO = new AuthVO();
		authVO.setEmp_id(emp_id);
		authVO.setFunc_no(func_no);
		dao.insert(authVO);
    	return authVO;
	}
	
//	public AuthVO updateAuth(String emp_id, String func_no) {
//		
//		AuthVO authVO = new AuthVO();
//		authVO.setEmp_id(emp_id);
//		authVO.setFunc_no(func_no);
//		dao.update(authVO);	
//		return authVO;
//	}
	
	
	public AuthVO getOneAuth(String emp_id) {
		return dao.findByPrimaryKey(emp_id);
	}
	
	public List<AuthVO> getAll(){
		return dao.getAll();
	} 

	public List <AuthVO> getAllByEmp(String emp_id) {
		return dao.findByEmp(emp_id);
	}
	
	public List <AuthVO> getAllByFunc(String func_no) {
		return dao.findByFunc(func_no);
	}
	
	public void deleteAuth(String emp_id, String func_no) {
		dao.delete(emp_id, func_no);
	} 
	
	public void deleteAllAuth(String emp_id) {
		dao.deleteAll(emp_id);
	}
}
