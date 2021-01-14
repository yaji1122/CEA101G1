<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.item.model.*"%>
<%@ page import="com.item_type.model.*"%>
<%@ page import="com.item_pics.model.*"%>
<%@ page import="com.members.model.*"%>
<%@ page import="com.shoppingCart.model.*"%>
<jsp:useBean id="itemSvc" scope="page" class="com.item.model.ItemService" />
<jsp:useBean id="item_typeSvc" scope="page" class="com.item_type.model.Item_typeService" />
<jsp:useBean id="cartSvc" scope="page" class="com.shoppingCart.model.CartService" />
<jsp:useBean id="item_picsSvc" scope="page" class="com.item_pics.model.Item_picsService" />
<%
	String item_type_no = request.getParameter("item_type_no");
	List<ItemVO> list = null;
	if (item_type_no == null) {
		list = itemSvc.getAllItemBySt();
	} else {
		list = itemSvc.getAllByItem_type_noBySt(item_type_no);
	}
	pageContext.setAttribute("list", list);
	pageContext.setAttribute("item_type_no", item_type_no);
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

<%@ include file="/frontend/files/commonCSS.file" %>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/slick.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/slick-theme.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/front/shoppage.css"
	type="text/css" />

</head>
<%@ include file="/frontend/files/loginCSS.file" %>
<body>
	<%@ include file="/frontend/files/login.file" %>
	<%@ include file="/frontend/files/loginbox.file" %>
	
	<%	
	String mb_id = null;
	if(member!=null){
		mb_id = member.getMb_id();
		System.out.println("mb_id = "+mb_id);		
	} else {
		session.setAttribute("sessionID", sessionID);
	}
	%>
	
	<div class="back"></div>
	<!-- Page Preloder -->
	<div id="preloder">
		<img id="preloaderpic"
			src="${pageContext.request.contextPath}/img/loading.png" />
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
				<img src="img/flag.jpeg" alt="" /> <span>TW <i
					class="fa fa-angle-down"></i></span>
			</div>
			<a href="#" class="bk-btn">立刻訂房</a>
		</div>
		<nav class="mainmenu mobile-menu">
			<ul>
				<li><a href="./pages.html" class="nav-event">會員中心</a>
					<ul class="dropdown">
						<li><a href="./room-details.html">個人檔案</a></li>
						<li><a href="./blog-details.html">我的假期</a></li>
					</ul></li>
				<li><a class="nav-event">住客專區</a>
					<ul class="dropdown">
						<li><a href="./activity.html">活動報名</a></li>
						<li><a href="">預約服務</a></li>
						<li><a href="">訂購餐點</a></li>
					</ul></li>
				<li><a href="shop.html" class="nav-evnet">戴蒙商城</a></li>
				<li><a class="nav-event" href="./rooms.html">渡假空間</a>
					<ul class="dropdown">
						<li><a href="#">戴蒙經典房</a></li>
						<li><a href="#">豪華蜜月房</a></li>
						<li><a href="#">奢華海景房</a></li>
						<li><a href="#">波賽頓套房</a></li>
						<li><a href="#">公共空間</a></li>
					</ul></li>
				<li><a class="nav-event" href="./pages.html">精彩活動</a>
					<ul class="dropdown">
						<li><a href="./room-details.html">陸上活動</a></li>
						<li><a href="./room-details.html">海上活動</a></li>
						<li><a href="./room-details.html">網紅行程</a></li>
					</ul></li>
				<li><a href="" class="nav-event">會員登入</a></li>
				<li><a href="" class="nav-event"
					style="color: rgb(240, 218, 162)">加入會員</a></li>
			</ul>
		</nav>
		<div id="mobile-menu-wrap"></div>
		<div class="top-social">
			<a href="#"><i class="fa fa-facebook"></i></a> <a href="#"><i
				class="fa fa-twitter"></i></a> <a href="#"><i
				class="fa fa-tripadvisor"></i></a> <a href="#"><i
				class="fa fa-instagram"></i></a>
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
			<div class="nav-menu">
				<nav class="mainmenu">
					<ul class="mainmenu-row">
						<div class="logobox">
							<a href="${pageContext.request.contextPath}/frontend/index.jsp"><img
								src="${pageContext.request.contextPath}/img/logo.png"/>
							</a>
						</div>
						<li  class="nav-list"><a class="nav-event" href="<%=request.getContextPath()%>/frontend/shop/shopPage.jsp">HOME</a></li>
						
						<li class="nav-list">
						<a class="nav-event">DIAMOND CLASSIC</a>
										<ul class="dropdown">
											<c:forEach var="item_typeVO"
												items="${item_typeSvc.allItem_type}" begin="0" end="2">
												<li value="${item_typeVO.item_type_no}" class="chtype"><a
													href="<%=request.getContextPath()%>/frontend/shop/shopPage.jsp?item_type_no=${item_typeVO.item_type_no}">${item_typeVO.type_name}</a></li>
											</c:forEach>
										</ul>
						</li>
						<li class="nav-list"><a class="nav-event">GIFTS & SOUVENIR</a>
										<ul class="dropdown">
											<c:forEach var="item_typeVO"
												items="${item_typeSvc.allItem_type}" begin="3" end="5">
												<li value="${item_typeVO.item_type_no}" class="chtype"><a
													href="<%=request.getContextPath()%>/frontend/shop/shopPage.jsp?item_type_no=${item_typeVO.item_type_no}">${item_typeVO.type_name}</a></li>
											</c:forEach>
										</ul></li>
						
						<li class="nav-list"><a class="nav-evnet">SEASONAL GOODS</a>
										<ul class="dropdown">
											<c:forEach var="item_typeVO"
												items="${item_typeSvc.allItem_type}" begin="6">
												<li value="${item_typeVO.item_type_no}" class="chtype"><a
													href="<%=request.getContextPath()%>/frontend/shop/shopPage.jsp?item_type_no=${item_typeVO.item_type_no}">${item_typeVO.type_name}</a></li>
											</c:forEach>
										</ul></li>
						
						<li class="nav-list"><a class="nav-event" href="<%=request.getContextPath()%>/frontend/shop/shopCartRedis.jsp"><i class="fas fa-shopping-cart icon" ></i></a></li>			
						<li class="nav-list"><a class="nav-event"> <c:choose>
									<c:when test="${member != null}"><i class="far fa-gem"></i></i>會員中心</a>
							<ul class="dropdown">
								<li><a href="${pageContext.request.contextPath}/frontend/members/memberInfo.jsp">個人檔案</a></li>
								<li><a href="${pageContext.request.contextPath}/frontend/members/memberBooking.jsp">我的假期</a></li>
								<li><a href="${pageContext.request.contextPath}/frontend/shop/shopAllOrder.jsp">購物訂單</a></li>
								<li><a
									href="${pageContext.request.contextPath}/LoginHandler?mb_email=${member.mb_email}&action=member-logout&location=${pageContext.request.requestURL}">登出</a></li>
							</ul></li>
						</c:when>
						<c:otherwise>
							<i class="fas fa-user-circle log-in"></i>
							</a>
						</c:otherwise>
						</c:choose>
						</li>
					</ul>
				</nav>
			</div>
		</div>
	</header>

	<!-- Header Section End -->
	<div class="wrapper">
		<div class="pageheader">


			
	<div class="shopCartBorder">
				
					<h3 class="shopCartTitle">ショッピングカート</h3>
				
				<form name="selectedForm"  method="POST">		
					
					<table class="cartList">
					<% double total = 0;%>
					<% 
						List<ItemVO> RedisBuylist = (List<ItemVO>) cartSvc.getAllItem_noByMb_id(mb_id);
						List<ItemVO> buylist = (List<ItemVO>) cartSvc.getAllItem_noBysessionID(sessionID);
					%>
			<%if(member!=null){ %>
					<%  if(RedisBuylist != null && (RedisBuylist.size() > 0)) {%>
					<%  for (int index = 0; index < RedisBuylist.size(); index++){
						ItemVO order = RedisBuylist.get(index);
						total += (cartSvc.getValueByItem_no(mb_id, order.getItem_no()))*(itemSvc.getOneItem(order.getItem_no()).getItem_price());
					%>
						<tr class="itBorTop">
							<td>
								<label class="checklabel">
									<input id="boxselect<%= index %>" type="checkbox" name="checkact" class="ceIt" value="<%= index %>">
									<input id="checknum<%= index %>" type="hidden" name="checkedItem" value="0">
									<input id="forPlusnum<%= index %>" type="hidden" value="<%= (cartSvc.getValueByItem_no(mb_id, order.getItem_no()))*(itemSvc.getOneItem(order.getItem_no()).getItem_price())%>">
								</label>
							</td>
							<td class="imgframe"><img id="imgCheck_<%=index %>" src="<%=request.getContextPath()%>/item_pics/item_pics.do?item_pic_no=<%=item_picsSvc.getAllPics(order.getItem_no()).get(0).getItem_pic_no()%>&action=getOne_Pic_Display"></td>
						<td>
								<div class="cartborderFi">
									<div class="cartItemName"><%=itemSvc.getOneItem(order.getItem_no()).getItem_name()%></div>
									<div class="number-input">
                                           <!--數量選單 -->
                                         <div class="qIn">																							
                                         	<span id="btn<%= index + "_1" %>" class="minusIn" onclick="this.parentNode.querySelector('input[type=number]').stepDown()" ><i class="fas fa-minus"></i></span>
											<input id="qty<%= index %>" class="quantity" min="0" name="quantity" value="<%=cartSvc.getValueByItem_no(mb_id, order.getItem_no())%>" type="number">						
											<span id="btn<%= index %>" class="plusIn" onclick="this.parentNode.querySelector('input[type=number]').stepUp()"><i class="fas fa-plus"></i></span>
										</div>  
									    <input type="hidden" name="action"  value="deleteSelected"> 
										<input type="hidden" name="item_no"  value="<%=order.getItem_no()%>"> 
										<input type="hidden" name="item_price"  value="<%=itemSvc.getOneItem(order.getItem_no()).getItem_price()%>"> 
										<input type="hidden" name="quantity" value="1" id="qty<%= index + "_1"%>">
										<input type="hidden" name="points" value="<%=itemSvc.getOneItem(order.getItem_no()).getPoints()%>" >
										<input type="button" value="刪 除" class="deOnIt" 
											onclick="location.href='<%=request.getContextPath()%>/shop/shoppingRedisCart.do?action=DELETE&del=<%=index%>&item_no=<%=order.getItem_no()%>&quantity=<%=cartSvc.getValueByItem_no(mb_id, order.getItem_no())%>'" >									
									</div>										
								</div>
							</td>							
							<td id="td<%= index %>">
								<div class="priceItemCart">
									<span>$ </span><span id="span<%= index %>" class="cartPrSi"><%= (cartSvc.getValueByItem_no(mb_id, order.getItem_no()))*(itemSvc.getOneItem(order.getItem_no()).getItem_price())%></span>
									<div class="poCaSi<%= index %>"><span>ポイント：</span><%=itemSvc.getOneItem(order.getItem_no()).getPoints()%></div>
								</div>
							</td>
						</tr>
					<% }%>
					<tr>
						<td colspan="5">
						<td class="totalPriInCart">總金額<span> $ </span></td>
						<td id="checkTotal">0</td>
						<td>
					</tr>
					</table>
					<br>
<!-- 					<input type="button" value="清除勾選" class="paybtn cleanbtn"  -->
<%-- 									onclick="go('<%=request.getContextPath()%>/shop/shoppingRedisCart.do?action=deleteSelected')"> --%>
					<input type="button" value="前往結帳" class="goPay">
				</form>
			</div>
			<% } else{%> 
			<div>購物車是空的</div>			
			<%} %>
			
			<%} else { %>
				 <%  if(buylist != null && (buylist.size() > 0)) {%>
					<%  for (int index = 0; index < buylist.size(); index++){
						ItemVO order = buylist.get(index);
						total += (cartSvc.getValueByItem_noCo(sessionID, order.getItem_no()))*(itemSvc.getOneItem(order.getItem_no()).getItem_price());
					%>
						<tr class="itBorTop">
							<td>
								<label class="checklabel">
									<input id="boxselect<%= index %>" type="checkbox" name="checkact" class="ceIt" value="<%= index %>">
									<input id="checknum<%= index %>" type="hidden" name="checkedItem" value="0">
									<input id="forPlusnum<%= index %>" type="hidden" value="<%= (cartSvc.getValueByItem_noCo(sessionID, order.getItem_no()))*(itemSvc.getOneItem(order.getItem_no()).getItem_price())%>">
								</label>
							</td>
							<td class="imgframe"><img id="imgCheck_<%=index %>" src="<%=request.getContextPath()%>/item_pics/item_pics.do?item_pic_no=<%=item_picsSvc.getAllPics(order.getItem_no()).get(0).getItem_pic_no()%>&action=getOne_Pic_Display"></td>
						<td>
								<div class="cartborderFi">
									<div class="cartItemName"><%=itemSvc.getOneItem(order.getItem_no()).getItem_name()%></div>
									<div class="number-input">
                                           <!--數量選單 -->
                                         <div class="qIn">																							
                                         	<span id="btn<%= index + "_1" %>" class="minusIn" onclick="this.parentNode.querySelector('input[type=number]').stepDown()" ><i class="fas fa-minus"></i></span>
											<input id="qty<%= index %>" class="quantity" min="0" name="quantity" value="<%=cartSvc.getValueByItem_noCo(sessionID, order.getItem_no())%>" type="number">						
											<span id="btn<%= index %>" class="plusIn" onclick="this.parentNode.querySelector('input[type=number]').stepUp()"><i class="fas fa-plus"></i></span>
										</div>  
									    <input type="hidden" name="action"  value="deleteSelected"> 
										<input type="hidden" name="item_no"  value="<%=order.getItem_no()%>"> 
										<input type="hidden" name="item_price"  value="<%=itemSvc.getOneItem(order.getItem_no()).getItem_price()%>"> 
										<input type="hidden" name="quantity" value="1" id="qty<%= index + "_1"%>">
										<input type="hidden" name="points" value="<%=itemSvc.getOneItem(order.getItem_no()).getPoints()%>" >
										<input type="button" value="刪 除" class="deOnIt" 
											onclick="location.href='<%=request.getContextPath()%>/shop/shoppingRedisCart.do?action=DELETE&del=<%=index%>&item_no=<%=order.getItem_no()%>&quantity=<%=cartSvc.getValueByItem_noCo(sessionID, order.getItem_no())%>'" >									
									</div>										
								</div>
							</td>							
							<td id="td<%= index %>">
								<div class="priceItemCart">
									<span>$ </span><span id="span<%= index %>" class="cartPrSi"><%= (cartSvc.getValueByItem_noCo(sessionID, order.getItem_no()))*(itemSvc.getOneItem(order.getItem_no()).getItem_price())%></span>
									<div class="poCaSi<%= index %>"><span>ポイント：</span><%=itemSvc.getOneItem(order.getItem_no()).getPoints()%></div>
								</div>
							</td>
						</tr>
					<% }%>
					<tr class="totalPriInCart">
						<td colspan="5">
						<td>總金額<span> $ </span></td>
						<td id="checkTotal">0</td>
						<td>
					</tr>
					</table>
					<br>
<!-- 					<input type="button" value="清除勾選" class="paybtn cleanbtn"  -->
<%-- 									onclick="go('<%=request.getContextPath()%>/shop/shoppingRedisCart.do?action=deleteSelected')"> --%>
					<input type="button" value="前往結帳" class="goPay">
				</form>
			</div>
			<% } else{%> 
			<div>購物車是空的</div>			
			<%} %>
		<%} %>
		</div>
	</div>
	<!-- Footer Section Start -->

	<!-- Footer Section End -->
	<!-- Js Plugins -->
<%@ include file="/frontend/files/commonJS.file" %>
	<script src="${pageContext.request.contextPath}/js/slick.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/front/frontShopPage.js"></script>
	<script type="text/javascript">
	function go(data){
		document.selectedForm.action=data;
		document.selectedForm.submit();
	}
	
$(function(){
		<% List<ItemVO> changinglist = (member!=null)? RedisBuylist : buylist;%>
		<%  for (int index = 0; index < changinglist.size(); index++){%>			
			 <%ItemVO order = changinglist.get(index);%>
			<% ItemVO oneItem = itemSvc.getOneItem(order.getItem_no()); %>
			var itemAdd_<%= index %> = {
				"action":"AddQty",
				"index":<%= index %>,
				"item_no":"<%=order.getItem_no()%>",
				"item_name":"<%=oneItem.getItem_name()%>",
				"item_price":<%=oneItem.getItem_price()%>,
				"points":<%=oneItem.getPoints()%>,
			};
			
			$("#btn<%= index %>").click(function(event){
				event.stopPropagation();
				console.log("<%=order.getItem_no()%>");
				$.ajax({
					data:itemAdd_<%= index %>,
					type:"POST",
					dataType: "json",
					url:"<%=request.getContextPath()%>/shop/changingServlet.do",
					success: function (data){
						console.log("增加"+data.amount);
						$("#span<%= index %>").html(data.amount+".0");	
						$(".poCaSi<%= index %>").html(data.poamount);
						if($("#checknum<%= index %>").val()!=0){
							$("#checknum<%= index %>").val(data.amount);
						}	
						$("#forPlusnum<%= index %>").val(data.amount);
						
						var shoppingTatal = 0;
						<%  for (int i = 0; i < RedisBuylist.size(); i++){%>
							shoppingTatal += parseInt($("#checknum<%= i %>").val(),10);
						<%}%>
						$("#checkTotal").text(shoppingTatal+".0");
					}
				});	
			});
			var itemSub_<%= index %> = {
				"action":"SubQty",
				"index":<%= index %>,
				"item_no":"<%=order.getItem_no()%>",
				"item_name":"<%=oneItem.getItem_name()%>",
				"item_price":<%=oneItem.getItem_price()%>,
			};
			
			$("#btn<%= index + "_1" %>").click(function(event){	
				event.stopPropagation();
				console.log("<%=order.getItem_no()%>");
				$.ajax({
					data:itemSub_<%= index %>,
					type:"POST",
					dataType: "json",
					url:"<%=request.getContextPath()%>/shop/changingServlet.do",
					success: function (data){
						console.log("減少"+data.amount);
						console.log("index"+data.index);
						if(data.amount!='')
							$("#span<%= index %>").html(data.amount+".0");
							if($("#checknum<%= index %>").val()!=0){
								$("#checknum<%= index %>").val(data.amount);
							}	
							$("#forPlusnum<%= index %>").val(data.amount);
							
							var shoppingTatal = 0;
							<%  for (int i = 0; i < RedisBuylist.size(); i++){%>
								shoppingTatal += parseInt($("#checknum<%= i %>").val(),10);
							<%}%>
							$("#checkTotal").text(shoppingTatal+".0");			
						if(data.amount=== undefined){
							Swal.fire({
							  title: '刪除商品',
							  text: '數量為0，從購物車移除',
							  icon: 'warning',
							  showConfirmButton: false,
							  customClass: 'swal-wide',
							  timer: 1500
							}).then(function () {
						        window.location.href = "<%=request.getContextPath()%>/frontend/shop/shopCartRedis.jsp"
						    })
						}
					}
				});	
			});
			
			$("#boxselect<%= index %>").change(function(event){
				
				console.log("check="+$("#boxselect<%= index %>").prop("checked"));
				console.log("forPlusnum="+$("#forPlusnum<%= index %>").val());
				event.stopPropagation();
				$.ajax({
					data:creatCheckedJson($("#boxselect<%= index %>").prop("checked"),$("#forPlusnum<%= index %>").val()),
					type:"POST",
					dataType: "json",
					url:"<%=request.getContextPath()%>/shop/changingServlet.do",
					success: function (data){		
						console.log("Checkamount="+data.amount);
						$("#checknum<%= index %>").val(data.amount);
						
						var shoppingTatal = 0;
						<%  for (int a = 0; a < changinglist.size(); a++){%>
							shoppingTatal += parseInt($("#checknum<%= a %>").val(),10);
						<%}%>
						$("#checkTotal").text(shoppingTatal+".0");
					}
				});
			});
			
			$("#imgCheck_<%=index %>").click(function(){
				$("#boxselect<%= index %>").click();
			});	

		<%}%>
		});
		function creatCheckedJson(boxselect, amount){
			console.log("boxselect:"+boxselect+"; amount:"+amount);
			var checkedJson= {"action":"BoxSelect", "boxselect":boxselect, "amount":amount};
			return checkedJson;
		}
		
	var mem = "<%=mb_id%>";
	console.log("mb_id= "+mem);

		$(".goPay").click(function(event){  //進入結帳
			let checked = false;
			let checkboxs = $(".ceIt");
			for (let i = 0; i < checkboxs.length; i++){
				if (checkboxs.eq(i).prop("checked") == true){
					checked = true;
					break;
				}
			}
			if(mem=="null"){
				Swal.fire({
					  title: '未登入會員',
					  text: '請登入會員,再進行結帳',
					  icon: 'warning'
					});
				$(".offcanvas-menu-overlay").removeClass("active");
				$(".login-window-overlay").addClass("active");
				$(".login-window").addClass("show-login-window");
				$(".offcanvas-menu-wrapper").removeClass("show-offcanvas-menu-wrapper");
			} else if (!checked){
				Swal.fire({
					  title: '未勾選商品',
					  text: '請勾選要結帳的商品,再進行結帳',
					  icon: 'warning'
					});
			} else{
				go('<%=request.getContextPath()%>/shop/shoppingRedisCart.do?action=CHECKOUT');
			}
		});
	</script>
</body>
</html>