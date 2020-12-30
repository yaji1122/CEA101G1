<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mealorderdetail.model.*"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<!-- Bootstrap -->
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
	integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/back/backend-meal.css" />
		<%@ include file="/backend/files/backend_header.file"%>
<title>餐點訂單細項管理</title>
<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
</head>

<body>
<%@ include file="/backend/files/backend_sidebar.file"%>
<div class="black"></div>
	<section class="wrapper">
		<ul class="tabs">
			<li class="active">所有餐點訂單細項</li>
		</ul>

		<ul class="tab__content">
			<li class="active">
				<div class="content__wrapper">
					<div class="showAllMealType">
						<jsp:include page="showAllMealOrderDetail.jsp" />
					</div>
				</div>
			</li>
		</ul>
	</section>

<%@ include file="/backend/files/backend_footer.file"%>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
	<script src="${pageContext.request.contextPath}/js/back/backend.js"></script>


</body>
</html>