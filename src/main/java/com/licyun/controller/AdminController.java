package com.licyun.controller;

import com.licyun.model.User;
import com.licyun.service.MessageService;
import com.licyun.util.Validate;
import com.licyun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by 李呈云
 * Description:
 * 2016/9/29.
 */
@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private Validate validate;

    @Autowired
    private MessageService messageService;

    //管理员首页
    @RequestMapping(value = "/admin", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String admin(HttpSession session, Model model){
        //判断session
        User sessionUser = (User)session.getAttribute("admin");
        if(sessionUser != null){
            model.addAttribute("users", userService.findAllUser());
            return "admin/index";
        }
        model.addAttribute("users", userService.findAllUser());
        return "admin/index";
    }

    //管理员登录
    @RequestMapping(value = "/admin/login",method = {RequestMethod.GET, RequestMethod.HEAD})
    public String login(HttpSession session, Model model){
        //判断session
        User sessionUser = (User)session.getAttribute("admin");
        if(sessionUser != null){
            model.addAttribute("users", userService.findAllUser());
            return "admin/index";
        }
        model.addAttribute(new User());
        return "admin/login";
    }
    @RequestMapping(value = "/admin/login",method = {RequestMethod.POST, RequestMethod.HEAD})
    public String login(@Valid User user, BindingResult result,
                        HttpSession session, Model model){
        //验证
        validate.loginValidate(user, result);
        if(result.hasErrors()){
            return "admin/login";
        }
        session.setAttribute("admin", user);
        model.addAttribute("users", userService.findAllUser());
        return "admin/index";
    }

    //退出登录
    @RequestMapping(value = "/admin/loginout", method = {RequestMethod.GET, RequestMethod.HEAD})
    public void loginOut(HttpSession session, HttpServletResponse response) throws Exception{
        session.invalidate();
        response.sendRedirect("/admin/login");
    }

    //增加管理员
    @RequestMapping(value = "/admin/add", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String addUser(Model model){
        model.addAttribute("user",new User());
        return "admin/add";
    }
    @RequestMapping(value = "admin/add", method = {RequestMethod.POST, RequestMethod.HEAD})
    public String addUser(@Valid User user, BindingResult result, Model model){
        System.out.println(user.getName());
        validate.registValidate(user, result);
        if(result.hasErrors()){
            return "admin/add";
        }
        user.setType(2);
        userService.saveUser(user);
        model.addAttribute("users", userService.findAllUser());
        return "admin/index";
    }

    //删除用户
    @RequestMapping(value = "/admin/delete-{id}", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String deleteUser(@PathVariable int id, Model model){
        userService.deleteUser(userService.findById(id));
        model.addAttribute("users", userService.findAllUser());
        return "admin/index";
    }

    //修改用户
    @RequestMapping(value = "/admin/edit-{id}", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String editUser(@PathVariable int id,Model model){
        model.addAttribute("user",userService.findById(id));
        return "admin/edit";
    }
    @RequestMapping(value = "/admin/edit-{id}", method = {RequestMethod.POST, RequestMethod.HEAD})
    public String editUser(@Valid User user, @PathVariable int id,
                           BindingResult result, Model model){
        //验证输入格式
        validate.updateValidate(user, id, result);
        if(result.hasErrors()){
            return "admin/edit";
        }
        User sqlUser = userService.findById(id);
        sqlUser.setEmail(user.getEmail());
        sqlUser.setName(user.getName());
        sqlUser.setPasswd(user.getPasswd());
        userService.updateUser(sqlUser);
        model.addAttribute("users", userService.findAllUser());
        return "admin/index";
    }

    //查看用户留言
    @RequestMapping(value = "/admin/message-{id}", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String message(Model model, @PathVariable int id){
        List<Object[]> list = messageService.findMessagesByUserId(id);
        model.addAttribute("messages", list);
        model.addAttribute("id", id);
        return "admin/message";
    }

    //删除用户留言
    @RequestMapping(value = "/admin/deleteMessage-{uid}-{mid}", method = {RequestMethod.GET, RequestMethod.HEAD})
    public void deleteMessage(@PathVariable int mid, @PathVariable int uid,
                              HttpServletResponse response) throws Exception{
        messageService.deleteMessageById(mid);
        response.sendRedirect("/admin/message-"+uid);
    }
}
