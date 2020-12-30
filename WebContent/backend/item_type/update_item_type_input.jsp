<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.item_type.model.*"%>

<%
	Item_typeVO item_typeVO = (Item_typeVO) request.getAttribute("item_typeVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<title>商品資料修改 - update_item_type_input.jsp</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/back/backend-shop.css" />
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
<%@ include file="/backend/files/backend_header.file" %>
</head>
<body bgcolor='white'>
<%@ include file="/backend/files/backend_sidebar.file" %>
	<div id="title">

		<div class="titleAll">修改商品類別資料</div>
		<div>
			<a href="<%=request.getContextPath()%>/backend/item/select_item.jsp"
				class="home">回商品管理</a>
		</div>

	</div>

	<h3 class="modItem">資料修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<div style="color: red" class="error">
			<div class="errorfix">請修正以下錯誤</div>
			<div class="closeerror">✖</div>
			<ul style=list-style-type:none>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</div>
	</c:if>

	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/item_type/item_type.do"
		name="form1">
		<table>
			<tr>
				<td>商品類別編號:<font color=red><b></b></font></td>
				<td class="inputData">${item_typeVO.item_type_no}</td>
			</tr>
			<tr>
				<td>商品類別名稱:</td>
				<td class="inputData"><input type="TEXT" name="type_name"
					size="45" value="${item_typeVO.type_name}" /></td>
			</tr>
		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="item_type_no"
			value="${item_typeVO.item_type_no}"> <input
			type="submit" value="送出修改" class="inputItem">
	</FORM>
	<%@ include file="/backend/files/backend_footer.file" %>
	<script src="${pageContext.request.contextPath}/js/back/backShopItem.js"></script>
</body>
</html>