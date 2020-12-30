<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List" %>
<%@ page import="com.roomrsv.model.*" %>
<%@ page import="com.members.model.*" %>
<%@ page import="com.roomtype.model.*" %>
<%@ page import="org.json.JSONObject" %>
<%@ include file="/frontend/files/login.file" %>

<jsp:useBean id="rmtypeSvc" scope="page" class="com.roomtype.model.RoomTypeService"/>
<jsp:useBean id="rmpicSvc" scope="page" class="com.roompic.model.RoomPicService"/>
<!DOCTYPE html>
<html>
<head>
		<link rel="icon" type="image/png" href="<%=request.getContextPath()%>/img/loading.png" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css"
            integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA=="
            crossorigin="anonymous"
        />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/nice-select.css" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/slick-theme.css" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/slick.css" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/front/style-for-all.css" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/front/booking.css" />
        <title>戴蒙訂房系統</title>
</head>
<%@ include file="/frontend/files/loginCSS.file" %>
<body>
	<%@ include file="/frontend/files/loginbox.file" %>
 	<!-- preloader -->
	<div id="preloder">
		<img id="preloaderpic"
			src="${pageContext.request.contextPath}/img/loading.png" />
		<div class="loader"></div>
	</div>
	 <!-- preloader -->
	<div class="curtain"></div>
        <header class="booking-header">
            <div class="logo">
                <img src="<%=request.getContextPath()%>/img/logo.png" alt="" />
            </div>
            
            
        </header>
        <div class="banner-pic">
            <img src="<%=request.getContextPath()%>/img/booking-bg.jpeg" alt="" />
        </div>
        <h2 class="checkout-title">BEST PRICE GUARANTEE</h2>
        <h2 class="checkout-title"><i class="fas fa-luggage-cart"></i>購物車</h2>
        <!-- 主頁面 -->
        <div class="main-wrapper">
             <div class="content">
              	<div class="show-all-booking"> 
	             	<c:forEach var="roomCard" items="${bookingCart}">
	             	<div class="room-card">
	             			<img class="cart-list-icons" src="<%=request.getContextPath()%>/img/icon/calendar.png" /><h4> ${roomCard.getString("startDate")} - ${roomCard.getString("leaveDate")} </h4>
	                         <img class="cart-list-icons" src="<%=request.getContextPath()%>/img/icon/user.png" /><p>${roomCard.getString("guest")} 成人</p>
	                        <h3 class="room-title">${rmtypeSvc.getOne(roomCard.getString("rmtype")).type_name}</h3>
	                        <div class="roomCard-pic">
	                        	<c:forEach var="rmtypepic" items="${rmpicSvc.getAllByRoomType(roomCard.getString('rmtype'))}" begin="0" end="1">
	                            <div><img src="<%=request.getContextPath()%>/RoomPicServlet?rmpicno=${rmtypepic.rm_pic_no}&action=getOneRmPic" /></div>
	                            </c:forEach>
	                        </div>
	                        <div class="cancel-rules">
	                            <h6>取消費用</h6>
	                            <div class="rule">
	                            	<p>取消日期</p><p>取消日期計費比例</p>
	                            </div>
	                            <div class="rule">
	                            	<p>13~4天之前</p><p>收取訂金30%</p>
	                            </div>
	                            <div class="rule">
	                            	<p>3~1天之前</p><p>收取訂金70%</p>
	                            </div>
	                            <div class="rule">
	                            	<p>當天取消</p><p>收取訂金100%</p>
	                            </div>
	                        </div>
	                        <hr />
	                        <div class="check-room-detail">
	                            <p>價格詳情</p>
	                            <img src="<%=request.getContextPath()%>/img/icon/down-arrow.png" alt="" />
	                        </div>
	                        <div class="price-detail room-detail">
	                            
	                        </div>
	                        
	                         <p>  此為${roomCard.getString("stay")}晚 ${roomCard.getString("guest")}人 1間 </p>
	                        <h4>小計<span>USD$</span><span class="subtotal">${roomCard.getString("subtotal")}</span>
	                           <span class="etc">＊價格已含稅,服務費</span>
	                       </h4>
	                    </div>
	                   </c:forEach> 
                   </div>
                   
                     <div class="variables">
              			 <a>繼續預定</a>
              			 <a>付款結帳</a>
             		</div>     
            	 </div>
                
           
        </div>
        <script src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
        <script src="<%=request.getContextPath()%>/js/jquery.nice-select.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
        <script src="<%=request.getContextPath()%>/js/slick.min.js"></script>
        <script src="<%=request.getContextPath()%>/js/front/main.js"></script>
        
</body>
</html>