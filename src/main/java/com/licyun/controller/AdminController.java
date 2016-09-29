package com.licyun.controller;

import com.licyun.model.User;
import com.licyun.service.UserService;
import com.licyun.util.RegistValid;
import com.licyun.util.UpdateValid;
import org.jboss.logging.annotations.ValidIdRange;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by 李呈云
 * Description:
 * 2016/9/29.
 */
@Controller
public class AdminController {

    private UserService userService;
    private RegistValid registValid;
    private UpdateValid updateValid;
    private User user;

    //管理员首页
    @RequestMapping(value = "/admin", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String admin(Model model){
        UserService userService = new UserService();
        model.addAttribute("users", userService.getAllUsers());
        return "admin/index";
    }

    //增加用户
    @RequestMapping(value = "/admin/add", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String addUser(Model model){
        model.addAttribute("user",new User());
        return "admin/add";
    }
    @RequestMapping(value = "admin/add", method = {RequestMethod.POST, RequestMethod.HEAD})
    public String addUser(@Valid User user, BindingResult result, Model model){
        registValid = new RegistValid();
        userService = new UserService();
        registValid.validate(user, result);
        if(result.hasErrors()){
            return "admin/add";
        }
        userService.saveUser(user);
        model.addAttribute("users", userService.getAllUsers());
        return "admin/index";
    }

    //删除用户
    @RequestMapping(value = "/admin/delete-{id}", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String deleteUser(@PathVariable Long id, Model model){
        userService = new UserService();
        userService.deleteUser(userService.findById(id));
        model.addAttribute("users", userService.getAllUsers());
        return "/admin/index";
    }

    //修改用户
    @RequestMapping(value = "/admin/edit-{id}", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String editUser(@PathVariable Long id,Model model){
        userService = new UserService();
        model.addAttribute("user",userService.findById(id));
        return "/admin/edit";
    }
    @RequestMapping(value = "/admin/edit-{id}", method = {RequestMethod.POST, RequestMethod.HEAD})
    public String editUser(@Valid User user, @PathVariable Long id,
                           BindingResult result, Model model){
        updateValid = new UpdateValid();
        userService = new UserService();
        updateValid.validate(user, result);
        if(result.hasErrors()){
            return "/admin/edit";
        }
        userService.updateUser(id, user);
        model.addAttribute("users", userService.getAllUsers());
        return "/admin/index";
    }

    //查看用户留言
    @RequestMapping(value = "/admin/message-{id}", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String message(){
        return "/admin/message";
    }
}
