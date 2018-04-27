var restUrl = "/rest/";
$(document).ready(function() {
  $(".trigger").click(function() {
    $(".menu").toggleClass("active"); 
  });
    countNotifications();
});

function countNotifications() {
    $.ajax("/rest/notifications",{
        contentType: "application/json",
        type: "post",
        success: function(data){
            if(data > 0){
                $("#notification").css("display", "block");
                $("#countNotifications").text(data);
            }
        }
    });
}