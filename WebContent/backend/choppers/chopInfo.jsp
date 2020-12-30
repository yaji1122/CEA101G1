<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.choppers.model.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/backend/files/backend_header.file" %> <!-- 加入常用 css -->
<title>空中載具管理</title>
</head>
<body>
<%@ include file="/backend/files/backend_sidebar.file" %> <!-- 加入sidebar -->
	<section class="wrapper">
		<ul class="tabs">
			<li class="active">所有機型</li>
			<li>新增載具</li>
		</ul>
		<ul class="tab__content">
			<li class="active">
				<div class="content__wrapper">
					<div class="table-wrapper">
						<jsp:include page="showAllChoppers.jsp" /></div>
				</div>
			</li>
			<li>
				<div class="content__wrapper">
						<jsp:include page="addChopper.jsp" />
				</div>
			</li>
		</ul>
	</section>

	<%@ include file="/backend/files/backend_footer.file" %> <!-- 加入常用 js -->
</body>
</html>
