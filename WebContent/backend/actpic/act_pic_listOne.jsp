<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.actpic.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
   ActPicVO actPicVO = (ActPicVO) request.getAttribute("actPicVO"); //ActPicServlet.java(Concroller), 存入req的ActPicVO物件
%>

<html>
<head>
<title>活動照片資料 - act_pic_listOne.jsp</title>

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
</style>

<style>
  table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
  table tr>td>img{
     width:200px;
     height:150px;
     padding:5px;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>活動照片資料 - ListOneAct.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/actpic/act_pic_select_page.jsp"><img src="img/logo.png" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>活動照片編號</th>
		<th>活動項目編號</th>
		<th>活動照片</th>

	</tr>
	<tr>
	    <td><%=actPicVO.getActPicNo()%></td>
	    <td><%=actPicVO.getActEventNo()%></td>
		<td>
		     <img src="<%=request.getContextPath()%>/ActPicReaderServlet?actPicNo=<%=actPicVO.getActPicNo()%>&action=getOnePic">		   
		</td>
		
	</tr>
</table>

</body>
</html>