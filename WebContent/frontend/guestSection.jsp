<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.members.model.*"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/frontend/files/commonCSS.file"%>
<title>住客專區</title>
</head>
<style>
.menu-item .nav-menu .mainmenu li a {
    color: black;
}
div.main-wrapper {
    height: 100vh;
    display: flex;
    flex-direction: row;
    justify-content: space-evenly;
    align-items: center;
}
div.section {
    height: 60vh;
    width: 20vw;
}
/*翻轉*/
.flip-container {
    -webkit-perspective: 1000;
    -moz-perspective: 1000;
    -o-perspective: 1000;
    perspective: 1000;
    cursor: pointer;
}

.flip-container:hover .flipper,
.flip-container.hover .flipper {
    -webkit-transform: rotateY(180deg);
    -moz-transform: rotateY(180deg);
    -o-transform: rotateY(180deg);
    transform: rotateY(180deg) scale(1.2);
}

.flip-container,
.front,
.back {
    width: 20vw;
    height: 60vh;
}

.flipper {
    -webkit-transition: 0.6s;
    -webkit-transform-style: preserve-3d;

    -moz-transition: 0.6s;
    -moz-transform-style: preserve-3d;

    -o-transition: 0.6s;
    -o-transform-style: preserve-3d;

    transition: 0.6s;
    transform-style: preserve-3d;

    position: relative;
}

.front,
.back {
    -webkit-backface-visibility: hidden;
    -moz-backface-visibility: hidden;
    -o-backface-visibility: hidden;
    backface-visibility: hidden;

    position: absolute;
    top: 0;
    left: 0;
}

#activity .front {
    background-image: url("<%=request.getContextPath()%>/img/guestSection/activity.jpg");
    background-repeat: no-repeat;
    background-size: cover;
    z-index: 2;
}
#activity .back {
    background-image: url("<%=request.getContextPath()%>/img/guestSection/act-back.jpg");
    background-repeat: no-repeat;
    background-size: cover;
    z-index: 2;
}
#service .front {
	background-image: url("<%=request.getContextPath()%>/img/guestSection/service.jpg");
    background-repeat: no-repeat;
    background-size: cover;
    z-index: 2;
}
#service .back {
	background-image: url("<%=request.getContextPath()%>/img/guestSection/svc-back.jpg");
    background-repeat: no-repeat;
    background-size: cover;
    z-index: 2;
}
#meal .front {
	background-image: url("<%=request.getContextPath()%>/img/guestSection/meal.jpg");
    background-repeat: no-repeat;
    background-size: cover;
    z-index: 2;
}
#meal .back {
	background-image: url("<%=request.getContextPath()%>/img/guestSection/meal-back.jpg");
    background-repeat: no-repeat;
    background-size: cover;
    z-index: 2;
}
#shopping .front {
	background-image: url("<%=request.getContextPath()%>/img/guestSection/shopping.jpg");
    background-repeat: no-repeat;
    background-size: cover;
    z-index: 2;
}
#shopping .back {
	background-image: url("<%=request.getContextPath()%>/img/guestSection/shop-back.jpg");
    background-repeat: no-repeat;
    background-size: cover;
    z-index: 2;
}
.back {
    -webkit-transform: rotateY(180deg);
    -moz-transform: rotateY(180deg);
    -o-transform: rotateY(180deg);
    transform: rotateY(180deg);
    background: #f8f8f8;
}

.front .name,
.back .name {
	text-align: center;
    font-size: 2em;
	width:100%;
    display: inline-block;
    color: white;
    font-family: Arial, Helvetica, sans-serif;
	background-color: #bbbbbb80;
    font-weight: 700;
    letter-spacing: 1px;
    padding: 5px 10px;
    bottom: 30px;
    position: absolute;
    left: 50%;
    transform: translateX(-50%);
}

.back-logo {
    position: absolute;
    top: 40px;
    left: 90px;
    width: 160px;
    height: 117px;
    background: url(http://davidwalsh.name/demo/logo.png) 0 0 no-repeat;
}

.back-title {
    font-weight: bold;
    color: #00304a;
    position: absolute;
    top: 180px;
    left: 0;
    right: 0;
    text-align: center;
    text-shadow: 0.1em 0.1em 0.05em #acd7e5;
    font-family: Courier;
    font-size: 2em;
}

.back p {
    position: absolute;
    bottom: 40px;
    left: 0;
    right: 0;
    text-align: center;
    padding: 0 20px;
    font-family: arial;
    line-height: 2em;
}
	
</style>
<%@ include file="/frontend/files/loginCSS.file" %>
<body>
<%@ include file="/frontend/files/login.file" %>   <!-- 登入必要檔案 -->
<%@ include file="/frontend/files/loginbox.file" %>  <!-- 登入必要檔案 -->
<%@ include file="/frontend/files/header.file"%>
	<div class="main-wrapper">
		<div class="section flip-container" id="activity"
			ontouchstart="this.classList.toggle('hover');">
			<div class="flipper">
				<div class="front">
					<span class="name">ACTIVITY</span>
				</div>
				<div class="back">
					<span class="name">精彩活動</span>
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
					<span class="name">至尊服務</span>
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
					<span class="name">美食佳餚</span>
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
					<span class="name">購物天堂</span>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/frontend/files/commonJS.file"%>
	<script>
		$("#activity").click(function(){
			window.location.href = "<%=request.getContextPath()%>/frontend/activity/activity.jsp"
		})
		$("#service").click(function(){
			window.location.href = "<%=request.getContextPath()%>/frontend/services/services.jsp"
		})
		$("#meal").click(function(){
			window.location.href = "<%=request.getContextPath()%>/frontend/meal/meal.jsp"
		})
		$("#shopping").click(function(){
			window.location.href = "<%=request.getContextPath()%>/frontend/shop/shopPage.jsp"
		})
	</script>
</body>
</html>