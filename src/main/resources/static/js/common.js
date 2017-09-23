$(function(){
    $(".menu.transition > .item").click(function(){
        var url = $("a", this).attr("href");
        window.location = url;
    });
});

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); 
    return null;
}

function isNumber(val) {
    return typeof val === 'number' && !isNaN(val); 
}