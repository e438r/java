
// 临时变量记录，
var context = {};

function compute() {
    var tppcode=$("#tppcode").val();
    var productNo = $("#productNo").val();
    var scene = $("#scene").val();
    var envparam = $("#envparam").val();
    ruleCompute(tppcode,productNo,scene,envparam);
};

function ruleCompute(tppcode,productNo,scene,envparam){

    $.ajax({
        url:"/rule/ruleCompute.do?"+"tppcode="+tppcode+"&productNo="+productNo+"&scene="+scene+"&envparam="+envparam,
        type:"GET",
        error:function(result){

        },
        success:function(result){
            if(result["returnCode"]=="000000"){
                alert("success");
                $("#computeResult").html("计算成功～，批次号:"+result["batchNo"]+"，通过率:"+result["percent"])
            }else{
                alert("fail");
            }
        }
    });
}

function clearData(){

    $.ajax({
        url:"/rule/clearData.do?",
        type:"GET",
        error:function(result){

        },
        success:function(result){
            if(result["returnCode"]=="000000"){
                alert("success，"+result["returnMsg"]);
            }else{
                alert("fail，"+result["returnMsg"]);
            }
        }
    });
    historyData();
}

function synData() {
    $.ajax({
        url:"/rule/synData.do?",
        type:"GET",
        error:function(result){

        },
        success:function(result){
            if(result["returnCode"]=="000000"){
                alert("success，"+result["returnMsg"]);
            }else{
                alert("fail，"+result["returnMsg"]);
            }
        }
    });
}

/* 查询数据 */
function historyData() {

    var source = {
        datatype: "json",
        datafields: [
            {name: 'batchNo', type: 'string'},
            {name: 'productNo', type: 'string'},
            {name: 'tppCode', type: 'string'},
            {name: 'scene', type: 'string'},
            {name: 'percent', type: 'string'}
        ],
        id: 'key',
        url: '/rule/historyData.do'
    };
    var dataAdapter = new $.jqx.dataAdapter(source, {
        downloadComplete: function (data, status, xhr) {
        },
        loadComplete: function (data) {
        },
        loadError: function (xhr, status, error) {

        },
        beforeLoadComplete: function (records){
        }
    });
    $("#historyContent").jqxGrid({
        width: '100%',
        source: dataAdapter,
        pageable: false,
        autoheight: true,
        sortable: true,
        editable: true,
        columnsresize: true,
        columns: [
            {text: '批次号', datafield: 'batchNo', editable: false, width: '20%'},
            {text: '产品码', datafield: 'productNo', editable: false, width: '20%'},
            {text: '通道', datafield: 'tppCode', editable: false, width: '20%'},
            {text: '场景', datafield: 'scene', editable: false, width: '20%'},
            {text: '通过率', datafield: 'percent', editable: false, width: '20%' }
        ]
    });
}