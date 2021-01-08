<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/backend/files/backend_header.file"%>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>selectPage</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/back/selectPage.css" />
</head>
<body>
<%@ include file="/backend/files/backend_sidebar.file"%>
 <div class="container">
 <button class="btn"><i class="fa fa-home">
                           <a href="<%=request.getContextPath()%>/backend/backend_index.jsp" >Home</a></i></button>
            <div class="card">
                <div clss="head">
                    <p><b>Diamond Resort Employee</b></p>
                </div>
                <div class="right">
                    <img id="company" src="${pageContext.request.contextPath}/img/company.png" width="150" />
                    <div>
                        <h4 class="mb-0">公司組織架構</h4>
                    </div>
                </div>
                <div class="right">
                    <a href='<%=request.getContextPath()%>/backend/emp/protected/listAllEmp.jsp'>
                    <img src="${pageContext.request.contextPath}/img/employee.png" width="150" />
                    </a>
                    <div>
                        <h4 class="mb-0">員工資料</h4>
                    </div>
                </div>
            </div>
            <div class="section">
                <div><a href="${pageContext.request.contextPath}/backend/dept/listAllDept.jsp">查詢公司部門總表</a></div>
                <div><a href="${pageContext.request.contextPath}/backend/title/listAllTitle.jsp">查詢公司職稱總表</a></div>
                <div><a href="${pageContext.request.contextPath}/backend/func/listAllFunc.jsp">查詢公司權限總表</a></div>
            </div>
        </div>
        <script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
        <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
        <script>
            $(document).ready(function () {
                $("#company").click(function () {
                    $(".section").show();
                });
            });
        </script>
   <%@ include file="/backend/files/backend_footer.file"%>
</body>
</html>