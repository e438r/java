<div class="header">
    <ol class="breadcrumb">
        <li><a href="/">首页</a></li>
        <li class="active">用户</li>
    </ol>
</div>
<div id="page-inner">
    <div class="card">
        <div class="card-content">
            <div class="row">
                <div class="input-field col s6">
                    <input type="text" id="selectName" name="selectName" class="input-small" placeholder="用户名"/>
                </div>
                <div class="input-field col s6">
                    <input type="text" id="selectPhone" name="selectPhone" class="input-small" placeholder="手机号"/>
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
    <div class="card hide" id="addform">
        <div class="card-action">
            编辑用户
        </div>
        <div class="card-content">
            <form class="col s12">
                <div class="row">
                    <div class="input-field col s6">
                        <input type="hidden" id="id">
                        <input type="text" id="userName" name="userName" class="input-small checkNotNull" placeholder="登录名"/>
                        <label for="userName">登录名</label>
                    </div>
                    <div class="input-field col s6">
                        <input type="text" id="age" name="age" class="input-small"
                               placeholder="年龄"/>
                        <label for="age">年龄</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s6">
                        <input type="text" id="phone" name="phone" class="input-small checkNotNull"
                               placeholder="手机号"/>
                        <label for="phone">手机号</label>
                    </div>
                    <div class="input-field col s6">
                        <input type="text" id="email" name="email" class="input-small"
                               placeholder="邮箱"/>
                        <label for="email">邮箱</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s6">
                        <input type="text" id="qq" name="qq" class="input-small"
                               placeholder="QQ"/>
                        <label for="qq">QQ</label>
                    </div>
                    <div class="input-field col s6">
                        <input type="text" id="trueName" name="trueName" class="input-small checkNotNull"
                               placeholder="真实姓名"/>
                        <label for="trueName">真实姓名</label>
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
        var url = "/user/add";
        if($('#id').val()!=""){
            url = "/user/update";
        }
        $.ajax({
            url: url,
            data: {
                id: $('#id').val(),
                userName: $('#userName').val(),
                age: $('#age').val(),
                phone: $('#phone').val(),
                email:$('#email').val(),
                qq:$('#qq').val(),
                trueName:$('#trueName').val()
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
                {name: 'userName', type: 'string'},
                {name: 'age', type: 'int'},
                {name: 'phone', type: 'string'},
                {name: 'email', type: 'string'},
                {name: 'qq', type: 'string'},
                {name: 'trueName', type: 'string'}
            ],
            id: 'id',
            url: '/user/list?name=' + $('#selectName').val()+'&phone='+$('#selectPhone').val(),
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
                {text: '登录名', datafield: 'userName', editable: false, width: '38%'},
                {text: '年龄', datafield: 'age', editable: false, width: '8%'},
                {text: '手机', datafield: 'phone', editable: false, width: '35%'},
                {text: '邮箱', datafield: 'email', editable: false, width: '35%'},
                {text: 'QQ', datafield: 'qq', editable: false, width: '35%'},
                {text: '真实姓名', datafield: 'trueName', editable: false, width: '35%'},
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
                url: '/user/del',
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
            url: '/user/get',
            data: {
                id: id
            },
            async: false,
            type: "POST",
            dataType: "json",
            success: function (data) {
                $('#id').val(data.id);
                $('#userName').val(data.userName);
                $('#age').val(data.age);
                $('#phone').val(data.phone);
                $('#email').val(data.email);
                $('#qq').val(data.qq);
                $('#trueName').val(data.trueName);
                $('#showForm').click();
            },
            error: function (data) {
                layer.msg(data);
            }
        });
    }
</script>