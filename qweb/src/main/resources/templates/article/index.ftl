<div class="header">
    <ol class="breadcrumb">
        <li><a href="/">首页</a></li>
        <li class="active">文章</li>
    </ol>
</div>
<div id="page-inner">
    <div class="card">
        <div class="card-content">
            <div class="row">
                <div class="input-field col s6">
                    <input type="text" id="selectName" name="selectName" class="input-small" placeholder="标题"/>
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
            编辑文章
        </div>
        <div class="card-content">
            <form class="col s12">
                <div class="row">
                    <div class="input-field col s6">
                        <input type="hidden" id="id">
                        <input type="text" id="title" name="title" class="input-small checkNotNull" placeholder="标题"/>
                        <label for="title">标题</label>
                    </div>
                    <div class="input-field col s6">
                        <input type="text" id="tag" name="tag" class="input-small checkNotNull"
                               placeholder="tag"/>
                        <label for="tag">文章标签</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s6">
                        <input type="text" id="category" name="category" class="input-small checkNotNull"
                               placeholder="分类编号"/>
                        <label for="category">分类编号</label>
                    </div>
                    <div class="input-field col s6">
                        <input type="text" id="sortBy" name="sortBy" class="input-small checkNotNull"
                               placeholder="排序"/>
                        <label for="sortBy">排序</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <input type="text" id="image" name="image" class="input-small" />
                        <label for="image">配图地址</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                       <textarea id="content" name="content" class="input-medium" style="height:500px;"></textarea>
                        <label for="content">正文</label>
                    </div>
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

<script>
    loadDataTable();
    $('#search').on('click',function () {
        loadDataTable();
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
                content: $('#content').val(),
                auther: $('#auther').val(),
                tag:$('#tag').val(),
                sortBy:$('#sortBy').val()
            },
            async: false,
            type: "POST",
            dataType: "json",
            success: function (data) {
                var imgurl = "/image/add";
                var aid = $('#id').val();
                var newId;
                if(aid!=""){
                    imgurl = "/image/update";
                    newId = aid;
                }else{
                    newId = data.errorMessage;
                }
                $.ajax({
                    url: imgurl,
                    data: {
                        aid: newId,
                        imgurl: $('#image').val(),
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
                var categoryurl = "/rel/add";
                if(aid!=""){
                    categoryurl = "/rel/update";
                }
                $.ajax({
                    url: categoryurl,
                    data: {
                        aid: newId,
                        cid: $('#category').val(),
                    },
                    async: false,
                    type: "POST",
                    dataType: "json",
                    success: function (data) {
                    },
                    error: function (data) {

                    }
                });
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
                {name: 'sortBy', type: 'string'}
            ],
            id: 'id',
            url: '/article/list?title=' + $('#selectName').val(),
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
                $('#content').val(data.content);
                $('#tag').val(data.tag);
                $('#auther').val(data.auther);
                $('#sortBy').val(data.sortBy);
                $('#showForm').click();
            },
            error: function (data) {
                layer.msg(data);
            }
        });
        $.ajax({
            url: '/image/get',
            data: {
                id: id
            },
            async: false,
            type: "POST",
            dataType: "json",
            success: function (data) {
                $('#image').val(data.imgurl);
            },
            error: function (data) {
                layer.msg(data);
            }
        });
    }
</script>