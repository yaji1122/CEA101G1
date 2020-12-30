package com.members.model;

import java.sql.Date;
import java.util.List;

public class MembersService {

	private MembersDAO_interface dao;
	
	public MembersService() {
		dao = new MembersDAO();
	}
	
	public MembersVO addNewMem(String mb_name, String mb_pwd, String mb_salt, Date mb_bd, byte[] mb_pic, String mb_phone,
			String mb_email, String mb_city, String mb_town, String mb_address) {
		MembersVO membervo = new MembersVO();
		membervo.setMb_name(mb_name);
		membervo.setMb_pwd(mb_pwd);
		membervo.setMb_salt(mb_salt);
		membervo.setMb_pic(mb_pic);
		membervo.setMb_bd(mb_bd);
		membervo.setMb_phone(mb_phone);
		membervo.setMb_email(mb_email);
		membervo.setMb_city(mb_city);
		membervo.setMb_town(mb_town);
		membervo.setMb_address(mb_address);
		dao.insert(membervo);
		return membervo;
	}
	
	public MembersVO updateMem(String mb_name, Date mb_bd, String mb_phone, String mb_city, String mb_town, String mb_address, String mb_id) {
		MembersVO membervo = new MembersVO();
		membervo.setMb_name(mb_name);
		membervo.setMb_bd(mb_bd);
		membervo.setMb_phone(mb_phone);
		membervo.setMb_city(mb_city);
		membervo.setMb_town(mb_town);
		membervo.setMb_address(mb_address);
		membervo.setMb_id(mb_id);
		dao.update(membervo);
		return membervo;
	}
	
	public void updateStatus(String mb_id, String mb_status) {
		MembersVO membervo = new MembersVO();
		membervo.setMb_id(mb_id);
		membervo.setMb_status(mb_status);
		dao.updateStatus(membervo);
	}
	
	public void updatePwd(String mb_id, String mb_pwd, String mb_salt) {
		MembersVO membervo = new MembersVO();
		membervo.setMb_id(mb_id);
		membervo.setMb_pwd(mb_pwd);
		membervo.setMb_salt(mb_salt);
		dao.updatePwd(membervo);
	}
	
	public void updateMemPic(String mb_id, byte[] mb_pic) {
		MembersVO membervo = new MembersVO();
		membervo.setMb_id(mb_id);
		membervo.setMb_pic(mb_pic);
		dao.updatePic(membervo);
	}
	
	public void updateMemPoint(String mb_id, Integer mb_point) {
		MembersVO membervo = new MembersVO();
		membervo.setMb_id(mb_id);
		membervo.setMb_point(mb_point);
		dao.updatePoint(membervo);
	}
	public MembersVO getOneByMbId(String mb_id) {
		return dao.getOneById(mb_id);
	}
	
	public MembersVO getOneByMbEmail(String mb_email) {
		return dao.getOneByEmail(mb_email);
	}
	
	public List<MembersVO> getAll(){
		return dao.getAll();
	}
	
	public List<MembersVO> getAllByStatus(String mb_status){
		return dao.getAllByStatus(mb_status);
	}
}
