var equipmentName;
var id = 0;
$(document).ready(function () {
    $("#formEquipment").submit(function () {
        equipmentName = $("option:selected", this).attr("equipmentName");
        addEquipment();
    });
    $(".equipment_delete").click(function () {
        id = $(this).val();
        equipmentDelete();
    })
});

function addEquipment() {
    var json = {
        "name": equipmentName,
        "brand": $("#brand").val(),
        "model": $("#model").val(),
        "type": $("#typeEquipment").val(),
        "code": $("#code").val(),
        "cabinet": $("#cabinet").val()
    };
    $.ajax("/admin/rest/equipment/save", {
        contentType: "application/json",
        data: JSON.stringify(json),
        type: "post",
        success: function (data) {
            if (data == true) {
                alert("Оборудование добавлено.");
                window.location.reload();
            }
        },
        error: function (data) {
            alert("Sorry! not save, try again.");
            console.log("Can't save " + data);
        }
    });
    event.preventDefault();
}

function equipmentDelete() {
    $.ajax("/admin/rest/equipment/delete", {
        contentType: "application/json",
        data: id,
        type: "post",
        success: function () {
            window.location.reload();
        },
        error: function (data) {
            alert("Если это оборудование ремонтировалось(заправлялось) то удалить его нельзя. пока не будут удалены все действия с ним!");
            console.log("Can't save " + data);
        }
    });
    event.preventDefault();
}