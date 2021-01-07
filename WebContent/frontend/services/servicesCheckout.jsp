<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.time.LocalDateTime"%>
<%@ page import="com.services.model.*"%>
<%@ page import="com.services_cart.model.*"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>預約服務結帳</title>
</head>
<body>
    <%
			Vector<ServicesItem> buylist = (Vector<ServicesItem>) session.getAttribute("shoppingcart");
		String amount = (String) request.getAttribute("amount");
		%>
		<%
			for (int i = 0; i < buylist.size(); i++) {
			ServicesItem order = buylist.get(i);
			String servicesNo = order.getServicesNo();
			Integer price = order.getPrice();
			Integer quantity = order.getQuantity();
			String location = order.getLocation();
			LocalDateTime servTime = order.getServTime();
		%>
	<table>
		<tr>
			<th>服務名稱</th>
			<th>時間</th>
			<th>價格</th>
			<th>數量</th>
			<th>地點</th>
			<th></th>
		</tr>

		
		<tr>
			<td><%=servicesNo%></td>
			<td><%=servTime%></td>
			<td><%=price%></td>
			<td><%=quantity%></td>
			<td><%=location%></td>
		</tr>

		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td><div align="center">
					<font color="red"><b>總金額：</b></font>
				</div></td>
			<td></td>
			<td><font color="red"><b>$<%=amount%></b></font></td>
			<td></td>
		</tr>
	</table>
	<a href="<%=request.getContextPath()%>/frontend/services/services.jsp">是否繼續購物</a><br>
	<form method="post"
		action="${pageContext.request.contextPath}/ServiceOrderServlet">
		<button type="submit">
			確定送出
		</button>
		<input type="hidden" name="action" value="insert"> <input
			type="hidden" name="serv_no" value="<%=servicesNo%>"> <input
			type="hidden" name="serv_time" value="<%=servTime%>">
			<!-- 2020-12-25 00:00:00.0 -->
		<input type="hidden" name="serv_count" value="<%=quantity%>">
		<input type="hidden" name="total_price" value="<%=amount%>">

	</form>
	<%
		}
	%>
</body>
</html>
