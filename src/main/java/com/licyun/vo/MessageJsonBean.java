package com.licyun.vo;

/**
 * Created by 李呈云
 * Description:
 * 2016/10/10.
 */

public class MessageJsonBean {

    private String name;
    private String ip;
    private String date;
    private String message;
    private String imgUrl;

    public MessageJsonBean(String name, String ip, String date, String message, String imgUrl) {
        this.name = name;
        this.ip = ip;
        this.date = date;
        this.message = message;
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



}
