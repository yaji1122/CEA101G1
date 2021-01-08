package com.mealorder.model;

import java.util.List;

import org.json.JSONObject;

import com.mealorderdetail.model.MealOrderDetailVO;


public interface MealOrderDAO_interface {
	public void insert(MealOrderVO mealOrderVO);
	public void update(MealOrderVO mealOrderVO);
	public void delete(String meal_odno);
	public MealOrderVO findByPrimaryKey(String meal_odno);
    public List<MealOrderVO> getAll();
    public List<MealOrderVO> getAllByBkNo(String bk_no);
    public void insertWithDetails(MealOrderVO mealOrderVO, List<MealOrderDetailVO> meallist);
}
