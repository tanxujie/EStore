$(function() {
    $("#messageArea").hide();
    $('.ui.menu .ui.dropdown').dropdown({on: 'hover'});
    $('.ui.menu a.item').on('click', function() {
        $(this).addClass('active').siblings().removeClass('active');
    });

    // auto load major category
    var $majorCategory;
    function loadMajorCategory() {
        var md = $("#selMajorCategory").empty();
        $.getJSON("/majorcategory/getAllOptions", function(data){
            var str = "";
            if (data && data.success) {
                var items = data.data;
                for (var i = 0; i < items.length; i++) {
                    str += ("<option value='" + items[i].value + "'>" + items[i].text + "</option>");
                }
                loadMinorCategory(items[0].value);
            }

            $majorCategory = md.append(str).dropdown({
                onChange: function(val) {
                    loadMinorCategory(val);
                }
            });
        });
    }

    var $minorCategory = $("#selMinorCategory");
    function loadMinorCategory(majorCategoryId) {
        $minorCategory.empty();
        $.getJSON("/minorcategory/getAllOptions", {majorCategoryId: majorCategoryId}, function(data){
            var str = "";
            if (data && data.success) {
                var items = data.data;
                for (var i = 0; i < items.length; i++) {
                    str += ("<option value='" + items[i].value + "'>" + items[i].text + "</option>");
                }
            }
            $minorCategory.append(str).dropdown();
        });
    }

    loadMajorCategory();

    $("#formAdd").form({
        //on: 'submit',
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

    var $imageFile = $("#imageFile").fileinput({
        theme: "explorer",
        uploadUrl: "/upload/image",
        deleteUrl: "/upload/image/remove/",
        uploadAsync: false,
        language: 'zh',
        showUpload: false,
        showRemove: false,
        showClose: false,
        showCancel: false,
        showZoom: false,
        showDownload: false,
        showBrowse: false,
        browseOnZoneClick: true,
        allowedPreviewTypes: ['image'],
        allowedFileTypes: ['image'],
        allowedFileExtensions: ['jpg', 'jpeg'],
        hiddenThumbnailContent: true,
        overwriteInitial: false,
        initialPreviewAsData: true,
        minFileCount: 1,
        maxFileCount: 9
    }).on("filebatchselected", function(event, files){
        $imageFile.fileinput("upload");
    }).on("filebatchuploadsuccess", function(event, data, children) {
        var filenames = data.response.data;
        for (var i = 0, len = (children.length <= filenames.length ? children.length : filenames.length); i < len; i++) {
            $('[name="imageNames"]', children[i]).val(filenames[i]);
        }
    });

    var $videoFile = $("#videoFile").fileinput({
        theme: "explorer",
        uploadUrl: "/upload/image",
        deleteUrl: "/upload/image/remove/",
        uploadAsync: false,
        language: 'zh',
        showUpload: false,
        showRemove: false,
        showClose: false,
        showCancel: false,
        showZoom: false,
        showDownload: false,
        showBrowse: false,
        browseOnZoneClick: true,
        allowedPreviewTypes: ['video'],
        allowedFileTypes: ['video'],
        allowedFileExtensions: ['mp4'],
        hiddenThumbnailContent: true,
        overwriteInitial: false,
        initialPreviewAsData: true,
        maxFileCount: 1
    }).on("filebatchselected", function(event, files){
        $videoFile.fileinput("upload");
    }).on("filebatchuploadsuccess", function(event, data, children){
        var filenames = data.response.data;
        $('[name="videoName"]', children[0]).val(filenames[0]);
    });

    $("#btnSave").click(function(event) {
        event.preventDefault();
        if ($("#formAdd").form('is valid')) {
            event.stopPropagation();
            $.post("/product/add", 
                    $("#formAdd").serialize(), 
                    function(data) {
                if (data && data.success) {
                    $("#messageArea").hide();
                    window.location = './product.html';
                } else {
                    $("#message").text(data.data);
                    $("#messageArea").show();
                }
            });
        }
    });

    $("#btnCancel").click(function(event){
        event.preventDefault();
        event.stopPropagation();
        window.location = './product.html';
    });
});