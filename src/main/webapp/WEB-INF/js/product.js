$(document).ready(function() {

    var id = hierarchyId;

    var first = null;
    var last = null;

    $.ajax({
        type: 'GET',
        url: 'load_product',
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

});


function setType(product){
    for(var i = 0; i < product.length; i++){
        $("#product").append('<li><a href="product?hierarchyId=' + product[i]['hierarchyId'] + '" id="hierarchy' + product[i]['hierarchyId'] + '">' + product[i]['description'] + '</a></li>');
    }
}
