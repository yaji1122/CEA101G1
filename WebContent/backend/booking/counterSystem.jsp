<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/backend/files/backend_header.file" %> <!-- 加入常用 css -->
<title>戴蒙酒店房務系統</title>
</head>
<body>
<%@ include file="/backend/files/backend_sidebar.file" %>
	<section class="wrapper">
		<ul class="tabs">
			<li class="active">CHECK IN</li>
			<li>CHECK OUT</li>
		</ul>

		<ul class="tab__content">
			<li class="active">
				<div class="content__wrapper">
					<div class="table-wrapper">
						<%-- <jsp:include page=".jsp" /> --%>
					</div>
				</div>
			</li>
		</ul>
	</section>

	<%@ include file="/backend/files/backend_footer.file" %> <!-- 加入常用 js -->
	<script src="${pageContext.request.contextPath}/js/back/backend.js"></script>
</body>
</html>
