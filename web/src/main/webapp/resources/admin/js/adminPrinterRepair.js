var id = 0;
var received = null;
$(document).ready(function () {

    $("#received").change(function () {
        event.preventDefault();
        id = $(this).val();
        if(id != 0){
            fillInTheFields();
        }else{
            clearInTheFields();
        }
    });

    $("#formPrinterRepair").submit(function () {
        savePrinterRepair();
    })
});

function fillInTheFields() {
    $.ajax("/admin/rest/received-repair/select",{
        contentType: "application/json",
        data: id,
        type: "post",
        success: function(data){
            received = data;
            var whose = data.whose;
            if(whose == null || whose == ""){
                whose = data.support.lastName + " (" + data.support.cabinet + ")";
            }
            $("#model").val(data.equipment.brand + " " + data.equipment.model);
            $("#whom").val(whose);
            $("#number").val(data.equipment.code);
        },
        error: function(data){
            alert("Sorry!Please try again.");
            console.log(data);
        }
    });
}
function clearInTheFields() {
    $("#id").val('');
    $("#model").val('');
    $("#whom").val('');
    $("#number").val('');
    received = null;
}

function savePrinterRepair(){
    var json = {
        "who" : $("#who").val(),
        "descriptionWork" : $('#description').val(),
        "receivedRepair" : received
    };

    $.ajax("/admin/rest/printer-repair/save",{
        contentType: "application/json",
        data: JSON.stringify(json),
        type: "post",
        success: function(data){
            if(data == true) {
                window.location.reload();
            }
        },
        error: function(data){
            alert("Sorry! not save, try again.");
            console.log("Can't save " + data);
        }
    });
    event.preventDefault();


}