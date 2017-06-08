<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<html>
<head>
    <title><spring:message code="Registration" text="Регистрация" /></title>

    <script src='${pageContext.request.contextPath}/js/jquery-1.4.2.min.js' type='text/javascript'></script>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <meta name="keywords" content="" />
    <meta name="description" content="" />

    <link href="css/templatemo_style.css" rel="stylesheet" type="text/css" />

    <link rel="stylesheet" type="text/css" href="css/ddsmoothmenu.css" />

    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/ddsmoothmenu.js"></script>

    <link rel="stylesheet" type="text/css" href="css/styles.css" />

</head>
<body id="home">

<!--!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! -->
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
        </div> <!-- END -->
    </div> <!-- END of header -->

    <div id="templatemo_menu" class="ddsmoothmenu">
        <ul>
            <li><a href="index" class="selected"><spring:message code="Main" text="Главная"/></a></li>
            <li><a href="about"><spring:message code="About" text="О нас"/></a></li>
            <li><a href="contact"><spring:message code="Contact" text="Контакты"/></a></li>
        </ul>
        <br style="clear: left" />
    </div>

    <div id="templatemo_middle">
        <img src="images/templatemo_image_01.png" alt="Image 01" />
        <h1><spring:message code="MainPage" text="Добро пожаловать в интернет-магазин"/></h1>
        <p><spring:message code="MainPageDescription" text="Здесь вы можете найти все, что Вам необходимо"/></p>
    </div>

    <div id="templatemo_main_top"></div>

    <div id="templatemo_main">


        <div id="authPanel">
            <h2><spring:message code="Registration" text="Регистрация"/></h2>

            <div id="auth">

                <c:if test="${not empty error}">
                    <div class="error">${error}</div>
                </c:if>
                <c:if test="${not empty msg}">
                    <div class="msg">${msg}</div>
                </c:if>
                <form name='registrationForm' method="post"
                      action = 'registration'>


                    <input name='username' type="text" required placeholder="<spring:message code="Username" text="Имя пользователя"/>" >

                    <input type='password' name='password1' required placeholder="<spring:message code="Password" text = "Пароль"/>" >

                    <input type='password' name='password2' required placeholder="<spring:message code="Password" text = "Пароль"/>" >

                    <input type='email' name='email' required placeholder="<spring:message code="Email" text = "Электронная почта" />" >

                    <input type="submit" name="subscribe" value="<spring:message code="Registration" text="Регистрация" />" id="comeIn" title="Subscribe" class="subscribebtn">

                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

                </form>
                <div class="cleaner h50"></div>
            </div>

        </div>

        <div class="cleaner"></div>
    </div> <!-- END of main -->

    <div id="templatemo_footer">

        <div class="cleaner h40"></div>
        <center>
            Copyright © 2017 Diploma work company
        </center>
    </div> <!-- END of footer -->

</div>

</body>
</html>
