<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.time_table.model.*"%>

<%
	TimeTableVO timeTableVO = (TimeTableVO) request.getAttribute("timeTableVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style>
form {
	max-width: 500px;
}
</style>
<title>服務時段新增</title>

</head>
<body>
<h5> <a href="<%=request.getContextPath()%>/backend/timeTable/TimeTableInfo.jsp">回所有列表</a> </h5>
	<h3>服務時段新增</h3>
	<form METHOD="post"
		ACTION="${pageContext.request.contextPath}/TimeTableServlet"
		name="form1">
		<div class="form-group">
			<label for="serv_no">服務編號:</label> <input type="text"
				class="form-control" id="serv_no" name="serv_no"
				value="<%=(timeTableVO == null) ? "001" : timeTableVO.getServ_no()%>">
		</div>
		
		<div class="form-group">
			<label for="serv_period">服務時段:</label> <input type="text"
				class="form-control" id="serv_period" name="serv_period"
				value="<%=(timeTableVO == null) ? "1" : timeTableVO.getServ_period()%>">
		</div>
		
		<div class="form-group">
			<label for="max_serv_ppl">可服務人數:</label> <input type="text"
				class="form-control" id="max_serv_ppl" name="max_serv_ppl"
				value="<%=(timeTableVO == null) ? "1" : timeTableVO.getMax_serv_ppl()%>">
		</div>
		
		<input type="hidden" name="action" value="insert"> <input
			class="btn btn-primary" type="submit" value="送出新增">
	</form>
</body>
</html>