<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/backend/files/backend_header.file"%>
<!-- 加入常用 css -->
<title>Insert title here</title>
</head>
<body>
	<%@ include file="/backend/files/backend_sidebar.file"%>
	<!-- 頁面內容開始-->

		<div class="logo">
			<img src="<%=request.getContextPath()%>/img/logo.png" style="margin-left: 460px;">
		</div>
		<div class="form-title">
			<img src="<%=request.getContextPath()%>/img/loading.png">
			<h2 style="margin-left: 80px;">新增照片</h2>
		</div>
    <!-- Example single danger button -->
    <%@ include file="/backend/actpic/act_pic_add.jsp"%>

	<%@ include file="/backend/files/backend_footer.file"%>
	<!-- 加入常用 js -->
	<script src="${pageContext.request.contextPath}/js/backend.js"></script>
</body>
</html>