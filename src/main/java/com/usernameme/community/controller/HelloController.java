package com.usernameme.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 功能描述:
 *
 * @email:username_me@163.com
 * @ClassName: HelloController
 * @Author: hhtc
 * @Date: 2020/2/19 0019 上午 10:02
 * @Version: V
 */
@Controller
public class HelloController {
    @GetMapping("/hello")
    public String Hello(@RequestParam(name = "name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }
}
