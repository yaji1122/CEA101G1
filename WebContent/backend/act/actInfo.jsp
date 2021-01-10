<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.act.model.*"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/backend/files/backend_header.file" %> <!-- 加入基本 css -->
<title>活動管理</title>
</head>
<body>
<%@ include file="/backend/files/backend_sidebar.file" %>
	<section class="wrapper">
		<ul class="tabs">
			<li class="active">活動訂單列表</li>
		</ul>
	
		<ul class="tab__content">
			<li class="active">
				<div class="content__wrapper">
					<div class="table-wrapper">
						<jsp:include page="act_listAll.jsp" />
					</div>
				</div>
			</li>
			<li>
				<div class="content__wrapper">
					<div class="table-wrapper">
						<jsp:include page="act_add.jsp" />
					</div>
				</div>
			</li>
			<li>
				<div class="content__wrapper">
					<div class="table-wrapper">
						<jsp:include page="/backend/actevent/act_event_listAll.jsp" />
					</div>
				</div>
			</li>
		</ul>
	</section>
	<%@ include file="/backend/files/backend_footer.file" %> <!-- 加入基本 js -->
</body>
</html>
