package com.auth.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth.model.*;
import com.emp.model.EmpService;
import com.emp.model.EmpVO;


public class AuthServlet extends HttpServlet {
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
        		String str = req.getParameter("emp_id");
        		if (str == null || (str.trim().length()==0)) {
        			errorMsgs.add("請輸入員工編號");
        		}
        		if (!errorMsgs.isEmpty()) {
        			RequestDispatcher failureView = req.getRequestDispatcher("/backend/auth/selectAuth.jsp");
        			failureView.forward(req, res);
        			return;//程式中斷
        		}
        		String emp_id = null;
        		try {
        			emp_id =new String(str);
        		} catch (Exception e) {
        			errorMsgs.add("員工編號格式不正確");
        		}
        		if (!errorMsgs.isEmpty()) {
        			RequestDispatcher failureView =req.getRequestDispatcher("/backend/auth/selectAuth.jsp");
        			failureView.forward(req, res);
        			return;
        		}
          //開始查詢資料
        		AuthService authSvc = new AuthService();
        		AuthVO authVO = authSvc.getOneAuth(emp_id);
        		if (authVO == null) {
        			errorMsgs.add("查無此資料");
        		}
        		if(!errorMsgs.isEmpty()) {
        			RequestDispatcher failureView = req.getRequestDispatcher("/backend/auth/selectAuth.jsp");
        			failureView.forward(req, res);
        			return;
        		}
         //查詢完成,準備轉交(Success View)無法取得要修改的資料
        		req.setAttribute("authVO", authVO);
        		String url = "/backend/auth/listOneAuth.jsp";
        		RequestDispatcher successView = req.getRequestDispatcher(url);
        		successView.forward(req, res);
         //其他可能的錯誤處理
        	}catch(Exception e) {
        		errorMsgs.add("無法取得資料:"+e.getMessage());
        		RequestDispatcher failureView = req.getRequestDispatcher("/backend/auth/selectAuth.jsp");
        		failureView.forward(req, res);
        	}
        }
        
//        if ("update".equals(action)) {
//        	List<String> errorMsgs = new LinkedList<String>();
//        	req.setAttribute("errorMsgs", errorMsgs);
//        	AuthVO authVO = new AuthVO();
//        	try {
//        		String emp_id = req.getParameter("emp_id").trim();
//        		String[] functions = req.getParameterValues("function");
//        		AuthService authSvc = new AuthService();
//        		
//        		for (int i = 0; i < functions.length; i++) {
//            	authSvc.updateAuth(emp_id, functions[i]);
//        		}
//        		
//        		
//        		if (emp_id == null || emp_id.trim().length() ==0) {
//        			errorMsgs.add("員工編號請勿空白");
//        		}
//       		
//        		if (!errorMsgs.isEmpty()) {
//        			req.setAttribute("authVO",authVO);
//        			RequestDispatcher failureView = req.getRequestDispatcher("/backend/auth/update_auth_input.jsp");
//        			failureView.forward(req, res);
//        			return;
//        		}
//        		
//        		
//        		req.setAttribute("authVO", authVO);
//        		String url ="/backend/auth/listOneAuth.jsp";
//        		RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneAuth.jsp
//				successView.forward(req, res);
//        	} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				req.setAttribute("authVO",authVO);
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/backend/auth/update_auth_input.jsp");
//				failureView.forward(req, res);
//			}
//        }
        
        if ("getOne_For_Update".equals(action)) {
        	List<String> errorMsgs = new LinkedList<String>();
        	req.setAttribute("errorMsgs", errorMsgs);
        	
        	try {
        		String emp_id = req.getParameter("emp_id");//請求參數
        		AuthService authSvc = new AuthService(); //開始查資料
        		AuthVO authVO = authSvc.getOneAuth(emp_id);
        		//查詢完成, 準備轉交
        		req.setAttribute("authVO", authVO);  //從資料庫取出的authVO物件, 存入req
        		String url ="/backend/auth/update_auth_input.jsp";
        		RequestDispatcher successView = req.getRequestDispatcher(url);//成功轉交給update_func_input.jsp
        		successView.forward(req, res);
        	} catch (Exception e) {
        		errorMsgs.add("無法取得要修改的資料:"+e.getMessage());
        		RequestDispatcher failureView = req.getRequestDispatcher("/backend/auth/listAllAuth.jsp");
        		failureView.forward(req, res);
        	} 
        }
        
        if ("insert".equals(action)) { // 來自addAuth.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
            AuthVO authVO = new AuthVO();
			try { //抓取資料
         		String emp_id = req.getParameter("emp_id").trim();
        		String[] functions = req.getParameterValues("function");
        		AuthService authSvc = new AuthService();
				//開始新增資料                
        		for (int i = 0; i < functions.length; i++) {
                	authSvc.addAuth(emp_id, functions[i]);
            		}
				System.out.println(functions.length);
        		if (emp_id == null || emp_id.trim().length() ==0) {
        			errorMsgs.add("員工編號請勿空白");
        		}
        		     		

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("authVO", authVO); // 含有輸入格式錯誤的authVO物件,也存入req
					System.out.println("1");
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/auth/addAuth.jsp");
					failureView.forward(req, res);
					return;
				}
						
				//新增完成,準備轉交(Send the Success view) 至 listAllEmp
				String url = "/backend/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);				
				
				//其他可能的錯誤處理
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/auth/addAuth.jsp");
				failureView.forward(req, res);
			}
		}
         if("getOneByEmp".equals(action)) {//來自select_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsg", errorMsgs);
			
			try {
		//接收請求參數,輸入格式的錯誤處理
				String str = req.getParameter("emp_id");
				if (str ==null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}//重導至查詢頁
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView =req.getRequestDispatcher("/backend/auth/selectAuth.jsp");
					failureView.forward(req,res);
					return;
				}
				
				String emp_id = null;
				try {
					emp_id = new String (str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}//重導至查詢頁
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView =req.getRequestDispatcher("/backend/auth/selectAuth.jsp");
					failureView.forward(req,res);
					return;
				}
			//開始查資料
				AuthService authSvc = new AuthService();
				List <AuthVO> list=authSvc.getAllByEmp(emp_id);
				System.out.println(emp_id);
				System.out.println(list);
				if (list == null){
					errorMsgs.add("查無資料");
				}//重導至查詢頁
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView =req.getRequestDispatcher("/backend/auth/selectAuth.jsp");
					failureView.forward(req,res);
					return;
				}
			//查詢完成,準備傳交
				req.setAttribute("list",list);
//				System.out.println(list);
				String url = "/backend/auth/listAllAuthByEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料"+e.getMessage());
				RequestDispatcher failureView =req.getRequestDispatcher("/backend/auth/selectAuth.jsp");
				failureView.forward(req,res);
			}
		}
         
         if("getOneByFunc".equals(action)) {//來自select_page.jsp的請求
 			
 			List<String> errorMsgs = new LinkedList<String>();
 			req.setAttribute("errorMsg", errorMsgs);
 			
 			try {
 		//接收請求參數,輸入格式的錯誤處理
 				String str = req.getParameter("func_no");
 				if (str ==null || (str.trim()).length() == 0) {
 					errorMsgs.add("請輸入功能編號");
 				}//重導至查詢頁
 				if (!errorMsgs.isEmpty()) {
 					RequestDispatcher failureView =req.getRequestDispatcher("/backend/auth/selectAuth.jsp");
 					failureView.forward(req,res);
 					return;
 				}
 				
 				String func_no = null;
 				try {
 					func_no = new String (str);
 				} catch (Exception e) {
 					errorMsgs.add("功能編號格式不正確");
 				}//重導至查詢頁
 				if (!errorMsgs.isEmpty()) {
 					RequestDispatcher failureView =req.getRequestDispatcher("/backend/auth/selectAuth.jsp");
 					failureView.forward(req,res);
 					return;
 				}
 			//開始查資料
 				AuthService authSvc = new AuthService();
 				List <AuthVO> list=authSvc.getAllByFunc(func_no);

 				if (list == null){
 					errorMsgs.add("查無資料");
 				}//重導至查詢頁
 				if (!errorMsgs.isEmpty()) {
 					RequestDispatcher failureView =req.getRequestDispatcher("/backend/auth/selectAuth.jsp");
 					failureView.forward(req,res);
 					return;
 				}
 			//查詢完成,準備傳交
 				req.setAttribute("list",list);
// 				System.out.println(list);
 				String url = "/backend/auth/listAllAuthByFunc.jsp";
 				RequestDispatcher successView = req.getRequestDispatcher(url);
 				successView.forward(req, res);
 			} catch (Exception e) {
 				errorMsgs.add("無法取得資料"+e.getMessage());
 				RequestDispatcher failureView =req.getRequestDispatcher("/backend/auth/selectAuth.jsp");
 				failureView.forward(req,res);
 			}
 		}
          
         if ("delete".equals(action)) { // 來自listAllAuth.jsp

 			List<String> errorMsgs = new LinkedList<String>();
 			// Store this set in the request scope, in case we need to
 			// send the ErrorPage view.
 			req.setAttribute("errorMsgs", errorMsgs);
 	
 			try {//接收請求參數
 				String emp_id = req.getParameter("emp_id");
 				String func_no = req.getParameter("func_no");
 				
 				//開始刪資料
 				AuthService authSvc = new AuthService();
 				authSvc.deleteAuth(emp_id, func_no);
 				
 				//刪除完成,準備轉交(Send the Success view)								
 				String url = "/backend/emp/protected/listAllEmp.jsp";
 				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
 				successView.forward(req, res);
 				
 				//其他可能的錯誤處理
 			} catch (Exception e) {
 				errorMsgs.add("刪除資料失敗:"+e.getMessage());
 				RequestDispatcher failureView = req
 						.getRequestDispatcher("/backend/auth/listAllAuthByFunc.jsp");
 				failureView.forward(req, res);
 			}
 		}
         
         
	}


}
