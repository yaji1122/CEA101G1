<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="/backend/files/backend_header.file"%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<meta charset="UTF-8">
<title>所有員工資料 - listAllEmpByTitle.jsp</title>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
 <link rel="stylesheet" href="${pageContext.request.contextPath}/css/back/listAllEmp.css" />

</head>
<body>
<%@ include file="/backend/files/backend_sidebar.file"%>
<%
	String title_no = request.getParameter("title_no");  
%>

<%
EmpService empSvc = new EmpService();
List <EmpVO> list = empSvc.getOneByTitle(title_no);
pageContext.setAttribute("list",list);
%>
<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" />
<jsp:useBean id="authSvc" scope="page" class="com.auth.model.AuthService" />
<jsp:useBean id="funcSvc" scope="page" class="com.func.model.FuncService" />

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
	   <tr><th colspan="9"><h4>所有員工資料表</h4></th></tr>
	      <tr>
              <th>員工編號</th>
              <th>員工姓名</th>
              <th>員工狀態</th>
              <th>員工職位</th>
              <th>員工部門</th>
              <th>員工權限</th>
              <th>到職日</th>
              <th>查詢</th>
              <th>修改</th>
          </tr>
		<%@ include file="pages/page1" %> 
<c:forEach var="empVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	<tr>
	<tbody>
	    <td>${empVO.emp_id}</td>
		<td>${empVO.emp_name}</td>
		<td>
			<c:choose>
		<c:when test="${empVO.emp_status.equals('0')}">未啟用</c:when>
		<c:when test="${empVO.emp_status.equals('1')}">已啟用</c:when>
		<c:when test="${empVO.emp_status.equals('2')}">已離職</c:when>
		<c:otherwise>狀態異常, 聯繫IT</c:otherwise>
			</c:choose></td>
		<td>${titleSvc.getOneTitle(empVO.title_no).title}</td>
		<td>
		<c:forEach var="deptVO" items="${deptSvc.all}">
            <c:if test="${empVO.dept_no==deptVO.dept_no}">
	            ${deptVO.dept_no}【${deptVO.dept}】
             </c:if>
          </c:forEach>
		</td>
	    <td>${funcSvc.getOneFunc(authSvc.getOneAuth(empVO.emp_id).func_no).func_name}</td>			
		<td>${empVO.emp_date}</td>
	   <td>
	    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do" style="margin-bottom: 0px;">
		    <input type="submit" value="More" class="btn btn-outline-secondary btn-sm">
		    <input type="hidden" name="emp_id"  value="${empVO.emp_id}">
		    <input type="hidden" name="action"	value="getOne_For_Display"></FORM>
	    </td>
	   <td>
	    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do" style="margin-bottom: 0px;">
		    <input type="submit" value="Update" class="btn btn-light btn-sm"">
		    <input type="hidden" name="emp_id"  value="${empVO.emp_id}">
		    <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
	    </td>
	    </tbody>
	 </tr>
	 </c:forEach>
</table>
<%@ include file="pages/page2" %>
		<%@ include file="/backend/files/backend_footer.file"%>
</body>
</html>