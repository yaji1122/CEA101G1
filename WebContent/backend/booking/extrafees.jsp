<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.actorder.model.*" %>
<%@ page import="com.service_order.model.*" %>
<%@ page import="com.mealorder.model.*" %>
<%@ page import="com.members.model.*" %>
<%@ page import="com.bookingorder.model.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDate" %>
<%
	String bk_no = request.getParameter("bk_no");
	pageContext.setAttribute("bkno", bk_no.toUpperCase());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>住宿消費明細</title>
</head>
<style>
	.invoice-box {
  max-width: 800px;
  margin: auto;
  padding: 30px;
  border: 1px solid #eee;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.15);
  font-size: 16px;
  line-height: 24px;
  font-family: "Helvetica Neue", "Helvetica", Helvetica, Arial, sans-serif;
  color: #555;
}

.invoice-box table {
  width: 100%;
  line-height: inherit;
  text-align: left;
}

.invoice-box table td {
  padding: 5px;
  vertical-align: top;
}

.invoice-box table tr td:nth-child(n + 2) {
  text-align: right;
}

.invoice-box table tr.top table td {
  padding-bottom: 20px;
}

.invoice-box table tr.top table td.title {
  font-size: 45px;
  line-height: 45px;
  color: #333;
}

.invoice-box table tr.information table td {
  padding-bottom: 40px;
}

.invoice-box table tr.heading td {
  background: #eee;
  border-bottom: 1px solid #ddd;
  font-weight: bold;
  text-align: left;
}

.invoice-box table tr.heading td:last-child {
	text-align: right;
}
.invoice-box table tr.details td {
  padding-bottom: 20px;
}

.invoice-box table tr.item td {
  border-bottom: 1px solid #eee;
}

.invoice-box table tr.item.last td {
  border-bottom: none;
}

.invoice-box table tr.item input {
  padding-left: 5px;
}

.invoice-box table tr.item td:first-child input {
  margin-left: -5px;
  width: 100%;
}
.invoice-box table tr.total td:nth-child(2) {
  border-top: 2px solid #eee;
  font-weight: bold;
}

.invoice-box input[type="number"] {
  width: 60px;
}

@media only screen and (max-width: 600px) {
  .invoice-box table tr.top table td {
    width: 100%;
    display: block;
    text-align: center;
  }

  .invoice-box table tr.information table td {
    width: 100%;
    display: block;
    text-align: center;
  }
}

/** RTL **/
.rtl {
  direction: rtl;
  font-family: Tahoma, "Helvetica Neue", "Helvetica", Helvetica, Arial,
    sans-serif;
}

.rtl table {
  text-align: right;
}

.rtl table tr td:nth-child(2) {
  text-align: left;
}
	
</style>
<body>
<div class="invoice-box">
  <table cellpadding="0" cellspacing="0">
    <tr class="top">
      <td colspan="4">
        <table>
          <tr>
            <td class="title">
              <img src="https://www.sparksuite.com/images/logo.png" style="width:100%; max-width:300px;">
            </td>

            <td>
              Invoice #:${bkno} <br> Created: <%=LocalDate.now()%>
            </td>
          </tr>
        </table>
      </td>
    </tr>

    <tr class="information">
      <td colspan="4">
        <table>
          <tr>
            <td>
              Diamond Resort, Inc.<br>富豪路 953號<br> 台北市, 信義區
            </td>
			<jsp:useBean id="mbSvc" scope="page" class="com.members.model.MembersService"/>
			<jsp:useBean id="bkodSvc" scope="page" class="com.bookingorder.model.BookingOrderService"/>
			
            <td>
               ${mbSvc.getOneByMbId(bkodSvc.getOneByBkNo(bkno).mb_id).mb_name} 君
            </td>
          </tr>
        </table>
      </td>
    </tr>

    <tr class="heading">
      <td colspan="3">Payment Method</td>
      <td>No #</td>
    </tr>

    <tr class="details">
      <td colspan="3">Credit Card</td>
      <td>1239 2939 1234 2312</td>
    </tr>

    <tr class="heading">
      <td>訂單類型</td>
      <td>訂單編號</td>
      <td>下單時間</td>
      <td>訂單總額</td>
    </tr>
    
	<jsp:useBean id="mealodSvc" scope="page" class="com.mealorder.model.MealOrderService"/>
	<c:forEach var="mealod" items="${mealodSvc.getAllByBkNo(bkno)}">
	<tr class="item">
      <td>餐飲服務</td>
      <td>${mealod.meal_odno}</td>
      <td>${mealod.od_time}</td>
      <td>${mealod.total_price}</td>
    </tr>
    <tr class="item-detail">
    	<td>
    		<table>
    			<tr>
    				<th>餐點名稱</th>
    				<th>單價</th>
    				<th>數量</th>
    				<th>小計</th>
    			</tr>
    			<jsp:useBean id="mealdetailSvc" scope="page" class="com.mealorderdetail.model.MealOrderDetailService"/>
    			<jsp:useBean id="mealSvc" scope="page" class="com.meal.model.MealService"/>
    			<c:forEach var="detail" items="${mealdetailSvc.getAllByOdno(mealod.meal_odno)}">
    			<tr>
    				<td>${mealSvc.getOneMeal(detail.meal_no).meal_name}</td>
    				<td>${detail.price}</td>
    				<td>${detail.qty}</td>
    				<td>\$ ${detail.price * detail.qty}</td>
    			</tr>
    			</c:forEach>
    		</table>
    	</td>
    </tr>
	</c:forEach>
	
	<jsp:useBean id="svcodSvc" scope="page" class="com.service_order.model.ServiceOrderService"/>
	<jsp:useBean id="svcSvc" scope="page" class="com.services.model.ServicesService"/>
    <c:forEach var="serviceod" items="${svcodSvc.getAllByBkNo(bkno)}">
    <tr class="item">
      <td>${svcSvc.getOneServices(serviceod.serv_no).serv_name}</td>
      <td>${serviceod.serv_odno}</td>
      <td>${serviceod.od_time}</td>
      <td>${serviceod.total_price}</td>
    </tr>
    </c:forEach>
<%-- 
	<jsp:useBean id="actodSvc" scope="page" class="com.actorder.model.ActOrderService"/>
	<jsp:useBean id="actSvc" scope="page" class="com.act.model.ActService"/>
	<c:forEach var="actod" items="${actodSvc.getAllByBkNo(bkno)}">
    <tr class="item">
      <td>${actSvc.getOneAct(actod.actNo).actName}</td>
      <td>${actod.actOdno}</td>
      <td>${actod.odTime}</td>
      <td>${actod.totalPrice}</td>
    </tr>
    </c:forEach>
	 --%>
    <tr class="total">
      <td colspan="3"></td>
      <td></td>
    </tr>
  </table>
</div>
</body>
</html>