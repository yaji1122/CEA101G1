<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.services.model.*"%>

<%
	ServicesVO servicesVO = (ServicesVO) request.getAttribute("servicesVO"); //ServicesServlet.java (Concroller) 存入req的servicesVO物件 (包括幫忙取出的servicesVO, 也包括輸入資料錯誤時的servicesVO物件)
%>

<jsp:useBean id="serviceTypeSvc" scope="page"
	class="com.service_type.model.ServiceTypeService" />
	
<%-- <%= servicesVO==null %> --${servicesVO.serv_no }-- --%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
	integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<style>
body {
	margin-left: 30px;
	margin-right: 30px;
}

#preview img {
	max-width: 300px;
}
</style>
<title>修改服務</title>
</head>
<body>
	<form METHOD="post"
		ACTION="${pageContext.request.contextPath}/ServicesServlet"
		name="form1" enctype="multipart/form-data">
		<div class="form-group">
			服務編號:<%=servicesVO.getServ_no()%>
		</div>
		<div class="form-group">
			<label for="serv_type_no">服務類型:</label> <select name="serv_type_no"
				id="serv_type_no">
				<c:forEach var="serviceTypeVO" items="${serviceTypeSvc.all}">
					<option value="${serviceTypeVO.serv_type_no}"
						${(servicesVO.serv_type_no==serviceTypeVO.serv_type_no)?'selected':'' }>${serviceTypeVO.serv_type_name}</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group">
			<label for="serv_status">上架狀態:</label> 
			
			<select name="serv_status" id="serv_status">
				<option value="0" ${servicesVO.serv_status == 0 ? 'selected':''}>未上架</option>
				<option value="1" ${servicesVO.serv_status == 1 ? 'selected':''}>已上架</option>
			</select> 
			<%-- <input type="text" class="form-control" id="serv_status"
				name="serv_status" value="<%=servicesVO.getServ_status()%>" required> --%>
		</div>
		<div class="form-group">
			<label for="serv_price">服務價格:</label> <input type="text"
				class="form-control" id="serv_price" name="serv_price"
				value="<%=servicesVO.getServ_price()%>" required>
		</div>
		<div class="form-group">
			<label for="serv_pic">服務圖片:</label> <img
				src="<%=request.getContextPath()%>/ServicesServlet?servno=${servicesVO.serv_no}&action=getOneServicesPic"
				alt="" class="img-thumbnail" width="150px"><input type="file"
				class="form-control" id="pick_serv_pic" name="serv_pic"
				value="<%=servicesVO.getServ_pic()%>">

		</div>
		<div class="form-group">
			<label>欲上傳圖片: </label>
			<div id="preview"></div>
		</div>

		<div class="form-group">
			<label for="serv_info">服務介紹:</label> 
			<textarea class="form-control" name="serv_info" id="serv_info" maxlength="500"
				placeholder="最多500字" required ><%=servicesVO.getServ_info()%></textarea>
			
			<%-- <input type="text"
				class="form-control" id="serv_info" name="serv_info"
				value="<%=servicesVO.getServ_info()%>"> --%>
		</div>
		<div class="form-group">
			<label for="serv_name">服務名稱:</label> <input type="text"
				class="form-control" id="serv_name" name="serv_name"
				value="<%=servicesVO.getServ_name()%>" required>
		</div>
		<div class="form-group">
			<label for="serv_dura">服務時長:</label> <input type="text"
				class="form-control" id="serv_dura" name="serv_dura"
				value="<%=servicesVO.getServ_dura()%>" required>
		</div>
		<div class="form-group">
			<label for="serv_ppl">服務人員人數:</label> <input type="text"
				class="form-control" id="serv_ppl" name="serv_ppl"
				value="<%=servicesVO.getServ_ppl()%>" required>
		</div>
		<input type="hidden" name="action" value="update"> <input
			type="hidden" name="serv_no" value="<%=servicesVO.getServ_no()%>">
		<input class="btn btn-primary" type="submit" value="送出修改"> <a
			href="${pageContext.request.contextPath}/backend/services/servicesInfo.jsp"
			class="btn btn-primary"> 取消 </a>
	</form>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
		integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"
		integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s"
		crossorigin="anonymous"></script>

	<script type="text/javascript">
		function init() {

			// 1. 抓取DOM元素
			let myFile = document.getElementById("pick_serv_pic");
			let filename = document.getElementById("serv_pic");
			let preview = document.getElementById('preview');

			// 2. 對myFile物件註冊change事件 - 改變選擇的檔案時觸發
			myFile.addEventListener('change', function() {
				// 取得檔案物件的兩種方式
				// 1. 直接從myFile物件上取得檔案物件 (因為非同步，一樣，多個classname註冊時會有問題)
				// 2. 從event物件中取得他的soure target，也就是myFile物件，再取得檔案物件 
				// 檔案的基本資訊，包括：檔案的名稱、大小與文件型態
				// 判斷files物件是否存在
				if (this.files) {
					// 取出files物件的第一個
					let file = this.files[0];
					// 判斷file.type的型別是否包含'image'
					console.log(file.type);
					if (file.type.indexOf('image') > -1) {
						// 填入檔名
						//  filename.value = file.name;
						// new a FileReader
						let reader = new FileReader();
						// 在FileReader物件上註冊load事件 - 載入檔案的意思
						reader.addEventListener('load', function(e) {
							// 取得結果 提示：從e.target.result取得讀取到結果
							// 新增img元素
							let img = document.createElement('img');
							// 賦予src屬性
							img.src = e.target.result;
							// 放到div裡面
							preview.append(img);
						});
						// 使用FileReader物件上的 readAsDataURL(file) 的方法，傳入要讀取的檔案，並開始進行讀取
						reader.readAsDataURL(file); /// trigger READING...
					} else {
						// 彈出警告視窗 alert('請上傳圖片！');
						alert('請上傳圖片！');
					}
				}
			});
		}

		window.onload = init;
	</script>

</body>
</html>