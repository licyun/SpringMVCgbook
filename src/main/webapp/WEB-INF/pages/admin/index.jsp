<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户列表</title>
    <c:import url="/WEB-INF/pages/include/inc.jsp"/>
</head>
<body>
<c:import url="/WEB-INF/pages/include/admin_header.jsp"/>
<h1 class="col-sm-offset-1">管理员界面</h1>
<div class="container">
    <table class="table">
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
                <td><button type="button" class="btn btn-info" onclick="edit('${pageContext.request.contextPath}/admin/message-${user.id}')">查看留言</button></td>
                <td><button type="button" class="btn btn-warning" onclick="edit('${pageContext.request.contextPath}/admin/edit-${user.id}')">编辑用户</button></td>
                <td><button type="button" class="btn btn-danger" onclick="edit('${pageContext.request.contextPath}/admin/delete-${user.id}')">删除用户</button></td>
            </tr>
        </c:forEach>
    </table>
    <div class="col-sm-offset-2">
        <ul class="pagination">
            <li><a href="#">&laquo;</a></li>
            <c:forEach begin="1" end="${count}" var="v" >
                <li><a class="pages" href="${pageContext.request.contextPath}/admin/users-${v}">${v}</a></li>
            </c:forEach>
            <li><a href="#">&raquo;</a></li>
        </ul>
    </div>
</div>
<script>
    function edit(url){
        window.location.href = url;
    }
</script>
</body>
</html>
