package com.licyun.util;

import com.licyun.model.User;
import com.licyun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by 李呈云
 * Description:
 * 2016/10/9.
 */
@Component
public class UploadImg {

    @Autowired
    private UserService userService;

    public void uploadimg(MultipartFile file, User user, String rootPath){
        // 上传目录
        File uploadRootDir = new File(rootPath);
        // 创建文件夹
        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }
        String name = file.getOriginalFilename();
        if(name != ""){
            String imgurl = rootPath +  File.separator + user.getImgUrl();
            File imgFile = new File(imgurl);
            imgFile.delete();
        }
        user.setImgUrl(name);
        userService.updateUser(user);
        System.out.println("Client File Name = " + name);
        if (name != null && name.length() > 0) {
            try {
                byte[] bytes = file.getBytes();
                // 创建跨平台的路径
                File serverFile = new File(uploadRootDir.getAbsolutePath()
                        + File.separator + name);
                // Stream to write data to file in server.
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                System.out.println("Write file: " + serverFile);
            } catch (Exception e) {
                System.out.println("Error Write file: " + name);
            }
        }
    }
}
