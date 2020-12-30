<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.title.model.*"%>

<%
  TitleVO titleVO = (TitleVO) request.getAttribute("titleVO"); 
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>職位資料修改 - update_title_input.jsp</title>

<style>
   table {
                box-sizing: border-box;
                overflow: hidden;
                margin: 4em auto;
                border-collapse: collapse;
                min-width: 23em;
                width: 50%;
                max-width: 56em;
                border-radius: 0.5em;
                box-shadow: 0 0 0.5em #000;
                position: relative;
                z-index: 1;
            }

            tbody {
                background: white;
                flex-direction: column;
                color: #000;
            }

            th,
            td {
                border: 1px solid gray;
                text-align: center;
            }

            th {
                background: black;
                color: white;
                text-align: center;
            }
            .update {
                width: 90%;
                height: 30%;
                margin: 0 auto;
                position: fixed;
                z-index: -99;
                opacity: 0;
                box-sizing: border-box;
            }
            .display-show {
                z-index: 99;
                opacity: 1;
            }
            #right{
             float:right;
            }
</style>

</head>
<body bgcolor='white'>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/title/title.do" name="form1">
<table>
	<tr>
		<th>職位編號:</th>
		<th>職位名稱:<input type="submit" value="送出修改" id="right"></th>		
	</tr>
	<tr>
		<td><%=titleVO.getTitle_no()%></td>
		<td><input type="TEXT" name="title" size="45" value="<%=titleVO.getTitle()%>" /></td>
	</tr>
</table>
<input type="hidden" name="action" value="update">
<input type="hidden" name="title_no" value="<%=titleVO.getTitle_no()%>">
</FORM>
</body>
</html>