<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.service_order.model.*"%>
<%@ page import="java.util.List"%>
<%@ include file="/backend/files/backend_header.file" %> <!-- 加入常用 css -->

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>服務時段表</title>
</head>
<body>
<%@ include file="/backend/files/backend_sidebar.file" %>
	<section class="wrapper">
		<ul class="tabs">
			<li class="active">服務訂單</li>
		</ul>

		<ul class="tab__content">
			<li class="active">
				<div class="content__wrapper">
					<div class="table-wrapper">
						<jsp:include page="listAllServiceOrder.jsp" />
					</div>
				</div>
			</li>
		</ul>
	</section>

	<%@ include file="/backend/files/backend_footer.file" %> <!-- 加入常用 js -->
	<script src="${pageContext.request.contextPath}/js/backend.js"></script>
</body>
</html>