<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page import="com.services_cart.model.*"%>
<%@ page import="com.services_cart.controller.*"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>服務預約</title>
</head>
<body bgcolor="#FFFFFF">

	<%Vector<ServicesItem> buylist = (Vector<ServicesItem>) session.getAttribute("shoppingcart");%>
	<%if (buylist != null && (buylist.size() > 0)) {%>


	<table class="table table-striped">
		<thead>
			<tr>
				<th>服務名稱</th>
				<th>時間</th>
				<th>價格</th>
				<th>人數</th>
				<th>地點</th>
				<th>delete</th>
			</tr>
		</thead>

		<%
			for (int index = 0; index < buylist.size(); index++) {
			ServicesItem order = buylist.get(index);
		%>
		<tbody>
			<tr>
				<td><%=order.getServicesNo()%></td>
				<td><%=order.getServTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))%></td>
				<td><%=order.getPrice()%></td>
				<td><%=order.getQuantity()%></td>
				<td><%=order.getLocations()%></td>
				<td><div align="center">
						<form name="deleteForm"
							action="${pageContext.request.contextPath}/ServicesCartServlet"
							method="POST">
							<input type="hidden" name="action" value="DELETE"> <input
								type="hidden" name="del" value="<%=index%>"> <input
								type="submit" value="刪除">
						</form>
					</div></td>
			</tr>

			<%}%>
		</tbody>
	</table>
	<p>
	
	<form name="checkoutForm"
		action="${pageContext.request.contextPath}/ServicesCartServlet"
		method="POST">
		<input type="hidden" name="action" value="CHECKOUT"> <input
			type="submit" value="預約送出">
	</form>
	<%}else{ %>
	<p>購物車尚無內容</p>
	<%} %>
</body>
</html>