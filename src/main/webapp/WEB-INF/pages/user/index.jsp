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
<div class="container">
    <h1>用户管理界面</h1>
    <div>
        <div class="user-left">
            <c:if test="${user.imgUrl == null}">
                <img src="${pageContext.request.contextPath}/upload/nopic.jpg" width="100" height="100"/>
            </c:if>
            <c:if test="${user.imgUrl != null}">
                <img src="${pageContext.request.contextPath}/upload/${user.imgUrl}" width="100" height="100"/>
            </c:if>
            <div class="user-left-down">
                <div>
                    <a href="${pageContext.request.contextPath}/user/edit-img">修改头像</a>
                </div>
            </div>
        </div>
        <div class="user-right">
            <div class="user-right-row">
                <div>用户id：${user.id}</div>
            </div>
            <div class="user-right-row">
                <div>用户名：${user.name}</div>
            </div>
            <div class="user-right-row">
                <div>用户邮箱：${user.email}</div>
            </div>
            <div class="user-right-row">
                <div>用户密码：${user.passwd}</div>
            </div>
            <div class="col-sm-offset-2">
                <a href="${pageContext.request.contextPath}/user/edit-${user.id}">修改个人信息</a>
            </div>
        </div>

    </div>

</div>
</body>
</html>
