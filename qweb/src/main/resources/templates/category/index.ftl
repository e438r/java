<div class="header">
    <ol class="breadcrumb">
        <li><a href="/">首页</a></li>
        <li class="active">分类</li>
    </ol>
</div>
<div id="page-inner">
    <div class="card">
        <div class="card-content">
            <div class="row">
                <div class="input-field col s6">
                    <input type="text" id="selectName" name="selectName" class="input-small" placeholder="分类名"/>
                </div>
                <div class="input-field col s6">
                    <a class="waves-effect waves-light btn" id="search">搜索</a>
                    <a class="waves-effect waves-light btn" id="showForm">添加</a>
                </div>
            </div>
        </div>
    </div>
    <div id="DataGrid"></div>
    <div class="card hide" id="addform">
        <div class="card-action">
            添加分类
        </div>
        <div class="card-content">
            <form class="col s12">
                <div class="row">
                    <div class="input-field col s6">
                        <input type="text" id="pid" name="pid" class="input-small checkNotNull" placeholder="分类编号"/>
                        <label for="pid">所属分类编号</label>
                    </div>
                    <div class="input-field col s6">
                        <input type="text" id="nodeName" name="nodeName" class="input-small checkNotNull"
                               placeholder="分类名"/>
                        <label for="nodeName">分类名</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s6">
                        <input type="text" id="nodeDesc" name="nodeDesc" class="input-small checkNotNull"
                               placeholder="分类描述"/>
                        <label for="pid">分类描述</label>
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
    $('#search').on('click',function () {
        loadDataTable();
    });
    /* 结束编辑，调用update方法 */
    $("#DataGrid").on('cellendedit', function (event) {
        var args = event.args;
        $.ajax({
            url: '/category/update',
            data: {
                id: args.row.id,
                pid: args.row.pid,
                nodeName: args.row.nodeName,
                nodeDesc: args.row.nodeDesc
            },
            async: false,
            type: "POST",
            dataType: "json",
            success: function (data) {
                layer.msg(data.errorMessage);
                loadDataTable();
            },
            error: function (data) {
                layer.msg(data.errorMessage);
            }
        });
        $("#DataGrid").jqxGrid({editable: false});
    });
    $('#submit').click(function () {
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
                layer.msg(data.errorMessage);
            },
            error: function (data) {

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
            url: '/category/list?nodeName=' + $('#selectName').val(),
            cache: false,
            root: 'Rows',
            beforeprocessing: function (data) {
                //根据实际做相应的调整不一定是data[0].TotalRows;建议写个debugger;调试
                if (data.length > 0){
                    source.totalrecords = data[0].count;
                } else {
                    source.totalrecords = 0;
                }
            }
        };
        var dataAdapter = new $.jqx.dataAdapter(source, {
            downloadComplete: function (data, status, xhr) {
            },
            loadComplete: function (data) {
            },
            loadError: function (xhr, status, error) {

            },
            beforeLoadComplete: function (records) { // 修饰数据
                for (var v = 0; v < records.length; v++) {
                    if (records[v] == null){
                        break;
                    }
                    /* 1. 所有行增加删除按钮 */
                    records[v].function = '<a class="deleteButton" onclick="deleteData(\'' + records[v].id + '\')">删掉</a>'
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
            pageable: true,
            virtualmode: true,
            rendergridrows: function (params) {
                return params.data;
            },
            autoheight: true,
            sortable: true,
            editable: false,
            columnsresize: true,
            selectionMode: 'multiplecellsadvanced',
            rowsheight: 35,
            columns: [
                {text: '分类编号', datafield: 'id', editable: false, width: '13%'},
                {text: '所属分类编号', datafield: 'pid', editable: true, width: '8%'},
                {text: '分类名', datafield: 'nodeName', editable: true, width: '8%'},
                {text: '分类描述', datafield: 'nodeDesc', editable: true, width: '35%'},
                {text: '编辑', datafield: 'function', editable: false, width: '8%'}
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
                url: '/category/del',
                data: {
                    id: id
                },
                async: false,
                type: "POST",
                dataType: "json",
                success: function (data) {
                    layer.msg(data.errorMessage);
                    loadDataTable();
                },
                error: function (data) {

                }
            });
        });
    }

    function editData() {
        $("#DataGrid").jqxGrid({editable: true});
        layer.msg("开启编辑模式");
    }
</script>