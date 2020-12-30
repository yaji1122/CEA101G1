package com.roomtype.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;

import com.roompic.model.*;
import com.roomtype.model.*;

@MultipartConfig
@WebServlet("/RoomTypeServlet")
public class RoomTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RoomTypeServlet() {
		super();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text; charset=utf-8");
		PrintWriter out = res.getWriter();
		String action = req.getParameter("action").trim();

		if ("insert_rm_type".equals(action)) { // 來自RoomType.jsp的請求
			RoomTypeService rmtypeSvc = new RoomTypeService();
			List<RoomTypeVO> roomtypes = rmtypeSvc.getAll();
			List<String> roomtypeNo = new ArrayList<>();
			for (RoomTypeVO roomtypevo : roomtypes) {
				roomtypeNo.add(roomtypevo.getRm_type());
			}
			try {
				/* 錯誤參數處理 */
				String rm_type = req.getParameter("rm_type").trim();
				if (roomtypeNo.contains(rm_type)) {
					out.print("編號重複");
					return;
				}
				if(Integer.valueOf(rm_type) > 9) {
					out.print("超出編號限制");
					return;
				}
				String type_name = req.getParameter("type_name").trim();
				String type_eng_name = req.getParameter("type_eng_name").trim();
				Integer rm_price = new Integer(req.getParameter("rm_price"));
				Integer rm_capacity = new Integer(req.getParameter("rm_capacity"));
				String rm_info_title = req.getParameter("rm_info_title").trim();
				String rm_info = req.getParameter("rm_info").trim();

				RoomPicService rmpicSvc = new RoomPicService();
				rmtypeSvc.addRoomType(rm_type, type_name, type_eng_name, rm_price, rm_capacity, rm_info_title, rm_info);// 新增房型資料至資料庫
				Collection<Part> parts = req.getParts();
				rmpicSvc.addRoomPic(rm_type, parts); // 如果有照片就上傳照片

			} catch (Exception e) {
				e.printStackTrace();
				out.print("資料格式錯誤");
			}
			out.print("新增成功");
		}

		if ("update_rm_type".equals(action)) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/backend/roomtype/roomTypeInfo.jsp");
			List<String> msgs = new ArrayList<>();
			try {
				String rm_type = req.getParameter("update-rmtype");
				String type_eng_name = req.getParameter("update-typeengname").trim();
				String type_name = req.getParameter("update-typename");
				Integer rm_price = new Integer(req.getParameter("update-rmprice"));
				Integer rm_capacity = new Integer(req.getParameter("update-rmcap"));
				String rm_info_title = req.getParameter("update-rminfotitle").trim();
				String rm_info = req.getParameter("update-rminfo");

				RoomTypeService rmtypeSvc = new RoomTypeService();
				rmtypeSvc.updateRoomType(rm_type, type_name, type_eng_name, rm_price, rm_capacity, rm_info_title, rm_info);
				msgs.add("更新成功");
				req.setAttribute("msgs", msgs);
				dispatcher.forward(req, res);
				return;
			} catch (Exception e) {
				e.printStackTrace();
				msgs.add("更新失敗");
				req.setAttribute("msgs", msgs);
				dispatcher.forward(req, res);
			}
		}
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

}
