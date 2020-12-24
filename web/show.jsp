<%--
  Created by IntelliJ IDEA.
  User: qaj
  Date: 2020/12/4
  Time: 22:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <table>
        <th>用户名</th>
        <th>人数</th>
        <th>预约地点</th>
        <th>订单状态</th>
        <th>截止时间</th>
        <c:forEach items="${orders}" var="order">
            <td>${order.userId}</td>
            <td>${order.people}</td>
            <td>${order.place}</td>
            <td>
                <c:choose>
                    <c:when test="${order.state==0}">
                        <p>未审核</p>
                    </c:when>
                    <c:when test="${order.state==0}">
                        <p>未通过</p>
                    </c:when>
                    <c:otherwise>
                        <p>已通过</p>
                    </c:otherwise>
                </c:choose>
            </td>
            <td>${order.deadline}</td>
        </c:forEach>
    </table>
</body>
</html>
