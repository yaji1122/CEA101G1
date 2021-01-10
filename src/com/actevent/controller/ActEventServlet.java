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
