package com.licyun.dao.imp;

import com.licyun.dao.MessageDao;
import com.licyun.model.Message;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 李呈云
 * Description:
 * 2016/10/7.
 */
@Repository
public class MessageDaoImp extends BaseDaoImp<Message> implements MessageDao {

    @SuppressWarnings("unchecked")
    public List<Object[]> findMessagesByUserId(int id){
        String hql = "select m.message, m.id, m.userid from Message m where userid = ?";
        List<Object[]> list = (List<Object[]>) getHibernateTemplate().find(hql, id);
        return list;
    }

    @SuppressWarnings("unchecked")
    public Message findMessageById(int id){
        return get(Message.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> findAllMessage(){
        String hql = "select u.email, m.message from User u, Message m where m.userid = u.id";
        List<Object[]> list = (List<Object[]>) getHibernateTemplate().find(hql);
        return list;
    }

    public void saveMessage(Message message){
        save(message);
    }

    public void deleteMessage(Message message){
        delete(message);
    }
}
