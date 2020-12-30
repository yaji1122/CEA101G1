<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.item_type.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<%
	Item_typeVO item_typeVO = (Item_typeVO) request.getAttribute("item_typeVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>商品類別資料 - listOneItem_type.jsp</title>

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

</head>
<body bgcolor='white'>

	<div id="title">

		<div class="titleAll">商品資料</div>
		<div>
			<a href="<%=request.getContextPath()%>/backend/item/select_item.jsp"
				class="home">回商品管理</a>
		</div>

	</div>
	<table>
		<tr>
			<th>商品編號</th>
			<th>商品名稱</th>
		</tr>

		<jsp:useBean id="item_typeSvc" scope="page"
			class="com.item_type.model.Item_typeService" />

		<tr>
			<td>${item_typeVO.item_type_no}</td>
			<td>${item_typeVO.type_name}</td>
		</tr>
	</table>

</body>
</html>