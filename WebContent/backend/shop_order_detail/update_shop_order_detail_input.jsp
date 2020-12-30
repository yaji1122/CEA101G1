<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shop_order_detail.model.*"%>

<%
	Shop_order_detailVO shop_order_detailVO = (Shop_order_detailVO) request.getAttribute("shop_order_detailVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>商品資料修改 - update_item_input.jsp</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/backend-shop.css" />
<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>

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
	font-size: 27px;
}

table {
	width: 800px;
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

.modItem {
	margin-left: 250px;
}

.inputItem {
	margin-left: 920px;
}

.inputData {
	float: left;
}
</style>

</head>
<body bgcolor='white'>

	<div id="title">

		<div class="titleAll">修改商品資料</div>
		<div>
			<a href="<%=request.getContextPath()%>/backend/shop_order/select_shop_order.jsp"
				class="home">回商品管理</a>
		</div>

	</div>

	<h3 class="modItem">資料修改:</h3>

	<%-- 錯誤表列 --%>

	<c:if test="${not empty errorMsgs}">
		<div style="color: red" class="error">
			<div class="errorfix">請修正以下錯誤</div>
			<div class="close">✖</div>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</div>
	</c:if>

	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/shop_order_detail/shop_order_detail.do"
		name="form1">
		<table>
			<tr>
				<td>訂單編號:<font color=red><b></b></font></td>
				<td class="inputData">${shop_order_detailVO.sp_odno}</td>
			</tr>
			<tr>
				<td>商品編號:<font color=red><b></b></font></td>
				<td class="inputData">${shop_order_detailVO.item_no}</td>
			</tr>

			<tr>
				<td>商品數量:</td>
				<td class="inputData"><input name="qty" type="text"
					size="45" value="${shop_order_detailVO.qty}" required></td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="update"> 
		<input type="hidden" name="item_no" value="${shop_order_detailVO.item_no}">
		<input type="hidden" name="sp_odno" value="${shop_order_detailVO.sp_odno}">
		  
		<input type="submit" value="送出修改" class="inputItem">
	</FORM>
	<script src="${pageContext.request.contextPath}/js/backShopItem.js"></script>
</body>
</html>