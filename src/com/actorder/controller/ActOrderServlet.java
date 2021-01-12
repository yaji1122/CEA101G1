package com.actorder.controller;

import java.io.*;
import java.time.LocalTime;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.act.model.*;
import com.actorder.model.*;
import com.bookingorder.model.*;

@MultipartConfig
public class ActOrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) {
			try {
				String actOdno = req.getParameter("actOdno");
				ActOrderService actOrderSvc = new ActOrderService();
				ActOrderVO actOrderVO = actOrderSvc.getOneActOrder(actOdno);
				List<ActOrderVO> actOrderList = new ArrayList<>();
				actOrderList.add(actOrderVO);
				req.setAttribute("actOrderList", actOrderList);
				String url = "/backend/actorder/actorderInfo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if ("getOne_For_Update".equals(action)) {
			try {
				String actOdno = req.getParameter("actOdno");
				ActOrderService actOrderSvc = new ActOrderService();
				ActOrderVO actOrderVO = actOrderSvc.getOneActOrder(actOdno);
				req.setAttribute("actOrderVO", actOrderVO);
				String url = "/backend/actorder/act_order_update.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if ("update".equals(action)) {
			res.setContentType("utf-8");
			PrintWriter out = res.getWriter();
			try {
				String actOdno = new String(req.getParameter("actOdno").trim());
				String odStatus = req.getParameter("order_status");
				String pplstr = req.getParameter("ppl").trim();
				Integer ppl = null;
				try {
					ppl = new Integer(pplstr);
				} catch (Exception e) {
					out.print("請輸入數字");
					return;
				}
				
				String actNo = req.getParameter("actNo");
				ActService actSvc = new ActService();
				ActVO act = actSvc.getOneAct(actNo);
				Integer total_price = ppl * act.getActPrice();
				ActOrderService actOrderSvc = new ActOrderService();
				actOrderSvc.updateActOrder(actOdno, odStatus, ppl, total_price);
				out.print("success");
			} catch (Exception e) {
				e.printStackTrace();
				out.print("fail");
			}
		}

		if ("getAllByOdStatus".equals(action)) {
			try {
				String odStatus = req.getParameter("odstatus");
				ActOrderService actOrderSvc = new ActOrderService();
				List<ActOrderVO> actOrderList = actOrderSvc.getAllByStatus(odStatus);
				req.setAttribute("actOrderList", actOrderList);
				String url = "/backend/actorder/actorderInfo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if ("insert".equals(action)) { // �Ӧ�addAct.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			res.setContentType("utf-8");
			PrintWriter out = res.getWriter();
			System.out.println(action);

			try {
				String actOdno = new String(req.getParameter("actOdno").trim());
				if (actOdno == null || actOdno.trim().length() == 0) {
					errorMsgs.add("活動單號不可為空");
				} else if (actOdno.equals("actOdno")) {
					errorMsgs.add("單號重複");
				}

				String odStatus = req.getParameter("odStatus");
				if (odStatus == null || odStatus.trim().length() == 0) {
					errorMsgs.add("請選擇活動狀態");
				}

				String actNo = req.getParameter("actNo");
				if (actNo == null || actNo.trim().length() == 0) {
					errorMsgs.add("找不到該活動");
				}

				String ppl = req.getParameter("ppl");
				if (ppl == null || ppl.trim().length() == 0) {
					errorMsgs.add("請選擇參加人數");
				}
				Integer Numppl = null;
				try {
					Numppl = new Integer(ppl);
				} catch (Exception e) {
					errorMsgs.add("請選擇參加人數");
				}
	
				String bkNo = req.getParameter("bkNo").trim();
				BookingOrderService bookOrderSvc = new BookingOrderService();
				BookingOrderVO bookingOrderVO = bookOrderSvc.getOneByBkNo(bkNo);
				if (bkNo == null || bkNo.trim().length() == 0) {
					errorMsgs.add("無此訂房單號");
				}
				
				
				String actOrderTime = req.getParameter("odTime");
				LocalTime odTime = LocalTime.parse(actOrderTime);


				String totalPrice = req.getParameter("totalPrice");
				if (totalPrice == null || totalPrice.trim().length() == 0) {
					errorMsgs.add("總價格不可為零");
				}
				Integer total_price = null;
				try {
					total_price = new Integer(totalPrice);
				} catch (Exception e) {
					errorMsgs.add("總價格錯誤");
				}

				ActOrderVO actOrderVO = new ActOrderVO();
				
				actOrderVO.setActOdno(actOdno);
				actOrderVO.setActNo(actNo);
				actOrderVO.setBkNo(bkNo);
				actOrderVO.setOdTime(odTime);
				actOrderVO.setOdStatus(odStatus);
				actOrderVO.setPpl(Numppl);
				actOrderVO.setTotalPrice(total_price);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					StringBuilder str = new StringBuilder();
					errorMsgs.stream().forEach(e -> str.append(e + ", "));
					out.print(str.toString());
					System.out.print(str);
					return;
				}

				ActOrderService ActOrderSvc = new ActOrderService();
				ActOrderSvc.addActOrder(actOdno, actNo, bkNo, odTime, odStatus, Numppl, total_price);
				out.print("success");
			} catch (Exception e) {
				e.printStackTrace();
				out.print("fail");
			}
		} 
	
	}
}
