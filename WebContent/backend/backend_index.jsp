<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
	String authErrorMsg = (String) session.getAttribute("authErrorMsg");
	if (authErrorMsg != null){
		pageContext.setAttribute("msg", authErrorMsg);
		session.removeAttribute("authErrorMsg");
	}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/backend/files/backend_header.file"%> <!-- 加入常用 css -->
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" />
<title>戴蒙後台作業系統</title>
</head>
<body>
	<%@ include file="/backend/files/backend_sidebar.file"%>
	



	<%@ include file="/backend/files/backend_footer.file"%> <!-- 加入常用 js -->
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
	<script>
	//WS
	var MyPoint = "/NotifyWS";
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
	var webSocket = new WebSocket(endPointURL);
	webSocket.onmessage = function(event) {
		var jsonObj = JSON.parse(event.data);
		let type = jsonObj.type;
		let odno = jsonObj.odno;
		Command: toastr["info"]( type + "編號為：" + odno, "有一筆新的訂單")
	};
		
	
		
	
	
	<c:if test="${msg != null}">
		Command: toastr["warning"]("請向主管申請開通權限", " 權限不足")
	</c:if>
	toastr.options = {
			  "closeButton": true,
			  "debug": false,
			  "newestOnTop": true,
			  "progressBar": false,
			  "positionClass": "toast-top-right",
			  "preventDuplicates": false,
			  "onclick": null,
			  "showDuration": "300",
			  "hideDuration": "1000",
			  "timeOut": "10000",
			  "showEasing": "swing",
			  "hideEasing": "linear",
			  "showMethod": "fadeIn",
			  "hideMethod": "fadeOut"
			}
	</script>
</body>
</html>