package com.rooms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.*;

import com.bookingorder.model.BookingOrderService;
import com.rooms.model.*;
import com.roomtype.model.RoomTypeService;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;

@MultipartConfig
@WebServlet("/RoomsServlet")
public class RoomsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RoomsServlet() {
		super();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text; charset=utf-8");
		PrintWriter out = res.getWriter();
		String action = req.getParameter("action").trim();

		if ("insert_room".equals(action)) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("backend/rooms/roomInfo.jsp");
			try {
				String rm_type = req.getParameter("rm_type");
				RoomsService rmSvc = new RoomsService();
				RoomsVO rmvo = rmSvc.addRoom(rm_type, "");
				RoomTypeService rmtypeSvc = new RoomTypeService();
				rmtypeSvc.updateRoomQty(rm_type, 1);
				req.setAttribute("msg", "新增的房號為" + rmvo.getRm_no());
				dispatcher.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
				req.setAttribute("msg", "已達最大新增數量");
				dispatcher.forward(req, res);
			}
		}

		if ("insert_room_manually".equals(action)) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("backend/rooms/roomInfo.jsp");
			try {
				String rm_type = req.getParameter("rm_type2");
				String rm_no = req.getParameter("rm_no");
				if (rm_no.charAt(0) != rm_type.charAt(0)) {
					req.setAttribute("msg", "房型與編號不符" ); 
					dispatcher.forward(req, res);
					return;
				}
				if (rm_no.length() < 3) {
					req.setAttribute("msg", "編號格式錯誤" ); 
					dispatcher.forward(req, res);
					return;
				}
				RoomsService rmSvc = new RoomsService();
				List<RoomsVO> rooms = rmSvc.getAllByRmType(rm_type);
				System.out.println(rooms.size());
				for (RoomsVO rm: rooms) {
					System.out.println(rm.getRm_no().equals(rm_no));
					System.out.println("有跑到");
					if (rm.getRm_no().equals(rm_no)) {
						req.setAttribute("msg", "房號已重複" ); 
						dispatcher.forward(req, res);
						return;
					}
				}
				RoomsVO rmvo = rmSvc.addRoom(rm_type, rm_no);
				RoomTypeService rmtypeSvc = new RoomTypeService();
				rmtypeSvc.updateRoomQty(rm_type, 1); //呼叫服務增加房間數量
				req.setAttribute("msg", "新增的房號為" + rmvo.getRm_no()); //將新增的房號丟回新增頁面
				dispatcher.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
				req.setAttribute("msg", "已達最大新增數量");
				dispatcher.forward(req, res);
			}
		}

		if ("update_room".equals(action)) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("backend/rooms/roomInfo.jsp");
			try {
				String rm_no = req.getParameter("update-rm-no");
				String rm_status = req.getParameter("update-rmstatus");
				RoomsService rmSvc = new RoomsService();
				rmSvc.updateRoomStatus(rm_no, rm_status);
				req.setAttribute("msg", "房號" + rm_no + "狀態已更新");
				dispatcher.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
				req.setAttribute("msg", "房號狀態更新失敗");
				dispatcher.forward(req, res);
			}
		}

		if ("update_check_in".equals(action)) {
			try {
				String rm_no = req.getParameter("rm_no");
				String mb_id = req.getParameter("mb_id");
				String bk_no = req.getParameter("bk_no");
				RoomsService rmSvc = new RoomsService();
				BookingOrderService bkSvc = new BookingOrderService();
				bkSvc.checkIn(bk_no);
				rmSvc.updateCheckIn(rm_no, mb_id);
				out.print("success");
			} catch (Exception e) {
				e.printStackTrace();
				out.print("fail");
			}
		}

		if ("delete_room".equals(action)) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("backend/rooms/roomInfo.jsp");
			try {
				String rm_no = req.getParameter("rm_no");
				String rm_type = req.getParameter("rm_type");
				RoomsService rmSvc = new RoomsService();
				rmSvc.delete(rm_no);
				RoomTypeService rmtypeSvc = new RoomTypeService();
				rmtypeSvc.updateRoomQty(rm_type, -1);
				req.setAttribute("msg", "房號" + rm_no + "已刪除");
				dispatcher.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
				req.setAttribute("msg", "刪除失敗");
				dispatcher.forward(req, res);
			}
		}

		if ("getall_by_status".equals(action)) {
			RequestDispatcher dispatcher = req.getRequestDispatcher(req.getContextPath() + "/roomsByStatus.jsp");
			try {
				List<RoomsVO> rooms = new ArrayList<>();
				String rm_status = req.getParameter("rm_status");
				RoomsService rmSvc = new RoomsService();
				rooms = rmSvc.getAllByStatus(rm_status);
				req.setAttribute("rooms", rooms);
				dispatcher.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
				req.setAttribute("error", "無符合條件之查詢結果");
				dispatcher.forward(req, res);
			}
		}

		if ("getall_by_rmtype".equals(action)) {
			RequestDispatcher dispatcher = req.getRequestDispatcher(req.getContextPath() + "/roomsByRmType.jsp");
			try {
				List<RoomsVO> rooms = new ArrayList<>();
				String rm_type = req.getParameter("rm_type");
				RoomsService rmSvc = new RoomsService();
				rooms = rmSvc.getAllByRmType(rm_type);
				req.setAttribute("rooms", rooms);
				dispatcher.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
				req.setAttribute("error", "無符合條件之查詢結果");
				dispatcher.forward(req, res);
			}
		}

		if ("getall".equals(action)) {
			RequestDispatcher dispatcher = req.getRequestDispatcher(req.getContextPath() + "/allRooms.jsp");
			try {
				List<RoomsVO> rooms = new ArrayList<>();
				RoomsService rmSvc = new RoomsService();
				rooms = rmSvc.getAll();
				req.setAttribute("rooms", rooms);
				dispatcher.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
				req.setAttribute("error", "無符合條件之查詢結果");
				dispatcher.forward(req, res);
			}
		}
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

}
