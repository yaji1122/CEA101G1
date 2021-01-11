<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.pickup.model.*"%>
<%@ page import="com.choppers.model.*" %>
<%@ page import="java.util.List"%>
<%
PickupService pkupService = new PickupService();
List<PickupVO> pkupList = (List<PickupVO>)request.getAttribute("pkupList");
String current_query_status = (String) request.getAttribute("query_status");
if(pkupList == null) {
	pkupList = pkupService.getAllPkupByStatus("0"); //預設列出所有未完成的訂單
	pageContext.setAttribute("pkupList", pkupList);
}
if (current_query_status == null) {
	current_query_status = "0";
}
pageContext.setAttribute("current_query_status", current_query_status);
%>
<!DOCTYPE html>
<html>
<head>
<title>接送預定狀況</title>
</head>
<style>
.conditions {
	padding:10px 30px;
	width:100%;
}
.conditions input {
	height:25px;
	display:inline-block;
}
#pkupstatusquery_form {
	display:flex;
	flex-direction:row;
	justify-content:space-evenly;
}
.form-parts {
display:inline-block;
width:fit-content;
}

.form-parts:nth-child(2) label{
float:left;
}
</style>
<body>
<div class="conditions">
<form method="post"  id="pkupstatusquery_form" action="<%=request.getContextPath()%>/PickupServlet?action=getAllByQuery">
		<div class="form-parts">
			<label for="bk_no_query">訂房單號：</label>
			<input type="text" placeholder="輸入訂房單號" name="bk_no_query" id="bk_no_query">
			<button type="submit" class="btn btn-light" id="composite-query">查詢</button>
		</div>
	<div class="form-parts">
			<label for="pkup_status_query">訂單狀態：</label>
			<select class="form-select" id="pkup_status_query" name="pkup_status_query">
				<option value="all" <c:if test="${current_query_status == 'all'}"> selected</c:if> >全部訂單</option>
				<option value="0"  <c:if test="${current_query_status == '0'}"> selected</c:if>>未完成</option>
				<option value="1" <c:if test="${current_query_status == '1'}"> selected</c:if>>已完成</option>
				<option value="2" <c:if test="${current_query_status == '2'}"> selected</c:if>>已取消</option>
			</select>
	</div>
	</form>
</div>
<jsp:useBean id="chopSvc" scope="page" class="com.choppers.model.ChoppersService"/>
	<c:if test="${not empty msgs}">
		<ul>
			<c:forEach var="message" items="${msgs}">
				<li style="color: red; list-style: none;">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	
			<table>
				<tr>
					<th>預約單號</th>
					<th>訂房單號</th>
					<th>直升機編號</th>
					<th>接到客戶時間</th>
					<th>預約接送時間</th>
					<th>訂單狀態</th>
					<th>修改資料</th>
				</tr>
				<c:choose>
					<c:when test="${pkupList.size() > 0}">
				<%
					String[] layer = {"odd", "even"};
					int number = 2;
				%>
				<c:forEach var="pkupvo" items="${pkupList}">
					<tr class="<%=layer[number++ % 2]%>">
						<td class="pkupno">${pkupvo.pkup_no}</td>
						<td><a href="<%=request.getContextPath()%>/bookingServlet?bkod_bkno_query=${pkupvo.bk_no}&action=getone_bybkno">${pkupvo.bk_no}</a></td>
						<td>${pkupvo.chop_no}：${chopSvc.getOneByChopNo(pkupvo.chop_no).chop_name}</td>
						<td>
							<c:choose>
								<c:when test="${pkupvo.pkup_time == null}">
								-
								</c:when>
								<c:otherwise>
								<fmt:formatDate value="${pkupvo.pkup_time}" pattern="yyyy/MM/dd HH:mm:ss"/>
								</c:otherwise>
							</c:choose>
						</td>
						<td><fmt:formatDate value="${pkupvo.arrive_datetime}" pattern="yyyy/MM/dd HH:mm"/></td>
						<td>
						<select class="pkupstatus" required>
							<c:choose>
								<c:when test="${pkupvo.pkup_status == '0'}">
								<option value="0" selected>未完成</option>
								<option value="1">已完成</option>
								<option value="2">已取消</option>
								</c:when>
								<c:when test="${pkupvo.pkup_status == '1'}">
								<option value="0">未完成</option>
								<option value="1" selected>已完成</option>
								<option value="2">已取消</option>
								</c:when>
								<c:otherwise>
								<option value="0">未完成</option>
								<option value="1">已完成</option>
								<option value="2" selected>已取消</option>
								</c:otherwise>
							</c:choose>
						</select>
						</td>
						<td>
						<input class="update btn btn-primary <c:if test="${pkupvo.pkup_status == '2'}">button-disabled</c:if>" type="button" value="修改" <c:if test="${pkupvo.pkup_status == '2'}">disabled</c:if> >
						</td>
					</tr>
				</c:forEach>
		</c:when>
		<c:otherwise>
			<h3>沒有符合條件的訂單</h3>
		</c:otherwise>
	</c:choose>
	</table>
	<!-- Update Box Start-->
	
	<div class="update-display">
	<form class="update-form" id="update-pkup-form" method="post" action="${pageContext.request.contextPath}/PickupServlet">
			<div class="close-icon">
				<i class="fas fa-times icon"></i>
			</div>
			<h3>
				接送單號：<b id="update-pkup-no"></b>
			</h3>
			<h3>
				訂房單號：<b id="update-bkno"></b>
			</h3>
			<label for="update-chopno">直升機編號</label> 
			<select name="update-chopno" id="update-chopno" required>
					<c:forEach var="chopvo" items="${chopSvc.getAll()}">
						<option value="${chopvo.chop_no}">${chopvo.chop_no}：${chopvo.chop_name}</option>
					</c:forEach>
			</select>
				<label for="update-arrivedatetime">預約接送時間</label> 
				<input type="text" name="update-arrivedatetime" id="update-arrivedatetime" autocomplete="off" required />
			<input name="action" value="update_pkup" style="display: none">
			<input id="update-pkupno" name="update-pkupno" type="text" style="display: none">
			<button class="update-data" type="submit" style="width: 100%">更新資料</button>
	</form>
	</div>
	<!-- Update Box end -->
	<script>
	$(document).ready(function(){
		$("#pkup_status_query").change(function(){
			let form = $("#pkupstatusquery_form");
			form.submit();
		})
		
		$("#update-arrivedatetime").datetimepicker();
		
		
		$("#update-pkup-form").submit(function(e){
			e.preventDefault();
			
			let data = new FormData(document.querySelector("#update-pkup-form"));
			let xhr = new XMLHttpRequest();
			xhr.open("post", "${pageContext.request.contextPath}/PickupServlet")
			xhr.onload = function(){
				if(xhr.readyState === xhr.DONE){
					if(xhr.status === 200) {
						if (xhr.responseText === "success"){
							Swal.fire({
                                position: "top",
                                icon: "success",
                                title: "預定狀態已更新",
                                showConfirmButton: false,
                                timer: 1500,
                            });
							setTimeout(function () {
                                location.reload();
                            }, 1400);
						}
					}
				}
			}
			xhr.send(data);
		})
		
		$(".pkupstatus").change(function(){
			let status = $(this).val();
			let data = new FormData();
			let pkup_no = $(this).parents("tr").children().eq(0).text();
			data.append("pkup_status", status);
			data.append("pkup_no", pkup_no);
			let xhr = new XMLHttpRequest();
			xhr.open("post", "${pageContext.request.contextPath}/PickupServlet?action=pkup_status_change")
			xhr.onload = function(){
				if(xhr.readyState === xhr.DONE){
					if(xhr.status === 200) {
						if (xhr.responseText === "success"){
							Swal.fire({
                                position: "top",
                                icon: "success",
                                title: "預定狀態已更新",
                                showConfirmButton: false,
                                timer: 1500,
                            });
							setTimeout(function () {
                                location.reload();
                            }, 1400);
						}
					}
				}
			}
			xhr.send(data);
		})
		$(".icon").click(function() {
			$(this).parents(".display-show").removeClass("display-show");
		})
		$(".update").click(function() {
			$(".update-display").addClass("display-show")
			let tr = $(this).parents("tr");
			let children = tr.children();
			$("#update-pkupno").val(children.eq(0).text());
			$("#update-pkup-no").text(children.eq(0).text());
			$("#update-bkno").text(children.eq(1).text());
			$("#update-chopno").val(children.eq(2).text().split("：")[0]).change();
			$("#update-pkuptime").val(children.eq(3).text());
			$("#update-arrivedatetime").val(children.eq(4).text());
		});
	})
	</script>
</body>
</html>