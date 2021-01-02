<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="UTF-8">
<title>Resort Store Manage</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/back/backend-shop.css" />
<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
<%@ include file="/backend/files/backend_header.file" %>
</head>
<body>
<%@ include file="/backend/files/backend_sidebar.file" %>
	<div id="titlename">商品訂單管理</div>
	<div class="page">
		<h3 class="subtitle">商品訂單查詢:</h3>

		<ul>
			<li><a href='listAllShop_order.jsp'>所有商品訂單查詢</a> <br>
			<br></li>
			<li>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/shop_order/shop_order.do">
					<b>輸入商品訂單編號:</b> <input type="text" name="sp_odno"
						class="selectoption"> <input type="hidden" name="action"
						value="getOne_For_Display"> <input type="submit"
						value="查詢">
				</FORM>
			</li>

	<jsp:useBean id="shop_orderSvc" scope="page" class="com.shop_order.model.Shop_orderService" />

			<li>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/shop_order/shop_order.do">
					<b>選擇商品訂單編號:</b> <select size="1" name="sp_odno" class="selectoption">
						<c:forEach var="shop_orderVO" items="${shop_orderSvc.allShop_order}">
							<option value="${shop_orderVO.sp_odno}">${shop_orderVO.sp_odno}
						</c:forEach>
					</select> <input type="hidden" name="action" value="getOne_For_Display">
					<input type="submit" value="查詢">
				</FORM>
			</li>

			<jsp:useBean id="shop_order_detailSvc" scope="page"
				class="com.shop_order_detail.model.Shop_order_detailService" />

<!-- 			<li> -->
<!-- 				<FORM METHOD="post" -->
<%-- 					ACTION="<%=request.getContextPath()%>/back-end/shop_order/shop_order.do"> --%>
<!-- 					<b>選擇類別名稱:</b> <select size="1" name="item_type_no" -->
<!-- 						class="selectoption"> -->
<%-- 						<c:forEach var="item_typeVO" items="${item_typeSvc.allItem_type}"> --%>
<%-- 							<option value="${item_typeVO.item_type_no}" --%>
<%-- 								${(itemVO.item_type_no==item_typeVO.item_type_no)? 'selected':'' }>${item_typeVO.type_name} --%>
<%-- 						</c:forEach> --%>
<!-- 					</select> <input type="hidden" name="action" -->
<!-- 						value="getItemByItem_type_no_For_Display"> <input -->
<!-- 						type="submit" value="查詢"> -->
<!-- 				</FORM> -->
<!-- 			</li> -->
		</ul>
		<%-- 錯誤表列 --%>

		<c:if test="${not empty errorMsgs}">
			<div style="color: red" class="error">
				<div class="errorfix">請修正以下錯誤</div>
				<div class="closeerror">✖</div>
				<ul style=list-style-type:none>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</div>
		</c:if>

	
		

<!-- 		<ul> -->
<%-- 			<li><a href='<%=request.getContextPath()%>/back-end/item_type/listAllItem_type.jsp'>所有商品類別查詢</a> <br> --%>
<!-- 			<br></li> -->
<!-- 			<li> -->
<!-- 				<FORM METHOD="post" -->
<%-- 					ACTION="<%=request.getContextPath()%>/back-end/item_type/item_type.do"> --%>
<!-- 					<b>選擇類別編號:</b> <select size="1" name="item_type_no" class="selectoption"> -->
<%-- 						<c:forEach var="item_typeVO" items="${item_typeSvc.allItem_type}"> --%>
<%-- 							<option value="${item_typeVO.item_type_no}">${item_typeVO.item_type_no} --%>
<%-- 						</c:forEach> --%>
<!-- 					</select> <input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 					<input type="submit" value="查詢"> -->
<!-- 				</FORM> -->
<!-- 			</li> -->

<!-- 			<li> -->
<!-- 				<FORM METHOD="post" -->
<%-- 					ACTION="<%=request.getContextPath()%>/back-end/item_type/item_type.do"> --%>
<!-- 					<b>選擇類別名稱:</b> <select size="1" name="item_type_no" -->
<!-- 						class="selectoption"> -->
<%-- 						<c:forEach var="item_typeVO" items="${item_typeSvc.allItem_type}"> --%>
<%-- 							<option value="${item_typeVO.item_type_no}">${item_typeVO.type_name} --%>
<%-- 						</c:forEach> --%>
<!-- 					</select> <input type="hidden" name="action" -->
<!-- 						value="getOne_For_Display"> <input -->
<!-- 						type="submit" value="查詢"> -->
<!-- 				</FORM> -->
<!-- 			</li> -->
<!-- 		</ul> -->
	</div>
	<%@ include file="/backend/files/backend_footer.file" %>
	<script src="${pageContext.request.contextPath}/js/back/backShopItem.js"></script>
</body>
</html>