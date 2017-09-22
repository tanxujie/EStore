$(function(){
    // menu
    $('.ui.menu .ui.dropdown').dropdown({on: 'hover'});
    $('.ui.menu a.item').on('click', function() {
        $(this).addClass('active').siblings().removeClass('active');
    });

    // 初始化查询表格
    $('#resultsTbl').DataTable({
        'serverSide': false,
        'paging': true,
        'bPaginate': true,
        'iDisplayLength': 10,
        'bLengthChange': false,
        'bInfo': false,
        'stateSave': true,
        'retrieve': true,
        'bFilter': false,
        'ajax': {
            'url' : '/microclass/search',
            'data': function(d) {
            },
            'dataSrc': 'data'
        },
        'columns': [
            { 'title': '标题', 'target': 1, 'width': '15%', 'data': 'title'},
            { 'title': '说明', 'target': 2, 'data': 'description', 'width': '30%' },
            { 'title': '状态', 'target': 3, 'data': 'status', 'width': '8%' }
        ]
    });
});