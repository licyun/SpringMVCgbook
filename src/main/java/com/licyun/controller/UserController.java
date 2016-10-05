package com.licyun.controller;

import com.licyun.model.User;
import com.licyun.service.UserService;
import com.licyun.util.LoginValid;
import com.licyun.util.RegistValid;
import com.licyun.util.UpdateValid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by 李呈云
 * Description:
 * 2016/9/27.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UpdateValid updateValid;

    @Autowired
    private RegistValid registValid;

    @Autowired
    private LoginValid loginValid;

    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String index(){
        return "index";
    }

    //用户首页
    @RequestMapping(value = {"/user"}, method = {RequestMethod.GET, RequestMethod.HEAD})
    public String userIndex(){
        return "/user/index";
    }

    //用户评论
    @RequestMapping(value = "/user/message", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String message(){
        return "/user/message";
    }


    //注册
    @RequestMapping(value = {"/user/register"}, method = {RequestMethod.GET, RequestMethod.HEAD})
    public String register(Model model){
        model.addAttribute(new User());
        return "user/register";
    }
    @RequestMapping(value = {"/user/register"}, method = {RequestMethod.POST, RequestMethod.HEAD})
    public String register(@Valid  User user,BindingResult result){
        registValid = new RegistValid();
        registValid.validate(user, result);
        if(result.hasErrors()){
            return "user/register";
        }
        user.setType(1);
        userService.saveUser(user);
        return "user/index";
    }

    //登录
    @RequestMapping(value = "/user/login", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String login(Model model, HttpSession session){
        //判断session
        String userSession = (String)session.getAttribute("user");
        if(userSession != null){
            model.addAttribute("user", userService.findByEmail(userSession));
            return "user/index";
        }
        model.addAttribute("user", new User());
        return "user/login";
    }
    @RequestMapping(value = "/user/login", method = {RequestMethod.POST, RequestMethod.HEAD})
    public String login(@Valid User user, BindingResult result,
                        HttpSession session, Model model){
        //验证
        loginValid.validate(user, result);
        if(result.hasErrors()){
            return "user/login";
        }
        session.setAttribute("user", user.getEmail());
        model.addAttribute("user", userService.findByEmail(user.getEmail()));
        return "user/index";
    }

    //退出登录
    @RequestMapping(value = "/user/loginout", method = {RequestMethod.GET, RequestMethod.HEAD})
    public void loginOut(HttpSession session, HttpServletResponse response) throws Exception{
        session.invalidate();
        response.sendRedirect("/user/login");
    }

    //编辑
    @RequestMapping(value = "/user/edit-{id}", method = {RequestMethod.GET, RequestMethod.HEAD})
         public String edit(@PathVariable int id, Model model){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user/edit";
    }
    @RequestMapping(value = "/user/edit-{id}", method = {RequestMethod.POST, RequestMethod.HEAD})
    public String edit(@Valid User user,@Valid Long id, BindingResult result){
        updateValid = new UpdateValid();
        updateValid.validate(user, result);
        if(result.hasErrors()){
            return "user/edit";
        }
        userService.updateUser(user);
        return "user/index";
    }
}
