var id = 0;
$(document).ready(function () {

    $("#order_form").submit(function () {
        sendOrderForm();
    });

    $(".order_success").click(function () {
        id = $(this).val();
        orderSuccess();
    });

});

function sendOrderForm() {
    var json = {
        "whom": $("#whom").val(),
        "description": $("#description").val()
    };
    $.ajax("/admin/rest/order/save", {
        contentType: "application/json",
        data: JSON.stringify(json),
        type: "post",
        success: function () {
            alert("Заявка оформлена. ");
            window.location.reload();
        },
        error: function (data) {
            alert("Sorry! not save, try again.");
            console.log("Can't save " + data);
        }
    });
    event.preventDefault();
}

function orderSuccess() {
    if (id > 0) {
        $.ajax("/admin/rest/order/order-success", {
            contentType: "application/json",
            data: id,
            type: "post",
            success: function () {
                alert("Заказ выполнен.");
                window.location.reload();
            },
            error: function (data) {
                alert("Sorry! not save, try again.");
                console.log("Can't save " + data);
            }
        });
    }
    event.preventDefault();
}