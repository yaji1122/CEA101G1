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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/front/chatbox.css" type="text/css" />
<title>Diamond Resort 戴蒙度假村</title>

</head>
<style>
	.nice-select.open .list {
	    transform: scale(1) translateY(-101%)
	}
	.nice-select .list {
		top:0;
		transform: translateY(-50%);
	}
</style>
<%@ include file="/frontend/files/loginCSS.file" %> <!-- 登入必要檔案 -->
<body <c:if test="${member != null }">onunload="disconnect();"</c:if> >
<%@ include file="/frontend/files/login.file" %>   <!-- 登入必要檔案 -->
<%@ include file="/frontend/files/loginbox.file" %>  <!-- 登入必要檔案 -->
<%@ include file="/frontend/files/header.file" %> <!-- 上方導覽 -->
<c:if test="${member != null}">
<!-- chatbox -->
<div id="chat-circle" class="btn btn-raised">
        <div id="chat-overlay"></div>
        <i class="fas fa-sms"></i>
</div>
<div class="chat-box">
    <div class="chat-box-header">
      <i class="far fa-gem" style="margin-right:5px"></i>專屬線上客服
      <span class="chat-box-toggle"><i class="fas fa-minus"></i></span>
    </div>
    <div class="chat-box-body">
      <div class="chat-box-overlay">   
      </div>
      <div class="chat-logs">
       
      </div><!--chat-log -->
    </div>
    <div class="chat-input">      
      <input type="text" id="chat-input" placeholder="Send a message..." onkeydown="if (event.keyCode == 13) sendMessage();"/>
      <button class="chat-submit" id="chat-submit"><i class="fas fa-paper-plane"></i></button>
    </div>
  </div>
<!-- chatbox -->
</c:if>
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
		<form action="<%=request.getContextPath()%>/frontend/roomrsv/available.jsp" method="POST">
			<div class="booking-form">
				<div class="box">
					<div class="check-date">
						<input type="text" class="date-input" id="date-in" name="date-in"
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
			<div>
				<div>
					<div class="about-text">
						<div class="section-title">
							<span>最新消息</span>
						</div>
						<p class="s-para">
							老闆跑路了，年前大出清，所有鍋碗瓢盆，高級傢俱，一率半價出售！！
						</p>
						<a href="about-us.html" class="primary-btn about-btn"><i class="far fa-hand-point-right"></i>查看更多最新消息</a>
					</div>
				</div>
				<div>
					<div class="about-text">
						<div class="section-title">
							<span>著名網紅推薦</span>
						</div>
						<div class="testimonial-slider">
							<div class="ts-item">
								<p>
									房間很乾淨，明亮。床好睡，地毯乾淨。客房服務快速，要求更換新浴袍，5分鍾內即送達。這次住9478，沙發有嚴重被壓扁的現象，需換新椅子。
									還有早餐也改爲半自助（看菜單點早餐主食）沒有之前的「澎派」據說只有週末的早餐才會是原有的豪華自助餐型態。
									早餐時我共點了3次熱拿鐵，送來的每一杯的溫度都差很多，（溫涼的，熱的，超熱的），可能要再加強。我愛他們check out
									time 12:00,所以可以睡晚一點，吃完早餐再去游泳洗澡後再從容離開飯店。</p>
								<div class="ti-author">
								<i class="fab fa-tripadvisor"></i>
									<div class="rating">
										<i class="icon_star"></i> <i class="icon_star"></i> <i
											class="icon_star"></i> <i class="icon_star"></i> <i
											class="icon_star-half_alt"></i>
									</div>
									<h5>- 大衛海鮮</h5>
								</div>
							</div>
							<div class="ts-item">
								<p>你們的旅館非常的不乾淨，我在裡面殺了好幾隻鬼了，希望貴飯店的安檢能改善。除此之外餐點和住宿都非常不錯，我妹妹彌豆子很喜歡這裡的下午茶。</p>
								<div class="ti-author">
								<i class="fab fa-tripadvisor"></i>
									<div class="rating">
										<i class="icon_star"></i> <i class="icon_star"></i> <i
											class="icon_star"></i> <i class="icon_star"></i> <i
											class="icon_star-half_alt"></i>
									</div>
									<h5>- 竈門 炭治郎</h5>
								</div>
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
						<span>絕美海景套房</span>
						<h2>美麗到令人屏息的億萬級海景</h2>
					</div>
				</div>
			</div>
			<div class="hp-room-items">
				<div class="row">
					<div class="col-lg-3 col-md-6">
						<div class="hp-room-item set-bg"
							data-setbg="${pageContext.request.contextPath}/img/room/standard/standard.jpeg">
							<div class="hr-text">
								<h3>Standard</h3>
								<h2>
									USD$500<span>人/晚</span>
								</h2>
								<table>
									<tbody>
										<tr>
											<td class="r-o">空間:</td>
											<td>500坪</td>
										</tr>
										<tr>
											<td class="r-o">人數:</td>
											<td>2~4人</td>
										</tr>
										<tr>
											<td class="r-o">兩大床:</td>
											<td>150 * 200cm</td>
										</tr>
									</tbody>
								</table>
								<a href="<%=request.getContextPath()%>/frontend/rooms/rooms.jsp" class="primary-btn">More..</a>
							</div>
						</div>	
					</div>
					<div class="col-lg-3 col-md-6">
						<div class="hp-room-item set-bg"
							data-setbg="${pageContext.request.contextPath}/img/room/honeymoon/honeymoon1.jpeg">
							<div class="hr-text">
								<h3>Honey Moon</h3>
								<h2>
									USD$600<span>人/晚</span>
								</h2>
								<table>
									<tbody>
										<tr>
											<td class="r-o">空間:</td>
											<td>550坪</td>
										</tr>
										<tr>
											<td class="r-o">人數:</td>
											<td>2人</td>
										</tr>
										<tr>
											<td class="r-o">一大床:</td>
											<td>200 * 200cm</td>
										</tr>
									</tbody>
								</table>
								<a href="<%=request.getContextPath()%>/frontend/rooms/rooms.jsp" class="primary-btn">More..</a>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-6">
						<div class="hp-room-item set-bg"
							data-setbg="${pageContext.request.contextPath}/img/room/deluxe/deluxe.jpeg">
							<div class="hr-text">
								<h3>Deluxe</h3>
								<h2>
									USD$750<span>人/晚</span>
								</h2>
								<table>
									<tbody>
										<tr>
											<td class="r-o">空間大小:</td>
											<td>600坪</td>
										</tr>
										<tr>
											<td class="r-o">人數:</td>
											<td>2~6人</td>
										</tr>
										<tr>
											<td class="r-o">三大床:</td>
											<td>250 * 250cm</td>
										</tr>
									</tbody>
								</table>
								<a href="<%=request.getContextPath()%>/frontend/rooms/rooms.jsp" class="primary-btn">More..</a>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-6">
						<div class="hp-room-item set-bg"
							data-setbg="${pageContext.request.contextPath}/img/room/poseidon/poseidon1.jpeg">
							<div class="hr-text">
								<h3>Poseidon</h3>
								<h2>
									USD$900<span>人/晚</span>
								</h2>
								<table>
									<tbody>
										<tr>
											<td class="r-o">空間大小：</td>
											<td>無限</td>
										</tr>
										<tr>
											<td class="r-o">人數:</td>
											<td>2~4人</td>
										</tr>
										<tr>
											<td class="r-o">一大床</td>
											<td>250 * 300cm</td>
										</tr>
									</tbody>
								</table>
								<a href="<%=request.getContextPath()%>/frontend/rooms/rooms.jsp" class="primary-btn">More..</a>
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
	
	<script>
	<c:if test="${member != null}">
		  var INDEX = 0; 
		  var memberImg = "<%=request.getContextPath()%>/MembersServlet?action=getMbPicForChat&mb_id=${member.mb_id}";
		  var empImg = "<%=request.getContextPath()%>/img/avatar/csAvatar.jpg";
		  $("#chat-submit").click(sendMessage);
		  
		  function sendMsg() {
		    var msg = $("#chat-input").val(); 
		    if(msg.trim() == ''){
		      return false;
		    }
		    generate_message(msg, 'member');
		  }
		  function generate_message(msg, type) {
		    INDEX++;
		    let img;
		    type === "member" ? img = memberImg : img = empImg
		    var str="";
		    str += "<div id='cm-msg-"+INDEX+"' class=\"chat-msg "+type+"\">";
		    str += "          <span class=\"msg-avatar\">";
		    str += "            <img src=\"" + img + "\">";
		    str += "          <\/span>";
		    str += "          <div class=\"cm-msg-text\">";
		    str += msg;
		    str += "          <\/div>";
		    str += "        <\/div>";
		    $(".chat-logs").append(str);
		    $("#cm-msg-"+INDEX).hide().fadeIn(300);
		    $("#chat-input").val(''); 
		    $(".chat-logs").stop().animate({ scrollTop: $(".chat-logs")[0].scrollHeight}, 1000);    
		  }  
		  
		  $("#chat-circle").click(function() {  
			if (webSocket == null){
				connect();
			}
		    $("#chat-circle").toggle(500);
		    $(".chat-box").toggle(500);	
		  })
		  
		  $(".chat-box-toggle").click(function() {
		    $("#chat-circle").toggle(500);
		    $(".chat-box").toggle(500);
		  })
		 //WebSocket
        var MyPoint = "/customerWS/${member.mb_id}";
		var host = window.location.host;
		var path = window.location.pathname;
		var webCtx = path.substring(0, path.indexOf('/', 1));
		var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
		var empID;
		var empName;
		var webSocket = null;
	
		function connect() {
			// create a websocket
			webSocket = new WebSocket(endPointURL);
	
			webSocket.onopen = function(event) {
				console.log("Connect Success!");
				document.getElementById('chat-input').disabled = false;
			}
	
			webSocket.onmessage = function(event) {
				var jsonObj = JSON.parse(event.data);
				if ("open" === jsonObj.type) {
					empID = jsonObj.empID;
					empImg = "<%=request.getContextPath()%>/emp/emp.do?action=getEmpPic&emp_id=" + empID;
					empName = jsonObj.empName;
					let msg = jsonObj.message;
					generate_message(msg, "emp")
				} else if ("history" === jsonObj.type) {
					let memberID;
					if (jsonObj.sender.indexOf("MEM") >= 0){
						memberID = jsonObj.sender;
					} else {
						memberID = jsonObj.receiver;
					}
					// 這行的jsonObj.message是從redis撈出與客戶的歷史訊息，再parse成JSON格式處理
					var messages = JSON.parse(jsonObj.message);
					for (var i = 0; i < messages.length; i++) {
						var historyData = JSON.parse(messages[i]);
						var msg = historyData.message;
						var time = historyData.time;
						// 根據發送者是自己還是對方來給予不同的class名, 以達到訊息左右區分
						let type = "";
						historyData.sender.indexOf("MEM") >= 0 ? type = 'member' : type = 'emp';
						generate_message(msg, type)
					}
				} else if ("chat" === jsonObj.type) {
					let msg = jsonObj.message;
					let type;
					jsonObj.sender.indexOf("MEM") >= 0 ? type = 'member' : type = 'emp';
					generate_message(msg, type)
				} else if ("empNotAvailable" === jsonObj.type){
					generate_message("目前客服人員均忙線中，請稍候。", "emp")
				}
			};
	
			webSocket.onclose = function(event) {
				console.log("Disconnected!");
			};
		}
		
		function sendMessage() {
			var inputMessage = document.getElementById("chat-input");
			var memberID = "${member.mb_id}";
			var memberName = "${member.mb_name}";
			var message = inputMessage.value.trim();
			let time = new Date();
			let timeStr = time.getFullYear() + "-" + (time.getMonth()+1) + "-" 
						+ time.getDate() + " " + time.getHours() + ":" + time.getMinutes();
			if (message === "") {
				inputMessage.focus();
				return;
			} else {
				var jsonObj = {
					"type" : "chat",
					"sender" : memberID + "-" + memberName,
					"receiver" : empID + "-" + empName,
					"message" : message,
					"time": timeStr,
				};
				webSocket.send(JSON.stringify(jsonObj));
				inputMessage.value = "";
				inputMessage.focus();
			}
		}
		
		function disconnect() {
			if (webSocket != null) webSocket.close();
		}
	</c:if>
	</script>
	
</body>
</html>
