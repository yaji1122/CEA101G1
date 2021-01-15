<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.service_type.model.*"%>

<%
	ServiceTypeVO serviceTypeVO = (ServiceTypeVO) request.getAttribute("serviceTypeVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@ include file="/frontend/files/commonCSS.file"%>
<style>
form {
	max-width: 500px;
}

.form-body {
	padding: 5%;
	width: 800px;
    margin-left: auto;
    margin-right: auto;
}
</style>
<%@ include file="/backend/files/backend_header.file"%>
<title>服務類型新增</title>

</head>
<body>
<%@ include file="/backend/files/backend_sidebar.file"%>
	<div class="container">
		<div class="form-body">
			<h3>服務類型新增</h3>
			<form METHOD="post"
				ACTION="${pageContext.request.contextPath}/ServiceTypeServlet"
				name="form1">
				<div class="form-group">
					<label for="serv_type_no">服務類型編號:</label> <input type="text"
						class="form-control" id="serv_type_no" name="serv_type_no"
						value="<%=(serviceTypeVO == null) ? "1" : serviceTypeVO.getServ_type_no()%>"
						required maxlength="1" pattern="\d+">
				</div>

				<div class="form-group">
					<label for="serv_type_name">服務類型名稱:</label> <input type="text"
						class="form-control" id="serv_type_name" name="serv_type_name"
						value="<%=(serviceTypeVO == null) ? "美體美容" : serviceTypeVO.getServ_type_name()%>"
						required>
				</div>

				<input type="hidden" name="action" value="insert"> <input
					class="btn btn-primary" type="submit" value="送出新增">
			</form>
		</div>
	</div>
	<%@ include file="/backend/files/backend_footer.file"%>
	<%@ include file="/frontend/files/commonJS.file"%>
</body>

</html>