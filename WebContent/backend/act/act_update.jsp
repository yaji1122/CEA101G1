<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.act.model.*"%>

<%
	ActVO actVO = (ActVO) request.getAttribute("actVO");
//ActServlet.java(Concroller), 存入req的ActVO物件 (包括幫忙取出的ActVO物件, 也包括輸入資料錯誤時的ActVO物件)
%>

<html>
<head>
<%@ include file="/backend/files/backend_header.file"%>
<!-- 加入基本 css -->
<title>修改活動 - addAct.jsp</title>
</head>
<style>
	#show {
	max-width: 100%;
	}
	#pic-area {
		width:400px;
		margin: 0 auto;
	}
	.message {
		text-align: center;
	}
</style>
<body>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}
			</c:forEach>
		</ul>
	</c:if>
	<FORM METHOD="post" id="act-form" enctype="multipart/form-data">
		<div id="form" class="update-form">
			<label for="inputPassword4" class=" -danger">活動項目編號:(ACT_Event_No):</label>
			<input type="text" class="form-control" id="input-Act-Event-No"
				name="actEventNo" size="45" placeholder="請選擇活動項目編號"
				value="<%=(actVO == null) ? "" : actVO.getActEventNo()%>" /> <label
				class=" " role="">活動名稱: (ACT_Name):</label> <input type="text"
				class="form-control" id="input-Act-Namel4" name="actName" size="45"
				placeholder="請輸入活動名稱"
				value="<%=(actVO == null) ? "" : actVO.getActName()%>" /> <label
				class=" -danger">活動狀態: (ACT_Status):</label> <input type="text"
				class="form-control" aria-label="Amount (to the nearest dollar)"
				name="actStatus" size="45"
				value="<%=(actVO == null) ? "" : actVO.getActStatus()%>" /> 
				<label>活動時段: (ACT_Time):</label> 
				<input type="text" class="form-control" id="actTime" name="actTime" size="45" value="<%=actVO.getActTime()%>" />
				<label>活動價格:</label> <input type="text"
				class="form-control" id="input-Act-Namel4" name="actPrice" size="45"
				placeholder="請填入數字"
				value="<%=(actVO == null) ? "" : actVO.getActPrice()%>" /> <label
				class=" ">活動照片:(Pic_Load):</label>
			<div id="pic-area">
				<img id="show" src="<%=request.getContextPath()%>/ActServlet?action=get_actpic&actno=<%=actVO.getActNo()%>">
			</div>
			<input onchange="showImg(this)" type="file" class="form-control"
                 aria-label="Amount (to the nearest dollar)"
				name="actPic" size="45"
				value="<%=(actVO == null) ? "" : actVO.getActPic()%>"> <label
				class=" " role="">活動敘述:</label> <input type="text"
				class="form-control" id="input-Act-Namel4" name="actInfo" size="100"
				placeholder="活動內容敘述"
				value="<%=(actVO == null) ? "" : actVO.getActInfo()%>" />

		</div>

		<div class="message">
			<input type="hidden" name="action" value="update"> <input
				type="hidden" name="empno" value="<%=actVO.getActNo()%>">
			<button type="submit" class="btn btn-primary">送出修改</button>
			<button type="reset" class="btn btn-primary">重設</button>
		</div>

	</FORM>
	<!-- 頁面內容結束 -->
	<%@ include file="/backend/files/backend_footer.file"%>
	<!-- 加入基本 js -->
	<script>
		$( document ).ready(function(){
			
			let formElem = document.querySelector("#act-form");
            formElem.addEventListener("submit", (e) => {
                e.preventDefault();
               
                let data = new FormData(formElem);
                let xhr = new XMLHttpRequest();
                xhr.open("post", "${pageContext.request.contextPath}/ActServlet");
                xhr.onload = function () {
                    if (xhr.readyState === xhr.DONE) {
                        if (xhr.status === 200) {
                            if (xhr.responseText === "新增成功") {
                                Swal.fire({
                                    position: "top-end",
                                    icon: "success",
                                    title: xhr.responseText,
                                    showConfirmButton: false,
                                    timer: 1500,
                                });
                                setTimeout(function () {
                                    location.reload();
                                }, 1400);
                            } else if (xhr.responseText === "編號重複") {
                                Swal.fire({
                                    position: "top-end",
                                    icon: "error",
                                    title: xhr.responseText,
                                    showConfirmButton: false,
                                    timer: 1500,
                                });
                            }
                        }
                    }
                };
                xhr.send(data);
            });
			
			function showImg(thisimg) {
				var file = thisimg.files[0];
				if (window.FileReader) {
					var fr = new FileReader();
	
					var showimg = document.getElementById('show');
					fr.onloadend = function(e) {
						showimg.src = e.target.result;
					};
					fr.readAsDataURL(file);
					showimg.style.display = 'block';
				}
			}
		$("#actTime").datetimepicker({
			datepicker:false,
			format: "h:i",
			step: 60,
		});
	}) 
	</script>
</body>
</html>