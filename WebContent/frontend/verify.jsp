<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="mail.*" %>
<%@ page import="com.members.model.*"%>
<%
String code = request.getParameter("authcode");
String mb_id = request.getParameter("mb_id");
String action = request.getParameter("action");
if (action == null || !action.equals("verify")) {
	response.sendRedirect(request.getContextPath() + "/frontend/index.jsp");
	return;
}
MailAuthenticate auth = new MailAuthenticate();
boolean isPass = false;
if (auth.verifyCode(mb_id, code)) {
	MembersService memberSvc = new MembersService();
	memberSvc.updateStatus(mb_id, "1");
	isPass = true;
}
pageContext.setAttribute("isPass", isPass);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/frontend/files/commonCSS.file" %>
<title>會員驗證</title>
</head>
<style>
	.banner {
		height:150px;
		width:100%;
		display:flex;
		flex-direction: row;
		justify-content: center;
	}
	.banner img {
		max-height: 100%;
	}
</style>
<body>
<div class="banner">
	<img src="${pageContext.request.contextPath}/img/logo.png"/>
</div>



<%@ include file="/frontend/files/commonJS.file" %>
<script>
	<c:choose>
		<c:when test="${isPass}">
			Swal.fire({
				position: "center",
				icon: "success",
				title: "會員驗證完成，歡迎加入！",
				text:"3秒後頁面將自動跳轉首頁",
				showConfirmButton: false,
				timer: 3000
			})
			setTimeout(function(){
				window.location.href = "<%=request.getContextPath()%>/frontend/index.jsp"
			}, 3000)
		</c:when>
		<c:otherwise>
		Swal.fire({
			position: "center",
			icon: "error",
			title: "驗證碼已失效，請洽詢客服人員！",
			text:"3秒後頁面將自動跳轉首頁",
			showConfirmButton: false,
			timer: 3000
		})
		setTimeout(function(){
				window.location.href = "<%=request.getContextPath()%>/frontend/index.jsp"
			}, 3000)
		</c:otherwise>
	</c:choose>
</script>
</body>
</html>