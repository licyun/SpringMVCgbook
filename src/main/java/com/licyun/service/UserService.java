package com.licyun.service;

import com.licyun.vo.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by 李呈云
 * Description:
 * 2016/9/27.
 */
@Service
public class UserService {

    private static final AtomicLong counter = new AtomicLong();

    private static List<User> users = null;

    static{
        users= populateDummyUsers();
    }

    public List<User> getAllUsers(){
        return users;
    }

    public User findById(Long id){
        for(User user: users){
            if(user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }

    public User findByName(String name){
        for(User user: users){
            if(user.getName().equals(name)){
                return user;
            }
        }
        return null;
    }

    public User findByEmail(String email){
        for(User user: users){
            if(user.getEmail().equals(email)){
                return user;
            }
        }
        return null;
    }

    public void saveUser(User user){
        user.setId(counter.incrementAndGet());
        users.add(user);
    }

    public void updateUser(Long id, User user){
        User originuser = findById(id);
        int index = users.indexOf(originuser);
        users.set(index, user);
    }

    public void deleteUser(User user){
        for(Iterator<User> iterator = users.iterator(); iterator.hasNext();){
            User iuser = iterator.next();
            if(iuser.getId() == user.getId()){
                iterator.remove();
            }
        }
    }

    public boolean isUserNameExist(String name){
        for(User user : users){
            if(user.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public boolean isUserEmailExist(String email){
        for(User user : users){
            if(user.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }

    private static List<User> populateDummyUsers(){
        List<User> users = new ArrayList<User>();

        users.add(new User((counter.incrementAndGet()),"licyun", "mzkwy@outlook.com", "123456", 1));
        users.add(new User(counter.incrementAndGet(),"lichengyun", "849528477@qq.com", "123456"));
        return users;
    }
}
