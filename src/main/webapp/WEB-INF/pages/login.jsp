<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>

<html>
<head>
<title>Авторизация</title>

<link rel="stylesheet" href="css/style1.css" media="screen" type="text/css" />

   <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Web Store - FAQs</title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />

    <link href="css/templatemo_style.css" rel="stylesheet" type="text/css" />

    <link rel="stylesheet" type="text/css" href="css/ddsmoothmenu.css" />

    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/ddsmoothmenu.js">
    </script>

    <script type="text/javascript">

    ddsmoothmenu.init({
    	mainmenuid: "templatemo_menu", //menu DIV id
    	orientation: 'h', //Horizontal or vertical menu: Set to "h" or "v"
    	classname: 'ddsmoothmenu', //class added to menu's outer DIV
    	//customtheme: ["#1c5a80", "#18374a"],
    	contentsource: "markup" //"markup" or ["container_id", "path_to_menu_file"]
    })

    function clearText(field)
    {
        if (field.defaultValue == field.value) field.value = '';
        else if (field.value == '') field.value = field.defaultValue;
    }
    </script>


</head>
<body id="subpage" onload='document.loginForm.username.focus();'>


    <div id="templatemo_wrapper">
        <div id="templatemo_header">
            <div id="site_title">
                <h1><a href="#">Free Website Template</a></h1>
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
                <li><a href="index">Главная</a></li>
                <li><a href="products.html">Products</a>
                    <ul>
                        <li><a href="#">Sub menu 1</a></li>
                        <li><a href="#">Sub menu 2</a></li>
                        <li><a href="#">Sub menu 3</a></li>
                  </ul>
                </li>
                <li><a href="about.html">About</a>
                    <ul>
                        <li><a href="#">Sub menu 1</a></li>
                        <li><a href="#">Sub menu 2</a></li>
                        <li><a href="#">Sub menu 3</a></li>
                        <li><a href="#">Sub menu 4</a></li>
                        <li><a href="#">Sub menu 5</a></li>
                  </ul>
                </li>
                <li><a href="admin" class="selected">Админцентр</a></li>
                <li><a href="registration">Регистрация</a></li>
                <li><a href="contact.html">Contact</a></li>
                <li>
                <c:if test="${pageContext.request.remoteUser != null}">
                    <div>${pageContext.request.remoteUser}</div>
                    <div id="user"><a href="#" onclick="document.getElementById('logoutForm').submit();">
                        <spring:message code="Logout" text="Logout" /></a></div>
                </c:if>

                <form id="logoutForm" action="<c:url value="/j_spring_security_logout" />" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
                </li>

            </ul>
            <br style="clear: left" />
        </div> <!-- end of templatemo_menu -->
        
        <div class="cleaner h20"></div>
        <div id="templatemo_main_top_cont"></div>
        <div id="templatemo_main_aut">

           <div id="content" class="faq">   
	         <div id="login-form">

	        <fieldset>

                <c:if test="${not empty error}">
                    <div class="error">${error}</div>
                </c:if>
                <c:if test="${not empty msg}">
                    <div class="msg">${msg}</div>
                </c:if>

                <form name='loginForm' method="post" action = '<c:url value="/j_spring_security_check" />'>
	                <input name='username' type="text" required value="Логин" onBlur="if(this.value=='')this.value='Логин'" onFocus="if(this.value=='Логин')this.value='' ">
	                <input type='password' name='password' required value="Пароль" onBlur="if(this.value=='')this.value='Пароль'" onFocus="if(this.value=='Пароль')this.value='' ">
	                <input type="submit" value="ВОЙТИ">
	                <footer class="clearfix">
	                    <p><span class="info">?</span><a href="#">Забыли пароль?</a></p>
	                </footer>

                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

	            </form>
	        </fieldset>

	    </div>
<!--	
		<h3>Login with Username and Password</h3>

		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>

		<form name='loginForm' method="post"
			action = '<c:url value="/j_spring_security_check" />'>

			<table>
				<tr>
					<td>User:</td>
					<td><input type='text' name='username'></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type='password' name='password' /></td>
				</tr>
				<tr>
					<td colspan='2'><input name="submit" type="submit"
						value="submit" /></td>
				</tr>
			</table>

			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

			<a href="registration">Registration</a>

		</form>

		-->
           </div> <!-- END of content -->
            <div class="cleaner"></div>
        </div> <!-- END of main -->
        
        <div id="templatemo_footer_aut">
            <div class="col col_16">
                <h4>Pages</h4>
                <ul class="footer_menu">
                    <li><a href="#">Home</a></li>
                    <li><a href="#">Services</a></li>
                    <li><a href="#">Shipping</a></li>
                    <li><a href="#">Privacy</a></li>
                </ul>  
            </div>
            <div class="col col_16">
                <h4>Social</h4>
                <ul class="footer_menu">
                    <li><a href="#">Twitter</a></li>
                    <li><a href="#">Facebook</a></li>
                    <li><a href="#">Youtube</a></li>
                    <li><a href="#">LinkedIn</a></li> 
              </ul>  
            </div>
            <div class="col col_13 no_margin_right">
                <h4>About Us</h4>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur semper quam sit amet turpis rhoncus id venenatis tellus sollicitudin. Fusce ullamcorper, dolor non mollis pulvinar, turpis tortor commodo nisl. Validate <a href="http://validator.w3.org/check?uri=referer" rel="nofollow"><strong>XHTML</strong></a> &amp; <a href="http://jigsaw.w3.org/css-validator/check/referer" rel="nofollow"><strong>CSS</strong></a>.</p>
            </div>
            
            <div class="cleaner h40"></div>
            <center>
                Copyright © 2048 Your Company Name
            </center>
        </div> <!-- END of footer -->   
       
    </div>


<!--	<a href="?lang=en">English</a> | <a href="?lang=ru">Russian</a> -->
<!--

-->
</body>
</html>