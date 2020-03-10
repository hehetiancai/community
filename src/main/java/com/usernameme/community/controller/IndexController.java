package com.usernameme.community.controller;

import com.usernameme.community.mapper.UserMapper;
import com.usernameme.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

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
public class IndexController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie:cookies){
            if (cookie.getName().equals("token")){
                String token = cookie.getValue();
                User user = userMapper.findByToken(token);
                if (user !=null){
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }
        return "index";

    }
}
