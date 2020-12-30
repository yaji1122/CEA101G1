<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.act.model.*"%>

<%
     ActVO actVO = (ActVO) request.getAttribute("actVO"); 
    //ActServlet.java(Concroller), 存入req的ActVO物件 (包括幫忙取出的ActEventVO物件, 也包括輸入資料錯誤時的empActTYpeVO物件)
%>

<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>

    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/back/backend-act_add.css" type="text/css" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <title>修改活動 - addAct.jsp</title>
</head>

<body>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

        <div class="logo">
			<img src="<%=request.getContextPath()%>/img/logo.png">
		</div>
		<div class="form-title">
			<img src="<%=request.getContextPath()%>/img/loading.png">
			<h2>活動查詢</h2>
		</div>


<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/act/ActServlet" name="form1">
<div id="form">
             <li>
                <label for="inputEmail4" class="alert alert-primary" role="alert">活動編號: (ACT_ODNo):</label>
                <input type="text" class="form-control" id="input-Act-no" placeholder="ACT_ODNo" name="actNo" size="45"
                size="45" required placeholder="請輸入活動編號" value="<%= (actVO==null)? "" : actVO.getActNo()%>" />
            </li>
            <li>
                <label for="inputPassword4" class="alert alert-danger">活動項目編號:(ACT_Event_No):</label>
                <input type="text" class="form-control" id="input-Act-Event-No"  name="actEventNo" size="45"
                 placeholder="請選擇活動項目編號" value="<%= (actVO==null)? "" :actVO.getActEventNo()%>"  />
            </li>
            <li>
                <label class="alert alert-primary" role="alert">活動名稱: (ACT_Name):</label>
                <input type="text" class="form-control" id="input-Act-Namel4" name="actName" size="45" 
                 placeholder="請輸入活動名稱" value="<%= (actVO==null)? "" :actVO.getActName()%>"/>
            </li>
            <li>
                <label class="alert alert-danger">活動狀態: (ACT_Status):</label>
                <input type="text" class="form-control" aria-label="Amount (to the nearest dollar)" 
                name="actStatus" size="45" value="<%= (actVO==null)? "" :actVO.getActStatus()%>" />
            </li>
            <li>
                <label class="alert alert-primary" role="alert">活動報名日期:</label>
                <input type="date" class="form-control" id="input-Act-Namel4" name="actRegTime" size="45"
                 value="<%= (actVO==null)? "" :actVO.getActRegTime()%>" />
            </li>
            <li>
                <label class="alert alert-primary" role="alert">活動截止日期:</label>
                <input type="date" class="form-control" id="input-Act-Namel4" name="deadLine" size="45"
                 value="<%= (actVO==null)? "" :actVO.getDeadLine()%>" />
            </li>
             <li>
                <label class="alert alert-primary" role="alert">活動舉辦日期:</label>
                <input type="date" class="form-control" id="input-Act-Namel4" name="actDate" size="45"
                 value="<%= (actVO==null)? "" :actVO.getActDate()%>" />
            </li>
            <li>
                <label class="alert alert-danger">活動時段: (ACT_Time):</label>
                <input type="text" class="form-control" aria-label="Amount (to the nearest dollar)" placeholder="活動時段請填入整點數字,如:1600"
                name="actTime" size="45" value="<%= (actVO==null)? "" :actVO.getActTime()%>" />
            </li>
            <li>
                <label class="alert alert-danger">會員姓名: (ACT_Status):</label>
                <input type="text" class="form-control" aria-label="Amount (to the nearest dollar)" placeholder="請填入姓名"
                name="participant" size="45" value="<%= (actVO==null)? "" :actVO.getParticipant()%>" />
            </li>
            <li>
                <label class="alert alert-primary" role="alert">活動價格:</label>
                <input type="text" class="form-control" id="input-Act-Namel4" name="actPrice" size="45"
                 placeholder="請填入數字" value="<%= (actVO==null)? "" :actVO.getActPrice()%>" />
            </li>
      </div> 
        
	    <div class="message" style="margin-left:600px; margin-top:500px;">
	              <input type="hidden" name="action" value="update">
                  <input type="hidden" name="ActNo" value="<%=actVO.getActNo()%>">
                  <button type="submit" class="btn btn-primary">送出修改</button>
                  <button type="reset" class="btn btn-primary">重設</button>
                  <button type="button" class="btn btn-outline-danger"
                  onclick="location.href='<%=request.getContextPath()%>/back-end/act/backend-act_select_page.jsp'">
                                                 回首頁</button>
         </div>
    
</FORM>>
<!-- 頁面內容結束 -->
	
	<script src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
	<script src="<%=request.getContextPath()%>/js/back/index-back.js"></script>
    
     </body>
</html>