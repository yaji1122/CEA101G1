<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.item_pics.model.*"%>
<%
	String item_no = request.getParameter("item_no");
	if (item_no == null) {
		item_no = (String) request.getAttribute("item_no");
	}
	Item_picsService item_picsSvc = new Item_picsService();
	List<Item_picsVO> list = item_picsSvc.getAllPics(item_no);
	pageContext.setAttribute("list", list);
%>

<html>
<head>
<title>商品照片資料 - listAllByItem_no.jsp</title>
<%@ include file="/backend/files/backend_header.file" %>
<style>
#titlePic {
	width: 100%;
	height: auto;
	margin-top: 15px;
	background-color: rgb(204, 202, 202);
	border: 1px solid;
	font-size: 30px;
	text-align: center;
	padding-top: 5px;
	display: flex;
	flex-direction: row;
}

.titleAllPic {
	margin-left: 450px;
	margin-right: auto;
	margin-top:-4px;
}

.homePic {
	margin-left: 83px;
	margin-right: 0px;
	float: right;
	width: 150px;
	font-size: 25px;
}

table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
	margin-left: auto;
	margin-right: auto;
	border: 1px gray;
}

tr:nth-of-type(even) {
	background-color: #f3f3f3;
}

tr:nth-of-type(odd) {
	background-color: #ddd;
}

th, td {
	padding: 5px;
	text-align: center;
}

.picpage {
	width: 400px;
	height: 350px;
	position: fixed;
	border: 1px solid;
	background-color: rgb(240, 232, 232);
	left: 0px;
	right: 0px;
	top: 50px;
	bottom: 0px;
	margin: auto;
	visibility: hidden;
}

.picpage-show {
	z-index: 99;
	visibility: visible;
}

.close :after {
	content: "✖";
}

.close:hover {
	cursor: pointer;
}

.close {
	margin-left: 370px;
	margin-top: 2px;
}

.imgsize {
	width: 300px;
	height: 250px;
}

.addPic {
	size: 30px;
}
.picBox{

}
.black{
width:100%;
height:100%;
background-color:rgb(0,0,0);
opacity:0.7;
z-index:80;
display:none;
position: fixed;
}
.black-show{

}
</style>
<script type="text/javascript">
	var loadFile = function(event) {
		var reader = new FileReader();
		reader.onload = function() {
			var putImg = document.getElementById('putImg');//為DIV的ID，用來存放圖片的
			putImg.innerHTML = "<img id ='preview' width=200px height=150px>"; //生成一個img標籤
			document.getElementById("preview").src = reader.result;//將圖片路徑讀進src
		}
		reader.readAsDataURL(event.target.files[0]);
	}
</script>
<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
</head>
<body bgcolor='white'>
<%@ include file="/backend/files/backend_sidebar.file" %>
<div class="black"></div>
	<jsp:useBean id="itemVO" scope="page" class="com.item.model.ItemVO" />
	<jsp:useBean id="itemSvc" scope="page" class="com.item.model.ItemService" />
	<div id="titlePic">

		<div class="titleAllPic">
			商品照片資料<a><%=item_no%><%=itemSvc.getOneItem(item_no).getItem_name()%></a>
		</div>

		<FORM style="margin-bottom: 0px;">
			<input type="button" value="新增圖片" class="addPic">
		</FORM>
		<a href="<%=request.getContextPath()%>/backend/item/itemInfo.jsp"
			class="homePic">回商品管理</a>
	</div>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table>
		<tr>
			<th>商品編號</th>
			<th>圖片編號</th>
			<th>商品圖片</th>
			<th>刪除</th>

		</tr>

		<c:forEach var="item_picsVO" items="${list}">

			<tr>
				<td>${item_picsVO.item_no}</td>
				<td>${item_picsVO.item_pic_no}</td>
				<td><img
					src="<%=request.getContextPath()%>/item_pics/item_pics.do?item_pic_no=${item_picsVO.item_pic_no}&action=getOne_Pic_Display"
					class="imgsize"></td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/item_pics/item_pics.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="item_pic_no" value="${item_picsVO.item_pic_no}"> <input
							type="hidden" name="action" value="delete"> <input
							type="hidden" name="item_no" value="<%=item_no%>">
					</FORM>
				</td>

			</tr>
		</c:forEach>
	</table>
<div class="picBox">
	<FORM
		action="<%=request.getContextPath()%>/item_pics/item_pics.do"
		method=post enctype="multipart/form-data" class="picpage" name="form1">

		<div class="close">✖</div>
		<img src=""> <input type="file" name="item_pic"
			onchange="loadFile(event)" required
			accept="image/gif,image/jpeg,image/jpg,image/png,image/svg, image/webp">

		<div id="putImg"></div>

		<input type="submit" value="上傳" onclick="fun1"> <input
			type="hidden" name="item_no" value="<%=item_no%>"> <input
			type="hidden" name="action" value="insert">

	</FORM>
	</div>
	<%@ include file="/backend/files/backend_footer.file" %>
	<script>
		$(".addPic").click(function() {
			$(".picpage").addClass("picpage-show");
			$(".black").css("display", "block");
		});
		$(".close").click(function() {
			$(".picpage").removeClass("picpage-show");
			$(".black").css("display", "none");
		});
	</script>

</body>
</html>