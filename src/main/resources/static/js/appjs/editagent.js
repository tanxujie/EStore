$(function() {
    var id = getUrlParam('id');
    if (!isNumber(id)) {
        window.location = './microclass.html';
        return;
    }

    // load details
    $.get('/microclass/getDetail', 
            {'id': id}, 
            function(data){
                if (data.success) {
                    $("#txtId").val(data.data.id);
                    $("#txtTitle").val(data.data.title);
                    $("#txtDescription").val(data.data.description);
                }
            }
         );

    var $hiddenVideoFile = $("#hiddenVideoFile");
    var $hiddenUploadFile = $("#hiddenUploadFile");
    $('.ui.menu .ui.dropdown').dropdown({on: 'hover'});
    $('.ui.menu a.item').on('click', function() {
        $(this).addClass('active').siblings().removeClass('active');
    });

    $("#formAdd").form({
        fields: {
            title: {
                identifier: 'title',
                rules:[
                    {
                        type:'empty',
                        prompt: '请输入标题'
                    },
                    {
                        type:'maxLength[50]',
                        prompt: '标题中最多输入50个字符'
                    }
                ]
            },
            description: {
                identifier: 'description',
                rules:[
                    {
                        type:'empty',
                        prompt: '请输入说明'
                    },
                    {
                        type:'maxLength[200]',
                        prompt: '说明中最多输入200个字符'
                    }
                ]
            }
        }
    });

    $("#btnSave").click(function(event) {
        event.preventDefault();
        event.stopPropagation();
        if ($("#formAdd").form('is valid')) {
            $.post("/microclass/modify", 
                    $("#formAdd").serialize(), 
                    function() {
                window.location = './microclass.html';
            });
        }
    });
});