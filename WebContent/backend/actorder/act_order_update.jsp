<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.actorder.model.*"%>

<%
     ActOrderVO actOrderVO = (ActOrderVO) request.getAttribute("actOrderVO"); 
    //ActServlet.java(Concroller), 存入req的ActVO物件 (包括幫忙取出的ActEventVO物件, 也包括輸入資料錯誤時的empActTYpeVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta charset="UTF-8" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/back/backend-add.css" type="text/css" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui.css">
    <title>修改訂單 - addAct.jsp</title>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/actorder/ActOrderServlet" name="form1">
     <div id="form">
             <li>
                <label for="inputEmail4" class="alert alert-primary" role="alert">活動訂單編號: (ACT_ODNo):</label>
                <input type="text" class="form-control" id="input-Act-no" placeholder="ACT_ODNo" name="actOdno" 
                size="45" required placeholder="請輸入活動訂單編號" value="<%= (actOrderVO==null)? "" : actOrderVO.getActOdno()%>" />
            </li>
            <li>
                <label for="inputPassword4" class="alert alert-danger">活動編號 (ACT_No):</label>
                <input type="text" class="form-control" id="input-Act-Event-No"  name="actNo" size="45"
                 placeholder="ACT_No" value="<%= (actOrderVO==null)? "" :actOrderVO.getActNo()%>" /> 
            </li>
            <li>
                <label class="alert alert-primary" role="alert">訂房編號: (BK_NO):</label>
                <input type="text" class="form-control" id="input-Act-Namel4" name="bkNo" size="45" 
                 placeholder="BK_NO" value="<%= (actOrderVO==null)? "" :actOrderVO.getBkNo()%>" />
            </li>
            <li>
                <label class="alert alert-danger">訂單日期: (OD_Time):</label>
                <input type="date" class="form-control" aria-label="Amount (to the nearest dollar)" 
                name="odTime" size="45" value="<%= (actOrderVO==null)? "" :actOrderVO.getOdTime()%>" />
            </li>
            <li>
                <label class="alert alert-primary" role="alert">活動狀態:</label>
                <input type="text" class="form-control" id="input-Act-Namel4" name="odStatus" size="45"
                 placeholder="ACT_Status" value="<%= (actOrderVO==null)? "" :actOrderVO.getOdStatus()%>" />
            </li>
            <li>
                <label class="alert alert-primary" role="alert">參加人數:</label>
                <input type="text" class="form-control" id="input-Act-Namel4" name="ppl" size="45"
                 placeholder="Participant" value="<%= (actOrderVO==null)? "" :actOrderVO.getPpl()%>" />
            </li>
            <li>
                <label class="alert alert-primary" role="alert">訂單總價格:</label>
                <input type="text" class="form-control" id="input-Act-Namel4" name="totalPrice" size="45"
                 placeholder="Total_Price" value="<%= (actOrderVO==null)? "" :actOrderVO.getTotalPrice()%>" />
                </div>
            </li>
      </div> 
        
	    <div class="message" style="margin-top:320px;">
	              <input type="hidden" name="action" value="update">
	              <input type="hidden" name="actOdno" value="<%=actOrderVO.getActOdno()%>">
                  <button type="submit" class="btn btn-primary">送出修改</button>
                  <button type="reset" class="btn btn-primary">重設</button>
                  <button type="button" class="btn btn-outline-danger"
                  onclick="location.href='<%=request.getContextPath()%>/back-end/actorder/backend-order_select_page.jsp?action=getAll'">
                                                 回首頁</button>
         </div>
    
</FORM>
  <!-- 頁面內容結束 -->

  
    <script src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
	<script src="<%=request.getContextPath()%>/js/index-back.js"></script>
    <script src="https://kit.fontawesome.com/dc3c868026.js" crossorigin="anonymous"></script>
     </body>
</html>