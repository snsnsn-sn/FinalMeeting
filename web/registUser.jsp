<%--
  Created by IntelliJ IDEA.
  User: qaj
  Date: 2020/11/25
  Time: 22:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <META HTTP-EQUIV="pragma" CONTENT="no-cache">
    <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
    <META HTTP-EQUIV="expires" CONTENT="0">

    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>UserLogin</title>

    <link rel="stylesheet" href="css/regist.css">
    <script src='js/jquery.js'></script>
</head>
<body>
    <%! String registInfo1=""; %>
    <%
        registInfo1 = (String)request.getAttribute("registInfo");
        if(registInfo1==null)
            registInfo1 = "";
    %>
    <div class="Mapper"></div>
    <div>
        <form method="post" action="/Final/Regist">
            <div class="signup-form">
                <h1>用户注册</h1>
                <input type="text" placeholder="账号" required="required" name="id" id="id" class="txtb" />
                <div id="infoDiv"></div>
                <input type="password" placeholder="密码" required="required" name="password" class="txtb" />
                <input type="text" placeholder="电话" required="required" name="phone" class="txtb" />
                <input type="text" placeholder="所属部门" required="required" name="part" class="txtb" />
                <input type="text" placeholder="真实姓名" required="required" name="name" class="txtb" />

                <input type="hidden" name="idValid" id="idValid" value=""/>
                <input type="hidden" name="type" value="user">
                <div style="color:red;font-size:5px;font-weight:bold"  id="registInformation"><p style="font-size: 15px"><%=registInfo1%></p></div>
                <input type="submit" value="注册" class="signup-btn" />
                <a href="index.jsp">已有账号 ？</a>
            </div>
        </form>
        <script src="js/userRegist.js"></script>
    </div>
</body>
</html>
