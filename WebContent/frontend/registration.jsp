<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("templname", request.getParameter("temp-lname"));
pageContext.setAttribute("tempfname", request.getParameter("temp-fname"));
pageContext.setAttribute("tempemail", request.getParameter("temp-email"));
pageContext.setAttribute("temppassword", request.getParameter("temp-password"));
pageContext.setAttribute("tempconfirmpassword", request.getParameter("temp-confirm-password"));
%>
<!DOCTYPE html>
<html lang="en">
<head>

<title>Diamond Resort Registration</title>
<%@ include file="/frontend/files/commonCSS.file"%>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css"
	integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA=="
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/intlTelInput.min.css"
	type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/front/registration.css" />
</head>

<body>
	<div class="wrapper">
		<div class="container-fluid">
			<div class="row">
				<div>
					<div class="card">
						<form id="msform" enctype="multipart/form-data" method="post">
							<input type="text" name="action" value="insert_member"
								style="display: none;">
							<!-- progressbar -->
							<ul id="progressbar">
								<li class="active" id="welcome"><strong>Welcome</strong></li>
								<li id="personal"><strong>Personal</strong></li>
								<li id="payment"><strong>Payment</strong></li>
								<li id="confirm"><strong>Finish</strong></li>
							</ul>

							<!-- fieldsets -->
							<fieldset>
								<h4 class="steps">Step 1 - 4</h4>
								<div class="form-card form1">
									<div class="row">
										<div class="col-6 offset-3">
											<h4 id="heading">Sign Up Your User Account</h4>
											<p>Fill all form field to go to next step</p>
											<h2 class="fs-title">Welcome to Diamond Resorts®:</h2>
										</div>
									</div>
									<p>Join Diamond Resorts®, your new loyalty program. It’s a
										world filled with thoughtful perks, personal connections and
										amazing experiences. And it revolves around you. Beyond great
										locations, luxurious rooms and top-notch amenities, Diamond
										Resorts® connects you to the people, places and stories at the
										heart of your world.</p>
									<p>
										For more information about privacy policy and term please <a
											href="private policy.html" target="_blank">see more</a>
									</p>

									<p class="custom-checkbox">
										<input type="checkbox" class="custom-control-input"
											id="customCheck1" required /> <label
											class="custom-control-label" for="customCheck1">I
											agree to the Diamond Resorts® Terms & Conditions and have
											read the Privacy Policy</label>
									</p>
								</div>
								<input type="button" name="next" class="next action-button"
									value="Next" />
							</fieldset>
							<fieldset class="form2">
								<h2 class="steps">Step 2 - 4</h2>
								<div class="form-card">
									<h2 class="fs-title">個人基本資料:</h2>
									<label class="fieldlabels">電子信箱(E-mail): <b>*</b></label> <input
										type="text" id="mb_email" name="mb_email" value="${tempemail}"
										pattern="^.+[\x40]{1}.+[.]{1}.*$" placeholder="Email"
										oninvalid="this.setCustomValidity('請輸入正確電子郵件格式')"
  										oninput="this.setCustomValidity('')"
										maxlength="50" required /> <label class="fieldlabels">密碼(Password):
										英文大小寫至少一個，最少8碼<b>*</b>
									</label> <input type="password" id="mb_pwd" name="mb_pwd"
										placeholder="Password" minlength="8" pattern="[a-zA-Z0-9]{8,}"
										oninvalid="this.setCustomValidity('格式錯誤(大小寫字母至少一個，共8個字元)')"
  										oninput="this.setCustomValidity('')"
										value="${temppassword}" required /> <label
										class="fieldlabels confirm_laebl">確認密碼(Confirm
										Password): <b>*</b>
									</label> <input type="password" id="mb_cpwd" name="mb_cpwd"
										placeholder="Confirm Password" value="${tempconfirmpassword}"
										required /> <label class="fieldlabels">姓(Last Name):
										<b>*</b>
									</label> <input type="text" id="mb_lname" name="mb_lname"
										value="${templname}" placeholder="First Name" maxlength="20"
										required /> <label class="fieldlabels">名(First Name):
										<b>*</b>
									</label> <input type="text" id="mb_fname" name="mb_fname"
										value="${tempfname}" placeholder="Last Name" maxlength="50"
										required /> <label class="fieldlabels">出生日期(Birthday):
										<b>*</b>
									</label> <input type="text" id="mb_bd" name="mb_bd"
										placeholder="Date of Birth" required /> <label
										class="fieldlabels">聯絡電話(Contact No.): <b>*</b></label> <input
										type="tel" id="mb_phone" name="mb_phone" 
										placeholder="Contact No." maxlength="20" required /> <label
										class="fieldlabels">居住地址(Address): <b>*</b></label> <input
										type="text" name="mb_city" placeholder="Address of City"
										required /> <input type="text" name="mb_town"
										placeholder="Address of Town" required /> <input type="text"
										name="mb_address" placeholder="Address" required /> <label
										class="fieldlabels">上傳個人照片(Upload Your Photo) optional</label>
										<input type="file" name="mb_pic" accept="image/*"
										style="text-align-last: center" />
								</div>
								<input type="button" name="next"
									class="next action-button form2next" value="Next" /> <input
									type="button" name="previous"
									class="previous action-button-previous" value="Previous" />
							</fieldset>
							<fieldset>
								<div class="credit-card">
									<h2 class="steps">Step 3 - 4</h2>
									<div class="form-card">
										<div class="containers preload">
											<div class="creditcard">
												<div class="front">
													<div id="ccsingle"></div>
													<svg version="1.1" id="cardfront"
														xmlns="http://www.w3.org/2000/svg"
														xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
														viewBox="0 0 750 471"
														style="enable-background: new 0 0 750 471"
														xml:space="preserve">
                                                            <g
															id="Front">
                                                                <g
															id="CardBackground">
                                                                    <g
															id="Page-1_1_">
                                                                        <g
															id="amex_1_">
                                                                            <path
															id="Rectangle-1_1_" class="lightcolor grey"
															d="M40,0h670c22.1,0,40,17.9,40,40v391c0,22.1-17.9,40-40,40H40c-22.1,0-40-17.9-40-40V40
                                                                             C0,17.9,17.9,0,40,0z" />
                                                                        </g>
                                                                    </g>
                                                                    <path
															class="darkcolor greydark"
															d="M750,431V193.2c-217.6-57.5-556.4-13.5-750,24.9V431c0,22.1,17.9,40,40,40h670C732.1,471,750,453.1,750,431z" />
                                                                </g>
                                                                <text
															transform="matrix(1 0 0 1 60.106 295.0121)"
															id="svgnumber" class="st2 st3 st4">
                                                                    0123 4567 8910 1112
                                                                </text>
                                                                <text
															transform="matrix(1 0 0 1 54.1064 428.1723)" id="svgname"
															class="st2 st5 st6">
                                                                    JOHN DOE
                                                                </text>
                                                                <text
															transform="matrix(1 0 0 1 54.1074 389.8793)"
															class="st7 st5 st8">
                                                                    cardholder name
                                                                </text>
                                                                <text
															transform="matrix(1 0 0 1 479.7754 388.8793)"
															class="st7 st5 st8">
                                                                    expiration
                                                                </text>
                                                                <text
															transform="matrix(1 0 0 1 65.1054 241.5)"
															class="st7 st5 st8">
                                                                    card number
                                                                </text>
                                                                <g>
                                                                    <text
															transform="matrix(1 0 0 1 574.4219 433.8095)"
															id="svgexpire" class="st2 st5 st9">
                                                                        01/23
                                                                    </text>
                                                                    <text
															transform="matrix(1 0 0 1 479.3848 417.0097)"
															class="st2 st10 st11">
                                                                        VALID
                                                                    </text>
                                                                    <text
															transform="matrix(1 0 0 1 479.3848 435.6762)"
															class="st2 st10 st11">
                                                                        THRU
                                                                    </text>
                                                                    <polygon
															class="st2" points="554.5,421 540.4,414.2 540.4,427.9 		" />
                                                                </g>
                                                                <g
															id="cchip">
                                                                    <g>
                                                                        <path
															class="st2"
															d="M168.1,143.6H82.9c-10.2,0-18.5-8.3-18.5-18.5V74.9c0-10.2,8.3-18.5,18.5-18.5h85.3
                                                                         c10.2,0,18.5,8.3,18.5,18.5v50.2C186.6,135.3,178.3,143.6,168.1,143.6z" />
                                                                    </g>
                                                                    <g>
                                                                        <g>
                                                                            <rect
															x="82" y="70" class="st12" width="1.5" height="60" />
                                                                        </g>
                                                                        <g>
                                                                            <rect
															x="167.4" y="70" class="st12" width="1.5" height="60" />
                                                                        </g>
                                                                        <g>
                                                                            <path
															class="st12"
															d="M125.5,130.8c-10.2,0-18.5-8.3-18.5-18.5c0-4.6,1.7-8.9,4.7-12.3c-3-3.4-4.7-7.7-4.7-12.3
                                                                             c0-10.2,8.3-18.5,18.5-18.5s18.5,8.3,18.5,18.5c0,4.6-1.7,8.9-4.7,12.3c3,3.4,4.7,7.7,4.7,12.3
                                                                             C143.9,122.5,135.7,130.8,125.5,130.8z M125.5,70.8c-9.3,0-16.9,7.6-16.9,16.9c0,4.4,1.7,8.6,4.8,11.8l0.5,0.5l-0.5,0.5
                                                                             c-3.1,3.2-4.8,7.4-4.8,11.8c0,9.3,7.6,16.9,16.9,16.9s16.9-7.6,16.9-16.9c0-4.4-1.7-8.6-4.8-11.8l-0.5-0.5l0.5-0.5
                                                                             c3.1-3.2,4.8-7.4,4.8-11.8C142.4,78.4,134.8,70.8,125.5,70.8z" />
                                                                        </g>
                                                                        <g>
                                                                            <rect
															x="82.8" y="82.1" class="st12" width="25.8" height="1.5" />
                                                                        </g>
                                                                        <g>
                                                                            <rect
															x="82.8" y="117.9" class="st12" width="26.1" height="1.5" />
                                                                        </g>
                                                                        <g>
                                                                            <rect
															x="142.4" y="82.1" class="st12" width="25.8" height="1.5" />
                                                                        </g>
                                                                        <g>
                                                                            <rect
															x="142" y="117.9" class="st12" width="26.2" height="1.5" />
                                                                        </g>
                                                                    </g>
                                                                </g>
                                                            </g>
                                                            <g id="Back"></g>
                                                        </svg>
												</div>
												<div class="back">
													<svg version="1.1" id="cardback"
														xmlns="http://www.w3.org/2000/svg"
														xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
														viewBox="0 0 750 471"
														style="enable-background: new 0 0 750 471"
														xml:space="preserve">
                                                            <g
															id="Front">
                                                                <line
															class="st0" x1="35.3" y1="10.4" x2="36.7" y2="11" />
                                                            </g>
                                                            <g id="Back">
                                                                <g
															id="Page-1_2_">
                                                                    <g
															id="amex_2_">
                                                                        <path
															id="Rectangle-1_2_" class="darkcolor greydark"
															d="M40,0h670c22.1,0,40,17.9,40,40v391c0,22.1-17.9,40-40,40H40c-22.1,0-40-17.9-40-40V40
                                                                         C0,17.9,17.9,0,40,0z" />
                                                                    </g>
                                                                </g>
                                                                <rect
															y="61.6" class="st2" width="750" height="78" />
                                                                <g>
                                                                    <path
															class="st3"
															d="M701.1,249.1H48.9c-3.3,0-6-2.7-6-6v-52.5c0-3.3,2.7-6,6-6h652.1c3.3,0,6,2.7,6,6v52.5
                                                                     C707.1,246.4,704.4,249.1,701.1,249.1z" />
                                                                    <rect
															x="42.9" y="198.6" class="st4" width="664.1"
															height="10.5" />
                                                                    <rect
															x="42.9" y="224.5" class="st4" width="664.1"
															height="10.5" />
                                                                    <path
															class="st5"
															d="M701.1,184.6H618h-8h-10v64.5h10h8h83.1c3.3,0,6-2.7,6-6v-52.5C707.1,187.3,704.4,184.6,701.1,184.6z" />
                                                                </g>
                                                                <text
															transform="matrix(1 0 0 1 621.999 227.2734)"
															id="svgsecurity" class="st6 st7">
                                                                    985
                                                                </text>
                                                                <g
															class="st8">
                                                                    <text
															transform="matrix(1 0 0 1 518.083 280.0879)"
															class="st9 st6 st10">
                                                                        security code
                                                                    </text>
                                                                </g>
                                                                <rect
															x="58.1" y="378.6" class="st11" width="375.5"
															height="13.5" />
                                                                <rect
															x="58.1" y="405.6" class="st11" width="421.7"
															height="13.5" />
                                                                <text
															transform="matrix(1 0 0 1 59.5073 228.6099)"
															id="svgnameback" class="st12 st13">
                                                                    John Doe
                                                                </text>
                                                            </g>
                                                        </svg>
												</div>
											</div>
										</div>
										<div class="form-container">
											<div class="field-container">
												<label for="name">姓名(Name)</label> <input id="name"
													maxlength="20" type="text" name="credit-card-name" required />
											</div>
											<div class="field-container">
												<label for="cardnumber">信用卡號(Card Number)</label><span
													id="generatecard"></span> <input id="cardnumber"
													type="text" name="cardnumber" inputmode="numeric" required />
												<svg id="ccicon" class="ccicon" width="750" height="471"
													viewBox="0 0 750 471" version="1.1"
													xmlns="http://www.w3.org/2000/svg"
													xmlns:xlink="http://www.w3.org/1999/xlink"></svg>
											</div>
											<div class="field-container">
												<label for="expirationdate">到期日(Expiration)(mm/yy)</label> <input
													id="expirationdate" type="text" name="expirationdate"
													inputmode="numeric" required />
											</div>
											<div class="field-container">
												<label for="securitycode">安全碼(Security Code)</label> <input
													id="securitycode" type="text" name="csc" maxlength="3"
													inputmode="numeric" required />
											</div>
										</div>
									</div>

									<!-- </div> -->
									<div class="form3button">
										<input type="submit" name="next" class="next action-button"
											value="Submit" /> <input type="button" name="previous"
											class="previous action-button-previous form3next"
											value="Previous" />
									</div>
								</div>
							</fieldset>
							<fieldset>
								<div class="form-card success-page">
									<h2 class="steps">Step 4 - 4</h2>
									<i class="fas fa-user-check" style="font-size:56px; color:green"></i>
									<h2 class="success-text">
										<strong>歡迎加入戴蒙尊榮會員</strong>
									</h2>
									<div class="justify-content-center">
										<h4>Welcome Join Us !</h4>
										<h5>帳戶啟用連結已發送至您註冊的電子郵箱</h5>
									</div>
								</div>
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Footer Section End -->

	<!-- Js Plugins -->
	<%@ include file="/frontend/files/commonJS.file"%>
	<script
		src="${pageContext.request.contextPath}/js/intlTelInput-jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/imask/3.4.0/imask.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/front/registration.js"></script>
	<script>
	//Ajax
	
	</script>
</body>
</html>
