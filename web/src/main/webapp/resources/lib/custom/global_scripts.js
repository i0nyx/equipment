/* URLs*/
var restUrlComment = "/rest/comment/";
var restUrlReview = "/rest/review/";
var restUrlOrder = "/rest/order/";

$(document).ready(function () {

    $(".contact-form").submit(function (event) {
        submitContactForm(this);
        event.preventDefault();
    });

    $('#review-form').submit(function (event) {
        submitReviewForm();
        event.preventDefault();
    });

    $("#order-add-to-order-btn").click(function () {
        var productId = $(this).attr('product-id');
        var quantity = $("#order-add-to-order-quantity").val();
        if (productId != null && quantity != null && quantity > 0) {
            $.ajax({
                url: restUrlOrder + 'add?productId=' + productId + '&quantity=' + quantity,
                type: "GET",
                success: function (data) {
                    $("#order-add-to-order-success").show();
                }
            });
        }
    });

    $(".remove-from-order-btn").click(function () {
        var productId = $(this).attr('product-id');
        $.ajax({
            url: restUrlOrder + 'remove?productId=' + productId,
            type: "GET",
            success: function (data) {
                location.reload();
            }
        });
    });

    $(".edit-quantity-order-btn").click(function () {
        var productId = $(this).attr('product-id');
        var quantity;
        $(".edit-quantity-input").each(function (index) {
            if ($(this).attr('product-id') == productId) {
                quantity = $(this).val();
            }
        });
        $.ajax({
            url: restUrlOrder + 'quantity?productId=' + productId + '&quantity=' + quantity,
            type: "GET",
            success: function (data) {
                location.reload();
            }
        });
    });


    $('#place-order-form').submit(function (event) {
        var json = {
            "firstName": $('#firstName').val(),
            "lastName": $('#lastName').val(),
            "email": $('#email').val(),
            "phone": $('#phone').val(),
            "country": $('#country').val(),
            "city": $('#city').val(),
            "address": $('#address').val()
        };
        $.ajax({
            url: restUrlOrder + "submit",
            type: "POST",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(json), //Stringified Json Object
            async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
            cache: false,    //This will force requested pages not to be cached by the browser
            processData: false, //To avoid making query String instead of JSON
            success: function (data) {
                window.location.href = '/confirmation';
            }
        });
        event.preventDefault();
    });


    /* UI scripts */
    $(".dropdown").hover(
        function () {
            $('.dropdown-menu', this).stop(true, true).slideDown("fast");
            $(this).addClass("open");
        },
        function () {
            $('.dropdown-menu', this).stop(true, true).slideUp("fast");
            $(this).removeClass("open");
        }
    );

    $('#bs-megadropdown-tabs .dropdown').on('click', function () {
        $(this).toggleClass('open');
    });

    $("#hamburger").click(function () {
        $(this).toggleClass("closemenu");
        $("#sidenav").toggleClass("sidenavIn");
    });


});

function submitReviewForm() {
    var json = {
        "name": $('#review-form-name').val(),
        "location": $('#review-form-location').val(),
        "phone": $('#review-form-phone').val(),
        "email": $('#review-form-email').val(),
        "rating": $('input[name=review-form-rating]:checked').val(),
        "content": $('#review-form-content').val()
    };
    $.ajax({
        url: restUrlReview,
        type: "POST",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(json), //Stringified Json Object
        async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
        cache: false,    //This will force requested pages not to be cached by the browser
        processData: false, //To avoid making query String instead of JSON
        success: function (data) {
            $('#review-form-alert-success').show();
            $('#review-form-name').val('');
            $('#review-form-location').val('');
            $('#review-form-phone').val('');
            $('#review-form-email').val('');
            $('#review-form-content').val('');
        }
    });
}


function submitContactForm(form) {
    var url = document.location.href;
    var json = {
        "name": $(form).find('.contact-form-name').val(),
        // "email": $(form).find('.contact-form-email').val(),
        "phone": $(form).find('.contact-form-phone').val(),
        "message": $(form).find('.contact-form-message').val() + "<br />" + url
    };
    $.ajax({
        url: restUrlComment,
        type: "POST",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(json), //Stringified Json Object
        async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
        cache: false,    //This will force requested pages not to be cached by the browser
        processData: false, //To avoid making query String instead of JSON
        success: function (data) {
            $(form).find('.contact-form-alert-success').show();
            $(form).find('.contact-form-name').val('');
            // $(form).find('.contact-form-email').val('');
            $(form).find('.contact-form-phone').val('');
            $(form).find('.contact-form-message').val('');
        }
    });
}
