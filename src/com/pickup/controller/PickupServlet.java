package com.pickup.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.pickup.model.*;

@MultipartConfig
@WebServlet("/PickupServlet")
public class PickupServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text; charset=utf-8");
		PrintWriter out = null;
		String action = req.getParameter("action").trim();
		
		if ("insert_pkup".equals(action)) {
			out = res.getWriter();
			try {
				String bk_no = req.getParameter("bk_no");
				String chop_no = req.getParameter("chop_no");
				String arriveDatetimeStr = req.getParameter("arrive_datetime");
				DateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mm");
				Timestamp arrive_datetime = new Timestamp(df.parse(arriveDatetimeStr).getTime());
				PickupService pkupSvc = new PickupService();
				PickupVO pkupvo = pkupSvc.addPkup(bk_no, chop_no, arrive_datetime);
				String pkup_no = pkupvo.getPkup_no();
				out.print(pkup_no);
			} catch (Exception e) {
				e.printStackTrace();
				out.print("fail");
				
			}
		}
		
		if ("update_pkup".equals(action)) {
			out = res.getWriter();
			try {
				String chop_no = req.getParameter("update-chopno");
				String arriveDatetimeStr = req.getParameter("update-arrivedatetime");
				String pkup_no = req.getParameter("update-pkupno");
				DateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mm");
				Timestamp arrive_datetime = new Timestamp(df.parse(arriveDatetimeStr).getTime());
				PickupService pkupSvc = new PickupService();
				pkupSvc.updatePkup(chop_no, arrive_datetime, pkup_no);
				out.print("success");
			} catch (Exception e) {
				e.printStackTrace();
				out.print("fail");
			}
		}
		
		if ("pkup_status_change".equals(action)) {
			out = res.getWriter();
			res.setContentType("text; charset=utf-8");
			try {
				String pkup_no = req.getParameter("pkup_no");
				String pkup_status = req.getParameter("pkup_status");
				PickupService pkupSvc = new PickupService();
				pkupSvc.customerArrive(pkup_no, pkup_status);
				out.print("success");
			} catch (Exception e) {
				e.printStackTrace();
				out.print("fail");
			}
		}
		
		if ("getAllByQuery".equals(action)) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/backend/pickup/pkupInfo.jsp");
			res.setContentType("text/html; charset=utf-8");
			List<PickupVO> pkupList = new LinkedList<>();
			List<PickupVO> newList = new LinkedList<>();
			try {
				String pkup_status = req.getParameter("pkup_status_query");
				String bk_no = req.getParameter("bk_no_query");
				PickupService pkupSvc = new PickupService();
				
				
				req.setAttribute("query_status", pkup_status);
				if (pkup_status.equals("all")) {
					pkupList = pkupSvc.getAllPkup();
				} else {
					pkupList = pkupSvc.getAllPkupByStatus(pkup_status);
				}
				if (!bk_no.isEmpty()) {
					for (PickupVO pkupvo: pkupList) {
						if (pkupvo.getBk_no().equals(bk_no)) {
							newList.add(pkupvo);
						}
					}
					req.setAttribute("pkupList", newList);
				} else {
					req.setAttribute("pkupList", pkupList);
				}
				dispatcher.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if("getAllByBkNo".equals(action)) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/backend/pickup/pkupInfo.jsp");
			res.setContentType("text/html; charset=utf-8");
			try {
				String bk_no = req.getParameter("bk_no");
				PickupService pkupSvc = new PickupService();
				List<PickupVO> pkupList = pkupSvc.getAllByBkNo(bk_no);
				dispatcher.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
