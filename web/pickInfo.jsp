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

</head>
<body>
<div class="table-body text-center" id="myMeetings">
    <table class="table">
        <tr>
            <td>用户名</td>
            <td>司机名</td>
            <td>接驾时间</td>
            <td>上车地点</td>
        </tr>
        <c:forEach items="${sessionScope.pickers}" var="picker">
            <tr>
                <td>${picker.userId}</td>
                <td><a href="/Final/Pick?method=showDriverInfo&driverId=${picker.driverId}">${picker.driverId}</a></td>
                <td>${picker.pickTime}</td>
                <td>${picker.pickPlace}</td>
            </tr>
        </c:forEach>

    </table>
</div>
</body>
</html>