package com.payment.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.*;

import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;

import com.payment.model.*;

@MultipartConfig
@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text; charset=utf-8");
		PrintWriter out = res.getWriter();
		String action = req.getParameter("action").trim();

		if ("insert_credit_card".equals(action)) { 
			try {
				String mb_id = req.getParameter("mb_id");
				String card_no = req.getParameter("card_no").trim();
				String card_name = req.getParameter("card_name").trim();
				String exp_mon = req.getParameter("exp_mon").trim();
				String exp_year = req.getParameter("exp_year").trim();
				String csc = req.getParameter("csc").trim();
				PaymentService paySvc = new PaymentService();
				PaymentVO payvo = paySvc.insertCrdt(mb_id, card_no, card_name, exp_mon, exp_year, csc);
				JSONObject json = new JSONObject();
				json.put("status", "success");
				json.put("payno", payvo.getPay_no());
				out.print(json.toString());
				return;
			} catch (Exception e) {
				e.printStackTrace(System.err);
				out.print("fail");
			}
		}

		if ("delete_credit_card".equals(action)) {
			try {
				String pay_no = req.getParameter("pay_no");
				PaymentService paySvc = new PaymentService();
				paySvc.deleteCrdt(pay_no);
				out.print("success");
				return;
			} catch (Exception e) {
				e.printStackTrace();
				out.print("fail");
			}
		}
		
		if ("getall_crdt_by_mbid".equals(action)) {
			RequestDispatcher dispatcher = req.getRequestDispatcher(req.getContextPath() + "/backend/payment/paymentInfo.jsp");
			try {
				String mb_id = req.getParameter("mb_id");
				PaymentService paySvc = new PaymentService();
				req.setAttribute("creditCars", paySvc.getAllByMbId(mb_id));
				req.setAttribute("msg", "已成功移除資料");
				dispatcher.forward(req, res);
				return;
			} catch (Exception e) {
				e.printStackTrace();
				req.setAttribute("msg", "移除資料失敗");
				dispatcher.forward(req, res);
			}
		}
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

}
