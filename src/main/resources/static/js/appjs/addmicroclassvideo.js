$(function() {
    var microclassid = getUrlParam('microclassid');
    if (!isNumber(microclassid)) {
        window.location = './microclass.html';
        return;
    }

    $("#txtMicroClassId").val(microclassid);

    var $hiddenVideoFile = $("#hiddenVideoFile");
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
            }//,
//            videoDate: {
//                identifier: 'videoDate',
//                rules:[
//                    {
//                        type:'regExp[^\d{4}(\-|\/|\.)\d{1,2}\1\d{1,2}$]',
//                        prompt: '请输入正确的日期'
//                    }
//                ]
//            }
        }
    });

    var dz = new Dropzone("#videoDropZone", {
        paramName: "videos",
        maxFilesize: 50,
        addRemoveLinks: true,
        uploadMultiple: true,
        thumbnailWidth:100,
        thumbnailHeight:100,
        thumbnailMethod: 'contain',
        maxFiles:1,
        acceptedFiles: ".mp4",
        //acceptedFiles:"image/*",
        url: '/upload/video',
        success: function(file, resp) {
            $("#messageArea").hide();
            $hiddenVideoFile.append($('<input type="hidden" name="videoName" value="' + resp.data[0] + '" />'));
            //file.previewElement.classList.add("dz-success");
        },
        error: function(file, response) {
            //file.previewElement.classList.add("dz-error");
        }
/*        ,
        removedfile: function(file) {
            //alert("removefile");
            //alert(file.content[0]);
            dz.removeFile(file);
           // return true;
        }*/
    });

    $("#btnSave").click(function(event) {
        event.preventDefault();
        if ($("#formAdd").form('is valid')) {
            $.post("/microclassvideo/save", 
                    $("#formAdd").serialize(), 
                    function(data) {
                if (data.success) {
                    window.location = './editmicroclass.html?id='+microclassid;
                }
            });
        }
    });
});