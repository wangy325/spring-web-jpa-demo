<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wangy325
  Date: 2020/3/16
  Time: 12:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
        <title>Register</title>
</head>
<body>

<h1>
        Register Form
</h1>

<form method="POST">
        First Name: <input id="firstName" type="text" name="firstName"/><br>
        Last Name: <input id="lastName" type="text" name="lastName"/><br>
        User Name: <input id="userName" type="text" name="username"/><br>
        Password: <input id="password" type="password" name="password"/><br>
        <input type="submit" value="Register">
</form>
</body>
</html>
