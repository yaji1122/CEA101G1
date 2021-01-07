<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.meal.model.*"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/back/backend-meal.css" />
<title>新增餐點</title>
</head>
<style>
#preview {
	height: 300px;
	width: 80%;
	border: 1px solid #495464;
	margin: 0 auto;
	display: inline-flex;
	flex-direction: row;
	flex-wrap: wrap;
	overflow: scroll;
	justify-content: center;
}

img {
	width: 200px;
	margin: 5px;
	border: 2px solid transparent;
}
</style>	
<body>
		<form method="post" action="${pageContext.request.contextPath}/MealServlet" enctype="multipart/form-data">
			<div class="insert-meal-type">
			<label for="meal_type_no">
				<p>種類編號</p> <input type="text" name="meal_type_no" id="meal_type_no" maxlength="5"
				placeholder="請依照格式輸入(TYPXX)" required/>
			</label> <label for="meal_name">
				<p>餐點名稱</p> <input type="text" name="meal_name"
				placeholder="請輸入餐點名稱" required/>
			</label>
			<label for="price"><p>單價</p> <input type="text" name="price"
				placeholder="請輸入單價" required/>
			 </label>
			<label for="meal_info"><p>餐點介紹</p> <textarea name="meal_info"
			 maxlength="500" placeholder="最多500字"></textarea>
			 </label>
			 <label for="making_time"><p>預計製作時間</p> <select name="making_time" required>
					<option value="0" selected>0</option>
					<option value="5">5</option>
					<option value="10">10</option>
					<option value="15">15</option>
			</select>
			</label>
			<label for="meal_pic"><p>上傳餐點照片</p>
				<div class="pic-upload" name="pic-upload" for="meal_pic" style="background-color: #BEBEBE;">
					<h6>
						<i class="icon fas fa-cloud-upload-alt"></i>上傳照片
					</h6>
					<input type="file" name="meal_pic" accept="image/*" required/>
				</div>
			</label>
		</div>

		<input type="hidden" name="action" value="insert_meal">
		<button type="submit" class="btn btn-light" style="background-color: #BEBEBE;">確認送出</button>

		</form>
	</div>
</body>

</html>