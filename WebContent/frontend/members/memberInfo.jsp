<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.members.model.*"%>
<%@ page import="com.payment.model.*"%>
<% 
MembersVO member = (MembersVO)session.getAttribute("member");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/frontend/files/commonCSS.file"%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/front/creditCard.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/front/memberInfo.css" />
<title>Diamond Resort 會員個人資料</title>
</head>
<body>
	<%@ include file="/frontend/files/header.file"%>
	<div class="main-wrapper">
		<div class="select-div">
			<div class="side-nav">
				<ul>
					<li><a
						href="<%=request.getContextPath()%>/frontend/members/memberInfo.jsp"><i
							class="far fa-user"></i> 個人資訊</a></li>
					<li><a
						href="<%=request.getContextPath()%>/frontend/members/memberBooking.jsp"><i
							class="far fa-calendar-check"></i> 假期管理</a></li>
					<li><a href="#"><i class="fas fa-clipboard-list"></i> 訂單管理</a>
					</li>
				</ul>
			</div>
		</div>
		<div class="info-div">
			<div class="info-content">
				<div class="tabset">
					<!-- Tab 1 -->
					<input type="radio" name="tabset" id="tab1"
						aria-controls="basic-info" checked /> <label for="tab1">基本資料</label>
					<!-- Tab 2 -->
					<input type="radio" name="tabset" id="tab2"
						aria-controls="account-info" /> <label for="tab2">帳號資訊</label>
					<!-- Tab 3 -->
					<input type="radio" name="tabset" id="tab3"
						aria-controls="payment-info" /> <label for="tab3">付款資訊</label>
					<div class="tab-panels">
						<section id="basic-info" class="tab-panel">
							<form method="post" id="user-info-form">
								<div class="user-pic">
									<img
										src="<%=request.getContextPath()%>/MembersServlet?mb_id=${member.mb_id}&action=getone_mbpic"
										alt="" />
									<div>
										<i class="fas fa-cloud-upload-alt"></i>
										<p>更換個人照片</p>
										<input type="file" name="update-mbpic" id="update-mbpic">
									</div>
								</div>
								<div class="user-infos">
									<label for="mb_name">姓名</label> <input class="input-disabled"
										type="text" name="update-mbname" id="mb_name"
										value="${member.mb_name}" maxlength="20" placeholder="未填寫"
										required disabled />
								</div>
								<div class="user-infos">
									<label for="mb_bd">生日</label> <input class="input-disabled"
										type="text" name="update-mbbd" id="mb_bd" maxlength="10"
										value="${member.mb_bd}" placeholder="未填寫" required disabled />
								</div>
								<div class="user-infos">
									<label for="mb_phone">聯絡電話</label> <input
										class="input-disabled" type="tel" inputmode="numeric"
										name="update-mbphone" id="mb_phone" maxlength="30"
										value="${member.mb_phone}" placeholder="未填寫" required disabled />
								</div>
								<div class="user-infos">
									<label for="mb_city">居住縣市</label> <input class="input-disabled"
										type="text" name="update-mbcity" id="mb_city" maxlength="30"
										value="${member.mb_city}" placeholder="未填寫" required disabled />
								</div>
								<div class="user-infos">
									<label for="mb_town">居住鄉鎮</label> <input class="input-disabled"
										type="text" name="update-mbtown" id="mb_town" maxlength="30"
										value="${member.mb_town}" placeholder="未填寫" required disabled />
								</div>
								<div class="user-infos">
									<label for="mb_address">居住地址</label> <input
										class="input-disabled" type="text" name="update-mbaddress"
										id="mb_address" value="${member.mb_address}" maxlength="50"
										placeholder="未填寫" required disabled />
								</div>
								<input style="display: none;" value="${member.mb_id}"
									name="update-mbid">
								<div class="basic-info-buttons">
									<button type="button" class="btn btn-primary cancel-update">取消修改</button>
									<button type="submit" class="btn btn-warning update-basic-info">確認變更</button>
								</div>
							</form>
							<div class="basic-info-buttons">
								<button class="btn btn-secondary show-basic-update">變更基本資料</button>
							</div>
						</section>
						<section id="account-info" class="tab-panel">
							<form method="post" id="account-info-form">
								<div>
									<label for="mb_id">會員編號</label> <input class="input-disabled"
										type="text" value="${member.mb_id}" readonly />
								</div>
								<div>
									<label for="mb_name">E-MAIL</label> <input
										class="input-disabled" type="text" name="mb_email"
										id="mb_email" value="${member.mb_email}"
										placeholder="guest@gmail.com" readonly />
								</div>
								<div style="display: none" class="password_reset">
									<label for="mb_name">請輸入舊密碼</label> <input
										class="input-disabled" type="password" name="old_mb_pwd"
										id="old_mb_pwd" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
										placeholder="Please enter old password" maxlength="40"
										required disabled />
								</div>
								<div style="display: none" class="password_reset">
									<label for="mb_name">請輸入新密碼</label> <input
										class="input-disabled" type="password" name="new_mb_pwd"
										id="new_mb_pwd" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
										placeholder="Please enter new password" maxlength="40"
										required disabled />
								</div>
								<div style="display: none" class="password_reset">
									<label for="mb_name">再次確認新密碼</label> <input
										class="input-disabled" type="password"
										name="confirm_new_mb_pwd" id="confirm_new_mb_pwd"
										pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
										placeholder="Please confirm new password again" maxlength="40"
										required disabled />
								</div>
								<input style="display: none;" value="${member.mb_id}"
									name="mb_id">
								<div class="basic-info-buttons">
									<button type="button" class="btn btn-primary cancel-update">取消修改</button>
									<button type="submit"
										class="btn btn-warning update-account-info">確認變更</button>
								</div>
							</form>
							<div class="basic-info-buttons">
								<button class="btn btn-secondary show-account-update">變更登入密碼</button>
							</div>
						</section>
						<jsp:useBean id="paymentSvc" scope="page"
							class="com.payment.model.PaymentService" />
						<section id="payment-info" class="tab-panel">
							<div class="creditcards">
								<c:forEach var="paymentVO"
									items="${paymentSvc.getAllByMbId(member.mb_id)}">
									<div class="creditcard">
										<h4 class="cardnumber">${paymentVO.card_no}</h4>
										<h6>CARDHOLDER NAME</h6>
										<p class="cardholder">${paymentVO.card_name}</p>
										<p class="exp">${paymentVO.exp_mon}/${paymentVO.exp_year}</p>
										<i class="fas fa-minus-circle delete-creditcard"></i> <input
											name="pay_no" class="pay_no" style="display: none"
											value="${paymentVO.pay_no}">
										<div class="creditcard-logo">
											<img
												src="<%=request.getContextPath()%>/img/creditcard/master.png" />
										</div>
									</div>
								</c:forEach>
								<div class="addnewcard creditcard">
									<i class="fas fa-plus-circle add-creditcard"
										id="add-creditcard"></i>
								</div>
							</div>
						</section>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="creditCard-input-view">
		<div style="position: relative">
			<div class="container preload">
				<div class="creditcard">
					<div class="front">
						<div id="ccsingle"></div>
						<svg version="1.1" id="cardfront"
							xmlns="http://www.w3.org/2000/svg"
							xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
							viewBox="0 0 750 471" style="enable-background: new 0 0 750 471;"
							xml:space="preserve">
                    <g id="Front">
                        <g id="CardBackground">
                            <g id="Page-1_1_">
                                <g id="amex_1_">
                                    <path id="Rectangle-1_1_"
								class="lightcolor grey"
								d="M40,0h670c22.1,0,40,17.9,40,40v391c0,22.1-17.9,40-40,40H40c-22.1,0-40-17.9-40-40V40
                            C0,17.9,17.9,0,40,0z" />
                                </g>
                            </g>
                            <path class="darkcolor greydark"
								d="M750,431V193.2c-217.6-57.5-556.4-13.5-750,24.9V431c0,22.1,17.9,40,40,40h670C732.1,471,750,453.1,750,431z" />
                        </g>
                        <text
								transform="matrix(1 0 0 1 60.106 295.0121)" id="svgnumber"
								class="st2 st3 st4">0123 4567 8910 1112</text>
                        <text
								transform="matrix(1 0 0 1 54.1064 428.1723)" id="svgname"
								class="st2 st5 st6">JOHN DOE</text>
                        <text
								transform="matrix(1 0 0 1 54.1074 389.8793)" class="st7 st5 st8">cardholder name</text>
                        <text
								transform="matrix(1 0 0 1 479.7754 388.8793)"
								class="st7 st5 st8">expiration</text>
                        <text transform="matrix(1 0 0 1 65.1054 241.5)"
								class="st7 st5 st8">card number</text>
                        <g>
                            <text
								transform="matrix(1 0 0 1 574.4219 433.8095)" id="svgexpire"
								class="st2 st5 st9">01/23</text>
                            <text
								transform="matrix(1 0 0 1 479.3848 417.0097)"
								class="st2 st10 st11">VALID</text>
                            <text
								transform="matrix(1 0 0 1 479.3848 435.6762)"
								class="st2 st10 st11">THRU</text>
                            <polygon class="st2"
								points="554.5,421 540.4,414.2 540.4,427.9 		" />
                        </g>
                        <g id="cchip">
                            <g>
                                <path class="st2"
								d="M168.1,143.6H82.9c-10.2,0-18.5-8.3-18.5-18.5V74.9c0-10.2,8.3-18.5,18.5-18.5h85.3
                        c10.2,0,18.5,8.3,18.5,18.5v50.2C186.6,135.3,178.3,143.6,168.1,143.6z" />
                            </g>
                            <g>
                                <g>
                                    <rect x="82" y="70" class="st12"
								width="1.5" height="60" />
                                </g>
                                <g>
                                    <rect x="167.4" y="70" class="st12"
								width="1.5" height="60" />
                                </g>
                                <g>
                                    <path class="st12"
								d="M125.5,130.8c-10.2,0-18.5-8.3-18.5-18.5c0-4.6,1.7-8.9,4.7-12.3c-3-3.4-4.7-7.7-4.7-12.3
                            c0-10.2,8.3-18.5,18.5-18.5s18.5,8.3,18.5,18.5c0,4.6-1.7,8.9-4.7,12.3c3,3.4,4.7,7.7,4.7,12.3
                            C143.9,122.5,135.7,130.8,125.5,130.8z M125.5,70.8c-9.3,0-16.9,7.6-16.9,16.9c0,4.4,1.7,8.6,4.8,11.8l0.5,0.5l-0.5,0.5
                            c-3.1,3.2-4.8,7.4-4.8,11.8c0,9.3,7.6,16.9,16.9,16.9s16.9-7.6,16.9-16.9c0-4.4-1.7-8.6-4.8-11.8l-0.5-0.5l0.5-0.5
                            c3.1-3.2,4.8-7.4,4.8-11.8C142.4,78.4,134.8,70.8,125.5,70.8z" />
                                </g>
                                <g>
                                    <rect x="82.8" y="82.1" class="st12"
								width="25.8" height="1.5" />
                                </g>
                                <g>
                                    <rect x="82.8" y="117.9"
								class="st12" width="26.1" height="1.5" />
                                </g>
                                <g>
                                    <rect x="142.4" y="82.1"
								class="st12" width="25.8" height="1.5" />
                                </g>
                                <g>
                                    <rect x="142" y="117.9" class="st12"
								width="26.2" height="1.5" />
                                </g>
                            </g>
                        </g>
                    </g>
                    <g id="Back">
                    </g>
                </svg>
					</div>
					<div class="back">
						<svg version="1.1" id="cardback"
							xmlns="http://www.w3.org/2000/svg"
							xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
							viewBox="0 0 750 471" style="enable-background: new 0 0 750 471;"
							xml:space="preserve">
                    <g id="Front">
                        <line class="st0" x1="35.3" y1="10.4" x2="36.7"
								y2="11" />
                    </g>
                    <g id="Back">
                        <g id="Page-1_2_">
                            <g id="amex_2_">
                                <path id="Rectangle-1_2_"
								class="darkcolor greydark"
								d="M40,0h670c22.1,0,40,17.9,40,40v391c0,22.1-17.9,40-40,40H40c-22.1,0-40-17.9-40-40V40
                        C0,17.9,17.9,0,40,0z" />
                            </g>
                        </g>
                        <rect y="61.6" class="st2" width="750"
								height="78" />
                        <g>
                            <path class="st3"
								d="M701.1,249.1H48.9c-3.3,0-6-2.7-6-6v-52.5c0-3.3,2.7-6,6-6h652.1c3.3,0,6,2.7,6,6v52.5
                    C707.1,246.4,704.4,249.1,701.1,249.1z" />
                            <rect x="42.9" y="198.6" class="st4"
								width="664.1" height="10.5" />
                            <rect x="42.9" y="224.5" class="st4"
								width="664.1" height="10.5" />
                            <path class="st5"
								d="M701.1,184.6H618h-8h-10v64.5h10h8h83.1c3.3,0,6-2.7,6-6v-52.5C707.1,187.3,704.4,184.6,701.1,184.6z" />
                        </g>
                        <text
								transform="matrix(1 0 0 1 621.999 227.2734)" id="svgsecurity"
								class="st6 st7">985</text>
                        <g class="st8">
                            <text
								transform="matrix(1 0 0 1 518.083 280.0879)"
								class="st9 st6 st10">security code</text>
                        </g>
                        <rect x="58.1" y="378.6" class="st11"
								width="375.5" height="13.5" />
                        <rect x="58.1" y="405.6" class="st11"
								width="421.7" height="13.5" />
                        <text
								transform="matrix(1 0 0 1 59.5073 228.6099)" id="svgnameback"
								class="st12 st13">John Doe</text>
                    </g>
                </svg>
					</div>
				</div>
			</div>
			<div class="form-container">
				<div class="field-container">
					<label for="name">Name</label> <input id="name" maxlength="20"
						type="text">
				</div>
				<div class="field-container">
					<label for="cardnumber">Card Number</label><span id="generatecard">generate
						random</span> <input id="cardnumber" type="text" pattern="[0-9]*"
						inputmode="numeric">
					<svg id="ccicon" class="ccicon" width="750" height="471"
						viewBox="0 0 750 471" version="1.1"
						xmlns="http://www.w3.org/2000/svg"
						xmlns:xlink="http://www.w3.org/1999/xlink">

            </svg>
				</div>
				<div class="field-container">
					<label for="expirationdate">Expiration (mm/yy)</label> <input
						id="expirationdate" type="text" pattern="[0-9]*"
						inputmode="numeric">
				</div>
				<div class="field-container">
					<label for="securitycode">Security Code</label> <input
						id="securitycode" type="text" pattern="[0-9]*" inputmode="numeric">
				</div>
				<div class="field-container">
					<label for="insertnewcard"></label> <input id="insertnewcard"
						type="button" value="SUBMIT">
				</div>
				<div class="field-container">
					<input id="cancelinsert" type="button" value="CANCEL">
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/frontend/files/commonJS.file"%>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/imask/3.4.0/imask.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/front/creditCard.js"></script>
	<script>
		$(document).ready(function () {
		    $(".show-basic-update").click(function () {
		        buttonShow($(this));
		    });
		    $(".show-account-update").click(function () {
		        buttonShow($(this));
		        $(".password_reset").css("display", "flex");
		    });
		    //按鈕出現與消失
		    function buttonShow(item) {
				item.css("display", "none")
		        let form = item.parent().siblings("form");
		        let divs = form.children("div");
		        let inputs = divs.children("input");
		        inputs.prop("disabled", false);
		        form.find(".cancel-update").css("display", "block");
				form.find(".cancel-update").next().css("display", "block");
		        form.find(".cancel-update").click(function () {
		            $(this).css("display", "none");
		 			$(this).next().css("display", "none");
				    item.css("display", "block")
		            inputs.prop("disabled", true);
		            $(".password_reset").css("display", "none");
		        });
		    }
		    
		    $(".addnewcard").click(function(){ //新增信用卡畫面
	        	$("#creditCard-input-view").css("opacity", "1");
	        	$("#creditCard-input-view").css("z-index", "99");
	        	$("#cancelinsert").click(()=>{
	        		$("#creditCard-input-view").css("opacity", "0");
	        		$("#creditCard-input-view").css("z-index", "-1");
	        	})
	        })
	        //新增信用卡
	        $("#insertnewcard").click(function(){
	        	let cardname = $("#name").val();
	        	let cardno = $("#cardnumber").val();
	        	let expmon = $("#expirationdate").val().split("/")[0];
	        	let expyear = $("#expirationdate").val().split("/")[1];
	        	let csc = $("#securitycode").val();
	        	let mbid = "${member.mb_id}"
	        	$.ajax({
	        		url:"<%=request.getContextPath()%>/PaymentServlet?action=insert_credit_card",
	        		data:{
	        			"card_name":cardname,
	        			"card_no":cardno,
	        			"exp_mon":expmon,
	        			"exp_year":expyear,
	        			"csc": csc,
	        			"mb_id": mbid
	        		},
	        		type:"POST",
	        		success: function(msg){
	        			let obj = JSON.parse(msg)
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
	        				fragment.innerHTML = `
	                                <h4 class="cardnumber">` + cardno + `</h4>
	                                <h6>CARDHOLDER NAME</h6>
	                                <p class="cardholder">` + cardname + `</p>
	                                <p class="exp">` + expmon + `/` + expyear + `</p>
	                                <i class="fas fa-minus-circle delete-creditcard"></i>
	                                <input name="pay_no" class="pay_no" style="display:none" value=`+ obj.payno +`>
	                                <div class="creditcard-logo">
	                                    <img src="<%=request.getContextPath()%>/img/creditcard/master.png">
	                                </div>
	        						`
	        				$(".creditcards").eq(0).prepend(fragment);
	        				setTimeout(function(){
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
	        		}
	        	})
	        })
	        //刪除信用卡
	        $(document.body).on("click", ".delete-creditcard",function () { 
	        	let thisCard = $(this).parent();
	            Swal.fire({
	                title: "確認刪除嗎?",
	                text: "刪除後將無法返回資料",
	                icon: "warning",
	                showCancelButton: true,
	                confirmButtonColor: "#3085d6",
	                cancelButtonColor: "#d33",
	                confirmButtonText: "確認",
	                cancelButtonText: "取消",
	            }).then((result) => {
	                if (result.isConfirmed) {
	                	let pay_no = thisCard.find("input").val();
	                	console.log(pay_no);
	                    $.ajax({
	                        url: "<%=request.getContextPath()%>/PaymentServlet?action=delete_credit_card",
	                        data: { "pay_no": pay_no },
	                        type: "POST",
	                        success: function (msg) {
	                        	if(msg == "success") {
	                        		Swal.fire({
	                                    position: "center",
	                                    icon: "success",
	                                    title: "已移除付款訊息",
	                                    showConfirmButton: false,
	                                    timer: 1000,
	                                });
	                        		thisCard.remove();
	                        	}
	                        },
	                    });
	                }
	            });
	        });
	      //變更基本資料
	        $("#user-info-form").submit(function (e) {
	            e.preventDefault();
	            let form = document.getElementById("user-info-form");
	            let divs = $("#user-info-form").children("div");
	            let inputs = divs.children("input");
	            let formData = new FormData(form);
	            $.ajax({
	                url: "<%=request.getContextPath()%>/MembersServlet?action=update_member",
	                data: formData,
	                type: "POST",
	                contentType: false,
	                processData: false,
	                success: function (msg) {
	                    if (msg == "success") {
	                        Swal.fire({
	                            position: "center",
	                            icon: "success",
	                            title: "已成功變更您的訊息",
	                            showConfirmButton: false,
	                            timer: 1500,
	                        });
	                        $("#user-info-form").find(".cancel-update").css("display", "none");
	                        $("#user-info-form").find(".update-basic-info").css("display", "none");
	                        $(".show-basic-update").css("display", "block");
	                        inputs.prop("disabled", true);
	                        $(".password_reset").css("display", "none");
	                    }
	                },
	            });
	        });
	      //變更個人照片
	      $("#update-mbpic").change(function(){
	    	  let pic = $(this).parent().siblings("img");
	    	  let file = this.files[0];
	    	  let formData = new FormData();
	    	  formData.append("mb_pic", file);
	    	  formData.append("mb_id", "${member.mb_id}");
	    	  $.ajax({
	    		  url: "<%=request.getContextPath()%>/MembersServlet?action=update_picture",
	    	  	  data: formData,
	    	  	  type:"POST",
	    		  contentType: false,
	              processData: false,
	   			  success: function(msg){
	   				  let reader = new FileReader();
	   				  reader.addEventListener("load", (ex)=>{
	   					  pic.attr("src", ex.target.result);
	   				  });
	   				  reader.readAsDataURL(file);
	   			  } 
	    	  })
	    	  
	      })
	       //變更密碼
	        $("#account-info-form").submit(function (e) {
	            
	            e.preventDefault();
	            let form = document.getElementById("account-info-form");
	            let divs = $("#account-info-form").children("div");
	            let inputs = divs.children("input");
	            let formData = new FormData(form);
	            let oldpwd = $("#old_mb_pwd").val();
	            let newpwd = $("#new_mb_pwd").val();
	            let confirmpwd = $("#confirm_new_mb_pwd").val();
	            if (newpwd != confirmpwd) {
	                Swal.fire({
	                    position: "center",
	                    icon: "error",
	                    title: "密碼確認錯誤",
	                    showConfirmButton: false,
	                    timer: 1500,
	                });
	                return;
	            }
	            $.ajax({
	                url: "<%=request.getContextPath()%>/MembersServlet?action=update_password",
	                data: formData,
	                type: "POST",
	                contentType: false,
	                processData: false,
	                success: function (msg) {
	                    if (msg == "success") {
	                        Swal.fire({
	                            position: "center",
	                            icon: "success",
	                            title: "已成功變更密碼",
	                            showConfirmButton: false,
	                            timer: 1500,
	                        });
	                        $("#old_mb_pwd").val("");
	                        $("#new_mb_pwd").val("");
	                        $("#confirm_new_mb_pwd").val("");
	                    } else if (msg == "pwd_incorrect") {
	                        Swal.fire({
	                            position: "center",
	                            icon: "error",
	                            title: "舊密碼輸入錯誤",
	                            showConfirmButton: false,
	                            timer: 1500,
	                        });
	                    } else {
	                        Swal.fire({
	                            position: "center",
	                            icon: "error",
	                            title: "系統忙碌中，請洽客服人員",
	                            showConfirmButton: false,
	                            timer: 1500,
	                        });
	                    }
	                    $("#account-info-form").find(".cancel-update").css("display", "none");
	                    $("#account-info-form").find(".update-account-info").css("display", "none");
	                    $(".show-account-update").css("display", "block");
	                    inputs.prop("disabled", true);
	                    $(".password_reset").css("display", "none");
	                },
	            });
	        });
		});

		
      

        </script>
</body>
</html>
