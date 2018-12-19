function searchWhiteUser() {
    $.ajax({
        url:"/quickpass/searchWhiteUser.do?"+"userId="+$("#whiteUserUid").val(),
        type:"GET",
        error:function(result){

        },
        success:function(result){
            if(result["returnCode"]=="000000"){
                alert("success");
                $("#computeResult").html(result["returnMsg"]+"，userId="+$("#whiteUserUid").val())
            }else{
                alert("fail");
                $("#computeResult").html(result["returnMsg"]+"，userId="+$("#whiteUserUid").val())
            }
        }
    });

}

function addWhiteUser(tppcode,productNo,scene,envparam){

    $.ajax({
        url:"/quickpass/addWhiteUser.do?"+"userId="+$("#whiteUserUid").val(),
        type:"GET",
        error:function(result){

        },
        success:function(result){
            if(result["returnCode"]=="000000"){
                alert("success");
                $("#computeResult").html("添加成功～，userId="+$("#whiteUserUid").val())
            }else{
                alert("fail");
                $("#computeResult").html("添加失败～，userId="+$("#whiteUserUid").val())
            }
        }
    });
}

function delWhiteUser(){
    $.ajax({
        url:"/quickpass/delWhiteUser.do?"+"userId="+$("#whiteUserUid").val(),
        type:"GET",
        error:function(result){

        },
        success:function(result){
            if(result["returnCode"]=="000000"){
                alert("success");
                $("#computeResult").html("删除成功～，userId="+$("#whiteUserUid").val())
            }else{
                alert("fail");
                $("#computeResult").html("删除失败～，userId="+$("#whiteUserUid").val())
            }
        }
    });
}

function searchAccount() {
    $.ajax({
        url:"/quickpass/searchAccount.do?"+"userId="+$("#openAccountUid").val(),
        type:"GET",
        error:function(result){

        },
        success:function(result){
            if(result["returnCode"]=="000000"){
                alert("success");
                $("#computeResult").html(result["returnMsg"]+"，userId="+$("#openAccountUid").val()+",cardNo="+result["cardNoSh"])
            }else{
                alert("fail");
                $("#computeResult").html(result["returnMsg"]+"，userId="+$("#openAccountUid").val())
            }
        }
    });

}

function addAccount(tppcode,productNo,scene,envparam){

    $.ajax({
        url:"/quickpass/addAccount.do?"+"userId="+$("#openAccountUid").val(),
        type:"GET",
        error:function(result){

        },
        success:function(result){
            if(result["returnCode"]=="000000"){
                alert("success");
                $("#computeResult").html("开户成功～，userId="+$("#openAccountUid").val()+",cardNo="+result["cardNoSh"])
            }else{
                alert("fail");
                $("#computeResult").html("开户失败～，userId="+$("#openAccountUid").val())
            }
        }
    });
}

function delAccount(){
    $.ajax({
        url:"/quickpass/delAccount.do?"+"userId="+$("#openAccountUid").val(),
        type:"GET",
        error:function(result){

        },
        success:function(result){
            if(result["returnCode"]=="000000"){
                alert("success");
                $("#computeResult").html("删除成功～，userId="+$("#openAccountUid").val())
            }else{
                alert("fail");
                $("#computeResult").html("删除失败～，userId="+$("#openAccountUid").val())
            }
        }
    });
}

