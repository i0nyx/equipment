var restUrl = "rest/";
$(document).ready(function () {

    $("#support_link").click(function () {
        showBlockSupport()
    });
    $("#computer_link").click(function () {
        showBlockComputer();
    });
    $("#notebook_link").click(function () {
        showBlockNotebook();
    });
    $("#other_link").click(function () {
        showBlockOther();
    });
    $("#support_form").submit(function () {
        sendSupportForm();
    })

});

function showBlockSupport() {
    $("#support").css("display", "block");
    $("#computer").css("display", "none");
    $("#notebook").css("display", "none");
    $("#other").css("display", "none");
}

function showBlockComputer() {
    $("#support").css("display", "none");
    $("#computer").css("display", "block");
    $("#notebook").css("display", "none");
    $("#other").css("display", "none");
}

function showBlockNotebook() {
    $("#support").css("display", "none");
    $("#computer").css("display", "none");
    $("#notebook").css("display", "block");
    $("#other").css("display", "none");
}

function showBlockOther() {
    $("#support").css("display", "none");
    $("#computer").css("display", "none");
    $("#notebook").css("display", "none");
    $("#other").css("display", "block");
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