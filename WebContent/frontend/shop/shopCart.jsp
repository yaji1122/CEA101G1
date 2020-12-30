<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.item.model.*"%>
<%@ page import="com.item_type.model.*"%>
<%@ page import="com.item_pics.model.*"%>
<%@ page import="com.members.model.*"%>
<%@ page import="com.shoppingCart.model.*"%>
<%
	ItemService itemSvc = new ItemService();
	String item_type_no = request.getParameter("item_type_no");
	List<ItemVO> list = null;
	if (item_type_no == null) {
		list = itemSvc.getAllItemBySt();
	} else {
		list = itemSvc.getAllByItem_type_noBySt(item_type_no);
	}
	pageContext.setAttribute("list", list);
	pageContext.setAttribute("item_type_no", item_type_no);
%>
<%
// 	String mb_id=(String)session.getAttribute("mb_id");
//     String mb_id   ="MEM0000001";
// 	MembersService membersSvc = new MembersService();
// 	MembersVO membersVO=null;
// 	membersVO=membersSvc.getOneByMbId(mb_id);
// 	pageContext.setAttribute("membersVO", membersVO);	
	
	CartService cartSvc = new CartService();
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="description" content="Sona Template" />
<meta name="keywords" content="Sona, unica, creative, html" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>Diamond Resort</title>
<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css?family=Lora:400,700&display=swap"
	rel="stylesheet" />
<link
	href="https://fonts.googleapis.com/css?family=Cabin:400,500,600,700&display=swap"
	rel="stylesheet" />
<!-- Css Styles -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	type="text/css" />
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.15.1/css/all.css"
	integrity="sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/elegant-icons.css"
	type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/flaticon.css"
	type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/owl.carousel.min.css"
	type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/nice-select.css"
	type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/jquery-ui.min.css"
	type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/slicknav.min.css"
	type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/template.css"
	type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/slick.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/slick-theme.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/shoppage.css"
	type="text/css" />


</head>

<body>
	<div class="back"></div>
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
	<!-- Offcanvas Menu Section End -->
	<!-- Header Section Begin -->
	<header class="header-section">
		<div class="menu-item">
			<div class="container">
				<div class="row">
					<div class="col-lg-3">
						<div class="logo">
							<a href="./index.html"> <img
								src="${pageContext.request.contextPath}/img/logo.png" alt="" />
							</a>
						</div>
					</div>
					<div class="col-lg-9">
						<div class="nav-menu">

							<jsp:useBean id="item_typeSvc" scope="page"
								class="com.item_type.model.Item_typeService" />

							<nav class="mainmenu">
								<ul>
									<li class="active"><a
										href="<%=request.getContextPath()%>/frontend/shop/shopPage.jsp">HOME</a></li>
									<li><a class="nav-event">DIAMOND CLASSIC</a>
										<ul class="dropdown">
											<c:forEach var="item_typeVO"
												items="${item_typeSvc.allItem_type}" begin="0" end="2">
												<li value="${item_typeVO.item_type_no}" class="chtype"><a
													href="<%=request.getContextPath()%>/frontend/shop/shopPage.jsp?item_type_no=${item_typeVO.item_type_no}">${item_typeVO.type_name}</a></li>
											</c:forEach>
										</ul></li>
									<li><a class="nav-event">GIFTS & SOUVENIR</a>
										<ul class="dropdown">
											<c:forEach var="item_typeVO"
												items="${item_typeSvc.allItem_type}" begin="3" end="5">
												<li value="${item_typeVO.item_type_no}" class="chtype"><a
													href="<%=request.getContextPath()%>/frontend/shop/shopPage.jsp?item_type_no=${item_typeVO.item_type_no}">${item_typeVO.type_name}</a></li>
											</c:forEach>
										</ul></li>
									<li><a class="nav-evnet">SEASONAL GOODS</a>
										<ul class="dropdown">
											<c:forEach var="item_typeVO"
												items="${item_typeSvc.allItem_type}" begin="6">
												<li value="${item_typeVO.item_type_no}" class="chtype"><a
													href="<%=request.getContextPath()%>/frontend/shop/shopPage.jsp?item_type_no=${item_typeVO.item_type_no}">${item_typeVO.type_name}</a></li>
											</c:forEach>
										</ul></li>
									<li><a href="<%=request.getContextPath()%>/frontend/shop/shopCart.jsp"><i class="fas fa-shopping-cart icon"></i></a></li>
								</ul>
							</nav>
						</div>
					</div>
				</div>
			</div>
		</div>

	</header>

	<!-- Header Section End -->
	<div class="wrapper">
		<div class="pageheader">


			<jsp:useBean id="item_picsSvc" scope="page"
				class="com.item_pics.model.Item_picsService" />
	<div class="outframe">
					<div class="outframe">
				<div>
					<h1>購物車明細 ShoppingCart</h1>
				</div>
				<form name="deleteForm" action="<%=request.getContextPath()%>/shop/ShoppingRedis.do" method="POST">
					
					<table class="tabletitle">
					    <tr>
					    	<th></th>
							<th class="picborder"></th>
							<th>編號</th>
							<th>名稱</th>
							<th>價格</th>
							<th>數量</th>
							<th>總額</th>
						    <th>動作</th>
					    </tr>
					</table>
					<table class="cartlist">
					<%
						@SuppressWarnings("unchecked") 
						List<ItemVO> buylist = (List<ItemVO>) session.getAttribute("buylist");
						
					%>
					<% double total = 0;%>
					<%
					if(buylist == null){
						
					%>
					<tr></tr>
					<%
					} else {
					%>
					<% if(buylist != null && (buylist.size() > 0)) {%>
					<% for (int index = 0; index < buylist.size(); index++){
						ItemVO order = buylist.get(index);
						total+=Math.round(order.getItem_price()*order.getQuantity());
					%>
					<tr>
						<td>
							<label class="checklabel">
								<input type="checkbox" name="checkact" value="<%= index %>">
								<span class="checkmark"></span>
							</label>
						</td>
						<td class="imgframe"><img src="<%=request.getContextPath()%>/item_pics/item_pics.do?item_pic_no=<%=item_picsSvc.getAllPics(order.getItem_no()).get(0).getItem_pic_no()%>&action=getOne_Pic_Display"></td>
						<td><%=order.getItem_no()%></td>
						<td><%=order.getItem_name()%></td>					
						<td><span>$ </span><%=order.getItem_price()%></td>
						<td><%=order.getQuantity()%></td>	
						<td><span>$ </span><%=(int)Math.round(order.getItem_price()*order.getQuantity())%></td>
				        <td>	
							<input type="hidden" name="action"  value="deleteSelected">
							<input type="hidden" name="del" value="<%= index %>">
							<input type="button" value="刪 除" class="button"  
								onclick="location.href='<%=request.getContextPath()%>/shop/shoppingRedisCart.do?action=DELETE&del=<%=index%>&item_no=<%=order.getItem_no()%>'">
						</td>
					</tr>
					<% }%>
					<tr>
						<td colspan="5">
						<td>總金額<span> $ </span></td>
						<td><%=(int)total %></td>
						<td>
					</tr>
					</table>
					<br>
					<br>
					<div>
						<input type="submit" value="清除勾選" class="paybtn cleanbtn">
					</div>
				</form>

				<div style="margin-left:230px;margin-top:-55px">
					<form name="checkoutForm" action="<%=request.getContextPath()%>/shop/shoppingRedisCart.do" method="POST">
						<input type="hidden" name="action"  value="CHECKOUT"> 
						<input type="hidden" name="loginLocation"  value="<%=request.getRequestURI()%>"> 
						<input type="submit" value="前往結帳" class="paybtn">
					</form>
				</div>
			</div>
			<br>

			<% }}%>

			<footer class="footer-section main-footer">
				<div class="copyright-option">
					<div class="container">
						<div class="row">
							<div>
								<div class="co-text">
									<p>
										Copyright &copy;
										<script>
											document.write(new Date()
													.getFullYear());
										</script>
										All rights reserved | This template is made with <i
											class="fa fa-heart" aria-hidden="true"></i> by <a
											href="https://colorlib.com" target="_blank">Colorlib</a>
										<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
									</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</footer>
		</div>
	</div>
	<!-- Footer Section Start -->

	<!-- Footer Section End -->
	<!-- Js Plugins -->
	<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/slick.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/jquery.nice-select.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.slicknav.js"></script>
	<script src="${pageContext.request.contextPath}/js/owl.carousel.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/template.js"></script>
	<script src="${pageContext.request.contextPath}/js/sweetalert.js"></script>
	<script src="${pageContext.request.contextPath}/js/frontShopPage.js"></script>

</body>
</html>