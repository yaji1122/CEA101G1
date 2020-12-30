package com.emp.controller;

import java.io.*;
import java.sql.Date;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.auth.model.AuthService;
import com.auth.model.AuthVO;
import com.emp.model.*;



@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class EmpServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action =req.getParameter("action");
		InputStream is = null;
		if ("getEmpPic".equals(action)) {
			String emp_id = req.getParameter("emp_id");
			res.setContentType("image/jpg, image/png, image/jpeg, image/gif");
			EmpService empSvc = new EmpService();
			EmpVO empVO= empSvc.getOnePic(emp_id);
			
			byte[] emp_pic= empVO.getEmp_pic();
			if (emp_pic != null) {
				res.getOutputStream().write(emp_pic);
			} else {
				is = req.getServletContext().getResourceAsStream("/img/nodata.png");
				byte[] pic = new byte[is.available()];
				is.read(pic);
				res.getOutputStream().write(pic);
				is.close();
			}
			return;
		}
		
		
		if ("getOne_For_Update".contentEquals(action)) {//來自listAllEmp.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {//接收請求參數
				String emp_id = req.getParameter("emp_id");

				//開始查資料
				EmpService empSvc = new EmpService();
				EmpVO empVO =empSvc.getOneEmp(emp_id);

				//查詢完成,準備轉交
				req.setAttribute("empVO", empVO);

				String url ="/backend/emp/protected/update_emp_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				//其他可能之錯誤處理
			} catch(Exception e) {
				errorMsgs.add("無法取得要修改的資料" + e.getMessage());
				RequestDispatcher failureView =req.getRequestDispatcher("/backend/emp/protected/listAllEmp.jsp");
				failureView.forward(req,res);
			}
		}
		
		if ("update".equals(action)) {//來自update_emp.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			EmpVO empVO = new EmpVO();
			
			//先刪除原本權限資料再重新新增			
			

			try {
				//接收請求參數, 輸入格式的錯誤處理
				
				String emp_id = req.getParameter("emp_id").trim();
				
				String emp_name = req.getParameter("emp_name");
				String emp_nameReg ="^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,20}$";
				if (emp_name == null || emp_name.trim().length() == 0) {
					errorMsgs.add("員工姓名:請勿空白");
				}else if (!emp_name.trim().matches(emp_nameReg)) {
					errorMsgs.add("員工姓名:只能是中, 英文字母, 數字和 _, 且長度必須在2到20之間");
				}
				
				String emp_pwd = req.getParameter("emp_pwd");
				if (emp_pwd == null || emp_pwd.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
			    }
				
				byte[] emp_pic =null;
				Part part =req.getPart("emp_pic");				
				
				InputStream in = part.getInputStream();
				
				if(in.available()==0) {
					EmpService empSvc = new EmpService();
					empVO= empSvc.getOnePic(emp_id);
					emp_pic=empVO.getEmp_pic();
				}else {
					
				emp_pic = new byte[in.available()];
				in.read(emp_pic);
				in.close();
				}
				
				String emp_phone = req.getParameter("emp_phone");
				if (emp_phone == null|| emp_phone.trim().length() == 0){
		           	errorMsgs.add("請輸入電話號碼");
		        }
				String emp_email = req.getParameter("emp_email");
				if (emp_email == null || emp_email.trim().length() == 0) {
					errorMsgs.add("電子信箱請勿空白");
				}
				String emp_city = req.getParameter("emp_city");
				if (emp_city == null || emp_city.trim().length() ==0) {
					errorMsgs.add("居住城市請勿空白");
				}
				String emp_town = req.getParameter("emp_town");
				if (emp_town == null || emp_town.trim().length() ==0) {
					errorMsgs.add("居住鄉鎮請勿空白");
				}
				
				String emp_address = req.getParameter("emp_address");
				if (emp_address == null || emp_address.trim().length() ==0) {
					errorMsgs.add("居住地址請勿空白");
				}
				
				String emp_status = req.getParameter("emp_status");
				if (emp_status == null || emp_status.trim().length() ==0) {
					errorMsgs.add("員工狀態請勿空白");
				}
			
				String emp_date = req.getParameter("emp_date");
				String title_no = req.getParameter("title_no").trim();
				String dept_no = req.getParameter("dept_no").trim();
				String[] functions = req.getParameterValues("function");
				
				empVO.setEmp_name(emp_name);
				empVO.setEmp_pwd(emp_pwd);
				empVO.setEmp_pic(emp_pic);
				empVO.setEmp_phone(emp_phone);
				empVO.setEmp_email(emp_email);
				empVO.setEmp_city(emp_city);
				empVO.setEmp_town(emp_town);
				empVO.setEmp_address(emp_address);
				empVO.setEmp_status(emp_status);
				empVO.setEmp_date(java.sql.Date.valueOf(emp_date));
				empVO.setTitle_no(title_no);
				empVO.setDept_no(dept_no);
				empVO.setEmp_id(emp_id);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/emp/protected/update_emp_input.jsp");
					failureView.forward(req, res);
					return;
					}
				//開始員工修改資料
				EmpService empSvc = new EmpService();
				empVO = empSvc.updateEmp(emp_id, emp_name, emp_pwd, emp_pic, emp_phone, emp_email, emp_city, emp_town, 
				emp_address, emp_status, java.sql.Date.valueOf(emp_date), title_no, dept_no);

				//修改完成, 準備轉交
				req.setAttribute("empVO",empVO);
				System.out.println(emp_id);
				//開始修改權限資料 
				AuthService authSvc = new AuthService();	
//				System.out.println("functions.length = " + functions.length);
        		for (int i = 0; i < functions.length; i++) {
//        			System.out.println("for i" + i)        			
                	authSvc.addAuth(emp_id, functions[i]);}
				
				String url = "/backend/emp/protected/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req,res);
				//其他可能的錯誤處理
		        }catch (Exception e) {
		        	errorMsgs.add("修改資料失敗:");
		        	e.printStackTrace();
		        	req.setAttribute("empVO", empVO);
		        	RequestDispatcher failureView =req.getRequestDispatcher("/backend/emp/protected/update_emp_input.jsp");
		        	failureView.forward(req, res);
		        	}
		        }
		   if ("insert".equals(action)) {//來自addEmp.jsp的請求
			List<String> errorMsgs = new LinkedList <String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				//接收請求參數,輸入格式的錯誤處理
				String emp_name = req.getParameter("emp_name");
				String emp_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,20}$";
				if (emp_name == null || emp_name.trim().length() == 0) {
					errorMsgs.add("員工姓名:請勿空白");
				} else if (!emp_name.trim().matches(emp_nameReg)) {
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
				}
				
				String emp_pwd = req.getParameter("emp_pwd");
				if (emp_pwd == null || emp_pwd.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
			    }
				
				byte[] emp_pic =null;
				Part part =req.getPart("emp_pic");				
				
				InputStream in = part.getInputStream();
				emp_pic = new byte[in.available()];
				in.read(emp_pic);
				in.close();
				
				String emp_phone =  req.getParameter("emp_phone");
				if (emp_phone ==null||emp_phone.trim().length() == 0){
					errorMsgs.add("請輸入電話號碼");
		        }
				String emp_email = req.getParameter("emp_email");
				if (emp_email == null || emp_email.trim().length() == 0) {
					errorMsgs.add("電子信箱請勿空白");
				}
				String emp_city = req.getParameter("emp_city");
				if (emp_city == null || emp_city.trim().length() ==0) {
					errorMsgs.add("居住城市請勿空白");
				}
				String emp_town = req.getParameter("emp_town");
				if (emp_town == null || emp_town.trim().length() ==0) {
					errorMsgs.add("居住鄉鎮請勿空白");
				}
			
				String emp_address = req.getParameter("emp_address");
				if (emp_address == null || emp_address.trim().length() ==0) {
					errorMsgs.add("居住地址請勿空白");
				}
				
				String emp_status = req.getParameter("emp_status");
				if (emp_status == null || emp_status.trim().length() ==0) {
					errorMsgs.add("員工狀態請勿空白");
				}
				
				String title_no = req.getParameter("title_no").trim();
				String dept_no = req.getParameter("dept_no").trim();
			    //抓取權限的checkbox值
				String[] functions = req.getParameterValues("function");
				if(functions == null) {
					errorMsgs.add("權限不可空白");
				}
				
				EmpVO empVO = new EmpVO();
				empVO.setEmp_name(emp_name);
				empVO.setEmp_pwd(emp_pwd);
				empVO.setEmp_pic(emp_pic);
				empVO.setEmp_phone(emp_phone);
				empVO.setEmp_email(emp_email);
				empVO.setEmp_city(emp_city);
				empVO.setEmp_town(emp_town);
				empVO.setEmp_address(emp_address);
				empVO.setEmp_status(emp_status);
				empVO.setTitle_no(title_no);
				empVO.setDept_no(dept_no);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/emp/protected/addEmp.jsp");
					failureView.forward(req, res);
					return;
					}
				//開始新增員工資料
				EmpService empSvc = new EmpService();
				EmpVO newEmp = empSvc.addEmp(emp_name, emp_pwd, emp_pic,
						emp_phone, emp_email, emp_city, emp_town, emp_address
						, emp_status, title_no,dept_no);
				//開始新增權限資料 
				String emp_id = newEmp.getEmp_id();
				AuthService authSvc = new AuthService();	
				System.out.println("functions.length = " + functions.length);
        		for (int i = 0; i < functions.length; i++) {
        			System.out.println("for i" + i);
                	authSvc.addAuth(emp_id, functions[i]);
            		}

        		//新增完成, 準備轉交至listAllEmp
        		String url ="/backend/emp/protected/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
				//其他可能的錯誤處理
			}  catch (Exception e) {
				errorMsgs.add("其他錯誤");
				e.printStackTrace();
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/emp/protected/addEmp.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("getAllByDept".equals(action)) {//來自select_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsg", errorMsgs);
			
			try {
		         //接收請求參數,輸入格式的錯誤處理
				String str = req.getParameter("dept_no");
				if (str ==null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入部門編號");
				}//重導至查詢頁
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView =req.getRequestDispatcher("/backend/emp/protected/listAllEmp.jsp");
					failureView.forward(req,res);
					return;
				}
				
				String dept_no = null;
				try {
					dept_no = new String (str);
				} catch (Exception e) {
					errorMsgs.add("部門編號格式不正確");
				}//重導至查詢頁
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView =req.getRequestDispatcher("/backend/emp/protected/listAllEmp.jsp");
					failureView.forward(req,res);
					return;
				}
			//開始查資料
				EmpService empSvc = new EmpService();
				List <EmpVO>list =empSvc.getOneByDept(dept_no);
				if (list == null){
					errorMsgs.add("查無資料");
				}//重導至查詢頁
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView =req.getRequestDispatcher("/backend/emp/protected/listAllEmp.jsp");
					failureView.forward(req,res);
					return;
				}
			//查詢完成,準備傳交
				req.setAttribute("list",list);
//				System.out.println(dept_no);
				String url = "/backend/emp/protected/listAllEmpByDept.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("無法取得資料"+e.getMessage());
				RequestDispatcher failureView =req.getRequestDispatcher("/backend/emp/protected/listAllEmp.jsp");
				failureView.forward(req,res);
			}
		}
		
        if("getOneByTitle".equals(action)) {//來自select_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsg", errorMsgs);
			
			try {
		//接收請求參數,輸入格式的錯誤處理
				String str = req.getParameter("title_no");
				if (str ==null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入職位編號");
				}//重導至查詢頁
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView =req.getRequestDispatcher("/backend/emp/protected/listAllEmp.jsp");
					failureView.forward(req,res);
					return;
				}
				
				String title_no = null;
				try {
					title_no = new String (str);
				} catch (Exception e) {
					errorMsgs.add("職位編號格式不正確");
				}//重導至查詢頁
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView =req.getRequestDispatcher("/backend/emp/protected/listAllEmp.jsp");
					failureView.forward(req,res);
					return;
				}
			//開始查資料
				EmpService empSvc = new EmpService();
				List <EmpVO> list=empSvc.getOneByTitle(title_no);
				if (list == null){
					errorMsgs.add("查無資料");
				}//重導至查詢頁
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView =req.getRequestDispatcher("/backend/emp/protected/listAllEmp.jsp");
					failureView.forward(req,res);
					return;
				}
			//查詢完成,準備傳交
				req.setAttribute("list",list);
				String url = "/backend/emp/protected/listAllEmpByTitle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料"+e.getMessage());
				RequestDispatcher failureView =req.getRequestDispatcher("/backend/emp/protected/listAllEmp.jsp");
				failureView.forward(req,res);
			}
		}
         
		if("getOne_For_Display".equals(action)) {//來自select_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
		        //接收請求參數,輸入格式的錯誤處理
				String str = req.getParameter("emp_id");
				if (str ==null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}//重導至查詢頁
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView =req.getRequestDispatcher("/backend/emp/protected/listAllEmp.jsp");
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
					RequestDispatcher failureView =req.getRequestDispatcher("/backend/emp/protected/listAllEmps.jsp");
					failureView.forward(req,res);
					return;
				}
			    //開始查員工資料
				EmpService empSvc = new EmpService();
				EmpVO empVO =empSvc.getOneEmp(emp_id);
				//開始查權限資料
				AuthService authSvc = new AuthService();
				List <AuthVO> list=authSvc.getAllByEmp(emp_id);
				if (empVO == null ){
					errorMsgs.add("查無資料");
				}//重導至查詢頁
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView =req.getRequestDispatcher("/backend/emp/protected/listAllEmp.jsp");
					failureView.forward(req,res);
					return;
				}
			    //查詢完成,準備傳交
				req.setAttribute("empVO",empVO);
				req.setAttribute("list",list);
				String url = "/backend/emp/protected/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料"+e.getMessage());
				RequestDispatcher failureView =req.getRequestDispatcher("/backend/emp/protected/listAllEmp.jsp");
				failureView.forward(req,res);
			}
		}
		
		if("logout".equals(action)) {
			//取得該次連線的session
			HttpSession session = req.getSession();
			//清快取
		    Cookie[] cookieList = req.getCookies();
            if (cookieList!=null) {
            	for (int i = 0;i<cookieList.length;i++) {
            		Cookie EmpCookie = cookieList[i];
            		if (EmpCookie.getName().equals("emp_id")) {
            			EmpCookie.setMaxAge(0);
            			res.addCookie(EmpCookie);
            		}
            	}
            }
			//使session失效
		    session.invalidate();
		    //重導至login網頁
		    res.sendRedirect(req.getContextPath()+"/backend/emp/loginEmp.jsp");
		}
		
		if ("UpdateOneByEmp".contentEquals(action)) {//來自listAllEmp.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {//接收請求參數
				String emp_id = req.getParameter("emp_id");

				//開始查資料
				EmpService empSvc = new EmpService();
				EmpVO empVO =empSvc.getOneEmp(emp_id);

				//查詢完成,準備轉交
				req.setAttribute("empVO", empVO);

				String url ="/backend/emp/protected/updateByEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				//其他可能之錯誤處理
			} catch(Exception e) {
				errorMsgs.add("無法取得要修改的資料" + e.getMessage());
				RequestDispatcher failureView =req.getRequestDispatcher("/backend/emp/protected/Backend.jsp");
				failureView.forward(req,res);
			}
		}
		
		if ("update_FromEmp".equals(action)) {//來自update_emp.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			EmpVO empVO = new EmpVO();

			try {
				//接收請求參數, 輸入格式的錯誤處理
				
				String emp_id = req.getParameter("emp_id").trim();
				
				String emp_name = req.getParameter("emp_name");
				String emp_nameReg ="^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,20}$";
				if (emp_name == null || emp_name.trim().length() == 0) {
					errorMsgs.add("員工姓名:請勿空白");
				}else if (!emp_name.trim().matches(emp_nameReg)) {
					errorMsgs.add("員工姓名:只能是中, 英文字母, 數字和 _, 且長度必須在2到20之間");
				}
				
				String emp_pwd = req.getParameter("emp_pwd");
				if (emp_pwd == null || emp_pwd.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
			    }
				
				byte[] emp_pic =null;
				Part part =req.getPart("emp_pic");				
				
				InputStream in = part.getInputStream();
				
				if(in.available()==0) {
					EmpService empSvc = new EmpService();
					empVO= empSvc.getOnePic(emp_id);
					emp_pic=empVO.getEmp_pic();
				}else {
					
				emp_pic = new byte[in.available()];
				in.read(emp_pic);
				in.close();
				}
				
				String emp_phone = req.getParameter("emp_phone");
				if (emp_phone == null|| emp_phone.trim().length() == 0){
		           	errorMsgs.add("請輸入電話號碼");
		        }
				String emp_email = req.getParameter("emp_email");
				if (emp_email == null || emp_email.trim().length() == 0) {
					errorMsgs.add("電子信箱請勿空白");
				}
				String emp_city = req.getParameter("emp_city");
				if (emp_city == null || emp_city.trim().length() ==0) {
					errorMsgs.add("居住城市請勿空白");
				}
				String emp_town = req.getParameter("emp_town");
				if (emp_town == null || emp_town.trim().length() ==0) {
					errorMsgs.add("居住鄉鎮請勿空白");
				}
				
				String emp_address = req.getParameter("emp_address");
				if (emp_address == null || emp_address.trim().length() ==0) {
					errorMsgs.add("居住地址請勿空白");
				}
				
				String emp_status = req.getParameter("emp_status");
				if (emp_status == null || emp_status.trim().length() ==0) {
					errorMsgs.add("員工狀態請勿空白");
				}
			
				String emp_date = req.getParameter("emp_date");
				String title_no = req.getParameter("title_no").trim();
				String dept_no = req.getParameter("dept_no").trim();
				
				empVO.setEmp_name(emp_name);
				empVO.setEmp_pwd(emp_pwd);
				empVO.setEmp_pic(emp_pic);
				empVO.setEmp_phone(emp_phone);
				empVO.setEmp_email(emp_email);
				empVO.setEmp_city(emp_city);
				empVO.setEmp_town(emp_town);
				empVO.setEmp_address(emp_address);
				empVO.setEmp_status(emp_status);
				empVO.setEmp_date(java.sql.Date.valueOf(emp_date));
				empVO.setTitle_no(title_no);
				empVO.setDept_no(dept_no);
				empVO.setEmp_id(emp_id);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/emp/protected/Backend.jsp");
					failureView.forward(req, res);
					return;
					}
				//開始員工自己修改資料
				EmpService empSvc = new EmpService();
				empVO = empSvc.updateEmp(emp_id, emp_name, emp_pwd, emp_pic, emp_phone, emp_email, emp_city, emp_town, 
				emp_address, emp_status, java.sql.Date.valueOf(emp_date), title_no, dept_no);

				//修改完成, 準備轉交
				req.setAttribute("empVO",empVO);
				System.out.println(emp_id);
				
				String url = "/backend/emp/protected/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req,res);
				//其他可能的錯誤處理
		        }catch (Exception e) {
		        	errorMsgs.add("修改資料失敗:");
		        	e.printStackTrace();
		        	req.setAttribute("empVO", empVO);
		        	RequestDispatcher failureView =req.getRequestDispatcher("/backend/emp/protected/Backend.jsp");
		        	failureView.forward(req, res);
		        	}
		        }

	}

}
