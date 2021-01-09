package com.act.controller;

import java.io.*;
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
			
			if ("getOne_For_Display".equals(action)) { 

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					String str = req.getParameter("actNo");
					if (str == null || (str.trim()).length() == 0) {
						errorMsgs.add("ACT_NO為空白");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/backend/act/backend-act_select_page.jsp");
						failureView.forward(req, res);
						return;
					}
					
					String actNo = null;
					try {
						actNo = new String(str);
					} catch (Exception e) {
						errorMsgs.add("");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/backend/act/backend-act_select_page.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.嚙罷嚙締嚙範嚙賠賂蕭嚙�*****************************************/
					ActService actSvc = new ActService();
					ActVO actVO = actSvc.getOneAct(actNo);
					if (actVO == null) {
						errorMsgs.add("請輸入活動編號");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/backend/act/backend-act_select_page.jsp");
						failureView.forward(req, res);
						return;//嚙緹嚙踝蕭嚙踝蕭嚙稻
					}
					
					/***************************3.嚙範嚙賠改蕭嚙踝蕭,嚙褒喉蕭嚙踝蕭嚙�(Send the Success view)*************/
					req.setAttribute("actVO", actVO); // 嚙踝蕭w嚙踝蕭嚙碼嚙踝蕭actEventVO嚙踝蕭嚙踝蕭,嚙編嚙皚req
					String url = "/backend/act/backend-act_listOne.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 嚙踝蕭嚙穀嚙踝蕭嚙� listOneAct.jsp
					successView.forward(req, res);

					/***************************嚙踝蕭L嚙箠嚙賞的嚙踝蕭嚙羯嚙畿嚙緲*************************************/
				} catch (Exception e) {
					errorMsgs.add( e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/act/backend-act_select_page.jsp");
					failureView.forward(req, res);
				}
			}
			
			if ("insert".equals(action)) { // 嚙諉佗蕭addAct.jsp嚙踝蕭嚙請求  
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				System.out.println(action);

				try {
					/***********************1.嚙踝蕭嚙踝蕭嚙請求嚙諸潘蕭 - 嚙踝蕭J嚙賣式嚙踝蕭嚙踝蕭嚙羯嚙畿嚙緲*************************/
					String actNo = req.getParameter("actNo").trim();
					String actNameReg = "^(A,C,T0-9_){10}$";
					if (actNo == null || actNo.trim().length() == 0) {
						errorMsgs.add("請勿空白");
					} 
					
					String actEventNo = req.getParameter("actEventNo");
					if (actEventNo == null || actEventNo.trim().length() == 0) {
						errorMsgs.add("請勿空白");
					}
					String actName = req.getParameter("actName");
					if (actName == null || actName.trim().length() == 0) {
						errorMsgs.add("請勿空白");
					}
					
					String actStatus = req.getParameter("actStatus");
					
					
					java.sql.Date actRegTime = null;
					try {
						actRegTime = java.sql.Date.valueOf(req.getParameter("actRegTime").trim());
					} catch (IllegalArgumentException e) {
						actRegTime = new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("日期請勿空白");
					}
					
					java.sql.Date deadLine = null;
					try {
						deadLine = java.sql.Date.valueOf(req.getParameter("deadLine").trim());
					} catch (IllegalArgumentException e) {
						actRegTime = new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("日期請勿空白");
					}
					java.sql.Date actDate = null;
					try {
						actDate = java.sql.Date.valueOf(req.getParameter("actDate").trim());
					} catch (IllegalArgumentException e) {
						actRegTime = new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("日期請勿空白");
					}
					
					String actTime = req.getParameter("actTime");
				
					String participant = req.getParameter("participant");
					
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
					if(actPic == null) {
						errorMsgs.add("照片不得空白");
					}
					InputStream picIn = actPic.getInputStream();
					byte[] act_pic = new byte[picIn.available()];
					picIn.read(act_pic);
					picIn.close();
					
					
					String actInfo = req.getParameter("actInfo");
//					String actNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
					if ( actInfo == null ||  actInfo.trim().length() == 0) {
						errorMsgs.add("請輸入活動內容");
					}else if(! actInfo.trim().matches(actInfo)) {
						errorMsgs.add("內容格式不符合");
					}
				
					ActVO actVO = new ActVO();
					
					actVO.setActNo(actNo);
					actVO.setActEventNo(actEventNo);
					actVO.setActName(actName);
					actVO.setActStatus(actStatus);
					actVO.setActDate(actDate);
					actVO.setActTime(actTime);
					actVO.setParticipant(participant);
					actVO.setActPrice(act_price);
					actVO.setActPic(act_pic);
					actVO.setActInfo(actInfo);

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("actVO", actVO); // 嚙緣嚙踝蕭嚙踝蕭J嚙賣式嚙踝蕭嚙羯嚙踝蕭actEventVO嚙踝蕭嚙踝蕭,嚙稽嚙編嚙皚req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/backend/act/backend-act_add.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.嚙罷嚙締嚙編嚙磕嚙踝蕭嚙�***************************************/
					ActService ActSvc = new ActService();
					actVO = ActSvc.addAct(actNo, actEventNo, actName, actStatus, actRegTime, 
							actDate, deadLine, actTime, participant, act_price,act_pic,actInfo);
					
					/***************************3.嚙編嚙磕嚙踝蕭嚙踝蕭,嚙褒喉蕭嚙踝蕭嚙�(Send the Success view)***********/
					String url = "/backend/act/backend-act_listAll.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 嚙編嚙磕嚙踝蕭嚙穀嚙踝蕭嚙踝蕭嚙締istAllEmp.jsp
					successView.forward(req, res);				
					
					/***************************嚙踝蕭L嚙箠嚙賞的嚙踝蕭嚙羯嚙畿嚙緲**********************************/
				} catch (Exception e) {
					errorMsgs.add("輸入格式異常");
					e.printStackTrace();
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/act/backend-act_add.jsp");
					failureView.forward(req, res);
				}
			} 
			
			//嚙磋嚙踝蕭
			if ("delete".equals(action)) { // 嚙諉佗蕭listAllEmp.jsp

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
		
				try {
					/***************************1.嚙踝蕭嚙踝蕭嚙請求嚙諸潘蕭***************************************/
					String actNo = req.getParameter("actNo").trim();
					
					/***************************2.嚙罷嚙締嚙磋嚙踝蕭嚙踝蕭嚙�***************************************/
					ActService actSvc = new ActService();
					actSvc.deleteAct(actNo);
					
					/***************************3.嚙磋嚙踝蕭嚙踝蕭嚙踝蕭,嚙褒喉蕭嚙踝蕭嚙�(Send the Success view)***********/								
					String url = "/backend/act/act_listAll.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 嚙磋嚙踝蕭嚙踝蕭嚙穀嚙踝蕭,嚙踝蕭嚙稷嚙箴嚙碼嚙磋嚙踝蕭嚙踝蕭嚙諉瘀蕭嚙踝蕭嚙踝蕭
					successView.forward(req, res);
					
					/***************************嚙踝蕭L嚙箠嚙賞的嚙踝蕭嚙羯嚙畿嚙緲**********************************/
				} catch (Exception e) {
					errorMsgs.add("嚙磋嚙踝蕭嚙踝蕭嚙踝蕭嚙�:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/act/act_listAll.jsp");
					failureView.forward(req, res);
				}
			}
			
			//嚙範嚙賠改蕭s
			if ("getOne_For_Update".equals(action)) { // 嚙諉佗蕭listAllAct.jsp嚙踝蕭嚙請求

	  			List<String> errorMsgs = new LinkedList<String>();
	  			// Store this set in the request scope, in case we need to
	  			// send the ErrorPage view.
	  			req.setAttribute("errorMsgs", errorMsgs);
	  			
	  			try {
	  				/***************************1.嚙踝蕭嚙踝蕭嚙請求嚙諸潘蕭****************************************/
	  				String actNo = new String(req.getParameter("actNo"));
	  				
	  				/***************************2.嚙罷嚙締嚙範嚙賠賂蕭嚙�****************************************/
	  				ActService actSvc = new ActService();
	  				ActVO actVO = actSvc.getOneAct(actNo);
	  								
	  				/***************************3.嚙範嚙賠改蕭嚙踝蕭,嚙褒喉蕭嚙踝蕭嚙�(Send the Success view)************/
	  				req.setAttribute("actVO", actVO);         // 嚙踝蕭w嚙踝蕭嚙碼嚙踝蕭empVO嚙踝蕭嚙踝蕭,嚙編嚙皚req
	  				String url = "/backend/act/backend-act_update.jsp";
	  				RequestDispatcher successView = req.getRequestDispatcher(url);// 嚙踝蕭嚙穀嚙踝蕭嚙� update_emp_input.jsp
	  				successView.forward(req, res);

	  				/***************************嚙踝蕭L嚙箠嚙賞的嚙踝蕭嚙羯嚙畿嚙緲**********************************/
	  			} catch (Exception e) {
	  				errorMsgs.add("嚙盤嚙糊嚙踝蕭嚙緻嚙緯嚙論改的嚙踝蕭嚙�:" + e.getMessage());
	  				RequestDispatcher failureView = req
	  						.getRequestDispatcher("/backend/act/backend-act_update.jsp");
	  				failureView.forward(req, res);
	  			}
	  		}
			
			//嚙踝蕭s
	        if ("update".equals(action)) { // 嚙諉佗蕭act_update_input.jsp嚙踝蕭嚙請求
	  			
	  			List<String> errorMsgs = new LinkedList<String>();
	  			// Store this set in the request scope, in case we need to
	  			// send the ErrorPage view.
	  			req.setAttribute("errorMsgs", errorMsgs);
	  		
	  			try {
	  				/***************************1.嚙踝蕭嚙踝蕭嚙請求嚙諸潘蕭 - 嚙踝蕭J嚙賣式嚙踝蕭嚙踝蕭嚙羯嚙畿嚙緲**********************/
	  				String actNo = req.getParameter("actNo").trim();
					String actNameReg = "^(A,C,T0-9_){10}$";
					if (actNo == null || actNo.trim().length() == 0) {
						errorMsgs.add("嚙請確嚙緹嚙踝蕭嚙褊編嚙踝蕭嚙賣式嚙瞌嚙稻嚙踝蕭嚙確");
					} 
					
					String actEventNo = req.getParameter("actEventNo");
					if (actEventNo == null || actEventNo.trim().length() == 0) {
						errorMsgs.add("嚙踝蕭嚙踝蕭嚙踝蕭嚙踝蕭嚙編嚙踝蕭: 嚙請勿空伐蕭");
					}
					String actName = req.getParameter("actName");
					if (actName == null || actName.trim().length() == 0) {
						errorMsgs.add("嚙踝蕭嚙踝蕭嚙踝蕭嚙踝蕭嚙編嚙踝蕭: 嚙請勿空伐蕭");
					}
					
					String actStatus = req.getParameter("actStatus");
					
					
					java.sql.Date actRegTime = null;
					try {
						actRegTime = java.sql.Date.valueOf(req.getParameter("actRegTime").trim());
					} catch (IllegalArgumentException e) {
						actRegTime = new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("嚙請選蕭J嚙踝蕭嚙�!");
					}
					
					java.sql.Date deadLine = null;
					try {
						deadLine = java.sql.Date.valueOf(req.getParameter("deadLine").trim());
					} catch (IllegalArgumentException e) {
						actRegTime = new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("嚙請選蕭J嚙踝蕭嚙�!");
					}
					java.sql.Date actDate = null;
					try {
						actDate = java.sql.Date.valueOf(req.getParameter("actDate").trim());
					} catch (IllegalArgumentException e) {
						actRegTime = new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("嚙請選蕭J嚙踝蕭嚙�!");
					}
					
					String actTime = req.getParameter("actTime");
				
					String participant = req.getParameter("participant");
					
					String actPrice = req.getParameter("actPrice");
					Integer act_price = null;
					try {
						act_price = new Integer(actPrice);
						if(act_price<=0){
							errorMsgs.add("嚙踝蕭嚙賣不嚙箠嚙踝蕭0");
						}
					} catch (Exception e) {
						errorMsgs.add("嚙踝蕭嚙賣不嚙踝蕭嚙確");
					}
					
					Part actPic = req.getPart("actPic");
					if(actPic == null) {
						errorMsgs.add("圖片是空白");
					}
					InputStream picIn = actPic.getInputStream();
					byte[] act_pic = new byte[picIn.available()];
					picIn.read(act_pic);
					picIn.close();
					
					
					String actInfo = req.getParameter("actInfo");
//					String actNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
					if ( actInfo == null ||  actInfo.trim().length() == 0) {
						errorMsgs.add("請輸入活動內容");
					}
				
					
					ActVO actVO = new ActVO();
					actVO.setActNo(actNo);
					actVO.setActEventNo(actEventNo);
					actVO.setActName(actName);
					actVO.setActStatus(actStatus);
					actVO.setActDate(actDate);
					actVO.setActTime(actTime);
					actVO.setParticipant(participant);
					actVO.setActPrice(act_price);
					actVO.setActPic(act_pic);
					actVO.setActInfo(actInfo);
	  				
	  				
	  				// Send the use back to the form, if there were errors
	  				if (!errorMsgs.isEmpty()) {
	  					req.setAttribute("actVO", actVO); // 嚙緣嚙踝蕭嚙踝蕭J嚙賣式嚙踝蕭嚙羯嚙踝蕭actTypeVO嚙踝蕭嚙踝蕭,嚙稽嚙編嚙皚req
	  					RequestDispatcher failureView = req
	  							.getRequestDispatcher("/backend/act/backend-act_update.jsp");
	  					failureView.forward(req, res);
	  					return; //嚙緹嚙踝蕭嚙踝蕭嚙稻
	  				}
	  				
	  				/***************************2.嚙罷嚙締嚙論改蕭嚙踝蕭*****************************************/
	  				ActService ActSvc = new ActService();
	  				actVO = ActSvc.updateAct(actNo, actEventNo, actName, actStatus, actRegTime, actDate, 
	  						deadLine, actTime, participant, act_price,act_pic,actInfo);
	  				
	  				/***************************3.嚙論改完嚙踝蕭,嚙褒喉蕭嚙踝蕭嚙�(Send the Success view)*************/
	  				req.setAttribute("actVO", actVO); // 嚙踝蕭wupdate嚙踝蕭嚙穀嚙踝蕭,嚙踝蕭嚙確嚙踝蕭嚙踝蕭actTypeVO嚙踝蕭嚙踝蕭,嚙編嚙皚req
	  				String url = "/backend/act/backend-act_listAll.jsp";
	  				RequestDispatcher successView = req.getRequestDispatcher(url); // 嚙論改成嚙穀嚙踝蕭,嚙踝蕭嚙締istOneEmp.jsp
	  				successView.forward(req, res);

	  				/***************************嚙踝蕭L嚙箠嚙賞的嚙踝蕭嚙羯嚙畿嚙緲*************************************/
	  			} catch (Exception e) {
	  				errorMsgs.add("嚙論改蕭嚙複伐蕭嚙踝蕭:"+e.getMessage());
	  				RequestDispatcher failureView = req
	  						.getRequestDispatcher("/backend/act/backend-act_update.jsp");
	  				failureView.forward(req, res);
	  			}
	  		}
	        
	        
	
       }
	}
