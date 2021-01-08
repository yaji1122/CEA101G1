<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.actorder.model.*" %>
<%@ page import="com.service_order.model.*" %>
<%@ page import="com.mealorder.model.*" %>
<%@ page import="com.members.model.*" %>
<%@ page import="com.bookingorder.model.*" %>
<%@ page import="java.util.List" %>
<%
	String bk_no = request.getParameter("bk_no");
	
	ActOrderService actSvc = new ActOrderService();
	MealOrderService mealSvc = new MealOrderService();
	ServiceOrderService svc = new ServiceOrderService();
	
	List<MealOrderVO> mealodList = mealSvc.getAllByBkNo(bk_no);
	List<ServiceOrderVO> svcList = 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>