var restUrl = "/admin/loader/";
$(document).ready(function () {

    $("#importEquipment").submit(function (e) {
        e.preventDefault();
        importEquipment();
    });

    $('body').on('click', '.export-equipment-button', function () {
        window.location.href = '/admin/loader/' + $(this).attr("value");
    });

});

function importEquipment() {
    var form = document.forms[0];
    var formData = new FormData(form);
    $.ajax( "/admin/loader/import-equipment", {
        data: formData,
        type: "post",
        cache : false,
        processData: false,
        contentType: false,
        success: function (data) {
            if(data.msg == "true"){
                $("#importEquipment").css("display", "none");
                $("#msgSuccess").text(data.msgSuccess);
                $("#msgSuccess").css("display", "block");
            }else{
                alert(data.msgError);
            }
        },
        error: function (data) {
            alert(data);
        }
        /*xhr: function(){
            //Get XmlHttpRequest object
            var xhr = $.ajaxSettings.xhr() ;

            //Set onprogress event handler
            xhr.upload.onprogress = function(event){
                var perc = Math.round((event.loaded / event.total) * 100);
                $('#progressBar').text(perc + '%');
                $('#progressBar').css('width',perc + '%');
            };
            return xhr ;
        },
        beforeSend: function( xhr ) {
            //Reset alert message and progress bar
            $('#progressBar').text('');
            $('#progressBar').css('width','0%');
        }*/
    });
}