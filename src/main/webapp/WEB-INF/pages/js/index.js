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
    // }
    // else{
    //     $("#hierarchyId" + id.toString()).remove("#hierarchy" + type[i]['hierarchyId']);
    //     $("#hierarchyId" + id.toString()).attr("expanded", 0);
    // }

}


function setType(type){

        if(type.length > 0){
            for(var i = 0; i < type.length; i++){
                $("#hierarchy" + type[i]['parentHierarchyId']).append('<li><a href="javascript:void(0);" onclick="loadProducts('+ type[i]['hierarchyId']+ ',' + ("hierarchy" + type[i]['hierarchyId']) + ')" id="hierarchy' + type[i]['hierarchyId'] + '">' + type[i]['description'] + '</a></li>');
                $("#hierarchy" + type[i]['parentHierarchyId']).append('<div class="types" id="hierarchy' + type[i]['hierarchyId'] + '"></div>');
            }
        }


}

function loadProducts(id, selector){

    if($(selector).attr("expanded") == null) {
        var first = null;
        var last = null;

        $.ajax({
            type: 'GET',
            url: 'load_product',
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

function setProduct(products) {

    for(var i = 0; i < products.length; i++){

        $("#hierarchy" + products[i]['parentHierarchyId']).append('<li><a href="javascript:void(0);" onclick="loadProducts('+ products[i]['hierarchyId']  + ')" id="hierarchy' + products[i]['hierarchyId'] + '">' + products[i]['description'] + '</a></li>');


    }

}
