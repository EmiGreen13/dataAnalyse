<%@ page import="entity.Basket" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title><spring:message code="MainPage" text="Welcome to E-Catalog" /></title>

    <script src='${pageContext.request.contextPath}/js/jquery-1.4.2.min.js' type='text/javascript'></script>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Web Store</title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />

    <link href="css/templatemo_style.css" rel="stylesheet" type="text/css" />

    <link rel="stylesheet" type="text/css" href="css/ddsmoothmenu.css" />

    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/ddsmoothmenu.js"></script>

    <link rel="stylesheet" type="text/css" href="css/styles.css" />

</head>

<script type="application/javascript">

    function setProducts(products) {

        var parser=new DOMParser();
        var xmlDoc;
        var product = {};
        var $xml;
        var selector = $("#content");

        selector.empty();

        for(var index = 0; index < products.length; index++){

            xmlDoc=parser.parseFromString(products[index]['content'],"text/html");
            $xml = $(xmlDoc);
            var image = $xml.find('img');

            if (image != null){

                product.pushButton = '<a class="add_to_cart" href="javascript:void(0);" onclick="addToBasket(' + products[index]['hierarchyId'] + ',' + '\'#num_count' + products[index]['hierarchyId'] + '\')"><spring:message code="AddToCard" text="Добавить в корзину"/></a>';
                product.price = '<p class="product_price">150 руб</p>';

                product.counter = '<div class="input_group_quantity_goods">'
                        +'<input type="number" step="1" min="1" max="16" id="num_count' + products[index]["hierarchyId"].toString() + '" name="quantity" value="1" title="Qty">'
                        +'</div>';

                if(products[index]['description'] != null){
                    product.h3 = '<h3>' + products[index]['description'] + '</h3>';

                }
                else{
                    product.h3 = '<h3><spring:message code="NoTitle" text="Нет названия"/></h3>';
                }

                product.img = '<a href = "product?productId=' + products[index]["productId"] + '" >' + products[index]['content'] + '</a>';

                selector.append('<div class="col col_14 product_gallery">'
                        + product.img
                        + product.h3
                        + product.price
                        <c:if test="${pageContext.request.remoteUser != null}">
                            + product.counter
                            + product.pushButton
                        </c:if>
                        <c:if test="${pageContext.request.remoteUser == null}">
                            + '<spring:message code="JustAuthorizedUsersCanMakeanOrder" text="Пожалуйста, авторизуйтесь для выполнения покупки"/>'
                        </c:if>

                        +
                        '</div>')
            }
        }
    }

</script>

<body id="home">

<input type="hidden" id="hdnSession" data-value="@Request.RequestContext.HttpContext.Session['basket']" />

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

        <div id="sidebar">

            <h3><a href="basket"><spring:message code="Basket" text="Корзина"/> (<span id="basket">0</span>)</a></h3>


            <h3><spring:message code="Catalog" text="Каталог"/></h3>

            <div id ="sidebar_menu">


                <div id="catalogs"></div>

                <script src='${pageContext.request.contextPath}/js/index.js' type='text/javascript'></script>


                <form id="logoutForm" action="<c:url value="/j_spring_security_logout" />" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </div>
        </div>

        <div id="content">

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
