$(function(){
    var btn = $("#id");
    btn.change(function(){
        var id = $("#id").val();
        $.ajax({
            url:'CheckId',
            type:'post',
            data:"id="+id+"&type=user",
            dataType:'text',
            success:function(data){
                var btn = $("#id");
                if(data=="true"){
                    $("#idValid").val("ok");
                    $("#infoDiv").attr("style","color:green;font-size:15px;font-weight:bold").text("用户名合格");
                }
                else if(data=="false"){
                    $("#idValid").val("no");
                    $("#infoDiv").attr("style","color:red;font-size:15px;font-weight:bold").text("用户名已存在");
                }
            },

        });
    });
});
$(function(){
    setTimeout(function(){
        $("#registInformation").css("display","none");//设置过一段时间自动消失
    },2000)
})