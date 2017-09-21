$(function(){
    $("#messageArea").hide();
    $('.ui.menu .ui.dropdown').dropdown({on: 'hover'});
    $('.ui.menu a.item').on('click', function() {
        $(this).addClass('active').siblings().removeClass('active');
    });

    $("#btnSearch").click(function(event) {
        searchProduct();
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
                        $("#btnDelConfirm").click(function(e){
                          e.preventDefault();
                          $.post('/product/remove', {productId: id}, function(){
                              searchProduct();
                          });
                        });
                        removeProduct();
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

    function removeProduct() {
        $("#modalConfirm").modal('show');
    }
});