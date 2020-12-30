package com.shoppingCart.model;

import java.util.List;

import com.item.model.ItemVO;
import com.members.model.MembersVO;

public interface CartDAO_interface {

	void insert(MembersVO membersVO, ItemVO itemVO);

	void update(MembersVO membersVO, ItemVO itemVO);

	void replace(MembersVO membersVO, ItemVO itemVO);

	void delete(MembersVO membersVO, ItemVO itemVO);

	List<String> getAllItem_noByMb_id(String mb_id);

	Integer getValueByItem_no(String mb_id, String it);
	
//	void insertCo(String user_session_id, ItemVO itemVO);

}
