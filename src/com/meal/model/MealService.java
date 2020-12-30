package com.meal.model;

import java.util.List;

public class MealService {

	private MealDAO_interface dao;
	
	public MealService() {
		dao = new MealDAO();
	}
	
	public MealVO addMeal(String meal_type_no, String meal_name, Integer price, byte[] meal_pic, String meal_info, Integer making_time) {

		MealVO mealVO = new MealVO();

		mealVO.setMeal_type_no(meal_type_no);
		mealVO.setMeal_name(meal_name);
		mealVO.setPrice(price);
		mealVO.setMeal_pic(meal_pic);
		mealVO.setMeal_info(meal_info);
		mealVO.setMaking_time(making_time);
		dao.insert(mealVO);

		return mealVO;
	}

	public MealVO updateMeal(String meal_no, String meal_type_no, String meal_name, Integer price, byte[] meal_pic, String meal_info, Integer making_time, String meal_status) {

		MealVO mealVO = new MealVO();

		mealVO.setMeal_no(meal_no);
		mealVO.setMeal_type_no(meal_type_no);
		mealVO.setMeal_name(meal_name);
		mealVO.setPrice(price);
		mealVO.setMeal_pic(meal_pic);
		mealVO.setMeal_info(meal_info);
		mealVO.setMaking_time(making_time);
		mealVO.setMeal_status(meal_status);
		dao.update(mealVO);

		return mealVO;
	}
	
	public MealVO updateMealStatus(String meal_status) {
		MealVO mealVO = new MealVO();
		
		mealVO.setMeal_status(meal_status);
		dao.updateMealStatus(meal_status);
		return mealVO;
	}

	public void deleteMeal(String meal_no) {
		dao.delete(meal_no);
	}

	public MealVO getOneMeal(String meal_no) {
		return dao.findByPrimaryKey(meal_no);
	}
	
	public List<MealVO> getActiveMeal(String meal_status) {
		return dao.getActiveMeal(meal_status);
	}

	public List<MealVO> getAll() {
		return dao.getAll();
	}
	
	public MealVO getOnePic(String meal_no) {
		return dao.getOnePic(meal_no);
	}
}
