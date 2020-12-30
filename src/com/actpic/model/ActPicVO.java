package com.actpic.model;
import java.sql.Date;

public class ActPicVO implements java.io.Serializable{
	
	private String actPicNo;
	private String actEventNo;
	private byte[] actPic;
	
	public String getActPicNo() {
		return actPicNo;
	}
	public void setActPicNo(String actPicNo) {
		this.actPicNo = actPicNo;
	}
	public String getActEventNo() {
		return actEventNo;
	}
	public void setActEventNo(String actEventNo) {
		this.actEventNo = actEventNo;
	}
	public byte[] getActPic() {
		return actPic;
	}
	public void setActPic(byte[] actPic) {
		this.actPic = actPic;
	}
	

}
