<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.act.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	ActService actSvc = new ActService();
	List<ActVO> list = actSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>所有活動資料 - listAllAct.jsp</title>

      <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
      <link rel="stylesheet" href="<%=request.getContextPath()%>/css/back/backend.css" type="text/css" />
      <link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui.css">
      <link rel="stylesheet" href="<%=request.getContextPath()%>/css/back/backend-search.css" type="text/css" />

</head>
<body bgcolor='white'>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	
	
	
	<div class="logo">
			<img src="<%=request.getContextPath()%>/img/logo.png" style="width:30%;">
		</div>
		<div class="form-title" style="margin-left:100px;">
			<img src="<%=request.getContextPath()%>/img/loading.png">
			<h2>活動查詢</h2>
		</div>
		
		  <div class="btn1" style="margin-right:80px;">
				     <button type="button" class="btn btn-outline-danger" 
                      onclick="location.href='<%=request.getContextPath()%>/back-end/act/backend-act_select_page.jsp?action=getAll'">
                                                          回首頁</button>
					<a class="btn btn-secondary dropdown-toggle" href="#" role="button"
						id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> 訂單狀態 </a>
					<div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
						<a class="dropdown-item" href="#">報名中</a> <a class="dropdown-item"
							href="#">已完成</a> <a class="dropdown-item" href="#">已取消</a>
					</div>
					<a class="btn btn-secondary dropdown-toggle" href="#" role="button"
						id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true">
						活動項目 </a>
					<div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
						<a class="dropdown-item" href="#">沙灘車</a> <a class="dropdown-item"
							href="#">射箭</a> <a class="dropdown-item" href="#">燈會</a> <a
							class="dropdown-item" href="#">衝浪</a> <a class="dropdown-item"
							href="#">浮潛</a> <a class="dropdown-item" href="#">賞鯨</a> <a
							class="dropdown-item" href="#">BBQ</a> <a class="dropdown-item"
							href="#">深潛</a> <a class="dropdown-item" href="#">狩獵</a>
					</div>
			</div>
			<br>

	<div class="table-content" id="content">
		<table class="table table-hover" id="table">
			<thead class="title">
				<tr class="table-primary">
					<th>活動編號</th>
					<th>活動項目編號</th>
					<th>活動名稱</th>
					<th>活動狀態</th>
					<th>活動報名日期</th>
					<th>報名截止日期</th>
					<th>活動舉辦日期</th>
					<th>活動時段</th>
					<th>參加者</th>
					<th>活動價格</th>
					<th>修改</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<c:forEach var="actVO" items="${list}">
						<tr>
							<td>${actVO.actNo}</td>
							<td>${actVO.actEventNo}</td>
							<td>${actVO.actName}</td>
							<td>
							<c:choose>
							      <c:when test="${actVO.actStatus == 0}">進行中</c:when>
							      <c:when test="${actVO.actStatus == 1}">已完成</c:when>
							      <c:otherwise>已取消</c:otherwise>
							</c:choose>
							</td>
							<td>${actVO.actRegTime}</td>
							<td>${actVO.actDate}</td>
							<td>${actVO.deadLine}</td>
							<td>${actVO.actTime}</td>
							<td>${actVO.participant}</td>
							<td>${actVO.actPrice}</td>

							<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/act/ActServlet"
									style="margin-bottom: 0px;">
									 <button type="submit" class="btn btn-outline-dark"> 修改</button> 
									<input type="hidden"
										name="actNo" value="${actVO.actNo}"> <input
										type="hidden" name="action" value="getOne_For_Update">
								</FORM>
							</td>
						</tr>
					</c:forEach>
				</tr>
			</tbody>
		</table>
	</div>

</body>
</html>