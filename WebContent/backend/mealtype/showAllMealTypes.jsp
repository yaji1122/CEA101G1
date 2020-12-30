<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mealtype.model.*"%>

<%
	MealTypeVO mealTypeVO = (MealTypeVO) request.getAttribute("mealTypeVO");
	MealTypeService mealTypeSvc = new MealTypeService();
	List<MealTypeVO> mealTypeList = mealTypeSvc.getAll();
	pageContext.setAttribute("mealTypeList", mealTypeList);
%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.31.3/css/theme.metro-dark.min.css" />
<link href='${pageContext.request.contextPath}/css/datatables.min.css'
	rel='stylesheet' />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/back/backend-meal.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.tablesorter.min.js"></script>
<script src='${pageContext.request.contextPath}/js/datatables.min.js'></script>
</head>

<body>
	<h2>所有餐點種類 - All Meal Types</h2>
	<hr>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<c:choose>
		<c:when test="${mealTypeList.size() > 0}">
			<table id="myTable" class="table tablesorter">
				<thead>
					<tr>
						<th scope="col">餐點種類編號</th>
						<th scope="col">種類名稱</th>
						<th scope="col">修改</th>
						<th scope="col">刪除</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="mealTypeVO" items="${mealTypeList}">
						<tr>
							<td style="font-size: 16px; text-align: center; padding-top: 4%;">${mealTypeVO.meal_type_no}</td>
							<td style="font-size: 16px; text-align: center; padding-top: 4%;">${mealTypeVO.type_name}</td>
							<td><input type="hidden" name="meal_type_no"
								value="${mealTypeVO.meal_type_no}">
								<button type="submit" class="update btn btn-info">修改</button></td>
							<td>

								<form method="post"
									action="${pageContext.request.contextPath}/MealTypeServlet">
									<input type="hidden" name="delete-mealtype-no"
										value="${mealTypeVO.meal_type_no}"> <input
										type="hidden" name="action" value="delete_meal_type">
									<button type="submit" class="btn btn-danger">刪除</button>
								</form>
							</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:when>
		<c:otherwise>
			<h3>查無資料</h3>
		</c:otherwise>
	</c:choose>

	<form class="update-display" method="post"
		action="${pageContext.request.contextPath}/MealTypeServlet">
		<div class="update-display-div">
			<div class="close-icon">
				<i class="fas fa-times icon"></i>
			</div>
			<h3>
				種類編號：<b id="update-mealtype-no"></b>
			</h3>

			<label for="update-typename"><p>
					<b>種類名稱</b>
				</p> <input type="text" id="update-typename" name="update-typename"
				placeholder="請輸入種類名稱" required /> </label> <input type="hidden"
				name="action" value="update_meal_type"> <input type="hidden"
				name="update-mealtype-no" id="update-mealtype-to-servlet">
			<button type="submit" class="btn btn-success"
				style="width: 100px; margin: 50px auto; background-color: pink;">更新資料</button>
		</div>
	</form>

	<script>
		$(".update").click(function() {
			$(".update-display").addClass("display-show");
			$(".black").css("display", "block");
			$(".black").click(function() {
				$(".black").css("display", "none");
			})
			let tr = $(this).parents("tr");
			let children = tr.children();
			$("#update-mealtype-no").text(children.eq(0).text());
			$("#update-mealtype-to-servlet").val(children.eq(0).text());
		})

		$(".icon").click(function() {
			let display = $(this).parents(".display-show");
			display.removeClass("display-show");
			$(".black").css("display", "none");
		})

		$("#myTable").tablesorter({
			theme : "metro-dark",
			widgets : [ 'zebra' ]
		});

		$(function() {
			$('#myTable').DataTable();
		});
	</script>

</body>

</html>