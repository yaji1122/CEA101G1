package com.emp.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.annotation.MultipartConfig;

public class EmpService {

	private EmpDAO_interface dao;
	
	public EmpService() {
		dao = new EmpDAO();
	}
	
	public EmpVO addEmp(String emp_name,String emp_pwd, byte[] emp_pic,
	String emp_phone, String emp_email, String emp_city, String emp_town, String emp_address
	, String emp_status, String title_no,String dept_no) {
		
		EmpVO empVO = new EmpVO();
		empVO.setEmp_name(emp_name);
		empVO.setEmp_pwd(emp_pwd);
		empVO.setEmp_pic(emp_pic);
		empVO.setEmp_phone(emp_phone);
		empVO.setEmp_email(emp_email);
		empVO.setEmp_city(emp_city);
		empVO.setEmp_town(emp_town);
		empVO.setEmp_address(emp_address);
		empVO.setEmp_status(emp_status);
		empVO.setTitle_no(title_no);
		empVO.setDept_no(dept_no);
		EmpVO newEmp =  dao.insert(empVO);
		
		return newEmp;
	}
	
	public EmpVO updateEmp(String emp_id, String emp_name, String emp_pwd, byte[] emp_pic,
			String emp_phone, String emp_email, String emp_city, String emp_town, String emp_address
			, String emp_status,Date emp_date, String title_no,String dept_no) {
		
		EmpVO empVO = new EmpVO();
		empVO.setEmp_id(emp_id);
		empVO.setEmp_name(emp_name);
		empVO.setEmp_pwd(emp_pwd);
		empVO.setEmp_pic(emp_pic);
		empVO.setEmp_phone(emp_phone);
		empVO.setEmp_email(emp_email);
		empVO.setEmp_city(emp_city);
		empVO.setEmp_town(emp_town);
		empVO.setEmp_address(emp_address);
		empVO.setEmp_status(emp_status);
		empVO.setEmp_date(emp_date);
		empVO.setTitle_no(title_no);
		empVO.setDept_no(dept_no);
		dao.update(empVO);
		
		return empVO;
	}
	
	
	public EmpVO getOneEmp(String emp_id) {
		return dao.findByPrimaryKey(emp_id);
	}
	
	public List<EmpVO> getAll(){
		return dao.getAll();
	}
	
	public List<EmpVO> getOneByDept(String dept_no) {
		return dao.findByDept(dept_no);
	}
	
	public List<EmpVO> getOneByTitle(String title_no) {
		return dao.findByTitle(title_no);
	}
	
	public EmpVO getOnePic(String emp_id) {
		return dao.getOnePic(emp_id);
	}
    
	public EmpVO getOneByID(String emp_id, String emp_pwd) {
		return dao.login(emp_id, emp_pwd);
	}
	
	
}
