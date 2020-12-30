<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.item.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<%
	ItemVO itemVO = (ItemVO) request.getAttribute("itemVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>商品資料 - listOneItem.jsp</title>

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
			<th>類別編號</th>
			<th>商品價格</th>
			<th>更新時間</th>
			<th>商品狀態</th>
			<th>促銷狀態</th>
			<th>商品詳細</th>
			<th>商品照片</th>
			<th>積分</th>
			<th>修改</th>
		</tr>

		<jsp:useBean id="item_typeSvc" scope="page"
			class="com.item_type.model.Item_typeService" />

		<tr>
			<td><%=itemVO.getItem_no()%></td>
			<td><%=itemVO.getItem_name()%></td>
			<td>${item_typeSvc.getOneItem_type(itemVO.item_type_no).type_name}</td>
			<td><%=itemVO.getItem_price()%></td>
			<td><fmt:formatDate value="${itemVO.item_renew}"
					pattern="yyyy-MM-dd HH:mm:ss" /></td>
			<td>${((itemVO.item_status)==1)?"上架中":"下架中"}</td>
			<td>${((itemVO.item_on_sale)==1)?"促銷中":"正常價格"}</td>
			<td><%=itemVO.getItem_detail()%></td>
			<td><a
				href="<%=request.getContextPath()%>/backend/item_pics/listAllByItem_no.jsp?item_no=${itemVO.item_no}">詳細照片</a></td>
			<td><%=itemVO.getPoints()%></td>
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
	</table>

</body>
</html>