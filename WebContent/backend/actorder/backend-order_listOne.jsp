<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/backend/files/backend_header.file"%>
<!-- 加入常用 css -->
<title>Insert title here</title>
</head>
<body>
	<%@ include file="/backend/files/backend_sidebar.file"%>
	<!-- 頁面內容開始-->
	
			<%@ include file="/backend/actorder/act_order_listOne.jsp"%>

	<!-- Example single danger button -->

	<%@ include file="/backend/files/backend_footer.file"%>
	<!-- 加入常用 js -->
	<script src="${pageContext.request.contextPath}/js/backend.js"></script>
</body>
</html>