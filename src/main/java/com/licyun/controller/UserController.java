package com.licyun.controller;

import com.licyun.model.Message;
import com.licyun.model.User;
import com.licyun.service.MessageService;
import com.licyun.util.UploadImg;
import com.licyun.util.Validate;
import com.licyun.service.UserService;
import com.licyun.vo.MessageJsonBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by 李呈云
 * Description:
 * 2016/9/27.
 */
@Controller
public class UserController {

    //图片路径
    private final String IMGURL = "/WEB-INF/upload";

    @Autowired
    private UploadImg uploadImg;

    @Autowired
    private UserService userService;

    @Autowired
    private Validate validate;

    @Autowired
    private MessageService messageService;

    //首页提交留言
    @RequestMapping(value = {"/", "/index"}, method = {RequestMethod.GET, RequestMethod.HEAD})
    public String index(Model model, HttpSession session){
        List<MessageJsonBean> list = messageService.findAllMessage();
        model.addAttribute("messages", list);
        model.addAttribute("message", new Message());
        model.addAttribute("pageCount", Math.ceil(messageService.findMessageCount() / 5));
        User sessionUser = (User)session.getAttribute("user");
        if(sessionUser != null){
            model.addAttribute("user", sessionUser);
            model.addAttribute("ifLogin", true);
            return "index";
        }
        model.addAttribute("ifLogin", false);
        return "index";
    }
    @RequestMapping(value = {"/", "/index"}, method = {RequestMethod.POST})
    public String index(@Valid Message message,BindingResult result, Model model,
                        HttpSession session, HttpServletRequest request,
                        HttpServletResponse response) throws Exception{
        validate.messageValidate(message, result);
        if(result.hasErrors()){
            return "index";
        }
        List<MessageJsonBean> list = messageService.findAllMessage();
        model.addAttribute("messages", list);
        User sessionUser = (User) session.getAttribute("user");
        message.setUserid(sessionUser.getId());
        message.setDate(messageService.getDate());
        message.setIp(request.getRemoteAddr());
        messageService.saveMessage(message);
        return "index";
//        response.sendRedirect("/");
    }

    @ResponseBody
    @RequestMapping(value = "/messageJson-{page}", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<MessageJsonBean> json(@PathVariable int page){
        List<MessageJsonBean> list = messageService.findMessageByPage(page, 5);
        return list;
    }

    //用户首页
    @RequestMapping(value = {"/user"}, method = {RequestMethod.GET, RequestMethod.HEAD})
    public String userIndex(HttpSession session, Model model){
        User sessionUser = (User)session.getAttribute("user");
        if(sessionUser != null){
            model.addAttribute("user", sessionUser);
            return "user/index";
        }
        model.addAttribute("user", new User());
        return "/user/index";
    }

    //查看用户评论
    @RequestMapping(value = "/user/message-{page}", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String message(HttpSession session, Model model, @PathVariable int page){
        User user = (User)session.getAttribute("user");
        List<Message> list = messageService.findMessagesByUserId(user.getId());
        model.addAttribute("messages", list);
        model.addAttribute("id", user.getId());
        model.addAttribute("count", Math.ceil(messageService.findMessageCount()/8));
        return "/user/message";
    }

    //删除用户评论
    @RequestMapping(value = "/user/deleteMessage-{mid}", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String deleteMessage(HttpSession session, Model model,
                                @PathVariable int mid){
        User user = (User)session.getAttribute("user");
        messageService.deleteMessageById(mid);
        List<Message> list = messageService.findMessagesByUserId(user.getId());
        model.addAttribute("messages", list);
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
        validate.registValidate(user, result);
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
        User sessionUser = (User)session.getAttribute("user");
        if(sessionUser != null){
            model.addAttribute("user", sessionUser);
            return "user/index";
        }
        model.addAttribute("user", new User());
        return "user/login";
    }
    @RequestMapping(value = "/user/login", method = {RequestMethod.POST, RequestMethod.HEAD})
    public String login(@Valid User user, BindingResult result,
                        HttpSession session, Model model){
        //验证
        validate.loginValidate(user, result);
        if(result.hasErrors()){
            return "user/login";
        }
        session.setAttribute("user", userService.findByEmail(user.getEmail()));
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
    public String edit(@Valid User user,@Valid int id, BindingResult result){
        validate.updateValidate(user, id, result);
        if(result.hasErrors()){
            return "user/edit";
        }
        User sqlUser = userService.findById(id);
        sqlUser.setEmail(user.getEmail());
        sqlUser.setName(user.getName());
        sqlUser.setPasswd(user.getPasswd());
        userService.updateUser(sqlUser);
        return "user/index";
    }

    // 修改图片
    @RequestMapping(value = "/user/edit-img", method = RequestMethod.GET)
    public String uploadOneFileHandler(HttpSession session, Model model) {
        //通过session获取user
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "/user/editimg";
    }
    @RequestMapping(value = "/user/edit-img", method = RequestMethod.POST)
    public String uploadFileHandler(HttpServletRequest request, HttpSession session,
                                    @RequestParam("file") MultipartFile file, Model model) {
        //通过session获取user
        User user = (User) session.getAttribute("user");
        // 上传目录
        String rootPath = request.getServletContext().getRealPath(IMGURL);
        System.out.println(rootPath);
        uploadImg.uploadimg(file, user, rootPath);
        model.addAttribute("user", user);
        return "/user/index";
    }

}
