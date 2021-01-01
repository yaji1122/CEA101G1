<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.actorder.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    ActOrderService actOrderSvc = new ActOrderService();
    List<ActOrderVO> list = actOrderSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<html>
<head>
<title>所有活動訂單 - act_order_listAll.jsp</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/back/backend.css" type="text/css" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/back/backend-search.css" type="text/css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/back/backend-add.css" type="text/css" />
</head>
<body bgcolor='white'>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
       <div class="logo" >
			<img src="<%=request.getContextPath()%>/img/logo.png" style="margin-left:450px;">
		</div>
		<div class="form-title">
			<img src="<%=request.getContextPath()%>/img/loading.png">
			<h2 style="margin-left:80px;">訂單查詢</h2>
		</div>

	
			<div class="dropdown">
				<div class="btn1">
				     <button type="button" class="btn btn-outline-danger" 
                      onclick="location.href='<%=request.getContextPath()%>/backend/actorder/backend-order_select_page.jsp?action=getAll'">
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
			</div>
			<br>
	
<div class="table-content" id="content">
    <table class="table table-hover" id="table">
       <thead class="title">
              <tr class="table-primary">
                <th>活動訂單編號:</th>
                <th>活動編號:</th>
                <th>會員編號:</th>
                <th>訂單狀態:</th>
                <th>訂單日期:</th>
                <th>參加人數:</th>
                <th>訂單總價格:</th>
                <th>修改</th>
              </tr>
        </thead>
        <tbody>
              <tr>
              <c:forEach var="actOrderVO" items="${list}">
              
                   <td>${actOrderVO.actOdno}</td>
	               <td>${actOrderVO.actNo}</td>
	               <td>${actOrderVO.mbId}</td>
	               <td>
	               <c:choose>
						<c:when test="${actOrderVO.odStatus == 0}">進行中</c:when>
						<c:when test="${actOrderVO.odStatus == 1}">已完成</c:when>
					    <c:otherwise>已取消</c:otherwise>
				   </c:choose>
	               </td>
	               <td>${actOrderVO.odTime}</td>
	               <td>${actOrderVO.ppl}</td>
	               <td>${actOrderVO.totalPrice}</td>
                   
                    <td>
                    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/actorder/ActOrderServlet" style="margin-bottom: 0px;">
                    <button type="submit" class="btn btn-outline-dark" value="修改"> 修改</button>
                    <input type="hidden" name="actOdno"  value="${actOrderVO.actOdno}">
			        <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
                   </td>
              </tr>
              </c:forEach>
         </tbody>
     </table>
     <nav id="pagination-1" aria-label="Page navigation example" >
		<ul class="pagination" style="magrin-left:150px;">
			<li class="page-item"><a class="page-link" href="#"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
			</a></li>
			<li class="page-item"><a class="page-link" href="#">1</a></li>
			<li class="page-item"><a class="page-link" href="#">2</a></li>
			<li class="page-item"><a class="page-link" href="#">3</a></li>
			<li class="page-item"><a class="page-link" href="#"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span>
			</a></li>
		</ul>
	</nav>
     
</div>


</body>
</html>