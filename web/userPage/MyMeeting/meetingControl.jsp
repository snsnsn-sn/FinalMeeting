<%--
  Created by IntelliJ IDEA.
  User: 71584
  Date: 2020/11/30
  Time: 23:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <!-- 引入 bootstrap核心css-->
    <link rel="stylesheet"  href="/ms/css/bootstrap.min.css"/>
    <!-- 引入jQuery核心js文件-->
    <script src="/ms/js/jquery-3.1.1.min.js"></script>
    <!-- 引入bootstrap核心js文件-->
    <script src="/ms/js/bootstrap.min.js"></script>
    <style>
        a.active{
            background-color: #2b669a;
            color: white;
        }

        .navbar-right .dropdown-menu{
            right: 0;
            left: -45px;
        }
    </style>

    <script>
        //加载页面
        $(function () {
            var meetingId='<%=request.getParameter("mid")%>';

            if(check_login())
            {
                $("#nav_left").load("/ms/userPage/nav.html #left_nav");
                getMeeting(meetingId);
                meetingImg();
            }
            else
            {
                alert("请先登录!!");
                window.location.href='/ms/userPage/login.jsp';
            }
        })

        function meetingImg() {
            $('#chooseImage').on('change',function(){
                var filePath = $(this).val(), //获取到input的value，里面是文件的路径
                    fileFormat = filePath.substring(filePath.lastIndexOf(".")).toLowerCase(),
                    src = window.URL.createObjectURL(this.files[0]); //转成可以在本地预览的格式

                // 检查是否是图片
                if( !fileFormat.match(/.png|.jpg|.jpeg/) ) {
                    error_prompt_alert('上传错误,文件格式必须为：png/jpg/jpeg');
                    return;
                }

                $('#cropedBigImg').attr('src',src);
            });
        }

        function getMeeting(meetingId) {
            $.get(
                '/ms/meeting',
                {fun:'midQuery',mid:meetingId},
                function (response) {
                    // alert(response);
                    var o=JSON.parse(response);
                    var mid=o[i].meetingId;
                    var mname=o[i].meetingName;
                    var place=o[i].place;
                    var time=o[i]/place;
                    var state;
                    if(o[i]==0)
                        state="未开始";
                    if(o[i].state==1)
                        state="报名截止";
                }
            )
        }

        function check_login() {
            var login_id='<%=session.getAttribute("userID")%>';
            return login_id!='null'?true:false;
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
                            <li><a href="#">个人中心</a></li>
                            <li><a href="#">我的消息</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="#" onclick="exit">退出登录</a></li>
                        </ul>
                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div>
    </nav>
</div>
<%--  ======================================================================================================================--%>

<%--下方内容--%>
<div class="row">
    <!--左侧菜单-->
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
            <button class="btn btn-sm" onclick="window.location.href='/ms/userPage/MyMeeting/created.jsp'">
                <span class="glyphicon glyphicon-menu-left"></span>
            </button>
            <h1 style="margin-top: -35px;margin-left: 45px" id="meetingTitle">武林大会</h1>
        </div>

        <div>
            <!-- 会议管理标签页 -->
            <ul class="nav nav-tabs">
                <li  class="active"><a href="#mc" aria-controls="home" data-toggle="tab">会议信息管理</a></li>
                <li><a href="#profile" aria-controls="profile" data-toggle="tab">与会者管理</a></li>
            </ul>

            <!-- 标签页内容-->
            <div class="tab-content">
                <%--会议信息管理面板--%>
                <div role="tabpanel" class="tab-pane active" id="mc">
                    <div class="panel-body">
                        <div class="row">
                            <div class="list-group">
                                <div href="#" class="list-group-item">
                                    <h4 class="list-group-item-heading"><b>设置会议封面</b></h4>
                                    <img id="cropedBigImg" value='custom' style="width: 300px;height: 150px" data-address='' />
                                    <form class="container" enctype="multipart/form-data" method="post" id='formBox' name="form">
                                        <input type="file" id="chooseImage"style="margin-top: 5px" name="file">
                                        <!-- 保存用户自定义的背景图片 -->
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div role="tabpanel" class="tab-pane" id="与会者管理面板">

                </div>
            </div>
    </div>

</body>
</html>
