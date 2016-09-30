<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理员首页</title>
    <link type="text/css" rel="stylesheet" href="<c:url value='/static/admin_main.css'/>">
</head>
<body>
<c:import url="/WEB-INF/pages/include/admin_header.jsp"/>
<h1>管理员界面</h1>
<div>
    <table border="1">
        <thead>
            <td>用户头像</td>
            <td>用户ID</td>
            <td>用户名</td>
            <td>用户邮箱</td>
            <td>查看该用户留言</td>
            <td>编辑该用户</td>
            <td>删除该用户</td>
        </thead>
        <c:forEach var="user" items="${users}">
        <tr>
            <td>img</td>
            <td>${user.id}</td>
            <td>${user.name} </td>
            <td>${user.email} </td>
            <td><a href="/admin/message-${user.id}">查看</a></td>
            <td><a href="/admin/edit-${user.id}">编辑</a></td>
            <td><a href="/admin/delete-${user.id}">删除</a></td>
        </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
