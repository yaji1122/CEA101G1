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
						密碼:<input type="password" name="emp_pwd" id="emp_pwd" value="${empVO.emp_pwd}"
							placeholder="Password" />
							<label><input type="checkbox" id="show_password" />顯示密碼</label>
					</h5>
					<h5 class="mt-2">
						職位: &nbsp&nbsp<%=titleVO.getTitle()%></h5>
					<h5 class="mt-2">
						部門: &nbsp&nbsp<%=deptVO.getDept()%></h5>
					<h5 class="mt-2">
						到職日: &nbsp&nbsp<%=empVO.getEmp_date()%></h5>
					<h5 class="mt-2">
						電話:<input type="text" name="emp_phone" placeholder="Phone"
							value="${empVO.emp_phone}">
					</h5>
					<h5 class="mt-2">
						信箱:<input type="email" name="emp_email" placeholder="Email"
							value="${empVO.emp_email}">
					</h5>
					<input type="hidden" name="emp_status" id="emp" value="1" />
					<div class="grid">
					<h5 class="mt-2">地址: </h5>
						<input type="text" name="emp_city" placeholder="City" value="${empVO.emp_city}">
						<input type="text" name="emp_town" placeholder="Town" value="${empVO.emp_town}">
					</div>
					
					<span class="mt-4">&emsp;&emsp;&nbsp;&nbsp;<input type="text" name="emp_address"
						size="24" placeholder="Address" value="<%=empVO.getEmp_address()%>"></span>
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
        <script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
        <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script type="text/javascript">
$(function(){
	// 先取得 #password1 及產生一個文字輸入框
	var $password = $('#emp_pwd'), 
		$passwordInput = $('<input type="text" name="' + $password.attr('name') + '" class="' + $password.attr('className') + '" />');
 
	// 當勾選顯示密碼框時
	$('#show_password').click(function(){
		// 如果是勾選則...
		if(this.checked){
			// 用 $passwordInput 來取代 $password
			// 並把 $passwordInput 的值設為 $password 的值
			$password.replaceWith($passwordInput.val($password.val()));
		}else{
			// 用 $password 來取代 $passwordInput
			// 並把 $password 的值設為 $passwordInput 的值
			$passwordInput.replaceWith($password.val($passwordInput.val()));
		}
	});
});
</script>
</body>
</html>