<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    session.setAttribute("init", 1);
%>
<html lang="cn">
<head>
    <meta charset="UTF-8">
    <title>登录界面</title>

    <!-- 引入 bootstrap核心css-->
    <link rel="stylesheet" href="../../css/bootstrap.min.css"/>
    <!-- 引入jQuery核心js文件-->
    <script src="../../js/jquery-3.1.1.min.js"></script>
    <!-- 引入bootstrap核心js文件-->
    <script src="../../js/bootstrap.min.js"></script>
    <style>
        .form-control {
            height: 45px;
        }
    </style>

    <script>
        //登录请求
        function adminLogin() {
            if (adminCheck()) {
                adminLoginSuccess();
            }
        }

        //检查输入合法性
        function adminCheck() {
            if ($("#account").val() == '') {
                alert("账号不能为空");
                $('#account').focus();
                return false;
            }
            if ($('#password').val() == '') {
                alert("密码不能为空");
                $('#password').focus();
                return false;
            }
            return true;
        }

        function adminLoginSuccess() {
            $.post(
                "/Final/adminLogin",
                {fun: "login", loginID: $("#account").val(), loginPW: $("#password").val()},
                function (response) {
                    console.log(response);
                    if (response == 0) {
                        var id = '<%=session.getAttribute("AdminID")%>';
                        alert('欢迎');
                        window.location.href = '${pageContext.request.contextPath}/adminPage/index.jsp';
                    } else {
                        alert('账号或密码错误');
                    }

                }
            )
        }
    </script>
</head>
<body>
<div class="container-fluid">

    <!--标题用户登录-->
    <div class="row">
        <div class="col-sm-6 col-sm-offset-3">
            <h1 class="text-center" style="margin-top: 60px">用户登录</h1>
        </div> <!--分成6份 偏移三份-->
    </div>

    <!--登录表单-->
    <div class="row">
        <div class="col-sm-6 col-sm-offset-3">
            <form>
                <div class="form-group">
                    <label for="account">账号</label>
                    <input type="text" class="form-control" id="account" placeholder="请输入用户名">
                </div>
                <div class="form-group">
                    <label for="password">密码</label>
                    <input type="password" class="form-control" id="password" placeholder="请输入密码">
                </div>

                <!--清除浮动-->
                <div class="clearfix"></div>
                <!--                <button  style="margin-top: 20px" type="submit" class="btn btn-danger btn-block">登录</button>-->
                <button style="float:left; margin-top: 20px;width: 68%" type="button" class="btn btn-danger btn-sm"
                        onclick="adminLogin()">登录
                </button>
                <button style="float:right;margin-top: 20px;width: 27%" type="button" class="btn btn-info btn-sm">注册
                </button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
