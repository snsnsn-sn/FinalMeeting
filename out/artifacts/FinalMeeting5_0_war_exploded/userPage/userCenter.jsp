<%--
  Created by IntelliJ IDEA.
  User: 71584
  Date: 2020/12/13
  Time: 21:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人中心</title>

    <!-- 引入 bootstrap核心css-->
    <link rel="stylesheet" href="../css/bootstrap.min.css"/>
    <!-- 引入jQuery核心js文件-->
    <script src="../js/jquery-3.1.1.min.js"></script>
    <!-- 引入bootstrap核心js文件-->
    <script src="../js/bootstrap.min.js"></script>

    <script src="../js/meetingSys.js"></script>
    <script>

        //加载页面
        $(function () {
            if (!check_login('<%=session.getAttribute("userID")%>', '<%=session.getAttribute("userName")%>')) {
                $("#nav_bar").load("/Final/userPage/nav.html");
                alert("请先登录");
                window.location.href = '/Final/userPage/login.jsp';
            } else {
                getUser();
            }
            getLeft(-1, -1);
        })

        function getUser() {
            $.get(
                '/Final/user',
                {fun: 'getUser'},
                function (response) {
                    var o = JSON.parse(response);
                    $("#id").val(o.userId);
                    $("#name").val(o.name);
                    $("#phone").val(o.phone);
                    $("#part").val(o.part);
                }
            )
        }

        function alterUserInfo() {
            $.get(
                '/Final/user',
                {
                    fun: 'alterInfo',
                    name: $('#name').val(),
                    phone: $('#phone').val(),
                    part: $('#part').val(),
                },
                function (response) {
                    if (response == 1) {
                        alert("修改成功");
                        getUser();
                    } else {
                        alert("修改失败");
                    }
                }
            )
        }

        function alterPassword() {
            var pw0 = $("#pw0").val();
            var pw1 = $("#pw1").val();
            var pw2 = $("#pw2").val();

            if (pw1 != pw2)
                alert("两次输入密码不一致!");
            else {
                $.get(
                    '/Final/user',
                    {
                        fun: 'alterPassword',
                        pw0: pw0,
                        pw1: pw1
                    },
                    function (response) {
                        if (response == 0) {
                            alert("密码输入错误或用户不存在");
                        } else {
                            alert("修改成功,请重新登录");
                            window.location.href = '/Final/userPage/login.jsp';
                        }
                    }
                )
            }
        }
    </script>
</head>
<body>
<!--导航条-->
<%--  ======================================================================================================================--%>
<div id="nav_bar"></div>
<%--  ======================================================================================================================--%>

<!--左侧菜单 -->
<%--  ======================================================================================================================--%>
<div class="col-sm-2" style="margin-left: 10px" id="left_menu"></div>
<%--  ======================================================================================================================--%>

<div class="col-sm-9">
    <div class="page-header">
        <h1 style="margin-top: -35px">个人中心</h1>
    </div>
    <div>

        <!-- 标签页选项 -->
        <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="active"><a href="#basic" aria-controls="home" role="tab"
                                                      data-toggle="tab">基本信息</a></li>
            <li role="presentation"><a href="#pw" aria-controls="profile" role="tab"
                                       data-toggle="tab">修改密码</a></li>
        </ul>


        <!-- 对应面板 -->
        <div class="tab-content">
            <div role="tabpanel" class="tab-pane active" id="basic">
                <div class="col-sm-9 col-sm-offset-2">
                    <form name="userControllForm" class="form-horizontal" style="padding-top: 60px;">
                        <div class="form-group">
                            <label for="id" class="col-sm-2 control-label" disabled="true">账号</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="id" disabled>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="name" class="col-sm-2 control-label">昵称</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="name">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="phone" class="col-sm-2 control-label">电话号码</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="phone">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="part" class="col-sm-2 control-label">部门</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="part">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-7">
                                <input class="btn btn-default" type="button" value="保存修改"
                                       onclick="alterUserInfo()">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div role="tabpanel" class="tab-pane" id="pw">
                <div class="col-sm-9 col-sm-offset-2">
                    <form class="form-horizontal" style="padding-top: 60px;">
                        <div class="form-group">
                            <label for="pw0" class="col-sm-2 control-label">原密码</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="pw0" placeholder="请输入原来的密码">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="pw1" class="col-sm-2 control-label">新密码</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="pw1" placeholder="请输入新密码">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="pw2" class="col-sm-2 control-label">确认新密码</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="pw2" placeholder="请再次输入新密码">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-7">
                                <input class="btn btn-default" type="button" value="确认修改"
                                       onclick="alterPassword()">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
