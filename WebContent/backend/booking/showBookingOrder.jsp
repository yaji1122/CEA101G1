<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.bookingorder.model.*"%>
<%@ page import="com.bookingdetail.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.stream.Collectors"%>
<%@ page import="java.time.LocalDate"%>
<%@ page import="com.members.model.*"%>
<%
	List<BookingOrderVO> bkodList = (List<BookingOrderVO>) request.getAttribute("bkodList");
	if (bkodList == null) {
		BookingOrderService bkodSvc = new BookingOrderService();
		bkodList = bkodSvc.getAllBooking().stream().filter(e -> e.getBk_status()
				   .equals(BKSTATUS.CHECKED)) 
				   .collect(Collectors.toList());
		pageContext.setAttribute("bkstatus", "2");
		
	}
	pageContext.setAttribute("bkodList", bkodList);
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/nice-select.css" type="text/css" />
<title>戴蒙度假村房務管理</title>
</head>
<style>
.conditions {
	padding: 10px 30px;
	width: 100%;
	display: flex;
	flex-direction: row;
	justify-content: space-evenly;	
}
.conditions form {
height: fit-content;
}
.conditions input {
	height: 25px;
	display: inline-block;
}
.conditions form label,
.conditions form button.btn {
	margin:0px;
}
.form-parts {
	display: inline-block;
	width: fit-content;
}

.form-parts:nth-child(2) label {
	float: left;
}

#booking-detail-info iframe {
	width: 100%;
	height: 100%;
	border: none;
}

table.bookingOrderTable a {
	background-color: transparent;
	border: none;
	padding: 10px;
}

table.bookingOrderTable a.update i {
	color: #0278ae;
	font-size: 16px;
}

table.bookingOrderTable a.cancel i {
	color: #ff414d;
	font-size: 16px;
}
.nice-select {
	height:20px;
	line-height:20px;
	float: unset;
    width: fit-content;
    margin: 0 auto
}
th {
	position:sticky;
	top:45px;
}
table.bookingOrderTable {
	overflow:scroll;
	max-height:75vh;
}
</style>
<body>
	<div class="conditions">
		<form method="post" class="bkod_mbid_query_form"
			action="<%=request.getContextPath()%>/bookingServlet?action=getall_bymbid">
			<div class="form-parts">
				<label for="bkod_mbid_query">會員編號：</label> <input type="text"
					placeholder="輸入會員編號" name="bkod_mbid_query" id="bkod_mbid_query" autocomplete="off" required>
				<button type="submit" class="btn btn-outline-dark btn-sm">查詢</button>
			</div>
		</form>
		<form method="post" class="bkod_bkno_query_form"
			action="<%=request.getContextPath()%>/bookingServlet?action=getone_bybkno">
			<div class="form-parts">
				<label for="bkod_bkno_query">訂單編號：</label> <input type="text"
					placeholder="輸入訂單編號" name="bkod_bkno_query" id="bkod_bkno_query" autocomplete="off" required>
				<button type="submit" class="btn btn-outline-dark btn-sm">查詢</button>
			</div>
		</form>
	</div>
	<jsp:useBean id="pkupSvc" scope="page"
		class="com.pickup.model.PickupService" />
	<jsp:useBean id="mbSvc" scope="page"
		class="com.members.model.MembersService" />
	<table class="bookingOrderTable">
		<tr>
			<th>訂房單號</th>
			<th>會員</th>
			<th>接送預約</th>
			<th>預定日期</th>
			<th style="color:black">
				<select
				class="form-select" id="order_status">
				<option value="all"
					<c:if test="${bkstatus == 'all'}"> selected</c:if>>全部訂單</option>
				<option value="1"
					<c:if test="${bkstatus == '1'}"> selected</c:if>>待入住</option>
				<option value="2"
					<c:if test="${bkstatus == '2'}"> selected</c:if>>入住中</option>
				<option value="3"
					<c:if test="${bkstatus == '3'}"> selected</c:if>>已完成</option>
				<option value="4"
					<c:if test="${bkstatus == '4'}"> selected</c:if>>已取消</option>
				</select>
			</th>
			<th>取消訂單</th>
		</tr>
		<c:choose>
			<c:when test="${bkodList.size() > 0}">
				<c:forEach var="bkodvo" items="${bkodList}">
					<tr <c:if test="${bkodvo.bk_status == '4' }">bk_cancel</c:if> ">
						<td><i class="fas fa-caret-right"></i><a class="booking-detail bkdetail"
							href="<%=request.getContextPath()%>/BookingDetailServlet?bk_no=${bkodvo.bk_no}&action=getall_bybkno">${bkodvo.bk_no}</a></td>
						<td><a class="booking-detail member"
							href="<%=request.getContextPath()%>/MembersServlet?mb_id=${bkodvo.mb_id}&action=getone_bymbid&location=memberDetail.jsp">${bkodvo.mb_id}</a><br>
							<i class="far fa-user member-icon"></i>${mbSvc.getOneByMbId(bkodvo.mb_id).mb_name}</td>
						<td><c:choose>
							<c:when test="${pkupSvc.getOneByBkNo(bkodvo.bk_no) == null}">
							無預約
							</c:when>
								<c:otherwise>
								<fmt:formatDate value="${pkupSvc.getOneByBkNo(bkodvo.bk_no).arrive_datetime}" type = "both" dateStyle = "medium" timeStyle = "short"/>
								</c:otherwise>
							</c:choose></td>
						<td>入住：${bkodvo.dateIn}<br>退房：${bkodvo.dateOut}
						</td>

						<td class="stauts_${bkodvo.bk_status}"><c:choose>
								<c:when test="${bkodvo.bk_status == '0'}">
										未付款
									</c:when>
								<c:when test="${bkodvo.bk_status == '1'}">
										待入住
									</c:when>
								<c:when test="${bkodvo.bk_status == '2'}">
										入住中
									</c:when>
								<c:when test="${bkodvo.bk_status == '3'}">
										已完成
									</c:when>
								<c:otherwise>
										已取消
									</c:otherwise>
							</c:choose></td>
						<td><a class="cancel" type="button" data-bkno="${bkodvo.bk_no}"
							<c:if test="${bkodvo.bk_status == '4'}">disabled</c:if>><i
								class="fas fa-trash-alt"></i></a></td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="6"><c:if test="${not empty msgs}">
							<c:forEach var="message" items="${msgs}">
								<h5 style="color: lightgrey;padding: 5px 0px;margin: 0;">${message}</h5>
							</c:forEach>
						</c:if></td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	<div class="info-display" id="booking-detail-info">
		<div class="close-icon">
			<i class="fas fa-times icon"></i>
		</div>
		<iframe id="myIframe" src=""></iframe>
	</div>
	<script src="${pageContext.request.contextPath}/js/jquery.nice-select.min.js"></script>
	<script>
	$(document).ready(function () {
	    $(".cancel").click(function (e) {
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

	    let bookingDetail = $("#booking-detail-info");
	    $(".booking-detail").click(function (e) {
	        e.preventDefault();
	        let src = $(this).attr("href");
	        bookingDetail.addClass("display-show");
	        bookingDetail.children("iframe").attr("src", src);
	    });

	    $(".icon").click(function () {
	        $(this).parents(".display-show").removeClass("display-show");
	    });
	    let iframe = document.getElementById("myIframe");
	    iframe.onload = function(){
	        iframe.style.height = iframe.contentWindow.document.body.scrollHeight + 'px';
	        iframe.style.width = iframe.contentWindow.document.body.scrollWidth + 'px';
	    }
	    $("#order_status").niceSelect();
	    $("#order_status").change(function(){
			let status = $(this).val();
			window.location.href = "<%=request.getContextPath()%>/bookingServlet?action=getallBybkStatus&bkstatus=" + status;
		})
	});
	</script>
</body>
</html>