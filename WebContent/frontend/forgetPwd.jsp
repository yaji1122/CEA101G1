<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.members.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/frontend/files/commonCSS.file"%>
<title>重設密碼</title>
</head>
<style>
.menu-item {
	background-color: #222736;
}
.banner {
	height: 100px;
	width: 100%;
	display: flex;
	flex-direction: row;
	justify-content: center;
}

.banner img {
	max-height: 100%;
}
#email {
	border:none;
	border-bottom: 1px solid grey;
	margin-bottom: 10px;
	text-align-last: center;
}
#resetpwd {
	display: flex;
	flex-direction: column;
	justify-content: center;
	width: 50%;
	margin: 0 auto;
	text-align: center;
	height: 100vh;
}

#captchaCode {
	display: inline-block;
}
#botdetect-captcha {
	display: flex;
    flex-direction: row;
    justify-content: center;
    margin-bottom: 15px;
}
</style>
<%@ include file="/frontend/files/loginCSS.file" %> <!-- 登入必要檔案 -->
<body>
<%@ include file="/frontend/files/login.file" %>   <!-- 登入必要檔案 -->
<%@ include file="/frontend/files/loginbox.file" %>  <!-- 登入必要檔案 -->
<%@ include file="/frontend/files/header.file" %> <!-- 上方導覽 -->

	<form id="resetpwd" method="POST">
		<div
			style="display: flex; flex-direction: row; justify-content: center;">


		</div>
		<label for="email">輸入要重設的會員帳號</label> 
		<input id="email" type="email" name="email" placeholder="E-Mail" required>
		<label for="botdetect-captcha"> <span>請輸入下方圖片中的文字:</span> <!-- captcha code: user-input textbox -->
		<input type="text" id="userCaptchaInput">
		</label>
		<div id="botdetect-captcha" data-captchastylename="jqueryBasicCaptcha"></div>
		<button class="g-recaptcha btn btn-outline-dark btn" data-sitekey="reCAPTCHA_site_key"
			data-callback='onSubmit' data-action='submit'>確認送出</button>
	</form>
	
	<%@ include file="/frontend/files/commonJS.file"%>
	<script src="<%=request.getContextPath()%>/js/jquery-captcha.js"></script>
	<script>
$(function () {

    // create the frontend captcha instance in the DOM.ready event-handler;
    // and set the captchaEndpoint property to point to 
    // the captcha endpoint path on your app's backend
    var captcha = $('#botdetect-captcha').captcha({
        captchaEndpoint: "<%=request.getContextPath()%>/simple-captcha-endpoint"
		});
			// process the form on submit event
			$('#resetpwd').submit(
					function(event) {

						// get the user-entered captcha code value to be validated at the backend side        
						var userEnteredCaptchaCode = captcha
								.getUserEnteredCaptchaCode();

						// get the id of a captcha instance that the user tried to solve
						var captchaId = captcha.getCaptchaId();
						var email = $("#email").val()
						var postData = {
							// add the user-entered captcha code value to the post data
							userEnteredCaptchaCode : userEnteredCaptchaCode,
							// add the id of a captcha instance to the post data
							captchaId : captchaId,
							email : email
						};

						// post the captcha data to the backend
						$.ajax({
							method : 'POST',
							url : "<%=request.getContextPath()%>/ResetPwd",
							dataType : 'json',
							contentType : 'application/json',
							data : JSON.stringify(postData),
							success : function(response) {
								if (response.success == false) {
									// captcha validation failed; show the error message
									Swal.fire({
										position:"center",
										title:"驗證碼錯誤",
										icon:"error",
										showConfirmButton: false,
										timer:2000
									})
									// call the captcha.reloadImage()
									// in order to generate a new captcha challenge
									captcha.reloadImage();
								}  else if (response.isMember == false){
									Swal.fire({
										position:"center",
										title:"此電子信箱位址尚未加入會員",
										icon:"error",
										showConfirmButton: false,
										timer:2000
									})
									captcha.reloadImage();
								}  else {
									// captcha validation succeeded; proceed with the workflow
									Swal.fire({
										position:"center",
										title:"重設密碼已寄出",
										text:"使用新密碼登入後，請至個人檔案>帳號資訊，修改個人密碼。3秒後重新導向首頁",
										icon:"success",
										showConfirmButton: false,
										timer:3000
									})
									setTimeout(function(){
										window.location.replace("<%=request.getContextPath()%>/frontend/index.jsp");
									}, 3000)
								}
							},
							error : function(error) {
								throw new Error(error);
							}
						});
						event.preventDefault();
					});
		});
	</script>
</body>
</html>