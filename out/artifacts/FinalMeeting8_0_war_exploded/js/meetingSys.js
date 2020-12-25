/**
 * 会务管理系统 页面方法
 *
 * 快速定位 Ctrl+F :
 *      用户首页 index
 *      管理个人会议 meetingControl
 *      会议查询页 meetingQuery
 *      会议详情页 meetingInfo
 *      我创建的会议 created
 *      我加入的会议 joined
 */

// 通用
//------------------------------------------------------------------------------
//获取会议图片路径
function getMeetingImg(mid) {
    $.get(
        '/Final/meeting',
        {
            fun: 'getImg',
            mid: mid
        },
        function (response) {
            console.log("getimg"+response);
            $("#meetingImg").attr('src', response);
        }
    )
}

//检查登录状态并返回
function check_login(uid, name) {
    if (uid != 'null') {
        $("#nav_bar").html(
            '<nav class="navbar navbar-default navbar-inverse">\n' +
            '        <div class="container">\n' +
            '            <div id="nav_left">\n' +
            '                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">\n' +
            '                    <span class="sr-only">Toggle navigation</span>\n' +
            '                    <span class="icon-bar"></span>\n' +
            '                    <span class="icon-bar"></span>\n' +
            '                    <span class="icon-bar"></span>\n' +
            '                </button>\n' +
            '                <a class="navbar-brand" href="#">会议管理系统<small>V1.0</small></a>\n' +
            '            </div>\n' +
            '\n' +
            '            <!-- 导航栏组件 -->\n' +
            '            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">\n' +
            '                <ul class="nav navbar-nav navbar-right">\n' +
            '                    <li class="dropdown">\n' +
            '                        <a href="login.jsp" id="login_a" class="dropdown-toggle" data-toggle="dropdown" role="button"\n' +
            '                           aria-haspopup="true" aria-expanded="false"><!--用户提示-->\n' +
            '                            <img src="https://static.hdslb.com/images/akari.jpg" class="img-circle" style="height: 30px"\n' +
            '                                 width="30px">\n' +
            '                            ' + name + '\n' +
            '                        </a>\n' +
            '                        <ul class="dropdown-menu" style="">\n' +
            '                            <li><a href="/Final/userPage/userCenter.jsp">个人中心</a></li>\n' +
            '                            <li role="separator" class="divider"></li>\n' +
            '                            <li><a onclick="exit()">退出登录</a></li>\n' +
            '                        </ul>\n' +
            '                    </li>\n' +
            '                </ul>\n' +
            '            </div><!-- /.navbar-collapse -->\n' +
            '        </div>\n' +
            '    </nav>'
        )
        return true;
    } else
        return false;
}

//退出登录 清空session
//退出登录 清空session
function exit() {
    $.get(
        '/Final/login',
        {fun: 'exit'},
        function () {
            window.location.href = '/Final/userPage/index.jsp';
        }
    )
}

//获取左侧菜单
//paneID:面板序号
//pageID:子页面序号
function getLeft(panelID, pageID) {
    var a = ['', '', ''];
    var b = ['', '', '', '', '', ''];
    if (pageID != -1) {
        a[panelID] = ' in ';
        b[pageID] = ' class="text-danger" ';
    }
    $("#left_menu").html(
        '<!--左侧菜单-->\n' +
        '        <div class="panel-group" id="accordion">\n' +
        '            <!--面板 会议大厅-->\n' +
        '            <div class="panel panel-default">\n' +
        '                <div class="panel-heading" id="MeetingHall">\n' +
        '                    <h4 class="panel-title">             <!--data-parent:指定父容器-->   <!--href:指定对应面板-->\n' +
        '                        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#MeetingHallChild">\n' +
        '                            <span class="glyphicon glyphicon-home active"></span> 会议大厅\n' +
        '                        </a>\n' +
        '                    </h4>\n' +
        '                </div>\n' +
        '                <div id="MeetingHallChild" class="panel-collapse collapse' + a[0] + '">\n' +
        '                    <div class="panel-body">\n' +
        '                        <!--嵌套列表组-->\n' +
        '                        <ul class="list-group">\n' +
        '                            <li class="list-group-item"><a href="/Final/userPage/index.jsp"' + b[0] + '>会议一览</a>\n' +
        '                            </li>\n' +
        '                            <li class="list-group-item"><a href="/Final/userPage/MeetingHall/meetingQuery.jsp" ' + b[1] + '>会议查询</a>\n' +
        '                            </li>\n' +
        '                        </ul>\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <!--面板 我的会议-->\n' +
        '            <div class="panel panel-default">\n' +
        '                <div class="panel-heading" id="MyMeeting">\n' +
        '                    <h4 class="panel-title">             <!--data-parent:指定父容器-->   <!--href:指定对应面板-->\n' +
        '                        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#MyMeetingChild">\n' +
        '                            <span class="glyphicon glyphicon-th-list"></span> 我的会议\n' +
        '                        </a>\n' +
        '                    </h4>\n' +
        '                </div>\n' +
        '                <div id="MyMeetingChild" class="panel-collapse collapse ' + a[1] + '">\n' +
        '                    <div class="panel-body">\n' +
        '                        <!--嵌套列表组-->\n' +
        '                        <ul class="list-group">\n' +
        '                            <li class="list-group-item"><a href="/Final/userPage/MyMeeting/created.jsp" ' + b[2] + '>我发起的会议</a></li>\n' +
        '                            <li class="list-group-item"><a href="/Final/userPage/MyMeeting/joined.jsp" ' + b[3] + '>我加入的会议</a></li>\n' +
        '                        </ul>\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <!--面板 我的订单-->\n' +
        '            <div class="panel panel-default">\n' +
        '                <div class="panel-heading" id="MyOrder">\n' +
        '                    <h4 class="panel-title">             <!--data-parent:指定父容器-->   <!--href:指定对应面板-->\n' +
        '                        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#MyOrderChild">\n' +
        '                            <span class="glyphicon glyphicon-duplicate"></span> 我的订单\n' +
        '                        </a>\n' +
        '                    </h4>\n' +
        '                </div>\n' +
        '                <div id="MyOrderChild" class="panel-collapse collapse ' + a[2] + '">\n' +
        '                    <div class="panel-body">\n' +
        '                        <!--嵌套列表组-->\n' +
        '                        <ul class="list-group">\n' +
        '                            <li class="list-group-item"><a href="/Final/Pick?method=showOrderCars" ' + b[4] + '>接驾订单</a></li>\n' +
        '                            <li class="list-group-item"><a href="/Final/Pick?method=showOrderHotels" ' + b[5] + '>酒店订单</a></li>\n' +
        '                        </ul>\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '        </div>'
    )
}

// index
//------------------------------------------------------------------------------
// 会议展示
function showMeeting() {
    $.get(
        '/Final/meeting',
        {fun: 'findAll'},
        function (response) {
            var code = '';
            var o = JSON.parse(response);

            for (var i in o) {
                if (i > 5)
                    break;
                var name = o[i].meetingName;
                var time = o[i].time;
                var mid = o[i].meetingId;

                $.ajaxSettings.async = false //取消异步,否则数据将错误
                $.get(
                    '/Final/meeting',
                    {
                        fun: 'getInfo',
                        mid: mid
                    },
                    function (response) {
                        var info = JSON.parse(response);
                        var brief = info.mbrief;
                        var part = info.part;
                        var path = '';
                        $.get(
                            '/Final/meeting',
                            {
                                fun: 'getImg',
                                mid: mid
                            },
                            function (response) {
                                path = response;
                            }
                        )

                        code = '<div class="row" style="margin: 20px 0px">\n' +
                            ' <div class="col-sm-3" style="margin-right: 30px">\n' +
                            '        <img style="height: 183px;width:345px" src=' + path + '\n' +
                            '             alt="会议图片">\n' +
                            '    </div>\n' +
                            '    <div class="col-sm-7 col-sm-offset-1">\n' +
                            '        <p><b>' + name + '</b></p>\n' +
                            '        <p><small>' + time + '</small></p>\n' +
                            '        <p>#' + part + '#\n' +
                            '        <p>' + brief + '</p>\n' +
                            '        <a style="margin-top: 10px" class="btn btn-default" href="/Final/userPage/MeetingHall/meetingInfo.jsp?mid=' + mid + '">了解更多</a>\n' +
                            '    </div>\n' +
                            '</div>';
                        $("#showMeetingDiv").append(code);
                    }
                )
                $.ajaxSettings.async = true;
            }
        }
    )
}

// meetingControl
//------------------------------------------------------------------------------
//获取会议 检查权限
function getMeeting() {
    if (requestId == 'null') {
        alert("会议未选定,无法进入管理界面");
        window.location.href = '/Final/userPage/MyMeeting/created.jsp';
    }
    $.get(
        '/Final/meeting',
        {fun: 'midQuery', mid: requestId},
        function (response) {
            // alert(response);
            var o = JSON.parse(response)[0];

            if (o.meetingId != requestId) {
                alert("你没有权限管理不属于你的会议!")
                window.location.href = '/Final/userPage/MyMeeting/created.jsp';
            } else {
                if (o.state == 0)
                    state = "<span class=\"text-info\">未开始</span>";
                if (o.state == 1)
                    state = "<span class=\"text-warning\">报名截止</span>";
                if (o.state == 2)
                    state = "<span class=\"text-success\">正在进行</span>";
                if (o.state == 3)
                    state = "<span class=\"text-danger\">已结束</span>";

                $("#meetingTitle").html(o.meetingName);
                $("#meetingName").html(o.meetingName);
                $("#meetingPlace").html(o.place);
                $("#meetingState").html(state);
                $("#meetingTime").html(o.time);

                $("#alterName").val(o.meetingName);
                $("#alterPlace").val(o.place);

                var time = new Date();
                var day = ("0" + time.getDate()).slice(-2);
                var month = ("0" + (time.getMonth() + 1)).slice(-2);
                var today = time.getFullYear() + "-" + (month) + "-" + (day);
                $('#alterTime').val(today);
                $("#stateButton").html(state + " <span class=\"caret\"></span> ");
                $("#alterState").val(o.state);
            }

        }
    )
}

//获取会议详细信息
function getInfo() {
    $.get(
        "/Final/meeting",
        {fun: 'getInfo', mid: requestId},
        function (response) {
            var i = JSON.parse(response);

            var hotelName = i.hotel_name;
            var useCar = i.useCar;
            var brief = i.mbrief;
            var part = i.part;

            $("#part").html(part);
            $("#meetingHotel").html(hotelName);
            $("#meetingBrief").html(brief);
            $("#meetingCar").html(useCar == 0 ? "<span class=\"text-danger\">不提供</span>" : "<span class=\"text-success\">提供</span>");
            //模态框
            $("#alterPart").val(part);
            $("#alterHotel").val(hotelName);
        }
    )
}

//获取参会者列表
function getParticipants() {
    $.get(
        "/Final/meeting",
        {fun: 'getParticipants', mid: requestId},
        function (response) {
            var o = JSON.parse(response);
            var code = '';
            for (i in o) {
                var id = o[i].userId;
                var name = o[i].name;
                var phone = o[i].phone;
                code += '<tr>\n' +
                    '<td>' + id + '</td>\n' +
                    '<td>' + name + '</td>\n' +
                    '<td>' + phone + '</td>\n' +
                    '<td><a class="text-danger" href="javascript:removeParticipant(\'' + id + '\',\'' + name + '\')">移除</a></td>\n' +
                    '</tr>'
            }
            $("#participants").html(code);
        }
    )
}


//修改基本信息
function alterBasic() {
    $.get(
        "/Final/meeting",
        {
            fun: 'alterBasic',
            mid: requestId,
            name: $("#alterName").val(),
            place: $("#alterPlace").val(),
            time: $("#alterTime").val(),
            state: $("#alterState").val()
        },
        function (response) {
            if (response == 1)
                getMeeting();
            else
                alert("操作失败");
            $("#basic-edit").modal('hide'); //关闭模态框
        }
    )
}

//修改详细信息
function alterDetail() {
    $.get(
        "/Final/meeting",
        {
            fun: 'alterDetail',
            mid: requestId,
            hotel: $("#alterHotel").val(),
            useCar: $("#alterUseCar").val(),
            part: $("#alterPart").val(),
        },
        function (response) {
            if (response == 1)
                getInfo();
            else
                alert("操作失败");
            $("#detail-edit").modal('hide'); //关闭模态框
        }
    )
}

//修改简介
function alterBrief() {
    var file = $("#uploadImg").get(0).files[0];
    console.log(file);
    var formData = new FormData();//*
    formData.append("image1", file);//*
    $.ajax({
        url: "/Final/imgServlet",
        type: "post",
        data: formData,
        processData: false,//*
        contentType: false,//*
    })
    $.get(
        "/Final/meeting",
        {
            fun: 'alterBrief',
            mid: requestId,
            brief: $("#briefText").val()
        },
        function (response) {
            if (response == 'true') {
                alert("修改成功!");
                console.log(requestId);
                getInfo();
                getMeetingImg();
                $("#brief-edit").modal('hide');
                getMeetingImg(requestId);
            } else
                alert("修改失败");
            $("#brief-edit").modal('hide');
        }
    )
}

//删除会议
function meetingDelete() {
    if (confirm("确认要删除这个会议吗?") == true) {
        $.get(
            "/Final/meeting",
            {
                fun: 'meetingDelete',
                mid: requestId
            },
            function (response) {
                if (response == 1) {
                    alert("已删除!");
                    window.location.href = '/Final/userPage/MyMeeting/created.jsp';
                } else {
                    alert("操作失败");
                }
            }
        )
    }
}

//参会者移除
function removeParticipant(uid, name) {
    if (confirm("确认要移除" + name + "吗?") == true) {
        $.get(
            "/Final/meeting",
            {
                fun: 'removeParticipant',
                mid: requestId,
                uid: uid
            },
            function (response) {
                if (response == 1) {
                    alert("已删除!");
                    getParticipants();
                } else {
                    alert("操作失败");
                }
            }
        )
    }
}


//meetingQuery
//---------------------------
//刷新会议表并展示
function refreshMeetingList() {
    $("#query_alert").hide();
    $.get(
        "/Final/meeting",
        {fun: 'findAll'},
        function (response) {
            console.log(response);
            var o = JSON.parse(response);
            var count = 1;
            var code = '';
            var meetingState = '未知';

            for (var i in o) {
                if (o[i].state === 0)
                    meetingState = "<span class=\"text-info\">未开始</span>";
                if (o[i].state == 1)
                    meetingState = "<span class=\"text-warning\">报名截止</span>";
                if (o[i].state == 2)
                    meetingState = "<span class=\"text-success\">正在进行</span>";
                if (o[i].state == 3)
                    meetingState = "<span class=\"text-danger\">已结束</span>";

                code += '<tr>' +
                    '<th scope="row">' + count + '</th>' +
                    '<td>' + o[i].meetingId + '</td>' +
                    '<td>' + o[i].meetingName + '</td>' +
                    '<td>' + o[i].userId + '</td>' +
                    '<td>' + o[i].time + '</td>' +
                    '<td>' + o[i].place + '</td>' +
                    '<td>' + meetingState + '</td>' +
                    '<td><a class="btn text-info text- btn-sm" href="/Final/userPage/MeetingHall/meetingInfo.jsp?mid=' + o[i].meetingId + '">详情</a></td>' +
                    '</tr>';
                count += 1;
            }
            $("#meetingList").html(code);
        }
    )
}

//通过会议id查询会议
function queryByMeetingId() {
    $("#query_alert").hide();
    $.get(
        "/Final/meeting",
        {fun: 'midQuery', mid: $("#qmid").val()},
        function (response) {
            console.log(response);
            if (response == 0)
                $("#query_alert").show();

            var o = JSON.parse(response);
            var count = 1;
            var code = ''
            var Mstate="未开始";
            for (var i in o) {
                if(o[i].state==0) Mstate='<font color="green">'+"未开始"+'</font>';
                else if(o[i].state==1) Mstate='<font color="red">'+"报名截止"+'</font>';
                else if(o[i].state==2) Mstate='<font color="#f4a460">'+"进行中"+'</font>';
                else if(o[i].state==3) Mstate='<font color="red">'+"已结束"+'</font>';

                code += '<tr>' +
                    '<th scope="row">' + count + '</th>' +
                    '<td>' + o[i].meetingId + '</td>' +
                    '<td>' + o[i].meetingName + '</td>' +
                    '<td>' + o[i].userId + '</td>' +
                    '<td>' + o[i].time + '</td>' +
                    '<td>' + o[i].place + '</td>' +
                    '<td>' + Mstate + '</td>' +
                    '<td><a class="btn text-info text- btn-sm">详情</a> </td>' +
                    '</tr>';
                count += 1;
            }
            $("#meetingList").html(code);
        }
    )
}

//通过会议名查询会议
function queryByMeetingName() {
    $("#query_alert").hide();
    $.get(
        "/Final/meeting",
        {fun: 'nameQuery', name: $("#qmname").val()},
        function (response) {
            console.log(response);
            if (response == 0)
                $("#query_alert").show();

            var o = JSON.parse(response);
            var count = 1;
            var code = ''
            var Mstate="未开始";
            for (var i in o) {
                if(o[i].state==0) Mstate='<font color="green">'+"未开始"+'</font>';
                else if(o[i].state==1) Mstate='<font color="red">'+"报名截止"+'</font>';
                else if(o[i].state==2) Mstate='<font color="#f4a460">'+"进行中"+'</font>';
                else if(o[i].state==3) Mstate='<font color="red">'+"已结束"+'</font>';
                code += '<tr>' +
                    '<th scope="row">' + count + '</th>' +
                    '<td>' + o[i].meetingId + '</td>' +
                    '<td>' + o[i].meetingName + '</td>' +
                    '<td>' + o[i].userId + '</td>' +
                    '<td>' + o[i].time + '</td>' +
                    '<td>' + o[i].place + '</td>' +
                    '<td>' + Mstate + '</td>' +
                    '<td><a class="btn text-info text- btn-sm">详情</a> </td>' +
                    '</tr>';
                count += 1;
            }
            $("#meetingList").html(code);

        }
    )
}

//通过创建者id查询会议
function queryByUserId() {
    $("#query_alert").hide();
    $.get(
        "/Final/meeting",
        {fun: 'cidQuery', cid: $("#qcid").val()},
        function (response) {
            if (response == 0)
                $("#query_alert").show();

            var o = JSON.parse(response);
            var count = 1;
            var code = ''
            var Mstate="未开始";
            for (var i in o) {

                if(o[i].state==0) Mstate='<font color="green">'+"未开始"+'</font>';
                else if(o[i].state==1) Mstate='<font color="red">'+"报名截止"+'</font>';
                else if(o[i].state==2) Mstate='<font color="#f4a460">'+"进行中"+'</font>';
                else if(o[i].state==3) Mstate='<font color="red">'+"已结束"+'</font>';

                code += '<tr>' +
                    '<th scope="row">' + count + '</th>' +
                    '<td>' + o[i].meetingId + '</td>' +
                    '<td>' + o[i].meetingName + '</td>' +
                    '<td>' + o[i].userId + '</td>' +
                    '<td>' + o[i].time + '</td>' +
                    '<td>' + o[i].place + '</td>' +
                    '<td>' + Mstate + '</td>' +
                    '<td><a class="btn text-info text- btn-sm">详情</a> </td>' +
                    '</tr>';
                count += 1;
            }
            $("#meetingList").html(code);
        }
    )
}

//meetingInfo
//---------------------------
/*获取会议详情*/
function getMeetingInfo() {
    if (requestId == 'null') {
        alert("错误!会议未选定!");
        window.location.href = '/Final/userPage/index.jsp';
    }
    $.get(
        '/Final/meeting',
        {fun: 'midQuery', mid: requestId},
        function (response) {
            //信息不存在
            if (response == 0) {
                alert("错误!没有该会议的信息!")
                window.location.href = '/Final/userPage/index.jsp';
            }
            //信息存在
            else {

                //查询用户是否已加入
                $.get(
                    '/Final/meeting',
                    {
                        fun: "isJoin",
                        mid: requestId
                    },
                    function (response) {
                        //已加入
                        if (response == 1) {
                            $("#joinButton").hide();
                            $("#quitButton").show();
                        }
                        //未加入
                        else {
                            $("#quitButton").hide();
                            $("#joinButton").show();
                        }
                    }
                )

                //会议信息
                var o = JSON.parse(response)[0];
                var meetingName = o.meetingName;
                var meetingTime = o.time;
                var meetingPlace = o.place;
                var peopleCount = o.peopleCount;
                var launcher = o.userId;
                var meetingState;
                if (o.state == 0)
                    meetingState = "<span class=\"text-info\"><b>未开始</b></span>";
                if (o.state == 1) {
                    meetingState = "<span class=\"text-warning\"><b>报名截止</b></span>";
                    $("#joinButton").attr("disabled", "true");
                }
                if (o.state == 2)
                    meetingState = "<span class=\"text-success\"><b>正在进行</b></span>";
                if (o.state == 3) {
                    meetingState = "<span class=\"text-danger\"><b>已结束</b></span>";
                    $("#joinButton").attr("disabled", "true");
                }

                $("#meetingTitle").html(meetingName);
                $("#basicInfo").html(
                    '<p><b>' + meetingName + '</b></p>\n' +
                    '<p><small>' + meetingTime + '</small></p>\n' +
                    '<p>会议id: ' + requestId + '</p>\n' +
                    '<p>举办人id: ' + launcher + '</p>\n' +
                    '<p>会议地点:' + meetingPlace + '</p>\n' +
                    '<p>已报名人数:' + peopleCount + '</p>\n' +
                    '<p>当前状态:' + meetingState + '</p>\n'
                )

                //获取详细信息
                $.get(
                    "/Final/meeting",
                    {fun: 'getInfo', mid: requestId},
                    function (response) {
                        var i = JSON.parse(response);

                        var hotelName = i.hotel_name;
                        var useCar = i.useCar;
                        var brief = i.mbrief;
                        var part = i.part;

                        if (i.useCar == 0)
                            useCar = '不提供';
                        else
                            useCar = '提供';

                        $("#otherInfo").html(
                            '<p>会议酒店:' + hotelName + '</p>\n' +
                            '<p>接驾服务:' + useCar + '</p>\n' +
                            '<p>举办方:' + part + '</p>\n'
                        )

                        $("#meetingBrief").html(
                            '<h5>' + brief + '</h5>'
                        )
                    }
                )

            }
        }
    )
}

//会议报名
function joinMeeting() {
    $.get(
        "/Final/meeting",
        {
            fun: 'joinMeeting',
            mid: requestId,
            useCar: $("#useCar").val(),
            needHotel: $("#needHotel").val(),
            carPeople: $("#carPeople").val(),
            carTime: $("#carTime").val(),
            carPlace: $("#carPlace").val(),
            hotelPeople: $("#hotelPeople").val(),
            hotelTime: $("#hotelTime").val()
        },
        function (response) {
            if (response == '1')
                alert("申请成功!")
            else if (response == '-1')
                alert("申请失败!你已经加入了该会议")
            else
                alert("错误");
            $("#join").modal('hide');//关闭模态框
            getMeetingInfo();
        }
    )
}

//会议退出
function quitMeeting() {
    if (confirm("确定要退出该会议吗?") == true) {
        $.get(
            "/Final/meeting",
            {
                fun: 'quitMeeting',
                mid: requestId,
            },
            function () {
                getMeetingInfo();
            }
        )
    }
}

//created
//---------------------------------
//隐藏提示框
function hide_alert() {
    $("#create_alert").hide();
    $("#control_alert").hide();
}

function refreshCreatedList(uid) {
    $.get(
        "/Final/meeting",
        {fun: 'myCreate', id: uid},
        function (response) {
            console.log(response);
            if (response == '请先登录')
                $("#login_alert").show();
            else if (response == 0)
                $('#create_alert').show();
            else {
                var o = JSON.parse(response);
                var count = 1;
                var code = '<table class="table text-center"><thead><tr><th>#</th><th>会议id</th><th>会议名称</th>' +
                    '<th>会议时间</th><th>会议地点</th><th>当前状态</th><th>&nbsp;操作</th></tr></thead><tbody>\n';

                var meetingState = '未知';
                for (var i in o) {
                    if (o[i].state == 0)
                        meetingState = "<span class=\"text-info\">未开始</span>";
                    if (o[i].state == 1)
                        meetingState = "<span class=\"text-warning\">报名截止</span>";
                    if (o[i].state == 2)
                        meetingState = "<span class=\"text-success\">正在进行</span>";
                    if (o[i].state == 3)
                        meetingState = "<span class=\"text-danger\">已结束</span>";

                    var mid = o[i].meetingId;
                    code += '<tr>' +
                        '<th scope="row">' + count + '</th>' +
                        '<td>' + mid + '</td>' +
                        '<td>' + o[i].meetingName + '</td>' +
                        '<td>' + o[i].time + '</td>' +
                        '<td>' + o[i].place + '</td>' +
                        '<td>' + meetingState + '</td>' +
                        '<td><a class="btn text-warning" href="meetingControl.jsp?mid=' + mid + '">管理</a> </td>' +
                        '</tr>';
                    count += 1;
                }
                code += '</tbody></table>';
                $("#myMeetings").html(code);
                $("#control_alert").show();
            }
        }
    )
}

//设置模态框时间
function getDate(uid) {

    $("#meetingTime").val(today);
    $.get(
        "/Final/user",
        {fun: 'getPart', id: 'uid'},
        function (response) {
            if (response != 0)
                $("#meetingPart").val(response);
        }
    )
}

//确认会议创建
function confirmAddMeeting(uid) {
    if (addMeetingCheck())
        $.post(
            "/Final/meeting",
            {
                fun: 'add',
                name: $("#meetingName").val(),
                uid: uid,
                part: $("#meetingPart").val(),
                time: $("#meetingTime").val(),
                place: $("#meetingPlace").val()
            },
            function (response) {
                $("#createMeetingModal").modal('hide'); //关闭模态框
                if (response == 1) {
                    alert("会议创建成功!您可以在管理页面中完善和管理你的会议");
                    refreshCreatedList();
                    $(".form-control").val(''); //清空输入
                } else
                    alert("创建会议失败!")
            }
        )
}

//检查创建会议参数合法性
function addMeetingCheck() {
    if ($("#meetingName").val() == '') {
        alert("会议名称不能为空");
        $('#meetingName').focus();
        return false;
    }
    if ($('#meetingPlace').val() == '') {
        alert("会议地点不能为空");
        $('#meetingPlace').focus();
        return false;
    }
    if ($('#meetingPart').val() == '') {
        alert("举办单位不能为空");
        $('#meetingPlace').focus();
        return false;
    }

    var date = $("#meetingTime").val();
    var meetingTime = date.replace(/\-/g, '/');
    var now = today.replace(/\-/g, '/');

    if (Date.parse(meetingTime) < Date.parse(now)) {
        alert("会议日期不能早于今天");
        return false;
    }
    return true;
}

//joined
//---------------------------
//刷新加入会议列表
function refreshJoinList(uid) {
    $.get(
        "/Final/meeting",
        {fun: 'myJoin', id: uid},
        function (response) {
            console.log(response);
            if (response == '请先登录')
                $("#login_alert").show();
            else if (response == 0)
                $('#create_alert').show();
            else {
                var o = JSON.parse(response);
                var count = 1;
                var code = '<table class="table"><thead><tr><th>#</th><th>会议id</th><th>会议名称</th>' +
                    '<th>会议时间</th><th>会议地点</th><th>当前状态</th><th>&nbsp;操作</th></tr></thead><tbody class="text-center">\n';
                var meetingState = '未知';

                for (var i in o) {
                    if (o[i].state == 0)
                        meetingState = "<span class=\"text-info\">未开始</span>";
                    if (o[i].state == 1)
                        meetingState = "<span class=\"text-warning\">报名截止</span>";
                    if (o[i].state == 2)
                        meetingState = "<span class=\"text-success\">正在进行</span>";
                    if (o[i].state == 3)
                        meetingState = "<span class=\"text-danger\">已结束</span>";
                    code += '<tr>' +
                        '<th scope="row">' + count + '</th>' +
                        '<td>' + o[i].meetingId + '</td>' +
                        '<td>' + o[i].meetingName + '</td>' +
                        '<td>' + o[i].time + '</td>' +
                        '<td>' + o[i].place + '</td>' +
                        '<td>' + meetingState + '</td>' +
                        '<td><a class="btn text-info btn-sm" href="/Final/userPage/MeetingHall/meetingInfo.jsp?mid=' + o[i].meetingId + '">详情</a> </td>' +
                        '</tr>';
                    count += 1;
                }
                code += '</tbody></table>';
                $("#myMeetings").html(code);
                $("#control_alert").show();
            }
        }
    )
}
