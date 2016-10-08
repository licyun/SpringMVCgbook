<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <title>登录</title>
    <meta charset="UTF-8">
    <link type="text/css" rel="stylesheet" href="<c:url value='/static/main.css'/>">
</head>
<body>
<c:import url="/WEB-INF/pages/include/user_header.jsp"/>
    <form:form modelAttribute="user" method="post" cssClass="wrap">
        <div>
            <label>邮箱</label>
            <form:input path="email"/>
            <form:errors path="email"/>
        </div>
        <div>
            <label>密码</label>
            <form:input path="passwd"/>
            <form:errors path="passwd"/>
        </div>
        <div>
            <input type="submit" value="登录"/>
        </div>
    </form:form>
</body>
</html>
