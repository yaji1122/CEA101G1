package com.members.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.Base64.Decoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.members.model.MembersService;
import com.members.model.MembersVO;

import security.SecureUtils;

/**
 * Servlet implementation class LoginHandler
 */
@MultipartConfig
@WebServlet("/LoginHandler")
public class LoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginHandler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action").trim();
		
		if ("member-login".equals(action)) {
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
			SecureUtils security = new SecureUtils();
			HttpSession user_session = req.getSession();
			try {
				String mb_email = req.getParameter("mb_email").trim();
				String mb_pwd = req.getParameter("mb_pwd").trim();
				String pass = req.getParameter("pass");
				MembersService memberSvc = new MembersService();
				MembersVO member = memberSvc.getOneByMbEmail(mb_email);
				if (member == null) {
					res.setContentType("text; charset=utf8");
					out.print("email_not_found");
					return;
				}
				Decoder decoder = Base64.getDecoder();
				byte[] salt = decoder.decode(member.getMb_salt());
				if (!member.getMb_pwd().equals(security.getSecurePassword(mb_pwd, salt))) {
					res.setContentType("text; charset=utf8");
					out.print("pwd_incorrect");
					return;
				}
				if ("pass".equals(pass)) { // 如果通過就存使用者的SeesionID 和 電子郵件 在Cookie裏，用於恢復登入狀態
					String sessionID = req.getSession().getId(); // 獲取使用者的sessionID
					Cookie user_session_id = new Cookie("user_session_id", sessionID);
					Cookie user_email = new Cookie("user_email", member.getMb_email());
					user_session_id.setMaxAge(24 * 60 * 60 * 30); // 設定cookie存活時間為一個月
					res.addCookie(user_session_id); // 加入cookie到使用者瀏覽器
					res.addCookie(user_email);
					Object locationObj =  user_session.getAttribute("location"); // breaching record
					String location = null;
					if (locationObj == null) {
						location = req.getParameter("location").toString();
						if(location.contains("booking.jsp")) {
							location = location.replace("frontend/roomrsv/booking.jsp", "booking/Available");
						}
					} else {
						location = (String) locationObj;
						user_session.removeAttribute("location");
					}
					
					req.getSession().setAttribute("member", member);
					res.sendRedirect((String) location);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if ("member-logout".equals(action)) {
			res.setCharacterEncoding("UTF-8");
			Cookie user_email = new Cookie("user_email", "");
			Cookie user_session_id = new Cookie("user_session_id", "");
			req.getSession().removeAttribute("member");
			user_email.setMaxAge(0);
			user_session_id.setMaxAge(0);
			res.addCookie(user_email);
			res.addCookie(user_session_id);
			res.sendRedirect(req.getContextPath() + "/frontend/index.jsp");
		}
	}

}
