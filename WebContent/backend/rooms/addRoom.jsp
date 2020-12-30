<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="rmtypeSvc" scope="page"
	class="com.roomtype.model.RoomTypeService" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增房間</title>
<style>
#rmtypeform, #rmtypeform2 {
	display:flex;
	flex-direction:column;
	justify-content: space-between;
	width: 80%;
	height:fit-content;
	padding-top:60px;
	margin:0 auto;
}
#manual-rmno {
	text-align-last:center;
}
</style>
</head>
<body>
	
	<form method="post" id="rmtypeform"
		action="<%=request.getContextPath()%>/RoomsServlet">
		<label for="rm_type">
			<p>客房類型</p></label> <select name="rm_type" required>
				<c:forEach var="rmtypevo" items="${rmtypeSvc.all}">
					<option value="${rmtypevo.rm_type}">${rmtypevo.type_name}</option>
				</c:forEach>
		</select> 
		
		<input name="action" value="insert_room" style="display: none">
		<button type="submit" class="sendData1 btn btn-danger">一鍵快速新增</button>
	</form>
	
	<form method="post" id="rmtypeform2"
		action="<%=request.getContextPath()%>/RoomsServlet">
		<label for="rm_type">
			<p>客房類型</p> 
			</label><select name="rm_type2" id="rm_type2" required>
					<option disabled selected>請選擇房型</option>
				<c:forEach var="rmtypevo" items="${rmtypeSvc.all}">
					<option value="${rmtypevo.rm_type}">${rmtypevo.type_name}</option>
				</c:forEach>
		</select> 
		
		<label for="rm_type">
			<p>自訂編號</p> </label>
			<input id="manual-rmno" type="text" name="rm_no" minlength="3" maxlength="3" pattern="\d*" autocomplete="off" placeholder="房型編號+2位數字" required>
		
		<input name="action" value="insert_room_manually" style="display: none">
		<button id="sendData2" type="submit" class="sendData2 btn btn-warning">新增自訂房號</button>
	</form>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
	<script>
		$("#rm_type2").change(function(){
			$("#manual-rmno").val($(this).val());
		})
		$("#sendData2").click(function(e){
			if ($("#manual-rmno").val().length < 3) {
				e.preventDefault();
				errorFire("請輸入三位數字");
			}
			if ($("#rm_type2").val() == null) {
				e.preventDefault();
				errorFire("請選擇要新增的房型");
			}
		}) 
		
		function errorFire(msg) {
			Swal.fire({
                position: "top-end",
                icon: "error",
                title: msg,
                showConfirmButton: false,
                timer: 1500,
            });
		}
	</script>
</body>
</html>