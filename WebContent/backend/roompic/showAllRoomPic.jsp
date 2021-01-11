<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.roompic.model.*"%>
<%@ page import="java.util.List"%>

<%
String rmtype = request.getParameter("rmtype-pic");
RoomPicService rmpicSvc = new RoomPicService();
List<RoomPicVO> rmpics = rmpicSvc.getAllByRoomType(rmtype);
pageContext.setAttribute("rmpics", rmpics);
%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/backend/files/backend_header.file" %>
<title>房型圖庫</title>
<style>

img {
	width: 400px;
	border-radius: 10px;
	margin: 5px auto;
	border: 2px solid transparent;
}
.add-border {
	border: 2px solid red;
}
div {
	padding: 5px;
}
.pic-wrapper {
	display: flex;
	flex-direction: row;
	justify-content: center;
	flex-wrap: wrap;
	margin: 0 auto;
	padding:0px;
	padding-bottom:60px;
}
.pic-wrapper h3 {
	padding-top:20px;
}
.removepic:hover {
    background-color: crimson;
}
.removepic {
    background-color: #f4f4f2;
	width:101%;
	height:50px;
	position:fixed;
	bottom:0px;
	border-top: 2px solid black;
    border-bottom: 2px solid black;
}
</style>
</head>
<body>
	<div class="pic-wrapper">
		<c:choose>
			<c:when test="${rmpics.size() != 0}">
				<c:forEach var="pic" items="${rmpics}">
					<div>
						<img
							src="<%=request.getContextPath()%>/RoomPicServlet?rmpicno=${pic.rm_pic_no}&action=getOneRmPic">
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
			<h3>尚未上傳圖片</h3>
			</c:otherwise>
		</c:choose>
		<button class="removepic">移除圖片</button>
		
	</div>
	<script>
		$("img").click(function() {
			if ($(this).hasClass("add-border")) {
				$(this).removeClass("add-border");
			} else {
				$(this).addClass("add-border");
			}
		})
		$(".removepic").click(function(){
			let willdel = document.getElementsByClassName("add-border");
			
			for (let i = 0; i < willdel.length; i++) {
				let srcStr = willdel[i].getAttribute("src").toString();
				let index = srcStr.indexOf("=") + 1;
				let picno = srcStr.substring(index, index+6);
				let formData = new FormData();
				formData.append("picno", picno);
				formData.append("action", "deletePic");
				let xhr = new XMLHttpRequest();
				xhr.addEventListener("load", uploadComplete, false);
				xhr.open("POST", "${pageContext.request.contextPath}/RoomPicServlet");
				xhr.send(formData);
			}
		});
		function uploadComplete(event) {
			location.reload();
		}
	</script>
</body>
</html>