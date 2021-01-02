<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.shop_order.model.*"%>
<%
	Shop_orderService shop_orderSvc = new Shop_orderService();
	List<Shop_orderVO> list = shop_orderSvc.getAllShop_order();
	pageContext.setAttribute("list", list);
%>
<%
	HashMap<String, String> sp_status = new HashMap<>();
sp_status.put("0", "待出貨");
sp_status.put("1", "已出貨");
sp_status.put("2", "交易完成");
sp_status.put("3", "退貨");
sp_status.put("4", "已取消");
	pageContext.setAttribute("sp_status", sp_status);
%>

<html>
<head>
<title>所有商品訂單資料 - listAllShop_order.jsp</title>
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

		<div class="titleAll">所有商品訂單資料</div>
		<div>
			<a
				href="<%=request.getContextPath()%>/backend/shop_order/select_shop_order.jsp"
				class="home">回商品訂單管理</a>
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

	<table>
		<tr>
			<th>訂單編號</th>
			<th>會員編號</th>
			<th>訂單成立時間</th>
			<th>訂單狀態</th>
			<th>出貨時間</th>
			<th>訂單細項</th>
			<th>訂單總額</th>
			<th>總積分</th>
			<th>客房編號</th>
			<th>修改</th>

		</tr>



		<%@ include file="/backend/files/page1.file"%>

		<c:forEach var="shop_orderVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${shop_orderVO.sp_odno}</td>
				<td>${shop_orderVO.mb_id}</td>
				<td><fmt:formatDate value="${shop_orderVO.sp_time}"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td>
				<c:forEach var="status" items="${sp_status}">		
				${shop_orderVO.sp_status==status.key? status.value:''}
				</c:forEach>
				</td>
				<td><fmt:formatDate value="${shop_orderVO.sp_dlvr}"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td><a
					href="<%=request.getContextPath()%>/backend/shop_order_detail/listAllDetail.jsp?sp_odno=${shop_orderVO.sp_odno}">訂單細項</a></td>
				<td>${shop_orderVO.total_price}</td>
				<td>${shop_orderVO.points_total}</td>
				<td>${shop_orderVO.rm_no}</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/shop_order/shop_order.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="sp_odno" value="${shop_orderVO.sp_odno}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>

			</tr>
		</c:forEach>
	</table>
	<h5 class="footer">
		<%@ include file="/backend/files/page2.file"%></h5>
	<%@ include file="/backend/files/backend_footer.file" %>
	<script src="${pageContext.request.contextPath}/js/back/backShopItem.js"></script>
</body>
</html>