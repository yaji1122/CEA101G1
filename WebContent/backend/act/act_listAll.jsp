<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.act.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	ActService actSvc = new ActService();
List<ActVO> list = actSvc.getAll();
pageContext.setAttribute("list", list);
%>



<html>
<head>
<title>所有活動資料 - listAllAct.jsp</title>
</head>
<body>
	<div>
		<div class="showmsg">
			<h3 class="msg">
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
			</h3>
			<p class="roomtotal">
				當前活動總數<b><%=list.size()%></b>
			</p>
		</div>

<%-- 		<div class="btn1" style="margin-right: 80px;">
			<button type="button" class="btn btn-outline-danger"
				onclick="location.href='<%=request.getContextPath()%>/backend/act/backend-act_select_page.jsp?action=getAll'">
				回首頁</button>
			<a class="btn btn-secondary dropdown-toggle" href="#" role="button"
				id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"> 訂單狀態 </a>
			<div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
				<a class="dropdown-item" href="#">報名中</a> <a class="dropdown-item"
					href="#">已完成</a> <a class="dropdown-item" href="#">已取消</a>
			</div>
			<a class="btn btn-secondary dropdown-toggle" href="#" role="button"
				id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true">
				活動項目 </a>
			<div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
				<a class="dropdown-item" href="#">沙灘車</a> <a class="dropdown-item"
					href="#">射箭</a> <a class="dropdown-item" href="#">燈會</a> <a
					class="dropdown-item" href="#">衝浪</a> <a class="dropdown-item"
					href="#">浮潛</a> <a class="dropdown-item" href="#">賞鯨</a> <a
					class="dropdown-item" href="#">BBQ</a> <a class="dropdown-item"
					href="#">深潛</a> <a class="dropdown-item" href="#">狩獵</a>
			</div>
		</div> --%>

		<div class="table-content" id="content">
			<table class="table table-hover" id="table">
				<thead class="title">
					<tr class="table-primary">
						<th>活動編號</th>
						<th>活動類別</th>
						<th>活動名稱</th>
						<th>活動狀態</th>
						<th>活動期間</th>
						<th>活動時段</th>
						<th>查看詳情</th>
					</tr>
				</thead>
				<tbody>
						<jsp:useBean id="eventSvc" scope="page" class="com.actevent.model.ActEventService"/>
						<c:forEach var="actVO" items="${list}">
							<tr>
								<td>${actVO.actNo}</td>
								<td>${eventSvc.getOneActEvent(actVO.actEventNo).actEventName}</td>
								<td>${actVO.actName}</td>
								<td><c:choose>
										<c:when test="${actVO.actStatus == 0}">進行中</c:when>
										<c:when test="${actVO.actStatus == 1}">已完成</c:when>
										<c:otherwise>已取消</c:otherwise>
									</c:choose></td>
								<td>${actVO.actRegTime} <br> ${actVO.deadLine}</td>
								<td>${actVO.actTime}</td>
								<td>
									<input class="update btn btn-primary" type="button"
									value="修改"> <input type="hidden" name="rm_no"
									value="${rmvo.rm_no}"> <input type="hidden" name="action"
									value="">
								</td>
							</tr>
						</c:forEach>
				</tbody>
			</table>
		</div>
		<form class="update-display update-form" method="post"
		action="${pageContext.request.contextPath}/Act.do">
			<div class="close-icon">
				<i class="fas fa-times icon"></i>
			</div>
			<h3>
				客房編號：<b id="update-rmno"></b>
			</h3>
			<h3>
				客房類型：<b id="update-rmtype"></b>
			</h3>
			<label for="update-rmstatus"><p>客房狀態</p> <select
				name="update-rmstatus" id="update-rmstatus" class="rm-select"
				required>
					<option value="0">閒置中</option>
					<option value="1">已入住</option>
					<option value="2">整修中</option>
					<option value="3">已廢棄</option>
			</select></label> <input name="action" value="update_room" style="display: none">
			<input id="update-room" name="update-rm-no" type="text"
				style="display: none">
			<button class="update-data" type="submit" style="width: 100%">更新資料</button>
	</form>
	</div>
	<script>
		$(".update").click(function() { //開啟修改視窗
			$(".update-display").addClass("display-show")
			let tr = $(this).parents("tr");
			let children = tr.children();
			$("#update-rmno").text(children.eq(0).text());
			$("#update-room").val(children.eq(0).text());
			$("#update-rmtype").text(children.eq(1).text());
			let status;
			console.log(children.eq(2).text())
			switch (children.eq(2).text().trim()) {
			case "閒置中":
				status = '0';
				break;
			case "已入住":
				status = '1';
				break;
			case "整修中":
				status = '2';
				break;
			default:
				status = '3';
				break;
			}
			$("#update-rmstatus").val(status).change();
		})

		$(".icon").click(function() { //關閉修改視窗
			let display = $(this).parents(".display-show");
			display.removeClass("display-show");
			$("#showroom").attr("src", "");
		})

		let roomTypeFilter = $("#room-type-select");
		let roomStatusFilter = $("#room-status-select");
		let currentTotal = parseInt($(".showmsg p b").text());
		let allTr = $("tr");

		roomTypeFilter.change(filter);
		roomStatusFilter.change(filter);

		$("#room_number").keyup(function() {
			let rmno = $("#room_number").val();
			let count = 0;
			for (let i = 1; i < allTr.length; i++) {
				if (allTr.eq(i).children().eq(0).text().indexOf(rmno) < 0) {
					allTr.eq(i).hide();
					count++;
				} else {
					allTr.eq(i).show();
				}
			}
			$(".showmsg p b").text(currentTotal - count);
		})

		function filter() {
			let selected_val = roomTypeFilter.val();
			let selected_val2 = roomStatusFilter.val();
			if (selected_val2 === "all" && selected_val === "all") {
				allTr.show();
				$(".showmsg p b").text(currentTotal);
				return;
			}
			allTr.show();
			let count = 0;

			for (let i = 1; i < allTr.length; i++) {
				if (selected_val === "all") {
					if (!allTr.eq(i).children().hasClass(selected_val2)) {
						allTr.eq(i).hide();
						count++;
					}
					continue;
				}
				if (selected_val2 === "all") {
					if (!allTr.eq(i).children().hasClass(selected_val)) {
						allTr.eq(i).hide();
						count++;
					}
					continue;
				}
				if (!allTr.eq(i).children().hasClass(selected_val2)
						|| !allTr.eq(i).children().hasClass(selected_val)) {
					allTr.eq(i).hide();
					count++;
				}
				/* if(!allTr.eq(i).children().hasClass(selected_val2) || !allTr.eq(i).children().hasClass(selected_val)){
					allTr.eq(i).hide();
					count++;
				} */
			}
			$(".showmsg p b").text(currentTotal - count);
		}
	</script>
</body>
</html>