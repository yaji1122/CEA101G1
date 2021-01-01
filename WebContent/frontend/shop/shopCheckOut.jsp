<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.item.model.*"%>
<%@ page import="com.members.model.*"%>
<%@ page import="com.item_pics.model.*"%>
<%@ page import="com.shoppingCart.model.*"%>
<jsp:useBean id="item_picsSvc" scope="page" class="com.item_pics.model.Item_picsService" />
<jsp:useBean id="itemSvc" scope="page" class="com.item.model.ItemService" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="description" content="Sona Template" />
<meta name="keywords" content="Sona, unica, creative, html" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
	<title>Diamond Resort ShopCheckout</title>
<%@ include file="/frontend/files/commonCSS.file" %>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/slick.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/slick-theme.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/front/shoppage.css"
	type="text/css" />
	
	<style type="text/css">
	.leftside {
	    background: none repeat scroll 0 0 transparent;
	    float: left;
	    width: 212px;
	}
	.rightside {
		float: right;
		width: calc(100% - 18.5em);
		height: 100%;
		overflow-x: hidden;
	}
	body{
        background-image: url('<%=request.getContextPath()%>/front-end/shop/images/background5.jpg') !important;
        background-repeat: no-repeat !important;
        background-size: cover !important;
        background-attachment: fixed !important; 
        background-position: center !important;
    }
	.nav {
		display: block !important;
	}
	.container_48 {
		top: 8em;
		margin-left:auto;
		margin-right:auto;
		width: 1350px !important;
	}
	.sale_category li {
		list-style-type: disc !important;
	}
	.side1 {
		top: 8em;
        position: fixed; 
    }
    .imgd {
    	border: none;
		background-color: rgba(0,0,0,0);
    }
    .btns {
    	display:inline !important;
    }
    .btnhome{
    	background-color:#0794F9;
    	color:#111;
    }
    .outframe table tr th,.outframe table tr td {
    	width:100px;
    }
	table {
		margin:auto;
	}
	.paybtn:hover{
		background-color:orange;
	}
	.btnhome:hover{
    	background-color:#2638ED;
    	color:#111;
    }
    .tabletitle, .cartlist {
		width: 1000px !important;
	}
	</style>
</head>
<%@ include file="/frontend/files/loginCSS.file" %>
<body>
	<%@ include file="/frontend/files/login.file" %>
	<%@ include file="/frontend/files/loginbox.file" %>
	<%
	String mb_id = (String)session.getAttribute("mb_id");
	if(member!=null){
	mb_id = member.getMb_id();
	System.out.println("mb_id = "+mb_id);
	session.setAttribute("mb_id", mb_id);
	} else {
		
	}
	%>
		<!-- Page Preloder -->
	<div id="preloder">
		<img id="preloaderpic"
			src="${pageContext.request.contextPath}/img/loading.png" />
		<div class="loader"></div>
	</div>
	<!-- Offcanvas Menu Section Begin -->
	<div class="offcanvas-menu-overlay"></div>
	<div class="canvas-open">
		<i class="icon_menu"></i>
	</div>
	<div class="offcanvas-menu-wrapper">
		<div class="canvas-close">
			<i class="icon_close"></i>
		</div>
		<div class="search-icon search-switch">
			<i class="icon_search"></i>
		</div>
		<div class="header-configure-area">
			<div class="language-option">
				<img src="img/flag.jpeg" alt="" /> <span>TW <i
					class="fa fa-angle-down"></i></span>
			</div>
			<a href="#" class="bk-btn">立刻訂房</a>
		</div>
		<nav class="mainmenu mobile-menu">
			<ul>
				<li><a href="./pages.html" class="nav-event">會員中心</a>
					<ul class="dropdown">
						<li><a href="./room-details.html">個人檔案</a></li>
						<li><a href="./blog-details.html">我的假期</a></li>
					</ul></li>
				<li><a class="nav-event">住客專區</a>
					<ul class="dropdown">
						<li><a href="./activity.html">活動報名</a></li>
						<li><a href="">預約服務</a></li>
						<li><a href="">訂購餐點</a></li>
					</ul></li>
				<li><a href="shop.html" class="nav-evnet">戴蒙商城</a></li>
				<li><a class="nav-event" href="./rooms.html">渡假空間</a>
					<ul class="dropdown">
						<li><a href="#">戴蒙經典房</a></li>
						<li><a href="#">豪華蜜月房</a></li>
						<li><a href="#">奢華海景房</a></li>
						<li><a href="#">波賽頓套房</a></li>
						<li><a href="#">公共空間</a></li>
					</ul></li>
				<li><a class="nav-event" href="./pages.html">精彩活動</a>
					<ul class="dropdown">
						<li><a href="./room-details.html">陸上活動</a></li>
						<li><a href="./room-details.html">海上活動</a></li>
						<li><a href="./room-details.html">網紅行程</a></li>
					</ul></li>
				<li><a href="" class="nav-event">會員登入</a></li>
				<li><a href="" class="nav-event"
					style="color: rgb(240, 218, 162)">加入會員</a></li>
			</ul>
		</nav>
		<div id="mobile-menu-wrap"></div>
		<div class="top-social">
			<a href="#"><i class="fa fa-facebook"></i></a> <a href="#"><i
				class="fa fa-twitter"></i></a> <a href="#"><i
				class="fa fa-tripadvisor"></i></a> <a href="#"><i
				class="fa fa-instagram"></i></a>
		</div>
		<ul class="top-widget">
			<li><i class="fa fa-phone"></i> (12) 345 67890</li>
			<li><i class="fa fa-envelope"></i> info.colorlib@gmail.com</li>
		</ul>
	</div>
	
		<!-- Header Section Begin -->
	<header class="header-section">
		<div class="menu-item">
			<div class="nav-menu">
				<nav class="mainmenu">
					<ul class="mainmenu-row">
						<div class="logobox">
							<a href="${pageContext.request.contextPath}/frontend/index.jsp"><img
								src="${pageContext.request.contextPath}/img/logo.png"/>
							</a>
						</div>
						<li  class="nav-list"><a class="nav-event" href="<%=request.getContextPath()%>/frontend/shop/shopPage.jsp">HOME</a></li>
						
						<li class="nav-list">
						<a class="nav-event">DIAMOND CLASSIC</a>
										<ul class="dropdown">
											<c:forEach var="item_typeVO"
												items="${item_typeSvc.allItem_type}" begin="0" end="2">
												<li value="${item_typeVO.item_type_no}" class="chtype"><a
													href="<%=request.getContextPath()%>/frontend/shop/shopPage.jsp?item_type_no=${item_typeVO.item_type_no}">${item_typeVO.type_name}</a></li>
											</c:forEach>
										</ul>
						</li>
						
						<li class="nav-list"><a class="nav-event">GIFTS & SOUVENIR</a>
										<ul class="dropdown">
											<c:forEach var="item_typeVO"
												items="${item_typeSvc.allItem_type}" begin="3" end="5">
												<li value="${item_typeVO.item_type_no}" class="chtype"><a
													href="<%=request.getContextPath()%>/frontend/shop/shopPage.jsp?item_type_no=${item_typeVO.item_type_no}">${item_typeVO.type_name}</a></li>
											</c:forEach>
										</ul></li>
						
						<li class="nav-list"><a class="nav-evnet">SEASONAL GOODS</a>
										<ul class="dropdown">
											<c:forEach var="item_typeVO"
												items="${item_typeSvc.allItem_type}" begin="6">
												<li value="${item_typeVO.item_type_no}" class="chtype"><a
													href="<%=request.getContextPath()%>/frontend/shop/shopPage.jsp?item_type_no=${item_typeVO.item_type_no}">${item_typeVO.type_name}</a></li>
											</c:forEach>
										</ul></li>
						
						<li class="nav-list"><a class="nav-event" href="<%=request.getContextPath()%>/frontend/shop/shopCartRedis.jsp"><i class="fas fa-shopping-cart icon" ></i></a></li>
						
						<li class="nav-list"><a class="nav-event"> <c:choose>
									<c:when test="${member != null}"><i class="far fa-gem"></i></i>會員中心</a>
							<ul class="dropdown">
								<li><a href="${pageContext.request.contextPath}/frontend/members/memberInfo.jsp">個人檔案</a></li>
								<li><a href="#">我的假期</a></li>
								<li><a href="#">歷史訂單</a></li>
								<li><a
									href="${pageContext.request.contextPath}/LoginHandler?mb_email=${member.mb_email}&action=member-logout&location=${pageContext.request.requestURL}">登出</a></li>
							</ul></li>
						</c:when>
						<c:otherwise>
							<i class="fas fa-user-circle log-in"></i>
							</a>
						</c:otherwise>
						</c:choose>
						</li>
						

					</ul>
				</nav>
			</div>
		</div>
	</header>
	
		<!-- Header Section End -->
	<div class="wrapper">
		<div class="pageheader">
	
	
	<div class="container_48">
		<%
			@SuppressWarnings("unchecked")
			List<ItemVO> buylist = (List<ItemVO>)session.getAttribute("buylist");
			Double amount = (Double)request.getAttribute("amount");
			request.setAttribute("amount",amount);
			Integer poamount = (int)request.getAttribute("poamount");
			request.setAttribute("poamount",poamount);
			
			
			CartService cartSVC = new CartService();
		%>
		<div class="rightside">
			<br>
			<div class="outframe">
				<div>
					<h1>結帳前確認 Check </h1>
				</div>
				<table class="tabletitle">
				    <tr>
				    	<th></th>
						<th class="imgframe"></th>
						<th>編號</th>
						<th>名稱</th>
						<th>價格</th>
						<th>數量</th>
						<th>積分</th>
						<th>總積分</th>
						<th>總額</th>
				    </tr>
				</table>
				<% for (int i = 0; i < buylist.size(); i++) {
					ItemVO order = buylist.get(i);
				%>
				<table class="cartlist">
					<tr>
						<td class="imgframe"><img src="<%=request.getContextPath()%>/item_pics/item_pics.do?item_pic_no=<%=item_picsSvc.getAllPics(order.getItem_no()).get(0).getItem_pic_no()%>&action=getOne_Pic_Display"></td>
						<td><%=order.getItem_no()%></td>
						<td><%=itemSvc.getOneItem(order.getItem_no()).getItem_name()%></td>					
						<td><span>$ </span><%=order.getItem_price()%></td>
						<td><%=order.getQuantity()%></td>
						<td><%=order.getPoints()%></td>
						<td><%= (cartSVC.getValueByItem_no(mb_id,order.getItem_no()))*(order.getPoints())%></td>
						<td><span>$ </span><%= (cartSVC.getValueByItem_no(mb_id,order.getItem_no()))*(order.getItem_price())%></td>
					</tr>
				<% }%>
					<tr>
						<td colspan="4">
						<td>總積分</td>
						<td><span>$ </span><%=poamount %></td>
					</tr>
					<tr>
						<td colspan="4">
						<td>總金額</td>
						<td><span>$ </span><%=amount %></td>
					</tr>
				</table>
				<br>
				<form  method="POST" action="<%=request.getContextPath()%>/shop_order/shop_order.do">
					<input type="hidden" name="action"  value="insertWithOrder_details">
					<input type="hidden" name="requestURL"  value="<%=request.getServletPath()%>">
					<input type="hidden" name="mb_id"  value="${member.mb_id}">
					<input type="hidden" name="om_tpr"  value="<%=amount%>">
					<input type="submit" value="確定購買" class="paybtn">
				</form>
			</div>
		</div>
	</div>
	</div>
	</div>
</body>
</html>