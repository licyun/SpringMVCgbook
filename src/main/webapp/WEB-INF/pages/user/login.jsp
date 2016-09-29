<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title></title>
</head>
<body>
    <form:form modelAttribute="user" method="post">
        <form:input path="email"/>
    </form:form>
</body>
</html>
