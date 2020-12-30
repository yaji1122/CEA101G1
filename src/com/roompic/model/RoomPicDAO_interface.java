package com.roompic.model;

import java.util.List;

public interface RoomPicDAO_interface {
	public void insert(RoomPicVO rmpicvo);
	public void delete(String picno);
	public List<RoomPicVO> getAllByRoomType(String rmtype);
	public byte[] getOneRmPic(String rm_pic_no);
}
