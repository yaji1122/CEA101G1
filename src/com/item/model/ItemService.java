package com.item.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemService {
 
	private ItemDAO_interface dao;
	
	public ItemService() {
		dao = new ItemDAO();
	}
	
	public ItemVO addItem(
			String item_name, String item_type_no, Double item_price, String item_status, String item_on_sale, String item_detail, Integer points) {
		
		ItemVO itemVO = new ItemVO();
		itemVO.setItem_name(item_name);
		itemVO.setItem_type_no(item_type_no);
		itemVO.setItem_price(item_price);
		itemVO.setItem_status(item_status);
		itemVO.setItem_on_sale(item_on_sale);
		itemVO.setItem_detail(item_detail);
		itemVO.setPoints(points);
		dao.insert(itemVO);
		
		return itemVO;
	}
	
	public ItemVO updateItem(String item_name, String item_type_no, Double item_price,
			String item_status, String item_on_sale, String item_detail, Integer points, String item_no) {

		ItemVO itemVO = new ItemVO();

		itemVO.setItem_name(item_name);
		itemVO.setItem_type_no(item_type_no);
		itemVO.setItem_price(item_price);
		itemVO.setItem_status(item_status);
		itemVO.setItem_on_sale(item_on_sale);
		itemVO.setItem_detail(item_detail);
		itemVO.setPoints(points);
		itemVO.setItem_no(item_no);
		dao.update(itemVO);

		return itemVO;
	}

	public ItemVO getOneItem(String item_no) {
		return dao.findByPrimaryKey(item_no);
	}
	public List<ItemVO> getAllItem(){
		return dao.getAll();
	}
	
	public List<ItemVO> getAllByItem_type_no(String item_type_no){
		return dao.getAllByItem_type_no(item_type_no);
	}
	
	public List<ItemVO> getAllItemBySt(){
		return dao.getAllByStatus();
	}
	
	public List<ItemVO> getAllByItem_type_noBySt(String item_type_no){
		return dao.getAllByItem_type_noByStatus(item_type_no);
	}
	
	public Map<String, List<String>>getItem_noBysessionID(String sessionID,String item_no){
		
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		
		List<String> currentValue = map.get(sessionID);
		if(currentValue==null){
			System.out.println("currentValue==null");
			map.put(sessionID, new ArrayList<String>(Arrays.asList(item_no)));
		} else {
			System.out.println("currentValue!=null");
			currentValue.add(item_no);
		}
		for (String keys : map.keySet()) {
		   System.out.println("key=" + keys);
		}
//		for(int i=0;i<currentValue.size();i++){
//		    System.out.println(currentValue.get(i));
//		} 
		return map;
	}
}
