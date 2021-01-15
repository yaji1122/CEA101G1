package com.roomrsv.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.members.model.MembersVO;
import com.roomrsv.model.*;
import com.roomtype.model.RoomTypeService;
import com.roomtype.model.RoomTypeVO;

@WebServlet("/booking/Available")
public class RoomRsvServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TimerTask rsvCleaner;
	private Timer schedule;  
    public RoomRsvServlet() {
        super();
    }
    
	public void init(ServletConfig config) throws ServletException { //每天移除前天的預定表
		rsvCleaner = new TimerTask() {
    		@Override
			public void run() {
				RoomRsvService svc = new RoomRsvService();
				LocalDate beforeYesterday = LocalDate.now().minusDays(2);
				svc.deleteRsvDate(beforeYesterday);
				System.out.print("已移除" + beforeYesterday + "之前的預約資料表");
			}
		};
		schedule = new Timer();
		schedule.scheduleAtFixedRate(rsvCleaner, 0, 24*60*60*1000);
	}

	public void destroy() { //kill
		schedule.cancel();
		rsvCleaner.cancel();
		schedule.purge();
		System.out.print("自我毀滅程序啟動，排程器已摧毀");
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text; charset=utf-8");
		PrintWriter out = null;
		String action = req.getParameter("action").trim();
		
//		if ("getall_rsv".equals(action)) {
//			try {
//				RoomRsvService rsvSvc = new RoomRsvService();
//				List<RoomRsvVO> list = rsvSvc.getAll();
//				req.setAttribute("rsvList", list);
//				return;
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		
		if("roomCheck".equals(action)) {
			out = res.getWriter();
			try {
				String date = req.getParameter("date");
				String guest = req.getParameter("guest");
				Integer stay = Integer.parseInt(req.getParameter("stay"));
				String rmType = req.getParameter("rmtype");
				LocalDate rsv_date = LocalDate.parse(date);
				RoomRsvService rsvSvc = new RoomRsvService();
				RoomTypeService rmtypeSvc = new RoomTypeService();
				JSONObject jsonObj = new JSONObject();
				if (rmType.equals("all")) {
					List<RoomTypeVO> rmtypeList = rmtypeSvc.getAll();
					for (RoomTypeVO rmtypevo : rmtypeList) {
						Integer rmLeft = rsvSvc.roomCheck(rsv_date, stay, rmtypevo.getRm_type());
						if (rmLeft > 0 && rmtypevo.getRm_capacity() >= Integer.parseInt(guest)) { //只放有空房且人數符合的房型
								jsonObj.put(rmtypevo.getRm_type(), rmLeft);
						}
					}
					if (jsonObj.isEmpty()) { //如果都沒空房的情況
						jsonObj.put("checkNext", rsv_date.plusDays(stay));
						jsonObj.put("isFull", "true");
					} else {
						jsonObj.put("checkNext", rsv_date.plusDays(1L));
					}
					out.print(jsonObj);
				} else {
					Integer rmLeft = rsvSvc.roomCheck(rsv_date, stay, rmType);
					RoomTypeVO rmtypevo = rmtypeSvc.getOne(rmType);
					if (rmLeft > 0 && rmtypevo.getRm_capacity() >= Integer.parseInt(guest)) {
						jsonObj.put(rmType, rmLeft);
						jsonObj.put("checkNext", rsv_date.plusDays(1L));
					} else {
						jsonObj.put("checkNext", rsv_date.plusDays(stay));
						jsonObj.put("isFull", "true");
					}
					out.print(jsonObj);
				}
				return;
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
		}
		
		if("booking".equals(action)) {
			try {
				String date = req.getParameter("date");
				String stay = req.getParameter("stay");
				String guest = req.getParameter("guest");
				LocalDate rsv_date = LocalDate.parse(date);
				RoomRsvService rsvSvc = new RoomRsvService();
				RoomTypeService rmtypeSvc = new RoomTypeService();
				JSONObject jsonObj = new JSONObject();
				List<RoomRsvVO> rsvvoList = new LinkedList<>();
				List<RoomTypeVO> rmtypeList = rmtypeSvc.getAll();
				for (RoomTypeVO rmtypevo : rmtypeList) {
					RoomRsvVO rsvvo = new RoomRsvVO();
					Integer rmLeft = rsvSvc.roomCheck(rsv_date, Integer.parseInt(stay), rmtypevo.getRm_type());
					if (rmLeft > 0 && rmtypevo.getRm_capacity() >= Integer.parseInt(guest)) { //只放有空房且人數符合的房型
						rsvvo.setRm_left(rmLeft);
						rsvvo.setRm_type(rmtypevo.getRm_type());
						rsvvo.setRsv_date(rsv_date);
						rsvvoList.add(rsvvo);
					}
				}
				jsonObj.put("stay", stay); //回傳額外訊息
				jsonObj.put("guest", guest);
				jsonObj.put("startDate", date);
				jsonObj.put("leaveDate", rsv_date.plusDays(Integer.parseInt(stay)).toString());
				req.setAttribute("rsvvoList", rsvvoList);
				req.setAttribute("infoJson", jsonObj);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/frontend/roomrsv/booking.jsp");
				dispatcher.forward(req, res);
				return;
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
		}
		
		if("addtocart".equals(action)) {
			out = res.getWriter();
			try {
				List<JSONObject> bookingCart = null;
				bookingCart  = (List<JSONObject>)req.getSession().getAttribute("bookingCart"); //取得購物車內清單
				if (bookingCart == null) {
					bookingCart = new ArrayList<>();
				}
				String rmtype = req.getParameter("roomType");
				String stay = req.getParameter("stay");
				String startDateStr = req.getParameter("startDate");
				LocalDate startDate = LocalDate.parse(startDateStr);
				LocalDate leaveDate = startDate.plusDays(Integer.parseInt(stay));
				JSONObject roomCard = new JSONObject();
				roomCard.put("startDate", startDateStr);
				roomCard.put("leaveDate", leaveDate.toString());
				roomCard.put("stay", req.getParameter("stay"));
				roomCard.put("guest", req.getParameter("guest"));
				roomCard.put("subtotal", req.getParameter("subtotal"));
				roomCard.put("rmtype", rmtype);
				roomCard.put("roomCardId", rmtype + "-" + startDate + "-" + stay);
				roomCard.put("group", startDateStr+ "/" + leaveDate.toString());
				bookingCart.add(roomCard);
				req.getSession().setAttribute("bookingCart", bookingCart); //不管是不是會員都存在session，限定時間內結帳，不存資料庫
				out.print("success");
				return;
			} catch (Exception e) {
				out.print("fail");
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
		}
		
		if("removefromcart".equals(action)) {
			out = res.getWriter();
			try {
				List<JSONObject> bookingCart  = (List<JSONObject>)req.getSession().getAttribute("bookingCart"); //取得購物車內清單
				String roomCardId = req.getParameter("roomCardId");
				JSONObject json = null;
				for (JSONObject obj: bookingCart) {
					if (obj.getString("roomCardId").equals(roomCardId)) {
						json = obj;
						break;
					}
				}
				bookingCart.remove(json);
				req.getSession().setAttribute("bookingCart", bookingCart); //不管是不是會員都存在session，限定時間內結帳，不存資料庫
				out.print("success");
				return;
			} catch (Exception e) {
				out.print("fail");
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
		}
		
		if("checkLeft".equals(action)) {
			out = res.getWriter();
			try {
				LocalDate date = LocalDate.parse(req.getParameter("date"));
				RoomRsvService rsvSvc = new RoomRsvService();
				List<RoomRsvVO> rsvList = rsvSvc.getOneByDate(date);
				JSONArray jsonArr= new JSONArray();
				rsvList.forEach(e -> {
					JSONObject json = new JSONObject();
					json.put("rmtype", e.getRm_type());
					json.put("rmleft", e.getRm_left());
					jsonArr.put(json);
				});
				out.print(jsonArr.toString());
				return;
			} catch (Exception e) {
				out.print("fail");
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
		}
	}

}
