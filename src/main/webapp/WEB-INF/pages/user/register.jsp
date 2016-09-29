<%--
  User: 李呈云
  Date: 2016/9/26
  Time: 20:18
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>register</title>
    <meta charset="UTF-8">
</head>
<body>
    <h1>
        注册
    </h1>
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
            <input type="submit" value="提交">
        </p>
    </form:form>
</body>
</html>
