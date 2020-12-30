<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.actpic.model.*"%>
<%
	ActPicService actPicSvc = new ActPicService();
	List<ActPicVO> list = actPicSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<head>
    <meta charset="UTF-8" />
    <meta name="description" content="Sona Template" />
    <meta name="keywords" content="Sona, unica, creative, html" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Diamond Resort</title>
    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css?family=Lora:400,700&display=swap" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css?family=Cabin:400,500,600,700&display=swap" rel="stylesheet" />
    <!-- Css Styles -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/frontend_css/bootstrap.min.css" type="text/css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/frontend_css/font-awesome.min.css" type="text/css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/frontend_css/elegant-icons.css" type="text/css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/frontend_css/flaticon.css" type="text/css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/frontend_css/owl.carousel.min.css" type="text/css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/frontend_css/nice-select.css" type="text/css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/frontend_css/jquery-ui.min.css" type="text/css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/frontend_css/slicknav.min.css" type="text/css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/frontend_css/template.css" type="text/css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/frontend_css/all.min.css" type=”image/x-icon”> 
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/frontend_css/slick.css" type="text/css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/frontend_css/slick-theme.min.css" type="text/css"/> 
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/frontend_css/land-new.css" /> 
</head>

<body>
    
    <!-- Page Preloder -->
    <div id="preloder">
        <img id="preloaderpic" src="<%=request.getContextPath()%>/img/loading.png" />
        <div class="loader"></div>
    </div>
    <!-- Offcanvas Menu Section Begin -->
    <div class="offcanvas-menu-overlay"></div>
    <div class="canvas-open">
        <i class="icon_menu"></i>
    </div>
    <div class="offcanvas-menu-wrapper">
        <div class="canvas-close">
            <i class="icon_close"></i>
        </div>
        <div class="search-icon search-switch">
            <i class="icon_search"></i>
        </div>
        <div class="header-configure-area">
            <div class="language-option">
                <img src="<%=request.getContextPath()%>/img/flag.jpeg" alt="" />
                <span>TW <i class="fa fa-angle-down"></i></span>
            </div>
            <a href="#" class="bk-btn">立刻訂房</a>
        </div>
        <nav class="mainmenu mobile-menu">
            <ul>
                <li>
                    <a href="./pages.html" class="nav-event">會員中心</a>
                    <ul class="dropdown">
                        <li><a href="./room-details.html">個人檔案</a></li>
                        <li><a href="./blog-details.html">我的假期</a></li>
                        <li><a href="#">我的足跡</a></li>
                    </ul>
                </li>
                <li>
                    <a class="nav-event">住客專區</a>
                    <ul class="dropdown">
                        <li><a href="./activity.html">活動報名</a></li>
                        <li><a href="">預約服務</a></li>
                        <li><a href="">訂購餐點</a></li>
                    </ul>
                </li>
                <li>
                    <a href="shop.html" class="nav-evnet">戴蒙商城</a>
                </li>
                <li>
                    <a class="nav-event" href="./rooms.html">渡假空間</a>
                    <ul class="dropdown">
                        <li><a href="#">戴蒙經典房</a></li>
                        <li><a href="#">豪華蜜月房</a></li>
                        <li><a href="#">奢華海景房</a></li>
                        <li><a href="#">波賽頓套房</a></li>
                        <li><a href="#">公共空間</a></li>
                    </ul>
                </li>
                <li>
                    <a class="nav-event" href="./pages.html">精彩活動</a>
                    <ul class="dropdown">
                        <li><a href="./room-details.html">陸上活動</a></li>
                        <li><a href="./room-details.html">海上活動</a></li>
                        <li><a href="./room-details.html">網紅行程</a></li>
                    </ul>
                </li>
                <li>
                    <a href="" class="nav-event">會員登入</a>
                </li>
                <li>
                    <a href="" class="nav-event" style="color: rgb(240, 218, 162)">加入會員</a>
                </li>
            </ul>
        </nav>
        <div id="mobile-menu-wrap"></div>
        <div class="top-social">
            <a href="#"><i class="fa fa-facebook"></i></a>
            <a href="#"><i class="fa fa-twitter"></i></a>
            <a href="#"><i class="fa fa-tripadvisor"></i></a>
            <a href="#"><i class="fa fa-instagram"></i></a>
        </div>
        <ul class="top-widget">
            <li><i class="fa fa-phone"></i> (12) 345 67890</li>
            <li><i class="fa fa-envelope"></i> info.colorlib@gmail.com</li>
        </ul>
    </div>
    <!-- Offcanvas Menu Section End -->
    <!-- Header Section Begin -->
    <header class="header-section">
        <div class="menu-item">
            <div class="container">
                <div class="row">
                    <div class="col-lg-3">
                        <div class="logo">
                            <a href="./index.html">
                                <img src="<%=request.getContextPath()%>/img/logo.png" alt="" />
                            </a>
                        </div>
                    </div>
                    <div class="col-lg-9">
                        <div class="nav-menu">
                            <nav class="mainmenu">
                                <ul>
                                    <li class="active"><a href="index.html">回首頁</a></li>
                                    <li>
                                        <a href="./pages.html" class="nav-event">陸上活動</a>
                                        <ul class="dropdown">
                                            <li><a href="./room-details.html">馳騁沙灣</a></li>
                                            <li><a href="./blog-details.html">百步穿楊</a></li>
                                            <li><a href="#">行燈夜宴</a></li>
                                        </ul>
                                    </li>
                                    <li>
                                        <a class="nav-event">海洋活動</a>
                                        <ul class="dropdown">
                                            <li><a href="./activity.html">水世界</a></li>
                                            <li><a href="">暢游大海</a></li>
                                            <li><a href="">與鯨共舞</a></li>
                                        </ul>
                                    </li>
                                    <li>
                                        <a href="shop.html" class="nav-evnet">特別行程</a>
                                    </li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </header>
    <!-- Header Section End -->
    <div id="slick">
      <c:forEach var="actPicVO" items="${list}">
        <div >
             <img src="<%=request.getContextPath()%>/ActPicReaderServlet?actPicNo=${actPicVO.actPicNo}&action=getOnePic"> 
        </div>
        </c:forEach>
    </div>
    <div class="box">
        <div id="card-container">
          <div id="card">
          <c:forEach var="actPicVO" items="${list}">
            <div class="front face">
              <img src="<%=request.getContextPath()%>/ActPicReaderServlet?actPicNo=00001&action=getOnePic">
            </div>
            </c:forEach>
            <div class="back face">
              <h1>Bouquet</h1>
              <p class="artist">The Chainsmokers</p>
              <p class="date">2015</p>
              <span class="icon-share">
                <a href="#" style="color:rgb(0, 0, 0)"><i class="fas fa-share-square"></i></a>
              </span>
              <div class="back-face-one">
                <p>Lorem ipsum dolor sit, a
                    met consectetur adipisicing elit. 
                    Accusamus libero eveniet, quisquam maxime ipsam rerum sequi nulla similique. Amet ut perspiciatis veniam cupiditate, officia excepturi optio eos sequi saepe possimus!</p>
                <p></p>
              </div>
            </div>
          </div>
        
        </div>
        <div id="card-container">
          <div id="card2">
            <c:forEach var="actPicVO" items="${list}">
            <div class="front face">
              <img src="<%=request.getContextPath()%>/ActPicReaderServlet?actPicNo=00002&action=getOnePic">
            </div>
            </c:forEach>
            <div class="back face">
              <h1>Roses</h1>
              <p class="artist">The Chainsmokers</p>
              <p class="date">2017</p>
              <span class="icon-share">
                <a href="#" style="color:rgb(0, 0, 0)"><i class="fas fa-share-square"></i></a>
              </span>
              <div class="back-face-one">
                <p>Lorem ipsum dolor sit, a
                    met consectetur adipisicing elit. 
                    Accusamus libero eveniet, quisquam maxime ipsam rerum sequi nulla similique. Amet ut perspiciatis veniam cupiditate, officia excepturi optio eos sequi saepe possimus!</p>
                <p></p>
              </div>
            </div>
          </div>
          
        </div>
        <div id="card-container">
          <div id="card3">
           <c:forEach var="actPicVO" items="${list}">
            <div class="front face">
              <img src="<%=request.getContextPath()%>/ActPicReaderServlet?actPicNo=00003&action=getOnePic">
            </div>
            </c:forEach>
            <div class="back face">
              <h1>Waterbed</h1>
              <p class="artist">The Chainsmokers</p>
              <p class="date">2015</p>
              <span class="icon-share">
                <a href="#" style="color:rgb(0, 0, 0)"><i class="fas fa-share-square"></i></a>
              </span>
              <div class="back-face-one">
                <p>Lorem ipsum dolor sit, a
                    met consectetur adipisicing elit. 
                    Accusamus libero eveniet, quisquam maxime ipsam rerum sequi nulla similique. Amet ut perspiciatis veniam cupiditate, officia excepturi optio eos sequi saepe possimus!</p>
                <p></p>
              </div>
            </div>
          </div>
          
        </div>
      </div>
        <div id="contentWalll">
            <div id="pic1">
                <p id="p1">
                    <span class="icon-start">
                        <a href="#" style="color:rgb(0, 0, 0)"><i class="far fa-star"></i></a>
                    </span>
                    Hello World
                    <span class="icon-thumb">
                        <a href="#" style="color:rgb(0, 0, 0)"><i class="fas fa-thumbs-up"></i></a>
                    </span>
                </p> 
            </div>
        </div>
        <div id="contentWall2">
            <div id="pic2">
                <p id="p2">
                    <span class="icon-start">
                        <a href="#" style="color:rgb(0, 0, 0)"><i class="far fa-star"></i></a>
                    </span>
                    Hello World
                    <span  class="icon-thumb">
                        <a href="#" style="color:rgb(0, 0, 0)"><i class="fas fa-thumbs-up"></i></a>
                    </span>
                </p> 
            </div>
        </div>
        <div id="contentWall-3">
            <div id="pic3"> 
                <p id="p3">
                    <span class="icon-start">
                        <a href="#" style="color:rgb(0, 0, 0)"><i class="far fa-star"></i></a>
                    </span>
                    Hello World
                    <span  class="icon-thumb">
                        <a href="#" style="color:rgb(0, 0, 0)"><i class="fas fa-thumbs-up"></i></a>
                    </span>
                </p> 
            </div>
        </div>
    
      <!--container-->
    

  

    <!-- Footer Section End -->
    <!-- Js Plugins -->
    <script src="<%=request.getContextPath()%>/js/frontend_js/jquery-3.5.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/frontend_js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/frontend_js/jquery.nice-select.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/frontend_js/jquery-ui.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/frontend_js/jquery.slicknav.js"></script>
    <script src="<%=request.getContextPath()%>/js/frontend_js/owl.carousel.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/frontend_js/template.js"></script>
    <script src="<%=request.getContextPath()%>/js/frontend_js/sweetalert.js"></script>
    <script src="<%=request.getContextPath()%>/js/frontend_js/slick.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/frontend_js/land.js"></script>
</body>

</html>