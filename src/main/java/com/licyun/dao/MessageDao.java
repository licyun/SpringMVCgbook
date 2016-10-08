package com.licyun.dao;

import com.licyun.model.Message;

import java.util.List;

/**
 * Created by 李呈云
 * Description:
 * 2016/10/7.
 */
public interface MessageDao {
    List<Object[]> findMessagesByUserId(int id);

    Message findMessageById(int id);

    List<Object[]> findAllMessage();

    void saveMessage(Message message);

    void deleteMessage(Message message);
}
