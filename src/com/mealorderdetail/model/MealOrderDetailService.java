package com.mealorderdetail.model;

import java.sql.Connection;
import java.util.List;

import org.json.JSONObject;

import com.mealorder.model.MealOrderVO;

public class MealOrderDetailService {

	private MealOrderDetailDAO_interface dao;

	public MealOrderDetailService() {
		dao = new MealOrderDetailDAO();
	}

		public MealOrderDetailVO addMealOrderDetail(String meal_odno, String meal_no, Integer price, Integer qty, Connection con) {
		MealOrderDetailVO mealOrderDetailVO = new MealOrderDetailVO();	
		mealOrderDetailVO.setMeal_odno(meal_odno);
		mealOrderDetailVO.setMeal_no(meal_no);
		mealOrderDetailVO.setPrice(price);
		mealOrderDetailVO.setQty(qty);
		dao.insert(mealOrderDetailVO, con);

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
	
	public List<MealOrderDetailVO> getAllByOdno(String meal_odno){
		return dao.getAllByOdno(meal_odno);
	}
}
