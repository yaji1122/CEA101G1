package com.func.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dept.model.DeptService;
import com.func.model.*;

public class FuncServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        
        //查詢來自select.page的請求
        if("getOne_For_Display".equals(action)) {
        	
        	List<String> errorMsgs = new LinkedList<String>();
        	req.setAttribute("errorMsgs", errorMsgs);
        	
        	try {
        //接收請求參數-輸入格式的錯誤處理
        		String str = req.getParameter("func_no");
        		if (str == null || (str.trim().length()==0)) {
        			errorMsgs.add("請輸入功能編號");
        		}
        		if (!errorMsgs.isEmpty()) {
        			RequestDispatcher failureView = req.getRequestDispatcher("/backend/func/selectFunc.jsp");
        			failureView.forward(req, res);
        			return;//程式中斷
        		}
        		String func_no = null;
        		try {
        			func_no =new String(str);
        		} catch (Exception e) {
        			errorMsgs.add("功能編號格式不正確");
        		}
        		if (!errorMsgs.isEmpty()) {
        			RequestDispatcher failureView =req.getRequestDispatcher("/backend/func/selectFunc.jsp");
        			failureView.forward(req, res);
        			return;
        		}
          //開始查詢資料
        		FuncService funcSvc = new FuncService();
        		FuncVO funcVO = funcSvc.getOneFunc(func_no);
        		if (funcVO == null) {
        			errorMsgs.add("查無此資料");
        		}
        		if(!errorMsgs.isEmpty()) {
        			RequestDispatcher failureView = req.getRequestDispatcher("/backend/func/selectFunc.jsp");
        			failureView.forward(req, res);
        			return;
        		}
         //查詢完成,準備轉交(Success View)
        		req.setAttribute("funcVO", funcVO);
        		String url = "/backend/func/listOneFunc.jsp";
        		RequestDispatcher successView = req.getRequestDispatcher(url);
        		successView.forward(req, res);
         //其他可能的錯誤處理
        	}catch(Exception e) {
        		errorMsgs.add("無法取得資料:"+e.getMessage());
        		RequestDispatcher failureView = req.getRequestDispatcher("/backend/func/selectFunc.jsp");
        		failureView.forward(req, res);
        	}
        }
        
        if ("getOne_For_Update".equals(action)) {
        	List<String> errorMsgs = new LinkedList<String>();
        	req.setAttribute("errorMsgs", errorMsgs);
        	
        	try {
        		String func_no = new String (req.getParameter("func_no"));//請求參數
        		FuncService funcSvc = new FuncService(); //開始查資料
        		FuncVO funcVO = funcSvc.getOneFunc(func_no);
        		//查詢完成, 準備轉交
        		req.setAttribute("funcVO", funcVO);  //從資料庫取出的funcVO物件, 存入req
        		String url ="/backend/func/update_func_input.jsp";
        		RequestDispatcher successView = req.getRequestDispatcher(url);//成功轉交給update_func_input.jsp
        		successView.forward(req, res);
        	} catch (Exception e) {
        		errorMsgs.add("無法取得要修改的資料:"+e.getMessage());
        		RequestDispatcher failureView = req.getRequestDispatcher("/backend/func/listAllFunc.jsp");
        		failureView.forward(req, res);
        	} 
        }
        
        if ("update".equals(action)) {
        	List<String> errorMsgs = new LinkedList<String>();
        	req.setAttribute("errorMsgs", errorMsgs);
        	
        	try {
        		String func_no = new String(req.getParameter("func_no").trim());
        		String func_name = req.getParameter("func_name");
        		String func_nameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,50}$";
        		if (func_name == null || func_name.trim().length() ==0) {
        			errorMsgs.add("功能名稱請勿空白");
        		}else if (! func_name.trim().matches(func_nameReg)){
        			errorMsgs.add("功能名稱:只能是中,英文字母,數字和_,且長度必須在2-50之間");
        		}
        		FuncVO funcVO = new FuncVO();
        		funcVO.setFunc_no(func_no);
        		funcVO.setFunc_name(func_name);
        		
        		if (!errorMsgs.isEmpty()) {
        			req.setAttribute("funcVO",funcVO);
        			RequestDispatcher failureView = req.getRequestDispatcher("/backend/func/update_func_input.jsp");
        			failureView.forward(req, res);
        			return;
        		}
        		FuncService funcSvc = new FuncService();
        		funcVO =funcSvc.updateFunc(func_no, func_name);
        		
        		req.setAttribute("funcVO", funcVO);
        		String url ="/backend/func/listAllFunc.jsp";
        		RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneFunc.jsp
				successView.forward(req, res);
        	} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/func/update_func_input.jsp");
				failureView.forward(req, res);
			}
        }
        if ("insert".equals(action)) { // 來自addFunc.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String func_no = new String(req.getParameter("func_no").trim());;
				String func_name = req.getParameter("func_name");
				String func_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,50}$";
				if (func_name == null || func_name.trim().length() == 0) {
					errorMsgs.add("功能名稱請勿空白");
				} else if(!func_name.trim().matches(func_nameReg)) { 
					errorMsgs.add("功能名稱:只能是中,英文字母,數字和_,且長度必須在2-50之間");
	            }

				FuncVO funcVO = new FuncVO();
				funcVO.setFunc_no(func_no);
				funcVO.setFunc_name(func_name);;


				if (!errorMsgs.isEmpty()) {
					req.setAttribute("funcVO", funcVO); // 含有輸入格式錯誤的funcVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/func/addFunc.jsp");
					failureView.forward(req, res);
					return;
				}
				
				//開始新增資料
				FuncService funcSvc = new FuncService();
				funcVO = funcSvc.addFunc(func_no,func_name);
				
				//新增完成,準備轉交(Send the Success view)
				String url = "/backend/func/listAllFunc.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);				
				
				//其他可能的錯誤處理
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/func/addFunc.jsp");
				failureView.forward(req, res);
			}
		}
        if ("delete".equals(action)) { // 來自listAllFunc.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				//接收請求參數
				String func_no = req.getParameter("func_no");
				
				//開始刪除資料
				FuncService funcSvc = new FuncService();
				funcSvc.deleteFunc(func_no);
				
				//刪除完成,準備轉交(Send the Success view)								
				String url = "/backend/func/listAllFunc.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				//其他可能的錯誤處理
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/func/listAllFunc.jsp");
				failureView.forward(req, res);
			}
		}
	}

}

