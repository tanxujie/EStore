$(function() {
    var id = getUrlParam('id');
    if (!isNumber(id)) {
        window.location = './agent.html';
        return;
    }

    // load details
    $.get('/agent/getDetail', 
            {'id': id}, 
            function(data){
                if (data.success) {
                    $("#txtId").val(data.data.id);
                    $("#txtName").val(data.data.name);
                    $("#txtPhoneNumber").val(data.data.phoneNumber);
                    $("#txtRoleCode").val(data.data.roleCode);
                    $("#txtEnabled").val(data.data.enabled);
                }
            }
         );

    var $hiddenVideoFile = $("#hiddenVideoFile");
    var $hiddenUploadFile = $("#hiddenUploadFile");
    $('.ui.menu .ui.dropdown').dropdown({on: 'hover'});
    $('.ui.menu a.item').on('click', function() {
        $(this).addClass('active').siblings().removeClass('active');
    });

    $("#formAdd").form({
        fields: {
            name: {
                identifier: 'name',
                rules:[
                    {
                        type:'empty',
                        prompt: '请输入代理姓名'
                    },
                    {
                        type:'maxLength[10]',
                        prompt: '代理姓名最多输入10个字符'
                    }
                ]
            },
            phoneNumber: {
                identifier: 'phoneNumber',
                rules:[
                    {
                        type:'empty',
                        prompt: '请输入电话'
                    },
                    {
                        type:'maxLength[11]',
                        prompt: '电话最多输入11个字符'
                    }
                ]
            },
            roleCode: {
                identifier: 'roleCode',
                rules:[
                    {
                        type:'empty',
                        prompt: '请输入等级'
                    },
                    {
                        type:'maxLength[10]',
                        prompt: '等级最多输入10个字符'
                    }
                ]
            },
            enabled: {
                identifier: 'enabled',
                rules:[
                    {
                        type:'empty',
                        prompt: '请输入状态'
                    },
                    {
                        type:'maxLength[5]',
                        prompt: '状态最多输入5个字符'
                    }
                ]
            }
        }
    });

    $("#btnSave").click(function(event) {
        event.preventDefault();
        event.stopPropagation();
        if ($("#formAdd").form('is valid')) {
            $.post("/agent/modify", 
                    $("#formAdd").serialize(), 
                    function() {
                window.location = './agent.html';
            });
        }
    });
});