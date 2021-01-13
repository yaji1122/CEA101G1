<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.act.model.*"%>
<%@ page import="com.actorder.model.*"%>
<%@ page import="com.bookingorder.model.*"%>

<% 
    ActService actSvc = new ActService();
    List<ActVO> actlist = actSvc.getAll();
    pageContext.setAttribute("actlist", actlist);
%>


<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<head>
<%@ include file="/frontend/files/commonCSS.file"%>
<meta charset="UTF-8" />
<meta name="description" content="Sona Template" />
<meta name="keywords" content="Sona, unica, creative, html" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>Diamond Resort</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/front/land-new.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/slick-theme.css">
	<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/slick.css">
		<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/slicknav.min.css">
</head>



<body>
	<div class="black">
		<%@ include file="/frontend/files/loginCSS.file"%>
		<%@ include file="/frontend/files/login.file"%>
		<%@ include file="/frontend/files/loginbox.file"%>
		<%@ include file="/frontend/files/header.file"%>
	</div>


	<!-- 互動視窗 -->
	<FORM METHOD="post" id="act_order_form" enctype="multipart/form-data">
		<div class="modal fade" id="exampleModalCenter" tabindex="-1"
			role="dialog" aria-labelledby="exampleModalCenterTitle"
			aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content">
					<div class="modal-header" style="margin-bottom: 10px;">
						<h5 class="modal-title" id="exampleModalCenterTitle">請確認以下資訊</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="reserve-info">
						<div>
							<label class="res-act-name">活動名稱: </label> <input type="text"
								name="act_name" id="act_name" readonly>
						</div>
						<div>
							<label class="res-act-date">活動時段: </label> <input type="text"
								name="act_Time" id="act_time" readonly>
						</div>
					</div>
					<div class="modal-body" style="width: 290px;">
						<div class="input-group mb-3">

							<div class="input-group mb-3">
								<div class="input-group-append">
									<label class="input-group-text" for="inputGroupSelect02">參加人數</label>
								</div>
								<select class="custom-select" id="inputGroupSelect02"
									style="text-align: center" name="ppl" value="${actOrderVO.ppl}">
									<option value="" selected>-Choose-</option>
									<option value="1">1人</option>
									<option value="2">2人</option>
									<option value="3">3人</option>
									<option value="4">4人</option>
									<option value="5">5人</option>
								</select>
							</div>
						</div>
						<div class="input-group-prepend" id="date-title"></div>
						<div class="reserve-info" style="width: 190px; margin: auto;">
							<label class="act_reserve"> 活動價格: </label> 
							<input type="text" name="act_price" id="total_price" readonly value="0"> 
							<input type="hidden" name="totalPrice" id="act_price" readonly value="${actOrderVO.totalPrice}">
						</div>
					</div>
					<jsp:useBean id="bookingorderSvc" scope="page" class="com.bookingorder.model.BookingOrderService" />
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">取消</button>
						<button type="submit" class="btn btn-primary">確認</button>
						<input type="hidden" name="action" value="insert">
						
						<input type="hidden" name="actNo" value="${actVO.actNo}" id="act_no">
						
						<c:forEach var="bookingOrderVO" items="${bookingorderSv.all}">
						   <input type="hidden" name="bkNo" value="${bookingOrderVO.bkNo}" id="bk_no">
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</FORM>
	<!-- 上方輪播 -->
	<div id="slick">
		<c:forEach var="actVO" items="${actlist}">
			<div>
				<img
					src="<%=request.getContextPath()%>/ActServlet?actno=${actVO.actNo}&action=get_actpic">
			</div>
		</c:forEach>
	</div>
	<div class="box">
		<div id="card-container">

			<div id="card">
				<c:forEach var="actVO" items="${actlist}">
					<div class="front face">
						<img
							src="<%=request.getContextPath()%>/ActServlet?actno=ACT0000001&action=get_actpic">
					</div>
					<div class="back face">
						<h1>馳騁沙灣</h1>
						<p class="artist">陸上活動</p>
						<p class="date">(沙灘車)</p>
						<span class="icon-share"> <a href="#"
							style="color: rgb(0, 0, 0)"><i class="fas fa-share-square"></i></a>
						</span>
						<div class="back-face-one">
							<p>
							  <c:if test="${actVO.actNo == 'ACT0000001'}">
							    ${actVO.actInfo}
							  </c:if>
							</p>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<div id="card-container">

			<div id="card2">
				<c:forEach var="actPicVO" items="${actlist}">
					<div class="front face">
						<img
							src="<%=request.getContextPath()%>/ActServlet?actno=ACT0000002&action=get_actpic">
					</div>
					<div class="back face">
						<h1>百步穿楊</h1>
						<p class="artist">陸上活動</p>
						<p class="date">(射箭)</p>
						<span class="icon-share"> <a href="#"
							style="color: rgb(0, 0, 0)"><i class="fas fa-share-square"></i></a>
						</span>
						<div class="back-face-one">
							<p>
							  <c:if test="${actVO.actNo == 'ACT0000002'}">
							    ${actVO.actInfo}
							  </c:if>
                            </p>
						</div>
					</div>
				</c:forEach>
			</div>

		</div>
		<div id="card-container">
			<div id="card3">
				<c:forEach var="actPicVO" items="${actlist}">
					<div class="front face">
						<img
							src="<%=request.getContextPath()%>/ActServlet?actno=ACT0000003&action=get_actpic">
					</div>
				<div class="back face">
					<h1>行燈夜宴</h1>
					<p class="artist">陸上活動</p>
					<p class="date">(煙火燈會)</p>
					<span class="icon-share"> <a href="#"
						style="color: rgb(0, 0, 0)"><i class="fas fa-share-square"></i></a>
					</span>
					<div class="back-face-one">
						<p>
						   <c:if test="${actVO.actNo == 'ACT0000003'}">
							    ${actVO.actInfo}
						   </c:if>
						</p>
					</div>
				</div>
				</c:forEach>
			</div>
		</div>
		<!--下方活動list-->
		<p>
			<button class="btn btn-primary" type="button" data-toggle="collapse"
				style="margin-left: 565px; margin-top: 250px;"
				data-target="#collapseExample" aria-expanded="false"
				aria-controls="collapseExample">活動詳情</button>
		</p>
		<div class="collapse" id="collapseExample">
			<c:forEach var="actVO" items="${actlist}">
				<c:if test="${actVO.actEventNo == 10 || actVO.actStatus == 0}">
					<div class="card card-body">

						<div class="list-img">

							<img
								src="<%=request.getContextPath()%>/ActServlet?actno=${actVO.actNo}&action=get_actpic">

							<div class="list-context">

								<div class="label">
									<label class="act_name">活動名稱:<span>${actVO.actName}
									<input type="hidden" class="act_no" value="${actVO.actNo}" id="actno">
									</span></label>
								</div>
								<div class="label">
									<label class="act_time">活動時段:<span>${actVO.actTime}</span>,
									</label> <label class="act_price">活動價格:<span>${actVO.actPrice}</span>$
									</label>
								</div>

								<div class="line"></div>
								<div class="label">
									<label>活動介紹:</label> <label style="width: 300px;">${actVO.actInfo}</label>
								</div>
							</div>
							<button type="button" class="btn btn-outline-dark make-res"
								data-toggle="modal" data-target="#exampleModalCenter">
								我要預約</button>
						</div>
					</div>
				</c:if>
			</c:forEach>

		</div>
	</div>
	<!--container-->




	<!-- Footer Section End -->
	<!-- Js Plugins -->
 	<%@ include file="/frontend/files/commonJS.file"%>
 	<script src="<%=request.getContextPath()%>/js/slick.min.js"></script>
 	<script src="<%=request.getContextPath()%>/js/slicknav.js"></script>
	<script>
		$(document).ready(function(){
				
			let resname = $("#act_name");
			let restime = $("#act_time");
			let resprice = $("#act_price");
			let resactno =$("#act_no");
			 
			$(".make-res").click(function(){
				let father = $(this).parents(".card");
				let actname = father.find(".act_name span").text();
				let acttime = father.find(".act_time span").text();
				let actprice = father.find(".act_price span").text();
				
				resname.val(actname);
				restime.val(acttime);
				resprice.val(actprice);
				
				let actno = $("#actno").val();
				resactno.val(actno);
				
			});
			
			$("#inputGroupSelect02").change(function(){
				let num = $("#inputGroupSelect02").val();
				if(num !== "") {
					let total = parseInt(num) * parseInt($("#act_price").val());
					$("#total_price").val(total);
				}else{
					$("#total_price").val(0);
				}
			});
			
			
			$('#slick').slick({
			    autoplay: true,
			    arrows: false,
			    slidesToShow: 1,
			    infinite: true,
			    autoplaySpeed: 1000,
			  });

			$('.pictrue-wall').slick({
			    slidesToShow: 1,
			    slidesToScroll: 1,
			    arrows: false,
			    fade: true,
			    autoplaySpeed: 3000,
			    autoplay: true
			  });
			$(".input-date").datepicker({
			    showOn : "button",
			    dateFormat:'yy/mm/dd',
			    buttonImage:'https://ps9103.s3.us-east-2.amazonaws.com/public/field_date+(1).png',
			    buttonImageOnly : false,
			    buttonText:'Date',
			 });
			
			
				let order_elem = document.querySelector("#act_order_form");
				order_elem.addEventListener("submit", function(e){	
					
				e.preventDefault();
				
				let data = new FormData(order_elem);
				let xhr = new XMLHttpRequest();
				xhr.open("post","${pageContext.request.contextPath}/ActOrderServlet");
				xhr.onload = function(){
					if(xhr.status === 200 ){
						if (xhr.responseText === "success") {
                            Swal.fire({
                                position: "top-end",
                                icon: "success",
                                title: xhr.responseText,
                                showConfirmButton: false,
                                timer: 1500,
                            });
//                             setTimeout(function () {
//                                 location.reload();
//                             }, 1400);
                        }else {
                            Swal.fire({
                                position: "top-end",
                                icon: "error",
                                title:"發生錯誤",
                                text: xhr.responseText,
                                showConfirmButton: false,
                                timer: 1500,
                            });
					    }
				    } 
			    }
				xhr.send(data);
			});
			
		});
		
		
	</script>
</body>

</html>