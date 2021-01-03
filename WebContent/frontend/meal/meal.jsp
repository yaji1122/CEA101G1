<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mealtype.model.*"%>
<%@ page import="com.meal.model.*"%>
<%@ page import="com.cart.model.*"%>
<%@ page import="java.util.*"%>

<%
	MealTypeVO mealTypeVO = (MealTypeVO) request.getAttribute("mealTypeVO");
	MealVO mealVO = (MealVO) request.getAttribute("mealVO");
%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Diamond Resort</title>
<!-- Css Styles -->
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
	integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
	crossorigin="anonymous" />
<%@ include file="/frontend/files/commonCSS.file"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/front/meal.css"
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

	<!-- header menu -->
	<header class="header-section">
		<div class="menu-item">
			<div class="nav-menu">
				<nav class="mainmenu">
					<ul class="mainmenu-row">
						<li class="nav-list"><a href="#" class="nav-evnet">最新消息</a></li>
						<li class="nav-list"><a class="nav-event">住客專區</a>
							<ul class="dropdown">
								<li><a
									href="<%=request.getContextPath()%>/frontend/activity/activity.jsp">活動報名</a></li>
								<li><a
									href="<%=request.getContextPath()%>/frontend/services/services.jsp">預約服務</a></li>
								<li><a
									href="<%=request.getContextPath()%>/frontend/meal/meal.jsp">訂購餐點</a></li>
							</ul></li>
						<li class="nav-list"><a
							href="<%=request.getContextPath()%>/frontend/shop/shopPage.jsp"
							class="nav-evnet">戴蒙商城</a></li>
						<div class="logobox">
							<a href="${pageContext.request.contextPath}/frontend/index.jsp"><img
								src="${pageContext.request.contextPath}/img/logo.png" /> </a>
						</div>
						<li class="nav-list"><a class="nav-event"
							href="${pageContext.request.contextPath}/frontend/rooms/rooms.jsp">客房</a>
						</li>
						<li class="nav-list">
						<a class="nav-event">
							<i class="fas fa-shopping-cart shopping-cart shopping-cart-icon" style="color: white;"></i>
							</a>
						</li>
						<li class="nav-list"><a class="nav-event"> <c:choose>
									<c:when test="${member != null}">
										<i class="far fa-gem"></i>
										</i>會員中心</a>
							<ul class="dropdown">
								<li><a
									href="${pageContext.request.contextPath}/frontend/members/memberInfo.jsp">個人檔案</a></li>
								<li><a href="#">我的假期</a></li>
								<li><a href="#">歷史訂單</a></li>
								<li><a
									href="${pageContext.request.contextPath}/LoginHandler?mb_email=${member.mb_email}&action=member-logout&location=${pageContext.request.requestURL}">登出</a></li>
							</ul></li>
						</c:when>
						<c:otherwise>
							<i class="fas fa-user-circle log-in"></i>
							</a>
						</c:otherwise>
						</c:choose>
						</li>
					</ul>
				</nav>
			</div>
		</div>
	</header>
	<!-- header menu -->


	<div class="body-wrapper">
		<div class="img-slide">
			<div class="container">
				<div class="row">
					<div class="col">
						<div id="carouselExampleIndicators" class="carousel slide"
							data-ride="carousel">
							<div class="carousel-inner" role="listbox">
								<div class="carousel-item active">
									<img class="d-block w-100 img-box"
										src="${pageContext.request.contextPath}/img/meal/food.jpg"
										alt="First slide">
								</div>
								<div class="carousel-item ">
									<img class="d-block w-100 img-box"
										src="${pageContext.request.contextPath}/img/meal/food-2.jpg "
										alt="Second slide ">
								</div>
								<div class="carousel-item ">
									<img class="d-block w-100 img-box"
										src="${pageContext.request.contextPath}/img/meal/food-3.jpg "
										alt="third slide ">
								</div>
								<div class="carousel-item ">
									<img class="d-block w-100 img-box"
										src="${pageContext.request.contextPath}/img/meal/food-4.jpg "
										alt="fourth slide ">
								</div>
							</div>
							<a class="carousel-control-left"
								href="#carouselExampleIndicators" role="button"
								data-slide="prev"> <span class="carousel-control-prev-icon"
								aria-hidden="true"></span> <span class="sr-only">Previous</span>
							</a> <a class="carousel-control-right"
								href="#carouselExampleIndicators" role="button"
								data-slide="next"> <span class="carousel-control-next-icon"
								aria-hidden="true"></span> <span class="sr-only">Next</span>
							</a>
							<ol class="carousel-indicators ">
								<li data-target="#carouselExampleIndicators " data-slide-to="0 "
									class="active "></li>
								<li data-target="#carouselExampleIndicators " data-slide-to="1 "></li>
								<li data-target="#carouselExampleIndicators " data-slide-to="2 "></li>
								<li data-target="#carouselExampleIndicators " data-slide-to="3 "></li>
							</ol>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="wrapper ">
			<div class="container ">
				<div class="row ">
					<div class="col-lg-3 type-wrapper nav-fixed-type ">
						<div id="list type-list " class="list-group ">
							<jsp:useBean id="mealTypeSvc" scope="page"
								class="com.mealtype.model.MealTypeService" />
							<c:forEach var="mealTypeVO" items="${mealTypeSvc.all}"
								varStatus="i">
								<a class="list-group-item list-group-item-action "
									href="#list-item-${i.index}">
									<div class="type-box">
										<h4 class="type-title ">${mealTypeVO.type_name}</h4>
									</div>
								</a>
							</c:forEach>
						</div>
					</div>

					<div class=" col-lg-9 card-wrapper ">
						<div data-spy="scroll " data-target="#list " data-offset="0"
							class="scrollspy-example ">
							<c:forEach var="mealTypeVO" items="${mealTypeSvc.all}"
								varStatus="j">

								<h2 class="title" id="list-item-${j.index}"
									style="visibility: hidden;">${mealTypeVO.type_name}</h2>
								<hr style="width: 800px;">
								<div class="row">
									<div class="col-lg-12">
										<h2 class="title">${mealTypeVO.type_name}</h2>
									</div>
								</div>
								<div class="row ">
									<jsp:useBean id="mealSvc" scope="page"
										class="com.meal.model.MealService" />

									<c:forEach var="mealVO"
										items="${mealSvc.getActiveMeal(meal_status)}" varStatus="k">
										<c:if
											test="${(mealVO.meal_type_no == mealTypeVO.meal_type_no)}">

											<div class="col-lg-4 card-box">
												<form id="form${k.index}" method="post"
													action="${pageContext.request.contextPath}/AddToCartServlet">
													<figure class="snip1268" id="card">
														<div class="image">
															<img
																src="${pageContext.request.contextPath}/MealServlet?action=view_mealpic&meal_no=${mealVO.meal_no}"
																alt="sq-sample4" /> <a href="#" class="add-to-cart"
																onclick="document.getElementById('form' + ${k.index} + '').submit();">加入購物車</a>
														</div>
														<figcaption>
															<h3 id="meal_name${k.index}">${mealVO.meal_name}</h3>
															<p>${mealVO.meal_info}</p>
															<div class="row">
																<div class="col-lg-12">
																	<i class="fas fa-minus-square display-icon-minus"
																		field="quantity${k.index}"
																		id="display-icon-minus${k.index}"></i> <input
																		type="text" value=1 class="display-qty"
																		id="display-qty${k.index}" name="quantity${k.index}"
																		style="border-radius: 5px;"> <i
																		class="fas fa-plus-square display-icon-plus"
																		field="quantity${k.index}"
																		id="display-icon-plus${k.index}"></i>
																</div>
															</div>
															<div class="row">
																<i class="col-lg-2 fas fa-dollar-sign"
																	style="float: left; margin-top: 25px; display: inline-block; color: rgba(0, 0, 0, 0.5)"></i>
																<div class="col-lg-10 price" id="showprice${k.index}">${mealVO.price}</div>
																<input type="hidden" id="price${k.index}"
																	value="${mealVO.price}">
															</div>
														</figcaption>

														<input type="hidden" id="item_name${k.index}"
															name="item_name" value="${mealVO.meal_name}">
														<input type="hidden" id="item_quantity${k.index}"
															field="hiddenQty${k.index}" name="quantity" value="1">
														<input type="hidden" id="item_price${k.index}"
															name="price" value="${mealVO.price}">
														<input type="hidden" name="action" value="addToCart">
													</figure>
												</form>
											</div>

										</c:if>
									</c:forEach>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
		<footer class="footer-section main-footer ">
			<div class="copyright-option ">
				<div class="container ">
					<div class="row ">
						<div>
							<div class="co-text ">
								<p>
									Copyright &copy;
									<script>
										document
												.write(new Date().getFullYear());
									</script>
									All rights reserved | This template is made with <i
										class="fa fa-heart " aria-hidden="true "></i> by <a
										href="https://colorlib.com " target="_blank ">Colorlib</a>
									<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
								</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</footer>
	</div>

	<%
		Vector<CartItem> buylist = (Vector<CartItem>) session.getAttribute("cart");
	%>
	<div class="shopping-cart-box">
		<a class="close-display-box"> <i class="fas fa-window-close"></i>
		</a>

		<div class="display-wrapper">
			<h2 style="text-align: center;">
				<b>購物車</b>
			</h2>
			<div class="shopping-cart-detail">
				<div class="container">
					<div class="row" style="height: 300px;">
						<div class="col-12">
							<h4 style="text-align: center;">您目前還沒添加商品至購物車唷！</h4>
						</div>
					</div>
					<div class="row" style="height: 70px;">
						<div class="col-12">
							<button class="display-button display-close"
								style="margin: 10px auto;" type="submit">
								<h5 class="display-button-word" style="margin: auto;">關閉</h5>
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%
		if (buylist != null && buylist.size() > 0) {
	%>

	<div class="shopping-cart-box">
		<a class="close-display-box"> <i class="fas fa-window-close"></i>
		</a>

		<div class="display-wrapper">
			<h2 style="text-align: center;">
				<b>購物車</b>
			</h2>
			<div class="shopping-cart-detail">
				<div class="container">
					<div class="row" style="height: 300px;">
						<%
							for (int index = 0; index < buylist.size(); index++) {
									CartItem order = buylist.get(index);
						%>
						<div class="col-lg-3">
							<h4 class="cart-name" style="text-align: center;"><%=order.getItem_name()%></h4>
						</div>
						<div class="col-lg-3">
							<h4 style="text-align: center;"><%=order.getQuantity()%></h4>
						</div>
						<div class="col-lg-3">
							<h4 id="cart-price<%=index%>" style="text-align: center;"><%=order.getPrice()%></h4>
						</div>
						<div class="col-lg-3">
							<form method="post"
								action="${pageContext.request.contextPath}/AddToCartServlet">
								<input type="hidden" name="action" value="DELETE"> <input
									type="hidden" name="del" value="<%=index%>">
								<button type="submit">
									<i class="fas fa-trash-alt"
										style="margin-left: 35px; font-size: 21px"></i>
								</button>
							</form>
						</div>
						<%
							}
						%>
					</div>
					<div class="row" style="height: 70px;">
						<div class="col-lg-12">
						<form>
							<button class="display-button" style="margin: 10px auto;"
								type="submit">
								<h5 class="display-button-word">確定送出 $</h5>
								<h5 id="totalprice" style="margin-top: 10px;"></h5>
							</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%
		}
	%>


	<%@ include file="/frontend/files/commonJS.file"%>
	<script src="${pageContext.request.contextPath}/js/front/meal.js "></script>
</body>

</html>