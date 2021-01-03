package com.mealorderdetail.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mealorderdetail.model.MealOrderDetailService;
import com.mealorderdetail.model.MealOrderDetailVO;

public class MealOrderDetailServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("update_meal_orderdetail".equals(action)) {
			String meal_odno =req.getParameter("update-meal-odno");
			String meal_no = req.getParameter("update-meal-no");
			Integer qty = new Integer(req.getParameter("update-qty"));
			
			MealOrderDetailVO mealOrderDetailVO = new MealOrderDetailVO();
			mealOrderDetailVO.setMeal_odno(meal_odno);
			mealOrderDetailVO.setMeal_no(meal_no);
			mealOrderDetailVO.setQty(qty);
			
			MealOrderDetailService mealOrderDetailSvc = new MealOrderDetailService();
			mealOrderDetailSvc.updateMealOrderDetail(meal_odno, meal_no, qty);
			
			req.setAttribute("mealOrderDetailVO", mealOrderDetailVO);
			String url = "/backend/mealorderdetail/mealOrderDetailInfo.jsp";
			RequestDispatcher dispatcher = req.getRequestDispatcher(url);
			dispatcher.forward(req, res);
		}
		
		if("getOneMealOrderDetail".equals(action)) {
			try {
				String meal_odno = req.getParameter("meal_odno");
				
				MealOrderDetailVO mealOrderDetailVO = new MealOrderDetailVO();
				mealOrderDetailVO.setMeal_odno(meal_odno);
				
				MealOrderDetailService mealOrderDetailSvc = new MealOrderDetailService();
				mealOrderDetailVO = mealOrderDetailSvc.getOneMealOrderDetail(meal_odno);
				
				req.setAttribute("mealOrderDetailVO", mealOrderDetailVO);
				String url = "/backend/mealorderdetail/mealOrderDetailInfo.jsp";
				RequestDispatcher dispatcher = req.getRequestDispatcher(url);
				dispatcher.forward(req, res);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
