package com.actevent.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.actevent.model.*;


public class ActEventServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

     
	
	//單項查詢
	if ("getOne_For_Display".equals(action)) { // 來自act_event_select_page.jsp的請求

		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String str = req.getParameter("actEventNo");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請選擇編號或項目名稱");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/actevent/act_event_select_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			String actEventNo = null;
			try {
				actEventNo = new String(str);
			} catch (Exception e) {
				errorMsgs.add("編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/actevent/act_event_select_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			/***************************2.開始查詢資料*****************************************/
			ActEventService actEventSvc = new ActEventService();
			ActEventVO actEventVO = actEventSvc.getOneActEvent(actEventNo);
			if (actEventVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/actevent/act_event_select_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			req.setAttribute("actEventVO", actEventVO); // 資料庫取出的actEventVO物件,存入req
			String url = "/back-end/actevent/act_event_listOne.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneAct.jsp
			successView.forward(req, res);

			/***************************其他可能的錯誤處理*************************************/
		} catch (Exception e) {
			errorMsgs.add("無法取得資料:" + e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/back-end/actevent/act_event_select_page.jsp");
			failureView.forward(req, res);
		}
	}
	
	//新增
    if ("insert".equals(action)) { // 來自addAct.jsp的請求  
		
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			String actEventNo = req.getParameter("actEventNo").trim();
			if (actEventNo == null || actEventNo.trim().length() == 0) {
				errorMsgs.add("請填寫新活動項目編號");
			} 
			
			String actTypeNo = req.getParameter("actTypeNo");
			if (actTypeNo == null || actTypeNo.trim().length() == 0) {
				errorMsgs.add("活動類型編號: 請勿空白");
			}
			
			String actEventName = req.getParameter("actEventName");
			if (actEventName == null || actEventName.trim().length() == 0) {
				errorMsgs.add("活動項目名稱: 請勿空白");
			}
			
			String actInfo = req.getParameter("actInfo");
			String actNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
			if ( actInfo == null ||  actInfo.trim().length() == 0) {
				errorMsgs.add("活動敘述: 請勿空白");
			}else if(! actInfo.trim().matches(actInfo)) {
				errorMsgs.add("敘述內容請勿過長");
			}
			
			ActEventVO actEventVO = new ActEventVO();
			actEventVO.setActEventNo(actEventNo);
			actEventVO.setActTypeNo(actTypeNo);
			actEventVO.setActEventName(actEventName);
			actEventVO.setActInfo(actInfo);
		

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("actEventVO", actEventVO); // 含有輸入格式錯誤的actEventVO物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/actevent/act_event_add.jsp");
				failureView.forward(req, res);
				return;
			}
			
			/***************************2.開始新增資料***************************************/
			ActEventService ActEventSvc = new ActEventService();
			actEventVO = ActEventSvc.addActEvent(actEventNo, actTypeNo, actEventName, actInfo);
			
			/***************************3.新增完成,準備轉交(Send the Success view)***********/
			String url = "/back-end/actevent/act_event_listAll.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);				
			
			/***************************其他可能的錯誤處理**********************************/
		} catch (Exception e) {
			errorMsgs.add("輸入的資料為空值");
			e.printStackTrace();
			RequestDispatcher failureView = req
					.getRequestDispatcher("/back-end/actevent/act_event_add.jsp");
			failureView.forward(req, res);
		}
	} 
    
    //刪除
    if ("delete".equals(action)) { // 來自listAllAct.jsp

		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/***************************1.接收請求參數***************************************/
			String actEventNo = req.getParameter("actEventNo");
			
			/***************************2.開始刪除資料***************************************/
			ActEventService ActEventSvc = new ActEventService();
			ActEventSvc.deleteActEvent(actEventNo);
			
			/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
			String url = "/back-end/actevent/act_event_listAll.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
			
			/***************************其他可能的錯誤處理**********************************/
		} catch (Exception e) {
			errorMsgs.add("刪除資料失敗:"+e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/back-end/actevent/act_event_listAll.jsp");
			failureView.forward(req, res);
		}
	}
  //更新
  		if ("getOne_For_Update".equals(action)) { // 來自listAllAct.jsp的請求

  			List<String> errorMsgs = new LinkedList<String>();
  			// Store this set in the request scope, in case we need to
  			// send the ErrorPage view.
  			req.setAttribute("errorMsgs", errorMsgs);
  			
  			try {
  				/***************************1.接收請求參數****************************************/
  				String actEventNo = req.getParameter("actEventNo");
  				
  				/***************************2.開始查詢資料****************************************/
  				ActEventService actEventSvc = new ActEventService();
  				ActEventVO actEventVO = actEventSvc.getOneActEvent(actEventNo);
  								
  				/***************************3.查詢完成,準備轉交(Send the Success view)************/
  				req.setAttribute("actEventVO", actEventVO);         // 資料庫取出的empVO物件,存入req
  				String url = "//back-end/actevent/act_event_input_update.jsp";
  				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
  				successView.forward(req, res);

  				/***************************其他可能的錯誤處理**********************************/
  			} catch (Exception e) {
  				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
  				RequestDispatcher failureView = req
  						.getRequestDispatcher("/back-end/actevent/act_event_listAllAct.jsp");
  				failureView.forward(req, res);
  			}
  		}
  		
  		//更新
        if ("update".equals(action)) { // 來自act_event_update_input.jsp的請求
  			
  			List<String> errorMsgs = new LinkedList<String>();
  			// Store this set in the request scope, in case we need to
  			// send the ErrorPage view.
  			req.setAttribute("errorMsgs", errorMsgs);
  		
  			try {
  				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
  				String actEventNo = new String(req.getParameter("actEventNo").trim());
  				if(actEventNo == null || actEventNo.trim().length() == 0) {
  					errorMsgs.add("請輸入新活動項目編號");
  				}
  				
  				String actTypeNo = req.getParameter("actTypeNo");

  				
  				String actEventName = req.getParameter("actEventName");
				String actNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
				if (actEventName == null || actEventName.trim().length() == 0) {
					errorMsgs.add("活動項目名稱: 請勿空白");
				} else if(!actEventName.trim().matches(actNameReg)) {
					errorMsgs.add("名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String actInfo = req.getParameter("actInfo");

  				
				ActEventVO actEventVO = new ActEventVO();
				actEventVO.setActEventNo(actEventNo);
				actEventVO.setActTypeNo(actTypeNo);
				actEventVO.setActEventName(actEventName);
				actEventVO.setActInfo(actInfo);
  				
  				
  				// Send the use back to the form, if there were errors
  				if (!errorMsgs.isEmpty()) {
  					req.setAttribute("actEventVO", actEventVO); // 含有輸入格式錯誤的actTypeVO物件,也存入req
  					RequestDispatcher failureView = req
  							.getRequestDispatcher("/back-end/actevent/act_event_input_update.jsp");
  					failureView.forward(req, res);
  					return; //程式中斷
  				}
  				
  				/***************************2.開始修改資料*****************************************/
  				ActEventService ActEventSvc = new ActEventService();
  				actEventVO = ActEventSvc.updateActEvent(actEventNo, actTypeNo, actEventName, actInfo);
  				
  				/***************************3.修改完成,準備轉交(Send the Success view)*************/
  				req.setAttribute("actEventVO", actEventVO); // 資料庫update成功後,正確的的actTypeVO物件,存入req
  				String url = "/back-end/actevent/act_event_listAll.jsp";
  				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
  				successView.forward(req, res);

  				/***************************其他可能的錯誤處理*************************************/
  			} catch (Exception e) {
  				errorMsgs.add("修改資料失敗:"+e.getMessage());
  				RequestDispatcher failureView = req
  						.getRequestDispatcher("/back-end/actevent/act_event_input_update.jsp");
  				failureView.forward(req, res);
  			}
  		}
    
    
  }
}
