<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.service_type.model.*"%>

<%
ServiceTypeService serviceTypeSvc = new ServiceTypeService();
List<ServiceTypeVO> list = serviceTypeSvc.getAll();
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>服務類型</title>
</head>
<body>
<a href="<%=request.getContextPath()%>/backend/serviceType/addServiceType.jsp">新增服務類型</a><br>
	<table>
		<thead>
			<tr>
				<th scope="col">服務類型編號</th>
				<th scope="col">服務類型名稱</th>
				<th scope="col">修改</th>
				<th scope="col">刪除</th>
			</tr>

		</thead>
		<tbody>

			<%@ include file="/backend/files/page1.file"%>
			<c:forEach var="serviceTypeVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">

				<tr>
					<th scope="row">${serviceTypeVO.serv_type_no}</th>
					<td>${serviceTypeVO.serv_type_name}</td>
					

					<td>
						<FORM METHOD="post"
							ACTION="${pageContext.request.contextPath}/ServiceTypeServlet"
							style="margin-bottom: 0px;">
							<input type="submit" value="修改"> <input type="hidden"
								name="serv_type_no" value="${serviceTypeVO.serv_type_no}"> <input
								type="hidden" name="action" value="getOne_For_Update">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post"
							ACTION="${pageContext.request.contextPath}/ServiceTypeServlet"
							style="margin-bottom: 0px;">
							<input type="submit" value="刪除"> <input type="hidden"
								name="serv_type_no" value="${serviceTypeVO.serv_type_no}"> <input
								type="hidden" name="action" value="delete">
						</FORM>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<%@ include file="/backend/files/page2.file"%>

</body>
</html>