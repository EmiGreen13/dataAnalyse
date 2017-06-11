<%@ page import="entity.Basket" %>
<%@ page import="org.codehaus.jackson.map.ObjectMapper" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title><spring:message code="Basket" text="Корзина" /></title>

    <script src='${pageContext.request.contextPath}/js/jquery-1.4.2.min.js' type='text/javascript'></script>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Web Store</title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />

    <link href="css/templatemo_style.css" rel="stylesheet" type="text/css" />

    <link rel="stylesheet" type="text/css" href="css/ddsmoothmenu.css" />
    <link rel="stylesheet" type="text/css" href="css/table.css" />
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/ddsmoothmenu.js"></script>

    <link rel="stylesheet" type="text/css" href="css/styles.css" />

</head>

<script type="application/javascript">

    $(document).ready(function() {
        loadProducts();
    });

    function loadProducts(){
        $.ajax({
            type: 'GET',
            url: 'show_basket',
            dataType: 'json',
            success: function (result) {
                showProducts(result);
            },
            error: function (msg) {
                var error = JSON.parse(msg.responseText);
                alert(error);
                return false;
            }
        });
    }

    function deleteProductFromBasket(productToPriceId){

        $.ajax({
            type: 'GET',
            url: 'delete_product_from_basket',
            data: {'productToPriceId': productToPriceId},
            dataType: 'json',
            success: function () {
                loadProducts();
            },
            error: function (msg) {
                var error = JSON.parse(msg.responseText);
                alert(error);
                return false;
            }
        });


    }

    function showProducts(products) {

        var selector = $("#auth");
        selector.empty();

        var table = $('<table id="cardProducts"></table>').addClass('table_blur');
        var row = $('<th><spring:message code="ProductCode" text="Код товара"/></th><th><spring:message code="ProductTitle" text="Название товара"/></th><th><spring:message code="ProductCount" text="Количество товара"/></th><th><spring:message code="ProductPrice" text="Цена товара"/></th><th><spring:message code="ProductTotal" text="Итого"/></th><th></th>');
        table.append(row);

        var productToPrice = null;
        var rowData = null;
        var total = 0;
        for(var index = 0; index < products.length; index++){

            productToPrice = products[index]['productToPrice'];

            row = $('<tr id="productToPriceId' + productToPrice['productToPriceId'] + '"></tr>');

            rowData = $('<td>' + productToPrice['hierarchyCode'] + '</td><td>' + productToPrice['description'] + '</td><td>' + products[index]['count'] + '</td><td>' +
                    productToPrice['price']+ '</td><td>' + Math.round(productToPrice['price'] * products[index]['count']*100)/100 + '</td><td>'+
                    '<a href="javascript:void(0);" onclick="deleteProductFromBasket(' + productToPrice['productToPriceId'] + ')" >' + '<spring:message code="RemoveProduct" text="Удалить"/>' + '</a>' + '</td>');

            total = total + productToPrice['price'] * products[index]['count'];

            row.append(rowData);
            table.append(row);
        }
        rowData = $('<td></td><td></td><td></td><td></td><td>' + Math.round(total*100)/100 + '</td><td></td>');
        row = $('<tr></tr>');
        row.append(rowData);
        table.append(row);
        selector.append(table);
    }
</script>

<body id="home">

<input type="hidden" id="hdnSession" data-value="@Request.RequestContext.HttpContext.Session['basket']" />


<form id="logoutForm" action="<c:url value="/j_spring_security_logout" />" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<div id="templatemo_wrapper">
    <div id="templatemo_header">
        <div id="site_title">
            <h1><a href="#"></a></h1>
        </div>

        <div id="header_right">
            <ul id="language">
                <li><a href="?lang=en"><img src="images/usa.png" alt="English" /></a></li>
                <li><a href="?lang=ru"><img src="images/Russia.png" alt="Russian" /></a></li>
            </ul>
            <div class="cleaner"></div>
            <div id="templatemo_search">
                <form action="#" method="get">
                    <input type="text" value="Search" name="keyword" id="keyword" title="keyword" onfocus="clearText(this)" onblur="clearText(this)" class="txt_field" />
                    <input type="submit" name="Search" value="" alt="Search" id="searchbutton" title="Search" class="sub_btn"  />
                </form>
            </div>
        </div>
    </div>

    <div id="templatemo_menu" class="ddsmoothmenu">
        <ul>
            <li><a href="index" class="selected"><spring:message code="Main" text="Главная"/></a></li>
            <li><a href="about"><spring:message code="About" text="О нас"/></a></li>
            <li><a href="contact"><spring:message code="Contact" text="Контакты"/></a></li>
            <c:if test="${pageContext.request.remoteUser == null}">
                <li><a href="login"><spring:message code="Login" text="Вход"/></a></li>
                <li><a href="registration"><spring:message code="Registration" text="Регистрация"/></a></li>
            </c:if>
            <div class="toRight">
                <c:if test="${pageContext.request.remoteUser != null}">
                    <li><a href="profile">${pageContext.request.remoteUser}</a></li>
                    <li><a href="#" onclick="document.getElementById('logoutForm').submit();"><spring:message code="Logout" text="Выход"/></a></li>
                </c:if>
            </div>
        </ul>
        <br style="clear: left" />
    </div> <!-- end of templatemo_menu -->

    <div id="templatemo_middle">
        <img src="images/templatemo_image_01.png" alt="Image 01" />
        <h1><spring:message code="MainPage" text="Добро пожаловать в интернет-магазин"/></h1>
        <p><spring:message code="MainPageDescription" text="Здесь вы можете найти все, что Вам необходимо"/></p>
    </div>

    <div id="templatemo_main_top"></div>
    <div id="templatemo_main">

        <div id="auth">

            <%--<div class="col col_14 product_gallery">--%>
            <%--<a href="productdetail.html"><img src="images/product/01.jpg" alt="Product 01" /></a>--%>
            <%--<h3>Ut eu feugiat</h3>--%>
            <%--<p class="product_price">$ 100</p>--%>
            <%--<a href="shoppingcart.html" class="add_to_cart">Add to Cart</a>--%>
            <%--</div>--%>

        </div> <!-- END of content -->
        <div class="cleaner"></div>
    </div>

    <div id="templatemo_footer">

        <div class="cleaner h40"></div>
        <center>
            Copyright © 2017 Diploma work company
        </center>
    </div>

</div>


</body>
</html>
