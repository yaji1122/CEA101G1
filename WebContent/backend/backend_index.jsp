<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.roomtype.model.*"%>
<%@ page import="com.roomrsv.model.*"%>
<%@ page import="java.util.List" %>
<%@ page import="com.bookingorder.model.*"%>
<%@ page import="com.rooms.model.*"%>
<%@ page import="java.time.LocalDate"%>
<jsp:useBean id="rmtypeSvc" scope="page" class="com.roomtype.model.RoomTypeService" />
<% 
	String authErrorMsg = (String) session.getAttribute("authErrorMsg");
	if (authErrorMsg != null){
		pageContext.setAttribute("msg", authErrorMsg);
		session.removeAttribute("authErrorMsg");
	}
	RoomRsvService rsvService = new RoomRsvService();
	List<RoomRsvVO> rsvList = rsvService.getAll();
	pageContext.setAttribute("rsvList", rsvList);
	
	BookingOrderService bkodSvc = new BookingOrderService();
	LocalDate today = LocalDate.now();
	List<BookingOrderVO> checkIns = bkodSvc.getAllBeforeToday(today);
	List<BookingOrderVO> checkOuts = bkodSvc.getAllByDateOut(today); //取得當天尚未CheckOut的訂單
	List<BookingOrderVO> checkeds = bkodSvc.getAllByBkStatus(BKSTATUS.CHECKED);
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0" />
	<meta http-equiv="X-UA-Compatible" content="ie=edge" />
	<link rel="icon" type="image/png" href="<%=request.getContextPath()%>/img/loading.png" />
	
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery.datetimepicker.min.css" />
    <link rel="stylesheet"
		  href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
		  integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
		  crossorigin="anonymous" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/back/backend_sidebar.css" />
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" />
<title>戴蒙後台作業系統</title>
</head>
<style>
.arrow {
    position: absolute;
    color: #bbbfca;
    font-size: 25px;
    padding: 0px 10px;
    z-index: 10;
}
.aval-title {
    width: 90%;
    margin: 0 auto;
}
.calendar-forward {
    right: 5%;
    top: 0;
    cursor: pointer;
}
.calendar-backward {
    right: 10%;
    top: 0;
    cursor: pointer;
}
.arrow:hover {
    color: #495464;
}
.view {
	position:relative;
    width: 900px;
    overflow: hidden;
    margin:0 auto;
}
#display {
    display: flex;
    flex-direction: row;
    width: fit-content;
    transition: 0.3s ease-in-out;
    margin: 0 auto;
    padding-top:60px;
}
.calendar-wrapper {
    min-width: 900px;
    opacity: 0;
    transition: 0.3s ease-in-out;
}
.title {
    display: flex;
    flex-direction: row;
    height: min-content;
    text-align: left;
    letter-spacing: 5px;
    font-size: 20px;
    width: fit-content;
}
.title b {
    height: fit-content;
}
.title p {
    margin: 0px;
}
.calendar-td {
    padding: 1px 1px;
}
.week-title th {
    text-align: center;
    padding: 10px 0px;
    font-size: 18px;
    font-weight: 700;
}
a.calendar-box {
    position: relative;
    display: block;
    background-color: #b9b9b9;
    height: 75px;
    width: 125px;
    margin: 0 auto;
}
a.calendar-default {
    display: flexbox;
    border: 1.5px solid #e8e8e8;
    background-color: white;
    color:grey;
}
a.calendar-box:hover {
    color: rgb(255, 109, 109);
    border-color: black;
    text-decoration: none;
}
a div.calendar-date {
	width:fit-content;
    position: absolute;
    right: 5px;
    top: 0px;
    font-size: 12px;
    text-align: right;
    align-self: flex-end;
}
a.calendar-isEmpty .calendar-price {
    position: absolute;
    bottom: 2px;
    right: 2px;
    font-size: 12px;
    font-weight: 500;
}
a.calendar-today .calendar-date {
    color: rgb(228, 104, 104);
}
.logo img {
    width: 150px;
}
.logo {
    text-align: center;
}

</style>
<body>
<jsp:useBean id="bkdetailSvc" scope="page"
	class="com.bookingdetail.model.BookingDetailService" />
	<%@ include file="/backend/files/backend_sidebar.file"%>
	<div class="header">
			<div>
				<h3><%=LocalDate.now()%></h3>
			</div>
			<div>
				<h4>今日待入住訂單</h4>
				<h3><%=checkIns.size()%> 筆</h3>
			</div>
			<div>
				<h4>今日待退房訂單</h4>
				<h3><%=checkOuts.size()%> 筆</h3>
			</div>
			
			<% 
				List<BookingOrderVO> list = bkodSvc.getAllByBkStatus(BKSTATUS.CHECKED);
				int totalGuest = 0;
				for (BookingOrderVO bkodvo: list){
					int i = bkdetailSvc.getAllByBkNo(bkodvo.getBk_no()).stream()
							.mapToInt(e -> e.getRm_guest())
							.sum();
					totalGuest += i;
				}
			%>
			<div>
				<h4>當前度假村人數</h4>
				<h3><%=totalGuest%> 人</h3>
			</div>
	</div>
	<div class="view">
            <div id="display"></div>
	        <div class="calendar-backward arrow">
	            <i class="fas fa-chevron-left"></i>
	        </div>
	        <div class="calendar-forward arrow">
	            <i class="fas fa-chevron-right"></i>
	        </div>
    </div>



	<%@ include file="/backend/files/backend_footer.file"%> <!-- 加入常用 js -->
	<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>  
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
	<script>
	$(document).ready(function () {
        let display = document.getElementById("display");
        let weeks = ["ㄧ", "二", "三", "四", "五", "六", "日"];
        let today = new Date();
        let thisYear = today.getFullYear();
        let thisMonth = today.getMonth();
        let todayDate = today.getDate();
        let todayStr = thisYear + "-" + (thisMonth+1) + "-" + todayDate
        console.log(todayStr);
        let current = 0;
        var loaded = [0, 1]
        getCalendars(12); //拿一年份的月曆！
        fetchAvalibility(current);
        fetchAvalibility(current+1);
        
        function createCalendar(year, month) {
            let feb = leapYear(year);
            let monthOfDay = [31, feb, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
            let wrapper = document.createElement("div"); //包住個別日曆
            wrapper.classList.add("calendar-wrapper");
            let title = document.createElement("div"); //產生日曆標頭
            title.classList.add("title");
            title.innerHTML = "<b>" + (month + 1) + "月</b><p>" + "," + year + "年</p>";
            let table = document.createElement("table"); //產生日曆表格
            table.classList.add("calendar");
            let firstTr = document.createElement("tr"); //產生標頭列
            firstTr.classList.add("week-title");

            table.append(firstTr);
            wrapper.append(title);
            wrapper.append(table);
            //建立抬頭
            for (let i = 0; i < 7; i++) {
                let th = document.createElement("th");
                th.innerText = weeks[i];
                firstTr.append(th);
            }
            //找出該月第一天是禮拜幾
            let firstDayOfWeek = new Date(year, month, 1).getDay();
            if (firstDayOfWeek == 0) firstDayOfWeek = 7;
            //確認月曆行數
            let rows = 6;
            //產生月曆行數
            for (let i = 0; i < rows; i++) {
                let tr = document.createElement("tr");
                for (let j = 1; j <= 7; j++) {
                    let td = document.createElement("td");
                    let a = document.createElement("a");
                    let img = document.createElement("img");
                    img.setAttribute("src", "<%=request.getContextPath()%>/css/ajax-loader.gif");
                    img.setAttribute("style", "display:none; width:100%")
                    td.classList.add("calendar-td");
                    a.classList.add("calendar-box");
                    let id =
                        year.toString() + "-"
                        + (month + 1).toString().padStart(2, "0") + "-"
                        + (i * 7 + j - firstDayOfWeek + 1).toString().padStart(2, "0");
                    if (i === 0 && j >= firstDayOfWeek) {
                        a.setAttribute("data-year", year);
                        a.setAttribute("data-month", month + 1);
                        a.setAttribute("data-date", j + i * 7 - firstDayOfWeek + 1);
                        a.setAttribute("id", id);
                    } else if (i * 7 + j - firstDayOfWeek + 1 <= monthOfDay[month]) {
                        a.setAttribute("data-year", year);
                        a.setAttribute("data-month", month + 1);
                        a.setAttribute("data-date", j + i * 7 - firstDayOfWeek + 1);
                        a.setAttribute("id", id);
                    }
                    a.append(img);
                    td.append(a);
                    tr.append(td);
                }
                table.append(tr);
            }
            return wrapper;
        }

        function leapYear(year) {
            let feb = (year % 4 == 0 && year % 100 != 0) || year % 400 == 0 ? 29 : 28;
            return feb;
        }
        //填充日期資訊
        function fillUpDates(year, month, thisMonthDate) {
            let feb = leapYear(year);
            let monthOfDay = [31, feb, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
            for (let i = thisMonthDate; i <= monthOfDay[month]; i++) {
                let celldate = document.createElement("div");
                let cellprice = document.createElement("div");
                cellprice.classList.add("calendar-price");
                celldate.classList.add("calendar-date");
                let id = year.toString() + "-" + (month + 1).toString().padStart(2, "0") + "-" + i.toString().padStart(2, "0");
                let box = document.getElementById(id);
                box.classList.add("calendar-default");
                celldate.innerText = i;
                box.append(celldate);
                box.append(cellprice);
            }
        }
        
        function getCalendars(number) {
            for (i = 0; i < number; i++) {
                let thisMonthDate = 1;
                let year = thisYear + Math.floor(thisMonth / 12);
                let month = thisMonth % 12;
                let calendar = createCalendar(year, month);
                display.append(calendar);
                if (i == 0){
                    thisMonthDate = todayDate;
                } 
                fillUpDates(year, month, thisMonthDate);
                thisMonth++;
            }
            thisMonth = today.getMonth();
            let todaybox = document.getElementById(
                       thisYear.toString() + "-" 
                    + (thisMonth + 1).toString().padStart(2, "0") + "-"
                    + today.getDate().toString().padStart(2, "0")
            );
            todaybox.classList.add("calendar-today");

            let position = 0;
            let forward = $(".calendar-forward");
            let backward = $(".calendar-backward");
            let calendars = $(".calendar-wrapper");
            calendars.eq(0).css("opacity", "1");
            calendars.eq(1).css("opacity", "1");
            backward.fadeOut();
            //導覽
            forward.click(function () {
                current += 1;
                let calendarWidth = parseInt($(".calendar-wrapper").css("width").split("px")[0]);
                calendars.css("opacity", "0");
                if (0 < number - current) {
                    backward.fadeIn(0);
                    $("#display").css("transform", "translateX(-" + (position += calendarWidth) + "px)");
                }
                if (1 === number - current) {
                    forward.fadeOut(0);
                }
                calendars.eq(current).css("opacity", "1");
                calendars.eq(current + 1).css("opacity", "1");
                if (loaded.indexOf(current+1) < 0){
                	fetchAvalibility(current + 1);
                    loaded.push(current+1);
                }
            });
            backward.click(function () {
                current -= 1;
                let calendarWidth = parseInt($(".calendar-wrapper").css("width").split("px")[0]);
                calendars.css("opacity", "0");
                if (number - current > 0) {
                    forward.fadeIn(0);
                    $("#display").css("transform", "translateX(-" + (position -= calendarWidth) + "px)");
                }
                if (number - current === 12) {
                    backward.fadeOut(0);
                }
                calendars.eq(current).css("opacity", "1");
                calendars.eq(current + 1).css("opacity", "1");
                if (loaded.indexOf(current) < 0){
                	fetchAvalibility(current);
                    loaded.push(current+1);
                }
            });
            $(window).resize(function () {
                let CalendarWidth = parseInt($(".calendar-wrapper").css("width").split("px")[0]);
                let reposition = CalendarWidth * current;
                position = reposition;
                $("#display").css("transform", "translateX(-" + reposition + "px)");
            });
        }
        var rm_price = {
        		<c:forEach var="rmtypevo" items="${rmtypeSvc.getAll()}">
        			${rmtypevo.rm_type}:${rmtypevo.rm_price},
        		</c:forEach>
        }
        function fetchAvalibility(currentCal){
            let allDays = $(".calendar-wrapper").eq(currentCal).find(".calendar-default");
            
            for (let i = 0 ; i < allDays.length; i++){
            	let currentDate = allDays.eq(i);
               <%--  $.ajax({
                     url: "<%=request.getContextPath()%>/booking/Available",
                     data:{
                         date: currentDate.attr("id"),
                         stay: stayDays,
                         rmtype: roomType,
                         guest: guests,
                         action:"roomCheck"
                     },
                     type: 'POST',
                     beforeSend: function() {
                    	 currentDate.children("img").show();
                      },
                     success: function(str){
                        var data = JSON.parse(str)
    					if(data.isFull == "true"){
    						currentDate.addClass("calendar-isFull");
    						/* currentDate.attr("href",""); */
    					} else {
    						for (let i = 1; i < 10; i++){
    							if (data[i] != null){
    								currentDate.children(".calendar-price").text("$" + rm_price[i] + "-")
    								break;
    							}
    						}
    						currentDate.addClass("calendar-isEmpty");
    						let length = currentDate.attr("href").length;
    						let href = currentDate.attr("href").split("stay")[0] 
    									+ "stay=" + $("#stay").val() 
    									+ "&guest=" + $("#guest").val();
    						
    						
    						
    						currentDate.attr("href", href);
    					}
                        currentDate.children("img").hide();
                     }
                }) --%>
            } 
        }
        //WS
    	var MyPoint = "/NotifyWS";
    	var host = window.location.host;
    	var path = window.location.pathname;
    	var webCtx = path.substring(0, path.indexOf('/', 1));
    	var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
    	var webSocket = new WebSocket(endPointURL);
    	webSocket.onmessage = function(event) {
    		var jsonObj = JSON.parse(event.data);
    		let type = jsonObj.type;
    		let odno = jsonObj.odno;
    		Command: toastr["info"]( type + "編號為：" + odno, "有一筆新的訂單")
    	};
    	
    	<c:if test="${msg != null}">
    		Command: toastr["warning"]("請向主管申請開通權限", " 權限不足")
    	</c:if>
    	toastr.options = {
    			  "closeButton": true,
    			  "debug": false,
    			  "newestOnTop": true,
    			  "progressBar": false,
    			  "positionClass": "toast-top-right",
    			  "preventDuplicates": false,
    			  "onclick": null,
    			  "showDuration": "300",
    			  "hideDuration": "1000",
    			  "timeOut": "10000",
    			  "showEasing": "swing",
    			  "hideEasing": "linear",
    			  "showMethod": "fadeIn",
    			  "hideMethod": "fadeOut"
    			}
    });
	</script>
</body>
</html>