<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.services.model.*"%>
<%@ include file="/backend/files/backend_header.file" %>
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

</style>
<title></title>
</head>

<body>
<%@ include file="/backend/files/backend_sidebar.file" %>
	<header>
		<h2>服務管理主頁面</h2>
	</header>

	<ul class="nav nav-tabs" id="myTab" role="tablist">
		<li class="nav-item" role="presentation"><a
			class="nav-link active" id="home-tab" data-toggle="tab" href="#home"
			role="tab" aria-controls="home" aria-selected="true">所有服務列表</a></li>
		<li class="nav-item" role="presentation"><a class="nav-link"
			id="profile-tab" data-toggle="tab" href="#profile" role="tab"
			aria-controls="profile" aria-selected="false">新增服務</a></li>
		<li class="nav-item" role="presentation"><a class="nav-link"
			id="contact-tab" data-toggle="tab" href="#contact" role="tab"
			aria-controls="contact" aria-selected="false">服務查詢</a></li>
	</ul>
	<div class="tab-content" id="myTabContent">
		<div class="tab-pane fade show active" id="home" role="tabpanel"
			aria-labelledby="home-tab"><jsp:include
				page="listAllServices.jsp" /></div>
		<div class="tab-pane fade" id="profile" role="tabpanel"
			aria-labelledby="profile-tab"><jsp:include
				page="addServices.jsp" /></div>
		<div class="tab-pane fade" id="contact" role="tabpanel"
			aria-labelledby="contact-tab">
			<div class="form-group">
				<form METHOD="post" ACTION="${pageContext.request.contextPath}/ServicesServlet">
					<label for="serv_no">輸入服務編號:</label> <input type="text"
						class="form-control" id="serv_no" name="serv_no"> <input
						type="hidden" name="action" value="getOne_For_Display"> <input
						type="submit" value="送出">
				</form>
			</div>
		</div>
	</div>

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