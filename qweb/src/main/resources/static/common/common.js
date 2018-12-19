/* 开layer弹框 */
function openLayerDialog(url, title, width, height){
    layer.open({
        type: 2,
        title: title,
        closeBtn: 1,
        maxmin: 1,
        shade: [0],
        area: [width, height],
        skin: 'layui-layer-lan',
        content: [url]
    });
}

/* 参数检查 总 */
function checkParams() {
    return checkNotNull();
}

/* 参数检查 不能为空 */
function checkNotNull() {
    var result = true;
    $(".checkNotNull").each(function () {
        if ($(this).val().length == 0 || $(this).val() == undefined || $(this).val() == null) {
            layer.tips('这个必须写', $(this), {tips: [3, '#009688'], tipsMore: true});
            result = false;
        }
    });
    return result;
}