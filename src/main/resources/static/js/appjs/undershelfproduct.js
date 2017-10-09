$(function(){
    $("#messageArea").hide();
    $('.ui.menu .ui.dropdown').dropdown({on: 'hover'});
    $('.ui.menu a.item').on('click', function() {
        $(this).addClass('active').siblings().removeClass('active');
    });

    function searchProduct() {
        var condition = $("#txtCondition").val();
        var $container = $("#imageContainer");
        $container.empty();
        $("#message").text("");
        $("#messageArea").hide();
        $.getJSON("/product/searchUnderShelf", { condition: condition }, function(data, textStatus, req) {
            if (data && data.success) {
                var pinfos = data.data;
                if (pinfos && pinfos.length) {
                    var imgStr = '';
                    $.each(pinfos, function(i, d) {
                        imgStr = imgStr + '<div class="card" style="width:260px;height:360px;"><div class="image" style="height:200px;"><img class="ui wireframe image" style="width:260px;height:200px;" src="'+d.imageName+'"></div><div class="content" style="height:50px;"><div class="header">'+(d.code||'')+'</div><div class="extra content"><div class="meta">'+(d.name||'')+'</div><div class="meta">价格：'+(d.price||'')+'</div></div></div><div class="footer"><div class="right aligned"><i>'+d.shelfStatus+'</i>&nbsp;&nbsp;&nbsp;<i class="large edit icon" title="编辑该产品" style="cursor:pointer;" pid="'+d.id+'"></i><i class="large remove icon" title="删除该产品" style="cursor:pointer;" pid="'+d.id+'"></i></div></div><br></div>';
                    });

                    $container.append($(imgStr));
                    $(".remove.icon", $container).unbind('click');
                    $(".edit.icon", $container).unbind('click');
                    $(".remove.icon", $container).click(function(event) {
                        event.preventDefault();
                        event.stopPropagation();
                        var id = $(this).attr("pid");
                        removeProductConfirm(id);
                    });
                    $(".edit.icon", $container).click(function(event){
                        var id = $(this).attr("pid");
                        window.location = './editproduct.html?id=' + id;
                        return;
                    });
                    $(".ui.shelf", $container).click(function(event){
                        var id = $(this).attr("pid");
                        $.post('/product/changeShelf', {productId: id}, function(d){
                            if (d.success) {
                                searchProduct();
                            }
                        });
                    });
                }
            } else {
                $("#message").text(data.data);
                $("#messageArea").show();
            }
        });
    }

    function removeProductConfirm(id) {
        $("#btnDelConfirm").unbind('click');
        $("#btnDelConfirm").click(function(event){
            event.preventDefault();
            event.stopPropagation();
            $.post('/product/remove', {productId: id}, function(){
                searchProduct();
                $("#modalConfirm").modal('hide');
            });
        });
        $("#modalConfirm").modal('show');
    }

    $("#btnSearch").click(function(event) {
        event.preventDefault();
        event.stopPropagation();
        // do search
        searchProduct();
    });

    // automatically load all
    searchProduct();
});