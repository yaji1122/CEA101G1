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
<body>
<div class="info-display member-info">
		<h5 style="text-align: center">
			會員編號：<b>${membervo.mb_id}</b>
		</h5>
		<div class="memberphoto row">
			<div class="col-6">
				<div>
					<img src="${pageContext.request.contextPath}/MembersServlet?action=getone_mbpic&mb_id=${membervo.mb_id}">
				</div>
				<h6>
					會員姓名：<b>${membervo.mb_name}</b>
				</h6>
				<h6>
					會員生日：<b>${membervo.mb_bd}</b>
				</h6>
				<h6>
					聯絡電話：<b>${membervo.mb_phone}</b>
				</h6>
				<h6>
					電子郵箱：<b>${membervo.mb_email}</b>
				</h6>
				<h6>
					居住城市：<b>${membervo.mb_city}</b>
				</h6>
				<h6>
					居住鄉鎮：<b>${membervo.mb_town}</b>
				</h6>
				<h6>
					詳細地址：<b>${membervo.mb_address}</b>
				</h6>
				<h6>
					帳號創建日期：<b>${membervo.create_date}</b>
				</h6>
			</div>
		</div>
	</div>

</body>
</html>