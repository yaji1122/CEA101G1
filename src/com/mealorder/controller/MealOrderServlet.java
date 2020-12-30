package com.mealorder.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mealorder.model.MealOrderService;
import com.mealorder.model.MealOrderVO;

public class MealOrderServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("insert_meal_order".equals(action)) {
			try {
			String mb_id = req.getParameter("mb_id");
			String rm_no = req.getParameter("rm_no");
			Integer total_price = new Integer(req.getParameter("total_price"));
			
			MealOrderVO mealOrderVO = new MealOrderVO();
			mealOrderVO.setMb_id(mb_id);
			mealOrderVO.setRm_no(rm_no);
			mealOrderVO.setTotal_price(total_price);
			
			MealOrderService mealOrderSvc = new MealOrderService();
			mealOrderVO = mealOrderSvc.addMealOrder(mb_id, rm_no, total_price);
			
			String url = "/backend/mealorder/mealOrderInfo.jsp";
			RequestDispatcher dispatcher = req.getRequestDispatcher(url);
			dispatcher.forward(req, res);
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		
		if("update_meal_order".equals(action)) {
			try {
				String meal_odno = req.getParameter("update-mealorder-no");
				String rm_no = req.getParameter("update-rmno");
				Integer total_price = new Integer(req.getParameter("update-totalprice"));
				String od_status = req.getParameter("update-odstatus");
				
				MealOrderVO mealOrderVO = new MealOrderVO();
				mealOrderVO.setMeal_odno(meal_odno);
				mealOrderVO.setRm_no(rm_no);
				mealOrderVO.setTotal_price(total_price);
				mealOrderVO.setOd_status(od_status);
				
				MealOrderService mealOrderSvc = new MealOrderService();
				mealOrderVO = mealOrderSvc.updateMealOrder(meal_odno, rm_no, total_price, od_status);
				
				req.setAttribute("mealOrderVO", mealOrderVO);
				String url = "/backend/mealorder/mealOrderInfo.jsp";
				RequestDispatcher dispatcher = req.getRequestDispatcher(url);
				dispatcher.forward(req, res);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if("delete_meal_order".equals(action)) {
			try {
				String meal_odno = req.getParameter("delete-mealorder-no").trim();
				
				MealOrderService mealOrderSvc = new MealOrderService();
				mealOrderSvc.deleteMealOrder(meal_odno);
				
				String url = "/backend/mealorder/mealOrderInfo.jsp";
				RequestDispatcher dispatcher = req.getRequestDispatcher(url);
				dispatcher.forward(req, res);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}	
	}
}