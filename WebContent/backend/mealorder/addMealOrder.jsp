<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mealorder.model.*"%>
<%@ page import="java.util.List"%>

<%
	MealOrderVO mealOrderVO = (MealOrderVO) request.getAttribute("mealOrderVO");
%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/back/backend-meal.css" />
<title>新增餐點訂單</title>
</head>

<body>
	<form method="post"
		action="${pageContext.request.contextPath}/MealOrderServlet">
		<div class="insert-meal-type">
			<label for="bk_no">
				<p>會員編號</p> <input type="text" name="bk_no" maxlength="10"
				placeholder="請依照格式輸入(MEMXXXXXXX)" required/>
			</label> 
			<label for="rm_no">
				<p>客房編號</p> <input type="text" name="rm_no" maxlength="3"
				placeholder="請依照格式輸入(XXX)" required/>
			</label>
			<label for="total_price">
				<p>訂單總額</p> <input type="text" name="total_price" required/>
			</label>			 
		</div>

		<input type="hidden" name="action" value="insert_meal_order">
		<button type="submit" class="btn btn-success" style="background-color:pink;">確認送出</button>

	</form>
<%-- 	<c:if test="${not empty errorMsgs}"> --%>
<!-- 		<font style="color: red">請修正以下錯誤:</font> -->
<!-- 		<ul> -->
<%-- 			<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 				<li style="color: red">${message}</li> --%>
<%-- 			</c:forEach> --%>
<!-- 		</ul> -->
<%-- 	</c:if> --%>
</body>

</html>