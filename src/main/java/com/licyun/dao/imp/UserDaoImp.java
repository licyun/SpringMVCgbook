package com.licyun.dao.imp;

import com.licyun.dao.UserDao;
import com.licyun.model.User;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 李呈云
 * Description:
 * 2016/10/5.
 */
@Repository
public class UserDaoImp extends BaseDaoImp<User> implements UserDao {

    public User findById(int id){
        return get(User.class, id);
    }

    public List<User> findByName(String name){

        return find("from User where name =?", name);

    }

    public List<User> findByEmail(String email){

        return find("from User where email =?", email);

    }

    public void saveUser(User user){
        save(user);
    }

    public void updateUser(User user){
        update(user);
    }

    public void deleteUser(User user){
        delete(user);
    }

    public List<User> findAllUser(){
        DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
        return (List<User>) getHibernateTemplate().findByCriteria(criteria);
    }
}
