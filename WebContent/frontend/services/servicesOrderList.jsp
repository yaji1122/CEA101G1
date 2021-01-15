<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.services.model.*"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page import="java.util.stream.Collectors"%>
<%@ page import="com.service_order.model.*"%>
<%@ page import="com.bookingorder.model.*"%>

<%
	ServiceOrderService serviceOrderSvc = new ServiceOrderService();
/* List<ServiceOrderVO> serviceOrderList = serviceOrderSvc.getAll(); */
/* pageContext.setAttribute("serviceOrderList", serviceOrderList); */
MembersVO membersVO = (MembersVO) session.getAttribute("member");
if (membersVO != null) {
	List<ServiceOrderVO> serviceOrderList = serviceOrderSvc.getAllByMbId(membersVO.getMb_id());
	pageContext.setAttribute("serviceOrderList", serviceOrderList);
}
%>

<jsp:useBean id="servicesSvc" scope="page"
	class="com.services.model.ServicesService" />

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8" />
<meta name="description" content="Sona Template" />
<meta name="keywords" content="Sona, unica, creative, html" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>Diamond Resort</title>


<%@ include file="/frontend/files/commonCSS.file"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/slick.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/slick-theme.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/slicknav.min.css"
	type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/front/services.css"
	type="text/css" />
<style>
.table-orderList {
	padding: 10%;
}

.orderListTable {
	width: 80%;
	position: absolute;
	text-align: center;
}

input:disabled {
	cursor: no-drop;
}

.mainmenu {
	position: absolute;
	right: 0;
}

.td-table {
	border-bottom: dotted 2px lightgray;
}

tbody tr {
	
}

td {
	padding: 20px 0px;
}
</style>

</head>

<body>
	<div class="black"></div>
	<%@ include file="/frontend/files/loginCSS.file"%>
	<%@ include file="/frontend/files/login.file"%>
	<%@ include file="/frontend/files/loginbox.file"%>

	<!-- preloader -->
	<div id="preloder">
		<img id="preloaderpic"
			src="${pageContext.request.contextPath}/img/loading.png" />
		<div class="loader"></div>
	</div>
	<!-- preloader -->

	<!-- offcanvas menu start -->

	<div class="offcanvas-menu-overlay"></div>
	<div class="canvas-open">
		<i class="icon_menu"></i>
	</div>
	<i class="fas fa-user-circle offcanvs-log-in"></i>
	<div class="offcanvas-menu-wrapper">
		<div class="canvas-close">
			<i class="icon_close"></i>
		</div>
		<div class="header-configure-area">
			<a href="#" class="bk-btn">立刻訂房</a>
		</div>
		<nav class="mainmenu mobile-menu menu-bar">
			<ul>
				<li><a class="nav-event">住客專區</a>
					<ul class="dropdown">
						<li><a href="./activity.html">活動報名</a></li>
						<li><a href="">預約服務</a></li>
						<li><a href="">訂購餐點</a></li>
					</ul></li>
				<li><a href="shop.html" class="nav-evnet">戴蒙商城</a></li>
				<li><a class="nav-event" href="./rooms.html">客房介紹</a></li>
				<li><a class="nav-event log-in">會員登入</a></li>
				<li><a href="" class="nav-event"
					style="color: rgb(240, 218, 162)">加入會員</a></li>
			</ul>
		</nav>
		<div id="mobile-menu-wrap"></div>
		<div class="top-social">
			<a href="#"><i class="fa fa-facebook"></i></a> <a href="#"><i
				class="fa fa-twitter"></i></a> <a href="#"><i
				class="fa fa-tripadvisor"></i></a> <a href="#"><i
				class="fa fa-instagram"></i></a>
		</div>
		<ul class="top-widget">
			<li><i class="fa fa-phone"></i> (12) 345 67890</li>
			<li><i class="fa fa-envelope"></i> info.colorlib@gmail.com</li>
		</ul>
	</div>
	<!-- offcanvas menu end -->

	<!-- Header Section Begin -->
	<header class="header-section nav-fixed">
		<div class="menu-item">
			<div class="container">
				<div class="row">
					<div class="col-lg-7">
						<div class="logobox">
							<a href="${pageContext.request.contextPath}/frontend/index.jsp"><img
								src="${pageContext.request.contextPath}/img/logo.png" /> </a>
						</div>
					</div>
					<div class="col-lg-5">
						<div class="nav-menu">
							<nav class="mainmenu">
								<ul>
									<li><a href="#" class="nav-event">至尊服務</a>

										<ul class="dropdown">
											<li><a
												href="<%=request.getContextPath()%>/frontend/services/services.jsp?serv_type_no=1">美容美體</a></li>
											<li><a href="<%=request.getContextPath()%>/frontend/services/services.jsp?serv_type_no=2">各式服務</a></li>
										</ul></li>
									<li><a class="nav-event">已預約服務</a></li>
									<!-- <li><a class="nav-event cart-nav">購物車</a></li> -->
								</ul>
							</nav>
						</div>
					</div>
				</div>
			</div>
		</div>
	</header>
	<!-- Header Section End -->
	<div class="table-orderList">
		<table class="orderListTable">
			<thead>
				<tr>
					<th scope="col">訂單編號</th>
					<th scope="col">訂房單號</th>
					<th scope="col">訂單成立時間</th>
					<th scope="col">服務名稱</th>
					<th scope="col">預約時間</th>
					<th scope="col">服務人數</th>
					<th scope="col">服務場所</th>
					<th scope="col">訂單總額</th>
					<th scope="col">訂單狀態</th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>

				<c:forEach var="serviceOrderVO" items="${serviceOrderList}">
					<tr class="td-table">
						<td scope="row">${serviceOrderVO.serv_odno}</td>
						<td>${serviceOrderVO.bk_no}</td>
						<td><fmt:formatDate value="${serviceOrderVO.od_time}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>


						<td>
							${servicesSvc.getOneServices(serviceOrderVO.serv_no).serv_name}</td>
						<td><fmt:formatDate value="${serviceOrderVO.serv_time}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td>${serviceOrderVO.serv_count}</td>
						<td>${serviceOrderVO.locations}</td>
						<td>${serviceOrderVO.total_price}</td>
						<td><c:choose>
								<c:when test="${serviceOrderVO.od_status.equals('0')}">未完成</c:when>
								<c:when test="${serviceOrderVO.od_status.equals('1')}">已完成</c:when>
								<c:when test="${serviceOrderVO.od_status.equals('2')}">已取消</c:when>
							</c:choose></td>
						<td>
							<FORM METHOD="post"
								ACTION="${pageContext.request.contextPath}/ServiceOrderServlet"
								style="margin-bottom: 0px;">
								<input type="submit" value="取消訂單" class="cancel btn btn-outline-info"
									<c:if test="${serviceOrderVO.od_status != 0}">disabled</c:if>>
								<input type="hidden" name="serv_odno"
									value="${serviceOrderVO.serv_odno}"> <input
									type="hidden" name="action" value="cancelOdStatus">
							</FORM>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>





	<%@ include file="/frontend/files/commonJS.file"%>
	<!-- 基本JS檔案 -->
	<script src="${pageContext.request.contextPath}/js/slick.min.js"></script>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/jquery.datetimepicker.full.min.js"></script>

	<script>
		$('.slider').slick({
			autoplay : true,
			autoplaySpeed : 5000,
			speed : 1500,
			fade : true,
			arrows : false,
			dots : false,
		});
	</script>
	<script>
		$.datetimepicker.setLocale('zh'); // kr ko ja en
		$('.f_date1').datetimepicker({
			theme : '', //theme: 'dark',
			timepicker : true, //timepicker: false,
			step : 60, //step: 60 (這是timepicker的預設間隔60分鐘)
			format : 'Y-m-d H:i',
			value : new Date(),
		//disabledDates:    ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
		//startDate:	        '2017/07/10',  // 起始日
		//minDate:           '-1970-01-01', // 去除今日(不含)之前
		//maxDate:           '+1970-01-01'  // 去除今日(不含)之後
		});
	</script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
	<script>
		$(document).ready(function() {

			$(".cart-nav").click(function() {
				$(".cart-content").addClass("cart-content-show");
				$(".black").css("display", "block");
				$(".black").click(function() {
					$(".cart-content").removeClass("cart-content-show");
					$(".black").css("display", "none");
				})
			});
			$(".cart-content-close").click(function() {
				$(".cart-content").removeClass("cart-content-show");
				$(".black").css("display", "none");
			});
			/* $(".cart-nav").click(function () {
				Swal.fire({
						icon: "info",
						text: "您尚未選取服務喔！"
						
				});
				}); */
		});
	</script>
	<script>
		$(document).ready(function() {
			$(".btn-serv-submit").click(function() {
				$("#quantity").val(1);
				console.log($(this).attr("data-serv_no"));
				$(".modal-title").text($(this).attr("data-serv_name"));
				$("#name-serv").val($(this).attr("data-serv_name"));
				$("#name-hidden").val($(this).attr("data-serv_no"));
				$(".serv-price").text($(this).attr("data-serv_price"));
				$(".serv-price").val($(this).attr("data-serv_price"));
				$("#total-price").text($(this).attr("data-serv_price"));
				let price = $(this).attr("data-serv_price");
				$(".btn-cart-qty").click(function() {

					console.log('price:' + price);
					let quantity = document.getElementById("quantity").value;
					console.log('quantity:' + quantity);
					let total = quantity * price;
					$("#total-price").text(total);
				});
			})

		})

		function add() {
			var text = $("#quantity").val();
			var config = {
				"url" : "${pageContext.request.contextPath}/AddCartServlet",
				"async" : false,
				"type" : "post",
				"dataType" : "text",
				"data" : {
					"text" : text,
				},
				"success" : function(result) {
					$("#quantity").val(result);
				},
				"error" : function(xhr, status, error) {

				}
			};
			$.ajax(config);
		}
		function sub() {
			var config = {
				"url" : "${pageContext.request.contextPath}/SubCartServlet",
				"async" : false,
				"type" : "post",
				"dataType" : "text",
				"data" : {
					"text" : $("#quantity").val(),
				},
				"success" : function(result) {
					$("#quantity").val(result);
				},
				"error" : function(xhr, status, error) {

				}
			};
			$.ajax(config);
		}
	</script>
</body>

</html>