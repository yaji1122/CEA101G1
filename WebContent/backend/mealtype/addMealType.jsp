<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mealtype.model.*"%>
<%@ page import="java.util.List"%>

<%
	MealTypeVO mealTypeVO = (MealTypeVO) request.getAttribute("mealTypeVO");
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
<title>新增餐點種類</title>
</head>

<body>
	<form method="post"
		action="${pageContext.request.contextPath}/MealTypeServlet">
		<div class="insert-meal-type">
			<label for="meal_type_no">
				<p>種類編號</p> <input type="text" value="TYP" name="meal_type_no" maxlength="5"
				placeholder="請依照格式輸入(TYPXX)" required/>
			</label> <label for="type_name">
				<p>種類名稱</p> <input type="text" name="type_name"
				placeholder="請輸入種類名稱" required/>
			</label>
		</div>

		<input type="hidden" name="action" value="insert_meal_type">
		<button type="submit" class="btn btn-success" style="background-color: #BEBEBE;">確認送出</button>

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