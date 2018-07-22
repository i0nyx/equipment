$(document).ready(function () {

    $('#registration_form').submit(function (e) {
        e.preventDefault();
        saveNewUser();
    })

});

function saveNewUser() {
    var valid = true;
    var firstName = $('#firstName').val();
    var lastName = $('#lastName').val();
    var position = $('#position').val();
    var phoneNumber = $('#phoneNumber').val();
    var password = $('#password').val();
    var confirmPassword = $('#confirmPassword').val();

    if (firstName == null || firstName == "") {
        valid = false;
        $('#firstName').css("border", "2px solid red");
    }
    if (lastName == null || lastName == "") {
        valid = false;
        $('#lastName').css("border", "2px solid red");
    }
    if (position == null || position == "") {
        valid = false;
        $('#position').css("border", "2px solid red");
    }
    if (checkPhoneNumber(phoneNumber)) {
        valid = false;
        $('#phoneNumber').css("border", "2px solid red");
    }
    if (password != confirmPassword) {
        valid = false;
        $('#password').css("border", "2px solid red");
        $('#confirmPassword').css("border", "2px solid red");
    }

    if (valid) {
        var json = {
            "firstName": firstName,
            "lastName": lastName,
            "position": position,
            "phoneNumber": phoneNumber,
            "cabinetNumber": $('#cabinetNumber').val(),
            "password": password
        };

        $.ajax("/rest/registration/save", {
            contentType: "application/json",
            data: JSON.stringify(json),
            type: "post",
            success: function () {
                window.location.pathname = "/login";
            },
            error: function (data) {
                alert("Ошибка! Проверьте правильность заполнения полей.");
                console.log("Can't registration " + data);
            }
        });
    }

}

function checkPhoneNumber(phoneNumber) {
    var valid = false;
    if (phoneNumber.length >= 7 && phoneNumber.length <= 12) {
        $.ajax("/rest/registration/changePhoneNumber", {
            contentType: "application/json",
            data: phoneNumber,
            type: "post",
            async: false,
            success: function (data) {
                if (data != true) {
                    valid = true;
                }
            }
        });
    } else {
        valid = true;
    }
    return valid;
}