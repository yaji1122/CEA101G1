<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.roomtype.model.*"%>
<%@ page import="java.util.List"%>
<%
RoomTypeService rmtypeSvc = new RoomTypeService();
List<RoomTypeVO> rmtypeList = rmtypeSvc.getAll();
pageContext.setAttribute("rmtypeList", rmtypeList);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Add RoomType Pictures</title>
</head>
<style>
.upload-content {
	display: none;
	z-index: 10;
	position: relative;
	text-align: center;
	min-height: 500px;
}
#dropbox:hover {
	background-color: whitesmoke;
}

#dropbox p {
	position: absolute;
	top: 0; 
	left : 50%;
	transform: translate(-50%);
	left: 50%;
}
#rmtype-select {
	margin-bottom: 15px;
}
.upload-content .pic-upload {
	position: absolute;
	bottom: 100px;
	left: 50%;
	transform: translateX(-50%);
}
#addRmTypePic {
position: absolute;
bottom: 0;
left: 0;
}
</style>
<body>
	<div class="insert">
		<select id="rmtype-select">
			<option disabled selected>選擇要上傳照片的房型</option>
			<c:forEach var="rmtypevo" items="${rmtypeList}">
				<option value="${rmtypevo.rm_type}">${rmtypevo.type_name}</option>
			</c:forEach>
		</select>
		<div class="upload-content">
			<div class="pic-upload">
				<h6>
					<i class="icon fas fa-cloud-upload-alt"></i>上傳照片
				</h6>
				<input type="file" accept="image/*" name="rm_pic_upload"
					class="upload-pic" multiple />
			</div>

			<div class="preview" id="dropbox">
				<p>拖曳或點擊上傳</p>
			</div>
			<div id="status"></div>
			<button id="addRmTypePic" type="submit" class="send-data">確認送出</button>
		</div>
	</div>


	<script>
		var files;
		
		$("#addRmTypePic").click(function(){
				for (let i = 0; i < files.length; i++){
					upload(files[i]);
				}
				let preview = $("#dropbox");
				preview.children().remove();
		}); 
	
		$("#rmtype-select").change(function() {
			$(".upload-content").css("display", "block");
			$(".upload-content").css("z-index", "10");
		});

		window.onload = function() {
			var dropbox = document.getElementById("dropbox");
			var picFiles = new Array();
			dropbox.addEventListener("dragenter", noop, false);
			dropbox.addEventListener("dragleave", noopexit, false);
			dropbox.addEventListener("dragexit", noop, false);
			dropbox.addEventListener("dragover", noop, false);
			dropbox.addEventListener("drop", dropUpload, false);
		};

		function noop(event) {
			event.stopPropagation();
			event.preventDefault();
			dropbox.style.backgroundColor = "rgb(255, 150, 150)";
		}
		function noopexit(event) {
			event.stopPropagation();
			event.preventDefault();
			dropbox.style.backgroundColor = "transparent";
		}

		function dropUpload(event) {
			noopexit(event);
			files = event.dataTransfer.files;
			let preview = $("#dropbox");
			preview.children().remove();
            for (let i = 0; i < files.length; i++) {
                if (files[i].type.indexOf("image") >= 0) {
                    let reader = new FileReader();
                    reader.addEventListener("load", (ex) => {
                        let div = document.createElement("div");
                        let img = document.createElement("img");
                        img.src = ex.target.result;
                        img.classList.add("previewImg");
                        div.append(img);
                        preview.append(div);
                    });
                    reader.readAsDataURL(files[i]);
                } else {
                    window.close();
                }
            }
		}
		
		function upload(file) {
			document.getElementById("status").innerHTML = "Uploading ";
			let formData = new FormData();
			formData.append("file", file);
			formData.append("action", "addRmPic");
			formData.append("rm_type", document.getElementById("rmtype-select").value);
			var xhr = new XMLHttpRequest();
			xhr.upload.addEventListener("progress", uploadProgress, false);
			xhr.addEventListener("load", uploadComplete, false);
			xhr.open("POST",
					"${pageContext.request.contextPath}/RoomPicServlet", true); // If async=false, then you'll miss progress bar support.
			xhr.send(formData);
		}

		function uploadProgress(event) {
			// Note: doesn't work with async=false.
			var progress = Math.round((event.loaded / event.total) * 100);
			document.getElementById("status").innerHTML = "Progress "
					+ progress + "%";
		}

		function uploadComplete(event) {
			document.getElementById("status").innerHTML = event.target.responseText;
		}
	</script>
</body>
</html>
