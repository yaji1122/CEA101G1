<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.services.model.*"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page import="com.services_cart.model.*"%>
<%@ page import="com.services_cart.controller.*"%>

<%
ServicesService servicesSvc = new ServicesService();
List<ServicesVO> list = servicesSvc.getAll();
pageContext.setAttribute("list", list);
%>

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
		<nav class="mainmenu mobile-menu">
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
												href="<%=request.getContextPath()%>/frontend/services/services.jsp">美容美體</a></li>
											<li><a href="#">各式服務</a></li>
										</ul></li>
									<li><a class="nav-event">已預約服務</a></li>
									<li><a class="nav-event cart-nav">購物車</a></li>
								</ul>
							</nav>
						</div>
					</div>
				</div>
			</div>
		</div>
	</header>
	<!-- Header Section End -->

	<div id="banner">
		<div class="slider">
			<div class="slider-1">
				<img src="<%=request.getContextPath()%>/img/services/bannerSpa1.jpg"
					alt="" />
			</div>

			<div class="slider-1">
				<img src="<%=request.getContextPath()%>/img/services/bannerSpa2.jpg"
					alt="" />
			</div>

			<div class="slider-1">
				<img src="<%=request.getContextPath()%>/img/services/bannerSpa3.jpg"
					alt="" />
			</div>

			<div class="slider-1">
				<img src="<%=request.getContextPath()%>/img/services/bannerSpa4.jpg"
					alt="" />
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div id="context">
				<div class="info-title">
					<h2>讓你煥發身心的一切所需</h2>
					<p>擁有 360 度無死角的美麗壯觀印度洋海景，全館每時段提前預訂按摩體驗，選擇喜歡的頂級奢華方案，
						享受按摩師嫻熟的技巧與貴婦等級的服務，度過美好的南洋之旅時光！</p>
				</div>
			</div>
		</div>
	</div>
	<div class="container">

		<c:forEach var="servicesVO" items="${list}" varStatus="vs">
			<c:if test="${servicesVO.serv_type_no == '1'}">
				<c:if test="${servicesVO.serv_status == '1'}">
					<c:choose>

						<c:when test="${vs.count%2==1}">
							<div class="box1and2">
								<div class="container box-1-spa">
									<div class="row box-row">
										<div class="col-6 pic-1-spa">
											<img
												src="<%=request.getContextPath()%>/ServicesServlet?servno=${servicesVO.serv_no}&action=getOneServicesPic"
												class="card-img-top" alt="...">
										</div>
										<div class="col-6 content-spa">
											<div class="spa-title">
												<p>${servicesVO.serv_name}</p>
											</div>
											<div class="spa-para">
												<p>${servicesVO.serv_info}</p>
											</div>
											<div class="btn_price-spa">
												<div class="price-spa">價格:${servicesVO.serv_price}</div>
												<div class="btn-spa">
													<button type="button" class="btn btn-primary"
														data-toggle="modal" data-target="#exampleModal${vs.index}"
														id="viewDetailButton${vs.index}">立即預約</button>
												</div>
											</div>
										</div>
									</div>
								</div>
						</c:when>
						<c:otherwise>
							<div class="box1and2">
								<div class="container box-2-spa">
									<div class="row box-row">
										<div class="col-6 content-spa">
											<div class="spa-title">
												<p>${servicesVO.serv_name}</p>
											</div>
											<div class="spa-info">
												<p>${servicesVO.serv_info}</p>
											</div>
											<div class="btn_price-spa">
												<div class="price-spa">價格:${servicesVO.serv_price}</div>
												<div class="btn-spa">
													<button type="button" class="btn btn-primary"
														data-toggle="modal" data-target="#exampleModal${vs.index}"
														id="viewDetailButton${vs.index}">立即預約</button>
												</div>
											</div>
										</div>
										<div class="col-6 pic-2-spa">
											<img
												src="<%=request.getContextPath()%>/ServicesServlet?servno=${servicesVO.serv_no}&action=getOneServicesPic"
												class="card-img-top" alt="...">
										</div>
									</div>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
					<div class="modal fade" id="exampleModal${vs.index}" tabindex="-1"
						aria-labelledby="exampleModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLabel">服務預約</h5>
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<form name="shoppingForm"
									action="${pageContext.request.contextPath}/ServicesCartServlet"
									method="POST">
									<div class="modal-body">
										<p>請選擇日期:</p>
										<input name="hiredate" class="f_date1" type="text">

										<div class="form-group">
											<label for="exampleFormControlSelect1">服務人數</label> <select
												name="quantity" class="form-control"
												id="exampleFormControlSelect1">
												<option>1</option>
												<option>2</option>
												<option>3</option>
												<option>4</option>
											</select>
										</div>
										<div class="form-group">
											<label for="exampleFormControlSelect2">服務場所</label> <select
												name="locations" class="form-control"
												id="exampleFormControlSelect2">
												<option>102客房</option>
												<option>露天按摩A區</option>
												<option>露天按摩B區</option>
											</select>
										</div>
										<p>服務價格:${servicesVO.serv_price}</p>
										<input type="hidden" name="servicesNo"
											value="${servicesVO.serv_no}"> <input type="hidden"
											name="price" value="${servicesVO.serv_price}">
										<!-- <input type="hidden" name="hiredate" value="2017-03-08T12:30:54">  -->
										<input type="hidden" name="action" value="ADD">




									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">取消</button>
										<input type="submit" class="btn btn-primary" value="預約送出">
									</div>
								</form>
							</div>
						</div>
					</div>
				</c:if>
			</c:if>
		</c:forEach>

	</div>

	<div class="cart-content">

		<%Vector<ServicesItem> buylist = (Vector<ServicesItem>) session.getAttribute("shoppingcart");%>
		<%if (buylist != null && (buylist.size() > 0)) {%>

		<div class="tableWithClose">
			<table class="table table-striped cart-table">
				<thead>
					<tr>
						<th>服務名稱</th>
						<th>時間</th>
						<th>價格</th>
						<th>人數</th>
						<th>地點</th>
						<th></th>
					</tr>
				</thead>

				<%
					for (int index = 0; index < buylist.size(); index++) {
					ServicesItem order = buylist.get(index);
				%>
				<tbody>
					<tr>
						<td><%=order.getServicesNo()%></td>
						<td><%=order.getServTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))%></td>
						<td><%=order.getPrice()%></td>
						<td><%=order.getQuantity()%></td>
						<td><%=order.getLocations()%></td>
						<td><div>
								<form name="deleteForm"
									action="${pageContext.request.contextPath}/ServicesCartServlet"
									method="POST">
									<input type="hidden" name="action" value="DELETE"> <input
										type="hidden" name="del" value="<%=index%>"> <input
										type="submit" value="刪除">
								</form>
							</div></td>
					</tr>

					<%}%>
				</tbody>
			</table>
		</div>
		<div class="close-icon cart-content-close">
			<i class="fas fa-times icon" style="margin-top: 5%;"></i>
		</div>

		<div class="checkOut-submit">
			<form name="checkoutForm"
				action="${pageContext.request.contextPath}/ServicesCartServlet"
				method="POST">
				<input type="hidden" name="action" value="CHECKOUT"> <input
					type="submit" value="預約送出">
			</form>
		</div>
	</div>
	<%
		} else {
	%>
	<p>購物車尚無內容</p>
	<%
		}
	%>






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
			step : 15, //step: 60 (這是timepicker的預設間隔60分鐘)
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
</body>

</html>