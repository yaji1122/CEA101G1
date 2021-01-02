<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.meal.model.*"%>

<%
	MealVO mealVO = (MealVO) request.getAttribute("mealVO");
	MealService mealSvc = new MealService();
	List<MealVO> mealList = mealSvc.getAll();
	pageContext.setAttribute("mealList", mealList);
%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/datatables.min.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.31.3/css/theme.metro-dark.min.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/back/backend-meal.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.tablesorter.min.js"></script>
<script src="${pageContext.request.contextPath}/js/datatables.min.js"></script>
<title>showAllMeals</title>
</head>

<body>
	<h2>所有餐點 - All Meals</h2>
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
		<c:when test="${mealList.size() > 0}">
			<form method="post"
				action="${pageContext.request.contextPath}/MealServlet">
				<input type="hidden" name="meal_status"
					value="${mealVO.meal_status}"> <input type="hidden"
					name=action value="update-allmealstatus">
				<button type="submit" class="btn btn-dark"
					style="float: right; margin-bottom: 13px;">一鍵上架</button>
			</form>
			<table id="myTable" class="table tablesorter">
				<thead>
					<tr>
						<th scope="col">種類編號</th>
						<th scope="col">餐點編號</th>
						<th scope="col">餐點名稱</th>
						<th scope="col">單價</th>
						<th scope="col">餐點介紹</th>
						<th scope="col">預計製作時間</th>
						<th scope="col">餐點狀態</th>
						<th scope="col">餐點圖片</th>
						<th scope="col">修改</th>
						<th scope="col">刪除</th>
					</tr>
				</thead>
				<tbody>
					<%-- 			<%@ include file="page1.file"%> --%>
					<c:forEach var="mealVO" items="${mealList}">
						<tr>
							<td
								style="font-size: 16px; text-align: center; padding-top: 1.5%;">${mealVO.meal_type_no}</td>
							<td
								style="font-size: 16px; text-align: center; padding-top: 1.5%;">${mealVO.meal_no}</td>
							<td
								style="font-size: 16px; text-align: center; padding-top: 1.5%;">${mealVO.meal_name}</td>
							<td
								style="font-size: 16px; text-align: center; padding-top: 1.5%;">${mealVO.price}</td>
							<td
								style="font-size: 16px; text-align: center; padding-top: 1.5%;">${mealVO.meal_info}</td>
							<td
								style="font-size: 16px; text-align: center; padding-top: 1.5%;">${mealVO.making_time}</td>
							<td><c:choose>
									<c:when test="${mealVO.meal_status.equals('0')}">
										<form method="post"
											action="${pageContext.request.contextPath}/MealServlet">
											<input type="hidden" name="off" value="${mealVO.meal_no}">
											<input type="hidden" name="action" value="off">
											<button id="offbutton" type="submit"
												class="btn btn-secondary">下架</button>
										</form>

									</c:when>
									<c:when test="${mealVO.meal_status.equals('1')}">
										<form method="post"
											action="${pageContext.request.contextPath}/MealServlet">
											<input type="hidden" name="on" value="${mealVO.meal_no}">
											<input type="hidden" name="action" value="on">
											<button type="submit" class="btn btn-secondary">上架</button>
										</form>
									</c:when>
								</c:choose></td>
							<td>
								<button class="showpic btn btn-info" name="meal_pic"
									value="${pageContext.request.contextPath}/MealServlet?action=view_mealpic&meal_no=${mealVO.meal_no}">
									查看照片</button>
							</td>
							<td><input type="hidden" name="meal_no"
								value="${mealVO.meal_no}">
								<button type="submit" class="update btn btn-info">修改</button></td>
							<td>

								<form method="post"
									action="${pageContext.request.contextPath}/MealServlet">
									<input type="hidden" name="delete-meal-no"
										value="${mealVO.meal_no}"> <input type="hidden"
										name="action" value="delete_meal">
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
	<%-- 	<%@ include file="page2.file"%> --%>

	<form class="update-display update-display-div" method="post"
		action="${pageContext.request.contextPath}/MealServlet"
		enctype="multipart/form-data">

		<div class="close-icon">
			<i class="fas fa-times icon" style="margin-top: 5%;"></i>
		</div>
		<h3>
			餐點編號：<b id="update-meal-no"></b>
		</h3>

		<label for="update-mealtype-no"><p>
				<b>種類編號</b>
			</p> <input type="text" id="update-mealtype-no" name="update-mealtype-no"
			placeholder="請輸入種類編號" maxlength="5" required /> </label> <label
			for="update-mealname"><p>
				<b>餐點名稱</b>
			</p> <input type="text" id="update-mealname" name="update-mealname"
			placeholder="請輸入餐點名稱" required /> </label> <label for="update-price"><p>
				<b>單價</b>
			</p> <input type="text" id="update-price" name="update-price"
			placeholder="請輸入單價" required /> </label> <label for="update-mealinfo"><p>
				<b>餐點介紹</b>
			</p> <textarea name="update-mealinfo" id="update-mealinfo"
				maxlength="500" placeholder="最多500字"></textarea> </label> <label
			for="update-makingtime"><p>
				<b>預計製作時間</b>
			</p> <select class="custom-select custom-select-sm"
			name="update-makingtime" id="update-makingtime" required>
				<option value="0" selected>0</option>
				<option value="5 ">5</option>
				<option value="10 ">10</option>
				<option value="15">15</option>
		</select> </label> <label for="update-mealstatus"><p>
				<b>餐點狀態</b>
			</p> <select class="custom-select custom-select-sm"
			name="update-mealstatus" id="update-mealstatus" required>
				<option value="0" selected>下架</option>
				<option value="1">上架</option>
		</select> </label> <label for="update-mealpic"><p>
				<b>上傳餐點照片</b>
			</p>
			<div class="pic-upload" name="pic-upload" for="update-mealpic">
				<h6>
					<i class="icon fas fa-cloud-upload-alt"></i>上傳照片
				</h6>
				<input type="file" name="update-mealpic" accept="image/*" />
			</div> </label>
		<div>
			<input type="hidden" name="action" value="update_meal"> <input
				type="hidden" name="update-meal-no" id="update-meal-to-servlet">
			<button type="submit" class="btn btn-light"
				style="width: 100px; margin: 50px auto; background-color: pink;">更新資料</button>
		</div>

	</form>

	<div class="pic-display">
		<div class="pic-display-div">
			<div class="close-icon">
				<i class="fas fa-times icon"></i>
			</div>
			<img id="showmeal">
		</div>
	</div>

	<script>
		$(".update").click(function() {
			$(".update-display").addClass("display-show");
			$(".black").css("display", "block");
			$(".black").click(function() {
				$(".update-display").removeClass("display-show");
				$(".black").css("display", "none");
			})
			let tr = $(this).parents("tr");
			let children = tr.children();
			$("#update-meal-no").text(children.eq(1).text());
			$("#update-meal-to-servlet").val(children.eq(1).text());
			$("#update-mealtype-no").val(children.eq(0).text());
			$("#update-mealname").val(children.eq(2).text());
			$("#update-price").val(children.eq(3).text());
			$("#update-mealinfo").val(children.eq(4).text());
			$("#update-makingtime").val(children.eq(5).text().change());
			$("#update-mealstatus").val(children.eq(6).text()).change();

		})

		$(".showpic").click(function() {
			$(".black").css("display", "block");
			$(".pic-display").addClass("display-show");
			let src = $(this).val();
			$("#showmeal").attr("src", src);
			$(".black").click(function() {
				$(".pic-display").removeClass("display-show");
				$(".black").css("display", "none");
			})
		})

		$(".icon").click(function() {
			let display = $(this).parents(".display-show");
			display.removeClass("display-show");
			$("#showmeal").attr("src", "");
			$(".black").css("display", "none");
		})

		$("#myTable").tablesorter({
			theme : "metro-dark",
			widgets : [ 'zebra' ]
		});

		$(document).ready(function() {
			$('#myTable').DataTable();
		});
	</script>

</body>

</html>