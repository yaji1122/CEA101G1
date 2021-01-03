<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.bookingorder.model.*"%>
<%@ page import="com.bookingdetail.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.time.LocalDate"%>
<%@ page import="com.members.model.*"%>
<%
	List<BookingOrderVO> bkodList = (List<BookingOrderVO>) request.getAttribute("bkodList");
if (bkodList == null) {
	BookingOrderService bkodSvc = new BookingOrderService();
	bkodList = bkodSvc.getAllByDateIn(LocalDate.now());
}
pageContext.setAttribute("bkodList", bkodList);
%>
<!DOCTYPE html>
<html>
<head>
<title>戴蒙度假村房務管理</title>
</head>
<style>
.conditions {
	padding: 10px 30px;
	width: 100%;
}

.conditions input {
	height: 25px;
	display: inline-block;
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
</style>
<body>
	<div class="conditions">
		<form method="post" class="bkod_mbid_query_form"
			action="<%=request.getContextPath()%>/bookingServlet?action=getone_bymbid">
			<div class="form-parts">
				<label for="bkod_mbid_query">會員編號：</label> <input type="text"
					placeholder="輸入會員編號" name="bkod_mbid_query" id="bkod_mbid_query">
				<button type="submit" class="btn btn-light">查詢</button>
			</div>
		</form>
		<form method="post" class="bkod_bkno_query_form"
			action="<%=request.getContextPath()%>/bookingServlet?action=getone_bybkno">
			<div class="form-parts">
				<label for="bkod_bkno_query">訂單編號：</label> <input type="text"
					placeholder="輸入訂單編號" name="bkod_bkno_query" id="bkod_bkno_query">
				<button type="submit" class="btn btn-light">查詢</button>
			</div>
		</form>
		<div class="form-parts">
			<label for="pkup_status_query">訂單狀態：</label> <select
				class="form-select" id="pkup_status_query" name="pkup_status_query">
				<option value="all"
					<c:if test="${current_query_status == 'all'}"> selected</c:if>>全部訂單</option>
				<option value="0"
					<c:if test="${current_query_status == '0'}"> selected</c:if>>未付款</option>
				<option value="1"
					<c:if test="${current_query_status == '1'}"> selected</c:if>>待入住</option>
				<option value="2"
					<c:if test="${current_query_status == '2'}"> selected</c:if>>入住中</option>
				<option value="3"
					<c:if test="${current_query_status == '3'}"> selected</c:if>>已完成</option>
				<option value="4"
					<c:if test="${current_query_status == '4'}"> selected</c:if>>已取消</option>
			</select>
		</div>

		<div>
			<a class="btn btn-light"
				href="<%=request.getContextPath()%>/bookingServlet?action=getall_bydatein&date_in=<%=LocalDate.now().toString()%>">查看今日入住訂單</a>
			<a class="btn btn-light"
				href="<%=request.getContextPath()%>/bookingServlet?action=getall_bkod">查看所有訂單</a>
		</div>
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
			<th>訂單狀態</th>
			<th>取消訂單</th>
		</tr>
		<c:choose>
			<c:when test="${bkodList.size() > 0}">
				<%
					String[] layer = {"odd", "even"}; //表格上色
				int number = 2;
				%>
				<c:forEach var="bkodvo" items="${bkodList}">
					<tr
						class="<%=layer[number++ % 2]%> <c:if test="${bkodvo.bk_status == '4' }">bk_cancel</c:if> ">
						<td><a class="booking-detail bkdetail"
							href="<%=request.getContextPath()%>/BookingDetailServlet?bk_no=${bkodvo.bk_no}&action=getall_bybkno">${bkodvo.bk_no}</a></td>


						<td><a class="booking-detail member"
							href="<%=request.getContextPath()%>/MembersServlet?mb_id=${bkodvo.mb_id}&action=getone_bymbid&location=memberDetail.jsp">${bkodvo.mb_id}</a><br>
							${mbSvc.getOneByMbId(bkodvo.mb_id).mb_name}</td>
						<td><c:choose>
								<c:when test="${pkupSvc.getAllByBkNo(bkodvo.bk_no).size() == 0}">
							無預約
							</c:when>
								<c:otherwise>
									<a class="booking-detail pkup"
										href="<%=request.getContextPath()%>/PickupServlet?bk_no=${bkodvo.bk_no}&action=getAllByBkNo">
										<i class="fas fa-search-plus"></i>
									</a>
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
								<h3 style="color: white;">${message}</h3>
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
		<iframe src=""></iframe>
	</div>
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

	    $("#pkup_status_query").change(function () {
	        let form = $("#pkupstatusquery_form");
	        form.submit();
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
	});
	</script>
</body>
</html>