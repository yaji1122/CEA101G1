package com.actreserve.model;

import java.util.List;
import com.members.model.MembersVO;
import com.act.model.ActVO;

public interface ActReserve_interface {
	
	void insert(MembersVO membersVO, ActVO actVO);
	
	void update(MembersVO membersVO, ActVO actVO);
	
	void delete(MembersVO membersVO, ActVO actVO);
	
	List<String> getAllAct_ByMb_id(String mb_id);
	
	Integer getPriceByMbid(String mb_id,String actNo);

}
