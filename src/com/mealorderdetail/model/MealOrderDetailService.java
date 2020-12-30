package com.mealorderdetail.model;

import java.util.List;

public class MealOrderDetailService {

	private MealOrderDetailDAO_interface dao;

	public MealOrderDetailService() {
		dao = new MealOrderDetailDAO();
	}

	public MealOrderDetailVO addMealOrderDetail(String meal_odno, String meal_no, Integer price, Integer qty) {
		MealOrderDetailVO mealOrderDetailVO = new MealOrderDetailVO();

		mealOrderDetailVO.setMeal_odno(meal_odno);
		mealOrderDetailVO.setMeal_no(meal_no);
		mealOrderDetailVO.setPrice(price);
		mealOrderDetailVO.setQty(qty);
		dao.insert(mealOrderDetailVO);

		return mealOrderDetailVO;
	}

	public MealOrderDetailVO updateMealOrderDetail(String meal_odno, String meal_no, Integer qty) {

		MealOrderDetailVO mealOrderDetailVO = new MealOrderDetailVO();

		mealOrderDetailVO.setMeal_odno(meal_odno);
		mealOrderDetailVO.setMeal_no(meal_no);
		mealOrderDetailVO.setQty(qty);
		dao.update(mealOrderDetailVO);

		return mealOrderDetailVO;
	}

	public void deleteMealOrderDetail(String meal_odno) {
		dao.delete(meal_odno);
	}

	public MealOrderDetailVO getOneMealOrderDetail(String meal_odno) {
		return dao.findByMealOdno(meal_odno);
	}

	public List<MealOrderDetailVO> getAll() {
		return dao.getAll();
	}
}
