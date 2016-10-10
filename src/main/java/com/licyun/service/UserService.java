package com.licyun.service;

import com.licyun.model.User;
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

    Integer findUserCount();

    List<User> findAllUser();

    boolean isUserEmailExist(String email);

    boolean isUserEmailExistExceptSelf(String sqlEmail, String localEmail);

}
