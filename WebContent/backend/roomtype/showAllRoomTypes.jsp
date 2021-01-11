<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.roomtype.model.*"%>
<%@ page import="java.util.List"%>
<%
RoomTypeService rmtypeSvc = new RoomTypeService();
List<RoomTypeVO> rmtypeList = rmtypeSvc.getAll();
pageContext.setAttribute("rmtypeList", rmtypeList);
%>
<!DOCTYPE html>
<html>
<head>
<title>所有房型 - All Room Types</title>
</head>
<style>
</style>
<body>
	<c:if test="${not empty msgs}">
		<ul>
			<c:forEach var="message" items="${msgs}">
				<li style="color: red; list-style: none;">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<c:choose>
		<c:when test="${rmtypeList.size() > 0}">
			<table>
				<tr>
					<th>房型編號</th>
					<th>房型名稱</th>
					<th>英文名稱</th>
					<th>房型數量</th>
					<th>房型價格(人/晚)</th>
					<th>最多可入住人數</th>
					<th>內容修改</th>
					<th>房型圖庫</th>
				</tr>
				<%
					String[] layer = {"odd", "even"};
				int number = 2;
				%>
				<c:forEach var="rmtypevo" items="${rmtypeList}">
					<tr class="<%=layer[number++ % 2]%>">
						<td class="rmtypeno">${rmtypevo.rm_type}</td>
						<td>${rmtypevo.type_name}</td>
						<td>${rmtypevo.type_eng_name}</td>
						<td>${rmtypevo.rm_qty}</td>
						<td>\$${rmtypevo.rm_price}</td>
						<td>${rmtypevo.rm_capacity}</td>
						<td style="display: none">${rmtypevo.rm_info_title}</td>
						<td style="display: none">${rmtypevo.rm_info}</td>
						<td><input class="update btn btn-outline-danger btn-sm" type="button" value="修改">
						</td>
						<td>
							<button class="showpic btn btn-outline-info btn-sm"
								value="<%=request.getContextPath() %>/backend/roompic/showAllRoomPic.jsp?rmtype-pic=${rmtypevo.rm_type}">查看圖片</button>
						</td>
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
	<form class="update-form" method="post" action="${pageContext.request.contextPath}/RoomTypeServlet">
			<div class="close-icon">
				<i class="fas fa-times icon"></i>
			</div>
			<h3>
				房型編號：<b id="update-rmtype-no"></b>
			</h3>
			<label for="update-typename">房型名稱</label> 
			<input type="text"
				name="update-typename" id="update-typename" class="rm-input" max="9"
				min="1" autocomplete="off" required />
				<label for="update-typeengname">英文名稱</label>
				<input type="text" name="update-typeengname" class="rm-input"
				id="update-typeengname" maxlength="10" autocomplete="off" required />
				<label for="update-rmprice">房型價格</label> 
				<input type="text" name="update-rmprice" id="update-rmprice" inputmode="numeric"
				class="rm-input" autocomplete="off" required />
				<label for="update-rmcap">最大入住人數</label> 
				<select name="update-rmcap" id="update-rmcap" class="rm-select" required>
					<option value="2">2</option>
					<option value="4">4</option>
					<option value="6">6</option>
				</select>
			<label for="update-rminfotitle">內容標頭</label>
			<input name="update-rminfotitle" class="rm-input" id="update-rminfotitle"
				maxlength="40" placeholder="輸入40字以內標題" required>
			<label for="update-rminfo">介紹內容</label>
			<textarea name="update-rminfo" class="rm-input" id="update-rminfo" maxlength="200" required></textarea>
			<input name="action" value="update_rm_type" style="display: none">
			<input id="update-rmtype" name="update-rmtype" type="text" style="display: none">
			<button class="update-data" type="submit" style="width: 100%">更新資料</button>
	</form>
	</div>
	<!-- Update Box end -->
	
	<!-- Photo display -->
	<div class="album-display">
			<div class="close-icon">
				<i class="fas fa-times icon"></i>
			</div>
			<iframe class="album-showroom"> </iframe>
	</div>
	<!-- Photo display end -->
	<script>
		$(".showpic").click(function() {
			$(".album-display").addClass("display-show")
			let src = $(this).val();
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
			$("#update-rmtype").val(children.eq(0).text());
			$("#update-rmtype-no").text(children.eq(0).text());
			$("#update-typename").val(children.eq(1).text());
			$("#update-typeengname").val(children.eq(2).text());
			$("#update-rmprice").val(children.eq(4).text());
			$("#update-rmcap").val(children.eq(5).text()).change();
			$("#update-rminfotitle").val(children.eq(6).text());
			$("#update-rminfo").val(children.eq(7).text());
		})
	</script>
</body>
</html>