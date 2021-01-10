<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.actorder.model.*"%>
<jsp:useBean id="actoderSvc" scope="page" class="com.actorder.model.ActOrderService"/>
<%	
	ActOrderService actorderSvc = new ActOrderService();
	List<ActOrderVO> actOrderList = (List<ActOrderVO>) request.getAttribute("actOrderList");
	if (actOrderList == null) {
		actOrderList = actorderSvc.getAll();
	}
	pageContext.setAttribute("actOrderList", actOrderList);
%>
<html>
<head>
</head>
<body>
	<div>
		<div>
			<label for="search">單號查詢</label>
			<input type="text" id="search" style="text-transform:uppercase; margin-right:10px;">
			<label for="order_status">訂單狀態</label>
			<select id="order_status" name="order_status">
				<option value="all">全部訂單</option>
				<option value="0">未結帳</option>
				<option value="1">已結帳</option>
				<option value="2">已取消</option>
			</select>
		</div>
		<table>
			<tr>
				<th>活動訂單編號</th>
				<th>活動編號</th>
				<th>訂房編號</th>
				<th>訂單狀態</th>
				<th>訂單日期</th>
				<th>參加人數</th>
				<th>訂單金額</th>
				<th>修改</th>
			</tr>
			<c:if test="${actOrderList.size() == 0}">
				<tr>
					<td colspan="8">查無訂單</td>
				</tr>
			</c:if>
			<c:forEach var="actOrderVO" items="${actOrderList}">
					
					<tr>
						<td>${actOrderVO.actOdno}</td>
						<td>${actOrderVO.actNo}</td>
						<td>${actOrderVO.bkNo}</td>
						<td><c:choose>
								<c:when test="${actOrderVO.odStatus == 0}">未結帳</c:when>
								<c:when test="${actOrderVO.odStatus == 1}">已結帳</c:when>
								<c:when test="${actOrderVO.odStatus == 2}">已取消</c:when>
							</c:choose></td>
						<td>${actOrderVO.odTime}</td>
						<td>${actOrderVO.ppl}</td>
						<td>${actOrderVO.totalPrice}</td>
						<td>
							<button type="submit" class="btn btn-outline-dark update" data-actodno="${actOrderVO.actOdno}">修改訂單</button>
						</td>
					</tr>
			</c:forEach>	
			
		</table>
	</div>
	<div class="info-display" id="update-frame">
			<div class="close-icon">
				<i class="fas fa-times icon"></i>
			</div>
			<iframe src="" style="border: none; height: 100%; width: 100%;"></iframe>
	</div>
	<script>
	$(document).ready(function(){
		let display = $("#update-frame");
	    $(".update").click(function (e) {
	        e.preventDefault();
	        let actodno = $(this).attr("data-actodno");
	        let url = "<%=request.getContextPath()%>/ActOrderServlet?action=getOne_For_Update&actOdno=" + actodno;
			display.addClass("display-show");
			display.children("iframe").attr("src", url);
		});

		$(".icon").click(function() {
			$(this).parents(".display-show").removeClass("display-show");
		});
		
		$("#search").keyup(function(e){
			if (event.keyCode == 13) {
				let actodno = $(this).val();
				window.location.href = "<%=request.getContextPath()%>/ActOrderServlet?action=getOne_For_Display&actOdno=" + actodno;
				
			}
		})
		$("#order_status").change(function(){
			let status = $(this).val();
			window.location.href = "<%=request.getContextPath()%>/ActOrderServlet?action=getAllByOdStatus&odstatus=" + status;
		})
	})
	</script>
</body>
</html>