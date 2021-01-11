<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.rooms.model.*"%>
<%@ page import="com.roomtype.model.*"%>
<%@ page import="java.util.List"%>
<%
RoomsService rmSvc = new RoomsService();
List<RoomsVO> roomsList = rmSvc.getAll();
pageContext.setAttribute("roomsList", roomsList);
%>
<jsp:useBean id="rmtypeSvc" scope="page"
	class="com.roomtype.model.RoomTypeService" />
<!DOCTYPE html>
<html>
<head>
<title>客房一覽 Show All Rooms</title>
<style>
th {
	position: sticky;
	top: 0px;
}

.roomtable {
	overflow: scroll;
	max-height:75vh;
}

.showmsg {
	width: 100%;
	height: 40px;
	margin: 0px auto;
	padding: 5px;
	position: relative;
}
.msg {
	width: fit-content;
}

.showmsg p {
	position: absolute;
	right: 5vw;
	bottom: 0px;
}

.showmsg p b {
	color: crimson;
}

#room_number {
	text-align-last: center;
	border-radius: 5px;
}
</style>
</head>
<body>
	<div>
		<div class="showmsg">
			<h3 class="msg">
				<%
				String msg = (String) request.getAttribute("msg");
				if (msg != null) {
				%>
				<%=msg%>
				<%}%>
			</h3>
			<p class="roomtotal">
				當前查詢房間總數<b><%=roomsList.size()%></b>
			</p>
		</div>
		<div class="roomtable">
			<table>
				<thead class="firstTr">
					<tr>
						<th><input type="text" id="room_number" maxlength="3"
							placeholder="客房編號"></th>
						<th><select id="room-type-select">
								<option value="all" selected>全部房型</option>
								<c:forEach var="rmtype" items="${rmtypeSvc.all}">
									<option value="rmtype${rmtype.rm_type}">${rmtype.type_name}</option>
								</c:forEach>
						</select></th>
						<th><select id="room-status-select">
								<option value="all" selected>全部</option>
								<option value="0">閒置中</option>
								<option value="1">已入住</option>
								<option value="2">整修中</option>
								<option value="3">已廢棄</option>
						</select></th>
						<th>目前入住會員</th>
						<th>更新狀態</th>
					</tr>
				</thead>
				<%
					String[] layer = {"odd", "even"};
				int number = 2;
				%>
				<%-- <%@ include file="/backend/page.file"%> --%>
				<c:forEach var="rmvo" items="${roomsList}">
					<tr class="<%=layer[number++ % 2]%> ">
						<td class="rm_no" id="${rmvo.rm_no}">${rmvo.rm_no}</td>
						<td class="rmtype${rmvo.rm_type}">
							${rmtypeSvc.getOne(rmvo.rm_type).type_name} <%-- <c:forEach var="rmtypevo" items="${rmtypeSvc.all}">
						<c:if test="${rmvo.rm_type==rmtypevo.rm_type}">
						${rmtypevo.type_name}
						</c:if>
					</c:forEach> --%>
						</td>
						<td class="${rmvo.rm_status}"><c:choose>
								<c:when test="${rmvo.rm_status.equals('0')}">閒置中</c:when>
								<c:when test="${rmvo.rm_status.equals('1')}">已入住</c:when>
								<c:when test="${rmvo.rm_status.equals('2')}">整修中</c:when>
								<c:otherwise>已廢棄</c:otherwise>
							</c:choose></td>
						<td><c:choose>
								<c:when test="${rmvo.mb_id==null}">
    						-
  						</c:when>
								<c:otherwise>
							${rmvo.mb_id}
  						</c:otherwise>
							</c:choose></td>
						<td><input class="update btn btn-outline-danger btn-sm" type="button"
							value="修改"> <input type="hidden" name="rm_no"
							value="${rmvo.rm_no}"> <input type="hidden" name="action"
							value=""></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<%-- <%@ include file="/backend/page2.file"%> --%>
	<form class="update-display update-form" method="post"
		action="${pageContext.request.contextPath}/RoomsServlet">
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