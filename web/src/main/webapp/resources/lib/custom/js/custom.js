var restUrlAdmin = "cadmin";
var restUrlAdminMedia = restUrlAdmin + "/content/media/rest/";
var restUrlAdminStaticPage = restUrlAdmin + "/content/static_page/rest/";
var restUrlAdminPost = restUrlAdmin + "/content/post/rest/";
var restUrlAdminReviews = restUrlAdmin + "/content/reviews/rest/";
var restUrlAdminAttribute = restUrlAdmin + "/catalog/rest/attribute";
var restUrlAdminCategory = restUrlAdmin + "/catalog/rest/category/";
var restUrlAdminLoader = restUrlAdmin + "/catalog/rest/loader/";
var restUrlAdminProduct = restUrlAdmin + "/catalog/rest/product";
var restUrlAdminPriceSender = restUrlAdmin + "/price_sender/rest/";


var restUrlAdminInternalProfile = restUrlAdmin + "/internal_profile/rest/";
var restUrlAdminEmailAccounts = restUrlAdmin + "/email-accounts/rest/";
var restUrlAdminProfile = restUrlAdmin + "/profiles/rest/";
var restUrlAdminShippingMethods = restUrlAdmin + "/shipping-methods/rest/";
var restUrlAdminPaymentTypes = restUrlAdmin + "/payment-types/rest/";
var restUrlAdminOrders = restUrlAdmin + "/orders/rest/";

var restUrlAdminCountries=restUrlAdmin + "/countries/rest/";

var restUrlAdminEmailTemplates = restUrlAdmin + "/messages/email-templates/rest/";

var ROLES = ["SUPER_ADMIN", "ADMIN", "MANAGER", "USER"];


function validateSeoUrl(value) {
    if(stringIsEmpty(value) || stringHasWhiteSpace(value)
        || !stringStartsWithSlash(value) || stringEndsWithSlash(value)){
        return false;
    }
    return true;
}

function stringIsEmpty(s) {
    return s == null || s == '';
}

function stringStartsWithSlash(s) {
    if(!stringIsEmpty(s)){
        return s.charAt(0) == '/';
    }else {
        return false;
    }
}

function stringEndsWithSlash(s) {
    if(!stringIsEmpty(s)){
        return s.charAt(s.length-1) == '/';
    }else {
        return false;
    }
}

function stringHasWhiteSpace(s) {
    if(!stringIsEmpty(s)){
        return /\s/g.test(s);
    }else {
        return false;
    }
}

function normalizePhoneNumber(s) {
    var result = '';
    if(!stringIsEmpty(s)){
        result =  s.replace(/[^0-9]/g, '');
    }
    return result;
}