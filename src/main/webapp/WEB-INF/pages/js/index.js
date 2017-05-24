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


    for(var index = 0; index < products.length; products++){
        alert(products[index]['content'])
        $("#content").append(products[index]['content'])

    }

}