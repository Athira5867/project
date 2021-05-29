<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<f:form action="searchProductById">
<table>
<tr><td>Product Id to search:</td><td><f:input path="productId"/></td></tr>
<tr><td>Product Name:</td><td><f:input path="productName"/></td></tr>
<tr><td>Product Quantity On Hand:</td><td><f:input path="quantityOnHand"/></td></tr>
<tr><td>Product Price:</td><td><f:input path="price"/></td></tr>
<tr><td><input type="submit" value="Fetch"></td>
<td><input type="submit" formaction="searchProductByIdForm" value="Reset"></td>
<td><input type="submit" formaction="saveProduct" value="update"></td>
<td><input type="submit" formaction="deleteProductById" value="Delete"></td>

</tr>


</table>
</f:form>
<hr>
<font color="red">${msg}</font>

</body>
</html>