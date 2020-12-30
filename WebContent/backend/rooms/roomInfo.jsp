<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.rooms.model.*"%>
<%@ page import="java.util.List"%>
<%@ page import="com.roomtype.model.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/backend/files/backend_header.file" %> <!-- 加入常用 css -->
<title>客房管理</title>
</head>
<body>
<%@ include file="/backend/files/backend_sidebar.file" %>
	<section class="wrapper">
		<ul class="tabs">
			<li class="active">客房查詢</li>
			<li>新增客房</li>
		</ul>

		<ul class="tab__content">
			<li class="active">
				<div class="content__wrapper">
					<div class="table-wrapper">
						<jsp:include page="showAllRooms.jsp" />
					</div>

				</div>
			</li>
			<li>
				<div class="content__wrapper">
						<jsp:include page="addRoom.jsp" />
				</div>
			</li>
		</ul>
	</section>
	<%@ include file="/backend/files/backend_footer.file" %> <!-- 加入常用 js -->
</body>
</html>
