package com.meal.model;

import java.util.List;

public interface MealDAO_interface {
	 public void insert(MealVO mealVO);
     public void update(MealVO mealVO);
     public void updateMealStatus(String meal_status);
     public void delete(String meal_no);
     public MealVO findByPrimaryKey(String meal_no);
     public MealVO getOnePic(String meal_no);
     public List<MealVO> getAll();
     public List<MealVO>getActiveMeal(String meal_status);     
}
