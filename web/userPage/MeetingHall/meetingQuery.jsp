<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="cn">
<head>
    <meta charset="UTF-8">
    <title>会议查询</title>

    <!-- 引入 bootstrap核心css-->
    <link rel="stylesheet"  href="../../css/bootstrap.min.css"/>
    <!-- 引入jQuery核心js文件-->
    <script src="../../js/jquery-3.1.1.min.js"></script>
    <!-- 引入bootstrap核心js文件-->
    <script src="../../js/bootstrap.min.js"></script>
    <style>
        .navbar-right .dropdown-menu{
            right: 0;
            left: -45px;
        }

        th{
            text-align: center;
        }
    </style>

    <script>
        //加载页面
        $(function () {
            $("#query_alert").hide();
            if(check_login())
                $("#nav_left").load("/ms/userPage/nav.html #left_nav");
            else
                $("#nav_bar").load("/ms/userPage/nav.html");

            refreshMeetingList();
        });

        function check_login() {
            console.log("check_login....")
            var login_id='<%=session.getAttribute("userID")%>';
            console.log(login_id);
            return login_id!='null'?true:false;
        }
        
        function refreshMeetingList() {
            $("#query_alert").hide();
            $.get(
                "/ms/meeting",
                {fun:'findAll'},
                function (response) {
                    console.log(response);
                    var o=JSON.parse(response);
                    var count=1;
                    var code=''

                    for(var i in o){
                        code+='<tr>' +
                            '<th scope="row">'+count+'</th>'+
                            '<td>'+o[i].meetingId+'</td>'+
                            '<td>'+o[i].meetingName+'</td>'+
                            '<td>'+o[i].userId+'</td>'+
                            '<td>'+o[i].time+'</td>'+
                            '<td>'+o[i].place+'</td>'+
                            '<td>'+o[i].state+'</td>'+
                            '<td><a class="btn text-info text- btn-sm">详情</a> </td>'+
                            '</tr>';
                        count+=1;
                    }
                    $("#meetingList").html(code);
                }
            )
        }

        function queryByMeetingId() {
            $("#query_alert").hide();
            $.get(
                "/ms/meeting",
                {fun:'midQuery',mid:$("#qmid").val()},
                function (response) {
                    console.log(response);
                    if(response==0)
                        $("#query_alert").show();

                    var o=JSON.parse(response);
                    var count=1;
                    var code=''

                    for(var i in o){
                        code+='<tr>' +
                            '<th scope="row">'+count+'</th>'+
                            '<td>'+o[i].meetingId+'</td>'+
                            '<td>'+o[i].meetingName+'</td>'+
                            '<td>'+o[i].userId+'</td>'+
                            '<td>'+o[i].time+'</td>'+
                            '<td>'+o[i].place+'</td>'+
                            '<td>'+o[i].state+'</td>'+
                            '<td><a class="btn text-info text- btn-sm">详情</a> </td>'+
                            '</tr>';
                        count+=1;
                    }
                    $("#meetingList").html(code);
                }
            )
        }

        function queryByMeetingName() {
            $("#query_alert").hide();
            $.get(
                "/ms/meeting",
                {fun:'nameQuery',name:$("#qmname").val()},
                function (response) {
                    console.log(response);
                    if(response==0)
                        $("#query_alert").show();

                    var o=JSON.parse(response);
                    var count=1;
                    var code=''

                    for(var i in o){
                        code+='<tr>' +
                            '<th scope="row">'+count+'</th>'+
                            '<td>'+o[i].meetingId+'</td>'+
                            '<td>'+o[i].meetingName+'</td>'+
                            '<td>'+o[i].userId+'</td>'+
                            '<td>'+o[i].time+'</td>'+
                            '<td>'+o[i].place+'</td>'+
                            '<td>'+o[i].state+'</td>'+
                            '<td><a class="btn text-info text- btn-sm">详情</a> </td>'+
                            '</tr>';
                        count+=1;
                        }
                    $("#meetingList").html(code);

                }
            )
        }

        function queryByUserId() {
            $("#query_alert").hide();
            $.get(
                "/ms/meeting",
                {fun:'cidQuery',cid:$("#qcid").val()},
                function (response) {
                    if(response==0)
                        $("#query_alert").show();

                    var o=JSON.parse(response);
                    var count=1;
                    var code=''

                    for(var i in o){
                        code+='<tr>' +
                            '<th scope="row">'+count+'</th>'+
                            '<td>'+o[i].meetingId+'</td>'+
                            '<td>'+o[i].meetingName+'</td>'+
                            '<td>'+o[i].userId+'</td>'+
                            '<td>'+o[i].time+'</td>'+
                            '<td>'+o[i].place+'</td>'+
                            '<td>'+o[i].state+'</td>'+
                            '<td><a class="btn text-info text- btn-sm">详情</a> </td>'+
                            '</tr>';
                        count+=1;
                    }
                    $("#meetingList").html(code);
                }
            )
        }

        // function refresh(object) {
        //
        // }
    </script>
</head>

<body>
<!--导航条-->
<%--  ======================================================================================================================--%>
<div id="nav_bar">
    <nav class="navbar navbar-default navbar-inverse">
        <div class="container">
            <div id="nav_left"></div>

            <!-- 导航栏组件 -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="login.jsp" id="login_a" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><!--用户提示-->
                            <img src="https://static.hdslb.com/images/akari.jpg" class="img-circle" style="height: 30px" width="30px">
                            <%=session.getAttribute("userName")%>
                        </a>
                        <ul class="dropdown-menu" style="">
                            <li><a href="#">我的信息</a></li>
                            <li><a href="#">修改密码</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="#">我的消息</a></li>
                        </ul>
                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div>
    </nav>
</div>
<%--  ======================================================================================================================--%>

<!--页面正文-->
<!--左侧菜单-->
<div class="row">
    <div class="col-sm-2" style="margin-left: 10px">

        <div class="panel-group" id="accordion">
            <!--面板 会议大厅-->
            <div class="panel panel-default">
                <div class="panel-heading"  id="MeetingHall">
                    <h4 class="panel-title">             <!--data-parent:指定父容器-->   <!--href:指定对应面板-->
                        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#MeetingHallChild">
                            <span class="glyphicon glyphicon-home active"></span> 会议大厅
                        </a>
                    </h4>
                </div>
                <div id="MeetingHallChild" class="panel-collapse collapse in"> <%--in:加载时不隐藏--%>
                    <div class="panel-body">
                        <!--嵌套列表组-->
                        <ul class="list-group">
                            <li class="list-group-item"><a href="/ms/userPage/index.jsp">会议一览</a></li>
                            <li class="list-group-item"><a href="/ms/userPage/MeetingHall/meetingQuery.jsp" class="text-danger">会议查询</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <!--面板 我的会议-->
            <div class="panel panel-default">
                <div class="panel-heading"  id="MyMeeting">
                    <h4 class="panel-title">             <!--data-parent:指定父容器-->   <!--href:指定对应面板-->
                        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#MyMeetingChild">
                            <span class="glyphicon glyphicon-th-list"></span> 我的会议
                        </a>
                    </h4>
                </div>
                <div id="MyMeetingChild" class="panel-collapse collapse">
                    <div class="panel-body">
                        <!--嵌套列表组-->
                        <ul class="list-group">
                            <li class="list-group-item"><a href="/ms/userPage/MyMeeting/created.jsp">我发起的会议</a></li>
                            <li class="list-group-item"><a href="/ms/userPage/MyMeeting/joined.jsp">我加入的会议</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <!--面板 我的订单-->
            <div class="panel panel-default">
                <div class="panel-heading"  id="MyOrder">
                    <h4 class="panel-title">             <!--data-parent:指定父容器-->   <!--href:指定对应面板-->
                        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#MyOrderChild">
                            <span class="glyphicon glyphicon-duplicate"></span> 我的订单
                        </a>
                    </h4>
                </div>
                <div id="MyOrderChild" class="panel-collapse collapse">
                    <div class="panel-body">
                        <!--嵌套列表组-->
                        <ul class="list-group">
                            <li class="list-group-item"><a href="/ms/userPage/MyOrder/pickOrder.jsp">接驾订单</a></li>
                            <li class="list-group-item"><a href="/ms/userPage/MyOrder/hotelOrder.jsp">酒店订单</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

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
                        <input type="text" class="form-control" id="qmid" onblur="queryByMeetingId()" placeholder="输入会议id查询">
                    </div>
                    <div class="form-group">
                        <label for="qmname">会议名</label>
                        <input type="text" class="form-control" id="qmname" onblur="queryByMeetingName()" placeholder="输入会议名查询">
                    </div>
                    <div class="form-group">
                        <label for="qcid">发起人id</label>
                        <input type="text" class="form-control" id="qcid" onblur="queryByUserId()" placeholder="输入会议发起人id查询">
                    </div>
                    <button class="btn btn-info" onclick="refreshMeetingList()" style="margin-left: 30px">刷新</button>
                </form>
                <div class="table-body text-center">
                    <div class="alert alert-danger alert-dismissible" id="query_alert" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        未找到查询结果<a onclick="refreshMeetingList()">刷新</a>
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


