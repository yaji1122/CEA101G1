package com.mealorder.model;

import java.util.List;

public interface MealOrderDAO_interface {
	public void insert(MealOrderVO mealOrderVO);
	public void update(MealOrderVO mealOrderVO);
	public void delete(String meal_odno);
	public MealOrderVO findByPrimaryKey(String meal_odno);
    public List<MealOrderVO> getAll();
}
