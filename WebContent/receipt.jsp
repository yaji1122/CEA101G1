<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.actorder.model.*"%>
<%@ page import="com.service_order.model.*"%>
<%@ page import="com.mealorder.model.*"%>
<%@ page import="com.members.model.*"%>
<%@ page import="com.bookingorder.model.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.time.LocalDate"%>
<%
String bk_no = request.getParameter("bk_no");
pageContext.setAttribute("bkno", bk_no.toUpperCase());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" type="image/png" href="<%=request.getContextPath()%>/img/loading.png" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />
<title>住宿消費明細</title>
</head>
<style>
.invoice-box {
	max-width: 800px;
	margin: auto;
	padding: 30px;
	border: 1px solid #eee;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.15);
	font-size: 14px;
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
.invoice-box table tr.top td {
	padding-bottom: 20px;
}
.invoice-box tr.booking td{
	border-bottom: 1px solid #ddd;
}
.invoice-box tr th.booking-title {
	background: #eee;
	padding: 5px;
	border-bottom: 1px solid #ddd;
}

.invoice-box tr th.booking-title span {
	float: right;
	font-weight: 400;
	font-size: 12px;
	padding-right: 10px;
}

.invoice-box tr.top td:last-child {
	text-align: right;
	border-bottom: 1px solid grey;
}

.invoice-box tr.information td {
	padding-bottom: 40px;
	text-align: right;
}

.invoice-box table tr.heading td {
	background: #eee;
	border-bottom: 1px solid #ddd;
	font-weight: bold;
	text-align: left;
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

.invoice-box table tr.total td span {
	font-size: 18px;
	font-weight: bold;
	letter-spacing: 1px;
	padding: 0px 5px;
}

.mealod-detail {
	font-size: 12px;
	border-bottom: 1px solid #eee
}

.mealod-detail tr th {
	padding: 0px;
}

.mealod-detail tr td {
	padding: 0px;
}
</style>
<body>
	<div class="invoice-box">
		<table cellpadding="0" cellspacing="0">
			<tr class="top">
				<td class="title" colspan="3"><img
					src="<%=request.getContextPath()%>/img/logo.png"
					style="width: 100%; max-width: 200px;"></td>
				<td colspan="3">Diamond Resort, Inc.<br>富豪路 953號<br>
					台北市, 信義區 <jsp:useBean id="mbSvc" scope="page"
						class="com.members.model.MembersService" /> <jsp:useBean
						id="bkodSvc" scope="page"
						class="com.bookingorder.model.BookingOrderService" />

				</td>
			</tr>

			<tr class="information">
				<td colspan="6">
					${mbSvc.getOneByMbId(bkodSvc.getOneByBkNo(bkno).mb_id).mb_name} 君 <br>
					${mbSvc.getOneByMbId(bkodSvc.getOneByBkNo(bkno).mb_id).mb_email} <br>
					<%=LocalDate.now()%>
				</td>
			</tr>

			<tr class="heading">
				<td colspan="6">付款方式</td>
			</tr>

			<tr class="details">
				<td colspan="6">信用卡 <span
					style="font-size: 12px; font-weight: 400">卡號：${bkodSvc.getOneByBkNo(bkno).card_no}</span></td>
			</tr>
			<tr>
				<th class="booking-title" colspan="6">訂房明細 ${bkodSvc.getOneByBkNo(bkno).dateIn} ~ ${bkodSvc.getOneByBkNo(bkno).dateOut}<span>No#
						${bkno}</span></th>
			</tr>
			<tr class="booking">
				<td>房型</td>
				<td>單價(人/晚)</td>
				<td colspan="2">入住人數</td>
				<td colspan="2">房型價格</td>
			</tr>
			<jsp:useBean id="bkdetailSvc" scope="page" class="com.bookingdetail.model.BookingDetailService"/>
			<jsp:useBean id="rmtypeSvc" scope="page" class="com.roomtype.model.RoomTypeService"/>
			<c:forEach var="bkod" items="${bkdetailSvc.getAllByBkNo(bkno)}">
			<tr>
				<td>${rmtypeSvc.getOne(bkod.rm_type).type_name}</td>
				<td>\$${rmtypeSvc.getOne(bkod.rm_type).rm_price}(人/晚)</td>
				<td colspan="2">${bkod.rm_guest}人</td>
				<td colspan="2">\$${bkod.rm_subtotal}</td>
			</tr>
			</c:forEach>
			<tr class="heading">
				<td>消費類型</td>
				<td>訂單編號</td>
				<td colspan="2">下單時間</td>
				<td colspan="2">訂單總額</td>
			</tr>

			<jsp:useBean id="mealodSvc" scope="page"
				class="com.mealorder.model.MealOrderService" />
			<c:forEach var="mealod" items="${mealodSvc.getAllByBkNo(bkno)}">
				<tr class="item">
					<td>餐飲服務</td>
					<td>${mealod.meal_odno}</td>
					<td colspan="2"><fmt:formatDate value="${mealod.od_time}"
							type="both" dateStyle="medium" timeStyle="medium" /></td>
					<td colspan="2">$ ${mealod.total_price}</td>
				</tr>
				<tr class="item-detail">
					<td colspan="3">
						<table class="mealod-detail">
							<tr>
								<td colspan="6"><i class="fas fa-caret-right"></i>明細</td>
							</tr>
							<tr>
								<td>餐點名稱</td>
								<td>單價</td>
								<td>數量</td>
								<td>價格</td>
							</tr>
							<jsp:useBean id="mealdetailSvc" scope="page"
								class="com.mealorderdetail.model.MealOrderDetailService" />
							<jsp:useBean id="mealSvc" scope="page"
								class="com.meal.model.MealService" />
							<c:forEach var="detail"
								items="${mealdetailSvc.getAllByOdno(mealod.meal_odno)}">
								<tr>
									<td>${mealSvc.getOneMeal(detail.meal_no).meal_name}</td>
									<td>${detail.price}</td>
									<td>${detail.qty}</td>
									<td>\$ ${detail.price * detail.qty}</td>
								</tr>
							</c:forEach>
						</table>
					</td>
					<td colspan="3"></td>
				</tr>
			</c:forEach>

			<jsp:useBean id="svcodSvc" scope="page"
				class="com.service_order.model.ServiceOrderService" />
			<jsp:useBean id="svcSvc" scope="page"
				class="com.services.model.ServicesService" />
			<c:forEach var="serviceod" items="${svcodSvc.getAllByBkNo(bkno)}">
				<tr class="item">
					<td>${svcSvc.getOneServices(serviceod.serv_no).serv_name}</td>
					<td>${serviceod.serv_odno}</td>
					<td>${serviceod.od_time}</td>
					<td>\$${serviceod.total_price}</td>
				</tr>
			</c:forEach>

			<jsp:useBean id="actodSvc" scope="page"
				class="com.actorder.model.ActOrderService" />
			<jsp:useBean id="actSvc" scope="page"
				class="com.act.model.ActService" />
			<c:forEach var="actod" items="${actodSvc.getAllByBkNo(bkno)}">
				<tr class="item">
					<td>${actSvc.getOneAct(actod.actNo).actName}</td>
					<td>${actod.actOdno}</td>
					<td>${actod.odTime}</td>
					<td>\$${actod.totalPrice}</td>
				</tr>
			</c:forEach>
			<%
				int total = 0;
				int subtotal = 0;
				total += svcodSvc.getAllByBkNo(bk_no.toUpperCase()).stream().mapToInt(e -> e.getTotal_price()).sum();
				total += actodSvc.getAllByBkNo(bk_no.toUpperCase()).stream().mapToInt(e -> e.getTotalPrice()).sum();
				total += mealodSvc.getAllByBkNo(bk_no.toUpperCase()).stream().mapToInt(e -> e.getTotal_price()).sum();
				subtotal = total;
				total += bkodSvc.getOneByBkNo(bk_no.toUpperCase()).getTotal_price();
				
				
				
			%>
			<tr class="total">
				<td colspan="6" style="text-align: right">訂單價格總計(含房費)： USD<span>$<%=total%></span></td>
			</tr>
			<tr class="total">
				<td colspan="6" style="text-align: right">住房期間消費： USD<span>$<%=subtotal%></span></td>
			</tr>
		</table>
	</div>
</body>
</html>