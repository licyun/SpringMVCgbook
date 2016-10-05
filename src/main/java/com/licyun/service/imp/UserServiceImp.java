package com.licyun.service.imp;

import com.licyun.dao.UserDao;
import com.licyun.service.UserService;
import com.licyun.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


/**
 * Created by 李呈云
 * Description:
 * 2016/9/27.
 */
@Service
@Transactional
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

    public User findById(int id){
        return userDao.findById(id);
    }

    public User findByName(String name){

        List<User> users = userDao.findByName(name);

        User user;

            user = (User) users.get(0);

        return  user;
    }

    public User findByEmail(String email){

        List<User> users = userDao.findByEmail(email);

        User user;

        user = (User) users.get(0);

        return  user;
    }

    public void saveUser(User user){
        userDao.saveUser(user);
    }

    public void updateUser( User user){
        userDao.updateUser(user);
    }

    public void deleteUser(User user){
        userDao.deleteUser(user);
    }

    public List<User> findAllUser(){
        return userDao.findAllUser();
    }

    public boolean isUserNameExist(String name){
        List<User> users = findAllUser();
        for(User user : users){
            if(user.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public boolean isUserEmailExist(String email){
        System.out.println("find all user");
        List<User> users = findAllUser();
        for(User user : users){
            if(user.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }

}
