package com.roompic.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.roompic.model.*;

import imgcompressor.ImgUtil;

@MultipartConfig
@WebServlet("/RoomPicServlet")
public class RoomPicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RoomPicServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String action = req.getParameter("action");
		
		
		if ("getOneRmPic".equals(action)) {
			res.setContentType("img/jpg");
			String rmpicno = req.getParameter("rmpicno").trim();
			RoomPicService rmpicSvc = new RoomPicService();
			byte[] rmpic = ImgUtil.shrink(rmpicSvc.getOneByPicNo(rmpicno), 1400) ;
			res.getOutputStream().write(rmpic);
			res.getOutputStream().flush();
			return;
		}
		
		if ("getOneRandomPic".equals(action)) {
			res.setContentType("img/jpg");
			String rmtype = req.getParameter("rmtype");
			RoomPicService rmpicSvc = new RoomPicService();
			List<RoomPicVO> picList = rmpicSvc.getAllByRoomType(rmtype);
			byte[] rmpic = ImgUtil.shrink(rmpicSvc.getOneByPicNo(picList.get(0).getRm_pic_no()), 300) ;
			res.getOutputStream().write(rmpic);
			res.getOutputStream().flush();
			return;
		}
		
		if ("addRmPic".equals(action)) {
			res.setCharacterEncoding("UTF-8");
			res.setContentType("text; charset=utf-8");
			PrintWriter out = res.getWriter();
			try {
				Collection<Part> parts = req.getParts();
				String rmtype = req.getParameter("rm_type").trim();
				RoomPicService rmpicSvc = new RoomPicService();
				List<RoomPicVO> rmpicvo = rmpicSvc.addRoomPic(rmtype, parts);
				out.print("上傳成功");
			} catch (Exception e) {
				e.printStackTrace();
				out.print("Oops..出錯囉");
			}
		}
		
		if ("deletePic".equals(action)) {
			res.setCharacterEncoding("UTF-8");
			res.setContentType("text; charset=utf-8");
			PrintWriter out = res.getWriter();
			try {
			String picno = req.getParameter("picno");
			RoomPicService rmpicSvc = new RoomPicService();
			rmpicSvc.deleteRoomPic(picno);
			} catch (Exception e) {
				e.printStackTrace();
				out.print("刪除失敗");
			}
			out.print("刪除成功");
		} 

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}
}
