package com.act.controller;

import java.io.*;
import java.time.LocalTime;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import com.act.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ActServlet extends HttpServlet{
		
		public void doGet(HttpServletRequest req, HttpServletResponse res)
				throws ServletException, IOException {
			doPost(req, res);
		}
		
		public void doPost(HttpServletRequest req, HttpServletResponse res)
				throws ServletException, IOException {

			req.setCharacterEncoding("UTF-8");
			String action = req.getParameter("action");
			System.out.println(action);
			
			if ("get_actpic".equals(action)) {
				res.setContentType("img/jpg");
				InputStream is = null;
				String actno = req.getParameter("actno").trim();
				ActService actSvc = new ActService();
				byte[] actpic = actSvc.getOneActPic(actno);
				if (actpic != null) {
					res.getOutputStream().write(actpic);
					return;
				} else {
					is = req.getServletContext().getResourceAsStream("/img/nodata.png");
					byte[] pic = new byte[is.available()];
					is.read(pic);
					res.getOutputStream().write(pic);
					is.close();
				}
				return;
			}
			
			if ("insert".equals(action)) {
				
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
				PrintWriter out = res.getWriter();
				System.out.println(action);

				try {
					String actEventNo = req.getParameter("actEventNo");
					if (actEventNo == null || actEventNo.trim().length() == 0) {
						errorMsgs.add("請勿空白");
					}
					String actName = req.getParameter("actName");
					if (actName == null || actName.trim().length() == 0) {
						errorMsgs.add("請勿空白");
					}
					
					String actStatus = req.getParameter("actStatus");
					
					String actTimeStr = req.getParameter("actTime");
					LocalTime actTime = LocalTime.parse(actTimeStr);
					
					String actPrice = req.getParameter("actPrice");
					Integer act_price = null;
					try {
						act_price = new Integer(actPrice);
						if(act_price<=0){
							errorMsgs.add("金額不得小於0");
						}
					} catch (Exception e) {
						errorMsgs.add("actPrice有異常");
					}
					
					Part actPic = req.getPart("ActPic");
					byte[] act_pic = null;
					if(actPic == null) {
						errorMsgs.add("照片不得空白");
					} else {
						InputStream picIn = actPic.getInputStream();
						act_pic = new byte[picIn.available()];
						picIn.read(act_pic);
						picIn.close();
					}
					
					String actInfo = req.getParameter("actInfo");
					if ( actInfo == null ||  actInfo.trim().length() == 0) {
						errorMsgs.add("請輸入活動內容");
					}else if(! actInfo.trim().matches(actInfo)) {
						errorMsgs.add("內容格式不符合");
					}
				
					ActVO actVO = new ActVO();
					
					actVO.setActEventNo(actEventNo);
					actVO.setActName(actName);
					actVO.setActStatus(actStatus);
					actVO.setActTime(actTime);
					actVO.setActPrice(act_price);
					actVO.setActPic(act_pic);
					actVO.setActInfo(actInfo);

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						StringBuilder str = new StringBuilder();
						errorMsgs.stream().forEach(e -> str.append(e + ", "));
						out.print(str.toString());
						return;
					}
					ActService ActSvc = new ActService();
					actVO = ActSvc.addAct(actEventNo, actName, actStatus, actTime, act_price, act_pic, actInfo);
					out.print("success");
				} catch (Exception e) {
					errorMsgs.add("輸入格式異常");
					e.printStackTrace();
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/act/actInfo.jsp");
					failureView.forward(req, res);
				}
			} 
			
			if ("delete".equals(action)) { 

				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
		
				try {
					String actNo = req.getParameter("actNo").trim();
					
					ActService actSvc = new ActService();
					actSvc.deleteAct(actNo);
					
					String url = "/backend/act/actInfo.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					
				} catch (Exception e) {
					errorMsgs.add("哇操"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/act/actInfo.jsp");
					failureView.forward(req, res);
				}
			}
			
			if ("getOne_For_Update".equals(action)) { 
	  			try {
	  				String actNo = new String(req.getParameter("actNo"));
	  				
	  				ActService actSvc = new ActService();
	  				ActVO actVO = actSvc.getOneAct(actNo);
	  								
	  				req.setAttribute("actVO", actVO);        
	  				String url = "/backend/act/act_update.jsp";
	  				RequestDispatcher successView = req.getRequestDispatcher(url);
	  				successView.forward(req, res);

	  			} catch (Exception e) {
	  				e.printStackTrace();
	  				RequestDispatcher failureView = req
	  						.getRequestDispatcher("/backend/act/actInfo.jsp");
	  				failureView.forward(req, res);
	  			}
	  		}
			
	        if ("update".equals(action)) {
	  			
	  			List<String> errorMsgs = new LinkedList<String>();
	  			req.setAttribute("errorMsgs", errorMsgs);
	  			PrintWriter out = res.getWriter();
	  			try {
	  				String actNo = req.getParameter("actNo");
					String actEventNo = req.getParameter("actEventNo");
					String actName = req.getParameter("actName");
					if (actName == null || actName.trim().length() == 0) {
						errorMsgs.add("");
					}
					String actStatus = req.getParameter("actStatus");
					String actTimeStr = req.getParameter("actTime");
					LocalTime actTime = LocalTime.parse(actTimeStr);
					String actPrice = req.getParameter("actPrice");
					Integer act_price = null;
					try {
						act_price = new Integer(actPrice);
						if(act_price<=0){
							errorMsgs.add("價格不得為負");
						}
					} catch (Exception e) {
						e.printStackTrace();
						errorMsgs.add("操你媽又哪裡錯了？");
					}
					
					Part actPicPart = req.getPart("actPic");
					byte[] actpic = null;
					if(actPicPart == null) {
						ActService actSvc = new ActService();
						actpic = actSvc.getOneActPic(actNo);
					} else {
						InputStream picIn = actPicPart.getInputStream();
						actpic = new byte[picIn.available()];
						picIn.read(actpic);
						picIn.close();
					}
					String actInfo = req.getParameter("actInfo");
					if ( actInfo == null ||  actInfo.trim().length() == 0) {
						errorMsgs.add("請輸入活動內容");
					}
					
					ActVO actVO = new ActVO();
					actVO.setActNo(actNo);
					actVO.setActEventNo(actEventNo);
					actVO.setActName(actName);
					actVO.setActStatus(actStatus);
					actVO.setActTime(actTime);
					actVO.setActPrice(act_price);
					actVO.setActPic(actpic);
					actVO.setActInfo(actInfo);
	  				
	  				
					if (!errorMsgs.isEmpty()) {
						StringBuilder str = new StringBuilder();
						errorMsgs.stream().forEach(e -> str.append(e + ", "));
						out.print(str.toString());
						return;
					}
	  				
	  				ActService ActSvc = new ActService();
	  				actVO = ActSvc.updateAct(actNo, actEventNo, actName, actStatus, actTime, act_price, actpic, actInfo);
	  				
	  				req.setAttribute("actVO", actVO); 
	  				out.print("success");
	  			} catch (Exception e) {
	  				throw new RuntimeException(e);
	  			}
	  		}
	        
	        
	
       }
	}
