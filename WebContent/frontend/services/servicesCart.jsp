<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.services_cart.model.*"%>
<%@ page import="com.services_cart.controller.*"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mode II 範例程式 - Cart.jsp</title>
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
				<th></th>
			</tr>
		</thead>

		<%
			for (int index = 0; index < buylist.size(); index++) {
			ServicesItem order = buylist.get(index);
		%>
		<tbody>
			<tr>
				<td><%=order.getServicesNo()%></td>
			    <%-- <td><fmt:formatDate value="${order.servTime}"
							pattern="yyyy-MM-dd HH:mm" /></td> --%>
				<td><%=order.getServTime()%></td>
				<td><%=order.getPrice()%></td>
				<td><%=order.getQuantity()%></td>
				<td><%=order.getLocation()%></td>
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
			type="submit" value="付款結帳">
	</form>
	<%}%>
</body>
</html>