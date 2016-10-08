<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户留言</title>
    <c:import url="/WEB-INF/pages/include/inc.jsp"/>
</head>
<body>
<c:import url="/WEB-INF/pages/include/admin_header.jsp"/>
<h1 class="col-sm-offset-1">查看用户留言</h1>
<div class="container">
    <div class="col-sm-10">
        <c:forEach var="message" items="${messages}">
            <div class="comment">
                <div class="row">
                    <div class="col-sm-10">
                            ${message[0]}
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-2 pull-right">
                        <a href="/admin/deleteMessage-${id}-${message[1]}">删除该评论</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
