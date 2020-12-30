package com.time_table.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service_order.model.ServiceOrderService;
import com.service_type.model.ServiceTypeService;
import com.service_type.model.ServiceTypeVO;
import com.time_table.model.*;

@WebServlet("/TimeTableServlet")
public class TimeTableServlet extends HttpServlet {

	public TimeTableServlet() {
		super();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Update".equals(action)) {

			String serv_no = req.getParameter("serv_no");

			Integer serv_period = null;
			serv_period = new Integer(req.getParameter("serv_period"));

			TimeTableService timeTableSvc = new TimeTableService();
			TimeTableVO timeTableVO = timeTableSvc.getOneTimeTable(serv_no, serv_period);

			req.setAttribute("timeTableVO", timeTableVO);
			String url = "/backend/timeTable/update_timeTable_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}

		if ("update".equals(action)) {

			Integer serv_period = null;
			serv_period = new Integer(req.getParameter("serv_period").trim());

			String serv_no = req.getParameter("serv_no");

			Integer max_serv_ppl = null;
			try {
				max_serv_ppl = new Integer(req.getParameter("max_serv_ppl").trim());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}

			TimeTableVO timeTableVO = new TimeTableVO();
			timeTableVO.setServ_period(serv_period);
			timeTableVO.setServ_no(serv_no);
			timeTableVO.setMax_serv_ppl(max_serv_ppl);

			TimeTableService timeTableSvc = new TimeTableService();
			timeTableVO = timeTableSvc.updateTimeTable(serv_period, serv_no, max_serv_ppl);

			req.setAttribute("timeTableVO", timeTableVO);
			String url = "/backend/timeTable/TimeTableInfo.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("insert".equals(action)) {

			try {
				Integer serv_period = null;
				serv_period = new Integer(req.getParameter("serv_period").trim());

				String serv_no = req.getParameter("serv_no").trim();

				Integer max_serv_ppl = null;
				max_serv_ppl = new Integer(req.getParameter("max_serv_ppl").trim());

				TimeTableVO timeTableVO = new TimeTableVO();
				timeTableVO.setServ_period(serv_period);
				timeTableVO.setServ_no(serv_no);
				timeTableVO.setMax_serv_ppl(max_serv_ppl);

				TimeTableService timeTableSvc = new TimeTableService();
				timeTableVO = timeTableSvc.addTimeTable(serv_period, serv_no, max_serv_ppl);

				String url = "/backend/timeTable/TimeTableInfo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if ("delete".equals(action)) {

			String serv_no = req.getParameter("serv_no");
			
			Integer serv_period = null;
			serv_period = new Integer(req.getParameter("serv_period").trim());

			TimeTableService timeTableSvc = new TimeTableService();
			timeTableSvc.deleteTimeTable(serv_no, serv_period);

			String url = "/backend/timeTable/TimeTableInfo.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

	}

}
