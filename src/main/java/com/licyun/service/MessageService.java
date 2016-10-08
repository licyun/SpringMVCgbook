package com.licyun.service;

import com.licyun.model.Message;
import com.licyun.vo.MessageBean;

import java.util.List;

/**
 * Created by 李呈云
 * Description:
 * 2016/10/7.
 */
public interface MessageService {

    List<Object[]> findMessagesByUserId(int id);

    Message findMessageById(int id);

    List<Object[]> findAllMessage();

    void saveMessage(Message message);

    void deleteMessage(Message message);

    void deleteMessageById(int id);
}
