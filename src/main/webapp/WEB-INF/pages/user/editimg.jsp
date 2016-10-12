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

<h1 class="col-sm-offset-1">修改用户头像</h1>
<div class="container">
    <div class="user-left">
        <c:if test="${user.imgUrl == null}">
            <img src="/upload/nopic.jpg" width="100" height="100">
        </c:if>
        <c:if test="${user.imgUrl != null}">
            <img src="/upload/${user.imgUrl}" width="100" height="100">
        </c:if>
    </div>
    <div class="user-right">
        <form method="POST" action="${pageContext.request.contextPath}/user/edit-img" enctype="multipart/form-data" role="form">
            <div class="form-group">
                <label>File to upload: </label>
                <input type="file" name="file"><br />
            </div>
            <div class="form-group">
                <input type="submit" value="Upload">
            </div>
        </form>
    </div>
</div>
</body>
</html>
