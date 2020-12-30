<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.actpic.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	ActPicService actPicSvc = new ActPicService();
	List<ActPicVO> list = actPicSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>所有活動照片資料 - listAll_ActPIC.jsp</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/back/backend.css" type="text/css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
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
			<img src="<%=request.getContextPath()%>/img/logo.png">
		</div>
		<div class="form-title">
			<img src="<%=request.getContextPath()%>/img/loading.png">
			<h2>全部照片</h2>
		</div>
    <!-- Example single danger button -->


	<div class="table-content" id="content">
	      <nav class="navbar-top navbar-light">
			<form class="form-inline">
				<button class="btn btn-outline-danger" type="button" 
				onclick="location.href='<%=request.getContextPath()%>/back-end/actorder/backend-order_select_page.jsp'">
				  回訂單查詢
				</button>
				<button class="btn btn-outline-danger" type="button" 
				onclick="location.href='<%=request.getContextPath()%>/back-end/act/backend-act_select_page.jsp'">
				  回活動查詢
				</button>
		    </form>
		   </nav>
		   <br>
		<table class="table table-hover" id="table">
			<thead class="title">
				<tr class="table-primary">
					<th>活動照片編號</th>
					<th>活動項目編號</th>
					<th>活動照片</th>
					<!--<th>修改</th>  -->
					<th>刪除</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach var="actPicVO" items="${list}">
					<tr>
						<td>${actPicVO.actPicNo}</td>
						<td>
						<c:choose>
							   <c:when test="${actPicVO.actEventNo == 10}">沙灘車</c:when>
							   <c:when test="${actPicVO.actEventNo == 20}">射箭</c:when>
							   <c:when test="${actPicVO.actEventNo == 30}">燈會</c:when>
							   <c:when test="${actPicVO.actEventNo == 40}">衝浪</c:when>
							   <c:when test="${actPicVO.actEventNo == 50}">浮潛</c:when>
							   <c:when test="${actPicVO.actEventNo == 60}">賞鯨</c:when>
							   <c:when test="${actPicVO.actEventNo == 70}">BBQ</c:when>
							   <c:when test="${actPicVO.actEventNo == 80}">深潛</c:when>
							   <c:when test="${actPicVO.actEventNo == 90}">狩獵</c:when>
						</c:choose>
						</td>
						<td>
						     <img src="<%=request.getContextPath()%>/ActPicReaderServlet?actPicNo=${actPicVO.actPicNo}&action=getOnePic">
						</td>

						<!-- <td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/actpic/ActPicServlet"
								style="margin-bottom: 0px;">
								<button type="submit" class="btn btn-outline-dark">修改</button>
								<input type="hidden"name="ActPicNo" value="${actPicVO.actPicNo}"> 
								<inputtype="hidden" name="action" value="getOne_For_Update">
							</FORM>
						</td> -->
						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/actpic/ActPicServlet"
								style="margin-bottom: 0px;">
								<input type="hidden"name="ActPicNo" value="${actPicVO.actPicNo}"> 
								<input type="hidden" name="action" value="delete">
								<button type="submit" class="btn btn-outline-dark">刪除</button>
							</FORM>
						</td>
					</tr>
				</c:forEach>
			
			</tbody>
		</table>
	</div>
</body>
    <script src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
	<script src="<%=request.getContextPath()%>/js/index-back.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap.bundle.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap.bundle.min.js"></script>
</html>