package com.licyun.dao;

import com.licyun.model.User;

import java.util.List;

/**
 * Created by 李呈云
 * Description:
 * 2016/10/5.
 */
public interface UserDao {


    User findById(int id);

    List<User> findByName(String name);

    List<User> findByEmail(String email);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(User user);

    List<User> findAllUser();

}
