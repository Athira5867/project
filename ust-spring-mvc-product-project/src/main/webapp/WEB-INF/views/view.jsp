<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<a href="/clientHome">Home</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="/viewCart">View Cart</a>
<%-- <h1>${message }</h1> --%>
<h1>${fullCart}</h1>
<h1>${msg}</h1>
<body>
<h1>Product Details</h1>
<!--  <div style="height:100px;width:400px;border:solid 2px orange;overflow:scroll;overflow-x:hidden;overflow-y:scroll;">
-->

<table border="5" cellspacing="10" align="Left">

<tbody>
<tr><th>ProductID</th><th>ProductName</th><th>QuantityOnHand</th><th>Quantity</th><th>Price</th></tr>
<c:forEach var="item" items="${products}">
<c:if test="${item.quantityOnHand ne 0}">
<tr>
<form name="modelDetail1" method="post" action="/addtocart">

<td><font size="3" face="Verdana, Arial, sans-serif"><strong>Product Id:</strong>${item.productId}</font>
<input type="hidden" name="productId" value="${item.productId}"></td>
<td><p><font size="3" face="Verdana, Arial, sans-serif"><strong>Product Name:</strong>${item.productName}</font>
<input type="hidden" name="productName" value="${item.productName}"></p></td>

<td><p><font size="3" face="Verdana, Arial, sans-serif"><strong>Quantity on hand:</strong>${item.quantityOnHand}</font>
<input type="hidden" name="quantityOnHand" value="${item.quantityOnHand}"></p></td>

<td><p><font size="3" face="Verdana, Arial, sans-serif"><strong>Enter Quantity:
<input required type="text" name="quantity" size="2"></strong></font></p></td>

<td><p><font size="3" face="Verdana, Arial, sans-serif"><strong>Price</strong>${item.price}</font>
<input type="hidden" name="price" value="${item.price}"></p></td>
<td><input type="hidden" name="action" value="add"><input type="submit" name="addcart" value="Add to cart">

</td>

</form>

</tr>
</c:if>
</c:forEach>


</tbody>
</table>
</div>




</body>
</html>