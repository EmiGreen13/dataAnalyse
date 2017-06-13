<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<html>
<head>
    <title><spring:message code="Admin" text="Управление" /></title>

    <script src='${pageContext.request.contextPath}/js/jquery-1.4.2.min.js' type='text/javascript'></script>



    <!--  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! -->

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <meta name="keywords" content="" />
    <meta name="description" content="" />

    <link href="css/templatemo_style.css" rel="stylesheet" type="text/css" />
    <link href="css/table.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="css/ddsmoothmenu.css" />
    <script type="text/javascript" src="js/ddsmoothmenu.js"></script>

    <link rel="stylesheet" type="text/css" href="css/styles.css" />

</head>



<script type="application/javascript">

    function getMonthStatistics(month) {
        $.ajax({
            type: 'GET',
            url: 'get_year_selected_month_statistics_user',
            data: {'month': month},
            dataType: 'json',
            success: function (result) {
                setYearStatisticsInSelectedMonth(result);
            },
            error: function (msg) {
                var error = JSON.parse(msg.responseText);
                alert(error);
                return false;
            }
        });
    }

    function setYearStatisticsInSelectedMonth(statistics) {
        var selector = $("#month_detailed");
        selector.empty();


        var table = $('<table></table>').addClass('table_blur');
        var row = $('<th><spring:message code="ProductCode" text="Код товара"/></th><th><spring:message code="ProductTitle" text="Название товара"/></th><th><spring:message code="ProductPrice" text="Цена"/></th><th><spring:message code="ProductCount" text="Количество"/></th>' +
                '<th><spring:message code="YearStatisticsReceipts" text="Выручка"/></th><th><spring:message code="Date" text="Дата"/></th>'
        );
        table.append(row);

        var rowData = null;
        var total = 0;
        for(var index = 0; index < statistics.length; index++){

            row = $('<tr></tr>');

            rowData = $('<td>' + statistics[index]['hierarchyCode'] + '</td><td>' + statistics[index]['description'] + '</td><td>' + statistics[index]['price'] + '</td><td>'
            + statistics[index]['quantity'] + '</td><td>' + statistics[index]['receipts'] + '</td><td>' + statistics[index]['date'] + '</td>');

            total = total + statistics[index]['receipts'];

            row.append(rowData);
            table.append(row);
        }
        rowData = $('<td></td><td></td><td></td><td></td><td>' + Math.round(total*100)/100 + '</td>');
        row = $('<tr></tr>');
        row.append(rowData);
        table.append(row);
        selector.append(table);
    }


    $(document).ready(function() {

        var productCount = 0;

        $.ajax({
            type: 'GET',
            url: 'get_year_month_statistics',
            // data: {'id': id, 'first': first, 'last': last},
            dataType: 'json',
            success: function (result) {
                setYearStatisticsPerMonth(result);
            },
            error: function (msg) {
                var error = JSON.parse(msg.responseText);
                alert(error);
                return false;
            }
        });

        $.ajax({
            type: 'GET',
            url: 'get_square_trand',
            // data: {'id': id, 'first': first, 'last': last},
            dataType: 'json',
            success: function (result) {
                setYearStatisticsTrand(result);
            },
            error: function (msg) {
                var error = JSON.parse(msg.responseText);
                alert(error);
                return false;
            }
        });

    });

    function setYearStatisticsTrand(statistics) {

        var selector = $("#trand_detailed");
        selector.empty();

        var table = $('<table></table>').addClass('table_blur');
        var row = $('<th>Месяц</th><th>Выручка</th><th>Скользящая средняя</th>');
        table.append(row);

        var rowData = null;
        for(var index = 0; index < statistics.length; index++){

            row = $('<tr></tr>');

            rowData = $('<td>' + statistics[index]['month'] + '</td><td>' + statistics[index]['receipts'] + '</td><td>' + statistics[index]['avg'] + '</td>');

            row.append(rowData);
            table.append(row);
        }
        selector.append(table);
    }

    function setYearStatisticsPerMonth(statistics) {

        var selector = $("#statistics");
        selector.empty();

        var table = $('<table></table>').addClass('table_blur');
        var row = $('<th><spring:message code="YearStatisticsMonth" text="Месяц"/></th><th><spring:message code="YearStatisticsReceipts" text="Выручка"/></th>');
            table.append(row);

            var productToPrice = null;
            var rowData = null;
            var total = 0;
            for(var index = 0; index < statistics.length; index++){

                row = $('<tr></tr>');

                rowData = $('<td><a onclick="getMonthStatistics(' + statistics[index]['month'] + ')">' + statistics[index]['month'] + '</a></td><td>' + statistics[index]['receipts'] + '</td>');

                total = total + statistics[index]['receipts'];

                row.append(rowData);
                table.append(row);
            }
            rowData = $('<td></td><td>' + Math.round(total*100)/100 + '</td>');
            row = $('<tr></tr>');
            row.append(rowData);
            table.append(row);
            selector.append(table);
    }

</script>



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

            <div id="statistics">


            </div>

            <div id="month_detailed">

            </div>

            <div id="user_detailed">

            </div>

            <div id="trand_detailed">

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
