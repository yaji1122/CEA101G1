<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.List"%>
<%@ page import="com.roomrsv.model.*"%>
<%@ page import="com.members.model.*"%>
<%@ page import="com.roomtype.model.*"%>
<%@ page import="org.json.JSONObject"%>
<%@ include file="/frontend/files/login.file"%>

<jsp:useBean id="rmtypeSvc" scope="page"
	class="com.roomtype.model.RoomTypeService" />
<jsp:useBean id="rmpicSvc" scope="page"
	class="com.roompic.model.RoomPicService" />
<% 
int total = 0; 
List<JSONObject> bookingCart = (List<JSONObject>) session.getAttribute("bookingCart");
total = bookingCart.stream()
		.mapToInt(e -> Integer.parseInt(e.getString("subtotal")))
		.sum();
pageContext.setAttribute("total", total);
%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/png"
	href="<%=request.getContextPath()%>/img/loading.png" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css"
	integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA=="
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/nice-select.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/slick-theme.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/slick.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/front/style-for-all.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/front/booking.css" />
<title>戴蒙訂房系統</title>
</head>
<%@ include file="/frontend/files/loginCSS.file"%>
<body>
	<%@ include file="/frontend/files/loginbox.file"%>
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
	<h4 class="checkout-title">
		<i class="fas fa-medal medal"></i>年度最佳度假村
	</h4>
	<h4 class="checkout-title">
		<i class="fas fa-luggage-cart"></i>購物車清單
	</h4>
	<!-- 主頁面 -->
	<div class="main-wrapper">
		<div class="content">
			<div class="show-all-booking">
			<% int i = 1; %>
				<c:forEach var="roomCard" items="${bookingCart}">
					<div class="room-card">
					<div class="booking-item-title"><h4>房間 <%= i++ %></h4></div>
						<div class="booking-date">
							<img class="booking-icons"
								src="<%=request.getContextPath()%>/img/icon/calendar.png" />
							<h4>${roomCard.getString("startDate")}-
								${roomCard.getString("leaveDate")}
								(${roomCard.getString("stay")} 晚)</h4>
						</div>
						<div class="booking-guest">
							<img class="booking-icons"
								src="<%=request.getContextPath()%>/img/icon/user.png" />
							<h4>${roomCard.getString("guest")}成人</h4>
						</div>

						<div class="booking-short-intro">
							<h3 class="room-title">${rmtypeSvc.getOne(roomCard.getString("rmtype")).type_name}</h3>
							<c:forEach var="rmtypepic"
								items="${rmpicSvc.getAllByRoomType(roomCard.getString('rmtype'))}"
								begin="0" end="0">
								<img
									src="<%=request.getContextPath()%>/RoomPicServlet?rmpicno=${rmtypepic.rm_pic_no}&action=getOneRmPic" />
							</c:forEach>
						</div>
						<div class="cancel-rules">
							<h5>退費政策</h5>
							<div class="rule">
								<p>取消日期</p>
								<p>取消日期計費比例</p>
							</div>
							<div class="rule">
								<p>13~4天之前</p>
								<p>收取訂金30%</p>
							</div>
							<div class="rule">
								<p>3~1天之前</p>
								<p>收取訂金70%</p>
							</div>
							<div class="rule">
								<p>當天取消</p>
								<p>收取訂金100%</p>
							</div>
						</div>

						<div class="booking-total-price">
							<h5>價格小計</h5>
							<h4>
								<span>USD$</span><span class="subtotal">
								<fmt:formatNumber type="number" pattern="#,###" value="${roomCard.getString('subtotal')}"/> 
								</span>
								<span class="etc">＊價格已含稅,服務費</span>
							</h4>
						</div>
						<div class="remove-button">
						<button data-rmtype="${roomCard.getString('rmtype')}" data-id="${roomCard.getString('roomCardId')}" class="remove-booking"><i class="far fa-trash-alt"></i>移除</button>
						</div>
					</div>
				</c:forEach>
			</div>

			<div class="variables booking-confirm">
				<div class="last-price">
					<p>Total Price</p>
					<h3 class="last-price">USD$<span></span></h3>
				</div>
				<p>須預付 30% 訂金作為訂房費用</p>
				<div class="last-price">
					<p>Deposit Rate</p>
					<h3 class="deposit-price">USD$<span></span></h3>
				</div>
				<div class="booking-confirm-actions">
				<a class="pay-now">付款預訂</a>
				<a class="add-more-room" href="<%=request.getContextPath()%>/booking/Available?<%=request.getQueryString()%>">追加房間</a>
				</div>
			</div>
			

			
		</div>
		<script src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
		<script
			src="<%=request.getContextPath()%>/js/jquery.nice-select.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
		<script src="<%=request.getContextPath()%>/js/slick.min.js"></script>
		<script src="<%=request.getContextPath()%>/js/front/main.js"></script>
		<script>
			$(document).ready(function(){
				totalPrice(); //進入頁面後先計算總價
				
				//從購物車移除
	        	$(document).on("click", ".remove-booking", function(){
	        		let id = $(this).attr("data-id");
	        		let rmtype = $(this).attr("data-rmtype");
	        		let card = $(this);
	        		$.ajax({
	        			url:"<%=request.getContextPath()%>/booking/Available?action=removefromcart",
	        			type:"POST",
	        			data:{
	        				roomCardId: id,
	        			},
	        			success: function(){
	        				card.parents(".room-card").animate({opacity: 0},
	        						500,function(){
	        					card.parents(".room-card").remove();
	            				totalPrice();
	        				});
	        			}
	        		})
	        	})
				
				$(".add-more-room").click(function(){
					history.back();
				})
				function totalPrice(){
					let prices = $("span.subtotal");
					let total = 0;
					$.each(prices, function(index, value){
						total += parseInt(value.innerText.replace(",", ""));
					})
					
					$(".last-price span").eq(0).text(total.toLocaleString());
					$(".deposit-price span").eq(0).text(total*0.3.toLocaleString());
				}
				
			})
		</script>
</body>
</html>