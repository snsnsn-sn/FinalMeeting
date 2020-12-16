<%--
  Created by IntelliJ IDEA.
  User: 86175
  Date: 2020/12/8
  Time: 22:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>酒店</title>
    <link type="text/css" rel="stylesheet" href="../css/style.css" />
    <script type="text/javascript" src="../js/jquery.js"></script>
    <script type="text/javascript" src="../js/menu.js"></script>
</head>
<body>
<div class="top"></div>
<div id="header">
    <div class="logo">酒店订单处理系统</div>
    <div class="navigation">
        <ul>
            <li>欢迎您！</li>
            <li><a href="">admin</a></li>
            <li><a href="">修改密码</a></li>
            <li><a href="">设置</a></li>
            <li><a href="">退出</a></li>
        </ul>
    </div>
</div>
<div id="content">
    <div class="left_menu">
        <ul id="nav_dot">
            <li>
                <h4 class="M1"><span></span>系统公告</h4>
                <div class="list-item none">
                    <a href=''>系统公告1</a>
                    <a href=''>系统公告2</a>
                    <a href=''>系统公告3</a>
                </div>
            </li>
            <li>
                <h4 class="M2"><span></span>定单处理</h4>
                <div class="list-item none">
                    <a href=''>查看订单</a>
                    <a href=''>工单处理2</a>
                    <a href=''>工单处理3</a>
                </div>
            </li>
            <li>
                <h4  class="M6"><span></span>数据统计</h4>
                <div class="list-item none">
                    <a href=''>数据统计1</a>
                    <a href=''>数据统计2</a>
                    <a href=''>数据统计3</a>
                </div>
            </li>
            <li>
                <h4   class="M10"><span></span>系统管理</h4>
                <div class="list-item none">
                    <a href=''>系统管理1</a>
                    <a href=''>系统管理2</a>
                    <a href=''>系统管理3</a>
                </div>
            </li>
        </ul>
    </div>
    <div class="m-right">
        <div class="right-nav">
            <ul>
                <li></li>
                <li style="margin-left:25px;">您当前的位置：</li>
                <li><a href="#">系统公告</a></li>
                <li>></li>
                <li><a href="#">最新公告</a></li>
            </ul>
        </div>
        <div class="main">

        </div>
    </div>
</div>
<div class="bottom"></div>
<div id="footer"><p></p></div>
<script>navList(12);</script>
</body>
</html>