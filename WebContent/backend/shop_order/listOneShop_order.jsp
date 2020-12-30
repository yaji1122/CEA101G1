<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.shop_order.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<%
	Shop_orderVO shop_orderVO = (Shop_orderVO) request.getAttribute("shop_orderVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>商品訂單資料 - listOneShop_order.jsp</title>

<style>
#title {
	width: 100%;
	height: auto;
	margin-top: 15px;
	background-color: rgb(204, 202, 202);
	border: 1px solid;
	font-size: 30px;
	text-align: center;
	padding-top: 5px;
	display: flex;
	flex-direction: row;
}

.titleAll {
	margin-left: auto;
	margin-right: auto;
}

.home {
	margin-left: 0px;
	margin-right: 5px;
	float: right;
	font-size:27px;
}

table {
	width: 1100px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
	margin-left: auto;
	margin-right: auto;
	border: 1px gray;
}

tr:nth-of-type(even) {
	background-color: #f3f3f3;
}

tr:nth-of-type(odd) {
	background-color: #ddd;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>
<%@ include file="/backend/files/backend_header.file" %>
</head>
<body bgcolor='white'>
<%@ include file="/backend/files/backend_sidebar.file" %>
	<div id="title">

		<div class="titleAll">商品訂單資料</div>
		<div>
			<a href="<%=request.getContextPath()%>/backend/shop_order/select_shop_order.jsp"
				class="home">回商品訂單管理</a>
		</div>

	</div>
	<table>
		<tr>
			<th>訂單編號</th>
			<th>會員編號</th>
			<th>訂單成立時間</th>
			<th>訂單狀態</th>
			<th>出貨時間</th>
			<th>訂單總價</th>
			<th>總積分</th>
			<th>客房號碼</th>
		</tr>

		<jsp:useBean id="shop_orderSvc" scope="page"
			class="com.shop_order.model.Shop_orderService" />

		<tr>
			<td>${shop_orderVO.sp_odno}</td>
			<td>${shop_orderVO.mb_id}</td>
			<td><fmt:formatDate value="${shop_orderVO.sp_time}"
					pattern="yyyy-MM-dd HH:mm:ss" /></td>
			<td>${shop_orderVO.sp_status}</td>
			<td><fmt:formatDate value="${shop_orderVO.sp_dlvr}"
					pattern="yyyy-MM-dd HH:mm:ss" /></td>
			<td>${shop_orderVO.total_price}</td>
			<td>${shop_orderVO.points_total}</td>
			<td>${shop_orderVO.rm_no}</td>
		</tr>
	</table>
<%@ include file="/backend/files/backend_footer.file" %> 
</body>
</html>