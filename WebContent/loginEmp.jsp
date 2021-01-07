<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>

<html>
    <head>
        <title>loginEmp</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/back/loginEmp.css" />
    </head>

    <body>
        <div class="container">
            <div class="form form--login">
                <div class="form--heading">Welcome back!</div>
                <form autocomplete="off"  method="post" action="<%=request.getContextPath()%>/emp/login.do">
                    <input type="text" placeholder="EMPID" name="emp_id"/>
                    <input type="password" placeholder="Password" name="emp_pwd">
                    <button class="button">Login</button>
                </form>
            </div>
            <%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
        </div>
        <script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
    </body>
</html>
