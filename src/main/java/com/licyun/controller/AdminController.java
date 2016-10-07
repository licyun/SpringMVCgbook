package com.licyun.controller;

import com.licyun.model.User;
import com.licyun.vo.UserBean;
import com.licyun.service.UserService;
import com.licyun.util.AdminLoginValid;
import com.licyun.util.RegistValid;
import com.licyun.util.UpdateValid;
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
    private RegistValid registValid;

    @Autowired
    private UpdateValid updateValid;

    @Autowired
    private AdminLoginValid adminLoginValid;

    //管理员首页
    @RequestMapping(value = "/admin", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String admin(HttpSession session, Model model){
        //判断session
        User sessionUser = (User)session.getAttribute("user");
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
        User sessionUser = (User)session.getAttribute("user");
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
        adminLoginValid.validate(user, result);
        if(result.hasErrors()){

            return "admin/login";
        }
        session.setAttribute("user", user);
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
        registValid.validate(user, result);
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
        updateValid.validate(user, result);
        if(result.hasErrors()){
            return "admin/edit";
        }
        //获取修改前的数据
        User updateUser = userService.findById(id);
        updateUser.setName(user.getName());
        updateUser.setEmail(user.getEmail());
        //密码为空则不修改
        System.out.println("length"+user.getPasswd().length());
        if(user.getPasswd().length() != 0)
            updateUser.setPasswd(user.getPasswd());
        userService.updateUser(updateUser);
        model.addAttribute("users", userService.findAllUser());
        return "admin/index";
    }

    //查看用户留言
    @RequestMapping(value = "/admin/message-{id}", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String message(){
        return "admin/message";
    }
}
