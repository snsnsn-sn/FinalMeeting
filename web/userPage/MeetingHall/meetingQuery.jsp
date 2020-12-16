<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="cn">
<head>
    <meta charset="UTF-8">
    <title>会议查询</title>

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
            $("#query_alert").hide();
            if (!check_login('<%=session.getAttribute("userID")%>', '<%=session.getAttribute("userName")%>'))
                $("#nav_bar").load("/ms/userPage/nav.html");//载入游客导航栏

            getLeft(0, 1);
            //为查询输入框绑定回车查询事件
            $('#qcid').bind('keypress', function (event) {
                if (event.keyCode == "13")
                    queryByUserId();
            });

            $('#qmname').bind('keypress', function (event) {
                if (event.keyCode == "13")
                    queryByMeetingName();
            });

            $('#qmid').bind('keypress', function (event) {
                if (event.keyCode == "13")
                    queryByMeetingId();
            });

            refreshMeetingList();
        });
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
        <h1 style="margin-top: -35px">会议信息查询</h1>
    </div>
    <div>
        <%--查询面板--%>
        <div class="panel panel-default text-center">
            <%--查询表单--%>
            <form class="form-inline" style="margin-top: 20px" onsubmit="return false">
                <div class="form-group">
                    <label for="qmid">会议ID</label>
                    <input type="text" class="form-control" id="qmid" onblur="queryByMeetingId()"
                           placeholder="输入id后回车">
                </div>
                <div class="form-group">
                    <label for="qmname">会议名</label>
                    <input type="text" class="form-control" id="qmname" onblur="queryByMeetingName()"
                           placeholder="输入名称后回车">
                </div>
                <div class="form-group">
                    <label for="qcid">发起人id</label>
                    <input type="text" class="form-control" id="qcid" onblur="queryByUserId()"
                           placeholder="输入id后回车">
                </div>
                <input type="reset" class="btn btn-default" onclick="refreshMeetingList()"
                       style="margin-left: 30px">
            </form>
            <div class="table-body text-center">
                <div class="alert alert-danger alert-dismissible" id="query_alert" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    未找到查询结果<a onclick="refreshMeetingList()">返回</a>
                </div>
                <table class="table">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>会议id</th>
                        <th>会议名称</th>
                        <th>发起人id</th>
                        <th>会议时间</th>
                        <th>会议地点</th>
                        <th>当前状态</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="meetingList" class="text-center"></tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</div>

</body>
</html>


