<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.auth.model.*"%>


<%
  String emp_id = request.getParameter("emp_id"); 
  EmpVO empVO = (EmpVO) request.getAttribute("empVO");
  AuthService authSvc = new AuthService();
  List<AuthVO> list = authSvc.getAllByEmp(emp_id);
  List<String> listStr = new ArrayList<>();
  for (AuthVO authvo: list){
	  listStr.add(authvo.getFunc_no());
  }
  
  pageContext.setAttribute("listStr",listStr);
%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="/backend/files/backend_header.file"%>
<meta charset="UTF-8">
<title>員工資料修改 - update_emp_input.jsp</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/back/update_emp_input.css" />
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
</head>
<body>
	<%@ include file="/backend/files/backend_sidebar.file"%>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do" name="form1" enctype="multipart/form-data">
<div>
   <div>
      <div>
         <div class="card" style="width:30rem;">
            <button class="btn"><i class="fa fa-home">
              <a id="back" href="<%=request.getContextPath()%>/emp/emp.do?emp_id=${empVO.emp_id}&action=getOne_For_Display"> Back</a></i></button>
                <div class="listOne">
                   <div class="left">
                      <img src="${pageContext.request.contextPath}/emp/emp.do?emp_id=${empVO.emp_id}&action=getEmpPic">
                      <input type="file" name="emp_pic" value="${empVO.emp_pic}" />
                       <p class="mt-2"><input type="text" name="emp_name" value="${empVO.emp_name}" placeholder="Name"></p>
                        <p class="mt-1 clearfix"><%=empVO.getEmp_id()%></p>
                        <input type="hidden" name="emp_pwd" size="45" value="${empVO.emp_pwd}" />
                        <p class="mt-1 clearfix">選擇職位
                          <select size ="1" name="title_no">
	                        <c:forEach var="titleVO" items="${titleSvc.all}">
				              <option value="${titleVO.title_no}" ${(empVO.title_no==titleVO.title_no)?'selected':'' } >${titleVO.title}
			                </c:forEach>
		                  </select>
		                </p>
		               <jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> 
		               <p class="mt-1 clearfix"> 選擇部門
		               <select size="1" name="dept_no">
			              <c:forEach var="deptVO" items="${deptSvc.all}">
				            <option value="${deptVO.dept_no}" ${(empVO.dept_no==deptVO.dept_no)?'selected':'' } >${deptVO.dept}
			              </c:forEach>
		               </select></p>
                       <span class="mt-4"><input type="text" name="emp_phone" placeholder="Phone" value="${empVO.emp_phone}"></span>
                       <span class="mt-4"><input type="email" name="emp_email" placeholder="Email" value="${empVO.emp_email}"></span>
	                   <span class="mt-4"><input type="text" name="emp_city" size= "10" placeholder="City" value="${empVO.emp_city}"></span>
	                   <span class="mt-4"><input type="text" name="emp_town" size= "10" placeholder="Town" value="${empVO.emp_town}"></span>
	                   <span class="mt-4"><input type="text" name="emp_address" size= "24" placeholder="Address" value="${empVO.emp_address}"></span>
                       <div id="status"><span class="mt-4">員工狀態:</span>
		                 <input type="radio" name="emp_status" id="emp" size="45" value="1" checked/>已啟用
		                 <input type="radio" name="emp_status" id="emp" size="45" value="2"/>已離職
		               </div>
                       <div>
                          <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do" > 
                          <input type="hidden" name="emp_id" value="${empVO.emp_id}">
                          <input type="hidden" name="emp_date" value="${empVO.emp_date}">
                          <input type="hidden" name="action" value="update">
                          <input type="submit" class="btn btn-primary" style="width:250px" value="送出修改" >
                          </FORM>    
                       </div>
                     
                        
                       <div class="bottom"><%=empVO.getEmp_date()%></div>
                      </div>
                    <div class="right">
                     <div class="checkbox">
                        <jsp:useBean id="funcSvc" scope="page" class="com.func.model.FuncService" />
	                      <div>員工權限:</div>
                            <c:forEach var="funcVO" items="${funcSvc.all}"> 
                             <div>
                              <input type="checkbox" value="${funcVO.func_no}" name="function" <c:if test="${listStr.contains(funcVO.func_no)}"> checked </c:if>  >${funcVO.func_no} ${funcVO.func_name} 
                             </div>
                            </c:forEach> 
                       </div>
                     
                    </div>
                  </div>  
                </div>
              </div> 
            </div>
          </div>
          </FORM>
    <%@ include file="/backend/files/backend_footer.file"%>
</body>
</html>