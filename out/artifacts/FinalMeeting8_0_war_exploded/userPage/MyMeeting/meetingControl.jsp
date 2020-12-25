<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dao.impl.HotelImpl" %>
<html>
<head>
    <title>会务管理系统-会议管理</title>

    <!-- 引入 bootstrap核心css-->
    <link rel="stylesheet" href="../../css/bootstrap.min.css"/>
    <!-- 引入jQuery核心js文件-->
    <script src="../../js/jquery-3.1.1.min.js"></script>
    <!-- 引入bootstrap核心js文件-->
    <script src="../../js/bootstrap.min.js"></script>

    <style>
        a.active {
            background-color: #2b669a;
            color: white;
        }

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
        //加载页面
        var requestId = 'null';
        $(function () {
            requestId = '<%=request.getParameter("mid")%>';
            // alert("meetingId=" + meetingId);

            //未登录
            if (!check_login('<%=session.getAttribute("userID")%>', '<%=session.getAttribute("userName")%>')) {
                $("#nav_bar").load("/Final/userPage/nav.html");//载入游客导航栏
                alert("请先登录!!");
                window.location.href = '/Final/userPage/login.jsp';
            } else {
                getMeeting();
                getInfo();
                getParticipants();
                getMeetingImg(requestId);
            }
        })
    </script>
    <%!
        HotelImpl h = new HotelImpl();
    %>
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
        <button class="btn btn-sm" onclick="window.location.href='/Final/userPage/MyMeeting/created.jsp'">
            <span class="glyphicon glyphicon-menu-left"></span>
        </button>
        <h1 style="margin-top: -35px;margin-left: 45px" id="meetingTitle"></h1>
    </div>

    <div>
        <!-- 会议管理标签页 -->
        <ul class="nav nav-tabs">
            <li class="active"><a href="#mc" aria-controls="home" data-toggle="tab">会议信息管理</a></li>
            <li><a href="#pc" aria-controls="profile" data-toggle="tab">与会者管理</a></li>
        </ul>

        <!-- 标签页内容-->
        <div class="tab-content">

            <%--会议信息管理面板--%>
            <div role="tabpanel" class="tab-pane active" id="mc">
                <div class="panel-body">

                    <div class="row">
                        <%--左侧 会议图片 简介--%>
                        <div class="col-sm-4">
                            <div class="panel panel-default" style="margin: 10px;border-bottom: 5px">
                                <div class="panel-heading">
                                    <h3 class="panel-title">会议简介</h3>
                                </div>
                                <div class="panel-body" style="margin: 20px 36px;">
                                    <img id="meetingImg" style="width: 100%;height: 180px">
                                </div>
                                <div class="text-center" style="margin: 36px 20px">
                                    <h5 id="meetingBrief"></h5>
                                </div>
                                <button class="btn-block btn-success" data-toggle="modal"
                                        data-target="#brief-edit">修改简介
                                </button>
                            </div>
                        </div>
                        <%--右侧 --%>
                        <div class="col-sm-6">
                            <div class="item-label" style="margin-top: 8px">基础信息</div>
                            <div class="table">
                                <table class="table text-center">
                                    <tbody id="basicSet">
                                    <tr data-toggle="modal" data-target="#basic-edit">
                                        <td>会议名称</td>
                                        <td id="meetingName"></td>
                                        <td><span class="glyphicon glyphicon-menu-right"></span></td>
                                    </tr>
                                    <tr data-toggle="modal" data-target="#basic-edit">
                                        <td>会议地点</td>
                                        <td id="meetingPlace"></td>
                                        <td><span class="glyphicon glyphicon-menu-right"></span></td>
                                    </tr>
                                    <tr data-toggle="modal" data-target="#basic-edit">
                                        <td>会议时间</td>
                                        <td id="meetingTime"></td>
                                        <td><span class="glyphicon glyphicon-menu-right"></span></td>
                                    </tr>
                                    </tr>
                                    <tr data-toggle="modal" data-target="#basic-edit">
                                        <td>当前状态</td>
                                        <td id="meetingState"></td>
                                        <td><span class="glyphicon glyphicon-menu-right"></span></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>

                            <div class="item-label" style="margin-top: 20px">详细设置</div>
                            <div class="table">
                                <table class="table text-center">
                                    <tbody>
                                    <tr data-toggle="modal" data-target="#detail-edit">
                                        <td>会议酒店</td>
                                        <td id="meetingHotel"></td>
                                        <td><span class="glyphicon glyphicon-menu-right"></span></td>
                                    </tr>
                                    <tr data-toggle="modal" data-target="#detail-edit">
                                        <td>接驾服务</td>
                                        <td id="meetingCar"></td>
                                        <td><span class="glyphicon glyphicon-menu-right"></span></td>
                                    </tr>
                                    <tr data-toggle="modal" data-target="#detail-edit">
                                        <td>举办单位</td>
                                        <td id="part"></td>
                                        <td><span class="glyphicon glyphicon-menu-right"></span></td>
                                </table>
                            </div>
                            <button class="btn btn-danger btn-block" onclick="meetingDelete()">删除会议</button>
                        </div>
                    </div>
                </div>
            </div>

            <!--与会者管理标签页-->
            <div role="tabpanel" class="tab-pane" id="pc">
                <div class="container-fluid">
                    <table class="table text-center">
                        <thead>
                        <tr>
                            <th>id</th>
                            <th>姓名</th>
                            <th>联系方式</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody id="participants">
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>


<%--=================================================================================================================================--%>
<%--模态框--%>
<%--基本信息修改--%>
<div id="basic-edit" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">基本信息修改</h4>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label>会议名称</label>
                        <input type="text" class="form-control" id="alterName">
                    </div>
                    <div class="form-group">
                        <label>会议时间</label>
                        <input type="date" class="form-control" id="alterTime">
                    </div>
                    <div class="form-group">
                        <label>会议地点</label>
                        <input type="text" class="form-control" id="alterPlace">
                    </div>
                    <div class="form-group">
                        <label>会议状态</label>
                        <select class="btn btn-default btn-block" id="alterState">
                            <option value="0">未开始</option>
                            <option value="1">报名截止</option>
                            <option value="2">进行中</option>
                            <option value="3">已结束</option>
                        </select>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="alterBasic()">确认修改</button>
            </div>
        </div>
    </div><!-- /.modal-content -->
</div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<%--模态框--%>
<%--详细信息修改--%>
<div id="detail-edit" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">其他设置</h4>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label>会议酒店</label>
                        <%--<select class="btn btn-default btn-block" id="alterHotel" name="alterHotel">--%>
                            <%--<option value="豪泰酒店"><%="豪泰酒店--"%><%=h.findByHotelName("豪泰酒店").getHotelPlace()%></option>--%>
                            <%--<option value="万达酒店"><%="万达酒店--"%><%=h.findByHotelName("万达酒店").getHotelPlace()%></option>--%>
                            <%--<option value="前湖宾馆"><%="前湖宾馆--"%><%=h.findByHotelName("前湖宾馆").getHotelPlace()%></option>--%>
                            <%--<option value="如家酒店"><%="如家酒店--"%><%=h.findByHotelName("如家酒店").getHotelPlace()%></option>--%>
                        <%--</select>--%>

                    <input type="text" class="form-control" id="alterHotel" placeholder="请输入酒店名">
                    </div>
                    <div class="form-group">
                        <label>接驾服务</label>
                        <select class="btn btn-default btn-block" id="alterUseCar">
                            <option value="1">提供</option>
                            <option value="0">不提供</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>举办单位</label>
                        <input type="text" class="form-control" id="alterPart">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="alterDetail()">确认修改</button>
            </div>
        </div>
    </div><!-- /.modal-content -->
</div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<%--简介修改--%>
<div id="brief-edit" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">简介修改</h4>
            </div>
            <div class="modal-body">
                <form action="/Final/imgServlet" enctype="multipart/form-data" method="post">
                    <div class="form-group">
                        <label>会议封面</label>
                        <input id="uploadImg" type="file" accept="image/png,image/gif,image/jpg" name="file"/>
                    </div>
                    <div class="form-group">
                        <label>简介</label>
                        <textarea class="form-control" rows="3" id="briefText"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="submit" class="btn btn-primary" onclick="alterBrief()">确认修改</button>
            </div>
        </div>
    </div><!-- /.modal-content -->
</div><!-- /.modal-dialog -->
</div><!-- /.modal -->
</body>
</html>
