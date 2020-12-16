<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wangy325
  Date: 2020/3/14
  Time: 21:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
        <title>Recent Spittles</title>
</head>
<body>
<h1>Recent Spittles</h1>
<c:forEach items="${spittleList}" var="spittle">
        <li id="spittle_c<c:out value="${spittle.id}"/>">
                <div class="spittleMessage">
                        <c:out value="${spittle.message}"/>
                </div>
                <div style="font-size: small">
                        <span class="spittleTime">
                                <c:out value="${spittle.time}"/>
                        </span>
                        <span class="spittleLocate">
                                (<c:out value="${spittle.latitude}"/>,
                                <c:out value="${spittle.longitude}"/>)
                        </span>
                </div>
        </li>
</c:forEach>

</body>
</html>
