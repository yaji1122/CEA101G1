<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.time_table.model.*"%>

<%
TimeTableService timeTableSvc = new TimeTableService();
List<TimeTableVO> list = timeTableSvc.getAll();
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
	href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<title>列出所有服務時段表</title>
</head>
<body>
    
    <a href="<%=request.getContextPath()%>/backend/timeTable/addTimeTable.jsp">新增服務時段</a><br>

	<table>
		<tr>
			<th>服務編號</th>
			<th>服務時段</th>
			<th>可服務人數</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>

		<%@ include file="/backend/files/page1.file"%>
		<c:forEach var="timeTableVO" items="${list}" begin="<%=pageIndex%>" varStatus="vs"
			end="<%=pageIndex+rowsPerPage-1%>">
			<tr>
				<td>${timeTableVO.serv_no}</td>
				<td>${timeTableVO.serv_period}</td>
				<td>${timeTableVO.max_serv_ppl}</td>

				<td>
					<FORM METHOD="post"
						ACTION="${pageContext.request.contextPath}/TimeTableServlet"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> 
						<input type="hidden" name="serv_no" value="${timeTableVO.serv_no}"> 
						<input type="hidden" name="serv_period" value="${timeTableVO.serv_period}">
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="${pageContext.request.contextPath}/TimeTableServlet"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="serv_no" value="${timeTableVO.serv_no}">
							<input type="hidden"
							name="serv_period" value="${timeTableVO.serv_period}">
							 <input
							type="hidden" name="action" value="delete">
						
					</FORM>
				</td> 
				
			</tr>
		</c:forEach>
	</table>
	<%@ include file="/backend/files/page2.file"%>
	<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>