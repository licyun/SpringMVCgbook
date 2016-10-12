$(function() {
    getjson(1);
});

//用ajax获取分页json数据 i 为第几页
function getjson(i) {
    $.ajax( {
        type : "get",
        url : "messageJson-"+i,
        dataType:"json",
        success : function(jsondata) {
            $("#comments").empty();
            var data=eval(jsondata);    //解析json
            var length = data.length;   //json对象数
            for(var i =0; i < length; i++){
                //img不存在时调用默认图片
                var imgUrl;
                if(data[i].imgUrl == null)
                    data[i].imgUrl = "nopic.jpg";
                //循环添加div
                var html =
                    "<div class='comment col-sm-offset-1'>" +
                        "<div class='comment-img'>" +
                            "<img src='upload/"+data[i].imgUrl+"' width='30' height='30'>" +
                        "</div>" +
                    "<div class='comment-head '>" +
                        "<div class='row'>" +
                            "<div class='comment-name col-sm-3'>" +
                                "用户名：" + data[i].name +
                            "</div>" +
                            "<div class='comment-date col-sm-3 col-sm-offset-1'>" +
                                "时间：" + data[i].date +
                            "</div>" +
                            "<div class='comment-ip col-sm-3 col-sm-offset-1'>" +
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
    //记录当前页，为上一页和下一页做准备
    currentpage = i;
}