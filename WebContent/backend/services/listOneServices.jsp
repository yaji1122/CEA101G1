<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
</style>
<title>Insert title here</title>
</head>
<body>
<h5> <a href="<%=request.getContextPath()%>/backend/services/select_page.jsp">回首頁</a> </h5>
	<table class="table table-striped table-dark">
		<thead>
			<tr>
				<th scope="col">服務編號</th>
				<th scope="col">服務名稱</th>
				<th scope="col">服務類型編號</th>
				<th scope="col">服務狀態代碼</th>
				<th scope="col">服務價格</th>
				<th scope="col">服務時長</th>
				<th scope="col">服務人員人數</th>
				<th scope="col">服務圖片</th>
				<th scope="col">服務介紹</th>
			</tr>

		</thead>
		<tbody>
			<tr>
				<th scope="row">${servicesVO.serv_no}</th>
				<td>${servicesVO.serv_name}</td>
				<td>${servicesVO.serv_type_no}</td>
				<td>${servicesVO.serv_status}</td>
				<td>${servicesVO.serv_price}</td>
				<td>${servicesVO.serv_dura}</td>
				<td>${servicesVO.serv_ppl}</td>
				<td><img src="<%=request.getContextPath()%>/ServicesServlet?servno=${servicesVO.serv_no}&action=getOneServicesPic" alt="" class="img-thumbnail" width="200px"></td>
				<td>${servicesVO.serv_info}</td>
			</tr>
		</tbody>
	</table>
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
</body>
</html>