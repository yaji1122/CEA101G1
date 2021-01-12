<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="com.title.model.*"%>
<%@ page import="com.dept.model.*"%>
<%@ page import="com.func.model.*"%>
<%@ page import="com.auth.model.*"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*"%>



<jsp:useBean id="funcSvc" scope="page" class="com.func.model.FuncService" />
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>員工資料 - listOneEmp.jsp</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/back/listOneEmp.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/back/phone.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/back/Email.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>

<%@ include file="/backend/files/backend_header.file"%>

</head>
<body>
 	<%@ include file="/backend/files/backend_sidebar.file"%>
 	<%
	EmpVO empVO = (EmpVO) request.getAttribute("empVO");
String emp_date = request.getParameter("emp_date"); 
%>

<%-- 取出 對應的DeptVO物件--%>
<%
  DeptService deptSvc = new DeptService();
  DeptVO deptVO = deptSvc.getOneDept(empVO.getDept_no());
%>
<%-- 取出 對應的AuthVO物件--%>
<%
   String emp_id = request.getParameter("emp_id");
   AuthService authSvc = new AuthService();
   List<AuthVO> list = authSvc.getAllByEmp(emp_id);
   pageContext.setAttribute("list",list);
%>
<div>
   <div>
      <div>
         <div class="card" style="width:30rem;">
            <button class="btn"><i class="fa fa-home">
              <a id="Back" href="<%=request.getContextPath()%>/backend/emp/protected/listAllEmp.jsp"> Back</a></i></button>
                 <div class="listOne">
                   <div class="left">
                     <img src="${pageContext.request.contextPath}/emp/emp.do?emp_id=${empVO.emp_id}&action=getEmpPic">
                        <h3 class="mt-2"><%=empVO.getEmp_name()%></h3>
                        <h5 class="mt-1 clearfix"><%=empVO.getEmp_id()%></h5>
                        
                        <h4 class="mt-1 clearfix"><%=deptVO.getDept()%></h4>
                        <h4 class="mt-1 clearfix">${titleSvc.getOneTitle(empVO.title_no).title}</h4>
                        <span class="mt-4"><%=empVO.getEmp_city()%><%=empVO.getEmp_town()%><%=empVO.getEmp_address()%></span>
                        <div class="button">
                            <button class="neo-button"><i class=" ${(empVO.emp_status==2)?"fa fa-times-circle fa-2x":"fa fa-check-circle fa-2x"}"></i></button>
                            <input type="hidden" id="status" value="<%=empVO.getEmp_status()%>" />
                            <button class="neo-button" id="phone"><i class="fa fa-mobile fa-2x"></i></button>
                            <button class="neo-button" id="email()"><i class="fa fa-google fa-2x" ></i></button>
                         </div>
                         
                        <div class="bottom"><%=empVO.getEmp_date()%></div>
                        </div>
                           <div class="right">
                                    <div class="checkbox">                       
                                       <c:forEach var="authVO" items="${list}">
                                          <div>
		                                      <td>${authVO.func_no}</td>
		                                      <td>${funcSvc.getOneFunc(authVO.func_no).func_name}</td>	      
		                                  </div>
	                                    </c:forEach>
                                    </div>
                                    <div>
                             <FORM METHOD="post" id="change" ACTION="<%=request.getContextPath()%>/emp/emp.do" >
 		                     <input type="submit" class="btn btn-primary" style="width:250px" value="修改"> 
		                     <input type="hidden" name="emp_id" value="${empVO.emp_id}">
		                     <input type="hidden" name="action" value="getOne_For_Update"></FORM>  
                                   </div>
                                </div>
                            </div>  
                        </div>
                    </div> 
                 </div>
   <section class="section">
      <div class="side">
      <span class="close">&times;</span>
       <a id="alo-phoneIcon" data-toggle="modal" data-target="#callme-modal" class="alo-phone alo-green alo-show">
            <div class="alo-ph-circle"></div>
            <div class="alo-ph-circle-fill"></div>
            <div class="alo-ph-img-circle"><i class="fa fa-phone"></i></div>
            <span class="alo-ph-text"><%=empVO.getEmp_phone()%></span>
        </a>
      </div>
    </section>
         <div id="contactForm" class="contactForm">
            <a id="closeBtn" onclick="closeForm()" href="javascript:void();"><i class="fa fa-times"></i></a>
            <h3>Send Email</h3>
            <form
                method="POST"
                action="<%=request.getContextPath()%>/emp/emp.do"
                target="_self"
                id="form">
                <div id="emailname"><%=empVO.getEmp_email()%></div>
                <input type="hidden" value="${empVO.emp_email}" name="emp_email">
                <input type="text" id="emailname"  placeholder="Subject" name="subject" required />
                <textarea type="text" id="emailname" placeholder="Message" name="messages" required /></textarea>
                <input type="hidden" name="action" value="sendEmail">
                <button type="submit" id="btn" name="submit">Submit</button>
            </form>
        </div>     
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
$(document).ready(function () {
    $("#phone").click(function () {
    	$(".side").addClass("display-show")
    	$(".close").click(function(){
    		$(".side").removeClass("display-show");
    	})
    });
});
let closeForm = function () {
    let form = document.getElementById("contactForm");
    let open = true;

    form.style.display = "block";

    if (open == true) {
        form.style.display = "none";
        open = false;
    }
};

 </script>
 <%@ include file="/backend/files/backend_footer.file"%>
</body>
</html>