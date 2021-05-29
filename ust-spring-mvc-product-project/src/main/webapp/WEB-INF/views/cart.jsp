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
<form:form action="saveCart">

Succces ...${message}<br><br> 
 <%-- ${fullCart}  --%>

<a href="/">Home</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="/viewCart">View Cart</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="view">View All Products</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</form:form>

</body>
</html>