package com.licyun.util;

import com.licyun.model.Message;
import com.licyun.model.User;
import com.licyun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 李呈云
 * Description:
 * 2016/9/27.
 */
@Component
public class Validate {

    @Autowired
    private UserService userService;

    public void commonValidate(User user, Errors errors) {
        //判断用户名和邮箱是否为空
        if(user.getName() != "" && user.getEmail() != ""){
            //判断email格式是否则正确
            String pattern = "(.*)@(.).(.*)";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(user.getEmail());
            boolean flag = m.matches();
            if(!flag){
                errors.rejectValue("email", "useremail.valid");
            }
            //判断密码格式是否正确
            if(user.getPasswd().length() < 6)

                errors.rejectValue("passwd", "userpasswd.valid");
        }else{
            ValidationUtils.rejectIfEmpty(errors, "name", "username.required");
            ValidationUtils.rejectIfEmpty(errors, "email", "useremail.required");
        }
    }

    public void registValidate(User user, Errors errors){
        commonValidate(user, errors);
        //判断name格式是否正确
        if(user.getName().length() < 6){
            errors.rejectValue("name", "username.valid");
        }
        //判断邮箱是否已经存在
        if (userService.isUserEmailExist(user.getEmail())) {
            errors.rejectValue("email", "useremail.exist");
        }
    }

    public void loginValidate(User user, Errors errors){
        commonValidate(user, errors);
        User sqlUser = userService.findByEmail(user.getEmail());
        if(sqlUser != null){
            if(sqlUser.getPasswd().equals(user.getPasswd())){

            }else{
                errors.rejectValue("passwd", "userpasswd.error");
            }
        }else{
            errors.rejectValue("email", "useremail.notexist");
        }
    }

    public void updateValidate(User user, int id, Errors errors){
        commonValidate(user, errors);
        User sqlUser = userService.findById(id);
        if( userService.isUserEmailExistExceptSelf(sqlUser.getEmail(), user.getEmail()) ){
            errors.rejectValue("email", "useremail.exist");
        }
    }

    public void messageValidate(Message message, Errors errors){
        if(message.getMessage() != ""){
            if(message.getMessage().length() < 255){

            }else{
                errors.rejectValue("message", "message.required");
            }
        }else{
            ValidationUtils.rejectIfEmpty(errors, "message", "message.required");
        }
    }
}
