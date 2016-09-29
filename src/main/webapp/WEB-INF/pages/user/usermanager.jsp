<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户管理界面</title>
</head>
<body>
<h1>用户管理界面</h1>
<div>
        <p>用户id：${user.id}</p>
        <p>用户名：${user.name} </p>
        <p>用户邮箱：${user.email} </p>
        <p>用户密码：${user.passwd} </p>
        <p><a href="/user/edit-${user.id}">修改个人信息</a></p>
</div>
</body>
</html>
