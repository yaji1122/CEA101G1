package com.cart.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cart.model.CartItem;
import com.meal.model.MealService;
import com.meal.model.MealVO;

public class AddToCartServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		Vector<CartItem> buylist = (Vector<CartItem>) session.getAttribute("cart");
		String action = req.getParameter("action");

			if ("DELETE".equals(action)) {
				String del = req.getParameter("del");
				int d = Integer.parseInt(del);
				buylist.removeElementAt(d);
				session.setAttribute("cart", buylist);
			}
			
			if ("addToCart".equals(action)) {
				boolean match = false;
				CartItem acartItem = getCartItem(req);

				if (buylist == null) {
					buylist = new Vector<CartItem>();
					buylist.add(acartItem);
				} else {
					for (int i = 0; i < buylist.size(); i++) {
						CartItem cartItem = buylist.get(i);
						if (cartItem.getItem_name().equals(acartItem.getItem_name())) {
							cartItem.setItem_no(cartItem.getItem_no());
							cartItem.setQuantity(cartItem.getQuantity() + acartItem.getQuantity());
							cartItem.setPrice(cartItem.getPrice() + acartItem.getPrice());
							buylist.setElementAt(cartItem, i);
							match = true;
						}
					}

					if (!match) {
						buylist.add(acartItem);
					}
				}
				session.setAttribute("cart", buylist);
			}
			
			if("RESET".equals(action)) {
				buylist.removeAllElements();
				session.setAttribute("cart", buylist);
			}
			
			if("changeQuantity".equals(action)){
				String itemNo = req.getParameter("itemNo");
				Integer newVal = new Integer(req.getParameter("newVal"));
				Integer itemPrice = new Integer(req.getParameter("itemPrice"));
				for(int i = 0; i < buylist.size(); i++) {
					CartItem order = buylist.get(i);
					if(order.getItem_no().equals(itemNo)) {
						order.setQuantity(newVal);
						order.setPrice(itemPrice);
					}
				}
				session.setAttribute("cart", buylist);
			}
	}
	
	

	private CartItem getCartItem(HttpServletRequest req) {
		String item_no = req.getParameter("item_no");
		String item_name = req.getParameter("item_name");
		Integer quantity = (new Integer(req.getParameter("quantity")).intValue());
		Integer price = (new Integer(req.getParameter("price")).intValue());
		CartItem ci = new CartItem();
		ci.setItem_no(item_no);
		ci.setItem_name(item_name);
		ci.setQuantity(quantity);
		ci.setPrice(price);
		return ci;
	}
}