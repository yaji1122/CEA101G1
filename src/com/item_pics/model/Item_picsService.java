package com.item_pics.model;

import java.util.List;

import javax.servlet.http.Part;

public class Item_picsService {
	
	private Item_picsDAO_interface dao;
	
	public Item_picsService() {
		dao = new Item_picsDAO();
	}
	
	public Item_picsVO addPics(
			String item_no, Part part) {
		
		Item_picsVO  item_picsVO = new  Item_picsVO();
		item_picsVO.setItem_no(item_no);
		item_picsVO.setPart(part);
		dao.insert(item_picsVO);
		
		return item_picsVO;
	}
	
	public Item_picsVO updateItem_pic(String item_pic_no, String item_no, byte[] item_pic
			) {

		Item_picsVO item_picsVO = new Item_picsVO();

		item_picsVO.setItem_pic_no(item_pic_no);
		item_picsVO.setItem_no(item_no);
		item_picsVO.setItem_pic(item_pic);
		
		dao.update(item_picsVO);

		return item_picsVO;
	}

	public Item_picsVO getOnePic(String item_pic_no) {
		return dao.findByPrimaryKey(item_pic_no);
	}
	public List<Item_picsVO> getAllPics(String item_no){
		return dao.getAll(item_no);
	}
		
	public void deletePic(String item_pic_no) {
		dao.delete(item_pic_no);
	}
}
