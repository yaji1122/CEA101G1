<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.members.model.*"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/frontend/files/commonCSS.file"%>
<title>Sorry this is guest only page.</title>
</head>
<style>
	#shallnotpass {
		position: absolute;
		top:50%;
		left:50%;
		transform: translate(-50%, -50%);
		text-align:center;
		max-width:100%;
		height:100vh;
		display:flex;
		flex-direction: column;
		justify-content: center;
	}
	#shallnotpass img {
		margin-bottom: 15px;
	}
	#shallnotpass h3 {
		margin-bottom: 15px;
	}
	#gear {
		animation-name: spin;
	    animation-duration: 4000ms;
	    animation-iteration-count: infinite;
	    animation-timing-function: linear;
	    margin-right:20px;
	}
	@keyframes spin {
		from {
			transform:rotate(0deg);
		} to {
			transform:rotate(360deg);
		}
	}
</style>
<body>
		<div class=logo style="height:50px;text-align:center;padding:10px 0px 0px 0px">
			<a href="${pageContext.request.contextPath}/frontend/index.jsp">
				<img style="max-height:100%" src="${pageContext.request.contextPath}/img/logo.png"/>
			</a>
		</div>
		<div id="shallnotpass">
			<img src="<%=request.getContextPath()%>/img/noAuth.gif">
			<h3><i class="fas fa-cog" id="gear"></i>網頁維護中</h3>
			<a href="<%=request.getContextPath()%>/frontend/index.jsp" class="btn btn-outline-dark">回首頁</a>
		</div>
<%@ include file="/frontend/files/commonJS.file"%>

</body>
</html>