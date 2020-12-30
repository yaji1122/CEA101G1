<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.meal.model.*"%>
<%@ page import="com.mealtype.model.*"%>


<%
	MealTypeVO mealTypeVO = (MealTypeVO) request.getAttribute("mealTypeVO");
	MealVO mealVO = (MealVO) request.getAttribute("mealVO");
%>


<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>

<c:forEach var="mealVO" items="${mealSvc.all}">
		<div class="display-box">
			<a class="close-display-box"> <i class="fas fa-window-close"></i>
			</a>
			<div class="display-wrapper">
				<div class="display-img-word">
					<div class="container">
						<div class="row">
							<div class="col-lg-6 display-img-box">
								<img
									src="${pageContext.request.contextPath}/MealServlet?action=view_mealpic&meal_no=${mealVO.meal_no}"
									id="display-img-box" style="width: 100%; height: 100%;">
							</div>
							<div class="col-lg-6 display-word-box">
								<h2 class="display-word-title" id="display-word-title">${mealVO.meal_name}</h2>
								<h4 class="display-word-info" id="display-word-info">${mealVO.meal_info}</h4>
								<h4 class="display-word-price" id="display-word-price">${mealVO.price}</h4>
							</div>
						</div>
						<div class="row" style="height: 70px;">
							<div class="col-lg-6">
								<i class="fas fa-minus-square display-icon-minus"></i> <input
									type="text" value=1 class="display-qty"
									style="margin: 6% 3%; border-radius: 5px;"
									oninput="if(value<1)value=1"> <i
									class="fas fa-plus-square display-icon-plus"></i>
							</div>
							<div class="col-lg-6">
								<button class="display-button" type="submit">
									<i class="fas fa-cart-plus" style="margin-right: 10px;"></i>
									<h5 class="display-button-word">加入購物車</h5>
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
</c:forEach>

</body>
</html>