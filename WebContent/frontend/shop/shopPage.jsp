<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.item.model.*"%>
<%@ page import="com.item_type.model.*"%>
<%@ page import="com.item_pics.model.*"%>

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
	System.out.print(list.size());
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

<%@ include file="/frontend/files/commonCSS.file" %>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/slick.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/slick-theme.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/front/shoppage.css"
	type="text/css" />

</head>
<%@ include file="/frontend/files/loginCSS.file" %>
<body>
    <%@ include file="/frontend/files/login.file" %>
	<%@ include file="/frontend/files/loginbox.file" %>
	<div class="back"></div>
	<!-- Page Preloder -->
	<div id="preloder">
		<img id="preloaderpic"
			src="${pageContext.request.contextPath}/img/loading.png" />
		<div class="loader"></div>
	</div>
	<!-- Offcanvas Menu Section Begin -->
<!-- 	<div class="shopping-cart"> -->
<!-- 		<div class="cart-border"> -->
<!-- 			<div class="cart-title">MY SHOPPING BAG</div> -->
<!-- 			<div class="cart-item"> -->
<!-- 				<div class="cart-item-pic"> -->
<!-- 					<img src="img/watch1.jpg" alt="" class="item-pic-size"> -->
<!-- 				</div> -->
<!-- 				<div class="cart-item-name">TRUNK CLUTCH</div> -->
<!-- 				<select name="" id="cart-item-no"> -->
<!-- 					<option value="">1</option> -->
<!-- 					<option value="">2</option> -->
<!-- 					<option value="">3</option> -->
<!-- 					<option value="">4</option> -->
<!-- 					<option value="">5</option> -->
<!-- 					<option value="">6</option> -->
<!-- 				</select> -->
<!-- 				<div class="cart-item-price">$790.00</div> -->
<!-- 				<div> -->
<!-- 					<i class="far fa-trash-alt trash"></i> -->
<!-- 				</div> -->
<!-- 			</div> -->

<!-- 		</div> -->
<!-- 		<div class="priceborder"> -->
<!-- 			<div class="total"> -->
<!-- 				<div class="totaltitle">Total:</div> -->
<!-- 				<div class="totalprice">$7,200.00</div> -->
<!-- 			</div> -->
<!-- 			<div class="point"> -->
<!-- 				<div class="pointtitle">Points:</div> -->
<!-- 				<div class="pointdiscount">$0.00</div> -->
<!-- 			</div> -->
<!-- 			<div class="subtotal"> -->
<!-- 				<div class="subtotaltitle">Subtotal:</div> -->
<!-- 				<div class="subtotalprice">$7,200.00</div> -->
<!-- 			</div> -->
<!-- 			<div class="close">✖</div> -->
<!-- 		</div> -->
<!-- 	</div> -->
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
	
	<jsp:useBean id="item_typeSvc" scope="page" class="com.item_type.model.Item_typeService" />
	
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
											<c:forEach var="item_typeVO" items="${item_typeSvc.allItem_type}" begin="0" end="2">
												<li value="${item_typeVO.item_type_no}" class="chtype">
												<a href="<%=request.getContextPath()%>/frontend/shop/shopPage.jsp?item_type_no=${item_typeVO.item_type_no}">${item_typeVO.type_name}</a>
												</li>
											</c:forEach>
										</ul>
						</li>
						
						<li class="nav-list"><a class="nav-event">GIFTS & SOUVENIR</a>
										<ul class="dropdown">
											<c:forEach var="item_typeVO"
												items="${item_typeSvc.allItem_type}" begin="3" end="5">
												<li value="${item_typeVO.item_type_no}" class="chtype"><a
													href="<%=request.getContextPath()%>/frontend/shop/shopPage.jsp?item_type_no=${item_typeVO.item_type_no}">${item_typeVO.type_name}</a>
												</li>
											</c:forEach>
										</ul>
						</li>
						
						<li class="nav-list"><a class="nav-evnet">SEASONAL GOODS</a>
										<ul class="dropdown">
											<c:forEach var="item_typeVO" items="${item_typeSvc.allItem_type}" begin="6">
												<li value="${item_typeVO.item_type_no}" class="chtype"><a
													href="<%=request.getContextPath()%>/frontend/shop/shopPage.jsp?item_type_no=${item_typeVO.item_type_no}">${item_typeVO.type_name}</a>
												</li>
											</c:forEach>
										</ul>
						</li>
						
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
						
						<div class="itemtypeheader">
							<div id="itemtype">
								<%
									Item_typeVO item_typeVO = new Item_typeVO();
								%>
								<div id="itemtypename">${(item_type_no==null)? "ALL SELECTION":(item_typeSvc.getOneItem_type(item_type_no).type_name)}</div>
								<div id="itemtypeno"><%=list.size()%>
									PRODUCTS
								</div>
							</div>
						</div>
					</ul>
				</nav>
			</div>
		</div>
	</header>
	
	<div class="wrapper">
		<div class="pageheader">

			<div class="pageslider">
				<div class="pagepic">
					<img src="${pageContext.request.contextPath}/img/page1.png" alt="" />
				</div>
				<div class="pagepic">
					<img src="${pageContext.request.contextPath}/img/page2.png" alt="" />
				</div>
			</div>

			<jsp:useBean id="item_picsSvc" scope="page"
				class="com.item_pics.model.Item_picsService" />

			<div class="container">
				<div class="row">
					<c:forEach var="itemVO" items="${list}">
						<div class="col col-12 col-sm-6 col-md-4">
							<div class="itemslider">
								<c:forEach var="item_picsVO"
									items="${item_picsSvc.getAllPics(itemVO.item_no)}">
									<div class="itempic">
										<a href="<%=request.getContextPath()%>/frontend/shop/shopItemDetail.jsp?item_no=${itemVO.item_no}">
											<img src="<%=request.getContextPath()%>/item_pics/item_pics.do?action=getOne_Pic_Display&item_pic_no=${item_picsVO.item_pic_no}"/> 
										</a>
									</div>
								</c:forEach>
							</div>
						
							<div class="itemdetail">
								<span class="itemdescribe">${itemVO.item_name}</span> 
								<span class="itemprice">$ ${itemVO.item_price}</span>
							</div>
						
						</div>

					</c:forEach>

				</div>
			</div>
		</div>
	</div>
	<!-- Footer Section Start -->

	<!-- Footer Section End -->
	<!-- Js Plugins -->
	<%@ include file="/frontend/files/commonJS.file" %>
	<script src="${pageContext.request.contextPath}/js/slick.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/front/frontShopPage.js"></script>
</body>
</html>