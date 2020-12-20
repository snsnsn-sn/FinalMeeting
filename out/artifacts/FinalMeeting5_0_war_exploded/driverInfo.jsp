<%--
  Created by IntelliJ IDEA.
  User: qaj
  Date: 2020/11/25
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <META HTTP-EQUIV="pragma" CONTENT="no-cache">
    <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
    <META HTTP-EQUIV="expires" CONTENT="0">

    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>DriverLogin</title>
    <!-- 引入 bootstrap核心css-->
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <!-- 引入jQuery核心js文件-->
    <script src="js/jquery-3.1.1.min.js"></script>
    <!-- 引入bootstrap核心js文件-->
    <script src="js/bootstrap.min.js"></script>

    <script src="js/meetingSys.js"></script>
    <script>
        //加载页面
        $(function () {
            if (!check_login('<%=session.getAttribute("userID")%>', '<%=session.getAttribute("userName")%>'))
                $("#nav_bar").load("/Final/userPage/nav.html");
            getLeft(0, 0);
            showMeeting();
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

<%--接驾订单--%>
<div class="col-sm-9">
    <div class="page-header">
        <h1 style="margin-top: -35px">司机信息</h1>
    </div>
    <div>
        <div class="panel panel-default text-center">
            <!--订单列表-->
            <div class="table-body text-center" id="myMeetings">
                <table class="table text-center">
                    <tr>
                        <td>司机账号</td>
                        <td>司机电话</td>
                        <td>司机姓名</td>
                    </tr>
                    <c:forEach items="${sessionScope.driverInfo}" var="driver">
                        <tr>
                            <td>${driver.driverId}</td>
                            <td>${driver.phone}</td>
                            <td>${driver.driverName}</td>
                        </tr>
                    </c:forEach>

                </table>
            </div>
        </div>
    </div>
</div>

</body>
</html>