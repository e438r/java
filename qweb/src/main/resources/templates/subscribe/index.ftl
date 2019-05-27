<div class="header">
    <ol class="breadcrumb">
        <li><a href="/">首页</a></li>
        <li class="active">预约</li>
    </ol>
</div>
<div id="page-inner">
    <div class="card">
        <div class="card-content">
            <div class="row">
                <div class="input-field col s6">
                    <input type="text" id="startTime" name="startTime" class="input-small checkNotNull Wdate" placeholder="yyyy-MM-dd" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})"/>
                    <label for="pid">起始时间</label>
                </div>
                <div class="input-field col s6">
                    <input type="text" id="endTime" name="endTime" class="input-small checkNotNull Wdate"
                           placeholder="yyyy-MM-dd" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})"/>
                    <label for="nodeName">结束时间</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <a class="waves-effect waves-light btn" id="search">搜索</a>
                    <a class="waves-effect waves-light btn" id="showForm">添加</a>
                </div>
            </div>
        </div>
    </div>
    <div id="DataGrid"></div>
</div>
<script language="javascript" type="text/javascript" src="/My97DatePicker/WdatePicker.js"></script>

<script>
    loadDataTable();
    $('#search').on('click',function () {
        loadDataTable();
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
                {name: 'title', type: 'string'},
                {name: 'content', type: 'string'},
                {name: 'auther', type: 'string'},
                {name: 'tag', type: 'string'},
                {name: 'createTime', type: 'string'},
                {name: 'sortBy', type: 'string'}
            ],
            id: 'id',
            url: '/article/list?title=' + $('#selectName').val()
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
                    records[v].function = '<a class="deleteButton" onclick="deleteData(\'' + records[v].id + '\')">删掉</a>'
                        + '<a class="deleteButton" onclick="editData(\'' + records[v].id + '\')">编辑</a>';
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
            autoheight: true,
            sortable: true,
            editable: false,
            columnsresize: true,
            selectionMode: 'multiplecellsadvanced',
            rowsheight: 35,
            columns: [
                {text: '标题', datafield: 'title', editable: false, width: '8%'},
                {text: '作者', datafield: 'auther', editable: false, width: '8%'},
                {text: '标签', datafield: 'tag', editable: false, width: '35%'},
                {text: '创建时间', datafield: 'createTime', editable: false, width: '35%'},
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
                url: '/article/del',
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

</script>