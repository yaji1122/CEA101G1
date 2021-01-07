package filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.*;

import com.auth.model.AuthService;
import com.auth.model.AuthVO;
import com.emp.model.EmpVO;
import com.members.model.MembersVO;

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
		String emp_id = (String) session.getAttribute("emp_id");
		//同時存入authVO中的func_no
		AuthService authSvc = new AuthService();
		List<AuthVO> list =authSvc.getAllByEmp(emp_id);
		//把authVO list列出並進行比對
		List<String> func_no=new ArrayList<String>();
		for(AuthVO authVO:list) {
			func_no.add(authVO.getFunc_no());
//			System.out.println(func_no);
			session.setAttribute("func_no", func_no);
		}

		if (empVO == null) {
			session.setAttribute("location", req.getRequestURI());
			res.sendRedirect(req.getContextPath()+"/loginEmp.jsp");
			return;
		}else {
			chain.doFilter(request, response);
		}	
		
	}
  
}
