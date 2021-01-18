<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.List"%>
<%@ page import="com.roomrsv.model.*"%>
<%@ page import="com.members.model.*"%>
<%@ page import="com.roomtype.model.*"%>
<%@ page import="org.json.JSONObject"%>


<jsp:useBean id="rmtypeSvc" scope="page"
	class="com.roomtype.model.RoomTypeService" />
<jsp:useBean id="rmpicSvc" scope="page"
	class="com.roompic.model.RoomPicService" />
<%@ include file="/frontend/files/login.file"%>
<%
	int total = 0;
List<JSONObject> bookingCart = (List<JSONObject>) session.getAttribute("bookingCart");
if (bookingCart != null) {
	total = bookingCart.stream().mapToInt(e -> Integer.parseInt(e.getString("subtotal"))).sum();
	pageContext.setAttribute("total", total);
}
%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/png"
	href="<%=request.getContextPath()%>/img/loading.png" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css"
	integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA=="
	crossorigin="anonymous" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/front/creditCard.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/nice-select.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/slick-theme.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/slick.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/front/style-for-all.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/front/booking.css" />
<script src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
<title>戴蒙訂房系統</title>
</head>
<%@ include file="/frontend/files/loginCSS.file"%>
<style>
/* 微調登入頁面*/
.cont p.forgot-pass {
	margin: 15px;
}

.img__btn {
	border: 2px solid white;
	border-radius: 20px;
}

.img__btn:after {
	content: none;
}
</style>
<body>
	<%@ include file="/frontend/files/loginbox.file"%>
	<!-- preloader -->
	<div id="preloder">
		<img id="preloaderpic"
			src="${pageContext.request.contextPath}/img/loading.png" />
		<div class="loader"></div>
	</div>
	<!-- preloader -->
	<div class="curtain"></div>
	<!-- header start -->
	<header class="booking-header">
		<div class="member-section">
			<c:choose>
				<c:when test="${member != null}">
					<i class="far fa-gem"></i>
					<ul class="dropdown">
						<li><a
							href="${pageContext.request.contextPath}/frontend/members/memberInfo.jsp">個人檔案</a></li>
						<li><a href="${pageContext.request.contextPath}/frontend/members/memberBooking.jsp">我的假期</a></li>
						<li><a
							href="${pageContext.request.contextPath}/LoginHandler?mb_email=${member.mb_email}&action=member-logout&location=${pageContext.request.requestURL}">登出</a></li>
					</ul>
				</c:when>
				<c:otherwise>
					<i class="far fa-user"></i>
					<ul class="dropdown">
						<li><a class="log-in">登入會員</a></li>
						<li><a
							href="${pageContext.request.contextPath}/frontend/registration.jsp">註冊會員</a></li>
					</ul>

				</c:otherwise>
			</c:choose>
		</div>
		<div class="logo">
			<img src="<%=request.getContextPath()%>/img/logo.png" alt="" />
		</div>
	</header>
	<!-- header end -->
	<div class="banner-pic">
		<img src="<%=request.getContextPath()%>/img/booking-bg.jpeg" alt="" />
	</div>
	<h4 class="checkout-title">
		<i class="fas fa-medal medal"></i>年度最佳度假村
	</h4>
	<h4 class="checkout-title">
		<i class="fas fa-luggage-cart"></i>購物車清單
	</h4>
	<!-- 主頁面 -->
	<div class="main-wrapper">
		<div class="content">
			<div class="show-all-booking">
				<h4 class="isempty">尚未選取預定房型</h4>
				<%
					int i = 1;
				%>
				<c:forEach var="roomCard" items="${bookingCart}">
					<div class="room-card">
						<div class="booking-item-title">
							<h4>
								房間
								<%=i++%></h4>
						</div>
						<div class="booking-date">
							<img class="booking-icons"
								src="<%=request.getContextPath()%>/img/icon/calendar.png" />
							<h4>${roomCard.getString("startDate")}-
								${roomCard.getString("leaveDate")}
								(${roomCard.getString("stay")} 晚)</h4>
						</div>
						<div class="booking-guest">
							<img class="booking-icons"
								src="<%=request.getContextPath()%>/img/icon/user.png" />
							<h4>${roomCard.getString("guest")}成人</h4>
						</div>

						<div class="booking-short-intro">
							<h3 class="room-title">${rmtypeSvc.getOne(roomCard.getString("rmtype")).type_name}</h3>
							<c:forEach var="rmtypepic"
								items="${rmpicSvc.getAllByRoomType(roomCard.getString('rmtype'))}"
								begin="0" end="0">
								<img
									src="<%=request.getContextPath()%>/RoomPicServlet?rmpicno=${rmtypepic.rm_pic_no}&action=getOneRmPic" />
							</c:forEach>
						</div>
						<div class="cancel-rules">
							<h5>退費政策</h5>
							<div class="rule">
								<p>取消日期</p>
								<p>取消日期計費比例</p>
							</div>
							<div class="rule">
								<p>13~4天之前</p>
								<p>收取訂金30%</p>
							</div>
							<div class="rule">
								<p>3~1天之前</p>
								<p>收取訂金70%</p>
							</div>
							<div class="rule">
								<p>當天取消</p>
								<p>收取訂金100%</p>
							</div>
						</div>

						<div class="booking-total-price">
							<h5>價格小計</h5>
							<h4>
								<span>USD$</span><span class="subtotal"> <fmt:formatNumber
										type="number" pattern="#,###"
										value="${roomCard.getString('subtotal')}" />
								</span> <span class="etc">＊價格已含稅,服務費</span>
							</h4>
						</div>
						<div class="remove-button">
							<button data-rmtype="${roomCard.getString('rmtype')}"
								data-id="${roomCard.getString('roomCardId')}"
								class="remove-booking">
								<i class="far fa-trash-alt"></i>移除
							</button>
						</div>
					</div>
				</c:forEach>
			</div>

			<div class="variables booking-confirm">
				<div class="last-price">
					<p>Total Price</p>
					<h3 class="last-price">
						USD$<span></span>
					</h3>
				</div>
				<p>須預付 30% 訂金作為訂房費用</p>
				<div class="last-price">
					<p>Deposit Rate</p>
					<h3 class="deposit-price">
						USD$<span></span>
					</h3>
				</div>
				<div class="booking-confirm-actions">
					<a class="pay-now">付款預訂</a> <a class="add-more-room"
						href="<%=request.getContextPath()%>/booking/Available?<%=request.getQueryString()%>">追加房間</a>
				</div>
			</div>
		</div>
	</div>
	<jsp:useBean id="paymentSvc" scope="page"
		class="com.payment.model.PaymentService" />
	<div id="payment-info">
		<h2>請選擇付款信用卡</h2>
		<div class="credit-cards">
			<c:forEach var="paymentVO"
				items="${paymentSvc.getAllByMbId(member.mb_id)}">
				<div class="creditcard">
					<h4 class="cardnumber">${paymentVO.card_no}</h4>
					<h6>CARDHOLDER NAME</h6>
					<p class="cardholder">${paymentVO.card_name}</p>
					<p class="exp">${paymentVO.exp_mon}/${paymentVO.exp_year}</p>
					<input name="pay_no" class="pay_no" style="display: none"
						value="${paymentVO.pay_no}">
					<div class="creditcard-logo">
						<img src="<%=request.getContextPath()%>/img/creditcard/master.png" />
					</div>
				</div>
			</c:forEach>
		</div>
		<button class="add-creditcard">新增信用卡</button>
		<button class="leave-payment">取消付款</button>
		<%@ include file="/frontend/files/addCreditCard.file"%>
	</div>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/imask/3.4.0/imask.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/front/creditCard.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
	<script src="<%=request.getContextPath()%>/js/front/main.js"></script>
	<script>
	$(document).ready(function () {
	    totalPrice(); //進入頁面後先計算總價
	    isEmpty();
	    //從購物車移除
	    $(document).on("click", ".remove-booking", function () {
	        let id = $(this).attr("data-id");
	        let rmtype = $(this).attr("data-rmtype");
	        let card = $(this);
	        $.ajax({
	            url: "<%=request.getContextPath()%>/booking/Available?action=removefromcart",
	            type: "POST",
	            data: {
	                roomCardId: id,
	            },
	            success: function () {
	                card.parents(".room-card").animate({ opacity: 0 }, 500, function () {
	                    card.parents(".room-card").remove();
	                    totalPrice();
	                    isEmpty();
	                });
	            },
	        });
	    });
	    
	    function isEmpty(){
	    	let items = $(".room-card").length;
	    	if (items == 0){
	    		$(".isempty").css("display", "block")
	    	} else {
	    		$(".isempty").css("display", "none")
	    	}
	    }
	    //顯示付款頁面
	    $(".pay-now").click(function () {
	    	let items = $(".room-card").length;
	        if ('<%=session.getAttribute("member")%>' == "null") {
	            $(".login-window-overlay").addClass("active");
	            $(".login-window").addClass("show-login-window");
	        } else if (items == 0){
	           	Swal.fire({
	           		position:"center",
	           		showConfirmButton: false,
	           		icon:"error",
	           		title:"購物車目前無商品",
	           		timer: 1500
	           	})
	        } else {
	        	 $("#payment-info").addClass("show-payment-info");
	        }
	    });
	    $(".log-in").click(function (e) {
	    	 e.preventDefault();
	         $(".login-window-overlay").addClass("active");
	         $(".login-window").addClass("show-login-window");
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
	    
	    function errorfire(title){
	    	Swal.fire({
                position: "center",
                icon: "error",
                title: title,
                showConfirmButton: false,
                timer: 1000,
            });
	    }
	    //新增信用卡
	    $("#insertnewcard").click(function () {
	    	let inputs = $(".form-container input");
	    	
	        let cardname = $("#name").val();
	        if (cardname == "") {
	        	errorfire("姓名未填寫");
	        	return;
	        }
	        let cardno = $("#cardnumber").val();
	        if (cardno == ""){
	        	errorfire("卡號未填寫");
	        	return;
	        }
	        let expmon = $("#expirationdate").val().split("/")[0];
	        let expyear = $("#expirationdate").val().split("/")[1];
	        if(expmon.match("^[0-9]{2}$") == null || expyear.match("^[0-9]{2}$") == null ) {
	        	errorfire("有效日期未填寫");
	        	return;
	        }
	        let csc = $("#securitycode").val();
	        if (csc == ""){
	        	errorfire("CSC未填寫");
	        	return;
	        }
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
	    $(document).on("click", ".creditcard", function(){  //點擊信用卡後付款
	    	let card_no = $(this).find(".cardnumber").text();
	    	window.location.href = "<%=request.getContextPath()%>/bookingServlet?action=insert_bkod&card_no=" + card_no;
	    })
	    function totalPrice() {
	        let prices = $("span.subtotal");
	        let total = 0;
	        $.each(prices, function (index, value) {
	            total += parseInt(value.innerText.replace(",", ""));
	        });

	        $(".last-price span").eq(0).text(total.toLocaleString());
	        $(".deposit-price span")
	            .eq(0)
	            .text((total*0.3).toLocaleString());
	    }
	});

		</script>
</body>
</html>