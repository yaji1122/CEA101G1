<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.acttype.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	ActTypeService actTypeSvc = new ActTypeService();
	List<ActTypeVO> list = actTypeSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>所有活動種類資料 - listAllActType.jsp</title>
<link rel="icon" type="image/png" href="img/loading.png" />
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="css/index-back.css" />
<script src="https://kit.fontawesome.com/dc3c868026.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="css/backend.css" type="text/css" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/jquery-ui.css">
<link rel="stylesheet" href="../css/backend-search.css" type="text/css" />



</head>
<body bgcolor='white'>

	<!-- <table id="table-1">
	<tr><td>
		 <h3>所有活動種類 - listAll_ActType.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/acttype/select_page.jsp"><img src="img/logo.png" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table> -->

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<div class="table-content" id="content">
		<table class="table table-hover" id="table">
			<thead class="title">
				<tr class="table-primary">
					<th>種類編號</th>
					<th>活動種類名稱</th>
					<th>修改</th>
					<th>刪除</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="actTypeVO" items="${list}">

					<tr>
						<td>${actTypeVO.actTypeNo}</td>
						<td>${actTypeVO.actTypeName}</td>
						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/acttype/ActTypeServlet"
								style="margin-bottom: 0px;">
								<input type="submit" value="修改"> <input type="hidden"
									name="ActTypeNo" value="${actTypeVO.actTypeNo}"> <input
									type="hidden" name="action" value="getOne_For_Update">
							</FORM>
						</td>
						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/acttype/ActTypeServlet"
								style="margin-bottom: 0px;">
								<input type="submit" value="刪除"> <input type="hidden"
									name="ActTypeno" value="${actTypeVO.actTypeNo}"> <input
									type="hidden" name="action" value="delete">
							</FORM>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>