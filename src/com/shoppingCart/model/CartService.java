package com.shoppingCart.model;

import java.util.*;
import com.members.model.*;
import com.item.model.*;

public class CartService {

	private CartDAO_interface dao;

	public CartService() {
		dao = new CartDAO();
	}

	public void insertCart(String mb_id, String item_no,Integer quantity) {

		ItemVO itemVO = new ItemVO();
		itemVO.setItem_no(item_no);
		itemVO.setQuantity(quantity);
		MembersVO membersVO = new MembersVO();
		membersVO.setMb_id(mb_id);
		
		dao.insert(membersVO, itemVO);
	}
	
//	public void insertCartCo(String user_session_id, String item_no,Integer quantity) {
//
//		ItemVO itemVO = new ItemVO();
//		itemVO.setItem_no(item_no);
//		itemVO.setQuantity(quantity);
//
//		
//		dao.insertCo(user_session_id, itemVO);
//	}
	
	public void updateCart(String mb_id, String item_no, Integer quantity) {

		ItemVO itemVO = new ItemVO();
		itemVO.setItem_no(item_no);
		itemVO.setQuantity(quantity);
		MembersVO membersVO = new MembersVO();
		membersVO.setMb_id(mb_id);

		dao.update(membersVO, itemVO);
	}
	
	public void replace(String mb_id, String item_no, Integer quantity) {
		ItemVO itemVO = new ItemVO();
		itemVO.setItem_no(item_no);
		itemVO.setQuantity(quantity);
		MembersVO membersVO = new MembersVO();
		membersVO.setMb_id(mb_id);
		dao.replace(membersVO, itemVO);
	}
	
	public void deleteCart(String mb_id, String item_no, Integer quantity) {
		
		ItemVO itemVO = new ItemVO();
		itemVO.setItem_no(item_no);
		itemVO.setQuantity(quantity);
		MembersVO membersVO = new MembersVO();
		membersVO.setMb_id(mb_id);
		
		dao.delete(membersVO, itemVO);
	}
	
	public List<ItemVO> getAllItem_noByMb_id(String mb_id){
		
		List<ItemVO> list = new ArrayList<>();
		System.out.println(" getAllItem_noByMb_id" + dao. getAllItem_noByMb_id(mb_id));
		
		for (String  i : dao. getAllItem_noByMb_id(mb_id)) {
			System.out.println(i);
			ItemVO aitem = new ItemVO();
			aitem.setItem_no(i);
			list.add(aitem);
		}
		System.out.println(list.toString());
		return list;
	}
	
	public Integer getValueByItem_no(String mb_id,String item_no) {
		return dao.getValueByItem_no(mb_id, item_no);
	}
}
