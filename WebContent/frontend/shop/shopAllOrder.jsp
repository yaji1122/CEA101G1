<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.item.model.*"%>
<%@ page import="com.item_pics.model.*"%>
<%@ page import="com.item_type.model.*"%>
<%@ page import="com.shop_order.model.*"%>
<%@ page import="com.shop_order_detail.model.*"%>
<%@ page import="com.members.model.*"%>

<%
	HashMap<String,String> sp_status = new HashMap<>();
		sp_status.put("0", "待出貨");
		sp_status.put("1", "已出貨");
		sp_status.put("2", "交易完成");
		sp_status.put("3", "退貨");
		sp_status.put("4", "已取消");
	pageContext.setAttribute("sp_status", sp_status);
%>
<jsp:useBean id="itemSvc" scope="page" class="com.item.model.ItemService" />
<jsp:useBean id="shop_order_detailSvc" scope="page" class="com.shop_order_detail.model.Shop_order_detailService" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<head>
	<meta charset="UTF-8" />
	<meta name="description" content="Sona Template" />
	<meta name="keywords" content="Sona, unica, creative, html" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta http-equiv="X-UA-Compatible" content="ie=edge" />
	<title>Diamond Resort ShopCheckout</title>
	<%@ include file="/frontend/files/commonCSS.file"%>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/slick.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/slick-theme.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/front/shoppage.css" type="text/css" />

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
	.shopping-cart {
		position: fixed;
		height: 50px;
		width: auto;
		cursor: pointer;
		top:80%;
		left:95%;
		transition: all 0.5s; 
	}
	.shopping-cart:hover {
		transform: scale(1.2,1.2); 
	}
	.side1 {
		top: 8em;
        position: fixed; 
    }
    .imgd {
    	border: none;
		background-color: rgba(0,0,0,0);
    }
    .btnhome{
    	background-color:orange;
    	color:#111;
    }
    .tablepart th,.tablepart td{
    	width: 230px;
    	text-align: center;
    }
	table {
		margin:auto;
	}
	</style>
	
</head>

<%@ include file="/frontend/files/loginCSS.file"%>

<body>

	<%@ include file="/frontend/files/login.file"%>
	<%@ include file="/frontend/files/loginbox.file" %>
	
		<%
		String mb_id = (String)session.getAttribute("mb_id");
		if(member!=null){
			mb_id = member.getMb_id();
			System.out.println("mb_id = " + mb_id);
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
	
	<div class="container_48">
		<div class="rightside">
			<br>
			<div class="outframe">
				<div>
					<h1>訂單明細 OrderDetail</h1>
				</div>
				 
				<c:forEach var="shop_orderVO" items="${list}">
				<div class="tablepart">
					<table class="tabletitle">
					    <tr style="background-color:orange;">
					    	<th colspan="4"><h3>訂單編號 :${shop_orderVO.sp_odno}</h3></th>
					    </tr>
					    <tr>
					    	<th>下單時間：</th>
					    	<th><fmt:formatDate value='${shop_orderVO.sp_time}' pattern='yyyy-MM-dd HH:mm:ss'/></th>
					    	<th></th>
					    	<th></th>	
					    </tr>	
					    <tr>
					    	<th>狀態：</th>
					    	<th><fmt:formatDate value='${order_masterVO.sp_dlvr}' pattern='yyyy-MM-dd HH:mm'/></th>
					    	<th></th>
					    	<th>
					    		<c:forEach var="status" items="${sp_status}">
									${shop_orderVO.sp_status==status.key? status.value : ''}
								</c:forEach>
							</th>
					    </tr>
					    <tr style="border-bottom: 1px dotted black;">
					    	<th></th>
							<th>名稱</th>
							<th>單價</th>
							<th>數量</th>
					    </tr>
					</table>
				</div>
				<div class="tablepart">
					<table class="cartlist" >
					<c:forEach var="order_detailVO" items="${order_detailSvc.getOrder_detailByP_order_id(order_masterVO.p_order_id)}">
						<tr>
							<td class="imgframe"><img src="<%=request.getContextPath()%>/product/productPic.do?p_id=${order_detailVO.p_id}"></td>
							<td>${productSvc.getOneProduct(order_detailVO.p_id).p_name}</td>
							<td>${order_detailVO.od_pr}</td>
							<td>${order_detailVO.od_qty}</td>
						</tr>
					</c:forEach>
					</table>
				</div>
				<div class="tablepart">
					<table class="tabletitle" >
						<tr style="border-top: 1px dotted black;">
							<th>總額</th>
							<th></th>
							<th></th>
							<th><span>$NT </span>${order_masterVO.om_tpr}</th>
						</tr>
					</table>
				</div>
				<br><br><br>
				</c:forEach>
			</div>
			<br>
		</div>
	</div>
	<%@ include file="/frontend/files/commonJS.file" %>
	<script src="${pageContext.request.contextPath}/js/slick.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/front/frontShopPage.js"></script>
</body>
</html>