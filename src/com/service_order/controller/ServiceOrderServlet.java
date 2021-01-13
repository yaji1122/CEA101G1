package com.service_order.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookingorder.model.BKSTATUS;
import com.bookingorder.model.BookingOrderService;
import com.bookingorder.model.BookingOrderVO;
import com.members.model.MembersVO;
import com.service_order.model.*;

@WebServlet("/ServiceOrderServlet")
public class ServiceOrderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

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
			String bk_no = req.getParameter("bk_no");
			String od_status = req.getParameter("od_status");
			String rm_no = req.getParameter("rm_no");
			String serv_no = req.getParameter("serv_no");

			java.sql.Timestamp serv_time = null;
			serv_time = java.sql.Timestamp.valueOf(req.getParameter("serv_time").trim());

			Integer serv_count = null;
			serv_count = new Integer(req.getParameter("serv_count").trim());
			Integer total_price = null;
			total_price = new Integer(req.getParameter("total_price").trim());

			String locations = req.getParameter("locations");

			ServiceOrderVO serviceOrderVO = new ServiceOrderVO();

//			Timestamp serv_time = serviceOrderVO.getServ_time();

			serviceOrderVO.setServ_odno(serv_odno);
			serviceOrderVO.setBk_no(bk_no);
//			serviceOrderVO.setOd_time(od_time);
			serviceOrderVO.setOd_status(od_status);
			serviceOrderVO.setServ_no(serv_no);
			serviceOrderVO.setServ_time(serv_time);
			serviceOrderVO.setServ_count(serv_count);
			serviceOrderVO.setTotal_price(total_price);
			serviceOrderVO.setLocations(locations);

			ServiceOrderService serviceOrderSvc = new ServiceOrderService();
			serviceOrderVO = serviceOrderSvc.updateServiceOrder(serv_odno, bk_no, /* od_time, */ od_status, serv_no,
					serv_time, serv_count, total_price, locations);

			req.setAttribute("serviceOrderVO", serviceOrderVO);

			String url = "backend/serviceOrder/serviceOrderInfo.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		if ("insert".equals(action)) {

			MembersVO member = (MembersVO) req.getSession().getAttribute("member");
			String mb_id = member.getMb_id();
			BookingOrderService bkodSvc = new BookingOrderService();
			List<BookingOrderVO> bkodList = bkodSvc.getAllByMbId(mb_id);
			List<BookingOrderVO> newList = bkodList.stream().filter(e -> e.getBk_status().equals(BKSTATUS.CHECKED))
					.collect(Collectors.toList());

			String bk_no = "";
			for (BookingOrderVO list : newList) {
				bk_no = list.getBk_no();
			}

			String[] serv_noArr = req.getParameterValues("serv_no");

			String[] serv_timeStr = req.getParameterValues("serv_time");

			String[] serv_countStr = req.getParameterValues("serv_count");

			String[] total_priceStr = req.getParameterValues("total_price");

			String[] locationsArr = req.getParameterValues("locations");

//				List<LocalDateTime> date = new ArrayList<LocalDateTime>();
//				for(int i =0;i<str.length;i++) {
//					date.add(LocalDateTime.parse(str[i]));
//				}
//				
//				List<java.sql.Timestamp> serv_time = new ArrayList<java.sql.Timestamp>();
//                for(int i =0;i<str.length;i++) {
//                	serv_time.add(java.sql.Timestamp.valueOf(date.get(i)));
//                }

//				java.sql.Timestamp serv_time = null;
//				serv_time = java.sql.Timestamp.valueOf(servTime);

//				for(int i =0;i<serv_no.length;i++) {
//					Integer [] serv_count = new Integer[i];
//				}

			ServiceOrderVO serviceOrderVO = new ServiceOrderVO();
			for (int i = 0; i < serv_noArr.length; i++) {

				String serv_no = serv_noArr[i];
				String locations = locationsArr[i];

				LocalDateTime servTime = LocalDateTime.parse(serv_timeStr[i]);

				java.sql.Timestamp serv_time = null;
				serv_time = java.sql.Timestamp.valueOf(servTime);

				Integer serv_count = null;
				serv_count = new Integer(serv_countStr[i]);

				Integer total_price = null;
				total_price = new Integer(total_priceStr[i]);

				serviceOrderVO.setBk_no(bk_no);
				serviceOrderVO.setServ_no(serv_no);
				serviceOrderVO.setServ_time(serv_time);
				serviceOrderVO.setServ_count(serv_count);
				serviceOrderVO.setTotal_price(total_price);
				serviceOrderVO.setLocations(locations);

				ServiceOrderService serviceOrderSvc = new ServiceOrderService();
				serviceOrderVO = serviceOrderSvc.addServiceOrder(/* serv_odno, */ bk_no, /* od_time, */ /* od_status, */
						serv_no, serv_time, serv_count, total_price, locations);

			}
			
			HttpSession session = req.getSession();
			session.removeAttribute("shoppingcart");

			String url = "/frontend/services/services.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}

		if ("insert2".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
//				String serv_odno = req.getParameter("serv_odno").trim();
//				MembersVO member = (MembersVO)req.getSession().getAttribute("member");
//				String bk_no = member.getBk_no();

				MembersVO member = (MembersVO) req.getSession().getAttribute("member");
				String mb_id = member.getMb_id();
				BookingOrderService bkodSvc = new BookingOrderService();
				List<BookingOrderVO> bkodList = bkodSvc.getAllByMbId(mb_id);
				List<BookingOrderVO> newList = bkodList.stream().filter(e -> e.getBk_status().equals(BKSTATUS.CHECKED))
						.collect(Collectors.toList());

				String bk_no = "";
				for (BookingOrderVO list : newList) {
					bk_no = list.getBk_no();
				}

				String serv_no = req.getParameter("serv_no");

				String str = req.getParameter("serv_time");
				LocalDateTime servTime = LocalDateTime.parse(str);

				System.out.println("order LocalDateTime:" + servTime);

				java.sql.Timestamp serv_time = null;
				serv_time = java.sql.Timestamp.valueOf(servTime);
				System.out.println("order Timestamp:" + serv_time);

				Integer serv_count = null;
				serv_count = new Integer(req.getParameter("serv_count").trim());
				Integer total_price = null;
				total_price = new Integer(req.getParameter("total_price").trim());

				String locations = req.getParameter("locations");

				ServiceOrderVO serviceOrderVO = new ServiceOrderVO();

//			Timestamp serv_time = serviceOrderVO.getServ_time();

//				serviceOrderVO.setServ_odno(serv_odno);
				serviceOrderVO.setBk_no(bk_no);
//			serviceOrderVO.setOd_time(od_time);
//				serviceOrderVO.setOd_status(od_status);
				serviceOrderVO.setServ_no(serv_no);
				serviceOrderVO.setServ_time(serv_time);
				serviceOrderVO.setServ_count(serv_count);
				serviceOrderVO.setTotal_price(total_price);
				serviceOrderVO.setLocations(locations);

				ServiceOrderService serviceOrderSvc = new ServiceOrderService();
				serviceOrderVO = serviceOrderSvc.addServiceOrder(/* serv_odno, */ bk_no, /* od_time, */ /* od_status, */
						serv_no, serv_time, serv_count, total_price, locations);

				HttpSession session = req.getSession();
				session.removeAttribute("shoppingcart");

				String url = "/frontend/services/services.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/services/services.jsp");
				failureView.forward(req, res);
			}

		}

		if ("delete".equals(action)) {

			String serv_odno = req.getParameter("serv_odno");

			ServiceOrderService serviceOrderSvc = new ServiceOrderService();
			serviceOrderSvc.deleteServiceOrder(serv_odno);

			String url = "/backend/serviceOrder/serviceOrderInfo.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

	}
}
