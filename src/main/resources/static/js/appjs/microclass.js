function toEdit(id) {
    window.location = './addmicroclass.html?id=' + id;
}

function removeConfirm(id) {
    alert(id);
}

$(function(){
    // menu
    $('.ui.menu .ui.dropdown').dropdown({on: 'hover'});
    $('.ui.menu a.item').on('click', function() {
        $(this).addClass('active').siblings().removeClass('active');
    });

    // 初始化查询表格
    var $table = $('#resultsTbl').DataTable({
        'serverSide': false,
        'select':true,
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
            'url' : '/microclass/search',
            'data': function(d) {
            },
            'dataSrc': 'data'
        },
        'columns': [
            { 'title': '序号', 'target': 0, 'width': '5%', 'data': 'id'},
            { 'title': '标题', 'target': 1, 'width': '20%', 'data': 'title'},
            { 'title': '说明', 'target': 2, 'sortable':false, 'width': '40%', 'data': 'description' }
//            { 'title': '', 'target': 3, 'data': function(item){
//                var id = item.id;
//                var str = '<button class="ui teal mini button" onclick="toEdit('+id+');"><i class="edit icon"></i>编辑</button>';
//                str += '<button class="ui grey mini button" onclick="removeConfirm('+id+');"><i class="delete icon"></i>删除</button>';
//                return str;
//            }}
        ]
    });
    
    $("#btnAdd").click(function(event){
        event.preventDefault();
        event.stopPropagation();
        window.location = './addmicroclass.html';
    });

    $("#btnEdit").click(function(event){
        event.preventDefault();
        event.stopPropagation();
        window.location = './addmicroclass.html?id=';
    });

    $("#btnDelete").click(function(event){
        event.preventDefault();
        event.stopPropagation();
        alert(213);
    });
});