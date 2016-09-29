package com.licyun.util;

import com.licyun.model.User;
import com.licyun.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 李呈云
 * Description:
 * 2016/9/27.
 */
@Component
public class UpdateValid implements Validator {
    public boolean supports(Class<?> klass) {
        return User.class.isAssignableFrom(klass);
    }

    public void validate(Object target, Errors errors) {
        UserService userService = new UserService();
        User user = (User) target;
        User originuser = userService.findById(user.getId());

        //判断用户名和邮箱是否为空
        ValidationUtils.rejectIfEmpty(errors, "name", "username.required");
        ValidationUtils.rejectIfEmpty(errors, "email", "useremail.required");
        //判断格式是否正确
        if(user.getName().length() < 6){
            errors.rejectValue("name", "username.valid");
        }
        String pattern = "(.*)@(.).(.*)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(user.getEmail());
        boolean flag = m.matches();
        if(!flag){
            errors.rejectValue("email", "useremail.valid");
        }

        //判断密码
        if(user.getPasswd() == ""){
            user.setPasswd(originuser.getPasswd());
        }else{
            if(user.getPasswd().length() < 8){
                errors.rejectValue("passwd", "userpasswd.valid");
            }
        }

        //判断邮箱和用户名是否已经存在
        if(originuser.getEmail().equals(user.getEmail())){

        }else{

            if (userService.isUserEmailExist(user.getEmail())) {
                errors.rejectValue("email", "useremail.exist");
            }
        }

        if(originuser.getName().equals(user.getName())){

        }else{
            if(userService.isUserNameExist(user.getName())) {
                errors.rejectValue("name", "username.exist");
            }
        }
    }
}
