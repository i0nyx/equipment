$(document).ready(function () {
    $("#support_link").click(function () {
        openSupportBlock();
    });
    $("#profile_link").click(function () {
        openProfileBlock();
    });
    $("#change-foto-link").click(function () {
       openChangeFotoBlock();
    });
    $("#change-profile-link").click(function () {
        openChangeProfileBlock();
    });
    $("#change-password-link").click(function () {
       openChangePasswordBlock();
    });


    $("#changeProfileForm").submit(function () {
        changeProfile();
    });
    $("#changePasswordForm").submit(function () {
        $("#passError").css("color", "");
        $("#newPassError").css("display", "none");
       changePassword();
    });
    $("#logout-link").click(function () {
       window.location.href="/logout";
    });
    $("#changeFotoForm").submit(function () {
        changeFoto();        
    });

});

function openSupportBlock() {
    $("#support_view").css("display", "block");
    $("#equipment_view").css("display", "none");
    $("#profile_view").css("display", "none");
    $("#change_profile_view").css("display", "none");
    $("#change_password_view").css("display", "none");
    $("#change_foto_view").css("display", "none");
}
function openProfileBlock() {
    $("#profile_view").css("display", "block");
    $("#support_view").css("display", "none");
    $("#equipment_view").css("display", "none");
    $("#change_profile_view").css("display", "none");
    $("#change_password_view").css("display", "none");
    $("#change_foto_view").css("display", "none");
}
function openChangeFotoBlock() {
    $("#change_foto_view").css("display", "block");
    $("#change_password_view").css("display", "none");
    $("#change_profile_view").css("display", "none");
    $("#equipment_view").css("display", "none");
    $("#support_view").css("display", "none");
    $("#profile_view").css("display", "none");
}
function openChangeProfileBlock() {
    $("#change_profile_view").css("display", "block");
    $("#equipment_view").css("display", "none");
    $("#support_view").css("display", "none");
    $("#profile_view").css("display", "none");
    $("#change_password_view").css("display", "none");
    $("#change_foto_view").css("display", "none");
}
function openChangePasswordBlock() {
    $("#change_password_view").css("display", "block");
    $("#change_profile_view").css("display", "none");
    $("#equipment_view").css("display", "none");
    $("#support_view").css("display", "none");
    $("#profile_view").css("display", "none");
    $("#change_foto_view").css("display", "none");
}

function changeProfile() {
    var id = $(".userId").val();
    var json = {
        "id" : id,
        "firstName" : $("#firstName").val(),
        "lastName" : $("#lastName").val(),
        "position" : $("#position").val(),
        "cabinetNumber" : $("#cabinetNumber").val(),
        "phoneNumber" : $("#phoneNumber").val()
    };
    if(id != 0 || id != null){
        $.ajax("/rest/profile/change",{
            contentType: "application/json",
            data: JSON.stringify(json),
            type: "post",
            success: function(data){
                if(data == true){
                    window.location.href = "/profile";
                }else{
                    $("#changeError").css("display", "block");
                }

            },
            error: function(data){
                alert("Ошибка! Проверьте правильность заполнения полей.");
                console.log("Can't change profile " + data);
            }
        });
    }
    event.preventDefault();
}
function changePassword() {
    var id = $(".userId").val();
    var password = $("#password").val();
    var newPass = $("#newPass").val();
    var newPassAgain = $("#newPassAgain").val();
    var valid = true;

    if(password == null || password == ""){
        valid = false;
        $("#passError").css("color", "red");
    }
    if(newPass != newPassAgain){
        valid = false;
        $("#newPassError").css("display", "block");
    }

    if(valid){
        var json = {
            "userId" : id,
            "oldPass" : password,
            "newPass" : newPass,
            "newPassAgain" : newPassAgain
        };
        $.ajax("/rest/profile/change-password",{
            contentType: "application/json",
            data: JSON.stringify(json),
            type: "post",
            success: function(data){
                if(data == true){
                    window.location.href = "/profile";
                }else{
                    $("#changePasswordError").css("display", "block");
                }

            },
            error: function(data){
                alert("Ошибка! Проверьте правильность заполнения полей.");
                console.log("Can't change password " + data);
            }
        });
    }
    event.preventDefault();
}
function changeFoto() {
    var form = document.forms[2];
    var formData = new FormData(form);
    $.ajax( "/rest/profile/change-avatar", {
        data: formData,
        type: "post",
        cache: false,
        processData: false,
        contentType: false,
        success: function (data) {
            if(data == true){
                window.location.href = "/profile";
            }
        },
        error: function () {
            alert("Ошибка!");
        }
    });
    event.preventDefault();
}