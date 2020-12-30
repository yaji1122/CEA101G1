package com.mealtype.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mealtype.model.MealTypeService;
import com.mealtype.model.MealTypeVO;
public class MealTypeServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("insert_meal_type".equals(action)) { 	
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {				
				String meal_type_no = req.getParameter("meal_type_no").trim();
				String meal_type_noReg = "TYP" + "[0-9]{2}$";
				if(meal_type_no == null || meal_type_no.trim().isEmpty()) {
					errorMsgs.add("種類編號不得留空");
				}
				else if(!meal_type_no.trim().matches(meal_type_noReg)) {
					errorMsgs.add("種類編號格式錯誤");
				}
				String type_name = req.getParameter("type_name").trim();
				if(type_name == null || type_name.trim().isEmpty()) {
					errorMsgs.add("種類名稱不得留空");
				}
						
				MealTypeVO mealTypeVO = new MealTypeVO();
				mealTypeVO.setMeal_type_no(meal_type_no);
				mealTypeVO.setType_name(type_name);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("mealTypeVO", mealTypeVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/mealtype/mealTypeInfo.jsp");
					failureView.forward(req, res);
					return;
				}

				MealTypeService mealTypeSvc = new MealTypeService();
				mealTypeVO = mealTypeSvc.addMealType(meal_type_no, type_name);
				
				String url = "/backend/mealtype/mealTypeInfo.jsp";
				RequestDispatcher dispatcher= req.getRequestDispatcher(url);
				dispatcher.forward(req, res);				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/mealtype/mealTypeInfo.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("update_meal_type".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
				try {
				String meal_type_no = req.getParameter("update-mealtype-no").trim();
				String type_name = req.getParameter("update-typename").trim();
				if(type_name == null || type_name.trim().isEmpty()) {
					errorMsgs.add("種類名稱不得留空");
				}
				
				MealTypeVO mealTypeVO = new MealTypeVO();
				mealTypeVO.setMeal_type_no(meal_type_no);
				mealTypeVO.setType_name(type_name);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("mealTypeVO", mealTypeVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/mealtype/mealTypeInfo.jsp");
					failureView.forward(req, res);
					return;
				}
				
				MealTypeService mealTypeSvc = new MealTypeService();
				mealTypeVO = mealTypeSvc.updateMealType(meal_type_no, type_name);
				
				req.setAttribute("mealTypeVO", mealTypeVO);
				String url = "/backend/mealtype/mealTypeInfo.jsp";
				RequestDispatcher dispatcher = req.getRequestDispatcher(url);
				dispatcher.forward(req, res);
			}catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/mealtype/mealTypeInfo.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("delete_meal_type".equals(action)) {
			try {
				String meal_type_no = req.getParameter("delete-mealtype-no").trim();
				
				MealTypeService mealTypeSvc = new MealTypeService();
				mealTypeSvc.deleteMealType(meal_type_no);
				
				String url = "/backend/mealtype/mealTypeInfo.jsp";
				RequestDispatcher dispatcher= req.getRequestDispatcher(url);
				dispatcher.forward(req, res);							
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
