<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户留言</title>
    <link type="text/css" rel="stylesheet" href="<c:url value='/static/main.css'/>">
</head>
<body>
<c:import url="/WEB-INF/pages/include/admin_header.jsp"/>
<h1>查看用户留言</h1>
<div class="wrap">
    <table>
        <thead>
        <td>评论：</td>
        <td>删除该评论：</td>
        </thead>
        <c:forEach var="message" items="${messages}">
            <tr>
                <td>${message[0]}</td>
                <td><a href="/admin/deleteMessage-${id}-${message[1]}">删除该评论</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
