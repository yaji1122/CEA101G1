<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.bookingorder.model.*"%>
<%@ page import="com.rooms.model.*"%>
<%@ page import="com.members.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.time.LocalDate"%>
<%
BookingOrderService bkodSvc = new BookingOrderService();
LocalDate today = LocalDate.now();
List<BookingOrderVO> checkIns = bkodSvc.getAllByDateIn(today);
List<BookingOrderVO> checkOuts = bkodSvc.getAllByDateOut(today);
pageContext.setAttribute("checkIns", checkIns);
pageContext.setAttribute("checkOuts", checkOuts);
%>
<!DOCTYPE html>
<html lang="en">
<head>
<link rel="icon" type="image/png"
	href="<%=request.getContextPath()%>/img/loading.png" />
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
	integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/back/backend_sidebar.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/back/counterSystem.css">
<title>戴蒙酒店房務系統</title>
</head>
<body>
	<%@ include file="/backend/files/backend_sidebar.file"%>
	<!-- preloader -->
	<div id="preloder">
		<img id="preloaderpic"
			src="${pageContext.request.contextPath}/img/loading.png" />
		<div class="loader"></div>
	</div>
	<!-- preloader -->
	<div class="wrapper">
		<div class="header">
			<div>
				<span>CALENDAR DATE</span> <span><%=LocalDate.now()%></span>
			</div>
			<div>TODAY CHECK IN</div>
			<div>TODAY CHECK OUT</div>
			<div>CURRENT GUESTS</div>
		</div>
		<jsp:useBean id="mbSvc" scope="page"
			class="com.members.model.MembersService" />
		<jsp:useBean id="bkdetailSvc" scope="page"
			class="com.bookingdetail.model.BookingDetailService" />
		<jsp:useBean id="rmtypeSvc" scope="page"
			class="com.roomtype.model.RoomTypeService" />
		<jsp:useBean id="rmSvc" scope="page"
			class="com.rooms.model.RoomsService" />
		<div class="main">
			<div class="list">
				<table>
					<tr>
						<td colspan="7" class="list-title">今日待入住</td>
					</tr>
					<tr class="table-head">
						<th>訂單編號</th>
						<th>入住會員</th>
						<th>預定入住日期</th>
						<th>預計退房日期</th>
						<th>辦理入住</th>
						<th>辦理退房</th>
						<th>辦理狀態</th>
					</tr>
					<c:if test="${checkIns.size()==0}">
						<tr>
							<td colspan="7" class="td-msg">今日無待入住客戶</td>
						</tr>
					</c:if>
					
					<c:forEach var="checkIn" items="${checkIns}">
						<tr class="list-data">
							<td>${checkIn.bk_no}</td>
							<td><a class="booking-detail member"
								href="<%=request.getContextPath()%>/MembersServlet?mb_id=${checkIn.mb_id}&action=getone_bymbid&location=memberDetail.jsp">${checkIn.mb_id}</a><br>
								${mbSvc.getOneByMbId(checkIn.mb_id).mb_name}</td>
							<td>${checkIn.dateIn}</td>
							<td>${checkIn.dateOut}</td>
							<td><button class="check-in"  <c:if test="${checkIn.bk_status != 1}">disabled</c:if> >CHECK IN</button></td>
							<td><button>CHECK OUT</button></td>
							<td>
							<c:choose>
								<c:when test="${checkIn.bk_status == 1}">
									未入住
								</c:when>
								<c:otherwise>
									已入住
								</c:otherwise>
							</c:choose>
							</td>
						</tr>
						<c:if test="${checkIn.bk_status == 1}">
						<tr>
							<td class="room-check-in" colspan="7">
							<c:forEach var="room" items="${bkdetailSvc.getAllByBkNo(checkIn.bk_no)}">
								<% int i = 1; %>
								<div class="room-info">
									<h5>房間 <%= i++ %><span class="rmtype" data-rmtype="${room.rm_type}">${rmtypeSvc.getOne(room.rm_type).type_name}</span></h5>
									<span>房客數：${room.rm_guest} 人</span>
								</div>
								<div class="checkin-option">
									<c:forEach var="rm" items="${rmSvc.getAllByRmType(room.rm_type)}">
										<span class="all-rooms <c:if test='${rm.rm_status == 0}'>empty</c:if>">${rm.rm_no}</span>
										<%-- <a 
										class="room-for-check <c:if test='${rm.rm_status != 0}'> not-aval  </c:if> "
										<c:if test="${rm.rm_status==0}">href="<%=request.getContextPath()%>/RoomsServlet?action=update_check_in&rm_no=${rm.rm_no}&mb_id=${checkIn.mb_id}&bk_no=${checkIn.bk_no}"</c:if> >${rm.rm_no}</a> --%>
									</c:forEach>
								</div>
							</c:forEach>
							<h2>未結餘款：USD\$${checkIn.total_price * 0.7}</h2>
							<button class="checkin-confirm" data-mbid="${checkIn.mb_id}" data-bkno="${checkIn.bk_no}">CONFIRM</button>
							</td>
						<tr>
						</c:if>
					</c:forEach>
					<tr>
						<td colspan="7"></td>
					</tr>
					<tr>
						<td colspan="7" class="list-title">今日待退房</td>
					</tr>
					<tr class="table-head">
						<th>訂單編號</th>
						<th>會員編號</th>
						<th>預定入住日期</th>
						<th>預計退房日期</th>
						<th>辦理入住</th>
						<th>辦理退房</th>
						<th>辦理狀態</th>
					</tr>
					<c:if test="${checkOuts.size()==0}">
						<tr>
							<td colspan="7" class="td-msg">今日無待退房客戶</td>
						</tr>
					</c:if>
					<c:forEach var="checkOut" items="${checkOuts}">
						<tr class="list-data">
							<td>${checkOut.bk_no}</td>
							<td>${checkOut.mb_id}</td>
							<td>${checkOut.dateIn}</td>
							<td>${checkOut.dateOut}</td>
							<td><button>CHECK IN</button></td>
							<td><button>CHECK OUT</button></td>
							<td>
								<c:choose>
								<c:when test="${checkIn.bk_status == 2}">
									入住中
								</c:when>
								<c:otherwise>
									已退房
								</c:otherwise>
							</c:choose>
							</td>
						</tr>

					</c:forEach>
				</table>
			</div>
		</div>
	</div>
	<div class="info-display" id="booking-detail-info">
		<div class="close-icon">
			<i class="fas fa-times icon"></i>
		</div>
		<iframe src=""></iframe>
	</div>
	<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
	<%@ include file="/backend/files/backend_footer.file"%>
	<!-- 加入常用 js -->
	<script src="${pageContext.request.contextPath}/js/back/backend.js"></script>
	<script>
		$(window).on("load", function() {
			$(".loader").delay(400).fadeOut();
			$("#preloder").delay(600).fadeOut("slow");
		});
		let bookingDetail = $("#booking-detail-info");
		$(".booking-detail").click(function(e) {
			e.preventDefault();
			let src = $(this).attr("href");
			bookingDetail.addClass("display-show");
			bookingDetail.children("iframe").attr("src", src);
		});
		$(".icon").click(function() {
			$(this).parents(".display-show").removeClass("display-show");
		});
		$(".check-in").click(function() {
			let rooms = $(this).closest("tr").next().find(".room-check-in")
			if (!rooms.hasClass("show")) {
				rooms.addClass("show")
			} else {
				rooms.removeClass("show");
			}
		})
		$(".empty").click(function(){
			$(this).siblings(".empty").removeClass("selected");
			$(this).addClass("selected");
		})
		$(".checkin-confirm").click(function(){
			let selects = $(this).siblings(".checkin-option");
			let mbid = $(this).attr("data-mbid");
			let bkno = $(this).attr("data-bkno");
			let roomArr = [];
			for (let i = 0; i < selects.length; i++){
				let rm_no = selects.eq(i).children(".selected").text();
				if (roomArr.indexOf(rm_no) < 0) {
					roomArr.push(rm_no);
				} else {
					Swal.fire({
						position:"center",
						title:"房號重複",
						icon:"error",
						text:"請重新選擇房號",
					})
					return;
				}
			}
			for (i in roomArr){
				$.ajax({
					url:"<%=request.getContextPath()%>/RoomsServlet?action=update_check_in",
					data:{
						rm_no: roomArr[i],
						mb_id: mbid,
						bk_no: bkno,
					},
					type:"POST",
					success: function(msg){
						if (msg == "success"){
							Swal.fire({
								position:"center",
								title:"辦理入住成功",
								icon:"success",
								text:"1秒後畫面將重新整理",
							})
							setTimeout(function(){
								window.location.reload();
							}, 1000)
						} else {
							Swal.fire({
								position:"center",
								title:"系統爆炸了",
								icon:"error",
							})
						}
					}
				})
			}
		})
	</script>
</body>
</html>
