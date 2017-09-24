$(function(){
    // menu
    $('.ui.menu .ui.dropdown').dropdown({on: 'hover'});
    $('.ui.menu a.item').on('click', function() {
        $(this).addClass('active').siblings().removeClass('active');
    });

    var $condition = $("#txtCondition");

    // 初始化查询表格
    var $table = $('#resultsTbl').DataTable({
        'serverSide': false,
        //'searching': false,
        //'select':true,
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
            'url' : '/microclass/search',
            'data': {
                condition: $condition.val()
            },
            'dataSrc': 'data'
        },
        'columnDefs': [{
            "targets": 0,
            "searchable": false
          }],
        'columns': [
            { 'title': '序号', 'target': 0, 'width': '5%', 'data': 'id'},
            { 'title': '标题', 'target': 1, 'width': '20%', 'data': 'title'},
            { 'title': '说明', 'target': 2, 'sortable':false, 'width': '40%', 'data': 'description' }
        ]
    });

    $("#resultsTbl_filter").hide();

    $condition.keyup(function() {
        var condition = $condition.val();
        $table.search(condition).draw();
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
        window.location = './addmicroclass.html';
    });

    $("#btnEdit").click(function(event){
        event.preventDefault();
        event.stopPropagation();
        var ids = $.map($table.rows('.selected').data(), function (item) {
            return item['id'];
        });
        if (ids && ids.length) {
            window.location = './editmicroclass.html?id='+ids[0];
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
            $.post('/microclass/remove', 
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