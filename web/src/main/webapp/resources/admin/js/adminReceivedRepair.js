var id = 0;
var support = null;
$(document).ready(function () {
    $("#applications").change(function () {
        event.preventDefault();
        id = $(this).val();
        if (id != 0) {
            fillInTheFields();
        } else {
            clearInTheFields();
        }
    });

    $("#formReceivedRepair").submit(function () {
        saveReceivedRepair();
    })
});

function fillInTheFields() {
    $.ajax("/admin/rest/application/select-by-id", {
        contentType: "application/json",
        data: id,
        type: "post",
        success: function (data) {
            support = data;
            var whose = data.lastName + " (" + data.cabinet + ")";
            var urgently;
            if (data.urgently == true) {
                urgently = "срочно!";
            } else {
                urgently = "не срочно.";
            }
            var comment = urgently + " " + data.comment;
            $("#whose").val(whose);
            $("#comment").text(comment);
        },
        error: function (data) {
            alert("Sorry!Please try again.");
            console.log(data);
        }
    });
}

function clearInTheFields() {
    $("#whose").val('');
    $("#comment").text('');
    id = 0;
    support = null;
}

function saveReceivedRepair() {
    var valid = true;
    var dateStr = $("#date").val();
    var number = $("#number").val();
    var equipment = checkNumber(number);
    var whose = $("#whose").val();

    if (equipment == null) {
        valid = false;
        $("#number_error").css("display", "block");
    }
    if (support != null) {
        whose = null;
    }
    var json = {
        "whose": whose,
        "date": Date.parse(dateStr),
        "comment": $("#comment").val(),
        "support": support,
        "equipment": equipment
    };

    if (valid) {
        $.ajax("/admin/rest/received-repair/save", {
            contentType: "application/json",
            data: JSON.stringify(json),
            type: "post",
            success: function (data) {
                if (data == true) {
                    window.location.reload();
                }
            },
            error: function (data) {
                alert("ОШИБКА!");
                console.log("Can't save " + data);
            }
        });
    }
    event.preventDefault();
}

function checkNumber(number) {
    var equipment = null;
    $.ajax("/admin/rest/equipment/check-code", {
        async: false,
        contentType: "application/json",
        data: number,
        type: "post",
        success: function (data) {
            if (data != null) {
                equipment = data;
            }
        }
    });
    return equipment;
}