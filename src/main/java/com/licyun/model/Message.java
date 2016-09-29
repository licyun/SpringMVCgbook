package com.licyun.model;

import java.util.List;

/**
 * Created by 李呈云
 * Description:
 * 2016/9/27.
 */
public class Message {
    private Long id;
    private List<String> listMessage;

    public List<String> getListMessage() {
        return listMessage;
    }

    public void setListMessage(List<String> listMessage) {
        this.listMessage = listMessage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
