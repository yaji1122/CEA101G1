<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.actpic.model.*"%>

<%
    ActPicVO actPicVO = (ActPicVO) request.getAttribute("actPicVO"); 
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>新增活動照片 - addAct.jsp</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/back/backend.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/back/backend-addpic.css">


</head>
<body bgcolor='white'>



<%-- 錯誤表列 --%>


<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/actpic/ActPicServlet" name="form1" enctype="multipart/form-data">
<div id="form">
            <li>
                <label for="inputEmail4" class="alert alert-primary" role="alert">活動照片編號: (Pic_No):</label>
                <input type="text" class="form-control" id="input-Act-no" placeholder="Pic_No"
                name="ActPicNo" size="45" value="<%= (actPicVO==null)? "000" : actPicVO.getActPicNo()%>" />
            </li>
            <li>
                <label for="inputPassword4" class="alert alert-danger">活動項目編號: (ACT_Event_No):</label>
                <input type="text" class="form-control" id="input-Act-Event-No" placeholder="ACT_Event_No"
                name="ActEventNo" size="45" value="<%= (actPicVO==null)? "" :actPicVO.getActEventNo()%>" /> 
            </li>
        </div>
        <div class="Pic_area">
              <li class="pic">
                <p class="alert alert-primary">活動照片:(Pic_Load):</p>
                 <img  id="show"  src="#">
                </li>
                 <input  onchange="showImg(this)"  type="file" class="form-control" 
                 aria-label="Amount (to the nearest dollar)" name="ActPic" size="45"
                 value="<%= (actPicVO==null)? "" :actPicVO.getActPic()%>" >
             
        </div>
        <div class="btn_area" style="margin:60px auto; margin-left:500px;">
                  <input type="hidden" name="action" value="insert">
                  <button type="submit" class="btn btn-primary">新增</button>
                  <button type="reset" class="btn btn-primary">重設</button>
                  <button type="button" class="btn btn-outline-danger" 
                  onclick="location.href='<%=request.getContextPath()%>/back-end/actorder/backend-order_select_page.jsp?action=getAll'">
                                                回首頁</button>
                  <button type="button" class="btn btn-outline-danger" 
                  onclick="location.href='<%=request.getContextPath()%>/back-end/actpic/backend-act_pic_listAll.jsp?action=getAll'">
                                                全部照片</button>
         </div>
 </div>
        
</FORM>
   
	<script>
	function showImg(thisimg) {
		var file = thisimg.files[0];
		if(window.FileReader) {
			var fr = new FileReader();
			
			var showimg = document.getElementById('show');
			fr.onloadend = function(e) {
			showimg.src = e.target.result;
		};
		fr.readAsDataURL(file);
		showimg.style.display = 'block';
		}
	}
	</script>
	 <script src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap.bundle.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap.bundle.min.js"></script>
	
     </body>
</html>