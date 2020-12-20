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
    <script src="../../js/meetingSys.js"></script>

    <script>
        var userID = '<%=session.getAttribute("userID")%>';
        //加载页面
        $(function () {
            //隐藏登陆后提示框
            $("#create_alert").hide();
            $("#control_alert").hide();

            if (check_login('<%=session.getAttribute("userID")%>', '<%=session.getAttribute("userName")%>')) {
                $("#login_alert").hide();  //隐藏登录警告
                refreshJoinList(userID);      //刷新会议表
            } else
                $("#nav_bar").load("/Final/userPage/nav.html");//载入游客导航栏
            getLeft(1, 3);
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


<%--中心内容--%>
<div class="col-sm-9">
    <%--页头--%>
    <div class="page-header">
        <h1 style="margin-top: -35px">我加入的会议</h1>
    </div>
    <div>
        <%--面板--%>
        <div class="panel panel-default text-center">
            <%--用户提示框--%>
            <div class="alert alert-success alert-dismissible" id="control_alert" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                你可以查看你加入的会议啦!
            </div>
            <div class="alert alert-danger alert-dismissible" id="login_alert" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                您还尚未<a href="/Final/userPage/login.jsp" class="">登录</a>!
            </div>
            <div class="alert alert-warning alert-dismissible" role="alert" id="create_alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                您还没有加入任何会议,去<a href="/Final/userPage/index.jsp" class="alert-link">会议大厅</a>看看吧
            </div>
            <div class="table-body text-center" id="myMeetings">

            </div>
        </div>
    </div>
</div>
</div>

</body>
</html>