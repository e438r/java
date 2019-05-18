<div class="header">
    <ol class="breadcrumb">
        <li><a href="/">首页</a></li>
        <li class="active">文章</li>
    </ol>
</div>
<div id="page-inner">
    <div class="card">
        <div class="card-content">
            <input type="text" id="selectName" name="selectName" class="input-small" placeholder="分类名"/>
            <a class="waves-effect waves-light btn" id="search">搜索</a>
            <a class="waves-effect waves-light btn" id="showForm">添加</a>
        </div>
    </div>
    <div id="DataGrid"></div>
    <div class="card hide" id="addform">
        <div class="card-action">
            添加文章
        </div>
        <div class="card-content">
            <form class="col s12">
                <div class="row">
                    <div class="input-field col s6">
                        <input type="text" id="title" name="title" class="input-small checkNotNull" placeholder="标题"/>
                        <label for="pid">标题</label>
                    </div>
                    <div class="input-field col s6">
                        <input type="text" id="tag" name="tag" class="input-small checkNotNull"
                               placeholder="tag"/>
                        <label for="nodeName">文章标签</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s6">
                        <input type="text" id="category" name="category" class="input-small checkNotNull"
                               placeholder="分类"/>
                        <label for="pid">分类</label>
                    </div>
                    <div class="input-field col s6">
                        <input type="text" id="order" name="order" class="input-small checkNotNull"
                               placeholder="排序"/>
                        <label for="pid">排序</label>
                    </div>
                </div>
                <div class="row">
                    <script id="editor" type="text/plain" style="width:100%;height:200px;">
                    </script>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <a class="waves-effect waves-light btn" id="submit">提交</a>
                        <a class="waves-effect waves-light btn" id="closePannel">返回</a>
                    </div>
                </div>
            </form>
            <div class="clearBoth"></div>
        </div>
    </div>
</div>
                        <script type="text/javascript" charset="utf-8" src="/editor/ueditor.config.js"></script>
                    <script type="text/javascript" charset="utf-8" src="/editor/ueditor.all.min.js"> </script>
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
                title: args.row.title,
                content: args.row.content,
                auther: args.row.auther,
                tag:args.row.tag,
                createTime:args.row.createTime,
                order:args.row.order
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
        var url = "/article/add";
        if($('#id').val()!=""){
            url = "/article/update";
        }
        $.ajax({
            url: url,
            data: {
                id: $('#id').val(),
                title: $('#title').val(),
                content: getContent(),
                auther: $('#auther').val(),
                tag:$('#tag').val(),
                order:$('#order').val()
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
                {name: 'title', type: 'string'},
                {name: 'content', type: 'string'},
                {name: 'auther', type: 'string'},
                {name: 'tag', type: 'string'},
                {name: 'createTime', type: 'string'},
                {name: 'order', type: 'string'}
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

    function editData(id) {
        $.ajax({
            url: '/article/get',
            data: {
                id: id
            },
            async: false,
            type: "POST",
            dataType: "json",
            success: function (data) {
                $('#id').val(data.id);
                $('#title').val(data.title);
                setContent(data.content);
                $('#tag').val(data.tag);
                $('#auther').val(data.auther);
                $('#order').val(data.order);
                $('#showForm').click();
            },
            error: function (data) {
                layer.msg(data);
            }
        });
    }
    var ue = UE.getEditor('editor');
    function getContent() {
        return UE.getEditor('editor').getContent();
    }
    function setContent(content) {
        UE.getEditor('editor').setContent(content, true);
    }
</script>