package com.licyun.service;

import com.licyun.model.User;
import com.licyun.vo.MessageJsonBean;

import java.util.List;


/**
 * Created by 李呈云
 * Description:
 * 2016/9/27.
 */

public interface UserService {

    User findById(int id);

    User findByName(String name);

    User findByEmail(String email);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(User user);

    Long findUserCount();

    List<User> findUserByPage(int pageNo, int pageSize);

    List<User> findAllUser();

    boolean isUserEmailExist(String email);

    boolean isUserEmailExistExceptSelf(String sqlEmail, String localEmail);

}
