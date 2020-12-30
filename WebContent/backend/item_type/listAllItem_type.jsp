<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.item_type.model.*"%>
<%
	Item_typeService item_typeSvc = new Item_typeService();
	List<Item_typeVO> list = item_typeSvc.getAllItem_type();
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>所有商品類別資料 - listAllItem_type.jsp</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/datatables.min.css" />
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
	width: 820px;
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

.footer {
	margin-left: auto;
	margin-right: auto;
	text-align: center;
}
</style>

</head>
<body bgcolor='white'>

	<div id="title">

		<div class="titleAll">所有商品類別資料</div>
		<div>
			<a href="<%=request.getContextPath()%>/backend/item_type/addItem_type.jsp"
				class="home">新增商品類別</a>
		</div>

	</div>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table id="myTable">
		<thead>
			<tr>
				<th>商品類別</th>
				<th>商品類別編號</th>
				<th>修改</th>

			</tr>
		</thead>
		<%-- 		<%@ include file="page1.file"%> --%>


		<c:forEach var="item_typeVO" items="${list}">

			<tr>
				<td>${item_typeVO.type_name}</td>
				<td>${item_typeVO.item_type_no}</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/item_type/item_type.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="item_type_no" value="${item_typeVO.item_type_no}">
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>

			</tr>
		</c:forEach>
	</table>
	<!-- 	<h5 class="footer"> -->
	<%-- 		<%@ include file="page2.file"%></h5> --%>
	<script src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/datatables.min.js"></script>
	<script>
		$(document).ready(function() {
			$('#myTable').DataTable();
		});
	</script>
</body>
</html>