package com.shoppingCart.controller;
import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.shoppingCart.model.CartService;
import com.item.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 500 * 1024 * 1024, maxRequestSize = 500 * 5 * 1024 * 1024)
public class ChangingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
    
	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException,IOException {
		
		req.setCharacterEncoding("UTF-8");

		CartService cartSvcMb = new CartService();
		ItemService itemSvc = new ItemService();
		
		HttpSession session = req.getSession();
		String mb_id = (String) session.getAttribute("mb_id");
		String action = req.getParameter("action");

		JSONObject output = new JSONObject();		
		List<ItemVO> RedisBuylist = (List<ItemVO>) cartSvcMb.getAllItem_noByMb_id(mb_id);

		//增加購物車商品
		if (action.equals("AddQty")) {
			System.out.println("AddQty開始");

			String item_no = req.getParameter("item_no");
			int index = Integer.parseInt(req.getParameter("index"));
			double item_price = Double.parseDouble(req.getParameter("item_price"));
			
			CartService cartSvc=new CartService();
			int quantity=cartSvc.getValueByItem_no(mb_id, item_no);
			
			
			System.out.println("會員編號=" + mb_id +",商品編號=" + item_no + ",數量=" + (quantity+1) + ",取得(單項)=" + index);
			cartSvcMb.replace(mb_id, item_no, quantity+1);
			
			try {
				output.put("amount",item_price*(quantity+1));
			} catch (JSONException e) {
				e.printStackTrace();
			}

		    res.setContentType("text/plain");
		    PrintWriter out = res.getWriter();
		    System.out.println(output);
		    out.write(output.toString());
		    out.flush();
		    out.close();
		}
		
		//減少購物車商品
		else if (action.equals("SubQty")) {
			System.out.println("SubQty開始");

			String item_no = req.getParameter("item_no");
			int index = Integer.parseInt(req.getParameter("index"));
			double item_price = Double.parseDouble(req.getParameter("item_price"));
			CartService cartSvc=new CartService();
			int quantity=cartSvc.getValueByItem_no(mb_id, item_no);
			//刪除數量0的商品
			if(quantity==1) {
				System.out.println("delete");
				cartSvcMb.deleteCart(mb_id, item_no, quantity);	
				try {
					output.put("index", index);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			//減少購物車商品
			}else {
				System.out.println("會員編號=" + mb_id +",商品編號=" + item_no + ",數量=" + (quantity-1) + ",index" + index);
				cartSvcMb.replace(mb_id, item_no, quantity-1);
				
				try {
					output.put("amount",item_price*(quantity-1));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
				
		    res.setContentType("text/plain");
		    PrintWriter out = res.getWriter();
		    System.out.println(output);
		    out.write(output.toString());
		    out.flush();
		    out.close();
	    
		} 
//		else if(action.equals("BoxSelect")) {
//		
//		
//		
//		
//		
//		
//		}
		else if (action.equals("BoxSelect")) {
			System.out.println("BoxSelect開始");
			
			String boxselect = req.getParameter("boxselect");
			System.out.println("boxselect=" + boxselect);
			Double amount = Double.parseDouble(req.getParameter("amount"));
			System.out.println("amount=" + amount);

			if(boxselect.equals("true")) {
				System.out.println("進true");

				try {
					output.put("amount", amount);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}else {
				System.out.println("進false");
				try {
					output.put("amount", 0);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

		    res.setContentType("text/plain");
		    PrintWriter out = res.getWriter();
		    System.out.println(output);
		    out.write(output.toString());
		    out.flush();
		    out.close();
		}
	}	
}
