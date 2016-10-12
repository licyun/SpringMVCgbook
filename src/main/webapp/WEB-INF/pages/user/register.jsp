<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <title>注册</title>
    <meta charset="UTF-8">
    <c:import url="/WEB-INF/pages/include/inc.jsp"/>
</head>
<body>
<c:import url="/WEB-INF/pages/include/login_header.jsp"/>
<div class="jumbotron">
    <div class="container">
        <h1 class="col-sm-offset-4">用户注册</h1>
        <form:form modelAttribute="user" method="post" class="form-horizontal" role="form">
            <div class="form-group">
                <label class="col-sm-offset-2 col-sm-2 control-label">用户名</label>
                <div class="col-sm-4">
                    <form:input path="name" id="name"/>
                    <form:errors path="name"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-offset-2 col-sm-2 control-label">邮箱</label>
                <div class="col-sm-4">
                    <form:input path="email" id="email"/>
                    <form:errors path="email"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-offset-2 col-sm-2 control-label">密码</label>
                <div class="col-sm-4">
                    <form:password path="passwd" id="passwd"/>
                    <form:errors path="passwd"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-4 col-sm-4">
                    <input type="submit" class="btn btn-primary btn-lg" role="button" value="登录"/>
                </div>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>
