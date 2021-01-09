<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.func.model.*"%>

<%
  FuncVO funcVO = (FuncVO) request.getAttribute("funcVO");
%>


<!DOCTYPE html>
<html>
<head>
<%@ include file="/backend/files/backend_header.file"%>
<meta charset="UTF-8">
<title>功能資料修改 - update_func_input.jsp</title>

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
<%@ include file="/backend/files/backend_sidebar.file"%>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/func/func.do" name="form1">
<table>
	<tr>
		<th>功能編號:</th>
		<th>功能名稱:<input type="submit" value="送出修改" id="right"></th>
	</tr>
	<tr>
	    <td><%=funcVO.getFunc_no()%></td>
		<td><input type="TEXT" name="func_name" size="45" value="<%=funcVO.getFunc_name()%>" /></td>
	</tr>
    <tr>
     <input type="hidden" name="action" value="update">
     <input type="hidden" name="func_no" value="<%=funcVO.getFunc_no()%>">
    </tr>

</table>
</FORM>
<%@ include file="/backend/files/backend_footer.file"%>
</body>
</html>