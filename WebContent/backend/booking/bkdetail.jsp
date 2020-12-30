<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.bookingdetail.model.*"%>
<%@ page import="java.util.List"%>
<%
List<BookingDetailVO> bkdetailList = (List<BookingDetailVO>)request.getAttribute("bkdetailList");
int totalPrice = 0;
for (BookingDetailVO vo: bkdetailList){
	totalPrice += vo.getRm_price() * vo.getQty();
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>客房訂單詳情</title>
</head>
<body>
<jsp:useBean id="rmtypeSvc" scope="page" class="com.roomtype.model.RoomTypeService"/>
<c:forEach var="bkdetailvo" items="${bkdetailList}">
<div>
<h3>房型：${rmtypeSvc.getOne(bkdetailvo.rm_type).type_name}</h3>
<h3>價格：${bkdetailvo.rm_price}</h3>
<h3>預定間數：${bkdetailvo.qty}</h3>
</div>
</c:forEach>
<h2>總價：<%=totalPrice %></h2>
</body>
</html>