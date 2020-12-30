package com.meal.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.meal.model.MealService;
import com.meal.model.MealVO;
import com.mealtype.model.MealTypeService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MealServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		
		if ("insert_meal".equals(action)) {
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
				
				String meal_name = req.getParameter("meal_name").trim();
				if(meal_name == null || meal_name.trim().isEmpty()) {
					errorMsgs.add("餐點名稱不得留空");
				}
				
				
				Integer price = null;
				try {
				 price = new Integer(req.getParameter("price"));
				}catch(Exception e) {
					errorMsgs.add("單價格式錯誤");
				}
				
				Part part = req.getPart("meal_pic");
				InputStream in = part.getInputStream();
				byte[] meal_pic = new byte[in.available()];
				in.read(meal_pic);
				in.close();
				String meal_info = req.getParameter("meal_info").trim();
				Integer making_time = new Integer(req.getParameter("making_time").trim());

				MealVO mealVO = new MealVO();
				mealVO.setMeal_type_no(meal_type_no);
				mealVO.setMeal_name(meal_name);
				mealVO.setPrice(price);
				mealVO.setMeal_pic(meal_pic);
				mealVO.setMeal_info(meal_info);
				mealVO.setMaking_time(making_time);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("mealVO", mealVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/meal/mealInfo.jsp");
					failureView.forward(req, res);
					return;
				}

				MealService mealSvc = new MealService();
				mealVO = mealSvc.addMeal(meal_type_no, meal_name, price, meal_pic, meal_info, making_time);

				String url = "/backend/meal/mealInfo.jsp";
				RequestDispatcher dispatcher = req.getRequestDispatcher(url);
				dispatcher.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if ("update_meal".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String meal_no = req.getParameter("update-meal-no").trim();
				
				String meal_type_no = req.getParameter("update-mealtype-no").trim();
				String meal_type_noReg = "TYP" + "[0-9]{2}$";
				if(meal_type_no == null || meal_type_no.trim().isEmpty()) {
					errorMsgs.add("種類編號不得留空");
				}
				else if(!meal_type_no.trim().matches(meal_type_noReg)) {
					errorMsgs.add("種類編號格式錯誤");
				}
				
				String meal_name = req.getParameter("update-mealname").trim();
				if(meal_name == null || meal_name.trim().isEmpty()) {
					errorMsgs.add("餐點名稱不得留空");
				}
				
				Integer price = null;
				try {
				 price = new Integer(req.getParameter("update-price"));
				}catch(Exception e) {
					errorMsgs.add("單價格式錯誤");
				}
				
				Part part = req.getPart("update-mealpic");
				InputStream in = part.getInputStream();
				byte[] meal_pic = new byte[in.available()];
				in.read(meal_pic);
				in.close();
				String meal_info = req.getParameter("update-mealinfo").trim();
				Integer making_time = new Integer(req.getParameter("update-makingtime").trim());
				String meal_status = req.getParameter("update-mealstatus").trim();

				MealVO mealVO = new MealVO();
				mealVO.setMeal_type_no(meal_type_no);
				mealVO.setMeal_name(meal_name);
				mealVO.setPrice(price);
				mealVO.setMeal_pic(meal_pic);
				mealVO.setMeal_info(meal_info);
				mealVO.setMaking_time(making_time);
				mealVO.setMeal_status(meal_status);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("mealVO", mealVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/meal/mealInfo.jsp");
					failureView.forward(req, res);
					return;
				}

				MealService mealSvc = new MealService();
				mealVO = mealSvc.updateMeal(meal_no, meal_type_no, meal_name, price, meal_pic, meal_info, making_time,
						meal_status);

				req.setAttribute("mealVO", mealVO);

				String url = "/backend/meal/mealInfo.jsp";
				RequestDispatcher dispatcher = req.getRequestDispatcher(url);
				dispatcher.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if("update-allmealstatus".equals(action)) {
			try {
				String meal_status = req.getParameter("meal_status");
				
				MealVO mealVO = new MealVO();
				mealVO.setMeal_status(meal_status);
				
				MealService mealSvc = new MealService();
				mealVO = mealSvc.updateMealStatus(meal_status);
				req.setAttribute("mealVO", mealVO);
				String url = "/backend/meal/mealInfo.jsp";
				RequestDispatcher dispatcher = req.getRequestDispatcher(url);
				dispatcher.forward(req, res);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if("on".equals(action)) {
			try {
				String meal_no = req.getParameter("on").trim();
				
				MealVO mealVO = new MealVO();
				mealVO.setMeal_no(meal_no);
				
				MealService mealSvc = new MealService();
				mealVO = mealSvc.updateOffMealStatus(meal_no);
				req.setAttribute("mealVO", mealVO);
				String url = "/backend/meal/mealInfo.jsp";
				RequestDispatcher dispatcher = req.getRequestDispatcher(url);
				dispatcher.forward(req, res);
				}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if("off".equals(action)) {
			try {
				String meal_no = req.getParameter("off").trim();
				
				MealVO mealVO = new MealVO();
				mealVO.setMeal_no(meal_no);
				
				MealService mealSvc = new MealService();
				mealVO = mealSvc.updateOnMealStatus(meal_no);
				req.setAttribute("mealVO", mealVO);
				String url = "/backend/meal/mealInfo.jsp";
				RequestDispatcher dispatcher = req.getRequestDispatcher(url);
				dispatcher.forward(req, res);
				}catch(Exception e) {
				e.printStackTrace();
			}
		}

		if ("delete_meal".equals(action)) {
			try {
				String meal_no = req.getParameter("delete-meal-no").trim();

				MealService mealSvc = new MealService();
				mealSvc.deleteMeal(meal_no);

				String url = "/backend/meal/mealInfo.jsp";
				RequestDispatcher dispatcher = req.getRequestDispatcher(url);
				dispatcher.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if("view_mealpic".equals(action)) {
		res.setContentType("image/gif, image/jpeg, image/png, image/jpg");
		InputStream is = null;
		String meal_no = req.getParameter("meal_no");
		
		MealService mealSvc = new MealService();
		MealVO mealVO = mealSvc.getOnePic(meal_no);
		byte[] meal_pic = mealVO.getMeal_pic();
		if(meal_pic != null) {
		res.getOutputStream().write(meal_pic);
		}else {
			is = req.getServletContext().getResourceAsStream("/img/nodata.png");
			byte[] pic = new byte[is.available()];
			is.read(pic);
			res.getOutputStream().write(pic);
			is.close();
		}
		}
	}
}
