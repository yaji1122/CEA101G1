<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.item.model.*"%>
<%@ page import="com.members.model.*"%>
<%@ page import="com.item_pics.model.*"%>
<%@ page import="com.shoppingCart.model.*"%>
<jsp:useBean id="item_picsSvc" scope="page" class="com.item_pics.model.Item_picsService" />
<jsp:useBean id="itemSvc" scope="page" class="com.item.model.ItemService" />
<jsp:useBean id="membersSvc" scope="page" class="com.members.model.MembersService" />
<!DOCTYPE html>
<html lang="en">
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
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/front/creditCard.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/front/booking.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/front/shoppage.css" type="text/css" />
</head>
<%@ include file="/frontend/files/loginCSS.file"%>
<body>
	<%@ include file="/frontend/files/login.file"%>
	<%@ include file="/frontend/files/loginbox.file" %>
	<%
		String mb_id = member.getMb_id();
		System.out.println("mb_id = "+mb_id);		
	%>	
	<!-- Page Preloder -->
	<div id="preloder">
		<img id="preloaderpic" src="${pageContext.request.contextPath}/img/loading.png" />
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
				<img src="img/flag.jpeg" alt="" /> 
				<span>TW<i class="fa fa-angle-down"></i></span>
			</div>
			<a href="#" class="bk-btn">立刻訂房</a>
		</div>
		<nav class="mainmenu mobile-menu">
			<ul>
				<li><a href="./pages.html" class="nav-event">會員中心</a>
					<ul class="dropdown">
						<li><a href="./room-details.html">個人檔案</a></li>
						<li><a href="./blog-details.html">我的假期</a></li>
					</ul>
				</li>
				<li><a class="nav-event">住客專區</a>
					<ul class="dropdown">
						<li><a href="./activity.html">活動報名</a></li>
						<li><a href="">預約服務</a></li>
						<li><a href="">訂購餐點</a></li>
					</ul>
				</li>
				<li><a href="shop.html" class="nav-evnet">戴蒙商城</a></li>
				<li><a class="nav-event" href="./rooms.html">渡假空間</a>
					<ul class="dropdown">
						<li><a href="#">戴蒙經典房</a></li>
						<li><a href="#">豪華蜜月房</a></li>
						<li><a href="#">奢華海景房</a></li>
						<li><a href="#">波賽頓套房</a></li>
						<li><a href="#">公共空間</a></li>
					</ul>
				</li>
				<li><a class="nav-event" href="./pages.html">精彩活動</a>
					<ul class="dropdown">
						<li><a href="./room-details.html">陸上活動</a></li>
						<li><a href="./room-details.html">海上活動</a></li>
						<li><a href="./room-details.html">網紅行程</a></li>
					</ul>
				</li>
				<li><a href="" class="nav-event">會員登入</a></li>
				<li><a href="" class="nav-event" style="color: rgb(240, 218, 162)">加入會員</a></li>
			</ul>
		</nav>
		<div id="mobile-menu-wrap"></div>
		<div class="top-social">
			<a href="#"><i class="fa fa-facebook"></i></a> 
			<a href="#"><i class="fa fa-twitter"></i></a> 
			<a href="#"><i class="fa fa-tripadvisor"></i></a> 
			<a href="#"><i class="fa fa-instagram"></i></a>
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
							<a href="${pageContext.request.contextPath}/frontend/index.jsp">
								<img src="${pageContext.request.contextPath}/img/logo.png" /> 
							</a>
						</div>
						<li class="nav-list">
							<a class="nav-event" href="<%=request.getContextPath()%>/frontend/shop/shopPage.jsp">HOME</a>
						</li>

						<li class="nav-list"><a class="nav-event">DIAMOND CLASSIC</a>
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
								<c:forEach var="item_typeVO" items="${item_typeSvc.allItem_type}" begin="3" end="5">
									<li value="${item_typeVO.item_type_no}" class="chtype">
										<a href="<%=request.getContextPath()%>/frontend/shop/shopPage.jsp?item_type_no=${item_typeVO.item_type_no}">${item_typeVO.type_name}</a>
									</li>
								</c:forEach>
							</ul>
						</li>

						<li class="nav-list"><a class="nav-evnet">SEASONAL GOODS</a>
							<ul class="dropdown">
								<c:forEach var="item_typeVO" items="${item_typeSvc.allItem_type}" begin="6">
									<li value="${item_typeVO.item_type_no}" class="chtype">
										<a href="<%=request.getContextPath()%>/frontend/shop/shopPage.jsp?item_type_no=${item_typeVO.item_type_no}">${item_typeVO.type_name}</a>
									</li>
								</c:forEach>
							</ul>
						</li>

						<li class="nav-list">
							<a class="nav-event" href="<%=request.getContextPath()%>/frontend/shop/shopCartRedis.jsp">
								<i class="fas fa-shopping-cart icon"></i>
							</a>
						</li>

						<li class="nav-list"><a class="nav-event"> 
						<c:choose>
							<c:when test="${member != null}">
											<i class="far fa-gem"></i>
											</i>會員中心</a>
								<ul class="dropdown">
									<li><a href="${pageContext.request.contextPath}/frontend/members/memberInfo.jsp">個人檔案</a></li>
									<li><a href="#">我的假期</a></li>
									<li><a href="${pageContext.request.contextPath}/frontend/shop/shopAllOrder.jsp">購物訂單</a></li>
									<li>
										<a href="${pageContext.request.contextPath}/LoginHandler?mb_email=${member.mb_email}&action=member-logout&location=${pageContext.request.requestURL}">登出</a>
									</li>
								</ul>
							</li>
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
				Integer poamount = (Integer)request.getAttribute("poamount");
				request.setAttribute("poamount",poamount);
				
				CartService cartSVC = new CartService();
			%>
				<div class="rightside">
					<br>
					<div class="outframe">
						<div>
							<h1>結帳前確認 Check</h1>
						</div>	
						<form method="POST"
							action="<%=request.getContextPath()%>/shop_order/shop_order.do">
						<table class="cartlist">
							<thead>
								<tr>
									<th></th>
									<th class="itThBo">商品編號</th>
									<th class="itThBo">商品名稱</th>
									<th class="itThBo">商品價格</th>
									<th class="itThBo">數量</th>
									<th class="itThBo">積分</th>
									<th class="itThBo">積分小計</th>
									<th class="itThBo">價格小計</th>
								</tr>
							</thead>						
						<% for (int i = 0; i < buylist.size(); i++) {
							ItemVO order = buylist.get(i);
						%>
							<tr class="checkBor">
								<td class="imgframe">
									<img src="<%=request.getContextPath()%>/item_pics/item_pics.do?item_pic_no=<%=item_picsSvc.getAllPics(order.getItem_no()).get(0).getItem_pic_no()%>&action=getOne_Pic_Display">
								</td>
								<td class="itTdBo"><%=order.getItem_no()%></td>
								<td class="itTdBo"><%=itemSvc.getOneItem(order.getItem_no()).getItem_name()%></td>
								
								
								<td><span>$ </span><%=order.getItem_price()%></td>
								<td class="itTdBo"><%=order.getQuantity()%></td>
								<td class="itTdBo"><%=order.getPoints()%></td>
								<td class="itTdBo"><%= (cartSVC.getValueByItem_no(mb_id,order.getItem_no()))*(order.getPoints())%></td>
								<td><span>$ </span><%= (cartSVC.getValueByItem_no(mb_id,order.getItem_no()))*(order.getItem_price())%></td>
							</tr>
						<% }%>
							<tr>
								<td colspan="4">
								<td>獲得總積分</td>
								<td><%=poamount %></td>
							</tr>
							<tr>
								<td colspan="4">
								<td>可使用積分</td>
								<%MembersVO mem = membersSvc.getOneByMbId(mb_id); %>
								<td><input type="number" id="pointUsed" max="<%=mem.getMb_point()%>" min="0" name="pointCos" value="0"></td>
								<td><span id="poiCanUse"><%=mem.getMb_point()%></span>分可用</td>
								<td><div type="button" id="hitTocom">使用積分</div></td>
							</tr>
							<tr>
								<td colspan="4">
								<td>總金額</td>
								<td><span>$</span><span id="priceAfPo"> <%=amount %></span></td>
							</tr>
						</table>
						<br>																									
							
							<button class="paybtn" type="button">確定購買</button>
										
	<jsp:useBean id="paymentSvc" scope="page" class="com.payment.model.PaymentService" />
							<div id="payment-info">
							<h2>請選擇付款信用卡</h2>
							<div class="credit-cards">
								<c:forEach var="paymentVO" items="${paymentSvc.getAllByMbId(member.mb_id)}">
									<div class="creditcard" type="submit">
										<h4 class="cardnumber">${paymentVO.card_no}</h4>
										<h6>CARDHOLDER NAME</h6>
										<p class="cardholder">${paymentVO.card_name}</p>
										<p class="exp">${paymentVO.exp_mon}/${paymentVO.exp_year}</p>
										<input name="pay_no" class="pay_no" style="display: none" value="${paymentVO.pay_no}">
										<div class="creditcard-logo">
											<img src="<%=request.getContextPath()%>/img/creditcard/master.png" />
										</div>
									</div>
								</c:forEach>
							</div>
							
							<input type="hidden" name="action" value="insertWithOrder_details"> 
							<input type="hidden" name="mb_id" value="${member.mb_id}">
							<input type="hidden" name="total_price" id="sendPri"value="<%=amount%>">
							<input type="hidden" name="points_total" id="sendPoi"value="<%=poamount%>">  
							<button class="add-creditcard"  type="button">新增信用卡</button>
							<button class="leave-payment"  type="button">取消付款</button>
							<button class="admit-payment">確認付款</button>
							<%@ include file="/frontend/files/addCreditCard.file"%>							
					     </div>		
					     					
					</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/frontend/files/commonJS.file" %>
		<script src="${pageContext.request.contextPath}/js/slick.min.js"></script>
		<script src="<%=request.getContextPath()%>/js/front/creditCard.js"></script>		
		<script src="https://cdnjs.cloudflare.com/ajax/libs/imask/3.4.0/imask.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/front/frontShopPage.js"></script>
	<script>
//積分使用	
$("#pointUsed").change(function(){
	var poCaUs = $("#poiCanUse").text();
	var oraPri = <%=amount %>;
	var poiUs = $("#pointUsed").val();
	console.log("oraPri - poiUs="+oraPri - poiUs);
	console.log("poiUs="+poiUs);
	console.log("poCaUs="+poCaUs);
	if(poCaUs < poiUs || poiUs > oraPri){
		$("#pointUsed").val(0);
		$("#priceAfPo").val(<%=amount %>);
		Swal.fire({
			  title: '錯誤的積分',
			  text: '請輸入正確的積分,再進行結帳',
			  icon: 'warning'
			});
	}else{
		$("#priceAfPo").html(oraPri - poiUs +".0");
		$("#sendPri").val(oraPri - poiUs);
		$("#sendPoi").val(poCaUs - poiUs);
	}
});
	
	$(document).ready(function () {
    //顯示付款頁面
    $(".paybtn").click(function () {
        if ('<%=session.getAttribute("member")%>' == "null") {
            $(".login-window-overlay").addClass("active");
            $(".login-window").addClass("show-login-window");
        } else {
            $("#payment-info").addClass("show-payment-info");
        }
    });
    //離開付款選單
    $("button.leave-payment").click(function () {
        $("#payment-info").removeClass("show-payment-info");
    });

    $(".add-creditcard").click(function () {
        //新增信用卡畫面
        $("#creditCard-input-view").css("opacity", "1");
        $("#creditCard-input-view").css("z-index", "99");
        $("#cancelinsert").click(() => {
            $("#creditCard-input-view").css("opacity", "0");
            $("#creditCard-input-view").css("z-index", "-1");
        });
    });
    //新增信用卡
    $("#insertnewcard").click(function () {
        let cardname = $("#name").val();
        let cardno = $("#cardnumber").val();
        let expmon = $("#expirationdate").val().split("/")[0];
        let expyear = $("#expirationdate").val().split("/")[1];
        let csc = $("#securitycode").val();
        let mbid = "${member.mb_id}";
        $.ajax({
            url: "<%=request.getContextPath()%>/PaymentServlet?action=insert_credit_card",
            data: {
                card_name: cardname,
                card_no: cardno,
                exp_mon: expmon,
                exp_year: expyear,
                csc: csc,
                mb_id: mbid,
            },
            type: "POST",
            success: function (msg) {
                let obj = JSON.parse(msg);
                if (obj.status == "success") {
                    Swal.fire({
                        position: "center",
                        icon: "success",
                        title: "已新增付款方式",
                        showConfirmButton: false,
                        timer: 1000,
                    });
                    let fragment = document.createElement("div");
                    fragment.classList.add("creditcard");
                    fragment.innerHTML =
                        `
                                <h4 class="cardnumber">` +
                        cardno +
                        `</h4>
                                <h6>CARDHOLDER NAME</h6>
                                <p class="cardholder">` +
                        cardname +
                        `</p>
                                <p class="exp">` +
                        expmon +
                        `/` +
                        expyear +
                        `</p>
                                <i class="fas fa-minus-circle delete-creditcard"></i>
                                <input name="pay_no" class="pay_no" style="display:none" value=` +
                        obj.payno +
                        `>
                                <div class="creditcard-logo">
                                    <img src="<%=request.getContextPath()%>/img/creditcard/master.png">
                                </div>
        						`;
                    $(".credit-cards").eq(0).prepend(fragment);
                    setTimeout(function () {
                        $("#creditCard-input-view").css("opacity", "0");
                        $("#creditCard-input-view").css("z-index", "-1");
                    }, 1000);
                } else {
                    Swal.fire({
                        position: "center",
                        icon: "error",
                        title: "伺服器忙線中，請稍後再試",
                        showConfirmButton: false,
                        timer: 1000,
                    });
                }
            },
        });
    });
});

	</script>	
	
</body>
</html>