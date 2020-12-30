<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.dept.model.*"%>

<%
  DeptVO deptVO = (DeptVO) request.getAttribute("deptVO"); 
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>部門資料修改 - update_dept_input.jsp</title>

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
<body>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/dept/dept.do" name="form1">
<table>
	<tr>
		<th>部門編號:</th>
		<th>部門名稱:<input type="submit" value="送出修改" id="right"></th>
	</tr>
	<tr>
		<td><%=deptVO.getDept_no()%></td>
		<td><input type="TEXT" name="dept" size="45" value="<%=deptVO.getDept()%>" /></td>
	</tr>
	<tr>
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="dept_no" value="<%=deptVO.getDept_no()%>">
    </tr>
</table>
</FORM>
</body>
</html>