<%--
  Created by IntelliJ IDEA.
  User: wangy325
  Date: 2020/3/16
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
        <title>Profile</title>
</head>
<body>
<h1> Your Profile</h1>

<c:out value="${Spitter.username}"/><br>
<c:out value="${Spitter.firstName}"/>
<c:out value="${Spitter.lastName}"/>
</body>
</html>
