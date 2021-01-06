<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="com.roomrsv.model.*"%>
<%@ page import="com.members.model.*"%>
<%@ page import="com.roomtype.model.*"%>
<%@ page import="org.json.JSONObject"%>
<%@ include file="/frontend/files/login.file"%>
<%
List<RoomRsvVO> rsvvoList = (List<RoomRsvVO>)request.getAttribute("rsvvoList");
pageContext.setAttribute("rsvvoList", rsvvoList);
JSONObject infoJson = (JSONObject) request.getAttribute("infoJson");
pageContext.setAttribute("infoJson", infoJson);
%>
<jsp:useBean id="rmtypeSvc" scope="page"
	class="com.roomtype.model.RoomTypeService" />
<jsp:useBean id="rmpicSvc" scope="page"
	class="com.roompic.model.RoomPicService" />
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
<title>戴蒙訂房系統</title>
</head>
<%@ include file="/frontend/files/loginCSS.file"%>
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
	<header class="booking-header">
		<div class="member-section">
			<c:choose>
				<c:when test="${member != null}">
					<i class="far fa-gem"></i>
					<ul class="dropdown">
						<li><a
							href="${pageContext.request.contextPath}/frontend/members/memberInfo.jsp">個人檔案</a></li>
						<li><a href="#">我的假期</a></li>
						<li><a href="#">歷史訂單</a></li>
						<li><a
							href="${pageContext.request.contextPath}/LoginHandler?mb_email=${member.mb_email}&action=member-logout&location=${pageContext.request.requestURL}">登出</a></li>
					</ul>
				</c:when>
				<c:otherwise>
					<i class="far fa-user log-in"></i>
					<ul class="dropdown">
						<li><a
							href="${pageContext.request.contextPath}/frontend/members/memberInfo.jsp">登入會員</a></li>
						<li><a
							href="${pageContext.request.contextPath}/frontend/registration.jsp">註冊會員</a></li>
					</ul>

				</c:otherwise>
			</c:choose>
		</div>
		<div class="logo">
			<img src="<%=request.getContextPath()%>/img/logo.png" alt="" />
		</div>
		<div class="shopping-cart">
			<i class="fas fa-luggage-cart"></i>
			<div class="counter">${bookingCart.size()}</div>
		</div>
		<div class="cart-list">
			<h3 class="cart-list-title">預訂清單</h3>
			<div class="cart-view-range">
				<c:forEach var="roomCard" items="${bookingCart}">
					<div class="room-in-cart">
						<div class="booking-date-div">
							<img class="cart-list-icons"
								src="<%=request.getContextPath()%>/img/icon/calendar.png" />
							<p class="booking-date">${roomCard.getString("startDate")}-
								${roomCard.getString("leaveDate")}</p>
						</div>
						<div class="pic-in-cart">
							<c:forEach var="rmtypepic"
								items="${rmpicSvc.getAllByRoomType(roomCard.getString('rmtype'))}"
								begin="0" end="0">
								<div>
									<img
										src="<%=request.getContextPath()%>/RoomPicServlet?rmpicno=${rmtypepic.rm_pic_no}&action=getOneRmPic" />
								</div>
							</c:forEach>
						</div>
						<h2>${rmtypeSvc.getOne(roomCard.getString('rmtype')).type_name}</h2>
						<br /> <img class="cart-list-icons"
							src="<%=request.getContextPath()%>/img/icon/user.png" />
						<p>${roomCard.getString("guest")}成人</p>
						<hr />
						<img class="cart-list-icons"
							src="<%=request.getContextPath()%>/img/icon/bed.png" />
						<p>1 間套房</p>
						<h3 class="single-price">
							<button data-rmtype="${roomCard.getString('rmtype')}"
								data-id="${roomCard.getString('roomCardId')}"
								class="delete-room-card">移除</button>
							<span>價格小計：</span>USD\$${roomCard.getString("subtotal")}
						</h3>
					</div>
				</c:forEach>
			</div>
			<c:if test="${bookingCart == null}">
				<h4 id="room-cart-empty">尚未選取商品</h4>
			</c:if>
			<h3 class="total-price">
				總價：<span>$</span>
			</h3>
			<a
				href="<%=request.getContextPath()%>/frontend/roomrsv/checkout.jsp?<%=request.getQueryString()%>"
				id="check-out">前往結賬</a>
		</div>
	</header>
	<div class="banner-pic">
		<img src="<%=request.getContextPath()%>/img/booking-bg.jpeg" alt="" />
	</div>
	<!-- 主頁面 -->
	<div class="main-wrapper">
		<div class="content">
			<div class="available-rooms">
				<c:if test="${rsvvoList.size() == 0 }">
					<div class="no-room-available">
						<h2>指定日期無符合條件之空房</h2>
					</div>
				</c:if>
				<c:forEach var="rsvvo" items="${rsvvoList}">
					<div class="room-card">
						<div class="room-pic">
							<c:forEach var="rmtypepic"
								items="${rmpicSvc.getAllByRoomType(rsvvo.rm_type)}">
								<div>
									<img
										src="<%=request.getContextPath()%>/RoomPicServlet?rmpicno=${rmtypepic.rm_pic_no}&action=getOneRmPic" />
								</div>
							</c:forEach>
						</div>
						<div class="room-infos">
							<h3 class="room-title">${rmtypeSvc.getOne(rsvvo.rm_type).type_name}</h3>
							<div class="room-cap room-info">
								<i class="fas fa-users"></i>
								1~${rmtypeSvc.getOne(rsvvo.rm_type).rm_capacity} Guest
							</div>
							<div class="room-space room-info">
								<i class="fas fa-expand-arrows-alt"></i> 220㎡
							</div>
							<div class="room-bed room-info">
								<i class="fas fa-bed"></i> King Size
							</div>
						</div>
						<div class="room-price">
							<p class="per-ppl">
								一晚每人<span>USD</span><span>${rmtypeSvc.getOne(rsvvo.rm_type).rm_price}</span>
							</p>
							<%
                            Integer stay = Integer.parseInt(infoJson.getString("stay"));
                            Integer guest = Integer.parseInt(infoJson.getString("guest"));
                            pageContext.setAttribute("stayxguest", stay*guest);
                            pageContext.setAttribute("stay", stay);
                            pageContext.setAttribute("guest", guest);
                            %>
							<h4>
								此為<%=stay%>晚1間的總計<span>USD$</span><span class="subtotal">${rmtypeSvc.getOne(rsvvo.rm_type).rm_price * stayxguest}</span><span
									class="etc">＊價格已含稅,服務費</span>
							</h4>
							<div class="room-left">
								<div>
									<i class="fas fa-exclamation-circle"></i>
									<p>
										尚餘<span class="room-left-number">${rsvvo.rm_left}</span>間客房
									</p>
								</div>
							</div>
						</div>
						<hr />
						<div class="room-intro">
							<p>${rmtypeSvc.getOne(rsvvo.rm_type).rm_info}</p>
						</div>
						<div class="room-checkout">
							<button class="add-to-cart" data-rmtype="${rsvvo.rm_type}"
								data-label="加入預定"></button>
						</div>
						<div class="check-room-detail">
							<p>房間詳情與服務</p>
							<img src="<%=request.getContextPath()%>/img/icon/down-arrow.png"
								alt="" />
						</div>
						<div class="room-detail">
							<div class="details">
								<img src="<%=request.getContextPath()%>/img/icon/bed.png" alt="" />
								<div>
									<p>
										<b>床型</b>
									</p>
									<p>
										床1 200 x110cm 西式房間1 <br />床1 200 x110cm 西式房間1<br /> 床1 200
										x110cm 西式房間1
									</p>
									<p class="etc">
										*如有需要，能夠更換成大床被褥（King-size、1套），請事先申請交換，入住當天申請的交換需額外收費。</p>
								</div>
							</div>
							<div class="details">
								<img src="<%=request.getContextPath()%>/img/icon/wifi.png"
									alt="" />
								<div>
									<p>
										<b>Wi-Fi上網</b>
									</p>
									<p>於所有客房和於部分公用區域提供：免費WiFi</p>
								</div>
							</div>
							<div class="details">
								<img src="<%=request.getContextPath()%>/img/icon/support.png"
									alt="" />
								<div>
									<p>
										<b>服務設施</b>
									</p>
									<p>閱讀書燈、熱飲電壺、迷你吧、保險箱、館內服、睡衣、拖鞋、牙刷、沐浴備品、化妝水、毛巾、吹風機</p>
								</div>
							</div>
							<div class="details">
								<img src="<%=request.getContextPath()%>/img/icon/shower.png"
									alt="" />
								<div>
									<p>
										<b>衛浴設備</b>
									</p>
									<p>半露天浴池、雙浴室水槽、浴缸、淋浴房、浴室/廁所分設、免治馬桶</p>
								</div>
							</div>
							<div class="details">
								<img src="<%=request.getContextPath()%>/img/icon/more.png"
									alt="" />
								<div>
									<p>
										<b>其他</b>
									</p>
									<p>本飯店全區域禁止吸煙。 圖像・平面圖僅供參考。 我們無法接受特定房間的預約,請您見諒。 訂房後不可更改人數。</p>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>

			<div class="variables">
				<div class="selects">
					<div class="guest-select">
						<img src="<%=request.getContextPath()%>/img/icon/user.png" /> <select
							name="guest" id="guest" class="wide">
							<option value="2" <c:if test="${guest == 2}"> selected</c:if>>2位成人</option>
							<option value="3" <c:if test="${guest == 3}"> selected</c:if>>3位成人</option>
							<option value="4" <c:if test="${guest == 4}"> selected</c:if>>4位成人</option>
							<option value="5" <c:if test="${guest == 5}"> selected</c:if>>5位成人</option>
							<option value="6" <c:if test="${guest == 6}"> selected</c:if>>6位成人</option>
						</select>
					</div>
					<div class="stay-select">
						<img src="<%=request.getContextPath()%>/img/icon/moon.png" /> <select
							name="stay" id="stay" class="wide">
							<option value="1" <c:if test="${stay == 1}"> selected</c:if>>1晚</option>
							<option value="2" <c:if test="${stay == 2}"> selected</c:if>>2晚</option>
							<option value="3" <c:if test="${stay == 3}"> selected</c:if>>3晚</option>
							<option value="4" <c:if test="${stay == 4}"> selected</c:if>>4晚</option>
							<option value="5" <c:if test="${stay == 5}"> selected</c:if>>5晚</option>
							<option value="6" <c:if test="${stay == 6}"> selected</c:if>>6晚</option>
							<option value="7" <c:if test="${stay == 7}"> selected</c:if>>7晚</option>
						</select>
					</div>
				</div>
				<div class="chosen-date">
					<p>
						<%=infoJson.getString("startDate")%>
						-
						<%=infoJson.getString("leaveDate")%></p>
				</div>
				<hr />
				<div class="side-calendar">
					<p>戴蒙度假村 價格·空房檢索</p>
					<button class="calendar-backward arrow">
						<img src="<%=request.getContextPath()%>/img/icon/up-chevron.png" />
					</button>
					<div class="view">
						<div id="display"></div>
					</div>
					<button class="calendar-forward arrow">
						<img src="<%=request.getContextPath()%>/img/icon/down-chevron.png" />
					</button>
				</div>
			</div>
		</div>
	</div>
	<script src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/js/jquery.nice-select.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
	<script src="<%=request.getContextPath()%>/js/slick.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/front/main.js"></script>
	<script>
        $(document).ready(function () {
        	<%
        		List<JSONObject> bookingCart = (List<JSONObject>) session.getAttribute("bookingCart");
        		if (bookingCart != null && bookingCart.size() != 0){
        			String startDate = infoJson.getString("startDate");
            		String stay = infoJson.getString("stay");
            		for (RoomRsvVO rsvvo: rsvvoList){
            			String id = rsvvo.getRm_type() + "-" + startDate + "-" + stay;
            			Integer rmLeft = rsvvo.getRm_left();
            			for (JSONObject book:bookingCart){
            				if (id.equals(book.getString("roomCardId"))){
            					rmLeft -= 1;
            				}
            			}
            			rsvvo.setRm_left(rmLeft);
            		}
        		}
        	%>
        	var roomLimit = { //建立js物件，得到房型數量，依此限制加入購物車的數量
        			<c:forEach var="rsvvo" items="${rsvvoList}">
            		${rsvvo.rm_type}: ${rsvvo.rm_left},
            		</c:forEach>	
        	}
        	calTotal(); 
        	//加入商品到購物車
        	$(".add-to-cart").click(function(){
        		let button = $(this);
        		let rmtype = $(this).attr("data-rmtype")
        		if (roomLimit[rmtype] == 0){ //判斷是否已達上限
        			Swal.fire({
        				position: "center",
                        icon: "info",
                        title: "已達剩餘間數上限",
                        showConfirmButton: false,
                        timer: 1000,
        			})
        			return;
        		}
        		roomLimit[rmtype] -= 1;
        		let subtotalstr = button.parent().siblings(".room-price").find(".subtotal").text();
        		let rmtypename = button.parent().siblings(".room-infos").find(".room-title").text();
        		$.ajax({
        			url:"<%=request.getContextPath()%>/booking/Available?action=addtocart",
        			data:{
        				roomType: rmtype,
        				startDate: "<%=infoJson.getString("startDate")%>",
        				stay:  $("#stay").val(),
        				guest: $("#guest").val(),
        				subtotal: subtotalstr,
        			},
        			type:"POST",
        			success: function(msg){
        				let id = rmtype + "-" + "<%=infoJson.getString("startDate")%>" + "-" + $("#stay").val()
        				let img = button.parent().siblings(".room-pic").find("div.slick-slide").eq(0).html()
        				console.log(img)
        				let roomCard = document.createElement("div");
        				roomCard.classList.add("room-in-cart");
        				let str =
        				`
	                    <div class="booking-date-div">
	                        <img class="cart-list-icons" src="<%=request.getContextPath()%>/img/icon/calendar.png" />
	                        <p class="booking-date"><%=infoJson.getString("startDate")%> - <%=infoJson.getString("leaveDate")%></p>
	                    </div>
	                    <div class="pic-in-cart">
	                         <div>`  + img + `</div>
	                    </div>
	                    <h2>` + rmtypename + `</h2>
	                    <br />
	                    <img class="cart-list-icons" src="<%=request.getContextPath()%>/img/icon/user.png" />
	                    <p><%=infoJson.getString("guest")%> 成人</p>
	                    <hr />
	                    <img class="cart-list-icons" src="<%=request.getContextPath()%>/img/icon/bed.png" />
	                    <p>1 間套房</p>
	                    <h3 class="single-price"><button class="delete-room-card" data-rmtype="` + rmtype + `" data-id="` + id + `">移除</button><span>價格小計：</span>USD\$` + subtotalstr + `</h3>
        				`
        				roomCard.innerHTML = str;
        				$(".booking-header .cart-list .cart-view-range").append(roomCard);
        				calTotal(); 
        			}
        		})
        	})
        	//從購物車移除
        	$(document).on("click", ".delete-room-card", function(){
        		let id = $(this).attr("data-id");
        		let rmtype = $(this).attr("data-rmtype");
        		let card = $(this);
        		$.ajax({
        			url:"<%=request.getContextPath()%>/booking/Available?action=removefromcart",
        			type:"POST",
        			data:{
        				roomCardId: id,
        			},
        			success: function(){
        				card.parents(".room-in-cart").animate({opacity: 0},
        						500,function(){
        					card.parents(".room-in-cart").remove();
        					roomLimit[rmtype] += 1;
            				calTotal(); 
        				});
        			}
        		})
        	})
        	//變更購物車總額
        	function calTotal(){
        		let subtotals = $(".room-in-cart .single-price");
        		let total = 0;
        		for (let i = 0; i < subtotals.length; i++){
        			total += parseInt(subtotals.eq(i).text().split("$")[1]);
        		}
        		$(".cart-list .total-price span").text("$" + total);
        		
        		let counter = $(".shopping-cart .counter").eq(0);
        		let number = $(".room-in-cart").length;
        		counter.text(number);
        		if (parseInt(number) > 0){
        			$("#room-cart-empty").remove();
        			counter.css("background-color", "crimson");
        			counter.css("color", "white");
        		} else {
        			counter.css("background-color", "white");
        			counter.css("color", "black");
        			let emptyMsg = document.createElement("h4");
        			emptyMsg.setAttribute("id", "room-cart-empty");
        			emptyMsg.innerText = "尚未選取商品";
        			$(".cart-view-range").eq(0).append(emptyMsg);
        			
        		}
        	}
        	
        	
        	//展開詳情效果
            let rotate = 0;
            $(".check-room-detail").click(function () {
                $(this).next().toggle();
                $(this)
                    .children("img")
                    .css("transform", "rotate(" + (rotate += 180) + "deg)");
            });
            //房型照片跑馬燈
            $(".room-pic").slick({
                arrows: false,
                dots: true,
                infinite: true,
                autoplay: true,
                autoplaySpeed: 4000,
                fade: true,
                cssEase: "linear",
            });
            //Niceselect套件
            $("select").niceSelect();
            
            //購物車動畫效果
            $(".shopping-cart").mouseenter(function () {
                $(this).addClass("shopping-cart-hover");
                $(this).next().addClass("cart-list-show");
                $(".curtain").addClass("curtain-show");
            });
            $(".cart-list").mouseleave(function () {
                $(this).removeClass("cart-list-show");
                $(this).prev().removeClass("shopping-cart-hover");
                $(".curtain").removeClass("curtain-show");
            });
            //變更條件後更新頁面
            $("#stay").change(function(){
            	let str = window.location.href.split("?")[0];
            	str = str + "?action=booking&date=${infoJson.getString("startDate")}&guest=${infoJson.getString("guest")}&stay=" + $(this).val()
            	window.location.replace(str);
            })
            $("#guest").change(function(){
            	let str = window.location.href.split("?")[0];
            	str = str + "?action=booking&date=${infoJson.getString("startDate")}&stay=${infoJson.getString("stay")}&guest=" + $(this).val()
            	window.location.replace(str);
            })
            // Calendar
            // Calendar
            // Calendar
            let display = document.getElementById("display");
            let weeks = ["ㄧ", "二", "三", "四", "五", "六", "日"];
            let today = new Date();
            let thisYear = today.getFullYear();
            let thisMonth = today.getMonth();
            let todayDate = today.getDate();
            let todayStr = thisYear + "-" + (thisMonth+1) + "-" + todayDate
            console.log(todayStr);
            let current = 0;
            var loaded = [0, 1]
            getCalendars(12); //拿一年份的月曆！
            fetchAvalibility(current);
            fetchAvalibility(current+1);
            
            function createCalendar(year, month) {
                let feb = leapYear(year);
                let monthOfDay = [31, feb, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
                let wrapper = document.createElement("div"); //包住個別日曆
                wrapper.classList.add("calendar-wrapper");
                let title = document.createElement("div"); //產生日曆標頭
                title.classList.add("title");
                title.innerHTML = "<b>" + (month + 1) + "月</b><p>" + "," + year + "年</p>";
                let table = document.createElement("table"); //產生日曆表格
                table.classList.add("calendar");
                let firstTr = document.createElement("tr"); //產生標頭列
                firstTr.classList.add("week-title");

                table.append(firstTr);
                wrapper.append(title);
                wrapper.append(table);
                //建立抬頭
                for (let i = 0; i < 7; i++) {
                    let th = document.createElement("th");
                    th.innerText = weeks[i];
                    firstTr.append(th);
                }
                //找出該月第一天是禮拜幾
                let firstDayOfWeek = new Date(year, month, 1).getDay();
                if (firstDayOfWeek == 0) firstDayOfWeek = 7;
                //確認月曆行數
                let rows = 6;
                //產生月曆行數
                for (let i = 0; i < rows; i++) {
                    let tr = document.createElement("tr");
                    for (let j = 1; j <= 7; j++) {
                        let td = document.createElement("td");
                        let a = document.createElement("a");
                        let img = document.createElement("img");
                        img.setAttribute("src", "<%=request.getContextPath()%>/css/ajax-loader.gif");
                        img.setAttribute("style", "display:none; width:100%")
                        td.classList.add("calendar-td");
                        a.classList.add("calendar-box");
                        let id =
                            year.toString() + "-"
                            + (month + 1).toString().padStart(2, "0") + "-"
                            + (i * 7 + j - firstDayOfWeek + 1).toString().padStart(2, "0");
                        if (i === 0 && j >= firstDayOfWeek) {
                            a.setAttribute("data-year", year);
                            a.setAttribute("data-month", month + 1);
                            a.setAttribute("data-date", j + i * 7 - firstDayOfWeek + 1);
                            a.setAttribute("id", id);
                        } else if (i * 7 + j - firstDayOfWeek + 1 <= monthOfDay[month]) {
                            a.setAttribute("data-year", year);
                            a.setAttribute("data-month", month + 1);
                            a.setAttribute("data-date", j + i * 7 - firstDayOfWeek + 1);
                            a.setAttribute("id", id);
                        }
                        a.append(img);
                        td.append(a);
                        tr.append(td);
                    }
                    table.append(tr);
                }
                return wrapper;
            }

            function leapYear(year) {
                let feb = (year % 4 == 0 && year % 100 != 0) || year % 400 == 0 ? 29 : 28;
                return feb;
            }
            //填充日期資訊
            function fillUpDates(year, month, thisMonthDate) {
                let feb = leapYear(year);
                let monthOfDay = [31, feb, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
                for (let i = thisMonthDate; i <= monthOfDay[month]; i++) {
                    let celldate = document.createElement("div");
                    let cellprice = document.createElement("div");
                    cellprice.classList.add("calendar-price");
                    celldate.classList.add("calendar-date");
                    let id = year.toString() + "-" + (month + 1).toString().padStart(2, "0") + "-" + i.toString().padStart(2, "0");
                    let hrefstr =
                        "<%=request.getContextPath()%>/booking/Available?action=booking" 
                        + "&date=" + id
                        + "&stay=0";
                    let box = document.getElementById(id);
                    box.setAttribute("href", hrefstr);
                    box.classList.add("calendar-default");
                    celldate.innerText = i;
                    box.append(celldate);
                    box.append(cellprice);
                }
            }
            //導覽
            function getCalendars(number) {
            	for (i = 0; i < number; i++) {
                    let thisMonthDate = 1;
                    let year = thisYear + Math.floor(thisMonth / 12);
                    let month = thisMonth % 12;
                    let calendar = createCalendar(year, month);
                    display.append(calendar);
                    if (i == 0){
                        thisMonthDate = todayDate;
                    } 
                    fillUpDates(year, month, thisMonthDate);
                    thisMonth++;
                }
                thisMonth = today.getMonth();
                let todaybox = document.getElementById(
                    thisYear.toString() +  "-" 
                      +  (thisMonth + 1).toString().padStart(2, "0")  + "-"
                      +  today.getDate().toString().padStart(2, "0")
                );
                todaybox.classList.add("calendar-today");

                let positionY = 0;
                let current = 0;
                let forward = $(".calendar-forward");
                let backward = $(".calendar-backward");
                let calendars = $(".calendar-wrapper");
                var calendarHeight = 700;
                calendars.eq(0).css("opacity", "1");
                calendars.eq(1).css("opacity", "1");
                backward.prop("disabled", true);
                forward.click(function () {
                    current += 2;
                    calendars.css("opacity", "0");
                    if (0 < number - current) {
                        backward.prop("disabled", false);
                        $("#display").css("transform", "translateY(-" + (positionY += calendarHeight) + "px)");
                    }
                    if (3 > number - current) {
                        forward.prop("disabled", true);
                    }
                    calendars.eq(current).css("opacity", "1");
                    calendars.eq(current + 1).css("opacity", "1");
                    if (loaded.indexOf(current) < 0){
                    	fetchAvalibility(current);
                    	fetchAvalibility(current + 1);
                    	loaded.push(current);
                        loaded.push(current+1);
                    }
                });
                backward.click(function () {
                    current -= 2;
                    calendars.css("opacity", "0");
                    if (number - current > 0) {
                        forward.prop("disabled", false);
                        $("#display").css("transform", "translateY(-" + (positionY -= calendarHeight) + "px)");
                    }
                    console.log(number - current);
                    if (number - current == 12) {
                        backward.prop("disabled", true);
                    }
                    calendars.eq(current).css("opacity", "1");
                    calendars.eq(current + 1).css("opacity", "1");
                    if (loaded.indexOf(current) < 0){
                    	fetchAvalibility(current);
                    	fetchAvalibility(current + 1);
                        loaded.push(current);
                        loaded.push(current+1);
                    }
                });
            }
            var rm_price = {
            		<c:forEach var="rmtypevo" items="${rmtypeSvc.getAll()}">
            			${rmtypevo.rm_type}:${rmtypevo.rm_price},
            		</c:forEach>
            }
            function fetchAvalibility(currentCal){
                let allDays = $(".calendar-wrapper").eq(currentCal).find(".calendar-default");
                let stayDays = $("#stay").val();
                let guests = $("#guest").val();
                for (let i = 0 ; i < allDays.length; i++){
                	let currentDate = allDays.eq(i);
                    $.ajax({
                         url: "<%=request.getContextPath()%>/booking/Available",
                         data:{
                             date: currentDate.attr("id"),
                             stay: stayDays,
                             rmtype: "all",
                             guest: guests,
                             action:"roomCheck"
                         },
                         type: 'POST',
                         beforeSend: function() {
                        	 currentDate.children("img").show();
                          },
                         success: function(str){
                            var data = JSON.parse(str)
        					if(data.isFull == "true"){
        						currentDate.addClass("calendar-isFull");
        						/* currentDate.attr("href",""); */
        					} else {
        						for (let i = 1; i < 10; i++){
        							if (data[i] != null){
        								currentDate.children(".calendar-price").text("$" + rm_price[i] + "-")
        								break;
        							}
        						}
        						currentDate.addClass("calendar-isEmpty");
        						let length = currentDate.attr("href").length;
        						let href = currentDate.attr("href").split("stay")[0] 
        									+ "stay=" + $("#stay").val() 
        									+ "&guest=" + $("#guest").val();
        						
        						
        						
        						currentDate.attr("href", href);
        					}
                            currentDate.children("img").hide();
                         }
                    })
                } 
            }
            //套件，按鈕讀取效果
            var loading = function(e) {
            	  e.preventDefault();
            	  e.stopPropagation();
            	  e.target.classList.add('loading');
            	  e.target.setAttribute('disabled','disabled');
            	  setTimeout(function(){
            	    e.target.classList.remove('loading');
            	    e.target.removeAttribute('disabled');
            	  },1500);
            	};

            	var btns = document.querySelectorAll('.room-checkout button');
            	for (var i=btns.length-1;i>=0;i--) {
            	  btns[i].addEventListener('click',loading);
            	}
            //套件，加入購物車效果
             $('.add-to-cart').on('click',function(){
			    var button = $(this);
			    var cart = $('.shopping-cart').eq(0);
			    cart.addClass('shake');
			      setTimeout(function(){
			        cart.removeClass('shake');
			      },500)
			    
			  })
        });
        /* preloader */
        $(window).on("load", function() {
        	$(".loader").delay(400).fadeOut();
        	$("#preloder").delay(600).fadeOut("slow");
        });
        </script>
</body>
</html>