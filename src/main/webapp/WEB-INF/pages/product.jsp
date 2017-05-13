<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Тип</title>
    <script src='${pageContext.request.contextPath}/js/jquery-1.4.2.min.js' type='text/javascript'></script>
    <script src='${pageContext.request.contextPath}/js/task3.js' type='text/javascript'></script>
</head>
<body>


    <div id="error"></div>

<div>
    <select id = "countries" onchange="changeCountry()"></select>
</div>
<div>
    <select id = "areas" onchange="changeArea()" ></select>
</div>
<div>
    <select id = "cities"></select>
</div>


</body>
</html>
