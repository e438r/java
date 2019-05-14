<div class="header">
    <h1 class="page-header">
        分类管理
        <small></small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="/">首页</a></li>
        <li class="active">分类</li>
    </ol>
</div>
<div id="page-inner">
    <div class="card">
        <div class="card-action">

        </div>
        <div class="card-content">
            <a class="waves-effect waves-light btn" id="showForm">添加</a>
        </div>
    </div>
    <div id="DataGrid"></div>
    <div class="card hide" id="addform">
        <div class="card-action">
            Basic Form Elements
        </div>
        <div class="card-content">
            <form class="col s12">
                <div class="row">
                    <div class="input-field col s6">
                        <input type="text" id="pid" name="pid" class="input-small checkNotNull" placeholder="pid"/>
                        <label for="pid">PID</label>
                    </div>
                    <div class="input-field col s6">
                        <input type="text" id="nodeName" name="nodeName" class="input-small checkNotNull"
                               placeholder="nodeName"/>
                        <label for="nodeName">nodeName</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s6">
                        <input type="text" id="nodeDesc" name="nodeDesc" class="input-small checkNotNull"
                               placeholder="nodeDesc"/>
                        <label for="pid">nodeDesc</label>
                    </div>
                    <div class="input-field col s6">
                        <a class="waves-effect waves-light btn" id="submit">提交</a>
                        <a class="waves-effect waves-light btn" id="closePannel">返回</a>
                    </div>
                </div>
            </form>
            <div class="clearBoth"></div>
        </div>
    </div>
</div>
<script>
    loadDataTable();
    /* 结束编辑，调用update方法 */
    $("#DataGrid").on('cellendedit', function (event) {
        var args = event.args;
        $.ajax({
            url: '/category/update',
            data: {
                id: $('#id').val(),
                pid: args.row.pid,
                nodeName: args.row.nodeName,
                nodeDesc: args.row.nodeDesc
            },
            async: false,
            type: "POST",
            dataType: "json",
            success: function (data) {
                layer.msg(data.data);
                loadDataTable();
            },
            error: function (data) {
                var message = JSON.parse(data.responseText).message;
                layer.msg(message, function () {
                });
            }
        });
        $("#DataGrid").jqxGrid({editable: false});
    });
    $('#addform').click(function () {
        if (!checkParams()) {
            return;
        }
        $.ajax({
            url: '/category/add',
            data: {
                pid: $('#pid').val(),
                nodeName: $('#nodeName').val(),
                nodeDesc: $('#nodeDesc').val()
            },
            async: false,
            type: "POST",
            dataType: "json",
            success: function (data) {
                layer.msg(data.data);
            },
            error: function (data) {
                var message = JSON.parse(data.responseText).message;
                layer.msg(message, function () {
                });
            }
        });
    });

    $('#showForm').click(function () {
        $('#addform').removeClass('hide');
        $('#DataGrid').hide(500);
        $('#addform').show(500);
    });
    $('#closePannel').click(function () {
        $('#addform').hide(500);
        $('#DataGrid').show(500);
    });
    function loadDataTable() {
        console.log('loadTable');
        layer.load(1);
        // if (!checkParams()) {
        //     layer.closeAll('loading');
        //     return;
        // }
        var source = {
            datatype: "json",
            datafields: [
                {name: 'id', type: 'int'},
                {name: 'pid', type: 'string'},
                {name: 'nodeName', type: 'string'},
                {name: 'nodeDesc', type: 'string'}
            ],
            id: 'id',
            url: '/category/list?id=' + $('#id').val() + '&pid=' + $('#pid').val() + '&nodeName=' + $('#nodeName').val() + '&nodeDesc=' + $('#nodeDesc').val()
        };
        var dataAdapter = new $.jqx.dataAdapter(source, {
            downloadComplete: function (data, status, xhr) {
            },
            loadComplete: function (data) {
                buildStyle(parent.MAIN_STYLE); // 加载完成后，自动再构造一遍页面效果
            },
            loadError: function (xhr, status, error) {
                layer.msg(JSON.parse(xhr.responseText).message, function () {
                });
            },
            beforeLoadComplete: function (records) { // 修饰数据
                for (var v = 0; v < records.length; v++) {
                    /* 1. 所有行增加删除按钮 */
                    records[v].function = '<a class="deleteButton" onclick="deleteData(\'' + records[v].characterIndex + '\', \'' + records[v].tppCode + '\', \'' + records[v].characterCode + '\')">删掉</a>'
                        + '<a class="deleteButton" onclick="editData()">编辑</a>';
                }
                /*   }else{
                       $("#dbTableName").val("")
                   }*/
                return records;
            }
        });
        $("#DataGrid").jqxGrid({
            width: '100%',
            source: dataAdapter,
            pageable: false,
            autoheight: true,
            sortable: true,
            editable: false,
            columnsresize: true,
            selectionMode: 'multiplecellsadvanced',
            rowsheight: 35,
            columns: [
                {text: 'id', datafield: 'id', editable: false, width: '13%'},
                {text: 'pid', datafield: 'pid', editable: true, width: '8%'},
                {text: 'nodeName', datafield: 'nodeName', editable: true, width: '8%'},
                {text: 'nodeDesc', datafield: 'nodeDesc', editable: true, width: '35%'},
                {text: 'function', datafield: 'option', editable: false, width: '8%'}
            ]
        });
        layer.closeAll('loading');
    }

    /* 删除指标 */
    function deleteData(id) {
        layer.confirm('确认删除？', {
            title: '删除',
            btn: [' 删除 ', ' 取消 '],
            icon: 3,
            skin: 'layui-layer-lan'
        }, function (index) {
            layer.close(index);
            $.ajax({
                url: '/category/delete',
                data: {
                    id: $('#id').val()
                },
                async: false,
                type: "POST",
                dataType: "json",
                success: function (data) {
                    layer.msg(data.data);
                    loadDataTable();
                },
                error: function (data) {
                    var message = JSON.parse(data.responseText).message;
                    layer.msg(message, function () {
                    });
                }
            });
        });
    }

    function editData() {
        $("#DataGrid").jqxGrid({editable: true});
        layer.msg("开启编辑模式");
    }
</script>