<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mealtype.model.*"%>
<%@ page import="com.meal.model.*"%>
<%@ page import="com.cart.model.*"%>
<%@ page import="com.members.model.*"%>
<%@ page import="com.rooms.model.*"%>
<%@ page import="com.bookingorder.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.stream.Collectors"%>

<%
	MealTypeVO mealTypeVO = (MealTypeVO) request.getAttribute("mealTypeVO");
	MealVO mealVO = (MealVO) request.getAttribute("mealVO");
	RoomsVO roomsVO = (RoomsVO) request.getAttribute("rooms");
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

	<%
		String mb_id = member.getMb_id();
		// 	System.out.println(mb_id);
		BookingOrderService bkodSvc = new BookingOrderService();
		List<BookingOrderVO> bkodList = bkodSvc.getAllByMbId(mb_id);
		List<BookingOrderVO> newList = bkodList.stream().filter(e -> e.getBk_status().equals(BKSTATUS.CHECKED))
				.collect(Collectors.toList());
		String bk_no = "";
		for (BookingOrderVO list : newList) {
			bk_no = list.getBk_no();
		}
		// 				System.out.println(bk_no);

		RoomsService roomsSvc = new RoomsService();
		List<RoomsVO> roomList = roomsSvc.getAllByMbId(mb_id);
		List<String> roomnoList = new ArrayList<>();
		for (RoomsVO list : roomList) {
			roomnoList.add(list.getRm_no());
		}
		// 				roomnoList.forEach(System.out::println);

		session.setAttribute("roomnoList", roomnoList);
	%>

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

	<%
		Vector<CartItem> buylist = (Vector<CartItem>) session.getAttribute("cart");
		session.setAttribute("buylist", buylist);
	%>

	<!-- header menu -->
	<header class="header-section">
		<div class="menu-item">
			<div class="nav-menu">
				<nav class="mainmenu">
					<ul class="mainmenu-row">
						<div class="logobox">
							<a href="${pageContext.request.contextPath}/frontend/index.jsp"><img
								src="${pageContext.request.contextPath}/img/logo.png" /> </a>
						</div>
						<li class="nav-list"><a href="#" class="nav-evnet">最新消息</a></li>

						<li class="nav-list"><a class="nav-event"
							href="${pageContext.request.contextPath}/frontend/rooms/rooms.jsp">客房介紹</a>
						</li>

						<li class="nav-list"><a
							href="<%=request.getContextPath()%>/frontend/shop/shopPage.jsp"
							class="nav-evnet">戴蒙商城</a></li>

						<li class="nav-list"><a
							href="<%=request.getContextPath()%>/frontend/guestSection.jsp"
							class="nav-event" class="nav-event"><i
								class="fas fa-glass-cheers"></i>住客專區</a>
							<ul class="dropdown">
								<li><a
									href="<%=request.getContextPath()%>/frontend/activity/activity.jsp">活動報名</a></li>
								<li><a
									href="<%=request.getContextPath()%>/frontend/services/services.jsp">預約服務</a></li>
								<li><a
									href="<%=request.getContextPath()%>/frontend/meal/meal.jsp">訂購餐點</a></li>
							</ul></li>

						<li><c:choose>
								<c:when test="${buylist.isEmpty()}">
									<a class="nav-event"> <i
										class="fas fa-shopping-cart shopping-cart shopping-cart-icon"
										style="font-size: 20px"> </i>
									</a>
								</c:when>

								<c:when test="${buylist.size() > 0}">
									<a class="nav-event"> <i
										class="fas fa-shopping-cart shopping-cart shopping-cart-icon"
										style="font-size: 20px">
											<div class="nav-counter nav-counter-blue">
												<h6 style="letter-spacing: normal;">
													<%=buylist.size()%>
												</h6>
											</div>
									</i>
									</a>
								</c:when>
								<c:otherwise>
									<a class="nav-event"> <i
										class="fas fa-shopping-cart shopping-cart shopping-cart-icon"
										style="font-size: 20px"> </i>
									</a>
								</c:otherwise>
							</c:choose></li>

						<li class="nav-list"><a class="nav-event"> <i
								class="far fa-gem"></i>
						</a>
							<ul class="dropdown">
								<li><a
									href="${pageContext.request.contextPath}/frontend/members/memberInfo.jsp">個人檔案</a></li>
								<li><a
									href="${pageContext.request.contextPath}/frontend/members/memberBooking.jsp">我的假期</a></li>
								<li><a
									href="${pageContext.request.contextPath}/frontend/members/memberHistory.jsp">歷史訂單</a></li>
								<li><a
									href="${pageContext.request.contextPath}/LoginHandler?mb_email=${member.mb_email}&action=member-logout&location=${pageContext.request.requestURL}">登出</a></li>
							</ul></li>
					</ul>
				</nav>
			</div>
		</div>
	</header>

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
										src="${pageContext.request.contextPath}/img/meal/hamburger.jpg"
										alt="First slide">
								</div>
								<div class="carousel-item ">
									<img class="d-block w-100 img-box"
										src="${pageContext.request.contextPath}/img/meal/pizza.jpg "
										alt="Second slide ">
								</div>
								<div class="carousel-item ">
									<img class="d-block w-100 img-box"
										src="${pageContext.request.contextPath}/img/meal/friedchicken.jpg "
										alt="third slide ">
								</div>
								<div class="carousel-item ">
									<img class="d-block w-100 img-box"
										src="${pageContext.request.contextPath}/img/meal/pasta.jpg "
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

		<div class="wrapper" style="background-color: rgb(44, 49, 64);">
			<div class="container ">
				<div class="row ">
					<div class="col-lg-3 type-wrapper nav-fixed-type">
						<div id="list" class="list-group type-list">
							<jsp:useBean id="mealTypeSvc" scope="page"
								class="com.mealtype.model.MealTypeService" />
							<c:forEach var="mealTypeVO" items="${mealTypeSvc.all}"
								varStatus="i">
								<a class="list-group-item list-group-item-action"
									style="background-color: rgb(51, 56, 71);"
									href="#list-item-${i.index}">
									<div class="type-box">
										<h5 class="type-title">
											<b>${mealTypeVO.type_name}</b>
										</h5>
									</div>
								</a>
							</c:forEach>
						</div>
					</div>

					<div class=" col-lg-9 card-wrapper ">
						<div data-spy="scroll " data-target="#list" data-offset="0"
							class="scrollspy-example ">
							<c:forEach var="mealTypeVO" items="${mealTypeSvc.all}"
								varStatus="j">

								<h2 class="title" id="list-item-${j.index}"
									style="visibility: hidden;">${mealTypeVO.type_name}</h2>
								<hr style="width: 800px;">
								<div class="row">
									<div class="col-lg-12">
										<h2 class="title" style="user-select: none;">
											<b>${mealTypeVO.type_name}</b>
										</h2>
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
												<%-- 												<form id="form${k.index}" method="post" --%>
												<%-- 													action="${pageContext.request.contextPath}/AddToCartServlet"> --%>
												<figure class="snip1268" id="card">
													<div class="image">
														<img
															src="${pageContext.request.contextPath}/MealServlet?action=view_mealpic&meal_no=${mealVO.meal_no}"
															alt="sq-sample4" /> <a class="add-to-cart"<%-- 																onclick="document.getElementById('form' + ${k.index} + '').submit();" --%>
																>加入購物車</a>
													</div>
													<figcaption>
														<div class="container">
															<div class="row">
																<div class="col-lg-12">
																	<h4 id="meal_name${k.index}"
																		style="color: white; user-select: none; float: left;">
																		<b>${mealVO.meal_name}</b>
																	</h4>
																</div>
															</div>
															<div class="row">
																<div class="col-lg-12">
																	<p style="color: white; user-select: none;">${mealVO.meal_info}</p>
																</div>
															</div>
															<div class="row" style="display: none;">
																<div class="col-lg-12">
																	<i class="fas fa-minus-square display-icon-minus"
																		field="quantity${k.index}"
																		id="display-icon-minus${k.index}"></i> <input
																		type="text" value=1 class="display-qty"
																		id="display-qty${k.index}" name="quantity${k.index}"
																		style="border-radius: 5px;" disabled="disabled"><i
																		class="fas fa-plus-square display-icon-plus"
																		field="quantity${k.index}"
																		id="display-icon-plus${k.index}"></i>
																</div>
															</div>
															<div class="row">
																<div class="col-lg-4 price">
																	<h5 style="color: white; display: inline-block">
																		<b>USD$</b>
																	</h5>
																</div>
																<div class="col-lg-8 price" id="showprice${k.index}">
																	<h5
																		style="color: white; user-select: none; display: inline-block;">
																		<b>${mealVO.price}</b>
																	</h5>
																</div>
																<input type="hidden" id="price${k.index}"
																	value="${mealVO.price}">
															</div>
														</div>
													</figcaption>

													<input type="hidden" id="item_no${k.index}" class="item_no"
														value="${mealVO.meal_no}">
													<input type="hidden" id="item_name${k.index}"
														class="item_name" value="${mealVO.meal_name}">
													<input type="hidden" id="item_quantity${k.index}"
														class="item_quantity" field="hiddenQty${k.index}"
														value="1">
													<input type="hidden" id="item_price${k.index}"
														class="item_price" value="${mealVO.price}">
													<input type="hidden" class="addToCart" value="addToCart">
												</figure>
												<!-- 												</form> -->
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
	</div>

	<div class="shopping-cart-box">
		<a class="close-display-box"> <i class="fas fa-window-close"
			style="color: white;"></i>
		</a>

		<div class="display-wrapper">
			<h2 style="text-align: center; color: white; margin-top: 20px;">
				<b>購物車</b>
			</h2>
			<hr style="background-color: white;">
			<div class="shopping-cart-detail">
				<div class="container">
					<div class="row" style="height: 330px;">
						<div class="col-lg-12">
							<h4 style="text-align: center; color: white; margin-top: 140px;">您目前還沒添加商品至購物車唷！</h4>
						</div>
					</div>
					<div class="row" style="height: 70px;">
						<div class="col-lg-12">
							<button class="display-button display-close"
								style="margin: 10px auto; background-color: rgb(44, 49, 64);"
								type="submit">
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
		<a class="close-display-box"> <i class="fas fa-window-close"
			style="color: white;"></i>
		</a>

		<div class="display-wrapper">
			<h2
				style="text-align: center; color: white; margin-top: 20px; display: inline-block; margin-left: 45%;">
				<b>購物車</b>
			</h2>
				<button class="remove-button" style="margin: 10px auto; margin-left: 17%;"
					type="submit">
					<h6 class="display-button-word">清空購物車</h6>
				</button>
				<input type="hidden" class="resetCart" value="RESET">
			<hr style="background-color: white;">
			<div class="shopping-cart-detail">
				<div class="container">

					<div class="row scrollbar"
						style="height: 320px; overflow-y: scroll;">
						<%
							for (int index = 0; index < buylist.size(); index++) {
									CartItem order = buylist.get(index);
						%>
						<div class="container" style="height: 80px;">
							<div class="row" style="height: 80px;">
								<div class="col-lg-2"
									style="width: 72px; height: 72px; padding: 0px 25px;">
									<img style="width: 100%; height: 100%; border-radius: 5px;"
										src="${pageContext.request.contextPath}/MealServlet?action=view_mealpic&meal_no=<%= order.getItem_no() %>">
								</div>
								<div class="col-lg-3" style="margin-top: 20px;">
									<h5 class="cart-name" style="text-align: center; color: white;"><%=order.getItem_name()%></h5>
									<input type="hidden" name="itemno" 
										value="<%=order.getItem_no()%>">
								</div>
								<div class="col-lg-3" style="margin-top: 20px;">
									<i class="fas fa-minus-square cart-icon-minus"
										field="cart-quantity<%=index%>" id="cart-icon-minus<%=index%>"></i>
									<input type="text" value=<%=order.getQuantity()%>
										class="cart-qty" id="cart-qty<%=index%>"
										name="cart-quantity<%=index%>"
										style="margin-left: 7.5%; border-radius: 5px; background-color: white;"
										disabled="disabled" maxlength="2"> <i
										class="fas fa-plus-square cart-icon-plus"
										field="cart-quantity<%=index%>" id="cart-icon-plus<%=index%>"></i>

								</div>
								<div class="col-lg-2" style="margin-top: 20px;">
									
									<b><h5 style="color: white; display:inline-block;">$</h5><h5 id="show-cartprice<%=index%>"
											style="text-align: center; color: white; display: inline-block;"><%=order.getPrice()%></h5></b>
<%-- 									<input type="text" name="price" value="<%=order.getPrice()%>"> --%>
									<input type="hidden" id="cart-price<%=index%>"
										value="<%=order.getPrice()%>">
								</div>
								<div class="col-lg-2" style="margin-top: 20px;">
									<!-- 									<form method="post" -->
									<%-- 										action="${pageContext.request.contextPath}/AddToCartServlet"> --%>
									<input type="hidden" value="DELETE" class="delToCart">
									<input class="delIndex" type="hidden" value="<%=index%>">
									<button type="submit" class="del-to-cart">
										<i class="fas fa-trash-alt"
											style="margin-left: 10px; font-size: 21px; color: rgb(238, 53, 76);"></i>
									</button>
									<!-- 									</form> -->
								</div>
							</div>
						</div>
						<%
							}
						%>
					</div>

						<div class="row" style="height: 70px;">
							<div class="col-lg-3">
								<h5
									style="margin-top: 25px; margin-left: 5%; display: inline-block; color: white;">
									<b>房號</b>
								</h5>

								<label> <select id="rm_no" name="rm_no"
									class="custom-select custom-select-sm"
									style="font-size: 20px; margin-top: -8px; margin-left: 30%;">
										<c:forEach var="roomsVO" items="${roomnoList}"
											varStatus="rmno">
											<option value="${roomsVO}">${roomsVO}</option>
										</c:forEach>
								</select>
								</label>

							</div>
							<div class="col-lg-9">
								<button class="display-button" style="margin: 10px auto;"
									type="submit">
									<h5 class="display-button-word">確認送出$</h5>
									<h5 id="totalprice" style="margin-top: 10px; color: white;"></h5>
								</button>
								<input type="hidden" class="insertMealOrder" value="insert_meal_order">
								<input type="hidden" class="amount" id="cart-to-servlet" value=1>
								<%
									for (int index = 0; index < buylist.size(); index++) {
											CartItem order = buylist.get(index);
								%>
								<input type="hidden" id="hidden-cartqty<%=index%>"
									field="hidden-cartqty<%=index%>" name="qty" class="qty"
									value="<%=order.getQuantity()%>"
									style="background-color: white;">
								<input type="hidden" id="itemNo<%=index %>" value="<%= order.getItem_no()%>">
								<%
									}
								%>
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
	<script>
	$(document).ready(function(){	
		let no, name, quantity, price, addToCart, src, delIndex, delToCart, resetCart, rmno, amount, qty, insertMealOrder;
		
		$(".add-to-cart").click(function(){			
			no = $(this).parent('.image').siblings('.item_no').val();
			name = $(this).parent('.image').siblings('.item_name').val();
			quantity = $(this).parent('.image').siblings('.item_quantity').val();
			price = $(this).parent('.image').siblings('.item_price').val();		
			addToCart = $(this).parent('.image').siblings('.addToCart').val();	
			src = $(this).siblings('img').attr('src');
			console.log(no+name+quantity+price+addToCart+src);
			$.ajax({
				type: "POST",
				url: "<%=request.getContextPath()%>/AddToCartServlet",	
				data: {					
					item_no: no,
					item_name: name,
					quantity: quantity,
					price: price,
					action: addToCart,
				},
				success: function(){
					Swal.fire({
	                    position: "center",
	                    icon: "success",
	                    title: "已新增至購物車",
	                    html: "Redirecting...",
	                    showConfirmButton: false,
	                    timer: 1000
					}).then(function(){
						location.href="<%=request.getContextPath()%>/frontend/meal/meal.jsp";
					});                 
				}
			})
		})
		
		$('.del-to-cart').click(function(){
			delIndex = $(this).siblings('.delIndex').val();
			delToCart = $(this).siblings('.delToCart').val();
			
			Swal.fire({
	            position: "center",
	            icon: "warning",
	            title: "確定刪除？",
	            showCancelButton: true,
	            confirmButtonText: "確定",
	            cancelButtonText: "取消"
			}).then(function(result){
				 if (result.value) {
					$.ajax({
						type: "POST",
						url: "<%=request.getContextPath()%>/AddToCartServlet",	
						data: {					
							del: delIndex,
							action: delToCart,
						},
						success: function(){
	                        Swal.fire({
	                        	position: "center",
	    	                    icon: "success",
	    	                    title: "已從購物車移除",
	    	                    html: "Redirecting...",
	    	                    showConfirmButton: false,
	    	                    timer: 1000
	                        }).then(function(){
	                        	location.href="<%=request.getContextPath()%>/frontend/meal/meal.jsp";
	    					});  
	                     }				 
					});  
				}
				else if (result.dismiss === "cancel"){}	
			})
		})
		
		$('.remove-button').click(function(){
			resetCart = $(this).siblings('.resetCart').val();
			console.log(resetCart);
			Swal.fire({
	            position: "center",
	            icon: "warning",
	            title: "確定清空？",
	            showCancelButton: true,
	            confirmButtonText: "確定",
	            cancelButtonText: "取消"
			}).then(function(result){
				 if (result.value) {
					$.ajax({
						type: "POST",
						url: "<%=request.getContextPath()%>/AddToCartServlet",	
						data: {					
							action: resetCart,
						},
						success: function(){
	                        Swal.fire({
	                        	position: "center",
	    	                    icon: "success",
	    	                    title: "已清空購物車",
	    	                    html: "Redirecting...",
	    	                    showConfirmButton: false,
	    	                    timer: 1000
	                        }).then(function(){
	                        	location.href="<%=request.getContextPath()%>/frontend/meal/meal.jsp";
	    					});  
	                     }				 
					});  
				}
				else if (result.dismiss === "cancel"){}	
			})
		})
		
		$('.display-button').click(function(){
			rmno = $('#rm_no').val();
			amount = $('.amount').val();
			insertMealOrder = $('.insertMealOrder').val();
			console.log(rmno);
			console.log(amount);
			console.log(insertMealOrder);
			
			Swal.fire({
	            position: "center",
	            icon: "question",
	            title: "確定送出？",
	            showCancelButton: true,
	            confirmButtonText: "確定",
	            cancelButtonText: "取消"
			}).then(function(result){
				 if (result.value) {
					$.ajax({
						type: "POST",
						url: "<%=request.getContextPath()%>/MealOrderServlet",	
						data: {					
							rm_no: rmno,
							amount: amount,
							action: insertMealOrder,
						},
						success: function(){
	                        Swal.fire({
	                        	position: "center",
	    	                    icon: "success",
	    	                    title: "訂單已送出",
	    	                    html: "Redirecting...",
	    	                    showConfirmButton: false,
	    	                    timer: 1000
	                        }).then(function(){
	                        	location.href="<%=request.getContextPath()%>/frontend/meal/meal.jsp";
	    					});  
	                     }				 
					});  
				}
				else if (result.dismiss === "cancel"){}	
			})
		})
		
		
		
		$(".cart-qty").each(function(index) {
			$("#cart-icon-plus" + index + "").click(function(e) {
				e.preventDefault();
				fieldName = $(this).attr('field');
				hiddenQty = $("#hidden-cartqty" + index + "").attr('field');
				var currentVal = parseInt($('input[name=' + fieldName + ']').val());
				var hiddenVal = parseInt($('input[field=' + hiddenQty + ']').val());
				price = parseInt($("#cart-price" + index + "").val());
				showprice = parseInt($("#show-cartprice" + index + "").html());
				if (!isNaN(currentVal) && currentVal < 99) {
					if(currentVal != 99){
						$('input[name=' + fieldName + ']').val(currentVal + 1);
						$('input[field=' + hiddenQty + ']').val(hiddenVal + 1);
						singleprice = price / currentVal;
						$("#show-cartprice" + index + "").html(showprice + singleprice);
						$("#cart-price" + index + "").val(showprice + singleprice);
					}
				}
				
				var totalprice = 0;
				$('.cart-name').each(function(index){
					var listprice = parseInt($("#cart-price" + index + "").val());
					totalprice = totalprice + listprice;
					$("#totalprice").html(totalprice);
					$("#cart-to-servlet").val(totalprice);
				})					
				
				let itemNo = $('#itemNo' + index + "").val();
				let newVal = $('input[name=' + fieldName + ']').val();
				let itemPrice = $("#cart-price" + index + "").val();
				let amount = $("#cart-to-servlet").val();

				$.ajax({
					type: "POST",
					url: "<%=request.getContextPath()%>/AddToCartServlet",	
					data: {					
						action: "changeQuantity",
						newVal: newVal,
						itemNo: itemNo,
						itemPrice: itemPrice,
					}
				})
			});
		})

		
		$(".cart-qty").each(function(index) {
	$("#cart-icon-minus" + index + "").click(function(e) {
		e.preventDefault();
		fieldName = $(this).attr('field');
		hiddenQty = $("#hidden-cartqty" + index + "").attr('field');
		var currentVal = parseInt($('input[name=' + fieldName + ']').val());
		var hiddenVal = parseInt($('input[field=' + hiddenQty + ']').val());
		price = parseInt($("#cart-price" + index + "").val());
		showprice = parseInt($("#show-cartprice" + index + "").html());
		if (!isNaN(currentVal) && currentVal > 1) {
			$('input[name=' + fieldName + ']').val(currentVal - 1);
			$('input[field=' + hiddenQty + ']').val(hiddenVal - 1);
		} else {
			$('input[name=' + fieldName + ']').val(1);
			$('input[field=' + hiddenQty + ']').val(1);
		}
		singleprice = price / currentVal;
		if ($("#cart-qty" + index + "").val() > 1) {
			$("#show-cartprice" + index + "").html(showprice - singleprice);
			$("#cart-price" + index + "").val(showprice - singleprice);
		} else if ($("#cart-qty" + index + "").val() == 1) {
			$("#show-cartprice" + index + "").html(singleprice);
			$("#cart-price" + index + "").val(singleprice);
		}
		

		var totalprice = 0;
		$('.cart-name').each(function(index){
			var listprice = parseInt($("#cart-price" + index + "").val());
			totalprice = totalprice + listprice;
			$("#totalprice").html(totalprice);
			$("#cart-to-servlet").val(totalprice);
		})
		
		
		let itemNo = $('#itemNo' + index + "").val();
		let newVal = $('input[name=' + fieldName + ']').val();
		let itemPrice = $("#cart-price" + index + "").val();
		
		$.ajax({
			type: "POST",
			url: "<%=request.getContextPath()%>/AddToCartServlet",	
			data: {					
				action: "changeQuantity",
				newVal: newVal,
				itemNo: itemNo,
				itemPrice: itemPrice,
			},
		})
		});

		})
		var totalprice = 0;
		$('.cart-name').each(function(index){
			var listprice = parseInt($("#cart-price" + index + "").val());
			totalprice = totalprice + listprice;
			$("#totalprice").html(totalprice);
			$("#cart-to-servlet").val(totalprice);
		})
	})
	</script>
	<script src="${pageContext.request.contextPath}/js/front/meal.js "></script>
</body>

</html>