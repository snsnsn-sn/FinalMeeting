<%@ page import="dao.impl.DriverImpl" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="icon" type="image/png" sizes="16x16" href="../../images/admin/favicon.png">
    <title>Home</title>
    <script href="../../js/jquery.js"></script>
    <link href="../../plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="../../css/adminstyle.css" rel="stylesheet">
    <link href="../../css/colors/default-dark.css" id="theme" rel="stylesheet">
    <script src="../../plugins/jquery/jquery.min.js"></script>
    <style>
        th{
            text-align: center;
        }
        td{
            text-align: center;
        }
    </style>
    <script type="text/javascript">
        function deleteDriver(driverId){
            if (confirm("确认要删除这个司机吗?") === true) {
                $.get(
                    "/Final/GetInfo",
                    {
                        fun: 'driverDelete',
                        did: driverId
                    },
                    function (response) {
                        if (response == 1) {
                            alert("已删除!");
                            window.location.href = '/Final/adminPage/driverControl/info.jsp';
                        } else {
                            alert("操作失败");
                        }
                    }
                )
            }
        }
        function updateDriver(driverId){
            if (confirm("确认要重置该司机的密码吗?") === true) {
                $.get(
                    "/Final/GetInfo",
                    {
                        fun: 'driverUpdate',
                        did: driverId
                    },
                    function (response) {
                        if (response == 1) {
                            alert("密码重置为111111");
                            window.location.href = '/Final/adminPage/driverControl/info.jsp';
                        } else {
                            alert("操作失败");
                        }
                    }
                )
            }
        }
    </script>
</head>

<body class="fix-header card-no-border fix-sidebar">
<div class="preloader">
    <div class="loader">
        <div class="loader__figure"></div>
        <p class="loader__label">Admin Pro</p>
    </div>
</div>
<div id="main-wrapper">
    <header class="topbar">
        <nav class="navbar top-navbar navbar-expand-md navbar-light">
            <div class="navbar-header">
                <a class="navbar-brand" href="../../adminPage/index.jsp">
                    <b>
                        <img src="../../images/admin/logo.jpg" alt="homepage" class="dark-logo" />
                    </b>
                    <span>
                            <img src="../../images/admin/logo-text.png" alt="homepage" class="dark-logo" />
                        </span>
                </a>
            </div>
            <div class="navbar-collapse">
                <ul class="navbar-nav mr-auto">
                    <!-- This is  -->
                    <li class="nav-item"> <a class="nav-link nav-toggler hidden-md-up waves-effect waves-dark" href="javascript:void(0)"><i class="ti-menu"></i></a> </li>
                </ul>
                <ul class="navbar-nav my-lg-0">
                    <li class="nav-item hidden-xs-down search-box"> <a class="nav-link hidden-sm-down waves-effect waves-dark" href="javascript:void(0)"><i class="ti-search"></i></a>
                        <form class="app-search">
                            <input type="text" class="form-control" placeholder="Search & enter"> <a class="srh-btn"><i class="ti-close"></i></a>
                        </form>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
    <aside class="left-sidebar">
        <div class="scroll-sidebar">
            <nav class="sidebar-nav">
                <ul id="sidebarnav">
                    <li> <a class="waves-effect waves-dark" href="../../adminPage/index.jsp" aria-expanded="false"><i class="mdi mdi-gauge"></i><span class="hide-menu">用户管理</span></a></li>
                    <li> <a class="waves-effect waves-dark" href="../../adminPage/hotelControl/info.jsp" aria-expanded="false"><i class="mdi mdi-account-check"></i><span class="hide-menu">酒店管理</span></a></li>
                    <li> <a class="waves-effect waves-dark" href="../../adminPage/driverControl/info.jsp" aria-expanded="false"><i class="mdi mdi-earth"></i><span class="hide-menu">司机管理</span></a></li>
                </ul>
            </nav>
        </div>
    </aside>
    <div class="page-wrapper">
        <div class="container-fluid">
            <div class="row page-titles">
                <div class="col-md-5 align-self-center">
                    <h3 class="text-themecolor">司机查询</h3>
                    <div class="col-md-12">
                        <form action="/Final/GetInfo?type=driver" method="post">
                            <input type="text" placeholder="输入司机id以查找" class="form-control form-control-line" name="driverId" id="driverId">
                            <input type="submit" class="btn btn-success" value="查询">
                        </form>
                    </div>
                </div>
            </div>
            <div class="row">
                <!-- column -->
                <div class="col-lg-12">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title">司机</h4>
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th>账号</th>
                                        <th>密码</th>
                                        <th>电话</th>
                                        <th>最大载客量</th>
                                        <th>姓名</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td>${sessionScope.driver.driverId}</td>
                                        <td>${sessionScope.driver.password}</td>
                                        <td>${sessionScope.driver.phone}</td>
                                        <td>${sessionScope.driver.passengers}</td>
                                        <td>${sessionScope.driver.driverName}</td>
                                        <c:if test="${sessionScope.driver.driverId!=null}">
                                            <td>
                                                <input type="button" class="btn btn-success" style="width: 60px" value="删除" onclick="deleteDriver('${sessionScope.driver.driverId}')">
                                                <input type="button" class="btn btn-success" style="width: 100px" value="重置密码" onclick="updateDriver('${sessionScope.driver.driverId}')">
                                            </td>
                                        </c:if>
                                    </tr>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="../../plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap tether Core JavaScript -->
<script src="../../plugins/bootstrap/js/popper.min.js"></script>
<script src="../../plugins/bootstrap/js/bootstrap.min.js"></script>
<!-- slimscrollbar scrollbar JavaScript -->
<script src="../../js/admin/perfect-scrollbar.jquery.min.js"></script>
<!--Wave Effects -->
<script src="../../js/admin/waves.js"></script>
<!--Menu sidebar -->
<script src="../../js/admin/sidebarmenu.js"></script>
<!--Custom JavaScript -->
<script src="../../js/admin/custom.js"></script>

</body>

</html>