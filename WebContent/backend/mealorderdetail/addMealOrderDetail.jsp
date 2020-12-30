<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mealorderdetail.model.*"%>
<%@ page import="java.util.List"%>

<%
	MealOrderDetailVO mealOrderDetailVO = (MealOrderDetailVO) request.getAttribute("mealOrderDetailVO");
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
<title>新增餐點訂單細項</title>
</head>

<body>
<jsp:useBean id="mealSvc" scope="page" class="com.meal.model.MealService" />
	<form method="post"
		action="${pageContext.request.contextPath}/MealOrderDetailServlet">
		<div class="insert-meal-type">
			<label for="meal_odno">
				<p>訂單編號</p> <input type="text" name="meal_odno" maxlength="10"
				placeholder="請依照格式輸入(MEALODXXXX)" required/>
			</label> 
			<label for="meal_no">
				<p>餐點編號</p> <input type="text" name="meal_no" maxlength="5"
				placeholder="請輸入種類名稱" required/>
			</label>
						
			<label for="price">
				<p>單價</p> <input type="text" name="price" placeholder="請輸入單價" required/>
			</label>
			
			<label for="qty">
				<p>數量</p> <input type="text" name="qty" placeholder="請輸入數量" required/>
			</label>			
		</div>

		<input type="hidden" name="action" value="insert_meal_orderdetail">
		<button type="submit" class="btn btn-success" style="background-color: pink;">確認送出</button>

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