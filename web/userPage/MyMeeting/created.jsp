<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="cn">
<head>
    <meta charset="UTF-8">
    <title>我发起的会议</title>

    <!-- 引入 bootstrap核心css-->
    <link rel="stylesheet"  href="/ms/css/bootstrap.min.css"/>
    <!-- 引入jQuery核心js文件-->
    <script src="/ms/js/jquery-3.1.1.min.js"></script>
    <!-- 引入bootstrap核心js文件-->
    <script src="/ms/js/bootstrap.min.js"></script>
    <style>
        .navbar-right .dropdown-menu{
            right: 0;
            left: -5px;
        }
        th{
            text-align: center;
        }
    </style>

    <script>
        var time = new Date();
        var day = ("0" + time.getDate()).slice(-2);
        var month = ("0" + (time.getMonth() + 1)).slice(-2);
        var today = time.getFullYear() + "-" + (month) + "-" + (day);

        //加载页面
        $(function () {
            hide_alert();
            check_login();
        })

        //隐藏提示框
        function hide_alert() {
            $("#create_alert").hide();
            $("#control_alert").hide();
        }

        //检查登陆状态
        function check_login() {
            //已登录
            if(is_login())
            {
                getDate(); //设置模态框时间为当日
                $("#nav_left").load("/ms/userPage/nav.html #left_nav");
                $("#login_alert").hide();  //隐藏登录提示框
                refreshMeetingList();      //刷洗会议表
            }
            //未登录
            else{
                $("#nav_bar").load("/ms/userPage/nav.html");
                $('#createNewMeetingButton').hide();  //隐藏创建会议按钮
                hide_alert();
            }
        }

        //检查是否登录
        function is_login() {
            console.log("check_login....")
            var login_id='<%=session.getAttribute("userID")%>';
            console.log(login_id);
            return login_id!='null'?true:false;
        }

        //刷新会议表
        function refreshMeetingList() {
            $.get(
                "/ms/meeting",
                {fun:'myCreate',id:'<%=session.getAttribute("userID")%>'},
                function (response) {
                    console.log(response);
                    if(response=='请先登录')
                        $("#login_alert").show();
                    else if(response==0)
                        $('#create_alert').show();
                    else{
                        var o=JSON.parse(response);
                        var count=1;
                        var code='<table class="table text-center"><thead><tr><th>#</th><th>会议id</th><th>会议名称</th>' +
                            '<th>会议时间</th><th>会议地点</th><th>当前状态</th><th>&nbsp;操作</th></tr></thead><tbody>\n';

                        for(var i in o){
                            var mid=o[i].meetingId;
                            code+='<tr>' +
                                '<th scope="row">'+count+'</th>'+
                                '<td>'+mid+'</td>'+
                                '<td>'+o[i].meetingName+'</td>'+
                                '<td>'+o[i].time+'</td>'+
                                '<td>'+o[i].place+'</td>'+
                                '<td>'+o[i].state+'</td>'+
                                '<td><a class="btn text-warning" href="meetingControl.jsp?mid='+mid+'">管理</a> </td>'+
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

        //设置模态框时间
        function getDate() {

            $("#meetingTime").val(today);
            $.get(
                "/ms/user",
                {fun: 'getPart',id:'<%=session.getAttribute("userID")%>'},
                function (response) {
                    if(response!=0)
                        $("#meetingPart").val(response);
                }
            )
        }

        //确认会议创建
        function confirmMeeting() {
            if(check())
                $.post(
                    "/ms/meeting",
                    {fun:'add',name:$("#meetingName").val(),uid:'<%=session.getAttribute("userID")%>',part:$("#meetingPart").val(),
                        time:$("#meetingTime").val(),place:$("#meetingPlace").val()},
                    function (response) {
                        $("#createMeetingModal").modal('hide'); //关闭模态框
                        if(response==1)
                        {
                            alert("会议创建成功!您可以在管理页面中完善和管理你的会议");
                            refreshMeetingList();
                            $(".form-control").val(''); //清空输入
                        }
                        else
                            alert("创建会议失败!")
                    }
                )
        }

        //检查输入合法性
        function check() {
            if($("#meetingName").val()=='')
            {
                alert("会议名称不能为空");
                $('#meetingName').focus();
                return false;
            }
            if($('#meetingPlace').val()=='')
            {
                alert("会议地点不能为空");
                $('#meetingPlace').focus();
                return false;
            }
            if($('#meetingPart').val()=='')
            {
                alert("举办单位不能为空");
                $('#meetingPlace').focus();
                return false;
            }

            var date=$("#meetingTime").val();
            var meetingTime=date.replace(/\-/g,'/');
            var now=today.replace(/\-/g,'/');

            if(Date.parse(meetingTime)<Date.parse(now))
            {
                alert("会议日期不能早于今天");
                return false;
            }
            return true;
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
                            <li class="list-group-item"><a href="/ms/userPage/MyMeeting/created.jsp" class="text-danger">我发起的会议</a></li>
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
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    你可以管理你的会议啦!
                </div>
                <div class="alert alert-danger alert-dismissible" id="login_alert" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    您还尚未<a href="/ms/userPage/login.jsp" class="">登录</a>!
                </div>
                <div class="alert alert-warning alert-dismissible" role="alert" id="create_alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    您还没有创建过会议,要<a href="#" class="alert-link">马上创建一个会议</a>吗?
                </div>
                <div class="table-body text-center" id="myMeetings"></div>

                <button id="createNewMeetingButton" type="button" data-toggle="modal" data-target="#createMeetingModal" class="btn btn-block btn-warning">发起新会议</button>
            </div>
        </div>

    </div>
</div>


<div id="createMeetingModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
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
                <button type="button" class="btn btn-primary" onclick="confirmMeeting()">确认创建</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
</body>
</html>


