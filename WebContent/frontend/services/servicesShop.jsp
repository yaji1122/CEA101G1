<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>Mode II 範例程式 - Eshop.jsp</title>
</head>
<body bgcolor="#FFFFFF">
<img src="images/tomcat.gif"> <font size="+3">網路書店</font>
<hr><p>

<table border="1" width="740">  
  <tr bgcolor="#999999"> 
    <th width="200">服務名稱</th><th width="100">價格</th><th width="100">數量</th><th width="100">地點</th>
		<th width="120"></th>
  </tr>

 <!--  
       第一種action寫法: <form name="shoppingForm" action="Shopping.html" method="POST">
       第二種action寫法: <form name="shoppingForm" action="/IBM_MVC/Shopping.html" method="POST">
       第三種action寫法: <form name="shoppingForm" action="<%=request.getContextPath()%>/Shopping.html" method="POST">
 -->
 <!-- 
      當某網頁可能成為被forward的網頁時, 此網頁內的所有html連結 , 如果採用相對路徑寫法時, 因為會被加上原先forward者的路徑
      在更複雜的MVC架構中, 上面第三種寫法, 先以request.getContextPath()方法, 先取得環境(Servlet Context)目錄路徑的寫法,
      將是最佳解決方案
 -->
  
  <form name="shoppingForm" action="${pageContext.request.contextPath}/ServicesCartServlet" method="POST">
    <tr> 
      <td width="200"><div align="center">不一樣的養生法</div></td>
      <td width="100"><div align="center">吳永志</div></td>
      <td width="100"><div align="center">基峰</div></td>
      <td width="100"><div align="center">600</div></td>
      <td width="120"><div align="center">數量： <input type="text" name="quantity" size="3" value=1></div></td>
      <td width="120"><div align="center">      <input type="submit" name="Submit" value="放入購物車"></div></td>
    </tr>
      <input type="hidden" name="name" value="不一樣的養生法">
      <input type="hidden" name="author" value="吳永志">
      <input type="hidden" name="publisher" value="基峰">
      <input type="hidden" name="price" value="600">
      <input type="hidden" name="action" value="ADD">	
  </form>
  
  <form name="shoppingForm" action="${pageContext.request.contextPath}/ServicesCartServlet" method="POST">
    <tr> 
      <td width="200"><div align="center">哈利波特-神秘的魔法石</div></td>
      <td width="100"><div align="center">J.K 羅琳</div></td>
      <td width="100"><div align="center">皇冠</div></td>
      <td width="100"><div align="center">200</div></td>
      <td width="120"><div align="center">數量： <input type="text" name="quantity" size="3" value=1></div></td>
      <td width="120"><div align="center">      <input type="submit" name="Submit" value="放入購物車"></div></td>
    </tr>
      <input type="hidden" name="name" value="哈利波特-神秘的魔法石">
      <input type="hidden" name="author" value="J.K 羅琳">
      <input type="hidden" name="publisher" value="皇冠">
      <input type="hidden" name="price" value="200">
      <input type="hidden" name="action" value="ADD">
  </form>
  
  <form name="shoppingForm" action="${pageContext.request.contextPath}/ServicesCartServlet" method="POST">
    <tr> 
      <td width="200"><div align="center">麻辣女教師</div></td>
      <td width="100"><div align="center">蕭蓉蓉</div></td>
      <td width="100"><div align="center">平安</div></td>
      <td width="100"><div align="center">190</div></td>
      <td width="120"><div align="center">數量： <input type="text" name="quantity" size="3" value=1></div></td>
      <td width="120"><div align="center">      <input type="submit" name="Submit" value="放入購物車"></div></td>
    </tr>
      <input type="hidden" name="name" value="麻辣女教師">
      <input type="hidden" name="author" value="蕭蓉蓉">
      <input type="hidden" name="publisher" value="平安">
      <input type="hidden" name="price" value="190">
      <input type="hidden" name="action" value="ADD">
  </form>
  
  <form name="shoppingForm" action="Shopping.html" method="POST">
    <tr> 
      <td width="200"><div align="center">把話說到心窩裡</div></td>
      <td width="100"><div align="center">劉墉</div></td>
      <td width="100"><div align="center">水雲齋</div></td>
      <td width="100"><div align="center">180</div></td>
      <td width="120"><div align="center">數量： <input type="text" name="quantity" size="3" value=1></div></td>
      <td width="120"><div align="center">      <input type="submit" name="Submit" value="放入購物車"></div></td>
    </tr>
      <input type="hidden" name="name" value="把話說到心窩裡">
      <input type="hidden" name="author" value="劉墉">
      <input type="hidden" name="publisher" value="水雲齋">
      <input type="hidden" name="price" value="180">
      <input type="hidden" name="action" value="ADD">
  </form>
  
  <form name="shoppingForm" action="Shopping.html" method="POST">
    <tr> 
      <td width="200"><div align="center">一個人聖經</div></td>
      <td width="100"><div align="center">高行健</div></td>
      <td width="100"><div align="center">聯經</div></td>
      <td width="100"><div align="center">300</div></td>
      <td width="120"><div align="center">數量： <input type="text" name="quantity" size="3" value=1></div></td>
      <td width="120"><div align="center">      <input type="submit" name="Submit" value="放入購物車"></div></td>
    </tr>
      <input type="hidden" name="name" value="一個人聖經">
      <input type="hidden" name="author" value="高行健">
      <input type="hidden" name="publisher" value="聯經">
      <input type="hidden" name="price" value="300">
      <input type="hidden" name="action" value="ADD">
  </form>

</table>
<p> 
<%--   <jsp:include page="/Cart.jsp" flush="true" /> --%>
</body>
</html>