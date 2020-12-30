<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.bookingorder.model.*"%>
<%@ page import="com.bookingdetail.model.*"%>
<%@ page import="java.util.List" %>
<%
BookingDetailService bkdetailSvc = new BookingDetailService();
BookingOrderVO bkodvo = (BookingOrderVO) request.getAttribute("bkodvo");
List<BookingDetailVO> bkdetailList = bkdetailSvc.getAllByBkNo(bkodvo.getBk_no());
pageContext.setAttribute("bkdetailList", bkdetailList);
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery.datetimepicker.min.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/back/backend.all.css" />
<title>Insert title here</title>
<style>
.update-display {
height:100vh;
width:100vw;
border:none;
}
.update-form {
display:flex;
flex-direction:column;
justify-content:space-around;
}
</style>
</head>
<body>
	<div class="update-display display-show">
		<form class="update-form" id="update-bkod-form" method="post">
			<h3>
				訂房單號：<b>${bkodvo.bk_no}</b>
				<input type="text" style="display:none;" name="bk_no" value="${bkodvo.bk_no}">
			</h3>
			<label for="dateIn">入住日期</label>
			<input type="text" class="datepicker" id="dateIn" name="date_in" value="${bkodvo.dateIn}"> 
			<label for="dateOut">退房日期</label>
			<input type="text" class="datepicker" id="dateOut" name="date_out" value="${bkodvo.dateOut}">
			<c:forEach var="bkdetailvo" items="${bkdetailList}">
			<h3>預定房型：${bkdetailvo.rm_type}</h3>
			<input type="text" style="display:none;" name="rm_type" value="${bkdetailvo.rm_type}">
			<label>預定間數</label>
			<input type="number" name="qty" value="${bkdetailvo.qty}"> 
			</c:forEach>
			<input type="text" style="display:none;" name="action" value="update_bkod">
			<button class="update-data" type="submit" style="width: 100%">更新資料</button>
		</form>
	</div>
	<script src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
	<%@ include file="/backend/files/backend_footer.file" %>
	<script>
	$(document).ready(function(){
		jQuery.datetimepicker.setLocale('ch');
		$(".datepicker").datetimepicker({
			 i18n:{
				  ch:{
				   months:[
				    '1月','2月','3月','4月',
				    '5月','6月','7月','8月',
				    '9月','10月','11月','12月',
				   ],
				   dayOfWeek:[
				    "日", "一", "二", "三", 
				    "四", "五", "六",
				   ]
				  }
				 },
				 timepicker:false,
				 format:'Y-m-d'
				});
		$("#update-bkod-form").submit(function(e){
			e.preventDefault();
			let form = document.getElementById("update-bkod-form");
			let data = new FormData(form);
			$.ajax({
				 url: "<%=request.getContextPath()%>/BookingOrderServlet",
			     data: data,
			     processData: false,
			     contentType: false,
			     type: 'POST',
			     success: function (msg) {
			        	   Swal.fire({
                               position: "top",
                               icon: "success",
                               title: "修改成功",
                               text: "2秒後自動關閉視窗",
                               showConfirmButton: false,
                               timer: 2000,
                           });
                           setTimeout(function () {
                        	   window.top.location.reload();
                           }, 2000);
			        },
			     error: function(msg) {
			    	 Swal.fire({
                         position: "top",
                         icon: "error",
                         title: "修改失敗",
                         showConfirmButton: false,
                         timer: 2000,
                     });
			     }
			})
		}) 
	})
	
	</script>
</body>
</html>