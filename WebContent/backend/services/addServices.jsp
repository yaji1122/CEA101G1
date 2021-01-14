<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.services.model.*"%>

<%
	ServicesVO servicesVO = (ServicesVO) request.getAttribute("servicesVO");
%>
<jsp:useBean id="serviceTypeSvc" scope="page"
	class="com.service_type.model.ServiceTypeService" />
<%-- <%=servicesVO == null%> --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style>
#preview img{
max-width:300px;

}
</style>
<title>服務新增</title>

</head>
<body>
	<form METHOD="post"
		ACTION="${pageContext.request.contextPath}/ServicesServlet"
		name="form1" enctype="multipart/form-data">
		<div class="form-group">
			<label for="serv_no">服務編號:</label> <input type="text"
				class="form-control" id="serv_no" name="serv_no"
				value="<%=(servicesVO == null) ? "001" : servicesVO.getServ_no()%>" required maxlength="3" minlength="3" pattern="\d*">
		</div>
		<div class="form-group">
			<label for="serv_type_no">服務類型:</label>
			<select name="serv_type_no"
				id="serv_type_no" class="form-control">
				<c:forEach var="serviceTypeVO" items="${serviceTypeSvc.all}">
					<option value="${serviceTypeVO.serv_type_no}">${serviceTypeVO.serv_type_name}</option>
				</c:forEach>
			</select>
			 <%-- <input type="text"
				class="form-control" id="serv_type_no" name="serv_type_no"
				value="<%=(servicesVO == null) ? "1" : servicesVO.getServ_type_no()%>"> --%>
		</div>
		<div class="form-group">
			<label for="serv_status">上架狀態:</label> <select name="serv_status" id="serv_status" class="form-control">
				<option value="0" >未上架</option>
				<option value="1" >已上架</option>
			</select> 
		</div>
		<div class="form-group">
			<label for="serv_price">服務價格:</label> <input type="text"
				class="form-control" id="serv_price" name="serv_price"
				value="<%=(servicesVO == null) ? "2000" : servicesVO.getServ_price()%>" required maxlength="5" pattern="\d+">
		</div>
		<div class="form-group">
			<label for="serv_pic">服務圖片:</label> <input type="file"
				class="form-control" id="pick_serv_pic" name="serv_pic">
			<div id="preview"></div>
				
		</div>
		<div class="form-group">
			<label for="serv_info">服務介紹:</label> 
			<textarea class="form-control" name="serv_info" id="serv_info" maxlength="500"
				placeholder="最多500字" required maxlength="500"></textarea>
			
			<%-- <input type="text"
				class="form-control" id="serv_info" name="serv_info"
				value="<%=(servicesVO == null) ? "test" : servicesVO.getServ_info()%>"> --%>
		</div>
		<div class="form-group">
			<label for="serv_name">服務名稱:</label> <input type="text"
				class="form-control" id="serv_name" name="serv_name"
				value="<%=(servicesVO == null) ? "按摩" : servicesVO.getServ_name()%>" required>
		</div>
		<div class="form-group">
			<label for="serv_dura">服務時長:</label> <input type="text"
				class="form-control" id="serv_dura" name="serv_dura"
				value="<%=(servicesVO == null) ? 1 : servicesVO.getServ_dura()%>" required maxlength="1" pattern="\d+">
		</div>
		<div class="form-group">
			<label for="serv_ppl">服務人員人數:</label> <input type="text"
				class="form-control" id="serv_ppl" name="serv_ppl"
				value="<%=(servicesVO == null) ? 1 : servicesVO.getServ_ppl()%>" required maxlength="2" pattern="\d+">
		</div>
		<input type="hidden" name="action" value="insert"> <input
			class="btn btn-primary" type="submit" value="送出新增">
	</form>
	
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