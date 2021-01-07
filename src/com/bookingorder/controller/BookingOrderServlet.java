package com.bookingorder.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;
import com.bookingdetail.model.*;
import com.bookingorder.model.*;
import com.members.model.MembersVO;

@MultipartConfig
@WebServlet("/bookingServlet")
public class BookingOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public BookingOrderServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action").trim();
		PrintWriter out = null;
		RequestDispatcher dispatcher = null;
		
		if ("insert_bkod".equals(action)) {
			out = res.getWriter();
			
			try {
				MembersVO member = (MembersVO) req.getSession().getAttribute("member");
				String card_no = req.getParameter("card_no");
				String mb_id = member.getMb_id();
				List<JSONObject> bookingCart = (List<JSONObject>) req.getSession().getAttribute("bookingCart");
				
				Collections.sort(bookingCart, new Comparator<JSONObject>() { //日期排序
					@Override
					public int compare(JSONObject o1, JSONObject o2) {
						LocalDate startDate1 = LocalDate.parse(o1.getString("startDate"));
						LocalDate startDate2 = LocalDate.parse(o2.getString("startDate"));
						return startDate1.compareTo(startDate2);
					}
				});
				
				Map<String, List<JSONObject>> groupMap = new HashMap<>(); //依照日期建立訂單，相同日期的房型在同個訂單
				groupMap = bookingCart.stream().collect(Collectors.groupingBy( e -> e.getString("group")));
				Set<String> group =  groupMap.keySet();
				HttpSession userSession = req.getSession();
				BookingOrderService bkodSvc = new BookingOrderService();
				Set<javax.websocket.Session> wsSessions = (Set<javax.websocket.Session>) userSession.getAttribute("wsSessions");
				for (String date: group) { //依照日期不同建立訂單
					Integer totalPrice = 0;
					List<JSONObject> dateGroup = groupMap.get(date);
					LocalDate dateIn = LocalDate.parse(date.split("/")[0]);
					LocalDate dateOut = LocalDate.parse(date.split("/")[1]);
					totalPrice = dateGroup.stream()
								.mapToInt(e -> Integer.parseInt(e.getString("subtotal")))
								.sum();
					BookingOrderVO bkodvo =  bkodSvc.addBkOd(mb_id, dateIn, dateOut, totalPrice, dateGroup, card_no);
					if (wsSessions != null) {
						JSONObject data = new JSONObject();
						data.put("type", "訂房訂單");
						data.put("odno", bkodvo.getBk_no());
						wsSessions.forEach(e -> e.getAsyncRemote().sendText(data.toString()));
					}
				}
				
				userSession.removeAttribute("bookingCart");
				userSession.setAttribute("bookingPass", "pass");
				res.sendRedirect(req.getContextPath() + "/frontend/roomrsv/bookingResult.jsp");
			} catch (Exception e){
				e.printStackTrace();
				res.sendRedirect(req.getContextPath() + "/frontend/roomrsv/bookingResult.jsp");
			} finally {
				if (out != null) out.close();
			}
		}
		
		if ("update_dateinout".equals(action)) {
			out = res.getWriter();
			try {
				String bk_no = req.getParameter("bk_no");
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String dateInStr = req.getParameter("datein");
				String dateOutStr = req.getParameter("dateout");
				LocalDate dateIn = LocalDate.parse(dateInStr);
				LocalDate dateOut = LocalDate.parse(dateOutStr);
				BookingOrderService bkodSvc = new BookingOrderService();
				bkodSvc.updateDateInOut(bk_no, dateIn, dateOut);
				out.print("success");
			} catch (Exception e){
				e.printStackTrace();
				out.print("fail");
			} finally {
				if (out != null) out.close();
			}
		}
		
		if("cancel_booking".equals(action)) {
			out = res.getWriter();
			try {
				String bk_no = req.getParameter("bk_no");
				BookingOrderService bkodSvc = new BookingOrderService();
				bkodSvc.cancelBooking(bk_no);
				out.print("success");
			} catch (Exception e){
				e.printStackTrace();
				out.print("fail");
			} finally {
				if (out != null) out.close();
			}
		}
		
		if("update_bkod".equals(action)) {
			out = res.getWriter();
			try {
				String bk_no = req.getParameter("bk_no");
				String dateInStr = req.getParameter("date_in");
				String dateOutStr = req.getParameter("date_out");
				String rm_type = req.getParameter("rm_type");
				Integer qty = Integer.valueOf(req.getParameter("qty"));
				LocalDate dateIn = LocalDate.parse(dateInStr);
				LocalDate dateOut = LocalDate.parse(dateOutStr);
				BookingOrderService bkodSvc = new BookingOrderService();
				BookingDetailService bkdetailSvc = new BookingDetailService();
				bkodSvc.updateDateInOut(bk_no, dateIn, dateOut);
				out.print("success");
			} catch (Exception e){
				e.printStackTrace();
				out.print("fail");
			} finally {
				if (out != null) out.close();
			}
		}
		
		if ("checkin".equals(action)) {
			out = res.getWriter();
			try {
				String bk_no = req.getParameter("bk_no");
				BookingOrderService bkodSvc = new BookingOrderService();
				bkodSvc.checkIn(bk_no);
				out.print("success");
			} catch (Exception e){
				e.printStackTrace();
				out.print("fail");
			} finally {
				if (out != null) out.close();
			}
		}
		
		if ("checkout".equals(action)) {
			out = res.getWriter();
			try {
				String bk_no = req.getParameter("bk_no");
				BookingOrderService bkodSvc = new BookingOrderService();
				bkodSvc.checkOut(bk_no);
				out.print("success");
			} catch (Exception e){
				e.printStackTrace();
				out.print("fail");
			} finally {
				if (out != null) out.close();
			}
		}
		
		if ("pay_bkod".equals(action)) {
			out = res.getWriter();
			try {
				String bk_no = req.getParameter("bk_no");
				BookingOrderService bkodSvc = new BookingOrderService();
				bkodSvc.bkOdPaid(bk_no);
				out.print("success");
			} catch (Exception e){
				e.printStackTrace();
				out.print("fail");
			} finally {
				if (out != null) out.close();
			}
		}
		
		if ("getone_bybkno".equals(action)) {
			dispatcher = req.getRequestDispatcher("/backend/booking/bookingInfo.jsp");
			List<BookingOrderVO> bkodList = new LinkedList<>();
			try {
				String bk_no = req.getParameter("bkod_bkno_query");
				BookingOrderService bkodSvc = new BookingOrderService();
				BookingOrderVO bkodvo = bkodSvc.getOneByBkNo(bk_no);
				if (bkodvo == null) {
					req.setAttribute("msgs", "查無訂單");
				} else {
					bkodList.add(bkodvo);
				}
				req.setAttribute("bkodList", bkodList);
				dispatcher.forward(req, res);
				return;
			} catch (Exception e){
				e.printStackTrace();
				req.setAttribute("msgs", "查無單號訊息");
				dispatcher.forward(req, res);
			} 
		}
		
		if ("getone_bybkno_forupdate".equals(action)) {
			dispatcher = req.getRequestDispatcher("/backend/booking/modifiedBooking.jsp");
			try {
				String bk_no = req.getParameter("bk_no");
				BookingOrderService bkodSvc = new BookingOrderService();
				BookingOrderVO bkodvo = bkodSvc.getOneByBkNo(bk_no);
				if (bkodvo == null) {
					req.setAttribute("msgs", "查無訂單");
				} else {
					req.setAttribute("bkodvo", bkodvo);
				}
				dispatcher.forward(req, res);
				return;
			} catch (Exception e){
				e.printStackTrace();
				req.setAttribute("msgs", "伺服器忙線中");
				dispatcher.forward(req, res);
			} 
		}
		
		if ("getall_bymbid".equals(action)) {
			dispatcher = req.getRequestDispatcher("/backend/booking/bookingInfo.jsp");
			List<BookingOrderVO> bkodList = new LinkedList<>();
			try {
				String mb_id = req.getParameter("bkod_mbid_query");
				BookingOrderService bkodSvc = new BookingOrderService();
				bkodList = bkodSvc.getAllByMbId(mb_id);
				if (bkodList.size() == 0) {
					req.setAttribute("msgs", "查無訂單");
				} else {
					req.setAttribute("bkodList", bkodList);
				}
				dispatcher.forward(req, res);
				return;
			} catch (Exception e){
				e.printStackTrace();
				req.setAttribute("msgs", "查無單號訊息");
				dispatcher.forward(req, res);
			} 
		}
		
		if ("getall_bydatein".equals(action)) {
			dispatcher = req.getRequestDispatcher("/backend/booking/bookingInfo.jsp");
			try {
				String dateInStr = req.getParameter("date_in");
				LocalDate dateIn = LocalDate.parse(dateInStr); 
				BookingOrderService bkodSvc = new BookingOrderService();
				List<BookingOrderVO> bkodList = bkodSvc.getAllByDateIn(dateIn);
				req.setAttribute("bkodList", bkodList);
				dispatcher.forward(req, res);
				return;
			} catch (Exception e){
				e.printStackTrace();
				req.setAttribute("msg", "查無單號訊息");
				dispatcher.forward(req, res);
			} 
		}
		
		if ("getDateInOut".equals(action)) {
			dispatcher = req.getRequestDispatcher("/backend/booking/bookingInfo.jsp");
			try {
				String dateOutStr = req.getParameter("date_out");
				LocalDate dateOut = LocalDate.parse(dateOutStr); 
				BookingOrderService bkodSvc = new BookingOrderService();
				List<BookingOrderVO> bkodList = bkodSvc.getAllByDateOut(dateOut);
				req.setAttribute("bkodList", bkodList);
				dispatcher.forward(req, res);
				return;
			} catch (Exception e){
				e.printStackTrace();
				req.setAttribute("msg", "查無單號訊息");
				dispatcher.forward(req, res);
			} 
		}
		
		if ("getall_bkod".equals(action)) {
			dispatcher = req.getRequestDispatcher("/backend/booking/bookingInfo.jsp");
			res.setContentType("text/html; charset=utf-8");
			try {
				BookingOrderService bkodSvc = new BookingOrderService();
				List<BookingOrderVO> bkodList = bkodSvc.getAllBooking();
				req.setAttribute("bkodList", bkodList);
				dispatcher.forward(req, res);
				return;
			} catch (Exception e){
				e.printStackTrace();
				req.setAttribute("msgs", "查無單號訊息");
				dispatcher.forward(req, res);
			} 
		}
	}
}
