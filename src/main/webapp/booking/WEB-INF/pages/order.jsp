<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title><spring:message code="Order" text="Заказ" /></title>
</head>
<body>

    <div>RRRRRRRRRR</div>

    <c:if test="${pageContext.request.remoteUser != null}">
        <div>${pageContext.request.remoteUser}</div>
        <div id="user"><a href="#" onclick="document.getElementById('logoutForm').submit();"><spring:message code="Logout" text="Logout" /></a></div>
    </c:if>

</body>
</html>
