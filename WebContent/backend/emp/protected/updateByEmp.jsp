<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="com.title.model.*"%>
<%@ page import="com.dept.model.*"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="/backend/files/backend_header.file"%>
<title>員工資料修改 - update_emp_input.jsp</title>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color: red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color: red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/back/updateByEmp.css" />
</head>
<style>
/*div.card {
	position: absolute;
	left:50%;
	top:50%;
	transform: translate(-50%, -50%);	
	border-radius: 20px;
	box-shadow: 20px 20px 60px #cacaca, -20px -20px 60px #ffffff;
	border: none;
	width: 650px !important;
	padding: 20px;
}

div.card input {
	height: 30px;
	padding: 5px;
}

div.card .right {
	position: absolute;
	display: flex;
	flex-direction: column;
	right: 10px;
	top: 10px;
	width: 250px;
	height: 250px;
	border-radius: 30%;
	background: linear-gradient(145deg, #d6d6d6, #ffffff);
	box-shadow: 20px 20px 60px #cacaca, -20px -20px 60px #ffffff;
}

div.card .right .upload-pic {
	margin-top: 250px;
}*/
</style>
<body>
	<%@ include file="/backend/files/backend_sidebar.file"%>
	<%
		EmpVO empVO = (EmpVO) session.getAttribute("empVO");
	TitleVO titleVO = titleSvc.getOneTitle(empVO.getTitle_no());
	DeptService deptSvc = new DeptService();
	DeptVO deptVO = deptSvc.getOneDept(empVO.getDept_no());
	%>
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do" id="emp-form"
		name="form1" enctype="multipart/form-data">

		<div class="card" style="width: 30rem;">
			<div class="listOne">
				<div class="left">

					<h5 class="mt-1 clearfix"><%=empVO.getEmp_id()%></h5>
					<h5 class="mt-2">
						姓名:<input type="text" name="emp_name" value="${empVO.emp_name}"
							placeholder="Name">
					</h5>
					<h5 class="mt-2">
						密碼:<input type="password" name="emp_pwd" value="${empVO.emp_pwd}"
							placeholder="Password" />
					</h5>
					<h5 class="mt-2">
						職位: &nbsp&nbsp<%=titleVO.getTitle()%></h5>
					<h5 class="mt-2">
						部門: &nbsp&nbsp<%=deptVO.getDept()%></h5>
					<h5 class="mt-2">
						到職日: &nbsp&nbsp<%=empVO.getEmp_date()%></h5>
					<h5 class="mt-2">
						電話:<input type="text" name="emp_phone" placeholder="Phone"
							value="0${empVO.emp_phone}">
					</h5>
					<h5 class="mt-2">
						信箱:<input type="email" name="emp_email" placeholder="Email"
							value="${empVO.emp_email}">
					</h5>
					<input type="hidden" name="emp_status" id="emp" value="1" />
					<h5 class="mt-2">
						地址: <div class="grid">
						<input type="text" name="emp_city" placeholder="City" value="${empVO.emp_city}">
						<input type="text" name="emp_town" placeholder="Town" value="${empVO.emp_town}">
						    </div>
					</h5>
					<span class="mt-4">&emsp;&emsp;<input type="text" name="emp_address"
						size="24" placeholder="Address" value="${empVO.emp_address}"></span>
					<div>
						<input type="hidden" name="title_no" value="${empVO.title_no}">
                          <input type="hidden" name="dept_no" value="${empVO.dept_no}">
                          <input type="hidden" name="emp_id" value="${empVO.emp_id}">
                          <input type="hidden" name="emp_date" value="${empVO.emp_date}">
                          <input type="hidden" name="action" value="update_FromEmp">
                          <input type="submit" class="btn btn-primary" style="width:250px" value="送出修改" >
					</div>
				</div>
				<div class="right">
					<input type="hidden" name=> <img
						src="${pageContext.request.contextPath}/emp/emp.do?emp_id=${empVO.emp_id}&action=getEmpPic">
					<input type="file" name="emp_pic" class="upload-pic"
						value="${empVO.emp_pic}" />
				</div>
			</div>
		</div>
	</FORM>
	<%@ include file="/backend/files/backend_footer.file"%>
</body>
</html>