<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>会务管理系统V1.0</title>

    <!-- 引入 bootstrap核心css-->
    <link rel="stylesheet" href="/ms/css/bootstrap.min.css"/>
    <!-- 引入jQuery核心js文件-->
    <script src="/ms/js/jquery-3.1.1.min.js"></script>
    <!-- 引入bootstrap核心js文件-->
    <script src="/ms/js/bootstrap.min.js"></script>
    <style>
        a.active {
            background-color: #2b669a;
            color: white;
        }

        .navbar-right .dropdown-menu {
            right: 0;
            left: -45px;
        }
    </style>

    <script src="/ms/js/meetingSys.js"></script>
    <script>
        //加载页面
        $(function () {
            if (!check_login('<%=session.getAttribute("userID")%>', '<%=session.getAttribute("userName")%>'))
                $("#nav_bar").load("/ms/userPage/nav.html");
            getLeft(0, 0);
            showMeeting();
        })
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

<!--中心内容-->
<%--  ======================================================================================================================--%>
<div class="col-sm-9">

    <%-- 巨幕  网站介绍--%>
    <div class="jumbotron">
        <div class="container">
            <h1>会务管理系统<small>V1.0</small></h1>
            <p id="p">
                对于活动主办方来说，通过传统方式举办一场活动繁琐的事情很多，从网站搭建、邀请参会者、酒店预订、接驾安排等等，
                这其中的繁琐与大量的财力物力支出都不可避免，<span class="text-info"><b>会务管理系统1.0</b></span>
                提供的一站式数字化在线管理极大的缩减了筹备时间，专业高效，系统完备。
            </p>
        </div>
    </div>
    <div class="container-fluid" id="showMeetingDiv" style="margin-bottom: 20px">
        <hr>
    </div>
</div>
</body>
</html>


