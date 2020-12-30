package com.mealtype.model;

import java.util.List;

public class MealTypeService {

	private MealTypeDAO_interface dao;

	public MealTypeService() {
		dao = new MealTypeDAO();
	}

	public MealTypeVO addMealType(String meal_type_no, String type_name) {
		MealTypeVO mealTypeVO = new MealTypeVO();

		mealTypeVO.setMeal_type_no(meal_type_no);
		mealTypeVO.setType_name(type_name);
		dao.insert(mealTypeVO);

		return mealTypeVO;
	}

	public MealTypeVO updateMealType(String meal_type_no, String type_name) {

		MealTypeVO mealTypeVO = new MealTypeVO();

		mealTypeVO.setMeal_type_no(meal_type_no);
		mealTypeVO.setType_name(type_name);
		dao.update(mealTypeVO);

		return mealTypeVO;
	}

	public void deleteMealType(String meal_type_no) {
		dao.delete(meal_type_no);
	}

	public MealTypeVO getOneMealType(String meal_type_no) {
		return dao.findByPrimaryKey(meal_type_no);
	}

	public List<MealTypeVO> getAll() {
		return dao.getAll();
	}
}
