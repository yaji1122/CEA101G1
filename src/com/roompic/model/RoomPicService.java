package com.roompic.model;

import javax.servlet.http.Part;

import java.util.*;

public class RoomPicService {
	private RoomPicDAO_interface dao;

	public RoomPicService() {
		dao = new RoomPicDAO();
	}

	public List<RoomPicVO> addRoomPic(String rm_type, Collection<Part> parts) {
		List<RoomPicVO> pics = new ArrayList<>();
		for (Part part: parts) {
			if (part.getContentType() == null || part.getContentType().indexOf("image") < 0 ) continue;
			RoomPicVO rmpicvo = new RoomPicVO();
			rmpicvo.setRm_type(rm_type);
			rmpicvo.setPart(part);
			dao.insert(rmpicvo);
			pics.add(rmpicvo);
		}
		return pics;
	}
	
	public void deleteRoomPic(String picno) {
		dao.delete(picno);
	}
	
	public List<RoomPicVO> getAllByRoomType(String rmtype) {
		return dao.getAllByRoomType(rmtype);
	}
	
	public byte[] getOneByPicNo(String rm_pic_no) {
		return dao.getOneRmPic(rm_pic_no);
	}
}
