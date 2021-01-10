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

    if ("insert".equals(action)) { 
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		PrintWriter out = res.getWriter();
		try {
			String actEventNo = req.getParameter("eventno");
			String actEventName = req.getParameter("eventname").trim();
			ActEventVO actEventVO = new ActEventVO();
			actEventVO.setActEventNo(actEventNo);
			actEventVO.setActEventName(actEventName);
			ActEventService ActEventSvc = new ActEventService();
			actEventVO = ActEventSvc.addActEvent(actEventNo, actEventName);
			out.print("success");
		} catch (Exception e) {
			e.printStackTrace();
			out.print("fail");
		}
	} 
    
    //�R��
    if ("delete".equals(action)) { // �Ӧ�listAllAct.jsp

		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/***************************1.�����ШD�Ѽ�***************************************/
			String actEventNo = req.getParameter("actEventNo");
			
			/***************************2.�}�l�R�����***************************************/
			ActEventService ActEventSvc = new ActEventService();
			ActEventSvc.deleteActEvent(actEventNo);
			
			/***************************3.�R������,�ǳ����(Send the Success view)***********/								
			String url = "/backend/actevent/act_event_listAll.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
			successView.forward(req, res);
			
			/***************************��L�i�઺���~�B�z**********************************/
		} catch (Exception e) {
			errorMsgs.add("�R����ƥ���:"+e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/backend/actevent/act_event_listAll.jsp");
			failureView.forward(req, res);
		}
	}
        if ("update".equals(action)) { 
        	PrintWriter out = res.getWriter();
  			try {
  				String actEventNo = new String(req.getParameter("eventno").trim());
  				String actEventName = req.getParameter("eventname");
  				ActEventService ActEventSvc = new ActEventService();
  				ActEventSvc.updateActEvent(actEventNo,actEventName);
  				out.print("success");
  			} catch (Exception e) {
  				e.printStackTrace();
  				out.print("fail");
  			}
  		}
    
    
  }
}
