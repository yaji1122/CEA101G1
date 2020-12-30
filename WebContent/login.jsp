<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0" />
		<meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <title>後台登入</title>
        <link rel="icon" type="image/png" href="<%=request.getContextPath()%>/img/loading.png" />
 	    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
 	     <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/back/login.css" />
        <script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>  
    </head>
    <body>
        <div class="bg"><img src="<%=request.getContextPath()%>/img/bgimg.png" alt="" /></div>

        <div class="wrapper">
            <div class="login-window">
                <form action="index.html" method="GET">
                    <div class="login-box">
                        <div>
                            <img src="<%=request.getContextPath()%>/img/logo.png" alt="" />
                        </div>
                        <div class="group">
                            <input type="text" name="user-id" id="user-id" autocomplete="off" required />
                            <span class="highlight"></span>
                            <span class="bar"></span>
                            <label>EMPLOYEE ID</label>
                        </div>
                        <div class="group">
                            <input type="password" name="user-pwd" id="user-pwd" required />
                            <span class="highlight"></span>
                            <span class="bar"></span>
                            <label>&nbsp&nbspPASSWORD</label>
                        </div>
                        <div class="">
                            <button type="submit" class="draw" id="login">LOGIN</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
        <script src="${pageContext.request.contextPath}/js/back/login.js"></script>
    </body>
</html>
