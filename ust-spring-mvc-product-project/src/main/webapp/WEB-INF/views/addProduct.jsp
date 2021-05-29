<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<!-- i18n,Errors,Themes-spring MVC Forms -->
<h2>Please fill the product details:</h2>

<form:form action="saveProduct">
Product Id:<form:input path="productId"/><br>
Product Name:<form:input path="productName"/><br>
Quantity:<form:input path="quantityOnHand"/><br>
Price:<form:input path="price"/><br>
<input type="submit" value="Save Product">
</form:form>


</body>
</html>
 
<!--  <form action="saveProduct">
Product Id: <input type="text" name="productId"></br>
Product Name: <input type="text" name="productName"></br>
Quantity_In_Hand: <input type="text" name="quantityOnHand"></br>
Price: <input type="text" name="price"></br>

<input type="submit" value="Save Product"></form>-->




</form>
</body>
</html>