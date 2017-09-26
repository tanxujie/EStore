$(function() {
    var id = getUrlParam('id');
    if (!isNumber(id)) {
        window.location = './product.html';
        return;
    }

    // auto load major category
    var $majorCategory = $("#selMajorCategory");
    function loadMajorCategory(defaultMajorId) {
        $majorCategory.empty();
        $.getJSON("/majorcategory/getAllOptions", function(data){
            var str = "";
            if (data && data.success) {
                var items = data.data;
                for (var i = 0; i < items.length; i++) {
                    str += ("<option value='" + items[i].value + "'>" + items[i].text + "</option>");
                }
            }

            var mc = $majorCategory.append(str).dropdown({
                onChange: function(val) {
                    loadMinorCategory(val);
                }
            });
            if (!!defaultMajorId) {
                mc.dropdown('set selected', defaultMajorId);
            }
        });
    }

    var $minorCategory = $("#selMinorCategory");
    function loadMinorCategory(defaultMajorId, defaultMinorId) {
        $minorCategory.empty();
        $.getJSON("/minorcategory/getAllOptions", {majorCategoryId: defaultMajorId}, function(data){
            var str = "";
            if (data && data.success) {
                var items = data.data;
                for (var i = 0; i < items.length; i++) {
                    str += ("<option value='" + items[i].value + "'>" + items[i].text + "</option>");
                }
            }
            var mc = $minorCategory.append(str).dropdown();
            if (!!defaultMinorId) {
                mc.dropdown('set selected', defaultMinorId);
            }
        });
    }

    // load product detail
    $.get('/product/getDetail', 
            {'productId': id}, 
            function(data){
                if (data.success) {
                    $("#txtId").val(data.data.id);
                    $("#txtUnderShelf").val(data.data.underShelf);
                    $("#lblCode").text(data.data.code);
                    $("#txtName").val(data.data.name);
                    $("#txtInnerDetail").val(data.data.innerDetail);
                    $("#txtExFactoryPricetxt").val(data.data.exFactoryPrice);
                    $("#txtFavorablePrice").val(data.data.favorablePrice);
                    $("#txtPrimaryPrice").val(data.data.primaryPrice);
                    $("#txtPhoneNumber").val(data.data.phoneNumber);
                    $("#txtWechatNumber").val(data.data.wechatNumber);
                    loadMajorCategory(data.data.majorCategoryId);
                    loadMinorCategory(data.data.majorCategoryId, data.data.minorCategoryId)
                }
            }
         );

    var $hiddenVideoFile = $("#hiddenVideoFile");
    var $hiddenUploadFile = $("#hiddenUploadFile");
    $('.ui.menu .ui.dropdown').dropdown({on: 'hover'});
    $('.ui.menu a.item').on('click', function() {
        $(this).addClass('active').siblings().removeClass('active');
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

    var dz = new Dropzone("#imageDropZone", {
        paramName: "images",
        maxFilesize: 10,
        addRemoveLinks: true,
        uploadMultiple: true,
        createImageThumbnails: true,
        thumbnailWidth:120,
        thumbnailHeight:120,
        thumbnailMethod: 'contain',
        dictRemoveLinks: "x",
        dictCancelUpload: "x",
        maxFiles:9,
        //dictDefaultMessage: '上传图片',
        dictRemoveFile: '删除',
        acceptedFiles: ".jpg,.jpeg",
        url: '/upload/image',
        success: function(file, resp) {
            $hiddenUploadFile.append($('<input type="hidden" name="imageNames" value="' + resp.data[0] + '" />'));
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

    $("#formAdd").form({
        fields: {
            code: {
                identifier: 'code',
                rules:[
                    {
                        type:'empty',
                        prompt: '请输入编码'
                    }
                ]
            },
            name: {
                identifier: 'name',
                rules:[
                    {
                        type:'empty',
                        prompt: '请输入描述'
                    }
                ]
            },
            exFactoryPrice: {
                identifier: 'exFactoryPrice',
                rules:[
                    {
                        type:'number',
                        prompt: '请输入正确的出厂价格'
                    }
                ]
            },
            favorablePrice: {
                identifier: 'favorablePrice',
                rules:[
                    {
                        type:'number',
                        prompt: '请输入正确的一级代理价格'
                    }
                ]
            },
            primaryPrice: {
                identifier: 'primaryPrice',
                rules:[
                    {
                        type:'number',
                        prompt: '请输入正确的二级代理价格'
                    }
                ]
            },
            phoneNumber: {
                identifier: 'phoneNumber',
                rules:[
                    {
                        type:'regExp[^$|^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,4-9]))\\d{8}$]',
                        prompt: '请输入正确的手机号码'
                    }
                ]
            },
            imageNames: {
                identifier: 'imageNames',
                rules:[
                    {
                        type:'empty',
                        prompt: '请上传图片'
                    }
                ]
            }
        }
    });

    $("#btnSave").click(function(event) {
        event.preventDefault();
        if ($("#formAdd").form('is valid')) {
            event.stopPropagation();
            $.post("/product/modify", 
                    $("#formAdd").serialize(), 
                    function() {
                window.location = './product.html';
            });
        }
    });

    $("#btnCancel").click(function(event){
        event.preventDefault();
        event.stopPropagation();
        window.location = './product.html';
    });
});