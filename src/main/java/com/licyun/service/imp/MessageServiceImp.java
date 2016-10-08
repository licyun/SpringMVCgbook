package com.licyun.service.imp;

import com.licyun.dao.MessageDao;
import com.licyun.model.Message;
import com.licyun.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by 李呈云
 * Description:
 * 2016/10/7.
 */
@Service
@Transactional
public class MessageServiceImp implements MessageService{

    @Autowired
    private MessageDao messageDao;

    public List<Object[]> findMessagesByUserId(int id){
        return messageDao.findMessagesByUserId(id);
    }

    public Message findMessageById(int id){
        return messageDao.findMessageById(id);
    }

    public List<Object[]> findAllMessage(){
        return messageDao.findAllMessage();
    }

    public void saveMessage(Message message){
        messageDao.saveMessage(message);
    }

    public void deleteMessage(Message message){
        messageDao.deleteMessage(message);
    }

    public void deleteMessageById(int id){
        deleteMessage(findMessageById(id));
    }
}
