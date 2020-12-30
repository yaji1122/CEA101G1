package com.actpic.controller;

import java.io.*;
import java.util.*;
import javax.servlet.annotation.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.actpic.model.*;



@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
@WebServlet("/ActPicReaderServlet")

 public class ActPicReaderServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public ActPicReaderServlet() {
		super();
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
		
		    String action = req.getParameter("action");
		    
		    if("getOnePic".equals(action)) {
		         res.setContentType("image/*");
		         String actPicNo = req.getParameter("actPicNo").trim();
		         ActPicService actPicSvc = new ActPicService();
		         byte[] act_pic = actPicSvc.getOneActPic(actPicNo).getActPic();
		         //.getActPic("id")需再加上.getActPic()取得照片
		         res.getOutputStream().write(act_pic);
		         res.getOutputStream().flush();
			     return;
			}
		    

		    
	}

}
