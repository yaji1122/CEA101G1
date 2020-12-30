<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/backend/files/backend_header.file"%>

<title>Resort Store Manage</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/back/backend-shop.css" />


</head>
<body>
	<%@ include file="/backend/files/backend_sidebar.file"%>
	<div id="titlename">商品管理</div>
	<div class="page">
		<h3 class="subtitle">商品資料查詢:</h3>

		<ul>
			<li><a href='<%=request.getContextPath()%>/backend/item/listAllItem.jsp'>所有商品查詢</a> <br>
			<br></li>
			<li>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/item/item.do">
					<b>輸入商品編號:</b> <input type="text" name="item_no"
						class="selectoption"> <input type="hidden" name="action"
						value="getOne_For_Display"> <input type="submit"
						value="查詢">
				</FORM>
			</li>

	<jsp:useBean id="itemSvc" scope="page" class="com.item.model.ItemService" />

			<li>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/item/item.do">
					<b>選擇商品編號:</b> <select size="1" name="item_no" class="selectoption">
						<c:forEach var="itemVO" items="${itemSvc.allItem}">
							<option value="${itemVO.item_no}">${itemVO.item_no}
						</c:forEach>
					</select> <input type="hidden" name="action" value="getOne_For_Display">
					<input type="submit" value="查詢">
				</FORM>
			</li>

			<li>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/item/item.do">
					<b>選擇商品名稱:</b> <select size="1" name="item_no" class="selectoption">
						<c:forEach var="itemVO" items="${itemSvc.allItem}">
							<option value="${itemVO.item_no}">${itemVO.item_name}
						</c:forEach>
					</select> <input type="hidden" name="action" value="getOne_For_Display">
					<input type="submit" value="查詢">
				</FORM>
			</li>

			<jsp:useBean id="item_typeSvc" scope="page"
				class="com.item_type.model.Item_typeService" />

			<li>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/item/item.do">
					<b>選擇類別名稱:</b> <select size="1" name="item_type_no"
						class="selectoption">
						<c:forEach var="item_typeVO" items="${item_typeSvc.allItem_type}">
							<option value="${item_typeVO.item_type_no}"
								${(itemVO.item_type_no==item_typeVO.item_type_no)? 'selected':'' }>${item_typeVO.type_name}
						</c:forEach>
					</select> <input type="hidden" name="action"
						value="getItemByItem_type_no_For_Display"> <input
						type="submit" value="查詢">
				</FORM>
			</li>
		</ul>
		<%-- 錯誤表列 --%>

		<c:if test="${not empty errorMsgs}">
			<div style="color: red" class="error">
				<div class="errorfix">請修正以下錯誤</div>
				<div class="close">✖</div>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</div>
		</c:if>

		<h3>商品類別查詢:</h3>
		

		<ul>
			<li><a href='<%=request.getContextPath()%>/backend/item_type/listAllItem_type.jsp'>所有商品類別查詢</a> <br>
			<br></li>
			<li>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/item_type/item_type.do">
					<b>選擇類別編號:</b> <select size="1" name="item_type_no" class="selectoption">
						<c:forEach var="item_typeVO" items="${item_typeSvc.allItem_type}">
							<option value="${item_typeVO.item_type_no}">${item_typeVO.item_type_no}
						</c:forEach>
					</select> <input type="hidden" name="action" value="getOne_For_Display">
					<input type="submit" value="查詢">
				</FORM>
			</li>

			<li>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/item_type/item_type.do">
					<b>選擇類別名稱:</b> <select size="1" name="item_type_no"
						class="selectoption">
						<c:forEach var="item_typeVO" items="${item_typeSvc.allItem_type}">
							<option value="${item_typeVO.item_type_no}">${item_typeVO.type_name}
						</c:forEach>
					</select> <input type="hidden" name="action"
						value="getOne_For_Display"> <input
						type="submit" value="查詢">
				</FORM>
			</li>
		</ul>



		<h3>新增修改商品:</h3>

		<ul>
			<li><a
				href='<%=request.getContextPath()%>/backend/item/addItem.jsp'>新增商品</a></li>
			<li><a
				href='<%=request.getContextPath()%>/backend/item/listAllItem.jsp'>修改商品</a></li>
			<li><a
				href='<%=request.getContextPath()%>/backend/item_type/addItem_type.jsp'>新增商品類別</a></li>
			<li><a
				href='<%=request.getContextPath()%>/backend/item_type/listAllItem_type.jsp'>修改商品類別</a></li>
		</ul>
	</div>
	<%@ include file="/backend/files/backend_footer.file"%>
	<script src="${pageContext.request.contextPath}/js/backend.js"></script>
	<script src="${pageContext.request.contextPath}/js/backShopItem.js"></script>
</body>
</html>