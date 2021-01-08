package com.news.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class NewsVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String news_no;
	private String news_content;
	private Timestamp news_time;
	private String emp_id;
	
	public String getNews_no() {
		return news_no;
	}
	public void setNews_no(String news_no) {
		this.news_no = news_no;
	}
	public String getNews_content() {
		return news_content;
	}
	public void setNews_content(String news_content) {
		this.news_content = news_content;
	}
	public Timestamp getNews_time() {
		return news_time;
	}
	public void setNews_time(Timestamp news_time) {
		this.news_time = news_time;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	
}
