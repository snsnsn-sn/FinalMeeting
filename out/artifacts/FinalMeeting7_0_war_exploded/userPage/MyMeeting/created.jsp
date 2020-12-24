<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="cn">
<head>
    <meta charset="UTF-8">
    <title>会务管理系统-我发起的会议</title>

    <!-- 引入 bootstrap核心css-->
    <link rel="stylesheet" href="../../css/bootstrap.min.css"/>
    <!-- 引入jQuery核心js文件-->
    <script src="../../js/jquery-3.1.1.min.js"></script>
    <!-- 引入bootstrap核心js文件-->
    <script src="../../js/bootstrap.min.js"></script>
    <style>
        .navbar-right .dropdown-menu {
            right: 0;
            left: -5px;
        }

        th {
            text-align: center;
        }
    </style>
    <script src="../../js/meetingSys.js"></script>
    <script>
        var time = new Date();
        var day = ("0" + time.getDate()).slice(-2);
        var month = ("0" + (time.getMonth() + 1)).slice(-2);
        var today = time.getFullYear() + "-" + (month) + "-" + (day);
        var userID = '<%=session.getAttribute("userID")%>';

        //加载页面
        $(function () {
            hide_alert();
            getLeft(1, 2);
            if (check_login('<%=session.getAttribute("userID")%>', '<%=session.getAttribute("userName")%>')) {
                getDate(userID); //设置模态框时间为当日
                $("#login_alert").hide();  //隐藏登录提示框
                refreshCreatedList(userID);      //刷洗会议表
            } else {
                $("#nav_bar").load("/Final/userPage/nav.html");//载入游客导航栏
                $('#createNewMeetingButton').hide();  //隐藏创建会议按钮
                hide_alert();
            }
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

<!--页面正文-->
<div class="col-sm-9">
    <%--页头--%>
    <div class="page-header">
        <h1 style="margin-top: -35px">我发起的会议</h1>
    </div>
    <div>
        <%--面板--%>
        <div class="panel panel-default text-center">
            <%--用户提示框--%>
            <div class="alert alert-success alert-dismissible" id="control_alert" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                你可以管理你的会议啦!
            </div>
            <div class="alert alert-danger alert-dismissible" id="login_alert" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                您还尚未<a href="/Final/userPage/login.jsp" class="">登录</a>!
            </div>
            <div class="alert alert-warning alert-dismissible" role="alert" id="create_alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                您还没有创建过会议,要<a href="#" class="alert-link">马上创建一个会议</a>吗?
            </div>
            <div class="table-body text-center" id="myMeetings"></div>

            <button id="createNewMeetingButton" type="button" data-toggle="modal" data-target="#createMeetingModal"
                    class="btn btn-block btn-warning">发起新会议
            </button>
        </div>
    </div>

</div>
</div>


<div id="createMeetingModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">发起会议</h4>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label for="meetingName">会议名称</label>
                        <input type="text" class="form-control" id="meetingName" placeholder="请输入会议名">
                    </div>
                    <div class="form-group">
                        <label for="meetingPart">举办单位</label>
                        <input type="text" class="form-control" id="meetingPart" placeholder="请输入举办单位">
                    </div>
                    <div class="form-group">
                        <label for="meetingTime">会议时间</label>
                        <input type="date" class="form-control" id="meetingTime">
                    </div>
                    <div class="form-group">
                        <label for="meetingPlace">会议地点</label>
                        <input type="text" class="form-control" id="meetingPlace" placeholder="请输入会议地点">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="confirmAddMeeting(userID)">确认创建</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
</body>
</html>