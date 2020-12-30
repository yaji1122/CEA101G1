<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.item.model.*"%>

<%
	ItemVO itemVO = (ItemVO) request.getAttribute("itemVO");
%>

<html>
<head>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/backend-shop.css" />
<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>商品資料新增 - addItem.jsp</title>

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

.addItemTitle {
	margin-left: -600px !important;
}

.inputItem {
	margin-left: 500px !important;
	
}
.inputData{
float:left;
}
</style>
<%@ include file="/backend/files/backend_header.file" %>
</head>
<body bgcolor='white'>
<%@ include file="/backend/files/backend_sidebar.file" %>
	<div id="title">

		<div class="titleAll">商品資料新增</div>
		<div>
			<a href="<%=request.getContextPath()%>/backend/item/select_item.jsp"
				class="home">回商品管理</a>
		</div>

	</div>

	<h3 class="addItemTitle">資料新增:</h3>

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
		ACTION="<%=request.getContextPath()%>/item/item.do"
		name="form1">
		<table>
			<tr>
				<td>商品名稱:</td>
				<td class="inputData"><input type="TEXT" name="item_name" size="45"
					value="<%=(itemVO == null) ? "HERMES EAU TERLEE" : itemVO.getItem_name()%>" /></td>
			</tr>
			<tr>
				<td>商品價格:</td>
				<td class="inputData"><input type="TEXT" name="item_price" size="45"
					value="<%=(itemVO == null) ? "100.00" : itemVO.getItem_price()%>" /></td>
			</tr>
			<tr>
				<td>商品詳情:</td>
				<td class="inputData"><input type="TEXT" name="item_detail" size="45"
					value="<%=(itemVO == null) ? "商品詳情" : itemVO.getItem_detail()%>" /></td>
			</tr>
			<tr>
				<td>商品積分:</td>
				<td class="inputData"><input type="TEXT" name="points" size="45"
					value="<%=(itemVO == null) ? "100" : itemVO.getPoints()%>" /></td>
			</tr>

			<jsp:useBean id="item_typeSvc" scope="page"
				class="com.item_type.model.Item_typeService" />
			<tr>
				<td>商品類別:<font color=red><b>*</b></font></td>
				<td class="inputData"><select size="1" name="item_type_no">
						<c:forEach var="item_typeVO" items="${item_typeSvc.allItem_type}">
							<option value="${item_typeVO.item_type_no}"
								${(itemVO.item_type_no==item_typeVO.item_type_no)? 'selected':'' }>${item_typeVO.type_name}
						</c:forEach>
				</select></td>
			</tr>

			<tr>
				<td>商品狀態:<font color=red><b>*</b></font></td>
				<td class="inputData"><select size="1" name="item_status">
						<option value="1">上架
						<option value="0">下架
				</select></td>
			</tr>
			<tr>
				<td>商品促銷狀態:<font color=red><b>*</b></font></td>
				<td class="inputData"><select size="1" name="item_on_sale">
						<option value="1">促銷中
						<option value="0">沒促銷，原價售出
				</select></td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增" class="inputItem">
	</FORM>
	<%@ include file="/backend/files/backend_footer.file" %>
	<script src="${pageContext.request.contextPath}/js/backShopItem.js"></script>
</body>

</html>