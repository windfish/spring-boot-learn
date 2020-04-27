package com.demon.demo.controller;

import com.demon.demo.settings.ServerSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * author: xuliang
 * since: 2020/4/27 14:14
 **/
@Controller
@RequestMapping("/freemarker")
public class FreemarkerController {

    @Autowired
    private ServerSettings serverSettings;

    @GetMapping("hello")
    public String index(ModelMap modelMap){
        modelMap.addAttribute("setting", serverSettings);

        return "fm/index";      // 不需要加后缀，在配置里面已经指定了后缀
    }

}
