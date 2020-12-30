<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.roomtype.model.*"%>
<%@ page import="java.util.List" %>
<%
RoomTypeService rmtypeSvc = new RoomTypeService();
List<RoomTypeVO> rmtypeList = rmtypeSvc.getAll();
pageContext.setAttribute("rmtypeList", rmtypeList);
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<%@ include file="/frontend/files/commonCSS.file" %>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/front/rooms.css" />
</head>
<%@ include file="/frontend/files/loginCSS.file" %>
<style>
.et-hero-tabs {
	background-image: url(<%=request.getContextPath()%>/img/background_beach.jpg);
	background-repeat: no-repeat;
	background-position: center;
	background-size: cover;
}
</style>
<body>
<div style="display:hidden" id="tab-top"></div>
<%@ include file="/frontend/files/login.file" %>
<%@ include file="/frontend/files/loginbox.file" %>
<%@ include file="/frontend/files/header.file" %>
	<!-- Hero -->
	<section class="et-hero-tabs">
		<h1>DAIMOND RESORT</h1>
		<h3>Enjoy the stuning view in our resort</h3>
		<div class="et-hero-tabs-container">
			<a class="et-hero-tab" href="#tab-top">GO TOP</a> 
			<c:forEach var="rmtype" items="${rmtypeList}">
			<a class="et-hero-tab" href="#tab${rmtype.rm_type}">${rmtype.type_eng_name}</a> 
			</c:forEach>
			<span class="et-hero-tab-slider"></span>
		</div>
	</section>
	<jsp:useBean id="rmpicSvc" scope="page" class="com.roompic.model.RoomPicService"/>
	<!-- Main -->
	<main class="et-main">
			<c:forEach var="rmtype" items="${rmtypeList}">
			<section class="et-slide" id="tab${rmtype.rm_type}">
			<div class="room-info">
				<h1>${rmtype.type_name}</h1>
				<h3>${rmtype.type_eng_name}</h3>
				<h6>查看詳情</h6>
				<div class="check-detail-arrow">
					<img class="dropdown"
						src="${pageContext.request.contextPath}/img/icon/drop-down-arrow.svg"
						alt="" />
				</div>
				<div class="room-detail-info">
					<h4>${rmtype.rm_info_title}</h4>
					<br />
					<p>
						${rmtype.rm_info}
					</p>
					<h6>每晚價格</h6>
					<b>\$${rmtype.rm_price}</b>
				</div>
			</div>
			<div class="et-background">
				<ul class="cb-slideshow">
					<c:forEach var="rmtypepics" items="${rmpicSvc.getAllByRoomType(rmtype.rm_type)}" begin="0" end="3">
					<li><span><img src="${pageContext.request.contextPath}/RoomPicServlet?rmpicno=${rmtypepics.rm_pic_no}&action=getOneRmPic" /></span></li>
					</c:forEach>
				</ul>
			</div>
		</section>
			</c:forEach>
		
	</main>
	<div class="booking-now">
		<div id="check-date">
			<a href="#available"><button >查看剩餘空房</button></a>
		</div>
		<div>
			<div id="available"><img class="booking-icon"
				src="<%=request.getContextPath()%>/img/icon/reserve-en-black-pc.svg" /></div>
		</div>
	</div>
	<div class="calendar-available">
		<jsp:include page="/frontend/roomrsv/available.jsp"/>
	</div>
	<%@ include file="/frontend/files/commonJS.file" %>
	<script src="${pageContext.request.contextPath}/js/front/rooms.js"></script>
	<script>
		$("#check-date a button").click(function(){
			$("div.calendar-available").addClass("show-calendar");
		})
		
		$(".close-calendar").click(function(){
			$("div.calendar-available").removeClass("show-calendar");
		})
	</script>
</body>
</html>
