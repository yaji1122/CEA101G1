<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.service_order.model.*"%>

<%
	ServiceOrderService serviceOrderSvc = new ServiceOrderService();
List<ServiceOrderVO> list = serviceOrderSvc.getAll();
pageContext.setAttribute("list", list);
%>

<jsp:useBean id="servicesSvc" scope="page"
	class="com.services.model.ServicesService" />


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href='${pageContext.request.contextPath}/css/datatables.min.css'
	rel='stylesheet' />
<style>
.table-order {
	text-align: center;
}
</style>
<title>服務訂單</title>
</head>
<body>

	<table id="myTable" class="table-order">
		<thead>
			<tr>
				<th scope="col">訂單編號</th>
				<th scope="col">訂房單號</th>
				<th scope="col">訂單成立時間</th>
				<th scope="col">訂單狀態</th>
				<th scope="col">服務名稱</th>
				<th scope="col">預約時間</th>
				<th scope="col">服務人數</th>
				<th scope="col">服務場所</th>
				<th scope="col">訂單總額</th>
				<th scope="col">修改</th>
				<th scope="col">刪除</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach var="serviceOrderVO" items="${list}">

				<tr>
					<td scope="row">${serviceOrderVO.serv_odno}</td>
					<td>${serviceOrderVO.bk_no}</td>
					<td><fmt:formatDate value="${serviceOrderVO.od_time}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><c:choose>
							<c:when test="${serviceOrderVO.od_status.equals('0')}">未完成</c:when>
							<c:when test="${serviceOrderVO.od_status.equals('1')}">已完成</c:when>
							<c:when test="${serviceOrderVO.od_status.equals('2')}">已取消</c:when>
						</c:choose></td>

					<td>
						${servicesSvc.getOneServices(serviceOrderVO.serv_no).serv_name}</td>
					<td><fmt:formatDate value="${serviceOrderVO.serv_time}"
							pattern="yyyy-MM-dd HH:mm" /></td>
					<td>${serviceOrderVO.serv_count}</td>
					<td>${serviceOrderVO.locations}</td>
					<td>${serviceOrderVO.total_price}</td>
					<td>
						<FORM METHOD="post"
							ACTION="${pageContext.request.contextPath}/ServiceOrderServlet"
							style="margin-bottom: 0px;">
							<input type="submit" class="btn btn-primary" value="修改">
							<input type="hidden" name="serv_odno"
								value="${serviceOrderVO.serv_odno}"> <input
								type="hidden" name="action" value="getOne_For_Update">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post"
							ACTION="${pageContext.request.contextPath}/ServiceOrderServlet"
							style="margin-bottom: 0px;">
							<input type="submit" class="btn btn-danger" value="刪除"> <input
								type="hidden" name="serv_odno"
								value="${serviceOrderVO.serv_odno}"> <input
								type="hidden" name="action" value="delete">
						</FORM>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
	<script src='${pageContext.request.contextPath}/js/datatables.min.js'></script>
	<script>
		$(document).ready(function() {
			$('#myTable').DataTable();
		});
	</script>


</body>
</html>