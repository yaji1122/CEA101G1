package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.members.model.MembersService;
import com.members.model.MembersVO;

public class GuestFilter implements Filter{
	private FilterConfig config;

	public void init(FilterConfig config) {
		this.config = config;
	}

	public void destroy() {
		config = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws ServletException, IOException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 【取得 session】
		HttpSession session = req.getSession();
		// 【從 session 判斷此user是否登入過】
		MembersVO member = (MembersVO)session.getAttribute("member");
		//判斷請求者是否為會員且已CheckIn
		if (member == null) {
			session.setAttribute("location", req.getRequestURI());
			res.sendRedirect(req.getContextPath() + "/frontend/login.jsp");
			return;
		} else if (!member.getMb_status().equals("2")){
			res.sendRedirect(req.getContextPath() + "/frontend/guestOnly.jsp");
		} else {
			chain.doFilter(request, response);
		}
	}
}
