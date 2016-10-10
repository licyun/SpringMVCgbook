package com.licyun.dao.imp;

import com.licyun.dao.MessageDao;
import com.licyun.model.Message;
import com.licyun.vo.MessageJsonBean;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
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
        return find(hql);
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

    public Long findMessageCount(){
        String hql = "select count(*) from Message as message";
        return (Long)getHibernateTemplate().find(hql).listIterator().next();
    }

    @SuppressWarnings("unchecked")
    public List<MessageJsonBean> findMessageByPage(final int pageNo,final int pageSize ){

        final String hql = "select new com.licyun.vo.MessageJsonBean(" +
                "u.name, m.ip, m.date, m.message, u.imgUrl) " +
                "from User u, Message m where m.userid = u.id Order by m.id desc";

        List<MessageJsonBean> list = (List<MessageJsonBean>) getHibernateTemplate().execute(new HibernateCallback<List<MessageJsonBean>>() {

            @SuppressWarnings("unchecked")
            public List<MessageJsonBean> doInHibernate(Session session) throws HibernateException {
                // TODO Auto-generated method stub
                List<MessageJsonBean> result = session.createQuery(hql).setFirstResult((pageNo-1)*pageSize).setMaxResults(pageSize).list();
                return result;
            }
        });
        return list;
    }
}
