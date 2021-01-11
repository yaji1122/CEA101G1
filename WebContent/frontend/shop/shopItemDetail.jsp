<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.item.model.*"%>
<%@ page import="com.item_type.model.*"%>
<%@ page import="com.item_pics.model.*"%>
<%@ page import="com.members.model.*"%>
	<jsp:useBean id="item_picsSvc" scope="page" class="com.item_pics.model.Item_picsService" />
	<jsp:useBean id="item_typeSvc" scope="page" class="com.item_type.model.Item_typeService" />
	<jsp:useBean id="itemSvc" scope="page" class="com.item.model.ItemService" />
<%
	String item_no = request.getParameter("item_no");
	System.err.println("item_no = " + item_no);
	ItemVO item = itemSvc.getOneItem(item_no);
	pageContext.setAttribute("item_no", item_no);
	pageContext.setAttribute("item", item);
	List<ItemVO> list = itemSvc.getAllItem();
	pageContext.setAttribute("list", list);
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
	href="${pageContext.request.contextPath}/css/front/shoppagedetail.css"
	type="text/css" />

</head>
<%@ include file="/frontend/files/loginCSS.file" %>
<body>
<%-- 	<%@ include file="/frontend/files/login.file" %> --%>
<%
String sessionID = null;
String mb_email = null;
MembersVO member = null;
member = (MembersVO) session.getAttribute("member");
sessionID = (String) session.getAttribute("user_session_id");
if (member == null && sessionID == null) { //表示session已失效
	Cookie[] cookieList = request.getCookies();
	if (cookieList != null) {
		for (int i = 0; i < cookieList.length; i++) {
			Cookie theCookie = cookieList[i];
			if (theCookie.getName().equals("user_session_id")) {
				sessionID = theCookie.getValue();
			}
			if (theCookie.getName().equals("user_email")) {
				mb_email = theCookie.getValue();
			}
		}
	}
	if (mb_email != null) {
		MembersService memberSvc = new MembersService();
		member = memberSvc.getOneByMbEmail(mb_email);
		session.setAttribute("member", member);
		session.setAttribute("sessionID", sessionID);//作為最近瀏覽使用
	} else if (sessionID != null && mb_email == null) { //非會員，取得先前的SessionID，存入session
		session.setAttribute("sessionID", sessionID);
	} else { //如果都沒有，紀錄sessionID存在使用者cookie，追蹤使用
		Cookie user_session_id = new Cookie("user_session_id", session.getId());
		user_session_id.setMaxAge(24 * 60 * 60 * 30); //有效期30天
		response.addCookie(user_session_id);
		session.setAttribute("user_session_id", session.getId());
	}
}
%>
<%@ include file="/frontend/files/loginbox.file"%>
 	<% 
 	if(member!=null){
		String mb_id = member.getMb_id();
		System.out.println("mb_id = "+mb_id);
		sessionID = (String) session.getAttribute("sessionID");
		session.setAttribute("sessionID", sessionID);
	} 
 	System.out.println("user_session_id = " + sessionID);
 	System.out.println("item_no = " + item_no);

	Map<String,List<String>> mapIn = (Map<String,List<String>>)session.getAttribute("map");
	if(mapIn==null){
		System.out.println("map==null");
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		map.put(sessionID, new ArrayList<String>(Arrays.asList(item_no)));
		session.setAttribute("map", map);
	} else{
		System.out.println("map!=null");
		for(String aitem :mapIn.get(sessionID)){
			if(item_no.equals(aitem)){
				continue;
			}else{
				mapIn.get(sessionID).add(item_no);
				session.setAttribute("map", mapIn);
				break;
			}
		}
	}
	Map<String,List<String>> map = (Map<String,List<String>>)session.getAttribute("map");	
	%> 

	<div class="back"></div>
	<!-- Page Preloder -->
	<div id="preloder">
		<img id="preloaderpic"
			src="${pageContext.request.contextPath}/img/loading.png" />
		<div class="loader"></div>
	</div>

	<!-- Offcanvas Menu Section Begin -->

		<div class="shopping-cart">
			<div class="cart-border">
				<div class="cart-title">ADDED TO CART<div class="close" id="cart-title-close">✖</div></div>
				<div class="cart-item" id="${cartList.item_no}">
					<c:forEach var="item_picsVO" begin="0" end="0" items="${item_picsSvc.getAllPics(item_no)}">
						<div class="cart-item-pic">
							<img src="<%=request.getContextPath()%>/item_pics/item_pics.do?item_pic_no=${item_picsVO.item_pic_no}&action=getOne_Pic_Display"/>
						</div>
					</c:forEach>
					<div class="cart-item-bor">
						<div class="cart-item-name"><%=itemSvc.getOneItem(item_no).getItem_name()%></div>
						<div class="cart-item-no"><%=itemSvc.getOneItem(item_no).getItem_no()%></div>  
			 			<div class="cart-item-price">$<%=itemSvc.getOneItem(item_no).getItem_price()%></div> 
			 			<div class="cart-points">Points: <%=itemSvc.getOneItem(item_no).getPoints()%></div>
			 			<div class="cart-but-bor">
			 				<div class="close" id="conShop">Continue Shopping</div>
							<a class="cart-view" href="<%=request.getContextPath()%>/frontend/shop/shopCartRedis.jsp">View my cart</a>
						</div>
					</div>
				</div>
			</div>
		</div>
        
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
						<li><a href="#">我的足跡</a></li>
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
								<li><a href="${pageContext.request.contextPath}/frontend/shop/shopAllOrder.jsp">購物訂單</a></li>
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

	<div class="itemtypeheader">
		<div id="itemtype">
			<div id="itemsmpic">
				<img
					src="<%=request.getContextPath()%>/item_pics/item_pics.do?item_pic_no=<%=item_picsSvc.getAllPics(item_no).get(0).getItem_pic_no()%>&action=getOne_Pic_Display"
					alt="">
			</div>
			<div id="itemtypename"><%=itemSvc.getOneItem(item_no).getItem_name()%></div>
		</div>
		<form METHOD="post"
			ACTION="<%=request.getContextPath()%>/shop/shoppingRedisCart.do"
			enctype="multipart/form-data">
			<button type="button" class="itemaddtocart" id="itemadd">Place in Cart</button>
<!-- 			<button id="itemadd" name="action" value="ADD">Place in Cart</button> -->
<%-- 			<input type="hidden" name="item_no" value="<%=itemSvc.getOneItem(item_no).getItem_no()%>">  --%>
<%-- 			<input type="hidden" name="item_price" value="<%=itemSvc.getOneItem(item_no).getItem_price()%>">  --%>
<%-- 			<input type="hidden" name="item_name" value="<%=itemSvc.getOneItem(item_no).getItem_name()%>">  --%>
<%-- 			<input type="hidden" name="points" value="<%=itemSvc.getOneItem(item_no).getPoints()%>">  --%>
<!-- 			<input type="hidden" name="quantity" value="1" > -->
			
		</form>
		<div id="itemsmprice">
			$<%=itemSvc.getOneItem(item_no).getItem_price()%>
		</div>
	</div>
	</header>

	<!-- Header Section End -->
	<div class="wrapper">
		<div class="item">
			<div class="picborder">
				<div class="sliderpics">
					<c:forEach var="item_picsVO"
						items="${item_picsSvc.getAllPics(item_no)}">
						<div class="picsize">
							<img
								src="<%=request.getContextPath()%>/item_pics/item_pics.do?item_pic_no=${item_picsVO.item_pic_no}&action=getOne_Pic_Display"
								alt="" />
						</div>
					</c:forEach>
				</div>
			</div>

			<div class="iteminfo">

				<div class="itemtypeno"><%=itemSvc.getOneItem(item_no).getItem_no()%></div>
				<div class="itemdename"><%=itemSvc.getOneItem(item_no).getItem_name()%></div>
				<div class="itemdeprice">
					$<%=itemSvc.getOneItem(item_no).getItem_price()%>
				</div>
				<div class="itempoints">Points: <%=itemSvc.getOneItem(item_no).getPoints()%>
				</div>
				<div class="itemdede"><%=itemSvc.getOneItem(item_no).getItem_detail()%></div>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/shop/shoppingRedisCart.do"
					enctype="multipart/form-data">
						<button type="button" class="itemaddtocart">Place in Cart</button>
					
					
<!-- 					<button name="action" value="ADD" class="itemaddtocart"> -->
<!-- 					Place in Cart -->
<!-- 					</button> -->

<%-- 					<input type="hidden" name="item_no" value="<%=itemSvc.getOneItem(item_no).getItem_no()%>">  --%>
<%-- 					<input type="hidden" name="item_price" value="<%=itemSvc.getOneItem(item_no).getItem_price()%>"> --%>
<%-- 					<input type="hidden" name="item_name" value="<%=itemSvc.getOneItem(item_no).getItem_name()%>">  --%>
<%-- 					<input type="hidden" name="points" value="<%=itemSvc.getOneItem(item_no).getPoints()%>">  --%>
<!-- 					<input type="hidden" name="quantity" value="1"> -->
				</FORM>
			</div>
		</div>
		<div class="otheritem">
			<div class="otheritemtitle">YOU MAY ALSO LIKE</div>
			<div class="titlename">
				<div id="recent">Recently Viewed</div>
				<div id="recomm">Recommended</div>
			</div>
			
			<div class="otheritemborder" id="otherRecomm">
				<div class="row">
					<c:forEach var="itemVO" items="<%=itemSvc.getAllByItem_type_no(item.getItem_type_no())%>" begin="0" end="2">
						<div class="col col-12 col-sm-6 col-md-4">
							<div class="itemslider">
								<c:forEach var="item_picsVO" begin="0" end="0"
									items="${item_picsSvc.getAllPics(itemVO.item_no)}">
									<div class="itempic">
										<a href="<%=request.getContextPath()%>/frontend/shop/shopItemDetail.jsp?item_no=${itemVO.item_no}">
											<img src="<%=request.getContextPath()%>/item_pics/item_pics.do?item_pic_no=${item_picsVO.item_pic_no}&action=getOne_Pic_Display"/>
										</a>
									</div>
								</c:forEach>
							</div>
							<div class="itemdetail">
								<span class="itemdescribe">${itemVO.item_name}</span> <span
									class="itemprice">$ ${itemVO.item_price}</span>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
						
 			<div class="otheritemborder" id="otherRecently"> 
 				<div class="row"> 
 				
 					<c:forEach var="itemno" items="${map.get(sessionID)}" begin="0" end="2">		 
 						<div class="col col-12 col-sm-6 col-md-4"> 
 							<div class="itemslider"> 
 								<c:forEach var="item_picsVO" begin="0" end="0" 
 									items="${item_picsSvc.getAllPics(itemno)}"> 
 									<div class="itempic"> 
 										<a href="<%=request.getContextPath()%>/frontend/shop/shopItemDetail.jsp?item_no=${itemno}"> 
 											<img src="<%=request.getContextPath()%>/item_pics/item_pics.do?item_pic_no=${item_picsVO.item_pic_no}&action=getOne_Pic_Display"/> 
 										</a> 
 									</div> 
 								</c:forEach> 
 							</div> 
 							<div class="itemdetail"> 
 								<span class="itemdescribe"> ${itemSvc.getOneItem(itemno).item_name}</span>  
 								<span class="itemprice">$ ${itemSvc.getOneItem(itemno).item_price}</span> 
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
	<script src="${pageContext.request.contextPath}/js/front/shopdetail.js"></script>
	<script>
	$(function(){
		var itemAddInCart = {
			"action":"ADD",
			"item_no": <%="\"" + itemSvc.getOneItem(item_no).getItem_no() + "\""%>,
			"item_price": <%="\"" + itemSvc.getOneItem(item_no).getItem_price() + "\""%>,
			"item_name": <%="\"" + itemSvc.getOneItem(item_no).getItem_name() + "\""%>,
			"points": <%="\"" + itemSvc.getOneItem(item_no).getPoints() + "\""%>,
			"quantity":1,
		};
		
		$(".itemaddtocart").click(function(event){
			event.stopPropagation();
			console.log(<%="\"" + itemSvc.getOneItem(item_no).getItem_no() + "\""%>);
			$.ajax({
				data:itemAddInCart,
				type:"POST",
				//dataType:"json",
				url:"<%=request.getContextPath()%>/shop/shoppingRedisCart.do",
				success:function(data){
					$(".shopping-cart").addClass("shopping-cart-show");
					$(".back").css("display", "block");
				}
			});
		});
	});
	$(".close").click(function () {
  		$(".shopping-cart").removeClass("shopping-cart-show");
  		$(".back").css("display", "none");
	});
	$("#recent").click(function(){
		$("#otherRecently").css("display", "block");
		$("#otherRecomm").addClass("recom-hide");
	});
	$("#recomm").click(function(){	
		$("#otherRecently").css("display", "none");
		$("#otherRecomm").removeClass("recom-hide");
	});
	</script>
</body>
</html>