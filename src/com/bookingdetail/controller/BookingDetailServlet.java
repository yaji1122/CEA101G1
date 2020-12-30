package com.bookingdetail.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bookingdetail.model.*;
import com.roomtype.model.*;


@WebServlet("/BookingDetailServlet")
public class BookingDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public BookingDetailServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action").trim();
		PrintWriter out = null;
		RequestDispatcher dispatcher = null;
		
		if ("insert_bkdetail".equals(action)) {
			out = res.getWriter();
			try {
				res.setContentType("text; charset=utf-8");
				String bk_no = req.getParameter("bk_no");
				String rm_type = req.getParameter("rm_ypte");
				RoomTypeService rmtypeSvc = new RoomTypeService();
				RoomTypeVO rmtypevo = rmtypeSvc.getOne(rm_type);
				Integer rm_price = rmtypevo.getRm_price();
				Integer qty = Integer.parseInt(req.getParameter("qty"));
				BookingDetailService bkdetailSvc = new BookingDetailService();
				bkdetailSvc.addBkDetail(bk_no, rm_type, rm_price, qty);
				out.print("sucess");
			} catch (Exception e) {
				e.printStackTrace();
				out.print("fail");
			} finally {
				if (out != null) out.close();
			}
		}
		
		if ("update_bkdetail".equals(action)) {
			out = res.getWriter();
			try {
				res.setContentType("text; charset=utf-8");
				String bk_no = req.getParameter("update_bk_no");
				String rm_type = req.getParameter("update_rm_ypte");
				Integer qty = Integer.parseInt(req.getParameter("update_qty"));
				BookingDetailService bkdetailSvc = new BookingDetailService();
				bkdetailSvc.updateBkDetail(bk_no, rm_type, qty);
				out.print("sucess");
			} catch (Exception e) {
				e.printStackTrace();
				out.print("fail");
			} finally {
				if (out != null) out.close();
			}
		}
		
		if ("getall_bybkno".equals(action)) {
			dispatcher = req.getRequestDispatcher("/backend/booking/bkdetail.jsp");
			try {
				res.setContentType("html/text; charset=utf-8");
				String bk_no = req.getParameter("bk_no");
				BookingDetailService bkdetailSvc = new BookingDetailService();
				List<BookingDetailVO> bkdetailList =  bkdetailSvc.getAllByBkNo(bk_no);
				System.out.print(bkdetailList.size());
				req.setAttribute("bkdetailList", bkdetailList);
				dispatcher.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
				req.setAttribute("msg", "伺服器忙線中");
				dispatcher.forward(req, res);
			} 
		}
	}

}
