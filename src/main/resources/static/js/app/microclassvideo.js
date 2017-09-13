$(function(){
    $('.ui.menu .ui.dropdown').dropdown({on: 'hover'});
    $('.ui.menu a.item').on('click', function() {
        $(this).addClass('active').siblings().removeClass('active');
    });

    var $addModal = $("#modalAdd");
    var $hiddenUploadFile = $("#hiddenUploadFile");
    var dz = new Dropzone("#videoDropZone", {
        paramName: "videos",
        maxFilesize: 100,
        addRemoveLinks: true,
        uploadMultiple: false,
        thumbnailWidth:100,
        thumbnailHeight:100,
        //maxFiles:1,
        //acceptedFiles:"image/*",
        url: '/upload/video',
        success: function(file, response) {
            $hiddenUploadFile.append($('<input type="hidden" name="newName" value="' + response.data[0] + '" />'));
            //file.previewElement.classList.add("dz-success");
        },
        error: function(file, response) {
            //file.previewElement.classList.add("dz-error");
        }
    });

    $("#resultsTbl").DataTable({
        "autoWidth": true,
        "deferRender": false,
        "info": true,
        "ordering": true,
        "paging": true,
        //"dom": "Bfrtip",
        "info": false,
        //lengthChange: false,
        "searching": false,
        "ajax": "/microclassvideo/search",
        "serverSide": true,
        "columnDefs":[
            { title : '标题', targets: 0, width: '300px' },
            { title : '描述', targets: 1, width: '400px' }
        ],
        "columns": [
            { data: "title" },
            { data: "description" }
        ],
        "language": {
            "processing": "处理中...",
            "lengthMenu": "显示 _MENU_ 项结果",
            "zeroRecords": "没有匹配结果",
            "info": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
            "infoEmpty": "显示第 0 至 0 项结果，共 0 项",
            "infoFiltered": "(由 _MAX_ 项结果过滤)",
            "infoPostFix": "",
            "search": "搜索:",
            "searchPlaceholder": "搜索...",
            "url": "",
            "emptyTable": "表中数据为空",
            "loadingRecords": "载入中...",
            "infoThousands": ",",
            "paginate": {
                "first": "首页",
                "previous": "上页",
                "next": "下页",
                "last": "末页"
            },
            "aria": {
                paginate: {
                    first: '首页',
                    previous: '上页',
                    next: '下页',
                    last: '末页'
                },
                "sortAscending": ": 以升序排列此列",
                "sortDescending": ": 以降序排列此列"
            },
            "decimal": "-",
            "thousands": "."
        }
//        ,
//        buttons: [
//            { extend: "create", editor: editor }
//        ]
    });

    $("#btnAdd").click(function(event){
        $addModal.modal('setting', 'closable', false).modal('show');
    });

    $("#btnSave1").click(function(event){
        $.post("/microclassvideo/save", $("#formAdd").serialize()).done(function(event){
            clearForm();
            $addModal.modal('close');
        });
    });

    $("#btnCancel1").click(function(event){
        clearForm();
        $addModal.modal('close');
    });

    function clearForm() {
        $("#formAdd input").val("");
        $hiddenUploadFile.empty();
        dz.removeAllFiles();
    }
});