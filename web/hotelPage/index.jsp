
<%--
  Created by IntelliJ IDEA.
  User: 86175
  Date: 2020/12/5
  Time: 21:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>酒店</title>
    <link type="text/css" rel="stylesheet" href="../css/style.css"/>
    <link type="text/css" rel="stylesheet" href="../css/bootstrap.min.css" />
    <script type="text/javascript" src="../js/menu.js"></script>
    <script type="text/javascript" src="../js/jquery.js" ></script>
    <script type="text/javascript" src="../js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../js/commons.js"></script>
<script>
    function alterPassword() {
        var pw0 = $("#oldPassword").val();
        var pw1 = $("#newPassword").val();
        var pw2 = $("#surePassword").val();

        if (pw1 != pw2)
            alert("两次输入密码不一致!");
        else {
            $.get(
                '/Final/changeHotelInfo',
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
    <script language="JavaScript">
      function showNotice(){
        var listHtml="<div id=\"juzhong\">\n" +
                "\t\t\t\t\t<h1 align=\"center\">欢迎使用酒店管理系统</h1><br>\t\t\t\t\n" +
                "\t\t\t\t\t<p align=\"center\">2020-12-15</p><br><br><br>\n" +
                "\t\t\t\t\t<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;本系统由南昌大学计软181/185六人小组打造，\n" +
                "\t\t\t\t\t为酒店方提供了各式各样的服务，包括查询订单，订单处理等服务。</p><br><br>\n" +
                "\t\t\t\t\t<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;为了您能够更加愉快的使用本系统，\n" +
                "\t\t\t\t\t我们将进行不断的优化和更新，敬请期待！</p><br><br><br><br>\n" +
                "\t\t\t\t\t<p align=\"center\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;欢迎您的使用！</p>\n" +
                "\t\t\t\t</div>"
        var locationHtml ="<ul>\n" +
                "          <li></li>\n" +
                "          <li style=\"margin-left:25px;\">您当前的位置：</li>\n" +
                "          <li><a href=\"#\">系统公告</a></li>\n" +
                "          <li>></li>\n" +
                "          <li><a href=\"#\">最新公告</a></li>\n" +
                "        </ul>"
        document.getElementById("location").innerHTML=locationHtml;
        document.getElementById("main").innerHTML=listHtml;
      }
      function showOrder(){
        var locationHtml ="<ul>\n" +
                "          <li></li>\n" +
                "          <li style=\"margin-left:25px;\">您当前的位置：</li>\n" +
                "          <li><a href=\"#\">订单处理</a></li>\n" +
                "          <li>></li>\n" +
                "          <li><a href=\"#\">查看订单</a></li>\n" +
                "        </ul>"
        xmlHttp.open("GET","/Final/showOrder",true);
        xmlHttp.onreadystatechange = function (){
          if(xmlHttp.readyState==4){
            var data = xmlHttp.responseText;
            var obj = JSON.parse(data);
            var listHtml = "<table class=\"table\" id=\"tab\">\n" +
                    "          <caption>订单信息</caption>\n" +
                    "          <thead>\n" +
                    "          <tr>\n" +
                    "            <th>订单</th>\n" +
                    "            <th>用户</th>\n" +
                    "            <th>人数</th>\n" +
                    "            <th>酒店</th>\n" +
                    "            <th>状态</th>\n" +
                    "            <th>入住时间</th>\n" +
                    "            <th>&nbsp;处理</th>\n" +
                    "          </tr>\n" +
                    "         </thead>\n"+
                    "         <tbody>\n";
            document.getElementById("location").innerHTML=locationHtml;
            for(var i=0;i<obj.length;i++){
              var k=i+1;
              if(obj[i].state==0){
                listHtml+="<tr><td>"+k+"</td><td>"+obj[i].userId+"</td><td>"+obj[i].people+"</td><td>"+obj[i].hotelName+"</td>" +
                        "<td>未审核</td><td>"+obj[i].ordertime+"</td><td><button type=\"button\" class=\"btn btn-default\" onclick=\"examine('"+obj[i].userId+"','"+obj[i].ordertime+"')\">审核</button></td></tr>";
              }
              if(obj[i].state==1){
                listHtml+="<tr><td>"+k+"</td><td>"+obj[i].userId+"</td><td>"+obj[i].people+"</td><td>"+obj[i].hotelName+"</td>" +
                        "<td>未通过</td><td>"+obj[i].ordertime+"</td><td><button type=\"button\" class=\"btn btn-default\" onclick=\"examine('"+obj[i].userId+"','"+obj[i].ordertime+"')\">删除</button></td></tr>";
              }
              if(obj[i].state==2){
                listHtml+="<tr><td>"+k+"</td><td>"+obj[i].userId+"</td><td>"+obj[i].people+"</td><td>"+obj[i].hotelName+"</td>" +
                        "<td>已通过</td><td>"+obj[i].ordertime+"</td><td><button type=\"button\" class=\"btn btn-default\" onclick=\"examine('"+obj[i].userId+"','"+obj[i].ordertime+"')\">取消</button></td></tr>";
              }
            }
            listHtml+="</tbody>\n" +
                    "  </table>";
            document.getElementById("main").innerHTML=listHtml;
          }
        }
        xmlHttp.send();
      }
      <%--审核修改status为2--%>
      function examine(userId,ordertime){
        var r = confirm("确定通过审核吗");
        if (r == true) {
          xmlHttp.open("GET","/Final/updateState?id="+userId+"&orderTime="+ordertime+"&state=2",true);
          xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState == 4) {
                alert("审核成功！");
                showOrder();
            }
          }
          xmlHttp.send();
        }
      }
      <%--删除该订单--%>
      function deleteOrder(userId,ordertime){
        var r = confirm("确定删除订单吗");
        if (r == true) {
          xmlHttp.open("GET","/Final/updateState?id="+userId+"&orderTime="+ordertime+"&state=3",true);
          xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState == 4) {
              alert("删除成功！");
              showOrder();
            }
          }
          xmlHttp.send();
        }
      }
      <%--取消该订单，将state改为1--%>
      function cancel(userId,ordertime){
        var r = confirm("确定取消订单吗");
        if (r == true) {
          xmlHttp.open("GET","/Final/updateState?id="+userId+"&orderTime="+ordertime+"&state=1",true);
          xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState == 4) {
              alert("取消成功！");
              showOrder();
            }
          }
          xmlHttp.send();
        }
      }
    </script>
  </head>
  <body onload="initAJAX();showNotice()">
  <div class="top"></div>
  <div id="header">
    <div class="logo">酒店订单处理系统</div>
    <div class="navigation">
      <ul>
        <li>欢迎您！</li>
          <li><%=session.getAttribute("hotelID")%></li>
        <li><a id="changeInfo">修改信息</a></li>
        <li><a id="changePassword">修改密码</a></li>
        <li><a href="/Final/index.jsp">退出</a></li>
      </ul>
    </div>
  </div>
  <div id="content">
    <div class="left_menu">
      <ul id="nav_dot">
        <li>
          <h4 class="M1"><span></span>系统公告</h4>
          <div class="list-item none">
            <a href="javascript:showNotice()">最新公告</a>
          </div>
        </li>
        <li>
          <h4 class="M2"><span></span>定单处理</h4>
          <div class="list-item none">
            <a href="javascript:showOrder() ">查看订单</a>
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
      </ul>
    </div>
    <div class="m-right">
      <div class="right-nav" id="location">

      </div>
      <div class="main" id="main">

      </div>
    </div>
  </div>
  <div class="bottom"></div>
  <div id="footer"><p></p></div>
  <script>
      navList(12);
      $(function(){
          $("#changeInfo").click(function () {
              $("#basic-edit").modal('show'); //关闭模态框
          })
          $("#changePassword").click(function () {
              $("#basic-edit1").modal('show'); //关闭模态框
          })

      })
  </script>

  <%--模态框--%>
  <div id="basic-edit" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
          </button>
          <h4 class="modal-title">个人信息</h4>
        </div>
        <div class="modal-body">
          <form action="/Final/changeHotelInfo?method=changeInfo" method="post">
            <div class="form-group">
              <label>用户名</label>
              <input type="text" class="form-control" name="alterID" id="alterID" readonly value="${sessionScope.hotelID}">
            </div>
              <div class="form-group">
                  <label>当前酒店名</label>
                  <input type="text" class="form-control" name="alterID" id="nowName" readonly value="${sessionScope.hotelName}">
              </div>
            <div class="form-group">
              <label>酒店位置</label>
              <input type="text" class="form-control" name="alterPlace" id="alterPlace" value="${sessionScope.hotelPlace}">
            </div>
            <div class="form-group">
              <label>修改酒店名称</label>
              <%--<input type="text" class="form-control" name="alterHotel" id="alterHotel" required>--%>
              <select class="btn btn-default btn-block" id="alterHotel" name="alterHotel">
                <option value="豪泰酒店">豪泰酒店</option>
                <option value="万达酒店" selected>万达酒店</option>
                <option value="前湖宾馆">前湖宾馆</option>
                <option value="如家酒店">如家酒店</option>
              </select>
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
  </div><!-- /.modal-di
  </body>
</html>
