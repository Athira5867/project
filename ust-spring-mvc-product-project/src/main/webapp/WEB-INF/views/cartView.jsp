<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table border="5" cellspacing="10" align="center">
<tbody>
<tr><th>Cart id</th><th>ProductID</th><th>ProductName</th><th>Quantity</th><th>Price</th></tr>

<c:forEach var="item" items="${cart}">
<%-- <f:form action="/deleteCart" method="post"> --%>
<tr>

<td>${item.cartId}</td><td><f:input path="productId"/></td>
<td>${item.productId}</td>
<td>${item.productName}</td>
<td>${item.quantity}</td>
<td>${item.price}</td><td>
 <a href="/deleteCart/${item.cartId}">Delete</a></td>
 <!-- <td><input type="submit" formaction="update" value="Update"></td>  -->

</tr>
<%-- </f:form> --%>
</c:forEach>

</tbody>
</table>
</body>
</html>