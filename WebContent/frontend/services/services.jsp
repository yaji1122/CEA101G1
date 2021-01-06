<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.services.model.*"%>

<%
ServicesService servicesSvc = new ServicesService();
List<ServicesVO> list = servicesSvc.getAll();
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8" />
<meta name="description" content="Sona Template" />
<meta name="keywords" content="Sona, unica, creative, html" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>Diamond Resort</title>


<%@ include file="/frontend/files/commonCSS.file"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/slick.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/slick-theme.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/slicknav.min.css"
	type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/front/services.css"
	type="text/css" />

</head>

<body>

	<!-- Header Section Begin -->
	<header class="header-section">
		<div class="menu-item">
			<div class="container">
				<div class="row">
					<div class="col-lg-3">
						<div class="logo">
							<a href="./index.html"> <img src="img/logo.png" alt="" />
							</a>
						</div>
					</div>
					<div class="col-lg-9">
						<div class="nav-menu">
							<nav class="mainmenu">
								<ul>
									<li class="active"><a href="index.html">回首頁</a></li>
									<li><a href="./pages.html" class="nav-event">至尊服務</a>
										<ul class="dropdown">
											<li><a href="./room-details.html">美容美體</a></li>
											<li><a href="./blog-details.html">各式服務</a></li>
										</ul></li>
									<li><a class="nav-event">已預約服務</a>
										<ul class="dropdown">
										</ul></li>
									<li><a href="<%=request.getContextPath()%>/frontend/services/servicesCart.jsp" class="nav-event">購物車</a>
								</ul>
							</nav>
						</div>
					</div>
				</div>
			</div>
		</div>
	</header>
	<!-- Header Section End -->

	<div id="banner">
		<div class="slider">
			<div class="slider-1">
				<img src="<%=request.getContextPath()%>/img/services/bannerSpa1.jpg"
					alt="" />
			</div>

			<div class="slider-1">
				<img src="<%=request.getContextPath()%>/img/services/bannerSpa2.jpg"
					alt="" />
			</div>

			<div class="slider-1">
				<img src="<%=request.getContextPath()%>/img/services/bannerSpa3.jpg"
					alt="" />
			</div>

			<div class="slider-1">
				<img src="<%=request.getContextPath()%>/img/services/bannerSpa4.jpg"
					alt="" />
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div id="context">
				<h2>Lorem ipsum dolor sit amet consectetur, adipisicing elit.
					Error omnis</h2>
				<p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Hic
					tenetur magnam obcaecati unde aspernatur ips</p>
			</div>
		</div>
	</div>

	<div id="boxServ">
		<c:forEach var="servicesVO" items="${list}" varStatus="vs">
			<div class="box-serv">
				<div class="card" style="width: 25rem;">
					<img
						src="<%=request.getContextPath()%>/ServicesServlet?servno=${servicesVO.serv_no}&action=getOneServicesPic"
						class="card-img-top" alt="...">
					<div class="card-body">
						<h5 class="card-title">${servicesVO.serv_name}</h5>
						<div class="card-p">
							<p class="card-text">${servicesVO.serv_info}</p>
						</div>
						<button type="button" class="btn btn-primary" data-toggle="modal"
							data-target="#exampleModal${vs.index}"
							id="viewDetailButton${vs.index}">立即預約</button>
					</div>
				</div>
			</div>

			<!-- Modal -->
			<div class="modal fade" id="exampleModal${vs.index}" tabindex="-1"
				aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">服務預約</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<form name="shoppingForm"
								action="${pageContext.request.contextPath}/ServicesCartServlet"
								method="POST">
						<div class="modal-body">
								<p>請選擇日期:</p>
								<input name="hiredate" class="f_date1" type="text">

								<div class="form-group">
									<label for="exampleFormControlSelect1">預約時段</label> <select
										class="form-control" id="exampleFormControlSelect1">
										<option>2020/12/28 13:00-14:00</option>
										<option>2020/12/28 14:00-15:00</option>
										<option>2020/12/28 15:00-16:00</option>
										<option>2020/12/28 16:00-17:00</option>
									</select>
								</div>

								<div class="form-group">
									<label for="exampleFormControlSelect1">服務人數</label> <select name="quantity"
										class="form-control" id="exampleFormControlSelect1">
										<option>1</option>
										<option>2</option>
										<option>3</option>
										<option>4</option>
									</select>
								</div>
								<div class="form-group">
									<label for="exampleFormControlSelect2">服務場所</label> <select name="location"
										class="form-control" id="exampleFormControlSelect2">
										<option>102客房</option>
										<option>露天按摩A區</option>
										<option>露天按摩B區</option>
									</select>
								</div>
								<p>服務價格:${servicesVO.serv_price}</p>
								<input type="hidden" name="servicesNo" value="${servicesVO.serv_no}"> 
								<input type="hidden" name="price" value="${servicesVO.serv_price}"> 
								<!-- <input type="hidden" name="hiredate" value="2017-03-08T12:30:54">  -->
								<input type="hidden" name="action" value="ADD">
									
							


						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">取消</button>
							<input type="submit" class="btn btn-primary" value="預約送出">
						</div>
						</form>
					</div>
				</div>
			</div>
		</c:forEach>
		
		<jsp:include page="servicesCart.jsp" flush="true" />
	</div>



	<%@ include file="/frontend/files/commonJS.file"%>
	<!-- 基本JS檔案 -->
	<script src="${pageContext.request.contextPath}/js/slick.min.js"></script>
	<!-- Js Plugins -->
	<%-- <script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/slick.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/jquery.magnific-popup.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/jquery.nice-select.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.slicknav.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
	<script src="${pageContext.request.contextPath}/js/main.js"></script>
	<script src="${pageContext.request.contextPath}/js/index.js"></script> --%>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/jquery.datetimepicker.full.min.js"></script>


	<%--  <script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.nice-select.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.slicknav.js"></script>
    <script src="${pageContext.request.contextPath}/js/owl.carousel.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/template.js"></script>
    <script src="${pageContext.request.contextPath}/js/sweetalert.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/slick/slick.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.datetimepicker.full.js"></script> --%>
	<script>
		$('.slider').slick({
			autoplay : true,
			autoplaySpeed : 5000,
			speed : 1500,
			fade : true,
			arrows : false,
			dots : true,
		});
	</script>
	<script>
		$.datetimepicker.setLocale('zh'); // kr ko ja en
		$('.f_date1').datetimepicker({
			theme : '', //theme: 'dark',
			timepicker : true, //timepicker: false,
			step : 15, //step: 60 (這是timepicker的預設間隔60分鐘)
			format : 'Y-m-d H:i',
			value : new Date(),
		//disabledDates:    ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
		//startDate:	        '2017/07/10',  // 起始日
		//minDate:           '-1970-01-01', // 去除今日(不含)之前
		//maxDate:           '+1970-01-01'  // 去除今日(不含)之後
		});
	</script>
	<script>
		$(".btn-primary").click(function() {
			$(".servForm").addClass("show-servForm");
		});
		$(".servForm-close").click(function() {
			$(".servForm").removeClass("show-servForm");
		});
	</script>
</body>

</html>