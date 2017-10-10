$(function() {
    var microclassid = getUrlParam('microclassid');
    var microclassvideoid = getUrlParam('microclassvideoid');
    if (!isNumber(microclassid) || !isNumber(microclassvideoid)) {
        window.location = './microclass.html';
        return;
    }

    $("#txtMicroClassId").val(microclassid);
    $("#txtMicroClassVideoId").val(microclassvideoid);
    var $videoCalendar = $("#videoCalendar").calendar({
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

    // load details
    $.get('/microclassvideo/getDetail', 
            {'id': microclassvideoid}, 
            function(data){
                if (data.success) {
                    $("#txtMicroClassVideoId").val(data.data.id);
                    $("#txtTitle").val(data.data.title);
                    $videoCalendar.calendar('set date', data.data.videoDateStr);
                    $("#txtDescription").val(data.data.description);
                    initVideoFileUploader(data.data.videoInitialPreview, data.data.videoPreviewConfig);
                }
            }
         );

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

    function initVideoFileUploader(initPrews, initPrewCfgs) {
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
            maxFileCount: 1,
            initialPreview: initPrews,
            initialPreviewConfig: initPrewCfgs
        }).on("filebatchselected", function(event, files){
        	$videoFile.fileinput("upload");
        }).on("filebatchuploadsuccess", function(event, data, children) {
            var filenames = data.response.data;
            $('[name="videoName"]', children[0]).val(filenames[0]);
        });
    }

    $("#btnSave").click(function(event) {
        event.preventDefault();
        if ($("#formAdd").form('is valid')) {
            event.stopPropagation();
            $.post("/microclassvideo/modify", 
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