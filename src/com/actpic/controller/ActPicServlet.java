package com.actpic.controller;

import java.io.*;

import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.actpic.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class ActPicServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		

        //嚙範嚙踝蕭
		if ("getOne_For_Display".equals(action)) { // 嚙諉佗蕭select_page.jsp嚙踝蕭嚙請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.嚙踝蕭嚙踝蕭嚙請求嚙諸潘蕭 - 嚙踝蕭J嚙賣式嚙踝蕭嚙踝蕭嚙羯嚙畿嚙緲**********************/
				String actPicNo = req.getParameter("actPicNo");
				if (actPicNo == null || (actPicNo.trim()).length() == 0) {
					errorMsgs.add("嚙請選蕭傮茪嚙踝蕭s嚙踝蕭");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/actpic/act_pic_select_page.jsp");
					failureView.forward(req, res);
					return;//嚙緹嚙踝蕭嚙踝蕭嚙稻
				}
				
				
//				String actPicNo = null;
//				try {
//					actPicNo = new String(str);
//				} catch (Exception e) {
//					errorMsgs.add("嚙編嚙踝蕭嚙賣式嚙踝蕭嚙踝蕭嚙確");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/actpic/act_pic_select_page.jsp");
//					failureView.forward(req, res);
//					return;//嚙緹嚙踝蕭嚙踝蕭嚙稻
//				}
				
				/***************************2.嚙罷嚙締嚙範嚙賠賂蕭嚙�*****************************************/
				ActPicService actPicSvc = new ActPicService();
				ActPicVO actPicVO =  actPicSvc.getOneActPic(actPicNo);
				
				if (actPicVO == null) {
					errorMsgs.add("嚙範嚙盤嚙踝蕭嚙�");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/actpic/act_pic_select_page.jsp");
					failureView.forward(req, res);
					return;//嚙緹嚙踝蕭嚙踝蕭嚙稻
				}
				
				/***************************3.嚙範嚙賠改蕭嚙踝蕭,嚙褒喉蕭嚙踝蕭嚙�(Send the Success view)*************/
				req.setAttribute("actPicVO", actPicVO); // 嚙踝蕭w嚙踝蕭嚙碼嚙踝蕭actTypeVO嚙踝蕭嚙踝蕭,嚙編嚙皚req
				String url = "/back-end/actpic/act_pic_listOne.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 嚙踝蕭嚙穀嚙踝蕭嚙� listOneAct.jsp
				successView.forward(req, res);

				/***************************嚙踝蕭L嚙箠嚙賞的嚙踝蕭嚙羯嚙畿嚙緲*************************************/
			} catch (Exception e) {
				errorMsgs.add("嚙盤嚙糊嚙踝蕭嚙緻嚙踝蕭嚙�:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/actpic/act_pic_select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		//嚙編嚙磕
        if ("insert".equals(action)) { // 嚙諉佗蕭addAct.jsp嚙踝蕭嚙請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.嚙踝蕭嚙踝蕭嚙請求嚙諸潘蕭 - 嚙踝蕭J嚙賣式嚙踝蕭嚙踝蕭嚙羯嚙畿嚙緲*************************/
				
				String actEventNo = req.getParameter("ActEventNo");
				if (actEventNo == null || actEventNo.trim().length() == 0) {
					errorMsgs.add("嚙踝蕭嚙褊塚蕭嚙諍編嚙踝蕭: 嚙請勿空伐蕭");
				}
				
				String actPicNo = new String(req.getParameter("ActPicNo").trim());
				if (actPicNo == null || actPicNo.trim().length() == 0) {
					errorMsgs.add("嚙諉歹蕭嚙編嚙踝蕭: 嚙請勿空伐蕭");
				} else if(actPicNo.equals("ActPicNo")) {
					errorMsgs.add(actPicNo+"該編號已重複");
				}
				
				//嚙諉歹蕭嚙磕嚙踝蕭
				Part actPic = req.getPart("ActPic");
				if(actPic == null) {
					errorMsgs.add("嚙請新嚙磕嚙諉歹蕭");
				}
				InputStream picIn = actPic.getInputStream();
				byte[] act_pic = new byte[picIn.available()];
				picIn.read(act_pic);
				picIn.close();

				ActPicVO actPicVO = new ActPicVO();
				actPicVO.setActPicNo(actPicNo);
				actPicVO.setActEventNo(actEventNo);
				actPicVO.setActPic(act_pic);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("actPicVO", actPicVO); // 嚙緣嚙踝蕭嚙踝蕭J嚙賣式嚙踝蕭嚙羯嚙踝蕭actTypeVO嚙踝蕭嚙踝蕭,嚙稽嚙編嚙皚req
					errorMsgs.add("嚙請哨蕭嚙編嚙確嚙緹嚙踝蕭J嚙踝蕭嚙踝蕭e");
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/actpic/backend-act_pic_listAll.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.嚙罷嚙締嚙編嚙磕嚙踝蕭嚙�***************************************/
				ActPicService actPicSvc = new ActPicService();
				actPicVO =  actPicSvc.addActPic(actPicNo, actEventNo,act_pic);
			
		
				/***************************3.嚙編嚙磕嚙踝蕭嚙踝蕭,嚙褒喉蕭嚙踝蕭嚙�(Send the Success view)***********/
				String url = "/backend/actpic/backend-act_pic_listAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 嚙編嚙磕嚙踝蕭嚙穀嚙踝蕭嚙踝蕭嚙締istAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************嚙踝蕭L嚙箠嚙賞的嚙踝蕭嚙羯嚙畿嚙緲**********************************/
			} catch (Exception e) {
				errorMsgs.add("嚙踝蕭J嚙踝蕭嚙踝蕭嚙踝蕭躑嚙�");
				e.printStackTrace();
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/actpic/backend-act_pic_add.jsp");
				failureView.forward(req, res);
			}
		} 
        
      //嚙磋嚙踝蕭
        if ("delete".equals(action)) { // 嚙諉佗蕭listAllAct.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.嚙踝蕭嚙踝蕭嚙請求嚙諸潘蕭***************************************/
				String actPicNo = req.getParameter("ActPicNo");
				
				/***************************2.嚙罷嚙締嚙磋嚙踝蕭嚙踝蕭嚙�***************************************/
				ActPicService ActPicSvc = new ActPicService();
				ActPicSvc.deleteActPic(actPicNo);
				
				/***************************3.嚙磋嚙踝蕭嚙踝蕭嚙踝蕭,嚙褒喉蕭嚙踝蕭嚙�(Send the Success view)***********/								
				String url = "/backend/actpic/backend-act_pic_listAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 嚙磋嚙踝蕭嚙踝蕭嚙穀嚙踝蕭,嚙踝蕭嚙稷嚙箴嚙碼嚙磋嚙踝蕭嚙踝蕭嚙諉瘀蕭嚙踝蕭嚙踝蕭
				successView.forward(req, res);
				
				/***************************嚙踝蕭L嚙箠嚙賞的嚙踝蕭嚙羯嚙畿嚙緲**********************************/
			} catch (Exception e) {
				errorMsgs.add("嚙磋嚙踝蕭嚙踝蕭嚙踝蕭嚙�:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/actpic/backend-act_pic_listAll.jsp");
				failureView.forward(req, res);
			}
		}
        
      //嚙踝蕭s
      		if ("getOne_For_Update".equals(action)) { // 嚙諉佗蕭listAllAct.jsp嚙踝蕭嚙請求

      			List<String> errorMsgs = new LinkedList<String>();
      			// Store this set in the request scope, in case we need to
      			// send the ErrorPage view.
      			req.setAttribute("errorMsgs", errorMsgs);
      			
      			try {
      				/***************************1.嚙踝蕭嚙踝蕭嚙請求嚙諸潘蕭****************************************/
      				String actPicNo = new String(req.getParameter("ActPicNo"));
      				
      				/***************************2.嚙罷嚙締嚙範嚙賠賂蕭嚙�****************************************/
      				ActPicService actPicSvc = new ActPicService();
      				ActPicVO actPicVO = actPicSvc.getOneActPic(actPicNo);
      								
      				/***************************3.嚙範嚙賠改蕭嚙踝蕭,嚙褒喉蕭嚙踝蕭嚙�(Send the Success view)************/
      				req.setAttribute("actPicVO", actPicVO);         // 嚙踝蕭w嚙踝蕭嚙碼嚙踝蕭empVO嚙踝蕭嚙踝蕭,嚙編嚙皚req
      				String url = "/back-end/actpic/act_pic_input_update.jsp";
      				RequestDispatcher successView = req.getRequestDispatcher(url);// 嚙踝蕭嚙穀嚙踝蕭嚙� update_emp_input.jsp
      				successView.forward(req, res);

      				/***************************嚙踝蕭L嚙箠嚙賞的嚙踝蕭嚙羯嚙畿嚙緲**********************************/
      			} catch (Exception e) {
      				errorMsgs.add("嚙盤嚙糊嚙踝蕭嚙緻嚙緯嚙論改的嚙踝蕭嚙�:" + e.getMessage());
      				RequestDispatcher failureView = req
      						.getRequestDispatcher("/backend/actpic/act_pic_listAll.jsp");
      				failureView.forward(req, res);
      			}
      		}
      		
                  if ("update".equals(action)) { // 嚙諉佗蕭update_act_type_input.jsp嚙踝蕭嚙請求
      			
      			List<String> errorMsgs = new LinkedList<String>();
      			// Store this set in the request scope, in case we need to
      			// send the ErrorPage view.
      			req.setAttribute("errorMsgs", errorMsgs);
      		
      			try {
      				/***************************1.嚙踝蕭嚙踝蕭嚙請求嚙諸潘蕭 - 嚙踝蕭J嚙賣式嚙踝蕭嚙踝蕭嚙羯嚙畿嚙緲**********************/
      				String actPicNo = new String(req.getParameter("ActPicNo").trim());
      				if(actPicNo == null || actPicNo.trim().length() == 0) {
      					errorMsgs.add("嚙請選蕭J嚙踝蕭嚙踝蕭嚙編嚙踝蕭");
      				} 
      				
      				String actEventNo = req.getParameter("ActEventNo");
//      		    String actNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
      				if (actEventNo == null || actEventNo.trim().length() == 0) {
      					errorMsgs.add("嚙踝蕭嚙踝蕭嚙踝蕭嚙踝蕭嚙磕嚙踝蕭: 嚙請勿空伐蕭");
      				} 
      				
      				Part actPic = req.getPart("ActPic");
    				InputStream picIn = actPic.getInputStream();
    				byte[] ActPic = new byte[picIn.available()];
    				picIn.read(ActPic);
    				picIn.close();

    				ActPicVO actPicVO = new ActPicVO();
    				actPicVO.setActPicNo(actPicNo);
    				actPicVO.setActEventNo(actEventNo);
    				actPicVO.setActPic(ActPic);
      				

      				
      				// Send the use back to the form, if there were errors
      				if (!errorMsgs.isEmpty()) {
      					req.setAttribute("actPicVO", actPicVO); // 嚙緣嚙踝蕭嚙踝蕭J嚙賣式嚙踝蕭嚙羯嚙踝蕭actTypeVO嚙踝蕭嚙踝蕭,嚙稽嚙編嚙皚req
      					RequestDispatcher failureView = req
      							.getRequestDispatcher("/backend/actpic/act_pic_input_update.jsp");
      					failureView.forward(req, res);
      					return; //嚙緹嚙踝蕭嚙踝蕭嚙稻
      				}
      				
      				/***************************2.嚙罷嚙締嚙論改蕭嚙踝蕭*****************************************/
      				ActPicService ActPicSvc = new ActPicService();
      				actPicVO = ActPicSvc.updateActPic(actPicNo, actEventNo, ActPic);
      				
      				/***************************3.嚙論改完嚙踝蕭,嚙褒喉蕭嚙踝蕭嚙�(Send the Success view)*************/
      				req.setAttribute("actPicVO", actPicVO); // 嚙踝蕭wupdate嚙踝蕭嚙穀嚙踝蕭,嚙踝蕭嚙確嚙踝蕭嚙踝蕭actPicVO嚙踝蕭嚙踝蕭,嚙編嚙皚req
      				String url = "/backend/actpic/act_pic_listAll.jsp";
      				RequestDispatcher successView = req.getRequestDispatcher(url); // 嚙論改成嚙穀嚙踝蕭,嚙踝蕭嚙締istOneEmp.jsp
      				successView.forward(req, res);

      				/***************************嚙踝蕭L嚙箠嚙賞的嚙踝蕭嚙羯嚙畿嚙緲*************************************/
      			} catch (Exception e) {
      				errorMsgs.add("嚙論改蕭嚙複伐蕭嚙踝蕭:"+e.getMessage());
      				RequestDispatcher failureView = req
      						.getRequestDispatcher("/backend/actpic/act_pic_input_update.jsp");
      				failureView.forward(req, res);
      			}
      		}
	}
}


