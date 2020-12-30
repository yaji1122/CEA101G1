package com.members.model;

import java.util.List;

public interface MembersDAO_interface {
	public void insert(MembersVO membervo);
	public void update(MembersVO membervo);
	public void updateStatus(MembersVO membervo);
	public void updatePwd(MembersVO membervo);
	public void updatePic(MembersVO membervo);
	public void updatePoint(MembersVO membervo);
	public MembersVO getOneByAcc(String mb_acc);
	public MembersVO getOneByEmail(String mb_email);
	public MembersVO getOneById(String mb_id);
	public List<MembersVO> getAll();
	public List<MembersVO> getAllByStatus(String mb_status);
}
