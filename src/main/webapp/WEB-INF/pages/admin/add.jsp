<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加用户</title>
    <link type="text/css" rel="stylesheet" href="<c:url value='/static/main.css'/>">
</head>
<body>
<c:import url="/WEB-INF/pages/include/admin_header.jsp"/>
<h1>增加用户</h1>
<div class="wrap">
    <form:form modelAttribute="user" method="post">
        <p>
            <label>name:</label>
            <form:input path="name" id="name"/>
            <form:errors path="name"/>
        </p>
        <p>
            <label>email:</label>
            <form:input path="email" id="email"/>
            <form:errors path="email"/>
        </p>
        <p>
            <label>passwd:</label>
            <form:password path="passwd" id="passwd"/>
            <form:errors path="passwd"/>
        </p>
        <p>
            <input type="submit" value="添加用户">
        </p>
    </form:form>
</div>
</body>
</html>
