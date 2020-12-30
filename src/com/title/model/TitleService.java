package com.title.model;

import java.util.List;

public class TitleService {

private TitleDAO_interface dao;
	
	public TitleService() {
		dao = new TitleDAO();
	}
	
	public TitleVO addTitle(String title_no, String title) {
		
		TitleVO titleVO =new TitleVO();
		titleVO.setTitle_no(title_no);
		titleVO.setTitle(title);;
		dao.insert(titleVO);
		
		return titleVO;
	}
	
	public TitleVO updateTitle(String title_no, String title) {

		TitleVO funcVO =new TitleVO();
		funcVO.setTitle_no(title_no);
		funcVO.setTitle(title);
		dao.update(funcVO);
		
		return funcVO;
	}
	
	public TitleVO getOneTitle(String title_no) {
		return dao.findByPrimaryKey(title_no);
	}
	
	public List<TitleVO> getAll(){
		return dao.getAll();
	}

}
