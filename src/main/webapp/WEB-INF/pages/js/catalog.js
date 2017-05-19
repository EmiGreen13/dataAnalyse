$(document).ready(function() {

    var id = hierarchyId;

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

});


function setType(type){
    for(var i = 0; i < type.length; i++){
        $("#type").append('<li><a href="type?hierarchyId=' + type[i]['hierarchyId'] + '" id="hierarchy' + type[i]['hierarchyId'] + '">' + type[i]['description'] + '</a></li>');
    }
}
