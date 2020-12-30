<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.service_type.model.*"%>

<%
	ServiceTypeVO serviceTypeVO = (ServiceTypeVO) request.getAttribute("serviceTypeVO"); 
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
		ACTION="${pageContext.request.contextPath}/ServiceTypeServlet"
		name="form1">
		<div class="form-group">
			服務類型編號:<%=serviceTypeVO.getServ_type_no()%>
		</div>
		<div class="form-group">
			<label for="serv_type_no">服務類型名稱:</label> <input type="text"
				class="form-control" id="serv_type_name" name="serv_type_name"
				value="<%=serviceTypeVO.getServ_type_name()%>" required>
		</div>
		
		
		<input type="hidden" name="action" value="update"> <input
			type="hidden" name="serv_type_no" value="<%=serviceTypeVO.getServ_type_no()%>">
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