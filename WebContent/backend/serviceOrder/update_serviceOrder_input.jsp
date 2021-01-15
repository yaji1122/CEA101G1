<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.service_order.model.*"%>

<%
	ServiceOrderVO serviceOrderVO = (ServiceOrderVO) request.getAttribute("serviceOrderVO"); //ServiceOrderServlet.java (Concroller) 存入req的serviceOrderVO物件 (包括幫忙取出的serviceOrderVO, 也包括輸入資料錯誤時的serviceOrderVO物件)
%>
<jsp:useBean id="servicesSvc" scope="page"
	class="com.services.model.ServicesService" />
	
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- <link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
	integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous"> -->
	<%@ include file="/frontend/files/commonCSS.file"%>
<style>
.form-body {
	padding: 5%;
	width: 800px;
    margin-left: auto;
    margin-right: auto;
}
#preview img{
max-width:300px;

}

</style>
<%@ include file="/backend/files/backend_header.file"%>
<title>修改訂單</title>
</head>
<body>
<%@ include file="/backend/files/backend_sidebar.file"%>
<div class="container">
		<div class="form-body">
	<form METHOD="post"
		ACTION="${pageContext.request.contextPath}/ServiceOrderServlet"
		name="form1">
		<div class="form-group">
			服務訂單編號:<%=serviceOrderVO.getServ_odno()%>
		</div>
		<div class="form-group">
			訂房單號:<%=serviceOrderVO.getBk_no()%>
		</div>
		<div class="form-group">
			<label for="od_status">訂單狀態:</label>
			<select name="od_status" id="od_status">
				<option value="0" ${serviceOrderVO.od_status == 0 ? 'selected':''}>未完成</option>
				<option value="1" ${serviceOrderVO.od_status == 1 ? 'selected':''}>已完成</option>
				<option value="2" ${serviceOrderVO.od_status == 2 ? 'selected':''}>已取消</option>
			</select> 
			<%--  <input type="text"
				class="form-control" id="od_status" name="od_status"
				value="<%=serviceOrderVO.getOd_status()%>" required> --%>
		</div>
		
		<div class="form-group">
			<label for="serv_no">服務名稱:</label> 
			<select name="serv_no"
				id="serv_no">
				<c:forEach var="servicesVO" items="${servicesSvc.all}">
					<option value="${servicesVO.serv_no}"
						${(serviceOrderVO.serv_no==servicesVO.serv_no)?'selected':'' }>${servicesVO.serv_name}</option>
				</c:forEach>
			</select>
			
			<%-- <input type="text"
				class="form-control" id="serv_no" name="serv_no"
				value="<%=serviceOrderVO.getServ_no()%>"> --%>
		</div>
		<%-- <div class="form-group">
			<label for="serv_time">預約時間:</label> <input type="text"
				class="form-control f_date1" id="serv_time" name="serv_time"
				value="<%=serviceOrderVO.getServ_time()%>" required>
		</div> --%>
		
		<%-- <div class="form-group">
			<label for="serv_time">預約時間:</label> <input type="text"
				class="form-control" id="serv_time" name="serv_time"
				value="<%=serviceOrderVO.getServ_time()%>" required>
		</div> --%>
		
		<div class="form-group">
			<label for="serv_count">服務人數:</label> <input type="number"
				class="form-control" id="serv_count" name="serv_count"
				value="<%=serviceOrderVO.getServ_count()%>" required>
		</div>
		<%-- <div class="form-group">
			<label for="locations">服務場所:</label> <input type="text"
				class="form-control" id="locations" name="locations"
				value="<%=serviceOrderVO.getLocations()%>" required>
		</div> --%>
		<div class="form-group">
			<label for="total_price">訂單總額:</label> <input type="number"
				class="form-control" id="total_price" name="total_price"
				value="<%=serviceOrderVO.getTotal_price()%>" required>
		</div>
		<input type="hidden" name="action" value="update"> 
		<input type="hidden" name="serv_odno" value="<%=serviceOrderVO.getServ_odno()%>">
		<input type="hidden" name="bk_no" value="<%=serviceOrderVO.getBk_no()%>">
		<input type="hidden" name="serv_time" value="<%=serviceOrderVO.getServ_time()%>">
		<input type="hidden" name="locations" value="<%=serviceOrderVO.getLocations()%>">
		<input class="btn btn-primary" type="submit" value="送出修改">
	</form>
	</div>
	</div>
	<!-- <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
		integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"
		integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s"
		crossorigin="anonymous"></script> -->
    <script type="text/javascript"
		src="${pageContext.request.contextPath}/js/jquery.datetimepicker.full.min.js"></script>
    <script>
		$.datetimepicker.setLocale('zh'); // kr ko ja en
		$('.f_date1').datetimepicker({
			theme : '', //theme: 'dark',
			timepicker : true, //timepicker: false,
			step : 60, //step: 60 (這是timepicker的預設間隔60分鐘)
			format : 'Y-m-d H:i',
			value : new Date(),
		//disabledDates:    ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
		//startDate:	        '2017/07/10',  // 起始日
		//minDate:           '-1970-01-01', // 去除今日(不含)之前
		//maxDate:           '+1970-01-01'  // 去除今日(不含)之後
		});
	</script>
	<%@ include file="/backend/files/backend_footer.file"%>
	<%@ include file="/frontend/files/commonJS.file"%>
</body>
</html>