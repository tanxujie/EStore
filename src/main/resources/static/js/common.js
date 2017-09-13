$(function(){
    $(".menu.transition > .item").click(function(){
        var url = $("a", this).attr("href");
        window.location = url;
    });
});