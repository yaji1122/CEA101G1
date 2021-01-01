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
 <link href="https://fonts.googleapis.com/css?family=Lora:400,700&display=swap" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css?family=Cabin:400,500,600,700&display=swap" rel="stylesheet" />
    <!-- Css Styles -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css" type="text/css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/font-awesome.min.css" type="text/css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/elegant-icons.css" type="text/css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/flaticon.css" type="text/css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/owl.carousel.min.css" type="text/css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/nice-select.css" type="text/css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/front/template.css" type="text/css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/front/landList-2.css" type="text/css" />
    
<body>
<body>
        <!-- Page Preloder -->

      

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
                                            <ul class="dropdown">
                                                <li><a href="./activity.html">蠻荒BBQ</a></li>
                                                <li><a href="">狩獵侏儸紀</a></li>
                                                <li><a href="">深海巡禮</a></li>
                                            </ul>
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
        
        <div id="wrapper">
            
           <div id="reg">
             <ul id="dateSle">
                 <li class="startDay">
                    <label id="start-day-label"> 
                        
                    </label>
                 </li>
                 <li>
                    <label id="start-day-label"> 
                    
                    </label>
                 </li>
                 <li>
                    <label id="start-day-label"> 
                                                          已預約
                    </label>
                    <span>
                        <a href="#"></a><i class="fas fa-bell fa-2x"></i></a>
                    </span>
                 </li>
                 <li>
                    <label id="start-day-label"> 
                                                        我的活動
                    </label>
                    <span>
                        <a href="#"><i class="fas fa-cart-arrow-down fa-2x"></i></a>
                    </span>
                </li>
             </ul>
             <!-- 第一列 -->
             <div class="content-1">
                <div class="line"></div>
                 <div class="content-img">
                         <img src="<%=request.getContextPath()%>/ActPicReaderServlet?actPicNo=00002&action=getOnePic">
                 </div>
                 <div class="text">
                     <div class="content-text">
                         <div class="top-row">
                            <h6>百步穿楊</h6>
                            <span>Lorem ipsum dolor sit amet 
                            consectetur adipisicing elit.
                             Necessitatibus corrupti cum 
                             ducimus hic blanditiis explicabo 
                             ea minima, consectetur ipsum quod 
                             officia molestiae illum dignissimos 
                             labore sed fuga odio possimus optio?</span>
                         </div>
                         <div class="line" style="border-bottom:outset 1px;"></div>
                         <div class="bottom-row">
                            <span><i class="fab fa-twitter"></i></span>
                            <span><i class="fab fa-wikipedia-w"></i></span>
                            <span><i class="fas fa-briefcase-medical"></i></span>
                            <span><i class="fab fa-apple-pay fa-2x"></i></span>
                         </div>
                     </div>
                 </div>
                 <div class="price">
                      <h2>5,000</h2>
                      <p>USD</p>
                 </div>
                 <div id="participant">
                    <select style="padding: 5px;">
                        <option value="1">1人</option>
                        <option value="2">2人</option>
                        <option value="3">3人</option>
                        <option value="4">4人</option>
                        <option value="5">5人</option>
                    </select>
                 </div>
                 <button type="button" class="btn btn-outline-primary">
                                                 我要預約
                </button>
             </div>
           </div>
           
    <script src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/jquery.nice-select.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/jquery-ui.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/jquery.slicknav.js"></script>
    <script src="<%=request.getContextPath()%>/js/owl.carousel.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/template.js"></script>
    <script src="<%=request.getContextPath()%>/js/sweetalert.js"></script>

</body>
</html>