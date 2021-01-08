<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.members.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.stream.Collectors"%>
<%@ page import="com.bookingorder.model.*"%>
<%@ page import="com.bookingdetail.model.*"%>
<%@ page import="java.time.format.TextStyle"%>
<%@ page import="java.time.LocalDate"%>

<%
BookingOrderService bkodSvc = new BookingOrderService();
MembersVO member = (MembersVO) session.getAttribute("member");
List<BookingOrderVO> bkodList = bkodSvc.getAllByMbId(member.getMb_id());
Collections.sort(bkodList, new Comparator<BookingOrderVO>() {
	public int compare(BookingOrderVO o1, BookingOrderVO o2) {
		LocalDate startDate1 = o1.getDateIn();
		LocalDate startDate2 = o2.getDateIn();
		return startDate1.compareTo(startDate2);
	}
});

List<BookingOrderVO> onGoing = new LinkedList<>();
List<BookingOrderVO> history = new LinkedList<>();
onGoing = bkodList.stream().filter(e -> e.getBk_status().equals(BKSTATUS.PAID)).collect(Collectors.toList());
history = bkodList.stream().filter(e -> !e.getBk_status().equals(BKSTATUS.PAID)).collect(Collectors.toList());
pageContext.setAttribute("onGoing", onGoing);
pageContext.setAttribute("history", history);
%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/frontend/files/commonCSS.file"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/slick-theme.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/slick.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/front/memberInfo.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/front/memberBooking.css" />

<title>Diamond Resort 會員個人資料</title>
</head>
<style>
	#pkupbooking {
		z-index:-99;
		opacity:0;
		position: fixed;
		left:50%;
		top:-100%;
		transform:translate(-50%, -50%);
		box-shadow: 0px 0px 3px black;
		transition: 1s ease;
	}
	#pkupbooking iframe {
		width:100vw;
		height:100vh;
		border:none;
	}
	#pkupbooking.show {
		z-index: 999;
		top:50%;
		opacity:1;
	}
</style>
<body>
	<%@ include file="/frontend/files/header.file"%>
	<div class="main-wrapper">
		<div class="info-div">
			<div class="info-content">
				<div class="tabset">
					<!-- Tab 1 -->
					<input type="radio" name="tabset" id="tab1"
						aria-controls="onGoingBooking" checked /> <label for="tab1">我的假期</label>
					<!-- Tab 2 -->
					<input type="radio" name="tabset" id="tab2"
						aria-controls="bookingHistory" /> <label for="tab2">假期紀錄</label>
					<jsp:useBean id="bkdetailSvc" scope="page"
						class="com.bookingdetail.model.BookingDetailService" />
					<jsp:useBean id="rmtypeSvc" scope="page"
						class="com.roomtype.model.RoomTypeService" />
					<jsp:useBean id="rmpicSvc" scope="page"
						class="com.roompic.model.RoomPicService" />
					<jsp:useBean id="pkupSvc" scope="page"
						class="com.pickup.model.PickupService" />
					<div class="tab-panels">
						<section id="onGoingBooking" class="tab-panel">
							<c:if test="${onGoing.size() == 0}">
							<div class="no-booking">
								<h2>目前尚未預約假期</h2>
								<a href="<%=request.getContextPath()%>/frontend/roomrsv/available.jsp">前往預定?</a>
							</div>
							</c:if>
							<c:forEach var="booking" items="${onGoing}">
								<div class="booking-order">
									<h5 class="booking-number">NO:${booking.bk_no}</h5>
									<div class="date-information">
										<div class="booking-date">
											<span>${booking.dateIn.getDayOfMonth()}</span> <span>${booking.dateIn.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH)}</span>
											<span>${booking.dateIn.getYear()}</span>
										</div>
										<img class="booking-date-arrow-down"
											src="<%=request.getContextPath()%>/img/icon/fast-forward.png">
										<div class="booking-date">
											<span>${booking.dateOut.getDayOfMonth()}</span> <span>${booking.dateOut.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH)}</span>
											<span>${booking.dateOut.getYear()}</span>
										</div>
									</div>
									<p class="pkuptime">
										<i class="fas fa-helicopter"></i> 預約接送時間：
										<c:out value="${pkupSvc.getOneByBkNo(booking.bk_no).arrive_datetime.toLocaleString()}" default="尚未預約接送"/>
									</p>
									<div class="rooms">
										<c:forEach var="detail"
											items="${bkdetailSvc.getAllByBkNo(booking.bk_no)}">
											<div class="room">
												<div class="room-pics">
													<img
														src="data:image;base64,${rmpicSvc.getOneRandomPic(detail.rm_type)}">
												</div>
												<div class="booking-details">
													<p>
														<i class="fa fa-calendar"></i>入住日期：${booking.dateIn}
													</p>
													<p>
														<i class="fa fa-calendar"></i>退房日期：${booking.dateOut}
													</p>
													<p>
														<i class="far fa-moon"></i>共入住${booking.dateOut.compareTo(booking.dateIn)}晚
													</p>
													<p>
														<i class="fas fa-home"></i>${rmtypeSvc.getOne(detail.rm_type).type_name}
													</p>
													<p>
														<i class="far fa-user"></i>${detail.rm_guest} Guest
													</p>
												</div>
											</div>
										</c:forEach>
									</div>
									<div class="buttons">
										<button class="pkup">
											<i class="fas fa-helicopter"></i>接送預約
										</button>
										<button class="check-order-detail checkout-invoice" data-bkno="${booking.bk_no}">
											<i class="fas fa-clipboard-list"></i>訂單詳情
										</button>
										<button class="cancel-order" data-bkno="${booking.bk_no}">
											<i class="far fa-bell-slash"></i>取消訂單
										</button>
									</div>
								</div>
							</c:forEach>
						</section>

						<section id="bookingHistory" class="tab-panel">
							<table id="history-table">
								<tr>
									<th>訂單編號</th>
									<th>訂單日期</th>
									<th>入住日期</th>
									<th>訂單總額</th>
									<th>付款卡號</th>
									<th>收據明細</th>
								</tr>
								<c:if test="${history.size() == 0}">
								<tr>
									<td colspan="6" style="text-align:center; color:#e8e8e8;">尚無訂購紀錄</td>
								</tr>
								</c:if>
								<c:forEach var="histo" items="${history}">
								<tr>
									<td><i class="fas fa-receipt"></i>${histo.bk_no} 
									<c:choose>
									<c:when test="${histo.bk_status == 2}"><span class="status-checked-in">CHECKED IN</span></c:when>
									<c:when test="${histo.bk_status == 3}"><span class="status-finished">FINISHED</span></c:when>
									<c:when test="${histo.bk_status == 4}"><span class="status-canceled">CANCELED</span></c:when>
									</c:choose>
									</td>
									<td>${histo.bk_date}</td>
									<td>${histo.dateIn}</td>
									<td>${histo.total_price}</td>
									<td>${histo.card_no}</td>
									<td><a class="checkout-invoice" data-bkno="${histo.bk_no}">INVOICE<i class="fas fa-file-invoice-dollar"></i></a></td>
								</tr>
								</c:forEach>
							</table>
							
						</section>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="pkupbooking">
		<iframe src="<%=request.getContextPath()%>/frontend/roomrsv/pickup.jsp"></iframe>
	</div>
	<%@ include file="/frontend/files/commonJS.file"%>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
	<script src="<%=request.getContextPath()%>/js/slick.min.js"></script>
	<script>
		$(document).ready(function(){
			$(".checkout-invoice").click(function(){
		    	let bkno = $(this).attr("data-bkno");
		    	url = "<%=request.getContextPath()%>/receipt.jsp?bk_no=" + bkno;
		    	window.open(url, '_blank');
		    })
			
			
			$(".pkup").click(function(){
				$("#pkupbooking").addClass("show");
			})
			
			$(".cancel-order").click(function (e) {
		        e.preventDefault();
		        let bkno = $(this).attr("data-bkno");
		        Swal.fire({
		            title: "Are you sure?",
		            text: "訂單取消後無法復原",
		            icon: "warning",
		            showCancelButton: true,
		            confirmButtonText: "確認取消",
		            cancelButtonText: "離開對話",
		            confirmButtonColor: '#d33',
		        }).then((result) => {
		            if (result.isConfirmed) {
		                $.ajax({
		                    url: "<%=request.getContextPath()%>/bookingServlet?action=cancel_booking",
		                    data: {
		                        bk_no: bkno,
		                    },
		                    type: "POST",
		                    success: function (msg) {
		                    	console.log(msg);
		                        if (msg == "success") {
		                            Swal.fire({
		                            	title: '訂單取消成功',
		                            	icon: 'success',
		                            	showConfirmButton: false,
		                            	timer: 1500,
		                            });
		                            setTimeout(function(){
		                            	window.location.reload();
		                            }, 1500);
		                        } else if (msg == "fail"){
		                        	Swal.fire({
		                            	title: '訂單取消失敗',
		                            	icon: 'warning',
		                            	showConfirmButton: false,
		                            	timer: 1500,
		                            });
		                        }
		                    },
		                });
		            }
		        });
		    });
		})
	</script>
</body>
</html>
