package com.usernameme.community.controller;

import com.usernameme.community.mapper.QuestionMapper;
import com.usernameme.community.mapper.UserMapper;
import com.usernameme.community.model.Question;
import com.usernameme.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 功能描述:
 *
 * @email:username_me@163.com
 * @ClassName: PublishController
 * @Author: hhtc
 * @Date: 2020/3/20 0020 上午 10:06
 * @Version: V
 */
@Controller
public class PublishController {
    /*注入对象*/
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    /*get请求*/
    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    /*post请求*/
    @PostMapping("/publish")
    public String dopublish(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "tag", required = false) String tag,
            HttpServletRequest request,
            Model model
    ) {
        /*将页面输入add到model中，方便保存*/
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);

        /*输入为空时提示*/
        if (title == null || title == "") {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (description == null || description == "") {
            model.addAttribute("error", "内容不能为空");
            return "publish";
        }
        if (tag == null || tag == "") {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }

        /*确保user登录*/
        User user = null;
        //获取cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null & cookies.length != 0) {
            //遍历cookie
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }

        /*向Question中set值*/
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmt_Create(System.currentTimeMillis());
        question.setGmt_Modified(question.getGmt_Create());
        questionMapper.create(question);
        return "redirect:/";
    }
}
