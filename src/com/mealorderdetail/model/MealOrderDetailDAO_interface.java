package com.mealorderdetail.model;

import java.sql.Connection;
import java.util.List;

import org.json.JSONObject;

import com.mealorder.model.MealOrderVO;
import com.mealorderdetail.model.MealOrderDetailVO;

public interface MealOrderDetailDAO_interface {
	public void insert(MealOrderDetailVO mealOrderDetailVO, Connection con);
	public void update(MealOrderDetailVO mealOrderDetailVO);
	public void delete(String meal_odno);
	public MealOrderDetailVO findByMealOdno(String meal_odno);
    public List<MealOrderDetailVO> getAll();
    public List<MealOrderDetailVO>getAllByOdno(String meal_odno);
}
