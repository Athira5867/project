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
<!--  <div style="height:100px;width:400px;border:solid 2px orange;overflow:scroll;overflow-x:hidden;overflow-y:scroll;">
-->

<table border="5" cellspacing="10" align="Left">

<tbody>
<tr><th>ProductID</th><th>ProductName</th><th>QuantityOnHand</th><th>Price</th><th>Quantity</th></tr>
<c:forEach var="item" items="${products}">

<tr>
<td>${item.productId}</td>
<td>${item.productName}</td>
<td>${item.quantityOnHand}</td>
<td>${item.price}</td>
<td><input type="text" name="quantity" path="quantity"></td>
<td><a href="/addcart/${item.productId}">Add to cart</a></td>
<td><a href="/delete/${item.productId}">Delete</a></td>

</tr>
</c:forEach>


</tbody>
</table>
</div>




</body>
</html>