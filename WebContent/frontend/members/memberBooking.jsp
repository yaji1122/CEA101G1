<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.members.model.*"%>
<%@ page import="com.payment.model.*"%>
<%
	MembersVO member = (MembersVO) session.getAttribute("member");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/frontend/files/commonCSS.file"%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/front/creditCard.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/front/memberInfo.css" />
<title>Diamond Resort 會員個人資料</title>
</head>
<body>
	<%@ include file="/frontend/files/header.file"%>
	<div class="main-wrapper">
		<div class="select-div">
			<div class="side-nav">
				<ul>
					<li><a
						href="<%=request.getContextPath()%>/frontend/members/memberInfo.jsp"><i
							class="far fa-user"></i> 個人資訊</a></li>
					<li><a
						href="<%=request.getContextPath()%>/frontend/members/memberBooking.jsp"><i
							class="far fa-calendar-check"></i> 假期管理</a></li>
					<li><a href="#"><i class="fas fa-clipboard-list"></i> 訂單管理</a>
					</li>
				</ul>
			</div>
		</div>
		<div class="info-div">
			<div class="info-content">
				<div class="tabset">
					<!-- Tab 1 -->
					<input type="radio" name="tabset" id="tab1"
						aria-controls="basic-info" checked /> <label for="tab1">我的假期</label>
					<!-- Tab 2 -->
					<input type="radio" name="tabset" id="tab2"
						aria-controls="account-info" /> <label for="tab2">歷史紀錄</label>

					<div class="tab-panels">
						<section id="basic-info" class="tab-panel"></section>
						<section id="account-info" class="tab-panel"></section>
					</div>
					<%@ include file="/frontend/files/commonJS.file"%>
					<script
						src="https://cdnjs.cloudflare.com/ajax/libs/imask/3.4.0/imask.min.js"></script>
					<script src="<%=request.getContextPath()%>/js/front/creditCard.js"></script>
					<script src="<%=request.getContextPath()%>/js/front/memberInfo.js"></script>
</body>
</html>
