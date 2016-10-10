package com.licyun.dao.imp;

import com.licyun.dao.MessageDao;
import com.licyun.model.Message;
import com.licyun.vo.MessageJsonBean;
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
    public List<Message> findMessagesByUserId(int id){
        String hql = "from Message m where m.userid = "+ id +
                "Order by m.id desc";
        //return find(hql, id);
        return findByPage(hql, 1, 2);
    }

    @SuppressWarnings("unchecked")
    public Message findMessageById(int id){
        return get(Message.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<MessageJsonBean> findAllMessage(){
        String hql = "select new com.licyun.vo.MessageJsonBean(" +
                "u.name, m.ip, m.date, m.message, u.imgUrl) " +
                "from User u, Message m where m.userid = u.id Order by m.id desc";
        List<MessageJsonBean> list = (List<MessageJsonBean>) getHibernateTemplate().find(hql);
        return list;
    }

    public void saveMessage(Message message){
        save(message);
    }

    public void deleteMessage(Message message){
        delete(message);
    }

    public Integer findMessageCount(){
        String hql = "select count(*) from Message as message";
        return (Integer)getHibernateTemplate().find(hql).listIterator().next();
    }
}
