<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.auth.model.*"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="com.title.model.*"%>
<%@ page import="com.dept.model.*"%>
<%
	String func_no = request.getParameter("func_no");  
%>

<%
    AuthService authSvc = new AuthService();
    List<AuthVO> list = authSvc.getAllByFunc(func_no);
    pageContext.setAttribute("list",list);
%>
<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" />
<jsp:useBean id="funcSvc" scope="page" class="com.func.model.FuncService" />
<jsp:useBean id="empSvc" scope="page" class="com.emp.model.EmpService" />

<html>
<head>
<%@ include file="/backend/files/backend_header.file"%>
<meta charset="UTF-8">
<title>所有權限資料 - listAllAuthByFunc.jsp</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
 <link rel="stylesheet" href="${pageContext.request.contextPath}/css/back/listAllEmp.css" />
<style>
table{
margin-top:40px;
width:70%;
}
</style>
</head>

<body>
	<%@ include file="/backend/files/backend_sidebar.file"%>
<button class="btn"><i class="fa fa-home">
                           <a href="<%=request.getContextPath()%>/backend/emp/protected/listAllEmp.jsp" >Back</a></i></button>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
<tr><th colspan="8"><h4>權限資料</h4></th></tr>
	<tr>
		<th>功能編號</th>
		<th>功能名稱</th>
		<th>員工編號</th>
		<th>員工姓名</th>
		<th>員工職位</th>
		<th>員工部門</th>	
		<th>刪除</th>
	</tr>
	<c:forEach var="authVO" items="${list}">
     <tr>
     <tbody>
        <td>${authVO.func_no}</td>
	    <td>${funcSvc.getOneFunc(authVO.func_no).func_name}</td>	
		<td>${authVO.emp_id}</td>
		<td>${empSvc.getOneEmp(authVO.emp_id).emp_name}</td>
		<td>${titleSvc.getOneTitle(empSvc.getOneEmp(authVO.emp_id).title_no).title}</td>
		<td>${deptSvc.getOneDept(empSvc.getOneEmp(authVO.emp_id).dept_no).dept}</td>	
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/auth/auth.do" style="margin-bottom: 0px;">
			     <input type="submit" value="Delete" class="btn btn-light btn-sm">
			     <input type="hidden" name="emp_id"  value="${authVO.emp_id}">
			     <input type="hidden" name="func_no"  value="${authVO.func_no}">
			     <input type="hidden" name="action"	value="delete"></FORM>
			</td>
		</tbody>
	</tr>
</c:forEach>
</table>
<%@ include file="/backend/files/backend_footer.file"%>
</body>
</html>