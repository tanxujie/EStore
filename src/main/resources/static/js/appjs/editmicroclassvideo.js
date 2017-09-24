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
                    //$("#txtVideoDate").val(data.data.videoDateStr);
                    $videoCalendar.calendar('set date', data.data.videoDateStr);
                    $("#txtDescription").val(data.data.description);
                }
            }
         );

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
            $hiddenVideoFile.append($('<input type="hidden" name="newName" value="' + resp.data[0] + '" />'));
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