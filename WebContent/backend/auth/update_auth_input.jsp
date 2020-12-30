<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.func.model.*"%>
<%@ page import="com.auth.model.*"%>
<%@ page import="java.util.*"%>

<%
  AuthVO authVO = (AuthVO) request.getAttribute("authVO"); 
  AuthService authSvc = new AuthService();
  List<AuthVO> list = authSvc.getAll();
  pageContext.setAttribute("list",list);
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Emp權限資料修改 - updateAuth.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>權限資料修改 - update_auth_input.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/backend/auth/selectAuth.jsp">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/auth/auth.do">
<table>
	<tr>
		<td>員工編號:<font color=red><b>*</b></font></td>
		<td><%=authVO.getEmp_id()%></td>
	</tr>
    <jsp:useBean id="funcSvc" scope="page" class="com.func.model.FuncService" />
    <tr><td>功能名稱:</td>
        <td>${funcSvc.getOneFunc(authVO.func_no).func_name}</td>
    </tr>
    <tr><td>功能名稱checkbox:</td><td>
    
         <c:forEach var="funcVO" items="${funcSvc.all}" > 
          <input type="checkbox" value="${funcVO.func_no}" name="function">${funcVO.func_no} ${funcVO.func_name}
           
         </c:forEach>   

       </td>
    </tr>
</table>

<br>
<input type="hidden" name="action" value="insert">
<input type="hidden" name="emp_id" value="<%=authVO.getEmp_id()%>">
<input type="submit" value="送出修改">
</FORM>
<script>
let func_no = 
</script>
</body>
</html>