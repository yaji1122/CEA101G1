package com.mealtype.model;

import java.util.List;

public interface MealTypeDAO_interface {
	 public void insert(MealTypeVO mealTypeVO);
     public void update(MealTypeVO mealTypeVO);
     public void delete(String meal_type_no);
     public MealTypeVO findByPrimaryKey(String meal_type_no);
     public List<MealTypeVO> getAll();
}
