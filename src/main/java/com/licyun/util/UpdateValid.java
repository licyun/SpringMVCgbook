package com.licyun.util;

import com.licyun.model.User;
import com.licyun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
        User user = (User) target;
        //判断用户名和邮箱是否为空
        if(user.getName() != "" && user.getEmail() != ""){
            //判断name格式是否正确
            if(user.getName().length() < 6){
                errors.rejectValue("name", "username.valid");
            }
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
}
