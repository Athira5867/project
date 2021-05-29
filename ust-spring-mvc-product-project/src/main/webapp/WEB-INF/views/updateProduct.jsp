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
<h2>Please fill the product details:</h2>
<form action="saveProduct">
Product Id: <input type="text" name="productId"></br>
Product Name: <input type="text" name="productName"></br>
Quantity_In_Hand: <input type="text" name="quantityOnHand"></br>
Price: <input type="text" name="price"></br>
<tr><td><input type="submit" formaction="searchProductById" value="Fetch"></td>

<input type="submit" value="Save Product"></form>




</form>

</body>
</html>