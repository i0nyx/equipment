var id = 0;
$(document).ready(function () {
    $("button.edit").click(function () {
        id = $(this).attr("attr");
        editUser(id);
    });
    $("button.delete").click(function () {
        id = $(this).attr("attr");
        deleteUser(id);
    });
});

function editUser(id) {
    $("#editModalBox").modal('show');
    fillFormEditUser(id);
    $("#changeProfileModalForm").submit(function () {
        saveNewUserProfile(id);
    })
}

function fillFormEditUser(id) {
    if (id != 0) {
        $.ajax("/admin/rest/profile/get-user-by-id", {
            contentType: "application/json",
            data: id,
            type: "post",
            success: function (data) {
                $("#userName").html(data.firstName);
                $("#firstName").val(data.firstName);
                $("#lastName").val(data.lastName);
                $("#position").val(data.position);
                $("#cabinetNumber").val(data.cabinetNumber);
                $("#phoneNumber").val(data.phoneNumber);
            },
            error: function (data) {
                alert("don't get user by id " + id);
                console.log(data);
            }
        });
    }

}

function saveNewUserProfile(id) {
    var json = {
        "id": id,
        "firstName": $("#firstName").val(),
        "lastName": $("#lastName").val(),
        "position": $("#position").val(),
        "cabinetNumber": $("#cabinetNumber").val(),
        "phoneNumber": $("#phoneNumber").val()
    };
    if (json != null) {
        $.ajax("/admin/rest/profile/save-user-data", {
            contentType: "application/json",
            data: JSON.stringify(json),
            type: "post",
            success: function (data) {
                if (data == true) {
                    window.location.reload();
                }
            },
            error: function (data) {
                alert("don't save user data");
                console.log(data);
            }
        });
    }
    event.preventDefault();
}

function deleteUser(id) {

}