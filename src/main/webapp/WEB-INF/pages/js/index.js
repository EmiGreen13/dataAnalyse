$(document).ready(function() {

    var id = 1;
    var first = null;
    var last = null;

    $.ajax({
        type: 'GET',
        url: 'load_catalogs',
        data: {'id': id, 'first': first, 'last': last},
        dataType: 'json',
        success: function (result) {
            setParagraphs(result);
        },
        error: function (msg) {
            var error = JSON.parse(msg.responseText);
            alert(error);
            return false;
        }
    });

});


function setParagraphs(catalogs){


        for(var i = 0; i < catalogs.length; i++){
            $("#catalogs").append('<li><a href="javascript:void(0);" onclick="loadCategories('+ catalogs[i]['hierarchyId']  + ',' + ("hierarchy" + catalogs[i]['hierarchyId']) + ' )" >' + catalogs[i]['description'] + '</a></li>');
            $("#catalogs").append('<div class="categories" id="hierarchy' + catalogs[i]['hierarchyId'] + '"></div>');
        }

}


function loadCategories(id, selector){

        if($(selector).attr("expanded") == null) {

            var first = null;
            var last = null;


            $.ajax({
                type: 'GET',
                url: 'load_type',
                data: {'id': id, 'first': first, 'last': last},
                dataType: 'json',
                success: function (result) {
                    setType(result);
                },
                error: function (msg) {
                    var error = JSON.parse(msg.responseText);
                    alert(error);
                    return false;
                }
            });

            $(selector).attr("expanded", 1);
        }
        else{

            if($(selector).attr("expanded") == 1){
                $(selector).hide();
                $(selector).attr("expanded", 0);
            }
            else{
                $(selector).show();
                $(selector).attr("expanded", 1);
            }

        }

}


function setType(type){

        if(type.length > 0){
            for(var i = 0; i < type.length; i++){
                $("#hierarchy" + type[i]['parentHierarchyId']).append('<li><a href="javascript:void(0);" onclick="loadSubtypes('+ type[i]['hierarchyId']+ ',' + ("hierarchy" + type[i]['hierarchyId']) + ')">' + type[i]['description'] + '</a></li>');
                $("#hierarchy" + type[i]['parentHierarchyId']).append('<div class="types" id="hierarchy' + type[i]['hierarchyId'] + '"></div>');
            }
        }


}

function loadSubtypes(id, selector){

    if($(selector).attr("expanded") == null) {
        var first = null;
        var last = null;

        $.ajax({
            type: 'GET',
            url: 'load_product',
            data: {'id': id, 'first': first, 'last': last},
            dataType: 'json',
            success: function (result) {
                setSubtype(result);
            },
            error: function (msg) {
                var error = JSON.parse(msg.responseText);
                alert(error);
                return false;
            }
        });
        $(selector).attr("expanded", 1);
    }
    else {

        if ($(selector).attr("expanded") == 1) {
            $(selector).hide();
            $(selector).attr("expanded", 0);
        }
        else {
            $(selector).show();
            $(selector).attr("expanded", 1);
        }
    }
}

function setSubtype(subtypes) {

    for(var i = 0; i < subtypes.length; i++){
        $("#hierarchy" + subtypes[i]['parentHierarchyId']).append('<li><a href="javascript:void(0);" onclick="loadProducts('+ subtypes[i]['hierarchyId']  + ')" id="hierarchy' + subtypes[i]['hierarchyId'] + '">' + subtypes[i]['description'] + '</a></li>');
    }
}


function loadProducts(id){

        var first = null;
        var last = null;

        $.ajax({
            type: 'GET',
            url: 'load_products',
            data: {'id': id, 'first': first, 'last': last},
            dataType: 'json',
            success: function (result) {
                setProducts(result);
            },
            error: function (msg) {
                var error = JSON.parse(msg.responseText);
                alert(error);
                return false;
            }
        });

}

function setProducts(products) {

    var parser=new DOMParser();
    var xmlDoc;
    var product = {};
    var $xml;

    for(var index = 0; index < products.length; index++){

        xmlDoc=parser.parseFromString(products[index]['content'],"text/html");
        $xml = $(xmlDoc);
        var image = $xml.find('img');

        if (image != null){

            product.pushButton = '<a class="add_to_cart" href="#" onclick="addToBasket(' + products[index]["hierarchyId"] + ', product' + products[index]["hierarchyId"] + ')">Add to Card</a>';
            //product.pushButton = '<a class="add_to_cart" href="#" onclick="addToBasket(' + products[index]["hierarchyId"] + ', product' + products[index]["hierarchyId"] + ')">' + 'Add to card' + '</a>';
            product.price = '<p class="product_price">150 руб</p>';


            if(products[index]['description'] != null){
                product.h3 = '<h3>' + products[index]['description'] + '</h3>';

            }
            else{
                product.h3 = '<h3></h3>';
            }

            product.img = '<a href = "product?productId=' + products[index]["productId"] + '" >' + products[index]['content'] + '</a>';


            // obj.description = products[index]['description'];
            // obj.button = '<input type="button" onclick="addToBasket(' + products[index]["hierarchyId"] + ', product' + products[index]["hierarchyId"] + ')" value="Добавить в корзину">';
            // obj.input = '<input type="text" value="1" id="product' + products[index]["hierarchyId"] + '">';

            // $("#content").html(obj.img);
            // $("#content").append('<div>' + obj.description + '</div>');
            // $("#content").append(obj.button);
            // $("#content").append(obj.input);


            $("#content").append('<div class="col col_14 product_gallery">'
                + product.img
                + product.h3
                + product.price
                + product.pushButton
                +
            '</div>')
        }
    }
}

function addToBasket(id, selector) {

    var count = $(selector).val();

    alert('ok')

    $.ajax({
        type: 'GET',
        url: 'add_product_to_basket',
        data: {'hierarchyId': id, 'count': count},
        dataType: 'json',
        success: function (result) {

            $("#basket").text(result);

        },
        error: function (msg) {
            var error = JSON.parse(msg.responseText);
            alert(error);
            return false;
        }
    });

}