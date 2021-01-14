<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.List" %>
<%
String pass = (String) session.getAttribute("bookingPass");
if (pass == null) response.sendRedirect(request.getContextPath() + "/frontend/index.jsp"); //如果並非透過下定後的頁面將被重新導向
session.removeAttribute("bookingPass");
%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/png"
	href="<%=request.getContextPath()%>/img/loading.png" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/front/style-for-all.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/front/booking.css" />
<script src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
<title>戴蒙訂房系統</title>
</head>
<style>
	.bk-msg {
		margin-top:-100%;
	}
	.banner-pic {
		height: 100vh;
	}
	.banner-pic img {
		top:0;
		left:0;		
		height:100vh;
	}
	.main-wrapper {
		position: absolute;
		left:0px;
		top:0px;
		height:100vh;
		width:100vw;
		display: flex;
		flex-direction: column;
		justify-content: center;
	}
	.main-wrapper{
		min-width:100%;
	}
	#pkupbooking {
		z-index:-99;
		opacity:0;
		position: absolute;
		left:50%;
		top:-100%;
		transform:translate(-50%, -50%);
		box-shadow: 0px 0px 3px black;
		transition: 1s ease;
	}
	#pkupbooking iframe {
		width:100vw;
		height:100vh;
		border:none;
	}
	#pkupbooking.show {
		z-index: 999;
		top:50%;
		opacity:1;
	}
	.receipts {
		margin-top: 50px;
	}
</style>
<body>
	<!-- preloader -->
	<div id="preloder">
		<img id="preloaderpic"
			src="${pageContext.request.contextPath}/img/loading.png" />
		<div class="loader"></div>
	</div>
	<!-- preloader -->
	<!-- header start -->
	<header class="booking-header">
		<div class="logo">
			<img src="<%=request.getContextPath()%>/img/logo.png" alt="" />
		</div>
	</header>
	<!-- header end -->
	<div class="banner-pic">
		<img src="<%=request.getContextPath()%>/img/booking-bg.jpeg" alt="" />
	</div>
	<!-- 主頁面 -->
	<div class="main-wrapper">
		<div class="bk-msg">
			<jsp:include page="resultReceipt.jsp"/>
		</div>
		
	</div>
	<div id="pkupbooking">
		<iframe src="<%=request.getContextPath()%>/frontend/roomrsv/pickup.jsp"></iframe>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
	<script src="<%=request.getContextPath()%>/js/front/main.js"></script>
	<script>
		$(document).ready(function(){
			//button
			$("#pkup").click(function(){
				$("#pkupbooking").addClass("show");
			})
			$(".bk-msg").animate({opacity: "1", marginTop: "40px"},2500, function(){
				$(".bk-msg").animate({marginTop:"30px"}, 200)
			});
		})
	</script>
</body>
</html>