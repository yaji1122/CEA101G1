<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/backend/files/backend_header.file" %> <!-- 加入常用 css -->
<title>接送預約情形</title>
</head>
<body>
<%@ include file="/backend/files/backend_sidebar.file" %>
	<section class="wrapper">
		<ul class="tabs">
			<li class="active">預約狀況</li>
		</ul>

		<ul class="tab__content">
			<li class="active">
				<div class="content__wrapper">
					<div class="table-wrapper">
						<jsp:include page="showAllPkup.jsp" />
					</div>
				</div>
			</li>
		</ul>
	</section>
	<%@ include file="/backend/files/backend_footer.file" %> <!-- 加入常用 js -->
</body>
</html>
