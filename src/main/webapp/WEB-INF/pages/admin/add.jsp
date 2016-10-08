<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加用户</title>
    <c:import url="/WEB-INF/pages/include/inc.jsp"/>
</head>
<body>
<c:import url="/WEB-INF/pages/include/admin_header.jsp"/>
<h1 class="col-sm-offset-1">增加用户</h1>
<div class="container">
    <form:form modelAttribute="user" method="post" class="form-horizontal" role="form">
        <div class="form-group">
            <label for="name" class="col-sm-2 control-label">name:</label>
            <div class="col-sm-4">
                <form:input path="name" id="name"/>
                <form:errors path="name"/>
            </div>
        </div>
        <div class="form-group">
            <label for="email" class="col-sm-2 control-label">email:</label>
            <div class="col-sm-4">
                <form:input path="email" id="email"/>
                <form:errors path="email"/>
            </div>
        </div>
        <div class="form-group">
            <label for="passwd" class="col-sm-2 control-label">passwd:</label>
            <div class="col-sm-4">
                <form:password path="passwd" id="passwd"/>
                <form:errors path="passwd"/>
            </div>
        </div>
        <div class="form-group">
            <input type="submit" class="btn btn-default col-sm-offset-2" value="添加用户">
        </div>
    </form:form>
</div>
</body>
</html>
