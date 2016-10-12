<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <title>登录</title>
    <meta charset="UTF-8">
    <c:import url="/WEB-INF/pages/include/inc.jsp"/>
</head>
<body>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">留言板</a>
        </div>
    </div>
</nav>
<div class="jumbotron">
    <div class="container">
        <h1 class="col-sm-offset-4">管理员登录</h1>
        <form:form modelAttribute="user" method="post" class="form-horizontal" role="form">
            <div class="form-group">
                <label class="col-sm-offset-2 col-sm-2 control-label">邮箱</label>
                <div class="col-sm-4">
                    <form:input path="email" class="form-control"/>
                    <form:errors path="email"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-offset-2 col-sm-2 control-label">密码</label>
                <div class="col-sm-4">
                    <form:input path="passwd" class="form-control"/>
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
