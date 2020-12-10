<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="cn">
<head>
    <meta charset="UTF-8">
    <title>我加入的会议</title>

    <!-- 引入 bootstrap核心css-->
    <link rel="stylesheet"  href="/ms/css/bootstrap.min.css"/>
    <!-- 引入jQuery核心js文件-->
    <script src="/ms/js/jquery-3.1.1.min.js"></script>
    <!-- 引入bootstrap核心js文件-->
    <script src="/ms/js/bootstrap.min.js"></script>
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
            //隐藏登陆后提示框
            $("#create_alert").hide();
            $("#control_alert").hide();

            if(check_login()) //已登录事物处理
            {
                $("#nav_left").load("/ms/userPage/nav.html #left_nav"); //载入导航栏左侧Brand
                $("#login_alert").hide();  //隐藏登录警告
                refreshMeetingList();      //刷新会议表
            }
            else{
                $("#nav_bar").load("/ms/userPage/nav.html"); //载入游客导航栏
            }
        })

        //检查登陆状态并返回
        function check_login() {
            console.log("check_login....")
            var login_id='<%=session.getAttribute("userID")%>';
            console.log(login_id);
            return login_id!='null'?true:false;
        }

        function refreshMeetingList() {
            $.get(
                "/ms/meeting",
                {fun:'myJoin',id:'<%=session.getAttribute("userID")%>'},
                function (response) {
                    console.log(response);
                    if(response=='请先登录')
                        $("#login_alert").show();
                    else if(response==0)
                        $('#create_alert').show();
                    else{
                        var o=JSON.parse(response);
                        var count=1;
                        var code='<table class="table"><thead><tr><th>#</th><th>会议id</th><th>会议名称</th>' +
                            '<th>会议时间</th><th>会议地点</th><th>当前状态</th><th>&nbsp;操作</th></tr></thead><tbody class="text-center">\n';

                        for(var i in o){
                            code+='<tr>' +
                                '<th scope="row">'+count+'</th>'+
                                '<td>'+o[i].meetingId+'</td>'+
                                '<td>'+o[i].meetingName+'</td>'+
                                '<td>'+o[i].time+'</td>'+
                                '<td>'+o[i].place+'</td>'+
                                '<td>'+o[i].state+'</td>'+
                                '<td><a class="btn text-info btn-sm">详情</a> </td>'+
                                '</tr>';
                            count+=1;
                        }
                        code+='</tbody></table>';
                        $("#myMeetings").html(code);
                        $("#control_alert").show();
                    }
                }
            )
        }
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
                <div id="MeetingHallChild" class="panel-collapse collapse"> <%--in:加载时不隐藏--%>
                    <div class="panel-body">
                        <!--嵌套列表组-->
                        <ul class="list-group">
                            <li class="list-group-item"><a href="/ms/userPage/index.jsp">会议一览</a></li>
                            <li class="list-group-item"><a href="/ms/userPage/MeetingHall/meetingQuery.jsp">会议查询</a></li>
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
                <div id="MyMeetingChild" class="panel-collapse collapse in">
                    <div class="panel-body">
                        <!--嵌套列表组-->
                        <ul class="list-group">
                            <li class="list-group-item"><a href="/ms/userPage/MyMeeting/created.jsp">我发起的会议</a></li>
                            <li class="list-group-item"><a href="/ms/userPage/MyMeeting/joined.jsp" class="text-danger">我加入的会议</a></li>
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
            <h1 style="margin-top: -35px">我加入的会议</h1>
        </div>
        <div>
            <%--面板--%>
            <div class="panel panel-default text-center">
                <%--用户提示框--%>
                <div class="alert alert-success alert-dismissible" id="control_alert" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    你可以查看你加入的会议啦!
                </div>
                <div class="alert alert-danger alert-dismissible" id="login_alert" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    您还尚未<a href="/ms/userPage/login.jsp" class="">登录</a>!
                </div>
                <div class="alert alert-warning alert-dismissible" role="alert" id="create_alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    您还没有加入任何会议,去<a href="/ms/userPage/index.jsp" class="alert-link">会议大厅</a>看看吧
                </div>
                <div class="table-body text-center" id="myMeetings">

                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>


