package com.news.model;

import java.util.List;

public class NewsService {
	private NewsDAO_interface dao;
	
	public NewsService() {
		dao = new NewsDAO();
	}
	
	public NewsVO addNews(String news_content, String emp_id) {
		NewsVO newsVO = new NewsVO();
		newsVO.setNews_content(news_content);
		newsVO.setEmp_id(emp_id);
		
		dao.insert(newsVO);
		return newsVO;
	}
	
	public NewsVO updateNews(String news_no, String news_content, String emp_id) {
		NewsVO newsVO = new NewsVO();
		newsVO.setNews_no(news_no);
		newsVO.setNews_content(news_content);
		newsVO.setEmp_id(emp_id);
		
		dao.update(newsVO);
		return newsVO;
	}
	
	public void deleteNews(String news_no) {
		dao.delete(news_no);
	}
	
	public NewsVO getOneNews(String news_no) {
		return dao.findByNewsno(news_no);
	}
	
	public List<NewsVO> getAllNews(){
		return dao.getAll();
	}
}
