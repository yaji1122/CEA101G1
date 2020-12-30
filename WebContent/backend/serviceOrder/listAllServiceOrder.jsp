<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.service_order.model.*"%>

<%
ServiceOrderService servicesSvc = new ServiceOrderService();
List<ServiceOrderVO> list = servicesSvc.getAll();
pageContext.setAttribute("list", list);
%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
	integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<%-- <link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/services.css" /> --%>


<title>服務訂單</title>
</head>
<body>


	<header>
		<h2>服務訂單</h2>
	</header>
	<table>

		<thead>
			<tr>
				<th scope="col">訂單編號</th>
				<th scope="col">會員編號</th>
				<th scope="col">訂單成立時間</th>
				<th scope="col">訂單狀態編號</th>
				<th scope="col">客房編號</th>
				<th scope="col">服務編號</th>
				<th scope="col">預約時間</th>
				<th scope="col">服務人數</th>
				<th scope="col">訂單總額</th>
				<th scope="col">修改</th>
				<th scope="col">刪除</th>
			</tr>

		</thead>
		<tbody>

			<%@ include file="/backend/files/page1.file"%>
			<c:forEach var="serviceOrderVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">

				<tr>
					<th scope="row">${serviceOrderVO.serv_odno}</th>
					<td>${serviceOrderVO.mb_id}</td>
					<td>${serviceOrderVO.od_time}</td>
					<td>${serviceOrderVO.od_status}</td>
					<td>${serviceOrderVO.rm_no}</td>
					<td>${serviceOrderVO.serv_no}</td>
					<td>${serviceOrderVO.serv_time}</td>
					<td>${serviceOrderVO.serv_count}</td>
					<td>${serviceOrderVO.total_price}</td>
					<td>
						<FORM METHOD="post"
							ACTION="${pageContext.request.contextPath}/ServiceOrderServlet"
							style="margin-bottom: 0px;">
							<input type="submit" value="修改"> <input type="hidden"
								name="serv_odno" value="${serviceOrderVO.serv_odno}"> <input
								type="hidden" name="action" value="getOne_For_Update">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post"
							ACTION="${pageContext.request.contextPath}/ServiceOrderServlet"
							style="margin-bottom: 0px;">
							<input type="submit" value="刪除"> <input type="hidden"
								name="serv_odno" value="${serviceOrderVO.serv_odno}"> <input
								type="hidden" name="action" value="delete">
						</FORM>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<%@ include file="/backend/files/page2.file"%>

	<!-- <script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
		integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"
		integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s"
		crossorigin="anonymous"></script>
	<%-- <script src="${pageContext.request.contextPath}/js/services.js"></script> --%>
	
	
</body>
</html>