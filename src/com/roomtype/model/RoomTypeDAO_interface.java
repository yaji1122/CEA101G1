package com.roomtype.model;

import java.util.*;

public interface RoomTypeDAO_interface {
	public void insert(RoomTypeVO rmtypevo);
	public void update(RoomTypeVO rmtypevo);
	public void updateQty(RoomTypeVO rmtypevo, int number);
	public RoomTypeVO getOneRmType(String rmtype);
	public List<RoomTypeVO> getallRoomType();
}
