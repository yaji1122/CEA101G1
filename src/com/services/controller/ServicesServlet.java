package com.services.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.services.model.*;

@MultipartConfig
@WebServlet("/ServicesServlet")
public class ServicesServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String str = req.getParameter("serv_no");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("請輸入員工編號");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
			String serv_no = null;
			serv_no = str;
//				try {
//					serv_no = new Integer(str);
//				} catch (Exception e) {
//					errorMsgs.add("員工編號格式不正確");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************2.開始查詢資料*****************************************/
			ServicesService servicesSvc = new ServicesService();
			ServicesVO servicesVO = servicesSvc.getOneServices(serv_no);
//				if (servicesVO == null) {
//					errorMsgs.add("查無資料");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("servicesVO", servicesVO); // 資料庫取出的servicesVO物件,存入req
			String url = "/backend/services/servicesInfo.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneServices.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 *************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/emp/select_page.jsp");
//				failureView.forward(req, res);
//			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String serv_no = req.getParameter("serv_no");

				/*************************** 2.開始查詢資料 ****************************************/
				ServicesService servicesSvc = new ServicesService();
				ServicesVO servicesVO = servicesSvc.getOneServices(serv_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("servicesVO", servicesVO); // 資料庫取出的servicesVO物件,存入req
				String url = "/backend/services/update_services_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/services/servicesInfo.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String serv_no = req.getParameter("serv_no").trim();
			ServicesService servicesService = new ServicesService();

			String serv_name = req.getParameter("serv_name");

			String serv_type_no = req.getParameter("serv_type_no").trim();
			String serv_status = req.getParameter("serv_status").trim();

			Integer serv_price = null;
			serv_price = new Integer(req.getParameter("serv_price").trim());

//			InputStream in = req.getPart("memPhoto").getInputStream();
//		    byte[] memPhoto = null;
//		    if (session.getAttribute("memPhoto") == null) {
//		     if (in.available() == 0) {
//		      MemService memSvc = new MemService();
//		      MemVO memVO = memSvc.getOneMem(memPhone);
//		      memPhoto = memVO.getMemPhoto();
//		     }else {
//		      memPhoto = new byte[in.available()];
//		      session.setAttribute("memPhoto", memPhoto);
//		      in.read(memPhoto);
//		      in.close();
//		     }
//		    } else {
//		     memPhoto = (byte[]) session.getAttribute("memPhoto");
//		     in.read(memPhoto);
//		     in.close();
//		    }

			byte[] buf = null;
			byte[] serv_pic = null;
			Part part = req.getPart("serv_pic");
			InputStream in = part.getInputStream();
			buf = new byte[in.available()];
			if (buf.length != 0) {

				in.read(buf);
				serv_pic = buf;
				in.close();
			} else if (buf.length == 0) {
				serv_no = req.getParameter("serv_no");
				ServicesService servicesSvc = new ServicesService();
				buf = servicesSvc.getOneByPicNo(serv_no);
				serv_pic = buf;
			}

//			Part part = req.getPart("serv_pic");
//			System.out.println(part.getContentType());
//			InputStream in = part.getInputStream();
//			byte[] serv_pic = new byte[in.available()];
//			in.read(serv_pic);
//			in.close();

			String serv_info = req.getParameter("serv_info").trim();

			Integer serv_dura = null;
			serv_dura = new Integer(req.getParameter("serv_dura").trim());

			Integer serv_ppl = null;
			serv_ppl = new Integer(req.getParameter("serv_ppl").trim());

			ServicesVO servicesVO = new ServicesVO();
			servicesVO.setServ_no(serv_no);
			servicesVO.setServ_type_no(serv_type_no);
			servicesVO.setServ_status(serv_status);
			servicesVO.setServ_price(serv_price);
			servicesVO.setServ_pic(serv_pic);
			servicesVO.setServ_info(serv_info);
			servicesVO.setServ_name(serv_name);
			servicesVO.setServ_dura(serv_dura);
			servicesVO.setServ_ppl(serv_ppl);

			System.out.println(serv_pic);

			/*************************** 2.開始修改資料 *****************************************/
			ServicesService servicesSvc = new ServicesService();
			servicesVO = servicesSvc.updateServices(serv_no, serv_type_no, serv_status, serv_price, serv_pic, serv_info,
					serv_name, serv_dura, serv_ppl);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("servicesVO", servicesVO); // 資料庫update成功後,正確的的servicesVO物件,存入req
			String url = "/backend/services/servicesInfo.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneServices.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 *************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/backend/services/update_services_input.jsp");
//				failureView.forward(req, res);
//			}
		}

		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String serv_no = req.getParameter("serv_no").trim();

				ServicesService servicesService = new ServicesService();
				List<ServicesVO> services = servicesService.getAll();
				for (ServicesVO serv : services) {
					if (serv.getServ_no().equals(serv_no)) {
						req.setAttribute("msg", "Oops!服務編號重複");
						String url = "/backend/services/servicesInfo.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url);
						successView.forward(req, res);
						return;
					}
				}

				String serv_name = req.getParameter("serv_name");

				String serv_type_no = req.getParameter("serv_type_no").trim();
				String serv_status = req.getParameter("serv_status").trim();

				Integer serv_price = null;
				serv_price = new Integer(req.getParameter("serv_price").trim());

				Part part = req.getPart("serv_pic");
				InputStream in = part.getInputStream();
				byte[] serv_pic = new byte[in.available()];
				in.read(serv_pic);
				in.close();

				String serv_info = req.getParameter("serv_info").trim();
				Integer serv_dura = null;
				serv_dura = new Integer(req.getParameter("serv_dura").trim());

				Integer serv_ppl = null;
				serv_ppl = new Integer(req.getParameter("serv_ppl").trim());

				ServicesVO servicesVO = new ServicesVO();
				servicesVO.setServ_no(serv_no);
				servicesVO.setServ_type_no(serv_type_no);
				servicesVO.setServ_status(serv_status);
				servicesVO.setServ_price(serv_price);
				servicesVO.setServ_pic(serv_pic);
				servicesVO.setServ_info(serv_info);
				servicesVO.setServ_name(serv_name);
				servicesVO.setServ_dura(serv_dura);
				servicesVO.setServ_ppl(serv_ppl);

				/*************************** 2.開始新增資料 *****************************************/
				ServicesService servicesSvc = new ServicesService();
				servicesVO = servicesSvc.addServices(serv_no, serv_type_no, serv_status, serv_price, serv_pic,
						serv_info, serv_name, serv_dura, serv_ppl);

				/*************************** 3.新增完成,準備轉交(Send the Success view) *************/
				String url = "/backend/services/servicesInfo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneServices.jsp
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/services/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String serv_no = req.getParameter("serv_no");

				/*************************** 2.開始刪除資料 ***************************************/
				ServicesService servicesSvc = new ServicesService();
				servicesSvc.deleteServices(serv_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/backend/services/servicesInfo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/services/servicesInfo.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOneServicesPic".equals(action)) {
			res.setContentType("img/jpg");
			String servno = req.getParameter("servno").trim();
			ServicesService servicesSvc = new ServicesService();
			byte[] servpic = servicesSvc.getOneByPicNo(servno);
			if (servpic != null) {
				res.getOutputStream().write(servpic);
				res.getOutputStream().flush();
				return;
			} else {
				InputStream ispu = null;
				ispu = req.getServletContext().getResourceAsStream("/img/nodata.png");
				byte[] pic = new byte[ispu.available()];
				ispu.read(pic);
				res.getOutputStream().write(pic);
				ispu.close();
			}
		}

	}
}