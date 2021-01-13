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
import com.members.model.MembersVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 500 * 1024 * 1024, maxRequestSize = 500 * 5 * 1024
		* 1024)
public class ChangingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		CartService cartSvcMb = new CartService();
		ItemService itemSvc = new ItemService();

		HttpSession session = req.getSession();
		MembersVO member = (MembersVO) session.getAttribute("member");
		String mb_id = null;
		String sessionID = null;
		if (member != null) {
			mb_id = member.getMb_id();
			System.out.println("mb_id = " + mb_id);
		} else {
			sessionID = (String) session.getAttribute("sessionID");
			System.out.println("sessionID = " + sessionID);
		}
		String action = req.getParameter("action");

		JSONObject output = new JSONObject();
		JSONObject jsonPoi = new JSONObject();
		List<ItemVO> RedisBuylist = (List<ItemVO>) cartSvcMb.getAllItem_noByMb_id(mb_id);
		List<ItemVO> buylist = (List<ItemVO>) cartSvcMb.getAllItem_noBysessionID(sessionID);

		// 增加購物車商品
		if (action.equals("AddQty")) {
			System.out.println("AddQty開始");

			if (mb_id != null) {
				String item_no = req.getParameter("item_no");
				int index = Integer.parseInt(req.getParameter("index"));
				double item_price = Double.parseDouble(req.getParameter("item_price"));

				CartService cartSvc = new CartService();
				int quantity = cartSvc.getValueByItem_no(mb_id, item_no);

				System.out.println("會員編號=" + mb_id + ",商品編號=" + item_no + ",數量=" + (quantity + 1) + ",取得(單項)=" + index);
				cartSvcMb.replace(mb_id, item_no, quantity + 1);

				try {
					output.put("amount", item_price * (quantity + 1));
				} catch (JSONException e) {
					e.printStackTrace();
				}

				res.setContentType("text/plain");
				PrintWriter out = res.getWriter();
				System.out.println(output);
				out.write(output.toString());
				out.flush();
				out.close();
			} else {
				String item_no = req.getParameter("item_no");
				int index = Integer.parseInt(req.getParameter("index"));
				double item_price = Double.parseDouble(req.getParameter("item_price"));

				CartService cartSvc = new CartService();
				int quantity = cartSvc.getValueByItem_noCo(sessionID, item_no);

				System.out.println(
						"sessionID=" + sessionID + ",商品編號=" + item_no + ",數量=" + (quantity + 1) + ",index=" + index);
				cartSvcMb.replaceCo(sessionID, item_no, quantity + 1);

				try {
					output.put("amount", item_price * (quantity + 1));
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
		}

		// 減少購物車商品
		else if (action.equals("SubQty")) {
			System.out.println("SubQty開始");

			if (mb_id != null) {
				String item_no = req.getParameter("item_no");
				int index = Integer.parseInt(req.getParameter("index"));
				double item_price = Double.parseDouble(req.getParameter("item_price"));
				CartService cartSvc = new CartService();
				int quantity = cartSvc.getValueByItem_no(mb_id, item_no);
				// 刪除數量0的商品
				if (quantity == 1) {
					System.out.println("delete");
					cartSvcMb.deleteCart(mb_id, item_no, quantity);
					try {
						output.put("index", index);
					} catch (JSONException e) {
						e.printStackTrace();
					}
					// 減少購物車商品
				} else {
					System.out
							.println("會員編號=" + mb_id + ",商品編號=" + item_no + ",數量=" + (quantity - 1) + ",index" + index);
					cartSvcMb.replace(mb_id, item_no, quantity - 1);

					try {
						output.put("amount", item_price * (quantity - 1));
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
			} else {
				String item_no = req.getParameter("item_no");
				int index = Integer.parseInt(req.getParameter("index"));
				double item_price = Double.parseDouble(req.getParameter("item_price"));
				CartService cartSvc = new CartService();
				int quantity = cartSvc.getValueByItem_noCo(sessionID, item_no);
				// 刪除數量0的商品
				if (quantity == 1) {
					System.out.println("delete");
					cartSvcMb.deleteCartCo(sessionID, item_no, quantity);
					try {
						output.put("index", index);
					} catch (JSONException e) {
						e.printStackTrace();
					}
					// 減少購物車商品
				} else {
					System.out.println(
							"sessionID=" + sessionID + ",商品編號=" + item_no + ",數量=" + (quantity - 1) + ",index" + index);
					cartSvcMb.replaceCo(sessionID, item_no, quantity - 1);

					try {
						output.put("amount", item_price * (quantity - 1));
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

		else if (action.equals("BoxSelect")) {
			System.out.println("BoxSelect開始");

			if (mb_id != null) {
				String boxselect = req.getParameter("boxselect");
				System.out.println("boxselect=" + boxselect);
				Double amount = Double.parseDouble(req.getParameter("amount"));
				System.out.println("amount=" + amount);

				if (boxselect.equals("true")) {
					System.out.println("進true");

					try {
						output.put("amount", amount);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				} else {
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
			} else {
				String boxselect = req.getParameter("boxselect");
				System.out.println("boxselect=" + boxselect);
				Double amount = Double.parseDouble(req.getParameter("amount"));
				System.out.println("amount=" + amount);

				if (boxselect.equals("true")) {
					System.out.println("進true");

					try {
						output.put("amount", amount);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				} else {
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
		//改變結帳積分
		else if (action.equals("changePointAct")) {
			System.out.println("結帳積分開始");
			Integer poCaUs = Integer.parseInt(req.getParameter("poCaUs"));
			Integer oraPri = Integer.parseInt(req.getParameter("oraPri"));
			Integer poiUs = Integer.parseInt(req.getParameter("poiUs"));
			
		}
	}
}
