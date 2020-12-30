package com.mealorder.model;

import java.util.List;

public class MealOrderService {

	private MealOrderDAO_interface dao;
	
	public MealOrderService() {
		dao = new MealOrderDAO();
	}
	
	public MealOrderVO addMealOrder(String mb_id, String rm_no, Integer total_price) {
		MealOrderVO mealOrderVO = new MealOrderVO();

	mealOrderVO.setMb_id(mb_id);
	mealOrderVO.setRm_no(rm_no);
	mealOrderVO.setTotal_price(total_price);
	dao.insert(mealOrderVO);

		return mealOrderVO;
	}

	public MealOrderVO updateMealOrder(String meal_odno, String rm_no, Integer total_price, String od_status) {

		MealOrderVO mealOrderVO = new MealOrderVO();

		mealOrderVO.setMeal_odno(meal_odno);
		mealOrderVO.setRm_no(rm_no);
		mealOrderVO.setTotal_price(total_price);
		mealOrderVO.setOd_status(od_status);
		
		dao.update(mealOrderVO);

		return mealOrderVO;
	}

	public void deleteMealOrder(String meal_odno) {
		dao.delete(meal_odno);
	}

	public MealOrderVO getOneMealOrder(String meal_odno) {
		return dao.findByPrimaryKey(meal_odno);
	}

	public List<MealOrderVO> getAll() {
		return dao.getAll();
	}
}
