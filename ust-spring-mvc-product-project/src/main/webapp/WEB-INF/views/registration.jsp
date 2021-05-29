<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js" integrity="sha384-SR1sx49pcuLnqZUnnPwx6FCym0wLsk5JZuNx2bPPENzswTNFaQU1RDvt3wT4gWFG" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.min.js" integrity="sha384-j0CNLUeiqtyaRmlzUHCPZ+Gy5fQu0dQ6eZ/xAww941Ai1SxSY+0EQqNXNE6DZiVc" crossorigin="anonymous"></script>
<script>
    function myFunction() {
        var pass1 = document.getElementById("pass").value;
        var pass2 = document.getElementById("rpass").value;
        if (pass1 != pass2) {
            alert("Passwords Do not match");
            document.getElementById("pass").style.borderColor = "#E34234";
            document.getElementById("rpass").style.borderColor = "#E34234";
        }
        else {
            alert("Passwords Match!!!");
            document.getElementById("regForm").submit();
        }
    }
</script>
</head>
<body>
<form:form id="regForm" method="POST" onsubmit="return myFunction()" action="/register" class="form-signin" modelAttribute="user">
<h3>Registration Page</h3>
<div class="form-group">
<br><br>
Username:<form:input path="username" class="form-control" placehoder="username"/><br><br>

Password:<form:input type="password" path="password" name="pass" id="pass" class="form-control" placehoder="password"/><br>
Confirm Password:<form:input type="password" name="rpass" id="rpass" path="confirmpassword" class="form-control" placehoder="password"/><br>
Email:<form:input path="mail" class="form-control" placehoder="email"/><br>
<button class="btn btn-primary" type="submit">Register</button>

</div>

</form:form>
<font color="red">${errorMsg}</font>
<font color="green">${msg}</font>
<font color="green">${message}</font>


</body>
</html>