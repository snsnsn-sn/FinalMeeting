<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="cn">
<head>
    <meta charset="UTF-8">
    <title>我加入的会议</title>

    <!-- 引入 bootstrap核心css-->
    <link rel="stylesheet" href="../../css/bootstrap.min.css"/>
    <!-- 引入jQuery核心js文件-->
    <script src="../../js/jquery-3.1.1.min.js"></script>
    <!-- 引入bootstrap核心js文件-->
    <script src="../../js/bootstrap.min.js"></script>

    <style>
        .navbar-right .dropdown-menu {
            right: 0;
            left: -45px;
        }

        th {
            text-align: center;
        }
    </style>

    <script src="/ms/js/meetingSys.js"></script>
    <script>
        //加载页面
        $(function () {
            //隐藏登陆后提示框
            $("#empty_alert").hide();

            if (!check_login('<%=session.getAttribute("userID")%>', '<%=session.getAttribute("userName")%>'))
                $("#nav_bar").load("/ms/userPage/nav.html");//载入游客导航栏
            else
                $("#login_alert").hide();//隐藏登录警告

            getLeft(2, 4);
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

<%--接驾订单--%>
<div class="col-sm-9">
    <div class="page-header">
        <h1 style="margin-top: -35px">接驾订单</h1>
    </div>
    <div>
        <div class="panel panel-default text-center">
            <!--用户提示框-->
            <div class="alert alert-success alert-dismissible" id="info_alert" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                你可以查看订单信息啦!
            </div>
            <div class="alert alert-danger alert-dismissible" id="login_alert" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                您还尚未<a href="/ms/userPage/login.jsp" class="">登录</a>!
            </div>
            <div class="alert alert-warning alert-dismissible" role="alert" id="empty_alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                当前无任何订单信息
            </div>
            <!--订单列表-->
            <div class="table-body text-center" id="myMeetings">
                <table class="table text-center">
                    <tr>
                        <th>司机</th>
                        <th>司机联系方式</th>
                        <th>接送时间</th> <!--pickUser-->
                        <th>接送位置</th> <!--pickPlace-->
                    </tr>
                    <tr>
                        <td>托马斯</td>
                        <td>1387512912</td>
                        <td>2020-12-09</td>
                        <td>五四中大道</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>


