package com.roomtype.model;

import java.util.List;

public class RoomTypeService {

	private RoomTypeDAO_interface dao;

	public RoomTypeService() {
		dao = new RoomTypeDAO();
	}

	public RoomTypeVO addRoomType(String rm_type, String type_name, String type_eng_name, Integer rm_price, Integer rm_capacity,
			String rm_info_title, String rm_info) {

		RoomTypeVO roomTypeVO = new RoomTypeVO();

		roomTypeVO.setRm_type(rm_type);
		roomTypeVO.setType_name(type_name);
		roomTypeVO.setType_eng_name(type_eng_name);
		roomTypeVO.setRm_price(rm_price);
		roomTypeVO.setRm_capacity(rm_capacity);
		roomTypeVO.setRm_info_title(rm_info_title);
		roomTypeVO.setRm_info(rm_info);
		dao.insert(roomTypeVO);

		return roomTypeVO;
	}

	public RoomTypeVO updateRoomType(String rm_type, String type_name, String type_eng_name, Integer rm_price, Integer rm_capacity,
			String rm_info_title, String rm_info) {

		RoomTypeVO roomTypeVO = new RoomTypeVO();

		roomTypeVO.setRm_type(rm_type);
		roomTypeVO.setType_name(type_name);
		roomTypeVO.setType_eng_name(type_eng_name);
		roomTypeVO.setRm_price(rm_price);
		roomTypeVO.setRm_capacity(rm_capacity);
		roomTypeVO.setRm_info_title(rm_info_title);
		roomTypeVO.setRm_info(rm_info);
		dao.update(roomTypeVO);

		return roomTypeVO;
	}

	public void updateRoomQty(String rm_type, int number) {
		RoomTypeVO roomTypeVO = new RoomTypeVO();
		roomTypeVO.setRm_type(rm_type);
		dao.updateQty(roomTypeVO, number);
	}

	public List<RoomTypeVO> getAll() {
		return dao.getallRoomType();
	}
	
	public RoomTypeVO getOne(String rmtype) {
		return dao.getOneRmType(rmtype);
	}
}
