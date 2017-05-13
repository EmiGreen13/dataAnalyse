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

    <div id="error"></div>

    <div id="product"></div>


</body>
</html>
