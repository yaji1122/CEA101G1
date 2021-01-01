<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css%>" type="text/css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/font-awesome.min.css" type="text/css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/elegant-icons.css" type="text/css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/flaticon.css" type="text/css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/owl.carousel.min.css" type="text/css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/nice-select.css" type="text/css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui.min.css" type="text/css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/front/template.css" type="text/css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/front/Activity.css" type="text/css" />
<title>Insert title here</title>
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
                        <li><a href="#">我的足跡</a></li>
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
            <div>
            </div>
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
                                        <a href="" class="nav-event">陸上活動</a>
                                        <ul class="dropdown">
                                            <li><a href="">馳騁沙灣</a></li>
                                            <li><a href="">百步穿楊</a></li>
                                            <li><a href="">行燈夜宴</a></li>
                                        </ul>
                                    </li>
                                    <li>
                                        <a class="nav-event">海洋活動</a>
                                        <ul class="dropdown">
                                            <li><a href="">水世界</a></li>
                                            <li><a href="">暢游大海</a></li>
                                            <li><a href="">與鯨共舞</a></li>
                                        </ul>
                                    </li>
                                    <li>
                                        <a href="shop.html" class="nav-evnet">特別行程</a>
                                        <ul class="dropdown">
                                            <li><a href="">蠻荒BBQ</a></li>
                                            <li><a href="">深海巡禮</a></li>
                                            <li><a href="">狩獵侏儸紀</a></li>
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
    <div id="wrapper" >
        <div class="land">
            <img src="<%=request.getContextPath()%>/img/sheet4.png">
            <figure>
                <figcaption><a href="<%=request.getContextPath()%>/front-end/Land_page.jsp" >Land</a></figcaption>
            </figure>
        </div>
        <div class="sea">
            <img src="<%=request.getContextPath()%>/img/land1.png">
            <figure>
                <figcaption><a href="Ocean.html">Ocean</a></figcaption>
            </figure>
        </div>
        <div class="spc">
            <img src="<%=request.getContextPath()%>/img/diving4.png">
            <figure>
                <figcaption><a href="Ocean.html">Especial</a></figcaption>
            </figure>
        </div>
    </div>
    <!-- Footer Section Start -->
    
    <!-- Footer Section End -->
    <!-- Js Plugins -->
    <script src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/jquery.nice-select.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/jquery-ui.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/jquery.slicknav.js"></script>
    <script src="<%=request.getContextPath()%>/js/owl.carousel.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/front/template.js"></script>
    
</body>

</html>