<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <title>register</title>
    <meta charset="UTF-8">
    <c:import url="/WEB-INF/pages/include/inc.jsp"/>
</head>
<body>
<c:import url="/WEB-INF/pages/include/user_header.jsp"/>
<h1 class="col-sm-offset-1">修改个人信息</h1>
<form:form modelAttribute="user" method="post" class="form-horizontal" role="form">
    <div class="form-group">
        <label class="col-sm-2 control-label">name:</label>
        <div class="col-sm-4">
            <form:input path="name" />
            <form:errors path="name"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">email:</label>
        <div class="col-sm-4">
            <form:input path="email" id="email"/>
            <form:errors path="email"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">passwd:</label>
        <div class="col-sm-4">
            <form:input path="passwd" id="passwd"/>
            <form:errors path="passwd"/>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-2">
            <input type="submit" value="提交修改">
        </div>
    </div>
</form:form>
</body>
</html>
