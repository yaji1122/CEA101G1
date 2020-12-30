<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.members.model.*"%>
<!DOCTYPE html>
<html lang="en">
<head>	
        <%@ include file="/frontend/files/commonCSS.file" %> <!-- 基本CSS檔案 -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/slick.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/slick-theme.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/slicknav.min.css" type="text/css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/front/index.css" type="text/css" />
<title>Diamond Resort 戴蒙度假村</title>

</head>
<%@ include file="/frontend/files/loginCSS.file" %> <!-- 登入必要檔案 -->
<body>
<%@ include file="/frontend/files/login.file" %>   <!-- 登入必要檔案 -->
<%@ include file="/frontend/files/loginbox.file" %>  <!-- 登入必要檔案 -->
<%@ include file="/frontend/files/header.file" %> <!-- 上方導覽 -->
	<section class="hero-section">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="hero-text">
						<h1 style="font-size: 60px; text-shadow: 1px 1px black">
							<b style="color: rgb(255, 217, 0)">Deluxe</b>Relax
						</h1>
						<p>全世界最棒的海島型度假村，全方位的體貼服務，享受帝王般的待遇！</p>
					</div>
				</div>
			</div>
		</div>
	</section>

	<div class="homepage-video">
		<video width="condition" type="video/mp4" autoplay="autoplay"
			loop="loop" muted="muted">
			<source src="${pageContext.request.contextPath}/video/homepage.mp4"
				type="video/mp4" />
		</video>
	</div>
	<!-- Hero Section End -->
	<div class="booking">
		<form action="<%=request.getContextPath()%>/booking/Available?action=booking" method="POST">
			<div class="booking-form">
				<div class="box">
					<div class="check-date">
						<input type="text" class="date-input" id="date-in" name="date"
							autocomplete="off" placeholder="入住日期" required/> <label for="date-in"><i
							class="icon_calendar"></i></label>
					</div>
				</div>
				<div class="box">
					<div class="select-option">
						<select id="stay" name="stay" required>
							<option disabled selected>入住天數</option>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
							<option value="6">6</option>
							<option value="6">7</option>
						</select>
					</div>
				</div>
				<div class="box">
					<div class="select-option">
						<select id="guest" name="guest" required>
							<option value="" disabled selected>入住人數</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
							<option value="6">6</option>
						</select>
					</div>
				</div>
				<div class="check-vacant-button box">
					<button type="submit" id="check-room" class="vacant-check">空房查詢</button>
				</div>
			</div>
		</form>
	</div>
	<!-- About Us Section Begin -->
	<section class="news-section">
		<div class="container news-container">
			<div class="row">
				<div class="col-lg-4">
					<div class="about-text">
						<div class="section-title">
							<span>最新消息</span>
						</div>
						<p class="f-para">戴蒙頂級奢華度假村 - 全年都是完美之選。
							不單從度假村的全包式假期到探索在地特色景點，都給您前所未有的新發現。</p>
						<p class="s-para">
							從交通接送、舒適住房、三餐精緻饗宴、全日無限吧台享用飲品與輕食、豐富水陸活動與指導，還有晚間娛樂活動與表演… 戴蒙度假村
							將假期中的吃、喝、玩、樂、住都為您貼心規劃好，您只需按照您的時間與心情，自由選擇我們為您規劃好的活動！無論是與事業夥伴、昔日同窗或是好友同行，都能在這裡留下獨一無二的美好假期回憶！
						</p>
						<a href="about-us.html" class="primary-btn about-btn">了解更多..</a>
					</div>
				</div>
				<div class="col-lg-8">
					<div class="about-text">
						<div class="section-title">
							<span>網紅推薦</span>
						</div>
						<div class="testimonial-slider">
							<div class="ts-item">
								<p>
									房間很乾淨，明亮。床好睡，地毯乾淨。客房服務快速，要求更換新浴袍，5分鍾內即送達。這次住9478，沙發有嚴重被壓扁的現象，需換新椅子。
									還有早餐也改爲半自助（看菜單點早餐主食）沒有之前的「澎派」據說只有週末的早餐才會是原有的豪華自助餐型態。
									早餐時我共點了3次熱拿鐵，送來的每一杯的溫度都差很多，（溫涼的，熱的，超熱的），可能要再加強。我愛他們check out
									time 12:00,所以可以睡晚一點，吃完早餐再去游泳洗澡後再從容離開飯店。</p>
								<div class="ti-author">
									<div class="rating">
										<i class="icon_star"></i> <i class="icon_star"></i> <i
											class="icon_star"></i> <i class="icon_star"></i> <i
											class="icon_star-half_alt"></i>
									</div>
									<h5>- 大衛海鮮</h5>
								</div>
								<img
									src="${pageContext.request.contextPath}/img/testimonial-logo.png"
									alt="" />
							</div>
							<div class="ts-item">
								<p>首先我非常感謝迎賓櫃檯經理
									周昭安先生，他的服務、他的安排一掃解決我剛check-in的不愉快，真心的很謝謝他，也謝謝他知道我生日，送了一個非常美味的蛋糕～誏我的旅程增加了美好的驚喜～雖然看了很多評價説他們的服務人員態度不佳，但是我必須說他們還是有很多服務人員是非常好的，像是紫艷酒吧的晚上11點的服務先生，更特別感謝周昭安先生，謝謝你的服務令人讚賞！因為之前來台北都是住寒舍艾美，但是因為他「周昭安
									經理」我會選擇W Taipei hotel~~~</p>
								<div class="ti-author">
									<div class="rating">
										<i class="icon_star"></i> <i class="icon_star"></i> <i
											class="icon_star"></i> <i class="icon_star"></i> <i
											class="icon_star-half_alt"></i>
									</div>
									<h5>- 萬磁王</h5>
								</div>
								<img
									src="${pageContext.request.contextPath}/img/testimonial-logo.png"
									alt="" />
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>


	<section class="hp-room-section">
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-12">
					<div class="section-title">
						<span>絕美套房</span>
						<h2>在美景裡盡情享受</h2>
					</div>
				</div>
			</div>
			<div class="hp-room-items">
				<div class="row">
					<div class="col-lg-3 col-md-6">
						<div class="hp-room-item set-bg"
							data-setbg="${pageContext.request.contextPath}/img/resort/room/standard-room.jpg">
							<div class="hr-text">
								<h3>經典海景房</h3>
								<h2>
									20000$<span>/每晚</span>
								</h2>
								<table>
									<tbody>
										<tr>
											<td class="r-o">空間:</td>
											<td>500坪</td>
										</tr>
										<tr>
											<td class="r-o">人數:</td>
											<td>最多2人</td>
										</tr>
										<tr>
											<td class="r-o">一大床:</td>
											<td>150 * 200cm</td>
										</tr>
										<tr>
											<td class="r-o">房間特色:</td>
											<td>無限美麗海景</td>
										</tr>
									</tbody>
								</table>
								<a href="rooms.html" class="primary-btn">查看詳情</a>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-6">
						<div class="hp-room-item set-bg"
							data-setbg="${pageContext.request.contextPath}/img/resort/room/luxury-room.jpg">
							<div class="hr-text">
								<h3>豪華蜜月房</h3>
								<h2>
									30000$<span>/每晚</span>
								</h2>
								<table>
									<tbody>
										<tr>
											<td class="r-o">空間:</td>
											<td>550坪</td>
										</tr>
										<tr>
											<td class="r-o">人數:</td>
											<td>最多2人</td>
										</tr>
										<tr>
											<td class="r-o">一大床:</td>
											<td>200 * 200cm</td>
										</tr>
										<tr>
											<td class="r-o">房間特色:</td>
											<td>200公尺私人海灘</td>
										</tr>
									</tbody>
								</table>
								<a href="rooms.html" class="primary-btn">查看詳情</a>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-6">
						<div class="hp-room-item set-bg"
							data-setbg="${pageContext.request.contextPath}/img/resort/room/deluxe-room.jpeg">
							<div class="hr-text">
								<h3>奢華海景房</h3>
								<h2>
									50000$<span>/每晚</span>
								</h2>
								<table>
									<tbody>
										<tr>
											<td class="r-o">空間大小:</td>
											<td>600坪</td>
										</tr>
										<tr>
											<td class="r-o">人數:</td>
											<td>最多2人</td>
										</tr>
										<tr>
											<td class="r-o">一大床:</td>
											<td>250cm * 250cm</td>
										</tr>
										<tr>
											<td class="r-o">房間特色:</td>
											<td>提供個人遊艇</td>
										</tr>
									</tbody>
								</table>
								<a href="rooms.html" class="primary-btn">查看詳情</a>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-6">
						<div class="hp-room-item set-bg"
							data-setbg="${pageContext.request.contextPath}/img/resort/room/Poseidon_Index.jpg">
							<div class="hr-text">
								<h3>波賽頓套房</h3>
								<h2>
									100000$<span>/每晚</span>
								</h2>
								<table>
									<tbody>
										<tr>
											<td class="r-o">空間大小：</td>
											<td>無限</td>
										</tr>
										<tr>
											<td class="r-o">人數:</td>
											<td>最多2人</td>
										</tr>
										<tr>
											<td class="r-o">一大床</td>
											<td>250 * 300cm</td>
										</tr>
										<tr>
											<td class="r-o">房間特色:</td>
											<td>成為海底之王</td>
										</tr>
									</tbody>
								</table>
								<a href="rooms.html" class="primary-btn">查看詳情</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>


	<section class="services-section spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="section-title">
						<span>房客專屬</span>
						<h2>享受帝王般的服務</h2>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-4 col-md-12">
					<div class="service-item">
						<i class="flaticon-036-parking"></i>
						<h4>空中接送</h4>
						<p>戴蒙度假村有專屬直升機與特殊海關通行許可，讓您無需等待冗長的過關服務，直接由機場出發到您預定的房間。</p>
					</div>
				</div>
				<div class="col-lg-4 col-md-12">
					<div class="service-item">
						<i class="flaticon-044-clock-1"></i>
						<h4>24HR客房服務</h4>
						<p>每分每秒有專業的服務人員替您服務，泰式MASSAGE，香氛精油SPA，讓您徹底放鬆。</p>
					</div>
				</div>
				<div class="col-lg-4 col-md-12">
					<div class="service-item">
						<i class="flaticon-012-cocktail"></i>
						<h4>上門餐點</h4>
						<p>動一動手指，餐點很快就到你門口！</p>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Services Section End -->

	<!-- Footer Section Begin -->
	<footer class="footer-section">
		<div class="container">
			<div class="footer-text">
				<div class="row">
					<div class="col-lg-6 ft-info">
						<div class="ft-contact">
							<h6>聯繫我們</h6>
							<ul>
								<li>(08) 0857 9487</li>
								<li>DiamondResort101@gmail.com</li>
								<li>Somewhere on the earth</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</footer>
	<!-- Footer Section End -->

	<!-- Js Plugins -->
	<%@ include file="/frontend/files/commonJS.file" %> <!-- 基本JS檔案 -->
	<script src="${pageContext.request.contextPath}/js/slick.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/front/index.js"></script>
</body>
</html>
