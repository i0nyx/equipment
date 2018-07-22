$(document).ready(function () {

    $("#application_link").click(function () {
        openApplication();
    });
    $("#all_application_link").click(function () {
        openAllApplication();
    });
});

function openApplication() {
    $("#applicationView").css("display", "block");
    $("#allApplicationView").css("display", "none");
}

function openAllApplication() {
    $("#allApplicationView").css("display", "block");
    $("#applicationView").css("display", "none");
}
