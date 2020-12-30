package com.title.model;

import java.io.Serializable;

public class TitleVO implements Serializable{
	
	private String Title_no;
	private String Title;
	
	public String getTitle_no() {
		return Title_no;
	}
	public void setTitle_no(String title_no) {
		Title_no = title_no;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}

}
