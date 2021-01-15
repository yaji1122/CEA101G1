<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.members.model.*"%>
<%@ page import="java.util.List"%>
<%
MembersService memberSvc = new MembersService();
List<MembersVO> members = memberSvc.getAll();
pageContext.setAttribute("members", members);
%>

<!DOCTYPE html>
<html>
<head>
<title>會員一覽 Show All Members</title>
<style>
th {
	position: sticky;
	top: 0;
}

.membertable table {
	margin: 0 auto;
	min-width: 95%;
}

.membertable thead th {
	position: sticky;
}

.membertable {
	height: 80vh;
	overflow: scroll;
}

.showmsg {
	width: 100%;
	height: 40px;
	margin: 0px auto;
	padding: 5px;
	position: relative;
}

.showmsg p {
	position: absolute;
	right: 5vw;
	bottom: 0px;
}

.showmsg p b {
	color: crimson;
}
.showmsg h3 {
width:fit-content;
}
#room_number {
	text-align-last: center;
	border-radius: 5px;
}

.member-info {
	padding: 10px;
	position: fixed;
	left: 50%;
	top: 20%;
	transform: translate(-50%);
	background-color: white;
	border: 1px solid black;
	height: fit-content;
	width: 60vw;
	height: fit-content;
}

.member-info h6 {
	text-align: left;
	width: fit-content;
	padding-left: 10px;
}

.mbpicdiv {
	height: 300px;
}

img {
	max-height:100%;
	border-radius: 10px;
}
#pic-area {
    display: flex;
    flex-direction: row;
    justify-content: center;
}
h5 {
	border-bottom: 1px solid grey;
}
div.member-detail-info  {
	 display: flex;
    flex-direction: column;
    width:100%;
}
div.member-detail-info i {
	margin-right: 10px;
	width: 25px;
	height:25px;
	text-align: center;
	line-height: 25px;
}
div.member-detail-info h6 {
	padding: 0px;
}
.member-detail {
	display:flex;
	flex-direction: row;
	justify-content:space-evenly;
}
</style>
</head>
<body>
	<div>
		<div class="showmsg">
			<h3>
				<%
					String msg = (String) request.getAttribute("msg");
				if (msg != null) {
				%>
				<%=msg%>
				<%}%>
			</h3>
			<p class="membertotal">
				當前查詢會員共<b><%=members.size()%></b>人
			</p>
		</div>
		<div class="membertable">
			<table>
				<thead class="firstTr">
					<tr>
						<th><input type="text" id="mb_id" maxlength="10"
							placeholder="會員編號" style="text-transform: uppercase" autocomplete="off"></th>
						<th><input type="text" id="mb_name" maxlength="50"
							placeholder="會員姓名" style="text-transform: uppercase" autocomplete="off"></th>
						<th><input type="text" id="mb_email" maxlength="50"
							placeholder="E-MAIL" style="text-transform: uppercase" autocomplete="off"></th>
						<th>擁有積分</th>
						<th>帳號狀態</th>
						<th>會員詳情</th>
						<th>資料修改</th>
					</tr>
				</thead>
				<%
					String[] layer = { "odd", "even" };
				int number = 2;
				%>
				<tbody>
					<%-- <%@ include file="/backend/page.file"%> --%>
					<c:forEach var="member" items="${members}">
						<tr class="<%=layer[number++ % 2]%> ">
							<td class="mb_id" id="${member.mb_id}">${member.mb_id}</td>
							<td class="mb_name">${member.mb_name}</td>
							<td class="mb_email">${member.mb_email}</td>
							<td class="mb_point">${member.mb_point}</td>
							<td class="mb_status status-${member.mb_status}"><c:choose>
									<c:when test="${member.mb_status.equals('0')}">未啟用</c:when>
									<c:when test="${member.mb_status.equals('1')}">已啟用</c:when>
									<c:when test="${member.mb_status.equals('2')}">入住中</c:when>
									<c:when test="${member.mb_status.equals('3')}">已鎖定</c:when>
									<c:otherwise>已註銷</c:otherwise>
								</c:choose></td>
							<td class="mb_bd" style="display: none">${member.mb_bd.toString()}</td>
							<td class="mb_phone" style="display: none">${member.mb_phone}</td>
							
							<td class="mb_city" style="display: none">${member.mb_city}</td>
							<td class="mb_town" style="display: none">${member.mb_town}</td>
							<td class="mb_address" style="display: none">${member.mb_address}</td>
							<td class="create_date" style="display: none">${member.create_date.toString()}</td>
							<td><button class="show-memberdetail btn btn-primary">查看</button></td>
							<td><button class="show-memberupdate btn btn-warning">修改</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<%-- <%@ include file="/backend/page2.file"%> --%>
	<div class="info-display member-info">
		<div class="close-icon">
			<i class="fas fa-times icon"></i>
		</div>
		<h5 style="text-align: center">
			<i class="far fa-gem"></i>會員編號：<b id="detail-mbid" style="color: crimson"></b>
		</h5>
		<div class="member-detail">
			<div class="member-detail-info">
				<h6>
					<i class="fas fa-caret-right"></i>會員姓名：<b id="detail-mbname"></b>
				</h6>
				<h6>
					<i class="fas fa-birthday-cake"></i>會員生日：<b id="detail-mbbd"></b>
				</h6>
				<h6>
					<i class="fas fa-phone"></i>聯絡電話：<b id="detail-mbphone"></b>
				</h6>
				<h6>
					<i class="fas fa-calendar-week"></i>帳號創建日期：<b id="detail-createdate"></b>
				</h6>
				<h6>
					<i class="far fa-building"></i>居住城市：<b id="detail-mbcity"></b>
				</h6>
				<h6>
					<i class="fas fa-igloo"></i>居住鄉鎮：<b id="detail-mbtown"></b>
				</h6>
				<h6>
					<i class="fas fa-road"></i>詳細地址：<b id="detail-mbaddress"></b>
				</h6>
			</div>
			<div class="mbpicdiv">
				<img id="detail-mbpic" src="">
			</div>
		</div>
	</div>
	<!-- Update display -->
	<div class="update-display">
	<div class="close-icon">
		<i class="fas fa-times icon"></i>
	</div>
		<form method="post" class="update-form" id="update-member-form" enctype="multipart/form-data"
			action="${pageContext.request.contextPath}/MembersServlet">
			<h3>
				會員編號：<b id="update-mbID"></b>
			</h3>
			<label for="update-mbname">會員姓名</label>
				<input type="text" name="update-mbname"
				id="update-mbname" class="rm-input" max="9" min="1"
				autocomplete="off" required />
			 <label for="update-mbname">會員生日</label>
				<input type="text" name="update-mbbd" id="update-mbbd" pattern="\d{4}[-]{1}\d{2}[-]\d{2}"
				class="rm-input" max="9" min="1" autocomplete="off" required 
				oninvalid="this.setCustomValidity('請輸入正確日期')"
  				oninput="this.setCustomValidity('')"/>
			 <label for="update-mbphone">聯絡號碼</label>
				<input type="text" name="update-mbphone" pattern="\d+"
				id="update-mbphone" class="rm-input" max="9" min="1"
				autocomplete="off" 
				oninvalid="this.setCustomValidity('請輸入數字')"
  				oninput="this.setCustomValidity('')"
				required />
			 <label for="update-mbcity">居住縣市</label>
				<input type="text" name="update-mbcity"
				id="update-mbcity" class="rm-input" max="9" min="1"
				autocomplete="off" required />
			 <label for="update-mbtown">居住鄉鎮</label>
				<input type="text" name="update-mbtown"
				id="update-mbtown" class="rm-input" max="9" min="1"
				autocomplete="off" required />
			 <label for="update-mbaddress">詳細地址</label>
				<input type="text" name="update-mbaddress"
				id="update-mbaddress" class="rm-input" max="9" min="1"
				autocomplete="off" required />
			 	<div class="pic-upload">
			 	<h6>
					<i class="icon fas fa-cloud-upload-alt"></i>上傳個人照片
				</h6>
				<input type="file" name="update-mbpic" onchange="showImg(this)"
				id="update-mbpic" class="rm-input"
				autocomplete="off" />
				</div>
				<div id="pic-area">
						<img id="show">
				</div>
			 <input name="action" value="update_member" style="display: none">
		     <input id="update-mbid" name="update-mbid" type="text"
				style="display: none">
			<button class="send-data" type="submit" style="width: 100%">更新資料</button>
		</form>
	</div>
	<script src="${pageContext.request.contextPath}/js/jquery.datetimepicker.full.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/back/member-backend.js"></script>
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
		$(document).ready(function(){
			
			$("#update-mbbd").datetimepicker({
				timepicker:false,
				format:"Y-m-d",
				lang:"zh-TW"
			})
			
			$("#update-member-form").submit(function(e){
				e.preventDefault();
				var myform = document.getElementById("update-member-form");
			    var fd = new FormData(myform);
			    $.ajax({
			        url: "<%=request.getContextPath()%>/MembersServlet",
			        data: fd,
			        cache: false,
			        processData: false,
			        contentType: false,
			        type: 'POST',
			        success: function (msg) {
			        	if (msg == "success") {
							Swal.fire({
								position: "center",	
								title:"修改成功",
								icon:"success",
								showConfirmButton: false,
								timeout: 1000,
							})
							setTimeout(function(){
								window.location.reload();
							}, 1000)
						}
			        }
			    });
			})
		})
		
	</script>
</body>
</html>