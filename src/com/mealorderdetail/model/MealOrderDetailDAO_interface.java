package com.mealorderdetail.model;

import java.util.List;

import com.mealorderdetail.model.MealOrderDetailVO;

public interface MealOrderDetailDAO_interface {
	public void insert(MealOrderDetailVO mealOrderDetailVO);
	public void update(MealOrderDetailVO mealOrderDetailVO);
	public void delete(String meal_odno);
	public MealOrderDetailVO findByMealOdno(String meal_odno);
    public List<MealOrderDetailVO> getAll();
}
