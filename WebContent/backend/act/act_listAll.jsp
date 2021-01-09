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
		<div class="table-content" id="content">
			<table class="table table-hover" id="table">
				<thead class="title">
					<tr class="table-primary">
						<th>活動編號</th>
						<th>活動類別</th>
						<th>活動名稱</th>
						<th>活動狀態</th>
						<th>活動開始時間</th>
						<th>修改活動</th>
					</tr>
				</thead>
				<tbody>
					<jsp:useBean id="eventSvc" scope="page"
						class="com.actevent.model.ActEventService" />
					<c:forEach var="actVO" items="${list}">
						<tr>
							<td>${actVO.actNo}</td>
							<td>${eventSvc.getOneActEvent(actVO.actEventNo).actEventName}</td>
							<td>${actVO.actName}</td>
							<td><c:choose>
									<c:when test="${actVO.actStatus == 0}">未開始</c:when>
									<c:when test="${actVO.actStatus == 1}">進行中</c:when>
								</c:choose></td>
							<td>${actVO.actTime}</td>
							<td><input class="update btn btn-primary" type="button"
								value="修改"> <input type="hidden" name="rm_no"
								value="${rmvo.rm_no}"> <input type="hidden"
								name="action" value=""></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<form class="update-display update-form" method="post"
			action="${pageContext.request.contextPath}/act.do">
			<div class="close-icon">
				<i class="fas fa-times icon"></i>
			</div>
			<h3>
				活動編號：<b id="update-actno"></b>
			</h3>
			<label for="update-actevent">活動類型</label> <select id="update-actevent" name="update-actevent" required>
				<c:forEach var="event" items="${eventSvc.getAll()}">
					<option value="${event.actEventNo}">${event.actEventName}</option>
				</c:forEach>
			</select> <label for="update-actname">活動名稱</label> <input type="text"
				name="update-actname" id="update-actname"  maxlength="20"
				min="1" autocomplete="off" required />
				 <label for="update-actstatus">活動狀態</label> 
				<select name="update-actstatus" id="update-actstatus"
				class="rm-select" required>
					<option value="0">已停止</option>
					<option value="1">進行中</option>
					<option value="2">已停辦</option>
			</select><label for="update-acttime">活動開始時間</label> <input type="text"
				name="update-acttime" id="update-acttime" class="rm-input" max="9"
				min="1" autocomplete="off" required /> <label for="update-actprice">活動價格</label>
			<input type="number" name="update-actprice" id="update-actprice"
				class="rm-input" max="9" min="1" autocomplete="off" required />
				 <label for="update-mbname">活動內容介紹</label> 
				 <textarea id="update-mbname" name="update-mbname"> </textarea>
				 <label for="update-mbname">活動圖片</label>
			<input type="text" name="update-mbname" id="update-mbname"
				class="rm-input" max="9" min="1" autocomplete="off" required /> <input
				name="action" value="update_room" style="display: none"> <input
				id="update-act" name="update-act-no" type="text"
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
			case "已停止":
				status = '0';
				break;
			case "進行中":
				status = '1';
				break;
			case "已停辦":
				status = '2';
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
	</script>
</body>
</html>