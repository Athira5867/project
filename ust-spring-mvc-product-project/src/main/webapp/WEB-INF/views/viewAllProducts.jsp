<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Product Details</h1>
<a href="addProduct">Add Product</a>
<!-- <a href="searchProductByIdForm">Update</a> -->
<a href="index">Home</a>
<h1>${message} </h1>
<table border="5" cellspacing="10" align="center">
<tbody>
<tr><th>ProductID</th><th>ProductName</th><th>QuantityOnHand</th><th>Price</th></tr>
<c:forEach var="item" items="${products}">
 <c:if test="${item.quantityOnHand ne 0}">
 <tr>
<td>${item.productId}</td>
<td>${item.productName}</td>
<td>${item.quantityOnHand}</td>
<td>${item.price}</td>
<td><a href="/delete/${item.productId}">Delete</a></td>
<td><a href="searchProductByIdForm">Update</a></td>

</tr>
</c:if>
</c:forEach>


</tbody>
</table>

</body>
</html>