package com.bookingorder.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bookingdetail.model.*;
import com.bookingorder.model.*;

@MultipartConfig
@WebServlet("/BookingOrderServlet")
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
				String mb_id = req.getParameter("mb_id");
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String dateInStr = req.getParameter("datein");
				String dateOutStr = req.getParameter("dateout");
				Date dateIn = new Date(df.parse(dateInStr).getTime());
				Date dateOut = new Date(df.parse(dateOutStr).getTime());
				Integer total_price = Integer.parseInt(req.getParameter("total_price"));
				BookingOrderService bkodSvc = new BookingOrderService();
				BookingOrderVO bkodvo = bkodSvc.addBkOd(mb_id, dateIn, dateOut, total_price);
				out.print(bkodvo.getBk_no());
			} catch (Exception e){
				e.printStackTrace();
				out.print("fail");
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
				Date dateIn = new Date(df.parse(dateInStr).getTime());
				Date dateOut = new Date(df.parse(dateOutStr).getTime());
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
		
		if("update_bkod".equals(action)) {
			out = res.getWriter();
			try {
				String bk_no = req.getParameter("bk_no");
				String dateInStr = req.getParameter("date_in");
				String dateOutStr = req.getParameter("date_out");
				String rm_type = req.getParameter("rm_type");
				Integer qty = Integer.valueOf(req.getParameter("qty"));
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date dateIn = new Date(df.parse(dateInStr).getTime());
				Date dateOut = new Date(df.parse(dateOutStr).getTime());
				BookingOrderService bkodSvc = new BookingOrderService();
				BookingDetailService bkdetailSvc = new BookingDetailService();
				bkodSvc.updateDateInOut(bk_no, dateIn, dateOut);
				bkdetailSvc.updateBkDetail(bk_no, rm_type, qty);
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
		
		if ("getone_bymbid".equals(action)) {
			dispatcher = req.getRequestDispatcher("/backend/booking/bookingInfo.jsp");
			List<BookingOrderVO> bkodList = new LinkedList<>();
			try {
				String mb_id = req.getParameter("bkod_mbid_query");
				BookingOrderService bkodSvc = new BookingOrderService();
				BookingOrderVO bkodvo = bkodSvc.getOneByMbId(mb_id);
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
		
		if ("getall_bydatein".equals(action)) {
			dispatcher = req.getRequestDispatcher("/backend/booking/bookingInfo.jsp");
			try {
				String datein = req.getParameter("date_in");
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date dateIn = new Date(df.parse(datein).getTime());
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
