<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.actevent.model.*"%>
<html>
<head>
</head>
<style>
	input[disabled] {
    background-color: transparent !important;
    border: 1px solid transparent !important;
    color: black !important;
	}
	input:focus {
		outline:2px solid red;
	}
	.update-confirm {
		display:none;
		position: absolute;
		right:10px;
		top:50%;
		transform:translateY(-50%);
	}
	.update-confirm.show {
		display:block;
	}
</style>
<body>
	<div>
		<table>
			<tr>
				<th>活動項目編號</th>
				<th>活動項目名稱</th>
				<th>修改</th>
			</tr>
			<tr>
				<c:forEach var="actEventVO" items="${eventSvc.all}">
					<tr>
						<td>${actEventVO.actEventNo}</td>
						<td style="position:relative">
						<input class="update-eventname" value="${actEventVO.actEventName}" disabled>
						<button class="btn btn-outline-danger update-confirm" data-acteventno="${actEventVO.actEventNo}" >確認</button>
						</td>
						<td>
							<button class="btn btn-outline-dark update-event" >修改名稱</button>
						</td>
					</tr>
				</c:forEach>
			</tr>
		</table>
			<table>
				<tr>
					<td colspan="3" style="color:white;background-color:black">新增活動項目</td>
				</tr>
				<tr>
					<td>活動項目編號:</td>
					<td>
						<% ActEventService eventSvc = new ActEventService(); %>
						<select style="width:100%; height:30px" id="updateeventno" required>
							<option value="00" selected>選擇項目編號</option>
							<option value="10"  <c:if test="<%= eventSvc.getAll().stream().anyMatch(e -> e.getActEventNo().equals(\"10\")) %>">disabled</c:if>>10</option>
							<option value="20"  <c:if test="<%= eventSvc.getAll().stream().anyMatch(e -> e.getActEventNo().equals(\"20\")) %>">disabled</c:if> >20</option>
							<option value="30"  <c:if test="<%= eventSvc.getAll().stream().anyMatch(e -> e.getActEventNo().equals(\"30\")) %>">disabled</c:if> >30</option>
							<option value="40"  <c:if test="<%= eventSvc.getAll().stream().anyMatch(e -> e.getActEventNo().equals(\"40\")) %>">disabled</c:if> >40</option>
							<option value="50"  <c:if test="<%= eventSvc.getAll().stream().anyMatch(e -> e.getActEventNo().equals(\"50\")) %>">disabled</c:if> >50</option>
							<option value="60"  <c:if test="<%= eventSvc.getAll().stream().anyMatch(e -> e.getActEventNo().equals(\"60\")) %>">disabled</c:if> >60</option>
							<option value="70"  <c:if test="<%= eventSvc.getAll().stream().anyMatch(e -> e.getActEventNo().equals(\"70\")) %>">disabled</c:if> >70</option>
							<option value="80"  <c:if test="<%= eventSvc.getAll().stream().anyMatch(e -> e.getActEventNo().equals(\"80\")) %>">disabled</c:if> >80</option>
							<option value="90"  <c:if test="<%= eventSvc.getAll().stream().anyMatch(e -> e.getActEventNo().equals(\"90\")) %>">disabled</c:if> >90</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>活動項目名稱:</td>
					<td><input type="TEXT" id="updateeventname" name="actEventName" maxlength="10" style="width:100%" required
						placeholder="請輸入活動項目名稱(10字為限)"></td>
				</tr>
				<tr>
					<td colspan="3">
						<button class="btn btn-outline-primary insert-eventno">確認新增</button>
					</td>
				</tr>
			</table>
			<br> 
	</div>
	<script>
		$(".insert-eventno").click(function(){
			let eventNo = $("#updateeventno").val();
			let eventName = $("#updateeventname").val();
			if (eventNo == "00") {
				Swal.fire({
					position:"center",
					title:"請選擇項目編號",
					icon:"error",
				})
				return;
			} else if (eventName == ""){
				Swal.fire({
					position:"center",
					title:"請填入項目名稱",
					icon:"error",
				})
				return;
			}
			
			$.ajax({
				url:"<%=request.getContextPath()%>/ActEventServlet?action=insert",
				type:"post",
				data:{
					eventname: eventName,
					eventno: eventNo,
				},
				success: function(msg){
					if (msg == "success"){
						Swal.fire({
							position:"center",
							title:"新增成功",
							icon:"success",
							showConfirmButton: false,
							timer:1000,
						})
						setTimeout(function(){
							window.location.reload();
						}, 1000)
					}
				}
			})
		})
	
		$(".update-event").click(function(){
			let input = $(this).parent().prev().children("input");
			let confirm = input.next();
			if (!confirm.hasClass("show")){
				input.attr("disabled", false);
				confirm.addClass("show");
				$(this).text("取消修改")
			} else {
				input.attr("disabled", true);
				confirm.removeClass("show");
				$(this).text("修改名稱")
			}
		})
		$(".update-confirm").click(function(){
			let name = $(this).prev().val();
			let no = $(this).attr("data-acteventno");
			let button = $(this);
			let nextButton = $(this).parent().next().children(".update-event");
			if (name == ""){
				Swal.fire({
					position:"center",
					title:"請填入名稱",
					icon:"error",
				})
				return;
			}
			$.ajax({
				url:"<%=request.getContextPath()%>/ActEventServlet?action=update",
				type:"post",
				data:{
					eventname: name,
					eventno: no,
				},
				success: function(msg){
					if (msg == "success"){
						button.removeClass("show");
						button.prev().attr("disabled", true);
						nextButton.text("修改名稱")
						}
				}
			})
		})
	</script>
</body>
</html>