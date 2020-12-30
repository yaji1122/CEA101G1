package com.func.model;

import java.util.List;

public class FuncService {

	private FuncDAO_interface dao;
	
	public FuncService() {
		dao = new FuncDAO();
	}
	
	public FuncVO addFunc(String func_no, String func_name) {
		
		FuncVO funcVO =new FuncVO();
		funcVO.setFunc_no(func_no);
		funcVO.setFunc_name(func_name);
		dao.insert(funcVO);	
		return funcVO;
	}
	
	public FuncVO updateFunc(String func_no, String func_name) {

		FuncVO funcVO =new FuncVO();
		funcVO.setFunc_no(func_no);
		funcVO.setFunc_name(func_name);
		dao.update(funcVO);
		return funcVO;
	}
	
	public FuncVO getOneFunc(String func_no) {
		return dao.findByPrimaryKey(func_no);
	}
	
	public List<FuncVO> getAll(){
		return dao.getAll();
	}

	public void deleteFunc(String func_no) {
		dao.delete(func_no);
	}
}
