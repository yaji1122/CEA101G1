<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.roomtype.model.*"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/backend/files/backend_header.file" %> <!-- 基本CSS檔案 -->
<title>房型管理</title>
</head>
<body>
<%@ include file="/backend/files/backend_sidebar.file" %> <!-- 側邊導覽檔案 -->
	<section class="wrapper">
		<ul class="tabs">
			<li class="active">所有房型</li>
			<li>上傳房型照片</li>
			<li>新增房型</li>
		</ul>

		<ul class="tab__content">
			<li class="active">
				<div class="content__wrapper">
					<div class="table-wrapper">
						<jsp:include page="showAllRoomTypes.jsp" /> 
					</div>
				</div>
			</li>
			<li>
				<div class="content__wrapper">
					<jsp:include page="addRoomTypePic.jsp" />
				</div>
			</li>
			<li>
				<div class="content__wrapper">
					<jsp:include page="addRoomType.jsp" />
				</div>
			</li>
		</ul>
	</section>

	<%@ include file="/backend/files/backend_footer.file" %> <!-- 基本JS檔案 -->
</body>
</html>
