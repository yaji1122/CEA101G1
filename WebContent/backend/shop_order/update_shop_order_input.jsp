<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shop_order.model.*"%>
<%@ page import="java.util.*"%>
<%
	Shop_orderVO shop_orderVO = (Shop_orderVO) request.getAttribute("shop_orderVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>商品資料修改 - update_item_input.jsp</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/back/backend-shop.css" />
<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>

<style>
#title {
	width: 100%;
	height: auto;
	margin-top: 15px;
	background-color: rgb(204, 202, 202);
	border: 1px solid;
	font-size: 30px;
	text-align: center;
	padding-top: 5px;
	display: flex;
	flex-direction: row;
}

.titleAll {
	margin-left: auto;
	margin-right: auto;
}

.home {
	margin-left: 0px;
	margin-right: 5px;
	float: right;
	font-size: 27px;
}

table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
	margin-left: auto;
	margin-right: auto;
	border: 1px gray;
}

tr:nth-of-type(even) {
	background-color: #f3f3f3;
}

tr:nth-of-type(odd) {
	background-color: #ddd;
}

th, td {
	padding: 5px;
	text-align: center;
}

.modItem {
	margin-left: 250px;
}

.inputItem {
	margin-left: 920px;
}

.inputData {
	float: left;
}
</style>

</head>
<body bgcolor='white'>

	<div id="title">

		<div class="titleAll">修改商品資料</div>
		<div>
			<a href="<%=request.getContextPath()%>/backend/shop_order/select_shop_order.jsp"
				class="home">回商品管理</a>
		</div>

	</div>

	<h3 class="modItem">資料修改:</h3>

<%-- 錯誤表列 --%>

		<c:if test="${not empty errorMsgs}">
			<div style="color: red" class="error">
				<div class="errorfix">請修正以下錯誤</div>
				<div class="close">✖</div>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</div>
		</c:if>

	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/shop_order/shop_order.do"
		name="form1">
		<table>
			<tr>
				<td>訂單編號:<font color=red><b></b></font></td>
				<td class="inputData">${shop_orderVO.sp_odno}</td>
			</tr>
			<tr>
				<td>訂單狀態:<font color=red><b></b></font></td>
				<td class="inputData"><select size="1" name="sp_status">
						<option value="0">待出貨
						<option value="1">已出貨
						<option value="2">交易完成
						<option value="3">退貨
						<option value="4">已取消
				</select></td>
			</tr>
			<tr>
				<td>出貨時間:<font color=red><b></b></font></td>
				<td class="inputData"><input name="sp_dlvr" id="f_date1" type="text"  /></td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="sp_odno" value="<%=shop_orderVO.getSp_odno()%>">
		<input type="submit" value="送出修改" class="inputItem">
	</FORM>
	<script src="${pageContext.request.contextPath}/js/backShopItem.js"></script>
</body>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:true,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
 		   value: '<%=shop_orderVO.getSp_dlvr()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           startDate:	            '<%=shop_orderVO.getSp_time()%>',  // 起始日
           minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
       
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
//              var somedate1 = new Date('2017-06-15');
//              $('#f_date1').datetimepicker({
//                  beforeShowDay: function(date) {
//                	  if (  date.getYear() <  somedate1.getYear() || 
//         		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
//         		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
//                      ) {
//                           return [false, ""]
//                      }
//                      return [true, ""];
//              }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
 </script> 
</html>