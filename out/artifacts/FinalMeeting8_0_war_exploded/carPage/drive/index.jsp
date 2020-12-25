<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>会务管理系统-司机</title>
    <meta name="keywords"  content="设置关键词..." />
    <meta name="description" content="设置描述..." />
    <meta name="author" content="DeathGhost" />
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <link rel="icon" href="../../images/icon/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="../../css/css1/style.css" />
    <script src="../../js/javascript/jquery.js"></script>
    <script src="../../js/javascript/plug-ins/customScrollbar.min.js"></script>
    <script src="../../js/javascript/plug-ins/enchants.min.js"></script>
    <script src="../../js/javascript/plug-ins/layerUi/layer.js"></script>
    <script src="../../editor/unEditor.config.js"></script>
    <script src="../../editor/unEditor.all.js"></script>

    <script src="../../js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="../../css/bootstrap.min.css">

    <script src="../../js/javascript/plug-ins/pagination.js"></script>
    <script src="../../js/javascript/public.js"></script>
    <script>
        function completeOrder(userId,driverId,pickTime) {
            if (confirm("确认完成订单吗?") === true) {
                $.get(
                    "/Final/driver",
                    {
                        method: 'complete',
                        did: driverId,
                        uid: userId,
                        pickTime: pickTime,
                    },
                    function (response) {
                        if (response == 1) {
                            alert("恭喜您又可以接受下一份订单了！");
                            window.location.href = '/Final/OrderCar?method=dFindOrder';
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
                    '/Final/Info',
                    {
                        method: 'alterDriverPassword',
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
<body>
<div class="main-wrap">
    <div class="side-nav">
        <div class="side-logo">
            <div class="logo">
				<span class="logo-ico">
					<i class="i-l-1"></i>
					<i class="i-l-2"></i>
					<i class="i-l-3"></i>
				</span>
                <strong>功能模板</strong>
            </div>
        </div>

        <nav class="side-menu content mCustomScrollbar" data-mcs-theme="minimal-dark">

            <ul>
                <li>
                    <dl>
                        <dt>
                            <i class="icon-columns"></i>查看订单<i class="icon-angle-right"></i>
                        </dt>
                        <dd>
                            <a href="/Final/OrderCar?method=dFindOrder">查看订单</a>
                        </dd>
                    </dl>
                </li>
                <li>
                    <dl>
                        <dt>
                            <i class="icon-inbox"></i>司机中心<i class="icon-angle-right"></i>
                        </dt>
                        <dd>
                            <a href="#" id="changeInfo">司机信息</a>
                        </dd>
                        <dd>
                            <a href="#" id="changePassword">修改密码</a>
                        </dd>
                    </dl>
                </li>

            </ul>
        </nav>


    </div>

    <div class="content-wrap">
        <header class="top-hd">
            <div class="hd-lt">
                <a class="icon-reorder"></a>
            </div>
            <div class="hd-rt">
                <ul>
                    <li>
                        <span >欢迎您</span>

                    </li>

                    <li>
                        <a><i class="icon-user"></i>司机:<em>${sessionScope.driver.driverId}</em></a>
                    </li>
                    <li>
                        <a href="#" id="SignOut"><i class="icon-signout"></i>安全退出</a>
                    </li>
                </ul>
            </div>
        </header>
        <main class="main-cont content mCustomScrollbar">
            <div class="page-wrap">
                <!--开始::内容-->
                <section class="page-hd">
                    <header>
                        <h2 class="title">订单详情</h2>
                    </header>
                    <hr>
                    <table class="table text-center">
                        <tr>
                            <td style="font-size: 15px;font-weight: bold">用户</td>
                            <td style="font-size: 15px;font-weight: bold">接驾司机</td>
                            <td style="font-size: 15px;font-weight: bold">接驾时间</td>
                            <td style="font-size: 15px;font-weight: bold">接驾地点</td>
                            <td style="font-size: 15px;font-weight: bold">操作</td>
                        </tr>

                        <c:forEach items="${sessionScope.picklist}" var="picklist">
                            <tr>
                                <td style="font-size: 15px;font-weight: bold"><a href="/Final/Pick?method=showUserInfo&userId=${picklist.userId}">${picklist.userId}</a></td>
                                <td style="font-size: 15px;font-weight: bold">${picklist.driverId}</td>
                                <td style="font-size: 15px;font-weight: bold">${picklist.pickTime}</td>
                                <td style="font-size: 15px;font-weight: bold">${picklist.pickPlace}</td>

                                <td style="font-size: 15px;font-weight: bold"><button class="btn btn-info" type="button" onclick="completeOrder('${picklist.userId}','${picklist.driverId}','${picklist.pickTime}')">订单完成</button></td>
                            </tr>
                        </c:forEach>
                    </table>
                </section>

                <!--开始::结束-->
            </div>
        </main>
    </div>
</div>

<%--模态框--%>
<div id="basic-edit" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">司机信息</h4>
            </div>
            <div class="modal-body">
                <form action="/Final/Info?method=driverChangeInfo" method="post">
                    <div class="form-group">
                        <label>司机账号</label>
                        <input type="text" class="form-control" name="alterID" id="alterID" readonly value="${sessionScope.driver.driverId}">
                    </div>
                    <div class="form-group">
                        <label>当前司机名</label>
                        <input type="text" class="form-control" name="nowName" id="nowName" readonly value="${sessionScope.driver.driverName}">
                    </div>
                    <div class="form-group">
                        <label>载客数</label>
                        <input type="text" class="form-control" name="alterPassengers" id="alterPassengers" required placeholder="请输入载客数">
                    </div>
                    <div class="form-group">
                        <label>电话</label>
                        <input type="text" class="form-control" name="alterPhone" id="alterPhone" required placeholder="请输入电话">
                    </div>
                    <div class="form-group">
                        <label>当前是否忙碌</label>
                        <select class="btn btn-default btn-block" id="alterState" name="alterState" required>
                            <option value="0">否</option>
                            <option value="1">是</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>修改司机名</label>
                        <input type="text" class="form-control" name="alterName" id="alterName" placeholder="请输入司机名" required>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary">提交</button>
                    </div>
                </form>
            </div>

        </div>
    </div><!-- /.modal-content -->
</div><!-- /.modal-dialog -->
<%--模态框2--%>
<div id="basic-edit1" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">修改密码</h4>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label>原密码</label>
                        <input type="password" class="form-control" name="oldPassword" id="oldPassword">
                    </div>

                    <div class="form-group">
                        <label>新密码</label>
                        <input type="password" class="form-control" name="newPassword" id="newPassword">
                    </div>
                    <div class="form-group">
                        <label>确认密码</label>
                        <input type="password" class="form-control" name="surePassword" id="surePassword">
                    </div>
                    <div value="" id="info"></div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" onclick="alterPassword()" id="sub">提交</button>
                    </div>
                </form>
            </div>

        </div>
    </div><!-- /.modal-content -->
</div>
</body>
</html>
