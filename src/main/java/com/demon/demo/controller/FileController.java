package com.demon.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * author: xuliang
 * since: 2020/4/20 15:39
 **/
@Controller
@PropertySource("classpath:application.properties")
public class FileController {

    /**
     * 跳转到 index.html
     */
    @RequestMapping(value = "/api/v1/go_page")
    public Object index(){
        return "index";
    }

    @Value("${upload.file.path}")
    private String filePath;

    @RequestMapping("upload")
    @ResponseBody
    public String upload(@RequestParam("head_img")MultipartFile file, HttpServletRequest request){
        String name = request.getParameter("name");
        System.out.println("用户名：" + name);

        String filename = file.getOriginalFilename();
        System.out.println("文件名：" + filename);

        String suffixName = filename.substring(filename.lastIndexOf("."));
        System.out.println("文件的后缀名：" + suffixName);

        filename = filePath + UUID.randomUUID() + suffixName;
        System.out.println("文件上传后路径：" + filename);
        File dest = new File(filename);

        try {
            file.transferTo(dest);
            return filename;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }

}
