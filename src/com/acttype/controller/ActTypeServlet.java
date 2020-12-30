package com.acttype.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.acttype.model.*;

public class ActTypeServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
        //查詢
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("actTypeNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請選擇活動名稱");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/acttype/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String actTypeno = null;
				try {
					actTypeno = new String(str);
				} catch (Exception e) {
					errorMsgs.add("編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/acttype/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ActTypeService actTypeSvc = new ActTypeService();
				ActTypeVO actTypeVO =  actTypeSvc.getOneActType(actTypeno);
				
				if (actTypeVO == null) {
					errorMsgs.add("查無資料");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/acttype/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("actTypeVO", actTypeVO); // 資料庫取出的actTypeVO物件,存入req
				String url = "/back-end/acttype/listOneAct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneAct.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/acttype/select_page.jsp");
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
				String actTypeno = new String(req.getParameter("ActTypeNo"));
				
				/***************************2.開始查詢資料****************************************/
				ActTypeService acttypeSvc = new ActTypeService();
				ActTypeVO actTypeVO = acttypeSvc.getOneActType(actTypeno);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("actTypeVO", actTypeVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back-end/acttype/update_act_type_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/acttype/listAllAct.jsp");
				failureView.forward(req, res);
			}
		}
		
            if ("update".equals(action)) { // 來自update_act_type_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String actTypeno = new String(req.getParameter("ActTypeNo").trim());
				if(actTypeno == null || actTypeno.trim().length() == 0) {
					errorMsgs.add("請輸入種類編號");
				}
				
				String actTypeName = req.getParameter("ActTypeName");
//				String actNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (actTypeName == null || actTypeName.trim().length() == 0) {
					errorMsgs.add("活動類型名稱: 請勿空白");
				} 
				
				ActTypeVO actTypeVO = new ActTypeVO();
				actTypeVO.setActTypeNo(actTypeno);
				actTypeVO.setActTypeName(actTypeName);
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("actTypeVO", actTypeVO); // 含有輸入格式錯誤的actTypeVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/acttype/update_act_type_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ActTypeService ActTypeSvc = new ActTypeService();
				actTypeVO = ActTypeSvc.updateActType(actTypeno,actTypeName);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("actTypeVO", actTypeVO); // 資料庫update成功後,正確的的actTypeVO物件,存入req
				String url = "/back-end/acttype/listAllAct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/update_act_type_input.jsp");
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
//    				String actTypeno = req.getParameter("ActTypeNo").trim();
//    				if (actTypeno == null || actTypeno.trim().length() == 0) {
//    					errorMsgs.add("請填寫新活動種類的編號");
//    				} 
    				
    				String actTypeName = req.getParameter("ActTypeName");
    				if (actTypeName == null || actTypeName.trim().length() == 0) {
    					errorMsgs.add("活動類型名稱: 請勿空白");
    				}
    				
    				String actTypeno = new String(req.getParameter("ActTypeNo").trim());

    				ActTypeVO actTypeVO = new ActTypeVO();
    				actTypeVO.setActTypeNo(actTypeno);
    				actTypeVO.setActTypeName(actTypeName);
    			

    				// Send the use back to the form, if there were errors
    				if (!errorMsgs.isEmpty()) {
    					req.setAttribute("actTypeVO", actTypeVO); // 含有輸入格式錯誤的actTypeVO物件,也存入req
    					RequestDispatcher failureView = req
    							.getRequestDispatcher("/back-end/acttype/addAct.jsp");
    					failureView.forward(req, res);
    					return;
    				}
    				
    				/***************************2.開始新增資料***************************************/
    				ActTypeService ActTypeSvc = new ActTypeService();
    				actTypeVO = ActTypeSvc.addActType(actTypeno, actTypeName);
    				
    				/***************************3.新增完成,準備轉交(Send the Success view)***********/
    				String url = "/back-end/acttype/listAllAct.jsp";
    				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
    				successView.forward(req, res);				
    				
    				/***************************其他可能的錯誤處理**********************************/
    			} catch (Exception e) {
    				errorMsgs.add("輸入的資料為空值");
    				e.printStackTrace();
    				RequestDispatcher failureView = req
    						.getRequestDispatcher("/back-end/acttype/addAct.jsp");
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
    				String actTypeno = req.getParameter("ActTypeno");
    				
    				/***************************2.開始刪除資料***************************************/
    				ActTypeService ActTypeSvc = new ActTypeService();
    				ActTypeSvc.deleteActType(actTypeno);
    				
    				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
    				String url = "/back-end/acttype/listAllAct.jsp";
    				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
    				successView.forward(req, res);
    				
    				/***************************其他可能的錯誤處理**********************************/
    			} catch (Exception e) {
    				errorMsgs.add("刪除資料失敗:"+e.getMessage());
    				RequestDispatcher failureView = req
    						.getRequestDispatcher("/eback-end/acttype/listAllAct.jsp");
    				failureView.forward(req, res);
    			}
    		}
            
            
	}
}
