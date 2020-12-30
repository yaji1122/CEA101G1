package com.dept.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import com.dept.model.*;
import com.emp.model.EmpService;



public class DeptServlet extends HttpServlet {
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
        		String str = req.getParameter("dept_no");
        		if (str == null || (str.trim().length()==0)) {
        			errorMsgs.add("請輸入部門編號");
        		}
        		if (!errorMsgs.isEmpty()) {
        			RequestDispatcher failureView = req.getRequestDispatcher("/backend/dept/selectDept.jsp");
        			failureView.forward(req, res);
        			return;//程式中斷
        		}
        		String dept_no = null;
        		try {
        			dept_no =new String(str);
        			System.out.println(dept_no);
        		} catch (Exception e) {
        			errorMsgs.add("部門編號格式不正確");
        		}
        		if (!errorMsgs.isEmpty()) {
        			RequestDispatcher failureView =req.getRequestDispatcher("/backend/dept/selectDept.jsp");
        			failureView.forward(req, res);
        			return;
        		}
          //開始查詢資料
        		DeptService deptSvc = new DeptService();
        		DeptVO deptVO = deptSvc.getOneDept(dept_no);
        		if (deptVO == null) {
        			errorMsgs.add("查無此資料");
        		}
        		if(!errorMsgs.isEmpty()) {
        			RequestDispatcher failureView = req.getRequestDispatcher("/backend/dept/selectDept.jsp");
        			failureView.forward(req, res);
        			return;
        		}
         //查詢完成,準備轉交(Success View)
        		req.setAttribute("deptVO", deptVO);
        		String url = "/backend/dept/listOneDept.jsp";
        		RequestDispatcher successView = req.getRequestDispatcher(url);
        		successView.forward(req, res);
         //其他可能的錯誤處理
        	}catch(Exception e) {
        		errorMsgs.add("無法取得資料:"+e.getMessage());
        		RequestDispatcher failureView = req.getRequestDispatcher("/backend/dept/selectDept.jsp");
        		failureView.forward(req, res);
        	}
        }
        
        if ("getOne_For_Update".equals(action)) {
        	List<String> errorMsgs = new LinkedList<String>();
        	req.setAttribute("errorMsgs", errorMsgs);
        	
        	try {
        		String dept_no = new String (req.getParameter("dept_no"));//請求參數
        		DeptService deptSvc = new DeptService(); //開始查資料
        		DeptVO deptVO = deptSvc.getOneDept(dept_no);
        		//查詢完成, 準備轉交
        		req.setAttribute("deptVO", deptVO);  //從資料庫取出的deptVO物件, 存入req
        		String url ="/backend/dept/update_dept_input.jsp";
        		RequestDispatcher successView = req.getRequestDispatcher(url);//成功轉交給update_dept_input.jsp
        		successView.forward(req, res);
        	} catch (Exception e) {
        		errorMsgs.add("無法取得要修改的資料:"+e.getMessage());
        		RequestDispatcher failureView = req.getRequestDispatcher("/backend/dept/listAllDept.jsp");
        		failureView.forward(req, res);
        	} 
        }
        
        if ("update".equals(action)) {
        	List<String> errorMsgs = new LinkedList<String>();
        	req.setAttribute("errorMsgs", errorMsgs);
        	
        	try {
        		String dept_no = new String(req.getParameter("dept_no").trim());
        		String dept = req.getParameter("dept");
        		if (dept == null || dept.trim().length() ==0) {
        			errorMsgs.add("功能名稱請勿空白");
        		}
        		DeptVO deptVO = new DeptVO();
        		deptVO.setDept_no(dept_no);
        		deptVO.setDept(dept);
        		
        		if (!errorMsgs.isEmpty()) {
        			req.setAttribute("deptVO",deptVO);
        			RequestDispatcher failureView = req.getRequestDispatcher("/backend/dept/update_dept_input.jsp");
        			failureView.forward(req, res);
        			return;
        		}
        		DeptService deptSvc = new DeptService();
        		deptVO = deptSvc.updateDept(dept_no, dept);
        		
        		req.setAttribute("deptVO", deptVO);
        		String url ="/backend/dept/listAllDept.jsp";
        		RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listAlleDept.jsp
				successView.forward(req, res);
        	} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/dept/update_dept_input.jsp");
				failureView.forward(req, res);
			}
        }
        if ("insert".equals(action)) { // 來自addDept.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String dept_no = new String(req.getParameter("dept_no").trim());
        		String dept = req.getParameter("dept");
//        		String deptReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,10}$";
        		if (dept == null || dept.trim().length() ==0) {
        			errorMsgs.add("部門名稱請勿空白");
        		}
//        			else if (! dept.trim().matches(deptReg)){
//        			errorMsgs.add("部門名稱:只能是中,英文字母,數字和_,且長度必須在2-10之間");
//        		}
        		DeptVO deptVO = new DeptVO();
        		deptVO.setDept_no(dept_no);
        		deptVO.setDept(dept);
				

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("deptVO", deptVO); // 含有輸入格式錯誤的deptVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/dept/listAllDept.jsp");
					failureView.forward(req, res);
					return;
				}
				
				//開始新增資料
				DeptService deptSvc = new DeptService();
				deptVO = deptSvc.addDept(dept_no,dept);
				
				//新增完成,準備轉交(Send the Success view)
				String url = "/backend/dept/listAllDept.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);				
				
				//其他可能的錯誤處理
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/dept/addDept.jsp");
				failureView.forward(req, res);
			}
		}        
	}
}

