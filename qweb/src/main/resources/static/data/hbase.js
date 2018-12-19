
function clearHbaseData(){
    var dataId=$("#dataId").val();
    var hbaseTable = $("#hbaseTable").val();
    var creditTppcode = $("#creditTppcode").val();
    var characterCode = $("#characterCode").val();

    $.ajax({
        url:"/hbaseData/clearHbaseData.do?"+"dataId="+dataId+"&hbaseTable="+hbaseTable+"&creditTppcode="+creditTppcode+"&characterCode="+characterCode,
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

function updateForHbaseUser(){
    var dataIdForUpdate=$("#dataIdForUpdate").val();
    var hbaseTableForUpdate = $("#hbaseTableForUpdate").val();
    var creditDataForUpdate = $("#creditDataForUpdate").val();

    $.ajax({
        url:"/hbaseData/updateForHbaseUser.do?"+"dataId="+dataIdForUpdate+"&hbaseTable="+hbaseTableForUpdate+"&creditData="+creditDataForUpdate,
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

function copyHbaseUser(){
    var hbaseTableForCopy=$("#hbaseTableForCopy").val();
    var fromIndexForCopy = $("#fromIndexForCopy").val();
    var toIndexForCopy = $("#toIndexForCopy").val();

    $.ajax({
        url:"/hbaseData/copyHbaseUser.do?"+"hbaseTableForCopy="+hbaseTableForCopy+"&fromIndexForCopy="+fromIndexForCopy+"&toIndexForCopy="+toIndexForCopy,
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

function uploadHbaseData(){
    var filePath=$("#filePath").val();
    $.ajax({
        url:"/hbaseData/uploadHbaseData.do?"+"filePath="+filePath,
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
function uploadHbaseUser() {
    var testUids=$("#testUids").val();
    $.ajax({
        url:"/hbaseData/uploadHbaseUser.do?"+"testUids="+testUids,
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
