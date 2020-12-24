<%--
  Created by IntelliJ IDEA.
  User: qaj
  Date: 2020/11/25
  Time: 22:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <META HTTP-EQUIV="pragma" CONTENT="no-cache">
    <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
    <META HTTP-EQUIV="expires" CONTENT="0">

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>会议管理系统-登录注册</title>

    <link rel="stylesheet" href="css/all.css">
    <link rel="stylesheet" href="css/main.css">
    <script src='js/jquery.js'></script>
    <script src="js/action.js"></script>
    <script>
        $(function(){
            setTimeout(function(){
                $("#loginInformation").css("display","none");//设置过一段时间自动消失
            },2000)
        })
    </script>

</head>

<body>
    <%! String loginInfo=""; %>
    <%
        loginInfo = (String)request.getAttribute("registInfo");
        if(loginInfo==null)
            loginInfo = "";
    %>
    <%--<div class="bg">--%>
        <video class="video_bg" autoplay muted loop>
            <source src="mp4/bg.mp4" type="video/mp4">
        </video>
    <%--</div>--%>
    <!-- 创建封装容器 -->
    <div class="container" id="container">

        <!-- 注册页面 -->
        <div class="form-container sign-up-container">
            <form action="#">
                <h1>注册</h1>
                <div class="social-container">
                    <a href="#" id="registuser" onclick="registUser()" style="text-decoration: none"><b>用户</b></a>
                    <a href="#" id="registdriver" onclick="registDriver()" style="text-decoration: none"><b>司机</b></a>
                </div>
            </form>
        </div>

        <!-- 登录页面 -->
        <div class="form-container sign-in-container">
            <form action="Login" method="post">
                <h1>登录</h1>
                <!-- 公共组件 -->
                <div class="social-container">
                    <a href="#" id="user" onclick="User()" style="text-decoration: none"><b>用户</b></a>
                    <a href="#" id="driver" onclick="Driver()" style="text-decoration: none"><b>司机</b></a>
                    <a href="#" id="hotel" onclick="Hotel()" style="text-decoration: none"><b>酒店</b></a>
                    <a href="#" id="admin" onclick="Admin()" style="text-decoration: none"><b>管理</b></a>
                    <a href="#" id="carteam" onclick="Carteam()" style="text-decoration: none"><b>车队</b></a>
                </div>
                <input type="hidden" name="type" id="type" value=""/>
                <input type="text" name="id" id="id" required="required" placeholder="账号..." >
                <input type="password" name="password" id="password" required="required" placeholder="密码...">

                <input type="text" name="verifycode" placeholder="验证码" required="required"><img id="verifycode" onclick="changeone()" src="verifycode.jsp">

                <div style="color:red;font-size:5px;font-weight:bold" id="loginInformation"><%=loginInfo%></div>
                <%--<a href="#" class="forget">forget your password</a>--%>
                <a href="#" onclick="changeone()" class="forget">看不清，换一张</a>

                <button>登录</button>
            </form>
        </div>

        <!-- 覆盖容器 -->
        <div class="overlay-container">
            <div class="overlay">
                <!-- 覆盖左边 -->
                <div class="overlay-penal overlay-left-container">
                    <h1>欢迎回来！</h1>
                    <p>
                       输入你的信息，加入我们吧
                    </p>
                    <button class="ghost" id="signIn">登录</button>
                </div>

                <!-- 覆盖右边 -->
                <div class="overlay-penal overlay-right-container">
                    <h1>您好！朋友</h1>
                    <p>
                       输入你的信息，开始你的旅程吧
                    </p>
                    <button class="ghost" id="signUp">注册</button>
                </div>
            </div>
        </div>
    </div>
    <script src="js/main.js"></script>
</body>

</html>