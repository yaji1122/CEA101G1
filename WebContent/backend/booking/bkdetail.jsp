<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.bookingdetail.model.*"%>
<%@ page import="java.util.List"%>
<%
	List<BookingDetailVO> bkdetailList = (List<BookingDetailVO>) request.getAttribute("bkdetailList");
int totalPrice = 0;
for (BookingDetailVO vo : bkdetailList) {
	totalPrice += vo.getRm_subtotal();
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>客房訂單詳情</title>
</head>
<style>
* {
	box-sizing: border-box;
	font-family: arial;
}
.wrapper {
	padding: 20px 10px;
}
table {
	width: 100%;
	border-spacing: 0;
    border-collapse: collapse;
}

table th {
	border-bottom: 1px solid grey;
	min-width:150px;
}

table tr.room {
	text-align: center;
}

h4.totalPrice {
	text-align: right;
	font-size: 16px;
	font-weight: 400;
	border-bottom: 1px solid grey;
}
h4.totalPrice span {
	font-size:18px;
	font-weight:700;
}
</style>
<body>
	<jsp:useBean id="rmtypeSvc" scope="page"
		class="com.roomtype.model.RoomTypeService" />
	<div class="wrapper">
		<table>
			<tr>
				<th>客房</th>
				<th>房型名稱</th>
				<th>入住人數</th>
				<th>價格小計</th>
			</tr>
			<%
				int i = 1;
			%>
			<c:forEach var="detail" items="${bkdetailList}">
				<tr class="room">
					<td>房間 <%=i++%></td>
					<td>${rmtypeSvc.getOne(detail.rm_type).type_name}</td>
					<td>${detail.rm_guest }</td>
					<td>${detail.rm_subtotal }</td>
				</tr>
			</c:forEach>
		</table>
		<h4 class="totalPrice">
			訂單總額：USD$ <span><%=totalPrice%></span> </h4>
	</div>
</body>
</html>