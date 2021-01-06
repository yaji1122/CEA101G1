package com.service_type.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service_type.model.*;

@WebServlet("/ServiceTypeServlet")
public class ServiceTypeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ServiceTypeServlet() {
		super();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) {

			String serv_type_no = req.getParameter("serv_type_no");

			ServiceTypeService serviceTypeSvc = new ServiceTypeService();
			ServiceTypeVO serviceTypeVO = serviceTypeSvc.getOneServiceType(serv_type_no);

			req.setAttribute("serviceTypeVO", serviceTypeVO);
			String url = "/backend/serviceType/listOneServiceType.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) {

			String serv_type_no = req.getParameter("serv_type_no");

			ServiceTypeService serviceTypeSvc = new ServiceTypeService();
			ServiceTypeVO serviceTypeVO = serviceTypeSvc.getOneServiceType(serv_type_no);

			req.setAttribute("serviceTypeVO", serviceTypeVO);
			String url = "/backend/serviceType/update_serviceType_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}
		if ("update".equals(action)) {
			String serv_type_no = req.getParameter("serv_type_no").trim();

			String serv_type_name = req.getParameter("serv_type_name");

			ServiceTypeVO serviceTypeVO = new ServiceTypeVO();
			serviceTypeVO.setServ_type_no(serv_type_no);
			serviceTypeVO.setServ_type_name(serv_type_name);
			
			ServiceTypeService serviceTypeSvc = new ServiceTypeService();
			serviceTypeVO = serviceTypeSvc.updateServiceType(serv_type_no, serv_type_name);

			req.setAttribute("serviceTypeVO", serviceTypeVO);
			String url = "/backend/serviceType/listOneServiceType.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
			

		}

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
			String serv_type_no = req.getParameter("serv_type_no").trim();
			String serv_type_name = req.getParameter("serv_type_name");

			ServiceTypeVO serviceTypeVO = new ServiceTypeVO();
			serviceTypeVO.setServ_type_no(serv_type_no);
			serviceTypeVO.setServ_type_name(serv_type_name);

			ServiceTypeService serviceTypeSvc = new ServiceTypeService();
			serviceTypeVO = serviceTypeSvc.addServiceType(serv_type_no, serv_type_name);

			String url = "/backend/serviceType/listAllServiceType.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/serviceType/listAllServiceType.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("delete".equals(action)) {
			String serv_type_no = req.getParameter("serv_type_no");
			
			ServiceTypeService serviceTypeSvc = new ServiceTypeService();
			serviceTypeSvc.deleteServiceType(serv_type_no);
			
			String url = "/backend/serviceType/listAllServiceType.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
		}

	}

}
