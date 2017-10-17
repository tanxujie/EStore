$(function(){
    // clear session
    $.post('/initLogin')
    $("#btnLogin").click(function(event){
        event.preventDefault();
        event.stopPropagation();
        $("#frmLogin").submit();
    });
});
