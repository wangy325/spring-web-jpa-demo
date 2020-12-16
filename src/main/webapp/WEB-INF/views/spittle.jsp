<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wangy325
  Date: 2020/3/15
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
        <title>Spittle</title>
</head>
<body>
<h1>Recent Spittle</h1>
<c:out value="${spittle.message}"/><br>
<c:out value="${spittle.time}"/>

</body>
</html>
