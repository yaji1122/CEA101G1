package com.title.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import com.title.model.*;

public class TitleServlet extends HttpServlet {

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
        		String str = req.getParameter("title_no");
        		if (str == null || (str.trim().length()==0)) {
        			errorMsgs.add("請輸入職位編號");
        		}
        		if (!errorMsgs.isEmpty()) {
        			RequestDispatcher failureView = req.getRequestDispatcher("/backend/title/selectTitle.jsp");
        			failureView.forward(req, res);
        			return;//程式中斷
        		}
        		String title_no = null;
        		try {
        			title_no =new String(str);
        		} catch (Exception e) {
        			errorMsgs.add("職位編號格式不正確");
        		}
        		if (!errorMsgs.isEmpty()) {
        			RequestDispatcher failureView =req.getRequestDispatcher("/backend/title/selectTitle.jsp");
        			failureView.forward(req, res);
        			return;
        		}
          //開始查詢資料
        		TitleService titleSvc = new TitleService();
        		TitleVO titleVO = titleSvc.getOneTitle(title_no);
        		if (titleVO == null) {
        			errorMsgs.add("查無此資料");
        		}
        		if(!errorMsgs.isEmpty()) {
        			RequestDispatcher failureView = req.getRequestDispatcher("/backend/title/selectTitle.jsp");
        			failureView.forward(req, res);
        			return;
        		}
         //查詢完成,準備轉交(Success View)
        		req.setAttribute("titleVO",titleVO);
        		String url = "/backend/title/listOneTitle.jsp";
        		RequestDispatcher successView = req.getRequestDispatcher(url);
        		successView.forward(req, res);
         //其他可能的錯誤處理
        	}catch(Exception e) {
        		errorMsgs.add("無法取得資料:"+e.getMessage());
        		RequestDispatcher failureView = req.getRequestDispatcher("/backend/title/selectTitle.jsp");
        		failureView.forward(req, res);
        	}
        }
        
        if ("getOne_For_Update".equals(action)) {
        	List<String> errorMsgs = new LinkedList<String>();
        	req.setAttribute("errorMsgs", errorMsgs);
        	
        	try {
        		String title_no = new String (req.getParameter("title_no"));//請求參數
        		TitleService titleSvc = new TitleService(); //開始查資料
        		TitleVO titleVO = titleSvc.getOneTitle(title_no);
        		//查詢完成, 準備轉交
        		req.setAttribute("titleVO", titleVO);  //從資料庫取出的titleVO物件, 存入req
        		String url ="/backend/title/update_title_input.jsp";
        		RequestDispatcher successView = req.getRequestDispatcher(url);//成功轉交給update_title_input.jsp
        		successView.forward(req, res);
        	} catch (Exception e) {
        		errorMsgs.add("無法取得要修改的資料:"+e.getMessage());
        		RequestDispatcher failureView = req.getRequestDispatcher("/backend/title/listAllTitle.jsp");
        		failureView.forward(req, res);
        	} 
        }
        
        if ("update".equals(action)) {
        	List<String> errorMsgs = new LinkedList<String>();
        	req.setAttribute("errorMsgs", errorMsgs);
        	
        	try {
        		String title_no = new String(req.getParameter("title_no").trim());
        		String title = req.getParameter("title");
//        		String title_nameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,20}$";
        		if (title == null || title.trim().length() ==0) {
        			errorMsgs.add("職稱請勿空白");
        		}
//        		else if (! title.trim().matches(title_nameReg)){
//        			errorMsgs.add("職稱:只能是中,英文字母,數字和_,且長度必須在2-20之間");
//        		}
        		TitleVO titleVO = new TitleVO();
        		titleVO.setTitle_no(title_no);
        		titleVO.setTitle(title);
        		
        		if (!errorMsgs.isEmpty()) {
        			req.setAttribute("titleVO",titleVO);
        			RequestDispatcher failureView = req.getRequestDispatcher("/backend/title/update_title_input.jsp");
        			failureView.forward(req, res);
        			return;
        		}
        		TitleService titleSvc = new TitleService();
        		titleVO =titleSvc.updateTitle(title_no, title);
        		
        		req.setAttribute("titleVO", titleVO);
        		String url ="/backend/title/listAllTitle.jsp";
        		RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneFunc.jsp
				successView.forward(req, res);
        	} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/title/update_title_input.jsp");
				failureView.forward(req, res);
			}
        }
        if ("insert".equals(action)) { // 來自addTitle.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String title_no = new String(req.getParameter("title_no").trim());;
				String title = req.getParameter("title");
//				String title_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,20}$";
				if (title == null || title.trim().length() == 0) {
					errorMsgs.add("職位請勿空白");
				}
//				else if(!title.trim().matches(title_nameReg)) { 
//					errorMsgs.add("職稱:只能是中,英文字母,數字和_,且長度必須在2-20之間");
//	            }

				TitleVO titleVO = new TitleVO();
				titleVO.setTitle_no(title_no);
				titleVO.setTitle(title);


				if (!errorMsgs.isEmpty()) {
					req.setAttribute("titleVO", titleVO); // 含有輸入格式錯誤的titleVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/title/addTitle.jsp");
					failureView.forward(req, res);
					return;
				}
				
				//開始新增資料
				TitleService titleSvc = new TitleService();
				titleVO = titleSvc.addTitle(title_no,title);
				
				//新增完成,準備轉交(Send the Success view)
				String url = "/backend/title/listAllTitle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);				
				
				//其他可能的錯誤處理
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/title/addTitle.jsp");
				failureView.forward(req, res);
			}
		}

	}

}
