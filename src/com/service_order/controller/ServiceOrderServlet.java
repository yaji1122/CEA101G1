package com.service_order.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service_order.model.*;

@WebServlet("/ServiceOrderServlet")
public class ServiceOrderServlet extends HttpServlet {

	public ServiceOrderServlet() {
		super();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) {
			String serv_odno = req.getParameter("serv_odno");

			ServiceOrderService serviceOrderSvc = new ServiceOrderService();
			ServiceOrderVO serviceOrderVO = serviceOrderSvc.getOneServiceOrder(serv_odno);

			req.setAttribute("serviceOrderVO", serviceOrderVO);
			String url = "/backend/serviceOrder/serviceOrderInfo.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) {

			String serv_odno = req.getParameter("serv_odno");

			ServiceOrderService serviceOrderSvc = new ServiceOrderService();
			ServiceOrderVO serviceOrderVO = serviceOrderSvc.getOneServiceOrder(serv_odno);

			req.setAttribute("serviceOrderVO", serviceOrderVO);

			String url = "backend/serviceOrder/update_serviceOrder_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}

		if ("update".equals(action)) {

			String serv_odno = req.getParameter("serv_odno").trim();
			String mb_id = req.getParameter("mb_id");
			String od_status = req.getParameter("od_status");
			String rm_no = req.getParameter("rm_no");
			String serv_no = req.getParameter("serv_no");

			java.sql.Timestamp serv_time = null;
			serv_time = java.sql.Timestamp.valueOf(req.getParameter("serv_time").trim());

			Integer serv_count = null;
			serv_count = new Integer(req.getParameter("serv_count").trim());
			Integer total_price = null;
			total_price = new Integer(req.getParameter("total_price").trim());

			ServiceOrderVO serviceOrderVO = new ServiceOrderVO();

//			Timestamp serv_time = serviceOrderVO.getServ_time();

			serviceOrderVO.setServ_odno(serv_odno);
			serviceOrderVO.setMb_id(mb_id);
//			serviceOrderVO.setOd_time(od_time);
			serviceOrderVO.setOd_status(od_status);
			serviceOrderVO.setRm_no(rm_no);
			serviceOrderVO.setServ_no(serv_no);
			serviceOrderVO.setServ_time(serv_time);
			serviceOrderVO.setServ_count(serv_count);
			serviceOrderVO.setTotal_price(total_price);

			ServiceOrderService serviceOrderSvc = new ServiceOrderService();
			serviceOrderVO = serviceOrderSvc.updateServiceOrder(serv_odno, mb_id, /* od_time, */ od_status, rm_no,
					serv_no, serv_time, serv_count, total_price);

			req.setAttribute("serviceOrderVO", serviceOrderVO);

			String url = "backend/serviceOrder/serviceOrderInfo.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
//				String serv_odno = req.getParameter("serv_odno").trim();
				String mb_id = req.getParameter("mb_id");
				String od_status = req.getParameter("od_status");
				String rm_no = req.getParameter("rm_no");
				String serv_no = req.getParameter("serv_no");

				java.sql.Timestamp serv_time = null;
				serv_time = java.sql.Timestamp.valueOf(req.getParameter("serv_time").trim());
				System.out.println(serv_time);

				Integer serv_count = null;
				serv_count = new Integer(req.getParameter("serv_count").trim());
				Integer total_price = null;
				total_price = new Integer(req.getParameter("total_price").trim());

				ServiceOrderVO serviceOrderVO = new ServiceOrderVO();

//			Timestamp serv_time = serviceOrderVO.getServ_time();

//				serviceOrderVO.setServ_odno(serv_odno);
				serviceOrderVO.setMb_id(mb_id);
//			serviceOrderVO.setOd_time(od_time);
				serviceOrderVO.setOd_status(od_status);
				serviceOrderVO.setRm_no(rm_no);
				serviceOrderVO.setServ_no(serv_no);
				serviceOrderVO.setServ_time(serv_time);
				serviceOrderVO.setServ_count(serv_count);
				serviceOrderVO.setTotal_price(total_price);

				ServiceOrderService serviceOrderSvc = new ServiceOrderService();
				serviceOrderVO = serviceOrderSvc.addServiceOrder(/*serv_odno,*/ mb_id, /* od_time, */ od_status, rm_no,
						serv_no, serv_time, serv_count, total_price);

				String url = "/backend/serviceOrder/serviceOrderInfo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/serviceOrder/serviceOrderInfo.jsp");
				failureView.forward(req, res);
			}

		}
		
		if("delete".equals(action)) {
			
			String serv_odno = req.getParameter("serv_odno");
			
			ServiceOrderService serviceOrderSvc = new ServiceOrderService();
			serviceOrderSvc.deleteServiceOrder(serv_odno);
			
			String url = "/backend/serviceOrder/serviceOrderInfo.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req,  res);
		}

	}
}
