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

public class EmpAuthFilter implements Filter {
	
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
		
		String uri = req.getRequestURI();
		int last = uri.lastIndexOf("/");
		String compare = uri.substring(0, last);
		if (uri.contains("backend_index.jsp") || uri.contains("loginEmp.jsp")) {
			chain.doFilter(request, response);
			return;
		}
		
		TreeMap<String, String[]> maps = new TreeMap<>();
		maps.put("01", new String[] {"emp", "auth", "title","func", "title"});
		maps.put("02", new String[] {"members", "payment"});
		maps.put("03", new String[] {"rooms", "roomtype", "roompic", "pickup","choppers"});
		maps.put("04", new String[] {"booking", "pickup","choppers"});
		maps.put("05", new String[] {"item", "item_pics", "item_type"});
		maps.put("06", new String[] {"shop_order", "shop_order_detail"});
		maps.put("07", new String[] {"act", "actevent", "actpic", "acttype"});
		maps.put("08", new String[] {"actorder"});
		maps.put("09", new String[] {"services", "serviceType"});
		maps.put("10", new String[] {"serviceOrder"});
		maps.put("11", new String[] {"meal","mealtype"});
		maps.put("12", new String[] {"mealorder","mealordertail"});
		
		EmpVO emp = (EmpVO)req.getSession().getAttribute("empVO");
		AuthService authSvc = new AuthService();
		List<AuthVO> authList = authSvc.getAllByEmp(emp.getEmp_id());
		boolean pass = false;
		for (int i = 0; i < authList.size(); i++) {
			String[] filenames =  maps.get(authList.get(i).getFunc_no());
			if (filenames != null) {
				for (int j = 0; j < filenames.length; j++) {
					if(compare.contains(filenames[j])) {
						pass = true;
						break;
					}
				}
			}
		}
		if (pass) {
			chain.doFilter(request, response);
		} else {
			res.sendRedirect(req.getContextPath()+"/backend/backend_index.jsp");
		}
	}
}
