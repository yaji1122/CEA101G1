<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增房型</title>
</head>
<body>
	<form method="post" id="rmtypeform" enctype="multipart/form-data">
		<div class="insert">
			<label for="rm_type">房型編號</label> <input type="text" name="rm_type"
				class="rm-input" id="rm_type" pattern="\d*" maxlength="1"
				autocomplete="off" placeholder="請選擇1-9的數字" required /> <label
				for="type_name">房型名稱</label> <input type="text" name="type_name"
				class="rm-input" id="type_name" maxlength="20" autocomplete="off"
				required /> <label for="type_eng_name">英文名稱</label> <input
				type="text" name="type_eng_name" class="rm-input" id="type_eng_name"
				maxlength="10" autocomplete="off" required /> <label for="rm_price">房型價格</label>
			<input type="text" name="rm_price" class="rm-input" pattern="\d*"
				id="rm_price" maxlength="10" autocomplete="off" required /> <label
				for="rm_capacity">最大入住人數</label> <select name="rm_capacity"
				class="rm-select" id="rm_capacity" required>
				<option value="2">2</option>
				<option value="4">4</option>
				<option value="6">6</option>
			</select> <label for="rm_info_title">內容標題</label> <input name="rm_info_title"
				class="rm-input" id="rm_info_title" maxlength="40"
				placeholder="輸入40字以內標題" required> <label for="rm_info">介紹內容</label>
			<textarea name="rm_info" class="rm-input" id="rm_info"
				maxlength="500" placeholder="最多500字" required></textarea>
			<div class="pic-upload">
				<h6>
					<i class="icon fas fa-cloud-upload-alt"></i>上傳照片
				</h6>
				<input type="file" accept="image/*" name="rm_pic" class="upload-pic" multiple />
			</div>
			<div class="preview"></div>
		</div>
		<input name="action" value="insert_rm_type" style="display: none" />
		<button type="submit" class="send-data">確認送出</button>
	</form>

	<script>
	$(document).ready(function () {
			
            let formElem = document.querySelector("#rmtypeform");
            formElem.addEventListener("submit", (e) => {
                e.preventDefault();
               
                let data = new FormData(formElem);
                let xhr = new XMLHttpRequest();
                xhr.open("post", "${pageContext.request.contextPath}/RoomTypeServlet");
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