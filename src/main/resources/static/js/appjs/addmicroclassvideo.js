$(function() {
    var microclassid = getUrlParam('microclassid');
    if (!isNumber(microclassid)) {
        window.location = './microclass.html';
        return;
    }

    $("#txtMicroClassId").val(microclassid);
    $("#videoCalendar").calendar({
        type: 'date', 
        today: true,
        formatter: {
            datetime: function (date, settings) {
                if (!date) {
                  return '';
                }
                var day = date.getDate();
                var month = (date.getMonth()+1);
                var year = date.getFullYear();
                return year + "/" + month + "/" + day;
              }
        }
    });

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
            }
        }
    });

    var $videoFile = $("#videoFile").fileinput({
        theme: "explorer",
        uploadUrl: "/upload/image",
        deleteUrl: "/upload/image/remove/",
        uploadAsync: false,
        language: 'zh',
        showUpload: false,
        showRemove: false,
        showClose: false,
        showCancel: false,
        showZoom: false,
        showDownload: false,
        showBrowse: false,
        browseOnZoneClick: true,
        allowedPreviewTypes: ['video'],
        allowedFileTypes: ['video'],
        allowedFileExtensions: ['mp4'],
        hiddenThumbnailContent: true,
        overwriteInitial: false,
        initialPreviewAsData: true,
        maxFileCount: 1
    }).on("filebatchselected", function(event, files){
        $imageFile.fileinput("upload");
    }).on("filebatchuploadsuccess", function(event, data, first){
        first.append('<input type="hidden" name="videoName" value="' + data.response.data +  '"/>');
    });

    $("#btnSave").click(function(event) {
        event.preventDefault();
        if ($("#formAdd").form('is valid')) {
            event.stopPropagation();
            $.post("/microclassvideo/save", 
                    $("#formAdd").serialize(), 
                    function(data) {
                if (data.success) {
                    window.location = './editmicroclass.html?id='+microclassid;
                }
            });
        }
    });

    $("#btnCancel").click(function(event){
        event.preventDefault();
        event.stopPropagation();
        window.location = './editmicroclass.html?id='+microclassid;
    });
});