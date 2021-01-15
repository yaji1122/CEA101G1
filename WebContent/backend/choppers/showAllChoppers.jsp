<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.choppers.model.*"%>
<%@ page import="java.util.List"%>
<%
ChoppersService chopSvc = new ChoppersService();
List<ChoppersVO> chopList = chopSvc.getAll();
pageContext.setAttribute("chopList", chopList);
%>
<!DOCTYPE html>
<html>
<head>
<title>所有機型</title>
</head>
<style>
.chop-total b {
color:crimson;
}
.showmsg h3 {
color:gold;
}
.chop-update-form {
background-color:white;
}
</style>
<body>
	<div class="showmsg">
			<h3>
				<%
					String msg = (String) request.getAttribute("msg");
				if (msg != null) {
				%>
				<%=msg%>
				<%}%>
			</h3>
			<p class="chop-total">
				目前共<b><%=chopList.size()%></b>台空中載具
			</p>
		</div>
	<c:choose>
		<c:when test="${chopList.size() > 0}">
			<table>
				<tr>
					<th>機型編號</th>
					<th>機型名稱</th>
					<th>接送價格</th>
					<th>出勤狀態</th>
					<th>機型圖片</th>
					<th>修改詳情</th>
				</tr>
				<%
					String[] layer = {"odd", "even"};
				int number = 2;
				%>
				<c:forEach var="chopvo" items="${chopList}">
					<tr class="<%=layer[number++ % 2]%>">
						<td class="chop_no">${chopvo.chop_no}</td>
						<td>${chopvo.chop_name}</td>
						<td>${chopvo.chop_price}</td>
						<td>
						<select id="chop-status-select"> 
						<c:choose>
							<c:when test="${chopvo.chop_status == '0'}">
								<option value="0" selected>閒置中</option>
								<option value="1">出勤中</option>
								<option value="2">已報廢</option>
							</c:when>
							<c:when test="${chopvo.chop_status == '1'}">
								<option value="0">閒置中</option>
								<option value="1"  selected>出勤中</option>
								<option value="2">已報廢</option>
							</c:when>
							<c:otherwise>
								<option value="0" selected>閒置中</option>
								<option value="1">出勤中</option>
								<option value="2"  selected>已報廢</option>
							</c:otherwise>
						</c:choose>
						</select>
						</td>
						<td style="display: none" id="chop_info">${chopvo.chop_info}</td>
						<td>
							<button class="showpic btn btn-info" value="${pageContext.request.contextPath}/ChopperServlet?chop_no=${chopvo.chop_no}&action=get_choppic">查看圖片</button>
						</td>
						<td><input class="update btn btn-primary" type="button" value="修改"></td>
						
					</tr>
				</c:forEach>
			</table>
		</c:when>
		<c:otherwise>
			<h3>查無資料</h3>
		</c:otherwise>
	</c:choose>
	<!-- Update Box Start-->
	<div class="update-display">
	<form class="update-form insert chop-update-form" method="post" action="${pageContext.request.contextPath}/ChopperServlet" enctype="multipart/form-data">
			<div class="close-icon">
				<i class="fas fa-times icon"></i>
			</div>
			<h3>
				機型編號：<b id="update-chop-no"></b>
			</h3>
			<label for="update-chopname">機型名稱</label> 
			<input type="text" name="update-chopname" id="update-chopname"  maxlength="20" autocomplete="off" required />
			<label for="update-chopprice">機型價格</label> 
			<input type="number" pattern="\d*" name="update-chopprice" id="update-chopprice" inputmode="numeric" autocomplete="off" required />
			<label for="update-chopinfo">介紹內容</label>
			<textarea name="update-chopinfo" id="update-chopinfo" maxlength="200" required></textarea>
			<label for="update-choppic">機型照片</label>
			<div class="pic-upload">
				<h6>
					<i class="icon fas fa-cloud-upload-alt" ></i>重新上傳照片
				</h6>
				<input type="file" accept="image/*" name="update-choppic" onchange="showImg(this)"/>
			</div>
			<div id="pic-area">
				<img id="show">
			</div>
			<input name="action" value="update_chopper" style="display: none">
			<input id="update-chopno" name="update-chopno" type="text" style="display: none">
			<button class="update-data" type="submit" style="width: 100%">更新資料</button>
	</form>
	</div>
	<!-- Update Box end -->
	
	<!-- Photo display -->
	<div class="album-display">
			<div class="close-icon">
				<i class="fas fa-times icon"></i>
			</div>
			<img class="album-showroom" src="">
	</div>
	<!-- Photo display end -->
	<script>
		
	function showImg(thisimg) {
		var file = thisimg.files[0];
		if (window.FileReader) {
			var fr = new FileReader();

			var showimg = document.getElementById('show');
			fr.onloadend = function(e) {
				showimg.src = e.target.result;
			};
			fr.readAsDataURL(file);
			showimg.style.display = 'block';
		}
	}
		
		$(".showpic").click(function() {
			$(".album-display").addClass("display-show")
			let src = $(this).val();
			console.log(src);
			$(".album-showroom").attr("src", src);
		})
		$(".icon").click(function() {
			$(this).parents(".display-show").removeClass("display-show");
			$(".album-showroom").attr("src", "");
		})
		$(".update").click(function() {
			$(".update-display").addClass("display-show")
			let tr = $(this).parents("tr");
			let children = tr.children();
			$("#update-chop-no").text(children.eq(0).text());
			$("#update-chopno").val(children.eq(0).text());
			$("#update-chopname").val(children.eq(1).text());
			$("#update-chopprice").val(children.eq(2).text());
			$("#update-chopinfo").text($("#chop_info").text());
		})
	</script>
</body>
</html>