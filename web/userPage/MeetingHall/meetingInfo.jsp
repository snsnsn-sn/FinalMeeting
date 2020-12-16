<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="cn">
<head>
    <meta charset="UTF-8">
    <title>会议详情</title>

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
        var requestId = 'null';

        //加载页面
        $(function () {
            requestId = '<%=request.getParameter("mid")%>';

            if (check_login('<%=session.getAttribute("userID")%>', '<%=session.getAttribute("userName")%>'))
                getMeetingInfo();
            else {
                $("#nav_bar").load("/ms/userPage/nav.html");//载入游客导航栏
                alert("请先登录!!");
                window.location.href = '/ms/userPage/login.jsp';
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
    <!--会议标题-->
    <div class="page-header">
        <!--点这个按钮回到我创建的会议页面-->
        <button class="btn btn-sm" onclick="window.location.href='/ms/userPage/index.jsp'">
            <span class="glyphicon glyphicon-menu-left"></span>
        </button>
        <h1 style="margin-top: -35px;margin-left: 45px" id="meetingTitle">会议名</h1>
    </div>
    <div class="container">
        <div class="row">
            <div class="pane panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">会议简介</h3>
                </div>
                <!--左侧 会议图片 简介-->
                <div class="col-sm-5">
                    <div class="panel-body" style="margin: 20px 36px;height:200px">
                        <img style="width: 100%;height: 180px"
                             src="https://bkimg.cdn.bcebos.com/pic/c75c10385343fbf2b211d073b737dd8065380cd72100?x-bce-process=image/resize,m_lfit,w_268,limit_1/format,f_jpg">
                    </div>
                    <div class="text-left" style="margin: 36px 40px" id="meetingBrief">
                        <h5>这里是会议简介:马老师,发生甚么事了,我说怎么回事,源来式左天,有两个年轻人...</h5>
                    </div>
                </div>
            </div>

            <%--会议信息--%>
            <div class="col-sm-7" style="margin-top: 31px">
                <div id="basicInfo">
                    <%--基础信息--%>
                </div>
                <div id="otherInfo">
                    <%--其他信息--%>
                </div>
                <button style="margin-top: 10px" class="btn btn-success" data-toggle="modal" data-target="#join"
                        id="joinButton">
                    马上报名
                </button>
                <button style="margin-top: 10px" class="btn btn-danger" onclick="quitMeeting()" id="quitButton">
                    取消报名
                </button>
            </div>
        </div>
    </div>
</div>
</div>
</div>
</div>
</div>

<%--========================================================================================--%>
<!--申请加入会议模态框-->
<div id="join" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">与会申请</h4>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label>接驾服务</label>
                        <select class="btn btn-default btn-block" id="useCar">
                            <option value="1">需要</option>
                            <option value="0">不需要</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>乘车人数</label>
                        <input type="text" class="form-control" id="carPeople">
                    </div>
                    <div class="form-group">
                        <label>乘车时间</label>
                        <input type="datetime-local" class="form-control" id="carTime">
                    </div>
                    <div class="form-group">
                        <label>乘车地点</label>
                        <input type="text" class="form-control" id="carPlace">
                    </div>
                    <hr>
                    <div class="form-group">
                        <label>酒店服务</label>
                        <select class="btn btn-default btn-block" id="needHotel">
                            <option value="1">需要</option>
                            <option value="0">不需要</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>住房人数</label>
                        <input type="text" class="form-control" id="hotelPeople">
                    </div>
                    <div class="form-group">
                        <label>入住时间</label>
                        <input type="datetime-local" class="form-control" id="hotelTime">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="submit" class="btn btn-primary" onclick="joinMeeting()">提交申请</button>
            </div>
        </div>
    </div><!-- /.modal-content -->
</div><!-- /.modal-dialog -->
</div><!-- /.modal -->
</body>
</html>

