<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta charset="UTF-8" />
    <meta name="description" content="Sona Template" />
    <meta name="keywords" content="Sona, unica, creative, html" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Diamond Resort</title>
    <%@ include file="/frontend/files/commonCSS.file"%>
    <!-- Css Styles -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/front/Activity.css" />
    
<title>Insert title here</title>
</head>

<body style="background-image:url('<%=request.getContextPath()%>/img/activity/bg4.png');">

     <div class="black" style="height:70px; background-color: rgb(0, 0, 0);">
     
		<%@ include file="/frontend/files/loginCSS.file"%>
		<%@ include file="/frontend/files/login.file"%>
		<%@ include file="/frontend/files/header.file"%>
		<%@ include file="/frontend/files/loginbox.file"%>
		
	</div>
    <!-- Header Section End -->
    <div id="wrapper" >
        <div class="land">
            <img src="<%=request.getContextPath()%>/img/sheet4.png">
            <figure>
                <figcaption><a href="<%=request.getContextPath()%>/frontend/activity/Land_page.jsp" >Land</a></figcaption>
            </figure>
        </div>
        <div class="sea">
            <img src="<%=request.getContextPath()%>/img/land1.png">
            <figure>
                <figcaption><a href="<%=request.getContextPath()%>/frontend/activity/Ocean_page.jsp">Ocean</a></figcaption>
            </figure>
        </div>
        <div class="spc">
            <img src="<%=request.getContextPath()%>/img/diving4.png">
            <figure>
                <figcaption><a href="<%=request.getContextPath()%>/frontend/activity/Special_page.jsp">Special</a></figcaption>
            </figure>
        </div>
    </div>
    <!-- Footer Section Start -->
    
    <!-- Footer Section End -->
    <!-- Js Plugins -->
    <%@ include file="/frontend/files/commonJS.file"%>
    
    
</body>

</html>