$(document).ready(function() {

    var id = 1;
    var first = null;
    var last = null;

    $.ajax({
        type: 'GET',
        url: 'load_paragraphs',
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


function setParagraphs(paragraphs){

    for(var i = 0; i < paragraphs.length; i++){
        $("#paragraphs").append('<li><a href="paragraph?componentId=' + paragraphs[i]['componentId'] + '" id="hierarchy' + paragraphs[i]['componentId'] + '">' + paragraphs[i]['description'] + '</a></li>');
    }
}
