<%--
  User: 李呈云
  Date: 2016/9/29
  Time: 14:23
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>修改用户</title>
</head>
<body>
<h1>修改用户</h1>
<div>
    <form:form modelAttribute="user" method="post">
        <form:input type="hidden" path="id"/>
        <form:input type="hidden" path="imgUrl"/>
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
            <input type="submit" value="提交修改">
        </p>
    </form:form>
</div>

</body>
</html>
