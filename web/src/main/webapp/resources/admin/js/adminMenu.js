var restUrl = "/admin/rest/menu";
$(document).ready(function () {
   attentionApplication();
    attentionReceivedRepair();
    attentionOrder();
});

function attentionApplication() {
    $.ajax("/admin/rest/menu/application",{
        contentType: "application/json",
        type: "post",
        success: function(data){
            if(data > 0){
                $("#application").css("color", "red");
            }
        }
    });
}
function attentionReceivedRepair() {
    $.ajax("/admin/rest/menu/received-repair",{
        contentType: "application/json",
        type: "post",
        success: function(data){
            if(data > 0){
                $("#receivedRepair").css("color", "red");
            }
        }
    });
}
function attentionOrder(){
    $.ajax("/admin/rest/menu/order",{
        contentType: "application/json",
        type: "post",
        success: function(data){
            if(data > 0){
                $("#order").css("color", "red");
            }
        }
    });
}
