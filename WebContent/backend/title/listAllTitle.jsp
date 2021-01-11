<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.title.model.*"%>

<html>
<head>
<%@ include file="/backend/files/backend_header.file"%>
<meta charset="UTF-8">
<title>所有職位資料 - listAllTitle.jsp</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/back/list.css" />

</head>

<body>
	<%@ include file="/backend/files/backend_sidebar.file"%>
	<%
    List<TitleVO> list = titleSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<a href="<%=request.getContextPath()%>/backend/emp/protected/selectPage.jsp" >Back</a>
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
    <tr><th colspan="3" class="title">所有職位列表</th></tr>
	<tr>
		<th>職位編號</th>
		<th>職位名稱</th>
		<th>修改</th>
	</tr>
	<c:forEach var="titleVO" items="${list}">
     <tr>
		<td>${titleVO.title_no}</td>
		<td>${titleVO.title}</td>	
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/title/title.do" style="margin-bottom: 0px;">
			     <input type="submit" value="Update">
			     <input type="hidden" name="title_no"  value="${titleVO.title_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<section class="section">
   <div class="side">
   <h2>新增職位AddTitle</h2>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/title/title.do" onclick="return false" name="form1">

   <div class="margin_20">職位編號:
   <input type="TEXT" name="title_no" id ="title_no" size="45" />
   </div>
   <div class="margin_20">職位名稱:
   <input type="TEXT" name="title" id="title" size="45"/>
   </div>
   <div class="margin_20">
       <input type="hidden" name="action" value="insert">
       <input type="submit" value="送出新增" id="button">
   </div>
 </FORM>
</div>
</section>
        <script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
        <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
        <script src="sweetalert2.all.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/promise-polyfill"></script>
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
            	if($("#title_no").val()==""){
            		Swal.fire("Oops", "未填寫職位編號", "error");
            	}else if ($("#title").val()==""){
            		Swal.fire("Oops", "未填寫職位名稱", "error");
            	} else{
                    document.form1.submit();
            	}
            	
            	});

        </script>
        <%@ include file="/backend/files/backend_footer.file"%>
</body>
</body>
</html>