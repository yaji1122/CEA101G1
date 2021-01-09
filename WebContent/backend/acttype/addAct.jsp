<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.acttype.model.*"%>

<%
    ActTypeVO actTypeVO = (ActTypeVO) request.getAttribute("actTypeVO"); 
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>新增活動種類 - addAct.jsp</title>
</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>新增活動種類 - addAct.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/acttype/select_page.jsp"><img src="img/logo.png" width="250" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/acttype/ActTypeServlet" name="form1">
<table>
	<tr>
		<td>活動種類編號:</td>
		<td><input type="TEXT" name="ActTypeNo" size="45" required placeholder="請輸入活動編號"
			 value="<%= (actTypeVO==null)? "" : actTypeVO.getActTypeNo()%>" /></td>
	</tr>
	<tr>
		<td>活動種類名稱:</td>
		<td><input type="TEXT" name="ActTypeName" size="45" required placeholder="請輸入活動名稱"
			 value="<%= (actTypeVO==null)? "" :actTypeVO.getActTypeName()%>" /></td>
	</tr>

	

</table>
     <br>
         <input type="hidden" name="action" value="insert">
         <input type="submit" value="送出新增"></FORM>
     </body>
</html>