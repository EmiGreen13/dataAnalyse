$(document).ready(function() {

    var id = hierarchyId;

    var first = null;
    var last = null;

    $.ajax({
        type: 'GET',
        url: 'load_product_content',
        data: {'id': id, 'first': first, 'last': last},
        dataType: 'json',
        success: function (result) {
            setProduct(result);
        },
        error: function (msg) {
            var error = JSON.parse(msg.responseText);
            alert(error);
            return false;
        }
    });

});


function setProduct(product){
    for(var i = 0; i < product.length; i++){

        var parser=new DOMParser();
        var xmlDoc=parser.parseFromString(product[i]['content'],"text/html");

        if (xmlDoc != null){
            var $xml = $(xmlDoc);
            var x = $xml.find('product');
            $("#productContent").html(x);
        }
        else{
            $("#productContent").html('The description doesn\'t exist')
        }

        $("#product").append('<div><input type="text" id="count"></div>');
        $("#product").append('<div><input type="submit" onClick="submitProduct(hierarchyId)" value="Добавить в корзину">' + '</div>')
        //$("#product").append('<li><a href="order?productId=' + product[i]['productId'] + '" id="productId' + product[i]['productId'] + '">' + product[i]['description'] + '</a></li>');

    }
}

function submitProduct(productId) {

    var count = $("#count").val();


    $.ajax({
        type: 'GET',
        url: 'add_product_to_basket',
        data: {'productId': productId, 'count': count},
        dataType: 'json',
        success: function (result) {
            updateBasketCount(result);
        },
        error: function (msg) {
            var error = JSON.parse(msg.responseText);
            alert(error);
            return false;
        }
    });
}


function updateBasketCount(result) {

    alert(result)

}
