<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增機型</title>
</head>
<body>
	<form method="post" id="chopperform" enctype="multipart/form-data">
		<div class="insert">
			<label for="chop_name">機型名稱</label> <input type="text"
				name="chop_name" id="rm_type" maxlength="20"
				autocomplete="off" required /> <label for="chop_price">接送價格</label>
			<input type="number" name="chop_price" id="chop_price" maxlength="10"
				pattern="\d*" autocomplete="off" inputmode="numeric" required /> <label
				for="chop_info">機型介紹</label>
			<textarea name="chop_info" id="chop_info" maxlength="500"
				placeholder="最多500字" required></textarea>
			<div class="pic-upload">
				<h6>
					<i class="icon fas fa-cloud-upload-alt"></i>上傳照片
				</h6>
				<input type="file" accept="image/*" name="chop_pic" class="upload-pic" required/>
			</div>
			<div class="preview"></div>
		</div>
		<input name="action" value="insert_chopper" style="display: none" />
		<button type="submit" class="send-data">確認送出</button>
	</form>

	<script>
	$(document).ready(function () {
			
            let formElem = document.querySelector("#chopperform");
            formElem.addEventListener("submit", (e) => {
                e.preventDefault();
               
                let data = new FormData(formElem);
                let xhr = new XMLHttpRequest();
                xhr.open("post", "${pageContext.request.contextPath}/ChopperServlet");
                xhr.onload = function () {
                    if (xhr.readyState === xhr.DONE) {
                        if (xhr.status === 200) {
                            if (xhr.responseText === "success") {
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
                            } 
                            }
                        }
                    }
                xhr.send(data);
            });
            //General Upload Pic Preview js
            let upload = $(".upload-pic");
            upload.change(function(){
            	if (this.files) {
                    files = this.files;
                    let preview = $(this).parents(".pic-upload").next();
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
            })
		});
        </script>
</body>
</html>