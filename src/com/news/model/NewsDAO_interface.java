package com.news.model;

import java.util.List;

public interface NewsDAO_interface {
	public void insert(NewsVO newsVO);
	public void update(NewsVO newsVO);
	public void delete(String news_no);
	public void updateByNewsno(String news_no);
	public NewsVO findByNewsno(String news_no);
	public List<NewsVO> getAll();
}
