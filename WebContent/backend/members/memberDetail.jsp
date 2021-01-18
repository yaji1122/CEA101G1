<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.members.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員詳情</title>
</head>
<style>
	.info-display {
		text-align:center;
	}
</style>
<body style="max-height:70vh">
	<div class="info-display member-info">
		<h4 style="text-align: center">
			會員編號 <span>${membervo.mb_id}</span>
		</h4>
		<div>
			<img style="max-width:60%"
				src="${pageContext.request.contextPath}/MembersServlet?action=getone_mbpic&mb_id=${membervo.mb_id}">
		</div>
		<h4>
			會員姓名：<span>${membervo.mb_name}</span>
		</h4>
		<h4>
			會員生日：<span>${membervo.mb_bd}</span>
		</h4>
		<h4>
			聯絡電話：<span>${membervo.mb_phone}</span>
		</h4>
		<h4>
			電子郵箱：<span>${membervo.mb_email}</span>
		</h4>
		<h4>
			居住城市：<span>${membervo.mb_city}</span>
		</h4>
		<h4>
			居住鄉鎮：<span>${membervo.mb_town}</span>
		</h4>
		<h4>
			詳細地址：<span>${membervo.mb_address}</span>
		</h4>
		<h4>
			帳號創建日期：<span>${membervo.create_date}</span>
		</h4>
	</div>
</body>
</html>