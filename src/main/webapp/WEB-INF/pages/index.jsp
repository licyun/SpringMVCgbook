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
<c:if test="${ifLogin==true}">
    <c:import url="/WEB-INF/pages/include/user_header.jsp"/>
</c:if>
<c:if test="${ifLogin==false}">
    <c:import url="/WEB-INF/pages/include/login_header.jsp"/>
</c:if>

<h1 class="col-sm-offset-1">留言板</h1>
<div class="container">
    <div class="col-sm-offset-1 col-sm-10">
        <form:form modelAttribute="message" method="post" role="form">
            <div class="form-group">
                    <div class="col-sm-12">
                        <form:textarea path="message" id="message" class="form-control" rows="3"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-4 col-sm-8">
                    <c:if test="${ifLogin==true}">
                        <input type="submit" class="btn btn-default col-sm-offset-8 submit-top" value="提交留言"/>
                    </c:if>
                    <c:if test="${ifLogin==false}">
                        <div class="col-sm-offset-6">
                            您还未登录，请先
                            <a href="/user/login">登录</a>
                            或
                            <a href="/user/register">注册</a>
                        </div>
                    </c:if>
                </div>
            </div>
        </form:form>
    </div>
    <div class="allcomment col-sm-offset-1 col-sm-10" id="comments">

    </div>
    <div class="col-sm-offset-2">
        <ul class="pagination">
            <li><a id="page-before" href="#!">&laquo;</a></li>
            <c:forEach begin="1" end="${pageCount}" var="v" >
                <li><a class="pages" href="#!">${v}</a></li>
            </c:forEach>
            <li><a id="page-after" href="#!">&raquo;</a></li>
        </ul>
    </div>
</div>
<script>
    //闭包循环为分页标签添加监听事件
    var pages = document.getElementsByClassName('pages');
    for (var i = 0; i < pages.length; i++) {
        (function(i){
            pages[i].onclick = function(){
                getjson(i+1);
            }
        })(i);
    }
    var pagebefore = document.getElementById("page-before");
    var pageafter = document.getElementById("page-after");
    var currentpage = 1;
    //上一页添加监听
    pagebefore.onclick = function(){
        if(currentpage != 1)
            currentpage = currentpage - 1;
        getjson(currentpage);
    }
    //下一页添加监听
    pageafter.onclick = function(){
        if(currentpage != ${pageCount})
            currentpage = currentpage + 1;
        getjson(currentpage);
    }
</script>
<script src="/static/pagejson.js"></script>
</body>
</html>
