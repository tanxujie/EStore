$(function(){
    var $addModal = $("#modalAdd");
    var $hiddenVideoFile = $("#hiddenVideoFile");
    var $hiddenUploadFile = $("#hiddenUploadFile");
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

//    $("#selMajorCategory").dropdown({
//        apiSetting: {
//            url: "//majorcategory/getAllOptions"
//        }
//    });

    var dz = new Dropzone("#videoDropZone", {
        paramName: "videos",
        maxFilesize: 50,
        addRemoveLinks: true,
        uploadMultiple: true,
        thumbnailWidth:100,
        thumbnailHeight:100,
        thumbnailMethod: 'contain',
        maxFiles:1,
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
        maxFilesize: 50,
        addRemoveLinks: true,
        uploadMultiple: true,
        thumbnailWidth:100,
        thumbnailHeight:100,
        thumbnailMethod: 'contain',
        maxFiles:9,
        //acceptedFiles:"image/*",
        url: '/upload/image',
        success: function(file, resp) {
            $("#messageArea").hide();
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

    $("#btnSearch").click(function(event) {
        searchProduct();
    });

    $("#btnAdd").click(function(event){
        $addModal.modal('setting', 'closable', false).modal('show');
    });

    $("#btnSave1").click(function(event){
        $.post("/product/save", $("#formAdd").serialize()).done(function(event){
            clearForm();
            $addModal.modal('close');
            searchProduct();
        });
    });

    $("#btnCancel1").click(function(event){
        clearForm();
        $addModal.modal('close');
    });

    function searchProduct() {
        var condition = $("#txtCondition").val();
        var $container = $("#imageContainer");
        $container.empty();
        $("#message").text("");
        $("#messageArea").hide();
        $.getJSON("/product/search", { condition: condition }, function(data, textStatus, req) {
            if (data && data.success) {
                var pinfos = data.data;
                if (pinfos && pinfos.length) {
                    var imgStr = '';
                    $.each(pinfos, function(i, d) {
                        imgStr = imgStr + '<div class="card" style="width:260px;height:300px;"><div class="image" style="height:200px;"><img class="ui wireframe image" style="width:260px;height:200px;" src="'+d.imageName+'"></div><div class="content" style="height:50px;"><div class="header">'+(d.code||'')+'</div><div class="meta">'+(d.name||'')+'</div></div><div class="extra content"><div class="left aligned">价格：'+(d.price||'')+'</div><div class="right aligned"><i class="large edit icon" style="cursor:pointer;" pid="'+d.id+'"></i><i class="large remove icon" style="cursor:pointer;" pid="'+d.id+'"></i></div></div></div>';
                    });
                    
                    $container.append($(imgStr));
                    $(".remove.icon", $container).click(function(event){
                        var id = $(this).attr("pid");
                        removeProduct(id);
                    });
                    $(".edit.icon", $container).click(function(event){
                        var id = $(this).attr("pid");
                        openProductEdit(id);
                    });
                }
            } else {
                $("#message").text(data.data);
                $("#messageArea").show();
            }
        });
    }

    function openProductEdit(pid) {
        $.getJSON("/product/getDetail", {productId: pid}, function(data, textStatus, req) {
            if (data.success) {
                var d = data.data;
                if (d) {
                    $("#txtCode").val(d.code);
                    $("#txtName").val(d.name);
                    $majorCategory.dropdown('set value', 3);
                    $addModal.modal('setting', 'closable', false).modal('show');
                    
                }
            } else {
                alert(data.data);
            }
        });
    }

    function removeProduct(pid) {
        $.post('/product/remove', {productId: pid}, function(){
            searchProduct();
        });
    }

    function clearForm() {
        $("#formAdd input").val("");
        $hiddenUploadFile.empty();
        dz.removeAllFiles();
    }

    function validate() {
        $("#formAdd").form({
            fields: {
                code : {
                    identifier: 'code',
                    rules: [
                        {
                            type: 'number',
                            prompt: 'Only Number'
                        }
                    ]
                }
            }
        });
    }
    
    validate();
});