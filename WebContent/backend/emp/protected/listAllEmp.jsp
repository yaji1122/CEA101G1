<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>

<%
	String emp_id = request.getParameter("emp_id");  
    String emp_name = request.getParameter("emp_name");
%>

<%
    EmpService empSvc = new EmpService();
    List<EmpVO> list = empSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" />
<jsp:useBean id="authSvc" scope="page" class="com.auth.model.AuthService" />
<jsp:useBean id="funcSvc" scope="page" class="com.func.model.FuncService" />

<!DOCTYPE html>
<html>
<head>
<%@ include file="/backend/files/backend_header.file"%>
<title>所有員工資料 - listAllEmp.jsp</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
 <link rel="stylesheet" href="${pageContext.request.contextPath}/css/back/listAllEmp.css" />
 <link rel="stylesheet" href="${pageContext.request.contextPath}/css/back/Searching.css" /> 
 
</head>

<body>
	<%@ include file="/backend/files/backend_sidebar.file"%>
<button class="btn"><i class="fa fa-home">
                           <a href="<%=request.getContextPath()%>/backend/emp/protected/selectPage.jsp" >Home</a></i></button>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
 <a href='<%=request.getContextPath()%>/backend/emp/protected/addEmp.jsp'>
    <img src="${pageContext.request.contextPath}/img/Add.png" width="50" id="Add"/>
 </a>
	<table>
	   <tr><th colspan="7"><h4>所有員工資料表</h4></th><th colspan="2" id="search">進階搜尋</th></tr>
	      <tr>
              <th>員工編號</th>
              <th>員工姓名</th>
              <th>員工狀態</th>
              <th>員工職位</th>
              <th>員工部門</th>
              <th>員工權限</th>
              <th>到職日</th>
              <th>查詢</th>
              <th>修改</th>
          </tr>
		<%@ include file="pages/page1" %> 
<c:forEach var="empVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	<tr>
	<tbody>
	    <td>${empVO.emp_id}</td>
		<td>${empVO.emp_name}</td>
		<td>
			<c:choose>
		<c:when test="${empVO.emp_status.equals('0')}">未啟用</c:when>
		<c:when test="${empVO.emp_status.equals('1')}">已啟用</c:when>
		<c:when test="${empVO.emp_status.equals('2')}">已離職</c:when>
		<c:otherwise>狀態異常, 聯繫IT</c:otherwise>
			</c:choose></td>
		<td>${titleSvc.getOneTitle(empVO.title_no).title}</td>
		<td>
		<c:forEach var="deptVO" items="${deptSvc.all}">
            <c:if test="${empVO.dept_no==deptVO.dept_no}">
	            ${deptVO.dept_no}【${deptVO.dept}】
             </c:if>
          </c:forEach>
		</td>
	    <td>${funcSvc.getOneFunc(authSvc.getOneAuth(empVO.emp_id).func_no).func_name}</td>			
		<td>${empVO.emp_date}</td>
	   <td>
	    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do">
		    <input type="submit" value="More" class="btn btn-outline-secondary btn-sm">
		    <input type="hidden" name="emp_id"  value="${empVO.emp_id}">
		    <input type="hidden" name="action"	value="getOne_For_Display"></FORM></FORM>
	    </td>
	   <td>
	    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do" style="margin-bottom: 0px;">
		    <input type="submit" value="Update" class="btn btn-light btn-sm">
		    <input type="hidden" name="emp_id"  value="${empVO.emp_id}">
		    <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
	    </td>
	    </tbody>
	 </tr>
	 </c:forEach>
</table>
<section class="section">
            <div class="side">
                <div>
                    <h3 class="text-dark">Searching</h3>
                    <p class="border-bottom pb-4">with multiple method</p>
                    <ul class="list-unstyled">
                        <li>                      
                            <div class="pl-3">
                                <h6 class="text-dark">選擇員工編號/姓名</h6>
                                <ul class="list-unstyled">
                                    <li>
                                    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do" > 
                                    <select size="1" name="emp_id">
                                     <c:forEach var="empVO" items="${list}" > 
                                        <option value="${empVO.emp_id}">${empVO.emp_id}
                                      </c:forEach>   
                                     </select>
                                    <input type="hidden" name="action" value="getOne_For_Display">
                                    <input type="submit" value="send" class="btn btn-outline-light btn-sm"> 
                                    </FORM>                             
                                    </li>
                                     <li>
                                     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do" >
                                     <select size="1" name="emp_id">
                                      <c:forEach var="empVO" items="${list}" > 
                                         <option value="${empVO.emp_id}">${empVO.emp_name}
                                      </c:forEach>   
                                     </select>
                                     <input type="hidden" name="action" value="getOne_For_Display">
                                     <input type="submit" value="send" class="btn btn-outline-light btn-sm"> 
                                     </FORM>
                                    </li>
                                </ul>
                            </div>
                        </li>
                       <li>
                          <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do" >
                            <div class="pl-3">
                                <h6 class="text-dark">選擇部門</h6>
                                <ul class="list-unstyled">
                                  <select size="1" name="dept_no">
                                     <c:forEach var="deptVO" items="${deptSvc.all}" > 
                                       <option value="${deptVO.dept_no}">${deptVO.dept_no} ${deptVO.dept}
                                     </c:forEach>   
                                  </select>
                                  <input type="hidden" name="action" value="getAllByDept">
                                  <input type="submit" value="send" class="btn btn-outline-light btn-sm">
                                </ul>
                            </div>
                          </FORM>
                        </li>

                        <li>
                          <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/auth/auth.do" >
                            <div class="pl-3">
                                <h6 class="text-dark">選擇權限</h6>
                                <ul class="list-unstyled">
                                  <li>
                                    <select size="1" name="func_no">
                                      <c:forEach var="funcVO" items="${funcSvc.all}" > 
                                         <option value="${funcVO.func_no}">${funcVO.func_no} ${funcVO.func_name}</option>
                                      </c:forEach>   
                                    </select>
                                    <input type="hidden" name="action" value="getOneByFunc">
                                    <input type="submit" value="send" class="btn btn-outline-light btn-sm">
                                  </li>
                                </ul>
                            </div>
                          </FORM>
                        </li>
                        <li>
                          <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do" >
                            <div class="pl-3">
                                <h6 class="text-dark">選擇職位</h6>
                                <ul class="list-unstyled">
                                    <li>
                                      <select size="1" name="title_no">
                                        <c:forEach var="titleVO" items="${titleSvc.all}" > 
                                          <option value="${titleVO.title_no}">${titleVO.title_no} ${titleVO.title}
                                        </c:forEach>   
                                      </select>
                                      <input type="hidden" name="action" value="getOneByTitle">
                                      <input type="submit" value="send" class="btn btn-outline-light btn-sm">
                                    </li>
                                </ul>
                            </div>
                           </FORM>
                        </li>
                    </ul>
                </div>
            </div>
        </section>
<%@ include file="pages/page2" %>

        <script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
        <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
        <script>
            $(document).ready(function () {
                $("#search").click(function () {
                	if($(".side").hasClass("display-show")) {
                		 $(".side").removeClass("display-show");
                	} else {
                		 $(".side").addClass("display-show");
                	}                  
                });
            });
        </script>
               <%@ include file="/backend/files/backend_footer.file"%>
</body>
</html>