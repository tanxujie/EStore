$(function(){
    // menu
    $('.ui.menu .ui.dropdown').dropdown({on: 'hover'});
    $('.ui.menu a.item').on('click', function() {
        $(this).addClass('active').siblings().removeClass('active');
    });

    // 初始化查询表格
    var $table = $('#resultsTbl').DataTable({
        'serverSide': false,
        //'select':true,
        'paging': true,
        'bPaginate': true,
        'iDisplayLength': 10,
        'bLengthChange': false,
        'bInfo': false,
        'stateSave': true,
        'retrieve': true,
        'bFilter': false,
        'sorter': true,
        'ajax': {
            'url' : '/agent/search',
            'data': function(d) {
            },
            'dataSrc': 'data'
        },
        'columns': [
            { 'title': '序号', 'target': 0, 'width': '5%', 'data': 'id'},
            { 'title': '标题', 'target': 1, 'width': '20%', 'data': 'title'},
            { 'title': '说明', 'target': 2, 'sortable':false, 'width': '40%', 'data': 'description' }
        ]
    });

    $('#resultsTbl tbody').on( 'click', 'tr', function () {
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        }
        else {
            $table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    } );

    $("#btnAdd").click(function(event){
        event.preventDefault();
        event.stopPropagation();
        window.location = './addagent.html';
    });

    $("#btnEdit").click(function(event){
        event.preventDefault();
        event.stopPropagation();
        var ids = $.map($table.rows('.selected').data(), function (item) {
            return item['id'];
        });
        if (ids && ids.length) {
            window.location = './editagent.html?id='+ids[0];
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

    $("#btnDeleteConfirm").click(function(event){
        event.preventDefault();
        event.stopPropagation();
        var ids = $.map($table.rows('.selected').data(), function (item) {
            return item['id'];
        });
        if (ids && ids.length) {
            $.post('/agent/remove', 
                    {'id': ids[0]}, 
                    function(data){
                        if (data.success) {
                            $table.row('.selected').remove().draw( false );
                            $("#deleteModal").modal('hide');
                        }
                    });
        }
    });
});