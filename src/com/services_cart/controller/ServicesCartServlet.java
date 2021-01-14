package com.services_cart.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.services_cart.model.ServicesItem;

@WebServlet("/ServicesCartServlet")
public class ServicesCartServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ServicesCartServlet() {
		super();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		HttpSession session = req.getSession();
		Vector<ServicesItem> buylist = (Vector<ServicesItem>) session.getAttribute("shoppingcart");

		if (!action.equals("CHECKOUT")) {

			// 刪除購物車
			if (action.equals("DELETE")) {
				String del = req.getParameter("del");
				int d = Integer.parseInt(del);
				buylist.removeElementAt(d);
			}
			// 新增至購物車中
			else if (action.equals("ADD")) {
				boolean match = false;

				// 取得後來新增
				ServicesItem aServicesItem = getServicesItem(req);

				// 新增第一個服務至購物車時
				if (buylist == null) {
					buylist = new Vector<ServicesItem>();
					buylist.add(aServicesItem);
				} else {
					for (int i = 0; i < buylist.size(); i++) {
						ServicesItem servicesItem = buylist.get(i);

						// 假若新增的服務和購物車的服務一樣時
						if (servicesItem.getServicesNo().equals(aServicesItem.getServicesNo())) {
							servicesItem.setServicesNo(servicesItem.getServicesNo());
							servicesItem.setQuantity(servicesItem.getQuantity() + aServicesItem.getQuantity());
							servicesItem.setPrice(servicesItem.getPrice() + aServicesItem.getPrice());
							buylist.setElementAt(servicesItem, i);
							match = true;
						} // end of if name matches
					} // end of for

					// 假若新增的服務和購物車的服務不一樣時
					if (!match)
						buylist.add(aServicesItem);
				}
			}

			session.setAttribute("shoppingcart", buylist);
			String url = "/frontend/services/services.jsp?serv_type_no=1";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}

		// 結帳，計算購物車服務價錢總數
		else if (action.equals("CHECKOUT")) {
			Integer total = 0;
			for (int i = 0; i < buylist.size(); i++) {
				ServicesItem order = buylist.get(i);
				int price = order.getPrice();
				int quantity = order.getQuantity();
				total += (price * quantity);
			}
			String amount = String.valueOf(total);
			req.setAttribute("amount", amount);
			String url = "/frontend/services/servicesCheckout.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}
	}

	private ServicesItem getServicesItem(HttpServletRequest req) {

		String servicesName = req.getParameter("servicesName");
		Integer quantity = (new Integer(req.getParameter("quantity")).intValue());
		String servicesNo = req.getParameter("servicesNo");
		Integer price = (new Integer(req.getParameter("price")).intValue());
		String locations = req.getParameter("locations");
		Integer unitPrice = new Integer(quantity * price);

		String str = req.getParameter("hiredate");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime servTime = LocalDateTime.parse(str, formatter);
		System.out.print("cart:" + servTime);

		ServicesItem theServicesItem = new ServicesItem();

		theServicesItem.setServicesName(servicesName);
		theServicesItem.setServicesNo(servicesNo);
		theServicesItem.setPrice(price);
		theServicesItem.setQuantity(quantity);
		theServicesItem.setLocations(locations);
		theServicesItem.setServTime(servTime);
		theServicesItem.setUnitPrice(unitPrice);
		return theServicesItem;
	}
}
