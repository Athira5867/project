<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Cart</title>
</head>
<body>
<!-- i18n,Errors,Themes-spring MVC Forms -->


<form:form action="addCart">
Product Id:<form:input path="productId"/><br>
Product Name:<form:input path="productName"/><br>
Quantity:<form:input path="quantityOnHand"/><br>
Price:<form:input path="price"/><br>

</form:form>


</body>
</html>





