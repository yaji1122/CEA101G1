package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.*;

public class EmpLoginFilter implements Filter {
	
	private FilterConfig config;
	
	public void init (FilterConfig config) {
		this.config = config;
	}
	
	public void destroy() {
		config = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//進行強轉型
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		//取得session
		HttpSession session = req.getSession();
		//讓登出後不能再被快取進來
		res.setHeader("Cache-Control", "no-store");
		res.setHeader("Pragma", "no-cache");
		res.setDateHeader("Expires",0);
		//從session判斷emp是否登入過
		Object empVO = session.getAttribute("empVO");
		if (empVO == null) {
			session.setAttribute("location", req.getRequestURI());
			res.sendRedirect(req.getContextPath()+"/loginEmp.jsp");
			return;
		}else {
			chain.doFilter(request, response);
		}	
	}
  
}
