$(function(){
    $("#btnLogin").click(function(event){
        event.preventDefault();
        event.stopPropagation();
        $("#frmLogin").submit();
    });
});
