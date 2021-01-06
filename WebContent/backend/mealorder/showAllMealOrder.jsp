<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mealorder.model.*"%>
<%@ page import="com.mealorderdetail.model.*"%>

<%
	MealOrderVO mealOrderVO = (MealOrderVO) request.getAttribute("mealOrderVO");
	MealOrderService mealOrderSvc = new MealOrderService();
	List<MealOrderVO> mealOrderList = mealOrderSvc.getAll();
	pageContext.setAttribute("mealOrderList", mealOrderList);

	MealOrderDetailVO mealOrderDetailVO = (MealOrderDetailVO) request.getAttribute("mealOrderDetailVO");
	MealOrderDetailService mealOrderDetailSvc = new MealOrderDetailService();
	List<MealOrderDetailVO> mealOrderDetailList = mealOrderDetailSvc.getAll();
	pageContext.setAttribute("mealOrderDetailList", mealOrderDetailList);
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
<title>showAllMealOrder</title>
</head>

<body>
	<h2>所有餐點訂單 - All Meal Order</h2>
	<hr>
	<%-- 	<c:if test="${not empty errorMsgs}"> --%>
	<!-- 		<font style="color: red">請修正以下錯誤:</font> -->
	<!-- 		<ul> -->
	<%-- 			<c:forEach var="message" items="${errorMsgs}"> --%>
	<%-- 				<li style="color: red">${message}</li> --%>
	<%-- 			</c:forEach> --%>
	<!-- 		</ul> -->
	<%-- 	</c:if> --%>


	<c:choose>
		<c:when test="${mealOrderList.size() > 0}">
			<table id="myTable" class="table tablesorter">
				<thead>
					<tr>
						<th scope="col">訂單編號</th>
						<th scope="col">訂房單號</th>
						<th scope="col">客房編號</th>
						<th scope="col">訂單時間</th>
						<th scope="col">訂單總額</th>
						<th scope="col">訂單狀態</th>
						<th scope="col">修改</th>
<!-- 						<th scope="col">刪除</th> -->
					</tr>
				</thead>
				<tbody>
					<%-- 		<%@ include file="page1.file" %> --%>
					<c:forEach var="mealOrderVO" items="${mealOrderList}" varStatus="i">
						<tr>

							<td style="font-size: 16px; text-align: center; padding-top: 2%;">
								<form id="form${i.index}" method="post"
									action="${pageContext.request.contextPath}/MealOrderDetailServlet">
									<input type="hidden" name="meal_odno"
										value="${mealOrderVO.meal_odno}"> <input type="hidden"
										name="action" value="getOneMealOrderDetail"> <a
										href="#"
										onclick="document.getElementById('form' + ${i.index} + '').submit();"
										style="text-decoration: none; color: brown;">
										${mealOrderVO.meal_odno} </a>
								</form>
							</td>

							<td style="font-size: 16px; text-align: center; padding-top: 2%;">${mealOrderVO.bk_no}</td>
							<td style="font-size: 16px; text-align: center; padding-top: 2%;">${mealOrderVO.rm_no}</td>
							<td style="font-size: 16px; text-align: center; padding-top: 2%;">
								<fmt:formatDate value="${mealOrderVO.od_time}"
									pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td style="font-size: 16px; text-align: center; padding-top: 2%;">${mealOrderVO.total_price}</td>
							<td style="font-size: 16px; text-align: center; padding-top: 2%;">
								<c:choose>
									<c:when test="${mealOrderVO.od_status.equals('0')}">準備中</c:when>
									<c:when test="${mealOrderVO.od_status.equals('1')}">製作中</c:when>
									<c:when test="${mealOrderVO.od_status.equals('2')}">已出餐</c:when>
									<c:when test="${mealOrderVO.od_status.equals('3')}">已取消</c:when>
								</c:choose>
							</td>
							<td><input type="hidden" name="meal_odno"
								value="${mealOrderVO.meal_odno}">
								<button type="submit" class="update btn btn-info">修改</button></td>
<!-- 							<td> -->
<!-- 								<form method="post" -->
<%-- 									action="${pageContext.request.contextPath}/MealOrderServlet"> --%>
<!-- 									<input type="hidden" name="delete-mealorder-no" -->
<%-- 										value="${mealOrderVO.meal_odno}"> <input type="hidden" --%>
<!-- 										name="action" value="delete_meal_order"> -->
<!-- 									<button type="submit" class="btn btn-danger">刪除</button> -->
<!-- 								</form> -->
<!-- 							</td> -->
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:when>
		<c:otherwise>
			<h3>查無資料</h3>
		</c:otherwise>
	</c:choose>
	<%-- 	<%@ include file="page2.file" %> --%>

	<form class="update-display update-display-div" method="post"
		action="${pageContext.request.contextPath}/MealOrderServlet">

		<div class="close-icon">
			<i class="fas fa-times icon"></i>
		</div>
		<h3>
			訂單編號：<b id="update-mealorder-no"></b>
		</h3>

		<label for="update-mbid">
			<p>會員編號</p> <input type="text" id="update-mbid" name="update-mbid"
			maxlength="10" placeholder="請依照格式輸入(MEMXXXXXXX)" required disabled="disabled"/>
		</label> <label for="update-rmno">
			<p>客房編號</p> <input type="text" id="update-rmno" name="update-rmno"
			maxlength="3" placeholder="請依照格式輸入(XXX)" required />
		</label> <label for="update-totalprice">
			<p>訂單總額</p> <input type="text" id="update-totalprice"
			name="update-totalprice" readonly="readonly"/>
		</label> <label for="update-odstatus">
			<p>訂單狀態</p> <select class="custom-select custom-select-sm"
			name="update-odstatus" id="update-odstatus">
				<option value="0" selected>準備中</option>
				<option value="1">製作中</option>
				<option value="2">已出餐</option>
				<option value="3">已取消</option>
		</select>
		</label> <input type="hidden" name="action" value="update_meal_order">
		<input type="hidden" name="update-mealorder-no"
			id="update-mealorder-to-servlet">
		<button type="submit" class="btn btn-success"
			style="width: 100px; margin: 50px auto; background-color: pink;">更新資料</button>

	</form>

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
			$("#update-mealorder-no").text(children.eq(0).text());
			$("#update-mealorder-to-servlet").val(children.eq(0).text());
			$("#update-mbid").val(children.eq(1).text());
			$("#update-rmno").val(children.eq(2).text());
			$("#update-totalprice").val(children.eq(4).text());
			$("#update-odstatus").val(children.eq(5).text().change());
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
			$('#myTable').DataTable({
				"bSort": false,
				language: { 
                "sProcessing": "處理中...",
                "sLengthMenu": "顯示 _MENU_ 項結果", 
				"sZeroRecords": "沒有匹配結果", 
				"sInfo": "顯示第 _START_ 至 _END_ 項結果，共 _TOTAL_ 項", 
				"sInfoEmpty": "顯示第 0 至 0 項結果，共 0 項",
				"sInfoFiltered": "(由 _MAX_ 項結果過濾)",
 				"sInfoPostFix": "", "sSearch": "搜尋:", 
				"sUrl": "", "sEmptyTable": "表中資料為空", 
				"sLoadingRecords": "載入中...", 
				"sInfoThousands": ",", 
				"oPaginate": { "sFirst": "首頁", "sPrevious": "上頁", "sNext": "下頁", "sLast": "末頁" }, 
				"oAria": { "sSortAscending": ": 以升序排列此列", "sSortDescending": ": 以降序排列此列" }
				}
			});
		});
		
	</script>
</body>

</html>