<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>会务管理系统V1.0</title>

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
      if(check_login())
        $("#nav_left").load("/ms/userPage/nav.html #left_nav");
      else
        $("#nav_bar").load("/ms/userPage/nav.html");
    })

    function check_login() {
      console.log("check_login....")
      var login_id='<%=session.getAttribute("userID")%>';
      console.log(login_id);
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

<!--页面正文-->
<div class="row">

  <!--左侧菜单 -->
  <%--  ======================================================================================================================--%>
  <div class="col-sm-2" style="margin-left: 10px">
    <!--左侧菜单-->
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
        <div id="MeetingHallChild" class="panel-collapse collapse in">
          <div class="panel-body">
            <!--嵌套列表组-->
            <ul class="list-group">
              <li class="list-group-item"><a href="/ms/userPage/index.jsp" class="text-danger">会议一览</a></li>
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
  <%--  ======================================================================================================================--%>

  <!--中心内容-->
  <%--  ======================================================================================================================--%>
  <div class="col-sm-9">

    <%-- 巨幕  网站介绍--%>
    <div class="jumbotron">
      <div class="container">
        <h1>会务管理系统<small>V1.0</small></h1>
        <p id="p">
          对于活动主办方来说，通过传统方式举办一场活动繁琐的事情很多，从网站搭建、邀请参会者、酒店预订、接驾安排等等，
          这其中的繁琐与大量的财力物力支出都不可避免，<span class="text-info"><b>会务管理系统1.0</b></span>
          提供的一站式数字化在线管理极大的缩减了筹备时间，专业高效，系统完备。
        </p>
        <p><a class="btn btn-primary btn-lg" href="#" role="button">了解更多</a></p>
      </div>
    </div>

    <%-- 面板 筛选会议类型--%>
    <div class="panel panel-default">
      <!-- 会议筛选按钮 -->
      <table class="table text-center" style="vertical-align:middle">
        <tr>
          <td><b><a class="btn disabled" style="color: black">类型：</a></b></td>
          <td><a class="btn active" id="type_buxian">不限</a></td>
          <td><a class="btn" id="type_yiliao">医疗会议</a></td>
          <td><a class="btn" id_xueshu>学术会议</a></td>
          <td><a class="btn">论坛、峰会</a></td>
          <td><a class="btn">培训会</a></td>
          <td><a class="btn">年会</a></td>
          <td><a class="btn">国际大会</a></td>
          <td><a class="btn">经销商会议</a></td>
          <td><a class="btn">其他</a></td>
        </tr>
        <tr>
          <td><b><a class="btn disabled" style="color: black">行业：</a></b></td>
          <td><a class="btn active">不限</a></td>
          <td><a class="btn">医疗</a></td>
          <td><a class="btn">金融</a></td>
          <td><a class="btn">培训</a></td>
          <td><a class="btn">政府</a></td>
          <td><a class="btn">媒体</a></td>
          <td><a class="btn">第三方</a></td>
          <td><a class="btn">汽车</a></td>
          <td><a class="btn">其他</a></td>
        </tr>
        <tr>
          <td><b><a class="btn disabled" style="color: black">城市：</a></b></td>
          <td><a class="btn active">不限</a></td>
          <td><a class="btn">南昌</a></td>
          <td><a class="btn">北京</a></td>
          <td><a class="btn">珠海</a></td>
          <td><a class="btn">上海</a></td>
          <td><a class="btn">广州</a></td>
          <td><a class="btn">杭州</a></td>
          <td><a class="btn">成都</a></td>
          <td><a class="btn">其他</a></td>
        </tr>
      </table>
    </div>

    <%--会议展示--%>
    <div class="container-fluid" id="showMeetingDiv" style="margin-bottom: 20px">
      <%--会议模板--%>
        <%--会议图片--%>
      <div class="row" style="margin: 20px 0px">
        <div class="col-sm-3"style="margin-right: 30px">
          <img style="height: 183px;width:345px" src="https://www.canevent.com/index3.0/images/39-img.jpg" alt="会议图片">
        </div>
          <%--会议信息--%>
        <div class="col-sm-7 col-sm-offset-1">
          <p><b>2018年“联动未来”ACCA全球高峰论坛</b></p>
          <p><small>2018-6-22</small></p>
          <p>#ACCA论坛#&nbsp;&nbsp;&nbsp;&nbsp; #高峰论坛#</p>
          <p>2018年6月22日，ACCA（特许公认会计师公会）全球高峰论坛在北京中国大饭店圆满落幕，以“联动未来”为主题，
            本次峰会掀开了ACCA进入中国内地30周年（1988-2018）系列庆祝活动的新高潮。
          </p>
          <button style="margin-top: 10px">了解更多</button>
        </div>
      </div><hr>

        <div class="row" style="margin: 20px 0px">
          <div class="col-sm-3"style="margin-right: 30px">
            <img style="height: 183px;width:345px" src="https://www.canevent.com/index3.0/images/37-img.jpg" alt="会议图片">
          </div>
          <%--会议信息--%>
          <div class="col-sm-7 col-sm-offset-1">
            <p><b>2018年“联动未来”ACCA全球高峰论坛</b></p>
            <p><small>2018-6-22</small></p>
            <p>#ACCA论坛#&nbsp;&nbsp;&nbsp;&nbsp; #高峰论坛#</p>
            <p>2018年6月22日，ACCA（特许公认会计师公会）全球高峰论坛在北京中国大饭店圆满落幕，以“联动未来”为主题，
              本次峰会掀开了ACCA进入中国内地30周年（1988-2018）系列庆祝活动的新高潮。
            </p>
            <button style="margin-top: 10px">了解更多</button>
          </div>
        </div><hr>

        <div class="row" style="margin: 20px 0px">
          <div class="col-sm-3"style="margin-right: 30px">
            <img style="height: 183px;width:345px" src="https://www.canevent.com/index3.0/images/36-img.jpg" alt="会议图片">
          </div>
          <%--会议信息--%>
          <div class="col-sm-7 col-sm-offset-1">
            <p><b>2018年“联动未来”ACCA全球高峰论坛</b></p>
            <p><small>2018-6-22</small></p>
            <p>#ACCA论坛#&nbsp;&nbsp;&nbsp;&nbsp; #高峰论坛#</p>
            <p>2018年6月22日，ACCA（特许公认会计师公会）全球高峰论坛在北京中国大饭店圆满落幕，以“联动未来”为主题，
              本次峰会掀开了ACCA进入中国内地30周年（1988-2018）系列庆祝活动的新高潮。
            </p>
            <button style="margin-top: 10px">了解更多</button>
          </div>
        </div><hr>
    </div>


</body>
</html>


