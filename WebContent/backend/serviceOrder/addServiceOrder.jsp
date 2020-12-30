<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.service_order.model.*"%>

<%
	ServiceOrderVO serviceOrderVO = (ServiceOrderVO) request.getAttribute("serviceOrderVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style>
form {
	max-width: 500px;
}
</style>
<title>訂單新增</title>

</head>
<body>
	<h3>訂單新增</h3>
	<form METHOD="post"
		ACTION="${pageContext.request.contextPath}/ServiceOrderServlet"
		name="form1">
		<div class="form-group">
			<label for="mb_id">會員編號:</label> <input type="text"
				class="form-control" id="mb_id" name="mb_id"
				value="<%=(serviceOrderVO == null) ? "MEM0000001" : serviceOrderVO.getMb_id()%>">
		</div>
		<div class="form-group">
			<label for="od_status">訂單狀態編號:</label> <input type="text"
				class="form-control" id="od_status" name="od_status"
				value="<%=(serviceOrderVO == null) ? "1" : serviceOrderVO.getOd_status()%>">
		</div>
		<div class="form-group">
			<label for="rm_no">客房編號:</label> <input type="text"
				class="form-control" id="rm_no" name="rm_no"
				value="<%=(serviceOrderVO == null) ? "101" : serviceOrderVO.getRm_no()%>">
		</div>
		<div class="form-group">
			<label for="serv_no">服務編號:</label> <input type="text"
				class="form-control" id="serv_no" name="serv_no"
				value="<%=(serviceOrderVO == null) ? "001" : serviceOrderVO.getServ_no()%>">
		</div>
		<div class="form-group">
			<label for="serv_time">預約時間:</label> <input type="text"
				class="form-control" id="serv_time" name="serv_time"
				value="<%=(serviceOrderVO == null) ? "2020-12-25 00:00:00.0" : serviceOrderVO.getServ_time()%>">
		</div>
		<div class="form-group">
			<label for="serv_count">服務人數:</label> <input type="text"
				class="form-control" id="serv_count" name="serv_count"
				value="<%=(serviceOrderVO == null) ? "1" : serviceOrderVO.getServ_count()%>">
		</div>
		
		<div class="form-group">
			<label for="total_price">訂單總額:</label> <input type="text"
				class="form-control" id="total_price" name="total_price"
				value="<%=(serviceOrderVO == null) ? 1000 : serviceOrderVO.getTotal_price()%>">
		</div>
		<input type="hidden" name="action" value="insert"> <input
			class="btn btn-primary" type="submit" value="送出新增">
	</form>
</body>
</html>