<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mealtype.model.*"%>
<%@ page import="com.meal.model.*"%>
<%@ page import="com.cart.model.*" %>
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
<%@ include file="/frontend/files/commonCSS.file" %>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/front/meal.css" type="text/css" />
</head>

<body>
	<div class="black"></div>
	<%@ include file="/frontend/files/login.file" %>
	<%@ include file="/frontend/files/loginbox.file" %>
	<%@ include file="/frontend/files/header.file" %>
	

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
									<div class="type-box ">
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
								<hr style="width: 831px;">
								<h2 class="title">${mealTypeVO.type_name}</h2>
								<div class="row ">
									<jsp:useBean id="mealSvc" scope="page"
										class="com.meal.model.MealService" />

									<c:forEach var="mealVO"
										items="${mealSvc.getActiveMeal(meal_status)}" varStatus="k">
										<c:if
											test="${(mealVO.meal_type_no == mealTypeVO.meal_type_no)}">
											
												<div class="col-lg-4 card-box">
												<form id="form${k.index}" method="post" action="${pageContext.request.contextPath}/AddToCartServlet">
													<figure class="snip1268" id="card">
														<div class="image">
															<img
																src="${pageContext.request.contextPath}/MealServlet?action=view_mealpic&meal_no=${mealVO.meal_no}"
																alt="sq-sample4" />
																 <a href="#" class="add-to-cart" 
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
														
														<input type="hidden" id="item_name${k.index}" name="item_name" value="${mealVO.meal_name}">
														<input type="hidden" id="item_quantity${k.index}" field="hiddenQty${k.index}" name="quantity" value="1">
														<input type="hidden" id="item_price${k.index}" name="price" value="${mealVO.price}">
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
	Vector<CartItem> buylist = (Vector<CartItem>)session.getAttribute("cart");
	if (buylist != null && buylist.size() > 0){
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
						for(int index = 0; index < buylist.size(); index++){
							CartItem order = buylist.get(index);
						%>
						<div class="col-3">
							<h4 style="text-align: center;"><%= order.getItem_name()%></h4>
						</div>
						<div class="col-3">
							<h4 style="text-align: center;"><%= order.getQuantity()%></h4>
						</div>
						<div class="col-3">
							<h4 style="text-align: center;"><%= order.getPrice()%></h4>
						</div>
						<div class="col-3">
							<button type="submit">
							<i class="fas fa-trash-alt" style="margin-left: 35px; font-size: 21px"></i>
							</button>
						</div>
						<% } %>
					</div>
					<div class="row" style="height: 70px;">
						<div class="col-12">
							<button class="display-button" style="margin: 10px auto;"
								type="submit">
								<i class="fas fa-cart-plus"
									style="margin-right: 10px; vertical-align: center;"></i>
								<h5 id="totalprice" class="display-button-word">確定送出 NT$</h5>
							</button>
						</div>
					</div>
				</div>
			</div>				
		</div>
	</div>
<% } %>


	<%@ include file="/frontend/files/commonJS.file" %>
	<script src="${pageContext.request.contextPath}/js/front/meal.js "></script>
</body>

</html>