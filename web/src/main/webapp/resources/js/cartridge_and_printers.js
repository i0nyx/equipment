var restUrl = "rest/";
$(document).ready(function () {
    $("#printer_link").click(function () {
        showBlockPrinter();
    });
    $("#cartridge_link").click(function () {
        showBlockCartridge()
    });
    $("#support_link").click(function () {
        showBlockSupport()
    });
    $("#support_form").submit(function () {
        sendSupportForm();
    })

});

function showBlockPrinter() {
    $("#support").css("display", "none");
    $("#cartridge").css("display", "none");
    $("#printer").css("display", "block");
}

function showBlockCartridge() {
    $("#support").css("display", "none");
    $("#printer").css("display", "none");
    $("#cartridge").css("display", "block");
}

function showBlockSupport() {
    $("#printer").css("display", "none");
    $("#cartridge").css("display", "none");
    $("#support").css("display", "block");
}

function sendSupportForm() {
    var json = {
        "lastName": $("#lastName").val(),
        "cabinet": $("#cabinet").val(),
        "comment": $("#comment").val(),
        "urgently": $("#urgently").prop('checked'),
        "supportType": $("#type").val(),
        "user": $("#user").val()
    };
    $.ajax(restUrl + "send", {
        contentType: "application/json",
        data: JSON.stringify(json),
        type: "post",
        success: function (data) {
            if (data == true) {
                alert("Ваша заявка отправлена. Ожидайте.");
                window.location.reload();
            }
        },
        error: function (data) {
            alert("Ошибка! Попробуйте снова или обратитесь к программисту.");
            console.log("Can't send " + data);
        }
    });
    event.preventDefault();
}