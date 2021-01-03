package com.mealorder.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.cart.model.CartItem;
import com.mealorder.model.MealOrderDAO;
import com.mealorder.model.MealOrderService;
import com.mealorder.model.MealOrderVO;
import com.mealorderdetail.model.MealOrderDetailVO;
import com.members.model.MembersVO;
import com.rooms.model.RoomsVO;

public class MealOrderServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		
		if("insert_meal_order".equals(action)) {
			try {
//			MembersVO member = (MembersVO) session().getAttribute("member");
			String mb_id = "MEM0000001";
//			RoomsVO room = (RoomsVO)session().getAttribute("room");
			String rm_no = "101";
			Integer total_price = new Integer(req.getParameter("amount"));
			
			MealOrderVO mealOrderVO = new MealOrderVO();
			mealOrderVO.setMb_id(mb_id);
			mealOrderVO.setRm_no(rm_no);
			mealOrderVO.setTotal_price(total_price);
			
			@SuppressWarnings("unchecked")
			List<CartItem> buylist = (List<CartItem>) session.getAttribute("cart");
			List<MealOrderDetailVO> meallist = new ArrayList<MealOrderDetailVO>();
			
			for(int index = 0; index < buylist.size(); index++) {
				MealOrderDetailVO mealOrderDetailVO = new MealOrderDetailVO();
				CartItem order = buylist.get(index);
				String meal_no = order.getItem_no();
				Integer qty = order.getQuantity();
				Integer price = order.getPrice() / order.getQuantity();
				System.out.println("編號: " + meal_no + "數量: " + qty + "單價: " + price);
				mealOrderDetailVO.setMeal_no(meal_no);
				mealOrderDetailVO.setQty(qty);
				mealOrderDetailVO.setPrice(price);
				meallist.add(mealOrderDetailVO);
				System.out.println("更新: " + meallist.size());
			}
				
			MealOrderService mealOrderSvc = new MealOrderService();
			mealOrderSvc.insertWithDetails(mealOrderVO, meallist);
						
			session.removeAttribute("cart");
			String url = "/frontend/meal/meal.jsp";
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