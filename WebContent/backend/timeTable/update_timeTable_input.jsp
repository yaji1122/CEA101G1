<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.time_table.model.*"%>

<%
	TimeTableVO timeTableVO = (TimeTableVO) request.getAttribute("timeTableVO"); 
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
<style>
body {
	margin-left: 30px;
	margin-right: 30px;
}


</style>
<title>修改服務類型</title>
</head>
<body>
	<form METHOD="post"
		ACTION="${pageContext.request.contextPath}/TimeTableServlet"
		name="form1">
		<div class="form-group">
			服務編號:<%=timeTableVO.getServ_no()%>
		</div>
		<div class="form-group">
			<label for="serv_period">服務時段:</label> <input type="text"
				class="form-control" id="serv_period" name="serv_period"
				value="<%=timeTableVO.getServ_period()%>" required>
		</div>
		<div class="form-group">
			<label for="max_serv_ppl">可服務人數:</label> <input type="text"
				class="form-control" id="max_serv_ppl" name="max_serv_ppl"
				value="<%=timeTableVO.getMax_serv_ppl()%>" required>
		</div>
		
		<input type="hidden" name="action" value="update">
		<input type="hidden" name="serv_no" value="${timeTableVO.serv_no}"> 
		<input type="hidden" name="serv_period" value="${timeTableVO.serv_period}">
		<input class="btn btn-primary" type="submit" value="送出修改">
	</form>
	
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

	

</body>
</html>