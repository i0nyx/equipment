$(document).ready(function () {

    $("#orderLink").click(function () {
        showBlockOrder()
    });
    $("#historyOrderLink").click(function () {
        showBlockHistoryOrder();
    });


});

function showBlockOrder() {
    $("#orderView").css("display", "block");
    $("#historyOrderView").css("display", "none");
}
function showBlockHistoryOrder() {
    $("#orderView").css("display", "none");
    $("#historyOrderView").css("display", "block");
}