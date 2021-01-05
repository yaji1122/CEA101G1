package com.actreserve.controller;

import java.util.*;
import java.io.*;
import java.sql.Date;

import javax.servlet.*;
import javax.servlet.http.*;
import com.actreserve.model.*;

public class ActReserveServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		
		@SuppressWarnings("unchecked")
		List<ActReserve> actlist = (Vector<ActReserve>) session.getAttribute("ActReserve");
		String action = req.getParameter("action");

		if (!action.equals("CHECKOUT")) {

			// 刪除購物車中活動
			if (action.equals("DELETE")) {
				String del = req.getParameter("del");
				int d = Integer.parseInt(del);
				actlist.remove(d);
			}
			// 新增活動至購物車中
			else if (action.equals("ADD")) {
				// 取得後來新增的活動
				ActReserve actreserve = getAct(req);

				if (actlist == null) {
					actlist = new Vector<ActReserve>();
					actlist.add(actreserve);
				} else {
					if (actlist.contains(actreserve)) {
						ActReserve innerAct = actlist.get(actlist.indexOf(actreserve));
						innerAct.setParticipant(innerAct.getParticipant() + actreserve.getActPrice());
					} else {
						actlist.add(actreserve);
					}
				}
			}

			session.setAttribute("shoppingcart", actlist);
			String url = "<%=request.getContextPath()%>/frontend/activity/Act_Checkout.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}
		
		// 結帳，計算購物車活動價錢總數
				else if (action.equals("CHECKOUT")) {
					Integer total = 0;
					for (int i = 0; i < actlist.size(); i++) {
						ActReserve actOrder = actlist.get(i);
						Integer price = actOrder.getActPrice();
						String ppl = actOrder.getParticipant(); //字串的人數
						
						Integer participant = null; //轉型至Int型態的人數
						try {
							participant = new Integer(ppl);
						}catch(Exception e) {
							e.printStackTrace();
						}
						
						total += (price * participant);
					}

					String act_total_price = String.valueOf(total);
					req.setAttribute("total_price", act_total_price);
					String url = "<%=request.getContextPath()%>/frontend/acttivity/Act_Checkout.jsp";
					RequestDispatcher rd = req.getRequestDispatcher(url);
					rd.forward(req, res);
				}
		
	}

	private ActReserve getAct(HttpServletRequest req) {

		String actNo = req.getParameter("actNo");
		String actEventNo = req.getParameter("actStatus");
		String actName = req.getParameter("actName");
		String actStatus = req.getParameter("actStatus");
		Date actRegTime = java.sql.Date.valueOf(req.getParameter("actRegTime").trim());
		Date actDate = java.sql.Date.valueOf(req.getParameter("actDate").trim());
		Date deadLine = java.sql.Date.valueOf(req.getParameter("deadLine").trim());
		String actTime = req.getParameter("actTime");
		String participant = req.getParameter("participant");
		String actPrice = req.getParameter("actPrice");

		ActReserve actReserve = new ActReserve();
		actReserve.setActNo(actNo);
		actReserve.setActEventNo(actEventNo);
		actReserve.setActName(actName);
		actReserve.setActStatus(actStatus);
		actReserve.setActRegTime(actRegTime);
		actReserve.setActDate(actDate);
		actReserve.setDeadLine(deadLine);
		actReserve.setActTime(actTime);
		actReserve.setParticipant(participant);
		actReserve.setActPrice((new Integer(actPrice)).intValue());

		return actReserve;
	}

}
