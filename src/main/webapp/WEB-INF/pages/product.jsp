<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Товар</title>
    <script src='${pageContext.request.contextPath}/js/jquery-1.4.2.min.js' type='text/javascript'></script>
    <script src='${pageContext.request.contextPath}/js/product.js' type='text/javascript'></script>
</head>
<body>

<script type="text/javascript">

    var hierarchyId='${param.hierarchyId}';

</script>

<a href="?hierarchyId=${param.hierarchyId}&lang=en">English</a> | <a href="?hierarchyId=${param.hierarchyId}&lang=ru">Russian</a>

<input type="button" value="Кнопка" onClick='location.href="http://codehelper.ru/"'>

<c:if test="${pageContext.request.remoteUser != null}">
    <div>${pageContext.request.remoteUser}</div>
    <div id="user"><a href="#" onclick="document.getElementById('logoutForm').submit();"><spring:message code="Logout" text="Logout" /></a></div>
</c:if>

<form id="logoutForm" action="<c:url value="/j_spring_security_logout" />" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

    <div id="error"></div>

    <div id="product"></div>

    <div id="productContent"></div>



</body>
</html>
