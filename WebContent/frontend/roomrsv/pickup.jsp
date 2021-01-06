<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.members.model.*"%>
<%@ page import="com.bookingorder.model.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.stream.Collectors"%>
<%
	BookingOrderService bkodSvc = new BookingOrderService();
	MembersVO member = (MembersVO) session.getAttribute("member");
	List<BookingOrderVO> uncheckedList = bkodSvc.getAllByMbId(member.getMb_id()).stream()
										.filter(e -> e.getBk_status().equals(BKSTATUS.PAID))
										.collect(Collectors.toList());
	
	pageContext.setAttribute("uncheckedList", uncheckedList);
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link
            rel="stylesheet"
            href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
            integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
            crossorigin="anonymous"
        />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery.datetimepicker.min.css" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/slick-theme.css" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/slick.css" />
        <title>接送預約</title>
    </head>
    <style>
        * {
            box-sizing: border-box;
            font-family: Impact, Haettenschweiler, "Arial Narrow Bold", sans-serif;
            outline:none;
        }
        #pickuptime {
            display: block;
            text-align-last: center;
            cursor: pointer;
            height: 40px;
            width: 380px;
            opacity: 0;
        }
        #chop-items {
        	max-width:300px;
        }
        .main {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            margin: 0 auto;
            width: 400px;
            text-align: center;
            padding: 10px;
            background-color:white;  
        }
        .main h4 {
        	width:100%;
        	background-color: #0f3057;
        	color:white;
        	letter-spacing:1px;
        	padding: 5px 0px;
        }
        .main .title {
            font-size: 30px;
            padding: 10px;
            margin:5px;
        }
         .main .intro p {
         	font-weight: 200;
         	font-size:12px;
         	text-align:center;
         }
        .reservation {
            position: relative;
            background-color: rgb(255, 255, 255);
            border: 2px solid grey;
            border-radius: 10px;
            width: fit-content;
            height: 40px;
            margin: 10px 0px;
        }
        .reservation span {
            position: absolute;
            right: 2.5px;
            top: 2.5px;
        }
        .reservation span i {
            font-size: 30px;
        }
        #time {
            font-size: 18px;
            font-family: Impact, Haettenschweiler, "Arial Narrow Bold", sans-serif;
            letter-spacing: 1px;
            position: absolute;
            left: 50%;
            top: 50%;
            transform: translate(-50%, -50%);
            width: fit-content;
        }
        .main .reservation:hover  {
         	box-shadow: 0px 0px 2px #008891
         }
        .main button.pkup-confirm,
        .main button.cancel {
        	margin-bottom:10px;
        	cursor:pointer;
        	width:100%;
        	height:50px;
        	background-color:#008891;
        	border:1px solid grey;
        	box-shadow: 0px 1px 1px grey;
        	font-size:16px;
        	letter-spacing:3px;
        	color:white;
        }
         .main button.cancel {
         	background-color: #9e9e9e
         }
        .main button.pkup-confirm:hover,
        .main button.cancel:hover {
        	filter:brightness(1.1);
        }
        .main .chop-item {
        	padding:10px;
        	margin-bottom: 20px;
        }
        .main .chop-item .chop-pic {
        	width:100%;
        }
        .main .chop-item .chop-pic img {
        	max-width:100%;
        }
        button.slick-arrow:before {
        	color:black;
        }
    </style>
    <body>
    <jsp:useBean id="chopSvc" scope="page" class="com.choppers.model.ChoppersService"></jsp:useBean>
        <div class="main">
            <h4 class="title"><i class="fas fa-helicopter"></i>機場快綫</h4>
            <div class="intro">
                <p>戴蒙度假村提供訂房旅客免費機場往返服務，您隨時可以取消或更改接送的方式與時間。</p>
                <p>
                    只需提供抵達機場的時間，我們將派遣專業機師與服務人員，親自至卡加布列島國的塞班島機場接送預約訂房的旅客。
                </p>
                <p>註：一筆訂單僅提供一次接送申請</p>
            </div>
            <h4>預約接送的假期</h4>
            <select id="bkno">
            	<option disabled selected>選擇訂單</option>
            	<c:forEach var="bkod" items="${uncheckedList}">
            	<option value="${bkod.bk_no}:${bkod.dateIn}">訂單編號${bkod.bk_no}:入住日期${bkod.dateIn}</option>
            	</c:forEach>
            </select>
            <h4>接送預約時間</h4>
            <div class="reservation">
                <span id="time"></span>
                <span><i class="far fa-clock"></i></span>
                <input id="pickuptime" type="text" />
            </div>
            <h4>選擇接送機型</h4>
            <div id="chop-items">
            	<c:forEach var="chop" items="${chopSvc.getAll()}">
            		<div class="chop-item" data-chopno="${chop.chop_no}">
            			<div class="chop-pic"><img src="data:image;base64,${chopSvc.getChopPic(chop.chop_no)}"></div>
            			<p>${chop.chop_name}</p>
            			<p>${chop.chop_info}</p>
            		</div>
            	</c:forEach>
            </div>
            <button class="pkup-confirm">確認預約</button>
            <button class="cancel">離開預約</button>
        </div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script
            src="https://cdnjs.cloudflare.com/ajax/libs/jquery-nice-select/1.1.0/js/jquery.nice-select.min.js"
            integrity="sha512-NqYds8su6jivy1/WLoW8x1tZMRD7/1ZfhWG/jcRQLOzV1k1rIODCpMgoBnar5QXshKJGV7vi0LXLNXPoFsM5Zg=="
            crossorigin="anonymous"
        ></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
        <script src="<%=request.getContextPath()%>/js/jquery.datetimepicker.full.min.js"></script>
        <script src="<%=request.getContextPath()%>/js/slick.min.js"></script>
        <script>
            (function ($) {
            	$("#chop-items").slick({
            		arrows: true,
            	});
            	$("button.cancel").click(function(){
            		window.parent.document.getElementById('pkupbooking').classList.remove("show");
            	})
            	$("#bkno").change(function(){
            		let dateIn = $("#bkno").val().split(":")[1];
            		$("#pickuptime").datetimepicker({
                        lang: "zh-TW",
                        format: "yyyy-MM-dd H:i",
                        minTime: "07:00",
                        maxTime: "22:00",
                        minDate: dateIn,
                        maxDate: dateIn,
                        step: 30,
                        onSelectTime: function (ct, i) {
                            let t = $("#pickuptime").datetimepicker("getValue");
                            let year = t.getFullYear();
                            let month = (t.getMonth() + 1).toString().padStart(2, "0");
                            let date = t.getDate().toString().padStart(2, "0");
                            let h = t.getHours().toString().padStart(2, "0");
                            let m = t.getMinutes().toString().padStart(2, "0");
                            let str = year + "-" + month + "-" + date + " " + h + ":" + m;
                            $("#time").text(str);
                        },
                    });
            	})
            	$("button.pkup-confirm").click(function(){
            		let time = $("#time").text();
            		let bkno = $("#bkno").val().split(":")[0];
            		let chopno = $(".slick-active").eq(0).attr("data-chopno");
            		$.ajax({
            			url: "<%=request.getContextPath()%>/PickupServlet?action=insert_pkup",
            			data:{
            				arrive_datetime: time,
            				bk_no:bkno,
            				chop_no:chopno,
            			},
            			type:'POST',
            			success: function(msg){
            				if (msg == "success") {
            					Swal.fire({
                					position:"center",
                					icon:"success",
                					showConfirmButton:false,
                					title:"預約成功"
                				})
            				}
            			}
            		})
            	})
            })(jQuery);
        </script>
    </body>
</html>