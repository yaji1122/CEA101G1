package com.choppers.model;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;
import java.util.List;

import com.roompic.model.RoomPicVO;


public class ChoppersService {
	private ChoppersDAO_interface dao;

	public ChoppersService() {
		dao = new ChoppersDAO();
	}

	public ChoppersVO addChopper(byte[] chop_pic, String chop_name, Integer chop_price, String chop_info) {

		ChoppersVO chopvo = new ChoppersVO();

		chopvo.setChop_pic(chop_pic);
		chopvo.setChop_name(chop_name);
		chopvo.setChop_price(chop_price);
		chopvo.setChop_info(chop_info);
		dao.insert(chopvo);
		return chopvo;
	}

	public ChoppersVO updateChopper(String chop_no, String chop_name, Integer chop_price, String chop_info) {

		ChoppersVO chopvo = new ChoppersVO();

		chopvo.setChop_no(chop_no);
		chopvo.setChop_name(chop_name);
		chopvo.setChop_price(chop_price);
		chopvo.setChop_info(chop_info);
		dao.update(chopvo);
		return chopvo;
	}
	
	public void updateChopperStatus(String chop_no, String chop_status) {

		ChoppersVO chopvo = new ChoppersVO();

		chopvo.setChop_no(chop_no);
		chopvo.setChop_status(chop_status);
		dao.update_status(chop_no, chop_status);
	}
	
	public void updateChopperPic(String chop_no, byte[] chop_pic) {

		ChoppersVO chopvo = new ChoppersVO();

		chopvo.setChop_no(chop_no);
		chopvo.setChop_pic(chop_pic);
		dao.update_pic(chop_no, chop_pic);
	}
	
	public void deleteChop(String chop_no) {
		dao.delete(chop_no);
	}

	public List<ChoppersVO> getAll() {
		return dao.getAll();
	}
	
	public String getChopPic(String chop_no) {
		String base64Img = "";
		ChoppersVO chop = dao.getOneByChopNo(chop_no);
		byte[] picBytes = chop.getChop_pic();
		if (picBytes == null) {
			return base64Img;
		}
		base64Img = Base64.getEncoder().encodeToString(picBytes);
		return base64Img;
	}
	
	public ChoppersVO getOneByChopNo(String chop_no) {
		return dao.getOneByChopNo(chop_no);
	}
}
