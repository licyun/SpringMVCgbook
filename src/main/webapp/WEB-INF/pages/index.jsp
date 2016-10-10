
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
                        <form:textarea path="message" class="form-control" rows="3"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-4 col-sm-8">
                    <c:if test="${ifLogin==true}">
                        <input type="submit" class="btn btn-default col-sm-offset-8" value="提交留言"/>
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
    <div class="col-sm-offset-1">
        <ul class="pagination">
            <li><a href="#">&laquo;</a></li>
            <c:forEach begin="1" end="${pageCount}" var="v" >
                <li><a class="pages" >${v}</a></li>
            </c:forEach>
            <li><a href="#">&raquo;</a></li>
        </ul>
    </div>
</div>
<script>
    var pages = document.getElementsByClassName('pages');
    console.log(pages.length);
    for (var i = 0; i < pages.length; i++) {
        (function(i){
            pages[i].onclick = function(){
                getjson(i+1);
            }
        })(i);
    }
</script>
<script>
    $(function() {
        getjson(1);
    });

    function getjson(i) {
        $.ajax( {
            type : "get",
            url : "/messageJson-"+i,
            dataType:"json",
            success : function(jsondata) {
                $("#comments").empty();
                var data=eval(jsondata);
                var length = data.length;
                for(var i =0; i < length; i++){
                    var imgUrl;
                    if(data[i].imgUrl == null)
                        data[i].imgUrl = "nopic.jpg";
                    var html =
                            "<div class='comment'>" +
                                "<div class='comment-img'>" +
                                    "<img src='/upload/"+data[i].imgUrl+"' width='30' height='30'>" +
                                "</div>" +
                                "<div class='comment-head'>" +
                                    "<div class='row'>" +
                                        "<div class='comment-name col-sm-offset-1'>" +
                                            "用户名：" + data[i].name +
                                        "</div>" +
                                        "<div class='comment-date col-sm-offset-1'>" +
                                            "时间：" + data[i].date +
                                        "</div>" +
                                        "<div class='comment-ip col-sm-offset-1'>" +
                                            "IP：" + data[i].ip +
                                        "</div>" +
                                    "</div>" +
                                "</div>" +
                                "<div class='clear'></div>" +
                                "<div class='comment-body col-sm-offset-1'>" +
                                    data[i].message +
                                "</div>" +
                            "</div>";
                    $("#comments").append(html);
                }
            }
        });
    }
</script>
</body>
</html>
