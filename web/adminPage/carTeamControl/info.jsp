<%@ page import="dao.impl.DriverImpl" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="icon" type="image/png" sizes="16x16" href="../../images/admin/favicon.png">
    <title>会务管理系统-管理员</title>
    <script href="../../js/jquery.js"></script>
    <link href="../../plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="../../css/adminstyle.css" rel="stylesheet">
    <link href="../../css/colors/default-dark.css" id="theme" rel="stylesheet">
    <script src="../../plugins/jquery/jquery.min.js"></script>
    <script src="../../js/bootstrap.js"></script>
    <style>
        th{
            text-align: center;
        }
        td{
            text-align: center;
        }
    </style>
    <script type="text/javascript">
        function deleteCarTeam(teamId){
            if (confirm("确认要删除这个车队账号吗?") === true) {
                $.get(
                    "/Final/GetInfo",
                    {
                        fun: 'carTeamDelete',
                        teamid: teamId
                    },
                    function (response) {
                        if (response == 1) {
                            alert("已删除!");
                            window.location.href = '/Final/adminPage/carTeamControl/info.jsp';
                        } else {
                            alert("操作失败");
                        }
                    }
                )
            }
        }
        function updateCarTeam(teamId){
            if (confirm("确认要重置该车队账号的密码吗?") === true) {
                $.get(
                    "/Final/GetInfo",
                    {
                        fun: 'carTeamUpdate',
                        teamid: teamId
                    },
                    function (response) {
                        if (response == 1) {
                            alert("密码重置为111111");
                            window.location.href = '/Final/adminPage/carTeamControl/info.jsp';
                        } else {
                            alert("操作失败");
                        }
                    }
                )
            }
        }
        $(function(){
            $("#SignOut").click(function(){
                if(confirm("确定要退出吗？")==true){
                    sessionStorage.clear();
                    window.location="/Final"
                }
            })
        })
        $(function(){
            $("#changeInfo").click(function (){
                $("#basic-edit").modal('show'); //关闭模态框
            })
            $("#changePassword").click(function (){
                $("#basic-edit1").modal('show'); //关闭模态框
            })

        })
        function alterPassword() {
            var pw0 = $("#oldPassword").val();
            var pw1 = $("#newPassword").val();
            var pw2 = $("#surePassword").val();

            if (pw1 != pw2)
                alert("两次输入密码不一致!");
            else {
                $.get(
                    '/Final/admin',
                    {
                        fun: 'alterPassword',
                        pw0: pw0,
                        pw1: pw1
                    },
                    function (response) {
                        if (response == 0) {
                            alert("密码输入错误或用户不存在");
                        } else {
                            alert("修改成功,请重新登录");
                            window.location.href = '/Final/index.jsp';
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
                    <li> <a class="waves-effect waves-dark" href="../../adminPage/carTeamControl/info.jsp" aria-expanded="false"><i class="mdi mdi-earth"></i><span class="hide-menu">车队管理</span></a></li>

                    <li> <a class="waves-effect waves-dark" href="#" id="SignOut" aria-expanded="false"><i class="mdi mdi-earth"></i><span class="hide-menu">退出登录</span></a></li>
                </ul>
            </nav>
        </div>
    </aside>
    <div class="page-wrapper">
        <div class="container-fluid">
            <div class="row page-titles">
                <div class="col-md-5 align-self-center">
                    <h3 class="text-themecolor">车队查询</h3>
                    <div class="col-md-12">
                        <form action="/Final/GetInfo?type=carteam" method="post">
                            <input type="text" placeholder="输入车队id以查找" class="form-control form-control-line" name="carTeamId" id="carTeamId">
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
                            <h4 class="card-title">车队</h4>
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th>账号</th>
                                        <th>密码</th>
                                        <th>车队名</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td>${sessionScope.carteam.teamId}</td>
                                        <td>${sessionScope.carteam.password}</td>
                                        <td>${sessionScope.carteam.teamName}</td>
                                        <c:if test="${sessionScope.carteam.teamId!=null}">
                                            <td>
                                                <input type="button" class="btn btn-success" style="width: 60px" value="删除" onclick="deleteCarTeam('${sessionScope.carteam.teamId}')">
                                                <input type="button" class="btn btn-success" style="width: 100px" value="重置密码" onclick="updateCarTeam('${sessionScope.carteam.teamId}')">
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