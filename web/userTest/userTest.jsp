<%--
  Created by IntelliJ IDEA.
  User: 71584
  Date: 2020/11/22
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>userTest</title>
    <%--    确保不同分辨率下正常显示--%>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="test,bootstrap">
    <meta http-equiv="description" content="test of bootstrap">

    <!-- 引入 bootstrap核心css-->
    <link rel="stylesheet"  href="../css/bootstrap.min.css"/>
    <!-- 引入jQuery核心js文件-->
    <script src="../js/jquery-3.1.1.min.js"></script>
    <!-- 引入bootstrap核心js文件-->
    <script src="../js/bootstrap.min.js"></script>

    <script>
        $(function () {
            $("#nav").load('/ms/userPage/nav.html'); //加载导航栏
            refreshUserList(); //刷新user表
        })
    </script>
</head>
<%--导航--%>
<%--================================================================================================================--%>
<div id="nav"></div>

<div class="container">
    <%--添加用户表单--%>
    <%--================================================================================================================--%>
    <div class="row">
        <div class="col-sm-6">
            <form name="userControllForm" class="form-horizontal" style="padding-top: 60px;">  <!-- 注意此处绝对路径是否正确 -->
                <div class="form-group">
                    <label for="aid" class="col-sm-2 control-label">账号</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="aid" onblur="check()">
                        <span id="checkSpan" name="checkSpan"></span>
                    </div>
                </div>

                <div class="form-group">
                    <label for="aname" class="col-sm-2 control-label">昵称</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="aname">
                    </div>
                </div>

                <div class="form-group">
                    <label for="apw" class="col-sm-2 control-label">密码</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="apw">
                    </div>
                </div>

                <div class="form-group">
                    <label for="aphone" class="col-sm-2 control-label">电话号码</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="aphone">
                    </div>
                </div>

                <div class="form-group">
                    <label for="apart" class="col-sm-2 control-label">部门</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="apart">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-7">
                        <input id="addUserButton" class="btn btn-primary" type="button" value="添加用户" onclick="addUser()">
                    </div>
                </div>
            </form>
        </div>

        <%--用户显示--%>
        <%--=======================================================================================================================--%>
        <div class="col-sm-6">
            <table class="table table-bordered table-hover">
                <thead>
                <caption><button class="btn-primary" onclick="refreshUserList()">刷新用户表</button></caption>
                <tr><th>用户id</th><th>昵称</th><th>密码</th><th>电话</th><th>部门</th><th></th></tr>
                </thead>
                <tbody id="userList">

                </tbody>
            </table>
        </div>

    </div>
</div>


<%--javascipt 函数--%>
<%--=======================================================================================================================--%>
<script language="JavaScript">

    // 注意此处路径是否正确
    function refreshUserList(){
        $.get(
            "/ms/user",
            {fun:'findAll'},
            function(response){
                var o=JSON.parse(response);
                /*表头*/
                var code='';
                /*内容*/
                for(var i in o){
                    code+='<tr>' +
                        '<td>'+o[i].userId+'</td>'+
                        '<td>'+o[i].name+'</td>'+
                        '<td>'+o[i].password+'</td>'+
                        '<td>'+o[i].phone+'</td>'+
                        '<td>'+o[i].part+'</td>'+
                        '<td><a class="danger" href="javascript:deleteUser(\''+o[i].userId+'\')">删除</a></td>'+
                        '</tr>';
                }
                $("#userList").html(code);
            }
        )
    }

    /**
     * 函数:检查新用户id是否可用
     */
    function check() {
        $.get(
            "/ms/user",
            {fun:'checkID',id:$("#aid").val()},
            function(response){
                $("#checkSpan").html(response);
            }
        )
    }

    /**
     * 提交添加用户申请,刷新userList,清空表单内容
     */
    function addUser(){
        $.get(
            "/ms/user",
            {fun:'add',aid:$("#aid").val(),aname:$("#aname").val(),apw:$("#apw").val(),aphone:$("#aphone").val(),apart:$("#apart").val()},
            function(response){
                response==1?alert("操作成功"):alert("操作失败");
                $(".form-control").val(''); //清空输入
                refreshUserList();
            }
        )
    }

    function deleteUser(id) {
        $.get(
            "/ms/user",
            {fun:'delete',did:id},
            function(response){
                response==1?alert("操作成功"):alert("操作失败");
                refreshUserList();
            }
        )
    }

    function refreshOnTime() {
        refreshUserList();
        // setInterval('refreshUserList()', 30000);  取消注释 定时刷新
    }
</script>

</body>
</html>
