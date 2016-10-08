
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>springMVC留言板</title>
    <meta charset="UTF-8"/>
    <c:import url="/WEB-INF/pages/include/inc.jsp"/>
</head>
<body>
<c:import url="/WEB-INF/pages/include/user_header.jsp"/>
<h1 class="col-sm-offset-1">留言板</h1>
<div class="container">
    <div class="col-sm-8">
        <form:form modelAttribute="message" method="post" role="form">
            <div class="form-group">
                <div for="message">留言</div>
                    <div class="col-sm-10">
                        <form:textarea path="message" class="form-control" rows="3"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <c:if test="${ifLogin==true}">
                        <input type="submit" class="btn btn-default col-sm-offset-6" value="提交"/>
                    </c:if>
                    <c:if test="${ifLogin==false}">
                        您还未登录，请先
                        <a href="/user/login">登录</a>
                        或
                        <a href="/user/register">注册</a>
                    </c:if>
                </div>
            </div>
        </form:form>
    </div>
    <div class="allcomment col-sm-offset-1 col-sm-10">
            <c:forEach var="message" items="${messages}">
                <div class="comment">
                    <div class="comment-head">
                            ${message[0]}
                    </div>
                    <div class="comment-body col-sm-offset-1">
                            ${message[1]}
                    </div>
                </div>

            </c:forEach>
    </div>

</div>
</body>
</html>
