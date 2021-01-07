package com.emp.controller;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;


public class EmpLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//【檢查使用者輸入的帳號(account) 密碼(password)是否有效?
	//【實際上應至資料庫搜尋比對】                           
	protected EmpVO allowEmp(String emp_id, String emp_pwd) {
		EmpService empSvc = new EmpService();
		EmpVO empVO =empSvc.getOneByID(emp_id, emp_pwd);
		if(empVO == null)
			return null;
		else 
			return empVO;                                         
	}                                                           

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF8");
        res.setContentType("text/html; charset=UTF8");
        PrintWriter out = res.getWriter();
    	HttpSession session = req.getSession();
        //錯誤驗證
        List<String> errorMsgs = new LinkedList<String>();
		session.setAttribute("errorMsgs", errorMsgs);
        
        //取得員工帳號密碼
        String emp_id = req.getParameter("emp_id");
        String emp_pwd = req.getParameter("emp_pwd");
        EmpService empSvc = new EmpService();
		EmpVO empVO =empSvc.getOneEmp(emp_id);
        
        //檢查員工帳號密碼(若非員工)
        if (allowEmp(emp_id, emp_pwd) == null) {
          errorMsgs.add("帳號或密碼錯誤!");
          RequestDispatcher failureView =req.getRequestDispatcher("/backend/emp/failure.jsp");
		  failureView.forward(req,res);
        }else {
        //若是員工
        	session.setAttribute("empVO", empVO);//存入session中的empVO, 做已經登入過的標識
        	session.setAttribute("emp_id", emp_id);
        //並同時將員工資料存入快取
        	Cookie EmpCookie = new Cookie("emp", emp_id);
        	EmpCookie.setMaxAge(60*60);
        	res.addCookie(EmpCookie);
        	res.sendRedirect(req.getContextPath()+ "/backend/backend_index.jsp");
        }
	}

}
