<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.item.model.*"%>
<%
	ItemService itemSvc = new ItemService();
	List<ItemVO> list = itemSvc.getAllItem();
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>所有商品資料 - listAllItem.jsp</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/datatables.min.css" />

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
	width: 100%;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
	margin-left: auto;
	margin-right: auto;
	border: 1px gray;
}

tr:nth-of-type(even) {
	background-color: #f3f3f3 !important;
}

tr:nth-of-type(odd) {
	background-color: #ddd !important;
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

		<div class="titleAll">所有商品資料</div>
		<div>
			<a href="<%=request.getContextPath()%>/backend/item/addItem.jsp"
				class="home">新增商品</a>
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
				<th>商品編號</th>
				<th>商品名稱</th>
				<th>商品類別</th>
				<th>商品價格</th>
				<th>更新時間</th>
				<th>商品狀態</th>
				<th>促銷狀態</th>
				<th>商品詳細</th>
				<th>商品照片</th>
				<th>積分</th>
				<th>修改</th>

			</tr>
		</thead>

		<%-- 		<%@ include file="page1.file"%> --%>

		<jsp:useBean id="item_typeSvc" scope="page"
			class="com.item_type.model.Item_typeService" />
		<jsp:useBean id="item_picsSvc" scope="page"
			class="com.item_pics.model.Item_picsService" />
		<c:forEach var="itemVO" items="${list}">

			<tr>
				<td>${itemVO.item_no}</td>
				<td>${itemVO.item_name}</td>
				<td>${item_typeSvc.getOneItem_type(itemVO.item_type_no).type_name}</td>
				<td>${itemVO.item_price}</td>
				<td><fmt:formatDate value="${itemVO.item_renew}"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td>${((itemVO.item_status)==1)?"上架中":"下架中"}</td>
				<td>${((itemVO.item_on_sale)==1)?"促銷中":"正常價格"}</td>
				<td>${itemVO.item_detail}</td>
				<td><a
					href="<%=request.getContextPath()%>/backend/item_pics/listAllByItem_no.jsp?item_no=${itemVO.item_no}">詳細照片</a></td>
				<td>${itemVO.points}</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/item/item.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="item_no" value="${itemVO.item_no}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>

			</tr>
		</c:forEach>
	</table>
	<!-- 	<h5 class="footer"> -->
	<%-- 		<%@ include file="page2.file"%></h5> --%>
	<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/datatables.min.js"></script>
	<script>
		$(document).ready(function() {
			$('#myTable').DataTable();
		});
	</script>

</body>
</html>