<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width =device-width, initial-scale=1, maximum-scale=1"/>
    <title></title>
    <meta charset="utf-8"/>
    <!--layui的css-->
    <script type="text/javascript" src="/jquery/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="/suiNavbar/sui.nav.min.js"></script>
    <script type="text/javascript" src="/layer/layer.js"></script>
    <script type="text/javascript" src="/common/common.js"></script>
    <script type="text/javascript" src="/layui/layui.all.js"></script>
    <script type="text/javascript" src="/layui/layui-xtree.js"></script>
    <script type="text/javascript" src="/menu/dist/typeit.min.js"></script>
    <script type="text/javascript" src="/menu/src/scripts.min.js"></script>
    <script type="text/javascript" src="/numberCount/count.js"></script>
    <script type="text/javascript" src="/bootstrap/js/bootstrap-treeview.js"></script>

    <link rel="stylesheet" type="text/css" href="/suiNavbar/sui.nav.css"/>
    <link rel="stylesheet" type="text/css" href="/layui/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="/menu/dist/typeit.min.css"/>
    <link rel="stylesheet" type="text/css" href="/menu/src/style.css"/>
    <link rel="stylesheet" type="text/css" href="/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="/bootstrap/css/bootstrap-treeview.css">
</head>
<body>
<div class="divButton" id="QCREDIT_DATA" characterIndex="userId" style="float:right">新增征信数据</div>
<table id="userTable" width="100%" lay-filter="demoEvent"></table>

</body>
<script>
$(document).ready(function(){
$('#QCREDIT_DATA').on('click',function(){
$.post('/user/get',{id:1},function(data){
alert(data.errorMessage);
});

});
});

</script>
</html>