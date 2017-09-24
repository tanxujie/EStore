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

    var $table = $('#resultsTbl').DataTable({
        'serverSide': false,
        'paging': true,
        'bPaginate': true,
        'iDisplayLength': 10,
        'bLengthChange': false,
        'bInfo': false,
        'stateSave': true,
        'retrieve': true,
        'bFilter': true,
        'sorter': true,
        'ajax': {
            'url' : '/microclassvideo/searchByMicroClassId',
            'data': {
                id: id
                },
            'dataSrc': 'data'
        },
        'columnDefs': [{
            "targets": 0,
            "searchable": false
          }],
        'columns': [
            { 'title': '标题', 'target': 0, 'width': '20%', 'data': 'title'},
            { 'title': '日期', 'target': 1, 'width': '20%', 'data': 'videoDateStr'},
            { 'title': '说明', 'target': 2, 'sortable':false, 'data': 'description' }
        ]
    });

    $("#resultsTbl_filter").hide();

    $('#resultsTbl tbody').on( 'click', 'tr', function () {
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        }
        else {
            $table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    } );

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

    $("#btnAdd").click(function(event){
        event.preventDefault();
        event.stopPropagation();
        window.location = './addmicroclassvideo.html?microclassid=' + id;
    });

    $("#btnEdit").click(function(event){
        event.preventDefault();
        event.stopPropagation();
        var ids = $.map($table.rows('.selected').data(), function (item) {
            return item['id'];
        });
        if (ids && ids.length) {
            window.location = './editmicroclassvideo.html?id='+ids[0];
        } else {
            $("#selectDataModal").modal('show');
        }
    });

    $("#btnDelete").click(function(event){
        event.preventDefault();
        event.stopPropagation();
        var ids = $.map($table.rows('.selected').data(), function (item) {
            return item['id'];
        });
        if (ids && ids.length) {
            $("#deleteModal").modal('show');
        } else {
            $("#selectDataModal").modal('show');
        }
    });

    $("#btnSave").click(function(event) {
        event.preventDefault();
        if ($("#formAdd").form('is valid')) {
            event.stopPropagation();
            $.post("/microclass/modify", 
                    $("#formAdd").serialize(), 
                    function(data) {
                if (data.success) {
                    window.location = './microclass.html';
                }
            });
        }
    });

    $("#btnCancel").click(function(event){
        event.preventDefault();
        event.stopPropagation();
        window.location = './microclass.html';
    });
});