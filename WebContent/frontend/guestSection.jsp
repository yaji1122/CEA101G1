<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.members.model.*"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/frontend/files/commonCSS.file"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/front/guestSection.css" />
<title>住客服務中心</title>
</head>
<body>
	<%@ include file="/frontend/files/header.file"%>
	<div class="main-wrapper">
		<div class="section flip-container" id="activity"
			ontouchstart="this.classList.toggle('hover');">
			<div class="flipper">
				<div class="front">
					<span class="name">ACTIVITY</span>
				</div>
				<div class="back">
					<p>
						超級精彩的活動拉
					</p>
				</div>
			</div>
		</div>
		<div class="section flip-container" id="service"
			ontouchstart="this.classList.toggle('hover');">
			<div class="flipper">
				<div class="front">
					<span class="name">SERVICE</span>
				</div>
				<div class="back">
					<p>
						有濕背秀服務唷～
					</p>
				</div>
			</div>
		</div>
		<div class="section flip-container" id="meal"
			ontouchstart="this.classList.toggle('hover');">
			<div class="flipper">
				<div class="front">
					<span class="name">CUIZINE</span>
				</div>
				<div class="back">
					<p>
						富有台灣色彩的海鮮料理
					</p>
				</div>
			</div>
		</div>
		<div class="section flip-container" id="shopping"
			ontouchstart="this.classList.toggle('hover');">
			<div class="flipper">
				<div class="front">
					<span class="name">SHOPPING</span>
				</div>
				<div class="back">
					<p>
						買，都買
					</p>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="/frontend/files/commonJS.file"%>
</body>
</html>