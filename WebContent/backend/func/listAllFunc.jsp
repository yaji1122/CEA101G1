<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.func.model.*"%>

<%
    FuncService funcSvc = new FuncService();
    List<FuncVO> list = funcSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<meta charset="UTF-8">
<title>所有功能資料 - listAllFunc.jsp</title>
 <link rel="stylesheet" href="${pageContext.request.contextPath}/css/back/list.css" />
 <style>
 table{
  width:50%;
 }
 img{
	width:50px;
	position:fixed;
	left:20%;
}
 </style>
</head>

<body bgcolor='white'>

<a href="<%=request.getContextPath()%>/backend/emp/protected/selectPage.jsp">Back</a>
</table>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<div class="add"><img id="add"src="<%=request.getContextPath()%>/img/Add.png"></div>

<table>
    <tr><th colspan="4" class="title">所有權限列表</th></tr>
	<tr>
		<th>權限編號</th>
		<th>權限名稱</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<c:forEach var="funcVO" items="${list}">
     <tr>
		<td>${funcVO.func_no}</td>
		<td>${funcVO.func_name}</td>	
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/func/func.do" style="margin-bottom: 0px;">
			     <input type="submit" value="Update">
			     <input type="hidden" name="func_no"  value="${funcVO.func_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/func/func.do" style="margin-bottom: 0px;">
			     <input type="submit" value="Delete">
			     <input type="hidden" name="func_no"  value="${funcVO.func_no}">
			     <input type="hidden" name="action"	value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<!--這邊開始是add-->
<section class="section">
   <div class="side">
   <h2>新增權限AddFunc</h2>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/func/func.do" onclick="return false" name="form1">

   <div class="margin_20">權限編號:
   <input type="TEXT" name="func_no" id ="func_no" size="45" />
   </div>
   <div class="margin_20">權限名稱:
   <input type="TEXT" name="func_name" id="func_name" size="45"/>
   </div>
   <div class="margin_20">
       <input type="hidden" name="action" value="insert">
       <input type="submit" value="送出新增" id="button">
   </div>
 </FORM>
</div>
</section>
<!--add結束 -->
        <script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
        <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script>
            $(document).ready(function () {
                $("#add").click(function () {
                	if($(".side").hasClass("display-show")) {
                		 $(".side").removeClass("display-show");
                	} else {
                		 $(".side").addClass("display-show");
                		 $("table").css("background-color","black");
                	}                  
                });
            });
            document.getElementById("button").addEventListener("click",function(){
            	if($("#func_no").val()==""){
            		swal("Oops", "未填寫權限編號", "error");
            	}else if ($("#func_name").val()==""){
            		swal("Oops", "未填寫權限名稱", "error");
            	} else{
            		swal("Success", "新增成功", "Success");
                    document.form1.submit();
            	}
            	
            	});

        </script>
</body>
</html>