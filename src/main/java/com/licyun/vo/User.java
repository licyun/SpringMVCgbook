package com.licyun.vo;


import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import javax.annotation.Generated;
import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by 李呈云
 * Description:
 * 2016/9/27.
 */
@Component
public class User {
    protected Long id;
    protected int type;
    private String name;
    private String email;
    private String passwd;
    private String imgUrl;

    public User(){}

    public User(Long id, String name, String email, String passwd){
        this.name = name;
        this.id = id;
        this.email = email;
        this.passwd = passwd;
    }

    public User(Long id, String name, String email, String passwd, int type){
        this.name = name;
        this.id = id;
        this.email = email;
        this.passwd = passwd;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) { this.id = id; }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
