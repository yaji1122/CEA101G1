package com.choppers.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.choppers.model.*;

@MultipartConfig
@WebServlet("/ChopperServlet")
public class ChopperServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ChopperServlet() {
		super();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text; charset=utf-8");
		PrintWriter out = null;
		String action = req.getParameter("action").trim();
		BufferedInputStream bis = null;
		RequestDispatcher dispatcher = null;
		
		if ("insert_chopper".equals(action)) {
			Part part = req.getPart("chop_pic");
			String chop_name = req.getParameter("chop_name");
			Integer chop_price = null;
			try {
				chop_price = Integer.valueOf(req.getParameter("chop_price"));
			} catch (NumberFormatException e) {
				dispatcher = req.getRequestDispatcher(req.getContextPath() + "/choppers/chopInfo.jsp");
				req.setAttribute("msg", "為什麼價格裡面不給我打數字？");
				dispatcher.forward(req, res);
			}
			
			String chop_info = req.getParameter("chop_info");
			bis = new BufferedInputStream(part.getInputStream());
			byte[] chop_pic = new byte[bis.available()];
			bis.read(chop_pic);
			ChoppersService chopSvc = new ChoppersService();
			chopSvc.addChopper(chop_pic, chop_name, chop_price, chop_info);
			out = res.getWriter();
			out.print("success");
			return;
		}

		if ("update_chopper".equals(action)) {
			String chop_no = req.getParameter("update-chopno");
			String chop_name = req.getParameter("update-chopname");
			Integer chop_price = Integer.valueOf(req.getParameter("update-chopprice"));
			String chop_info = req.getParameter("update-chopinfo");
			
			Part part = req.getPart("update-choppic"); //有圖片就送～
			ChoppersService chopSvc = new ChoppersService();
			if (part != null && part.getContentType().indexOf("image") >= 0) {
				bis = new BufferedInputStream(part.getInputStream());
				byte[] chop_pic = new byte[bis.available()];
				bis.read(chop_pic);
				chopSvc.updateChopperPic(chop_no, chop_pic);
			}
			chopSvc.updateChopper(chop_no, chop_name, chop_price, chop_info);
			req.setAttribute("msg", "更新成功");
			dispatcher = req.getRequestDispatcher("/backend/choppers/chopInfo.jsp");
			dispatcher.forward(req, res);
			return;
		}
		
		if ("update_chopper_status".equals(action)) {
			String chop_no = req.getParameter("chop_no");
			String chop_status = req.getParameter("chop_status");
			ChoppersService chopSvc = new ChoppersService();
			chopSvc.updateChopperStatus(chop_no, chop_status);
			return;
		}
		
		if("get_choppic".equals(action)) {
			System.out.print("hi");
			String chop_no = req.getParameter("chop_no");
			ChoppersService chopSvc = new ChoppersService();
			ChoppersVO chopvo = chopSvc.getOneByChopNo(chop_no);
			InputStream is = null;
			byte[] chop_pic = chopvo.getChop_pic();
			if (chop_pic != null) {
				res.getOutputStream().write(chop_pic);
			} else {
				is = req.getServletContext().getResourceAsStream("/images/nodata/nodata.png");
				byte[] pic = new byte[is.available()];
				is.read(pic);
				res.getOutputStream().write(pic);
				is.close();
			}
			return;
		}
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
}
